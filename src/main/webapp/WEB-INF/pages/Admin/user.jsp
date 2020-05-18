<%-- 
    Document   : user
    Created on : Jan 24, 2019, 12:09:12 PM
    Author     : rasel
--%>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>

<tiles:insertDefinition name="DefaultTemplate">
    <tiles:putAttribute name="body">

        <!-- Content -->
        <div class="content">
            <sf:form action="${action}" modelAttribute="${model}" id="fUserForm">

            </sf:form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>