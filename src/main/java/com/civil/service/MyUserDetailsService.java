package com.civil.service;

import com.civil.detail.PageDetail;
import com.civil.config.AuthorityInfo;
import com.civil.dao.UserDao;
import com.civil.model.Role;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rasel
 */
          
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PageService pageService;

    @Autowired
    private HttpServletRequest request;



    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        com.civil.model.User user = userDao.findByUserName(username);
        List<AuthorityInfo> authorities = new ArrayList<AuthorityInfo>();

        authorities = buildUserAuthority(user.getRole());

        UserDetails u = buildUserForAuthentication(user, authorities);

      
        return u;

    }

    // Converts com.doctorprescription.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(com.civil.model.User user, List<AuthorityInfo> authorities) {
        return new User(user.getEmail(), user.getPasswordHash(), true, true, true, true, authorities);
    }

    private List<AuthorityInfo> buildUserAuthority(Role role) {
        List<AuthorityInfo> authorityInfos = new ArrayList<AuthorityInfo>();
        if (role == null) {
            return authorityInfos;
        }

        List<PageDetail> pageDetails = pageService.getAllPageByRoleId(role.getId());
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Map<String, Integer> urlMap = new HashMap<String, Integer>();

        for (PageDetail pageDetail : pageDetails) {

            if (pageDetail.getType() == 0) {
                continue;
            }
            if (urlMap.get(pageDetail.getUrl()) == null) {
                AuthorityInfo a = new AuthorityInfo(pageDetail.getUrl());

                a.setDisplayName(pageDetail.getName());

                a.setStatus(pageDetail.getType());

                authorityInfos.add(a);
                urlMap.put(pageDetail.getUrl(), 1);
            }
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return authorityInfos;
    }

}
