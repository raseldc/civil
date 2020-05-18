/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

import com.civil.converter.ItemConverter;
import com.civil.detail.ItemDetail;
import com.civil.model.Item;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static javassist.CtMethod.ConstParameter.string;
import org.hibernate.Hibernate;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rasel
 */
@Repository
public class ItemDaoImpl extends GenericDaoImpl<com.civil.model.Item, Integer> implements ItemDao {

    @Override
    public ItemDetail getItemDetailByItemCode(String code) {
        Item item = (Item) currentSession().createQuery("SELECT item FROM Item as item WHERE item.code =:code").setParameter("code", code).uniqueResult();
        ItemDetail itemDetail = ItemConverter.getDetail(item);
        return itemDetail;
    }

    @Override
    public List<ItemDetail> getItemDetailList(int skip, int take) {
       // itemFurnish();
        String query = "select i.id,i.code,i.description,i.unit,i.price,i.parent_code,i.parent_id from item i";
        List<ItemDetail> ItemDetail_ = currentSession().createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(ItemDetail.class)).list();

        return ItemDetail_;
    }

    @Override
    public String insertItemBatch(final List<ItemDetail> itemDetails) {

        currentSession().doWork(
                new Work() {

            public void execute(Connection connection) throws SQLException {

                java.sql.PreparedStatement pstmt = null;
                try {
                    String sqlInsert = "INSERT IGNORE   INTO `item` (`code`, `description`, `unit`, `price`,`parent_code`)  VALUES (?,?,?,?,?)";
                    pstmt = connection.prepareStatement(sqlInsert);

                    int i = 0;
                    for (ItemDetail t : itemDetails) {
                        pstmt.setString(1, t.getCode());
                        pstmt.setString(2, t.getDescription());
                        pstmt.setString(3, t.getUnit());
                        pstmt.setString(4, t.getPrice() == null ? null : t.getPrice().toString());
                        pstmt.setString(5, t.getParent_code());

                        pstmt.addBatch();
                        if (i % 20 == 0) {
                            try {
                                pstmt.executeBatch();
                            } catch (Exception e) {
                                System.out.println("error messege" + e.getMessage());
                                //  msg += e.getMessage() + " NID : " + pstmt.toString().split("VALUES")[1].split(",")[5] + "\n";

                            }
                        }
                        i++;

                    }

                    try {
                        pstmt.executeBatch();
                    } catch (Exception e) {
                        System.out.println("error messege" + e.getMessage());
                        //   msg += e.getMessage() + " NID : " + pstmt.toString().split("VALUES")[1].split(",")[5] + "\n";

                    }
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    //    Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    pstmt.close();

                }
            }
        }
        );
        return "";
    }

    public void itemFurnish() {
        List<Item> items = currentSession().createQuery("SELECT i FROM Item i ").list();

        for (Item item : items) {
            if (item.getCode().equals("civil-")) {
                item.setShowCode(item.getParentCode());
                Hibernate.initialize(item.getItem());
                String des = "";
                if (item.getItem() != null) {
                    Hibernate.initialize(item.getItem().getItem());
                    if (item.getItem().getItem() != null) {
                        des = item.getItem().getItem().getDescription() + "\n";
                    }
                    des = des + item.getItem().getDescription() + "\n";
                }
                des = des + item.getDescription();
                item.setParentItemDescription(des);
            } else {
                item.setShowCode(item.getCode());
            }            
            currentSession().saveOrUpdate(item);            
            
        }
    }
}
