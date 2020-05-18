
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"/>
<head>


    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <!-- responsive screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${Contexpath}/resources/css/bootstrap.min.css"/>

    <!-- Font Awesome -->
    <link rel="stylesheet" href="${Contexpath}/resources/font-awesome/css/font-awesome.min.css"/>
    <!-- Ionicons -->

    <!-- Custom Stylesheet -->
    <link rel="stylesheet" href="${Contexpath}/resources/css/news.css" type="text/css" />
    
    <link rel="stylesheet" href="${Contexpath}/resources/css/skins/_all-skins.css"/>
    <!-- Google Font -->
    <link rel="stylesheet" href="${Contexpath}/resources/css/editor.css"/>

    <link rel="stylesheet" href="${Contexpath}/resources/datatables.net-bs/css/dataTables.bootstrap.min.css"/>


    <!-- jQuery 3 -->
    <script src="${Contexpath}/resources/js/jquery.min.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="${Contexpath}/resources/js/bootstrap.min.js"></script>
   


    <script src="${Contexpath}/resources/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="${Contexpath}/resources/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>


    
     <!-- iclique App -->
    <script src="${Contexpath}/resources/js/news.min.js"></script>


    <script type="text/javascript">
        $(function () {




        });

        $(".block1").click(function () {

        });


        $(".error").click(function () {
            toastr.error('You Got Error', 'Inconceivable!', {
                timeOut: 2000,
                closeButton: true,
                progressBar: true,
                positionClass: "toast-top-center"
            });
        });


        $(".info").click(function () {
            toastr.info('It is for your kind information', 'Information', {
                timeOut: 2000,
                closeButton: true,
                progressBar: true,
                positionClass: "toast-top-center"
            });
        });


        $("#warningalert").click(function () {
            toastr.warning('Please Check Information', 'Warning', {
                timeOut: 2000,
                closeButton: true,
                progressBar: true,
                positionClass: "toast-top-center"
            });
        });

    </script>
</head>
<body  class="hold-transition skin-teal fixed sidebar-mini" >
    <div class="wrapper">

        <!-- Left side Menu -->

        <tiles:insertAttribute name="header"/> 

        <!-- Left side Menu -->


        <!-- Left side column. contains the sidebar -->

        <tiles:insertAttribute name="menu" />                  

        <!-- =============================================== -->
        <div class="content-wrapper">
            <tiles:insertAttribute name="body"/> 
        </div>
        <tiles:insertAttribute name="footer" />
    </div>

</body>



</html>