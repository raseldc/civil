<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>

<tiles:insertDefinition name="DefaultTemplate">
    <tiles:putAttribute name="body">
        <style>
            .div_size{
                height: 150px
            }
            .pe-7s-note2 {
                color: #fff;
            }
            .pe-7s-id{color: #fff;}

            .pe-7s-users{
                color: #fff;
            }
        </style>
        <!-- Content -->
        <section class="content">
            <!-- Content Section -->

            <div class="row">
                
            </div>
            <!-- /.Content Section -->
        </section>
        <!-- /.content -->
        <!--<script src="${Contexpath}/custom/dashboard.js"></script>-->
    </tiles:putAttribute>
</tiles:insertDefinition>