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
        <title>Hillside resort</title>
        <meta name="description" content="Hillside resort management">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <c:set var="baseURL" value="${pageContext.servletContext.contextPath}" />


        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">



        <link rel="stylesheet" href="${Contexpath}/resources/assets/css/style.css">



        <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
    </head>
    <body class="bg-dark">

        <div class="sufee-login d-flex align-content-center flex-wrap">
            <div class="container">
                <div class="login-content">
                    <div class="login-logo">
                        <h2>Hillside resort Management </h2>
                        <a href="index.html">
                            <!--<img class="align-content" src="${Contexpath}/resources/images/logo.png" alt="">-->
                        </a>
                    </div>
                    <div class="login-form">
                        <form  id="frmReg" action="#" modelAttribute="model">
                            <div class="row" id="divMsg">
                                <div class="col-lg-12" style="color: green">

                                    <label id="errorMsg"></label>
                                </div>


                            </div>
                            <div class="form-group">
                                <label>Full Name </label>
                                <input id="tbFullName" type="text" class="form-control" placeholder="User Name" name="fullname" require="true"/>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input  id="tbEmail" type="email" class="form-control" placeholder="Email" name="email" require="true"/>
                            </div>                          
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" name="tbPassword" id="tbPassword" class="form-control" placeholder="Password" require="true">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox"> Agree the terms and policy
                                </label>
                            </div>
                            <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30">Register</button>
                            <div class="social-login-content">
                                <div class="social-button">
                                    <!--<button type="button" class="btn social facebook btn-flat btn-addon mb-3"><i class="ti-facebook"></i>Register with facebook</button>-->
                                    <!--<button type="button" class="btn social twitter btn-flat btn-addon mt-2"><i class="ti-twitter"></i>Register with twitter</button>-->
                                </div>
                            </div>
                            <div class="register-link m-t-15 text-center">
                                <p>Already have account ? <a href="${Contexpath}/login"> Sign in</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--<script src="${baseURL}/resources/assets/bootstrap/js/bootstrap.min.js"></script>-->
        <script src="${baseURL}/resources/assets/plugins/jquery/jquery-1.12.4.min.js"></script>
        <script src="${baseURL}/resources/assets/bootstrap/js/bootstrap.min.js"></script>


        <script type="text/javascript" src="${baseURL}/resources/script/jquery.validate.js" />"></script>
    <script type="text/javascript"  src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function ()
        {

            $('#frmReg').validate({
                errorClass: "validator-error",
                rules: {
                    'fullname': {
                        required: true
                    },
                    'email': {
                        required: true
                    },
                    'tbPassword': {
                        required: true,
                        pwcheck: true,
                        minlength: 6

                    }


                },
                messages: {
                    'fullname': {
                        required: "Name Required"
                    },
                    'tbPassword': {
                        required: "Password Required",
                        color: "red",
                        pwcheck: "Must contain one lower case and digit.",
                        minlength: "minimum length 6"

                    },
                    'email': {
                        required: "Required"
                    }


                },
                submitHandler: function (form) {
                    console.log("Validation working");
                    saveData();

                }
            });


            $.validator.addMethod("pwcheck", function (value) {
                console.log("val " + value);
                return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) // consists of only these
                        && /[a-z]/.test(value) // has a lowercase letter
                        && /\d/.test(value) // has a digit
            });
        });
        function  saveData()
        {
            console.log("start");
            var userInfo = {
                "fullname": $("#tbFullName").val(),
                "passwordHash": $("#tbPassword").val(),
                "email": $("#tbEmail").val()


            };
            console.log(userInfo);

            $.ajax(
                    {
                        type: "POST",
                        url: "${baseURL}/user/registrationsumbit",
                        contentType: 'application/json; charset=utf-8',
                        data: JSON.stringify(userInfo),
                        dataType: 'json',
                        success: function (data) {
                            console.log(data.isError);
                            console.log(data.errorMsg);
                            console.log("data " + data.toString());
                            if (data.isError == false)
                            {


                                window.location.replace("${baseURL}/login");

                            } else
                            {
                                $("#errorMsg").css("color", 'red');
                                $("#errorMsg").html(data.errorMsg);


                                setTimeout(function () {
                                    $("#divMsg").hide();
                                }, 5000);

                            }
                        },
                        error: function () {



                            console.log("failure");
                            //location.reload();
                        }
                    });
        }

    </script>
</body>
</html>
