/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

import com.civil.converter.ProjectsItemHistoryConverter_;
import com.civil.detail.ProjectsItemDetail;
import com.civil.detail.ProjectsItemHistoryDetail;

import com.civil.model.ProjectsItem;
import com.civil.model.ProjectsItemHistory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rasel
 */
@Repository
public class ProjectsItemHistoryDaoImpl extends GenericDaoImpl<ProjectsItemHistory, Integer> implements ProjectsItemHistoryDao {

    @Autowired
    ProjectItemDao projectItemDao;

    @Override
    public void addList(List<ProjectsItemHistoryDetail> projectsItemHistoryDetails) {

        if (projectsItemHistoryDetails.size() > 0) {
            int projectId = projectsItemHistoryDetails.get(0).getProjectId();
            int itemId = projectsItemHistoryDetails.get(0).getItemId();
            ProjectsItem projectsItem = (ProjectsItem) currentSession().createQuery("SELECT pi FROM ProjectsItem AS pi WHERE pi.item.id =:itemId AND pi.project.id =:projectId").setParameter("projectId", projectId).setParameter("itemId", itemId).uniqueResult();

            currentSession().createQuery("DELETE FROM ProjectsItemHistory pih WHERE pih.projectsItem.id =:projectItemId").setParameter("projectItemId", projectsItem.getId()).executeUpdate();;

            String insertQuery = "insert into projects_item_history (name,`int1`,`int2`,`int3`,`ft_in`,title,isAddition,`group_name`,project_item_id) \n"
                    + "	VALUES ";

            String summary_addition = "";
            String summary_deduction = "";
            String grp = "";
            float subtotal = 0;
            float add_subtotal = 0;
            float dd_subtotal = 0;
            for (ProjectsItemHistoryDetail rp : projectsItemHistoryDetails) {
                insertQuery = insertQuery + "('" + rp.getName() + "'"
                        + "," + rp.getIn1()
                        + "," + rp.getIn2()
                        + "," + rp.getIn3()
                        + ",'" + rp.getFtIn()+"'"
                        + ",'" + rp.getTitle() + "'"
                        + "," + rp.getIsAddition()
                        + ",'" + rp.getGroup() + "'"
                        + "," + projectsItem.getId()
                        + "),";

                if ("".equals(grp)) {
                    grp = rp.getTitle();
                }
                if (rp.getIsAddition() == 1) {
                    if (!grp.equals(rp.getTitle())) {
                        summary_addition = summary_addition + "<div class='row'> <div class='col-xl-8  col-sm-8' style='font-size:15px;font-weight:bold'>" + "Sub Total </div><div class='col-xl-4  col-sm-4'>= " + subtotal + "</div> </div> <br/>\n";
                        add_subtotal = add_subtotal + subtotal;
                        subtotal = 0;
                        grp = rp.getTitle();

                    }

                    float result = rp.getIn1() * rp.getIn2() * rp.getIn3() * getFloatFromFtIn(rp.getFtIn());
                    subtotal = subtotal + result;
                    summary_addition = summary_addition + "<div class='row'> <div class='col-xl-4  col-sm-4'>" + rp.getName() + "</div> <div class='col-xl-4  col-sm-4'> " + rp.getIn1() + "*" + rp.getIn2() + "*" + rp.getIn3() + "*" + rp.getFtIn() + "</div><div class='col-xl-4  col-sm-4'> = " + result + "</div></div>\n";

                } else {
                    if (summary_deduction == "" && summary_addition != "") {
                        summary_addition = summary_addition + "<div class='row'><div class='col-xl-8  col-sm-8' style='font-size:15px;font-weight:bold'>" + "Sub Total </div><div class='col-xl-4  col-sm-4'>= " + subtotal + "</div></div><br/>";
                        add_subtotal = add_subtotal + subtotal;
                        subtotal = 0;
                        grp = rp.getTitle();
                    }

                    if (!grp.equals(rp.getTitle())) {
                        summary_deduction = summary_deduction + "<div class='row'><div class='col-xl-8  col-sm-8'>" + "Sub Total </div><div class='col-xl-4  col-sm-4' style='font-size:15px;font-weight:bold'>= " + subtotal + "</div></div>\n\n";
                        dd_subtotal = dd_subtotal + subtotal;
                        subtotal = 0;
                        grp = rp.getTitle();

                    }
                    float result = rp.getIn1() * rp.getIn2() * rp.getIn3()  * getFloatFromFtIn(rp.getFtIn());
                    subtotal = subtotal + result;
                    summary_deduction = summary_deduction + "<div class='row'><div class='col-xl-4  col-sm-4'>" + rp.getName() + "</div> <div class='col-xl-4  col-sm-4'> " + rp.getIn1() + "*" + rp.getIn2() + "*" + rp.getIn3() + "*" + rp.getFtIn() + "</div><div class='col-xl-4  col-sm-4'> = " + result + "</div></div>\n";

                }
            }

            if (!"".equals(summary_deduction)) {
                summary_deduction = summary_deduction + "<div class='row'><div class='col-xl-8  col-sm-8' style='font-size:15px;font-weight:bold' style='font-size:15px;font-weight:bold'>" + "Sub Total </div><div class='col-xl-4  col-sm-4'>= " + subtotal + "</div></div><br/>";
                dd_subtotal = dd_subtotal + subtotal;
                subtotal = 0;

            } else if (!"".equals(summary_addition)) {
                summary_addition = summary_addition + "<div class='row'><div class='col-xl-8  col-sm-8'>" + "Sub Total </div><div class='col-xl-4  col-sm-4' style='font-size:15px;font-weight:bold'> = " + subtotal + "</div></div><br/>";
                add_subtotal = add_subtotal + subtotal;
                subtotal = 0;
            }

            String Summary = summary_addition + "\n\n\n" + summary_deduction + "\n\n" + "<div class='row'> <div class='col-xl-8  col-sm-8' style='font-size:15px;font-weight:bold'> Diff </div><div class='col-xl-4  col-sm-4'>" + (add_subtotal - dd_subtotal);

            insertQuery = insertQuery.substring(0, insertQuery.length() - 1) + ";";

            currentSession().createSQLQuery(insertQuery).executeUpdate();
            projectsItem.setCalculationSummary(Summary);
            currentSession().saveOrUpdate(projectsItem);

        }
    }

    private float getFloatFromFtIn(String ftin_str) {
        float fitIn = 0;
        if (ftin_str != null || !"".equals(ftin_str)) {

            float ft_float = Float.parseFloat(ftin_str.split("-")[0]);
            float inc_float = 0;
            if (ftin_str.split("-").length > 1) {
                inc_float = Float.parseFloat(ftin_str.split("-")[1]);
            }
            fitIn = ft_float + (inc_float / 12);
        }
        return fitIn;
    }

    @Override
    public List<ProjectsItemHistoryDetail> getAllProjectItemDetailHistoyByProjectIdAndItemId(int projectId, int itemId) {
        List<ProjectsItemHistoryDetail> projectsItemlHistoryDetails = new ArrayList<>();
        ProjectsItemDetail projectsItem = projectItemDao.getProjectItemByProjectIdAndItemId(projectId, itemId);

        List<ProjectsItemHistory> projectsItemHistorys = currentSession().createQuery("SELECT a FROM ProjectsItemHistory a WHERE a.projectsItem.id =:projectsItemId").setParameter("projectsItemId", projectsItem.getId()).list();
        projectsItemHistorys.forEach(a -> {
            projectsItemlHistoryDetails.add(ProjectsItemHistoryConverter_.getDetail(a));
        });

        return projectsItemlHistoryDetails;
    }

}
