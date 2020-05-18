<%-- 
    Document   : register
    Created on : Jan 28, 2019, 4:32:30 PM
    Author     : rasel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>News Management Register</title>
        <meta name="description" content="Training Management ">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="apple-touch-icon" href="https://i.imgur.com/QRAUqs9.png">
        <link rel="shortcut icon" href="https://i.imgur.com/QRAUqs9.png">
        <link rel="stylesheet" href="${Contexpath}/resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${Contexpath}/resources/font-awesome/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="${Contexpath}/resources/css/ionicons.min.css"/>
        <link rel="stylesheet" href="${Contexpath}/resources/css/news.css"/>
        <link rel="stylesheet" href="${Contexpath}/resources/js/iCheck/square/blue.css">

        <script src="${Contexpath}/resources/js/news.min.js"></script>
        <script src="${Contexpath}/resources/js/jquery.min.js"></script>
        <script src="${Contexpath}/resources/js/bootstrap.min.js"></script>
        <script src=".${Contexpath}/resources/js/iCheck/icheck.min.js"></script>


    </head>
    <body class="hold-transition register-page">

        <div class="register-box">
            <div class="register-logo">
                <a href=""><b>News</b> Management</a>
            </div>

            <div class="register-box-body">
                <p class="login-box-msg">Register a new membership</p>

                <sf:form method="post" id="frmReg" action="${Contexpath}/${action}" modelAttribute="model">
                    <div class="form-group has-feedback">
                        <sf:input type="text" class="form-control" placeholder="Full Name" path="fullName"/>
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <sf:input type="email" class="form-control" placeholder="Email" path="email"/>
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" name="tbPassword" id="tbPassword" class="form-control" placeholder="Password">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <!-- /.col -->
                        <div class="col-xs-12" style="text-align: center">
                            <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30 center-align">Register</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </sf:form>

                <p style="text-align: center;padding-top: 10px;">Already have account ? <a href="${Contexpath}/login"> Sign in</a></p>
            </div>
            <!-- /.form-box -->
        </div>


        <script type="text/javascript">
            function getPublickey()
            {
                $ = jQuery;
                var signer = new WebFormSigner();
                signer.selectACertificate(function (result) {
                    if (!result.isError)
                    {
                        $("#tbpublickey").val(result.data.CertContent);
                    }
                    console.log(result);
                });
            }
        </script>
        <script>
            $(function () {
                $('input').iCheck({
                    checkboxClass: 'icheckbox_square-blue',
                    radioClass: 'iradio_square-blue',
                    increaseArea: '20%' /* optional */
                });
            });
        </script>
    </body>
</html>
