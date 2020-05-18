/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

import com.civil.detail.UserDetail;
import com.civil.model.Page;
import com.civil.model.Role;
import com.civil.model.User;
import com.civil.viewClass.UserViewClass;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

/**
 *
 * @author rasel
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     *
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {

//          User u = new User();
//        u.setEmail(username);
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        passwordEncoder.encode("123456");
//        u.setPasswordHash(passwordEncoder.encode("123456"));
//        UserRole ur = new UserRole();
//        ur.setName("RoleAdmin");
//        ur.setUser(u);
        List<User> users = new ArrayList<User>();

//        Session s = sessionFactory.getCurrentSession();
        //   User u = (User) currentSession().createQuery("SELECT a FROM User AS a WHERE a.userName ='" + username + "'").uniqueResult();
        users = currentSession().createQuery("from User where email=:email and status = 1").setParameter("email", username).list();
        System.out.println("done1");

        if (users.size() > 0) {

            Hibernate.initialize(users.get(0).getRole());
            Hibernate.initialize(users.get(0).getEmployee());

            return users.get(0);
        } else {
            return null;
        }

        //  Set<UserRole> urs = new HashSet<UserRole>(0);
        //  urs.add(ur);
        //   u.setUserRoles(urs);
        //  return u;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public User getUserByEmail(String email) {

        List<User> users = sessionFactory.getCurrentSession().createQuery("SELECT u FROM User AS u WHERE u.email='" + email + "' ").list();
        if (users.size() > 0) {
            Hibernate.initialize(users.get(0).getEmployee());
            Hibernate.initialize(users.get(0).getRole());
            return users.get(0);
        }
        return null;
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserWithEmployeeInfo(int userId) {
        User user = find(userId);
        if (user != null) {
            Hibernate.initialize(user.getEmployee());

        }
        return user;

    }

    /**
     *
     * @param skip
     * @param take
     * @return
     */
    @Override
    public List<UserViewClass> getAllUser(int skip, int take) {
        List<User> users;
        List<UserDetail> userDetails = new ArrayList<>();
        users = currentSession().createQuery("SELECT u FROM User  AS u  order by u.id desc,u.fullName asc").setFirstResult(skip).setMaxResults(take).list();
        String query = "select u.id userId, u.fullname fullName,u.email email,u.status status,r.id roleid,r.name rolename,e.id empid,e.post designation from user u "
                + "	left join role r on u.role_id = r.id "
                + "	left join employee e on u.emp_id= e.id ";

        List<UserViewClass> userViewClass = currentSession().createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(UserViewClass.class)).list();
        //   int count = ((Number) currentSession().createQuery("SELECT COUNT(u.id) FROM User  AS u where u.isDelete = false").uniqueResult()).intValue();

//        setCount(count);
//
//        users.forEach(userInfo
//                -> {
//            Hibernate.initialize(userInfo.getEmployee());
//            Hibernate.initialize(userInfo.getReporterType());
//            Hibernate.initialize(userInfo.getRole());
//            userDetails.add(UserConverter.getDetail(userInfo));
//        }
//        );
        return userViewClass;
    }

    @Override
    public List<UserDetail> getAllUserByRole(Integer value) {
        List<User> users = currentSession().createQuery("SELECT u FROM User AS u  order by u.id desc,u.fullname asc").list();
//        employeeCertifications.forEach(a -> {
//            employeeCertificationsDetails.add(EmployeeCertificationsConverter.getDetail(a));
//        });
        List<UserDetail> userDetails = new ArrayList<UserDetail>();
        users.forEach(a -> {

        });
        return userDetails;

    }

    @Override
    public List<User> getAllUsersByReporterType(Integer id) {
        List<User> users = currentSession().createQuery("SELECT u FROM User AS u where u.reporterType.id = :id  order by u.id desc,u.fullname asc").setParameter("id", id).list();

        users.forEach(u
                -> {
                    Hibernate.initialize(u.getEmployee());

                }
        );
        return users;

    }

    @Override
    public User getCNEUser() {
        return (User) currentSession().createQuery("SELECT u FROM User AS u WHERE u.reporterType.name ='CNE' order by u.id desc,u.fullname asc").uniqueResult();
    }

    @Override
    public List<Page> getAllParentPageFroUser(int id) {
        Role r = (Role) currentSession().createQuery("SELECT a.role FROM UserRole AS a WHERE a.user.id =:userId ").setParameter("userId", id).uniqueResult();
        List<Page> parentPage = currentSession().createQuery("SELECT rp.page FROM RolePage rp WHERE rp.role.id =:roleId AND rp.page.type = 3 order by rp.page.position").setParameter("roleId", r.getId()).list();

//        for (Page p : parentPage) {
//            List<Page> menuPage = currentSession().createQuery("SELECT rp FROM RolePage rp WHERE RP.page =:parentId and rp.page.type = 3").setParameter("parentId", p.getId()).list();
//
//        }
        return parentPage;
    }

    @Override
    public List<Page> getAllMenuPageForParentPage(int parenId) {

        List<Page> menuPage = currentSession().createQuery("SELECT rp FROM RolePage rp WHERE RP.page =:parentId and rp.page.type = 3").setParameter("parentId", parenId).list();

        return menuPage;
    }

    public User getUserById(int uId) {

        User u = find(uId);

        return u;
    }
}
