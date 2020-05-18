<!DOCTYPE html>


<%@page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>
<html lang="en">

    <head>
        <title>Civil Login Portal</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>

    <body>
        <style>
            @media print {
                *,
                ::after,
                ::before {
                    text-shadow: none!important;
                    box-shadow: none!important
                }
                a,
                a:visited {
                    text-decoration: underline
                }
            }

            *,
            ::after,
            ::before {
                box-sizing: inherit
            }

            a {
                color: #007bff;
                text-decoration: none;
                background-color: transparent;
                -webkit-text-decoration-skip: objects
            }

            a:hover {
                color: #0056b3;
                text-decoration: underline
            }

            a,
            button,
            input {
                -ms-touch-action: manipulation;
                touch-action: manipulation
            }

            button:focus {
                outline: 1px dotted;
                outline: 5px auto -webkit-focus-ring-color
            }

            button,
            input {
                margin: 0;
                font-family: inherit;
                font-size: inherit;
                line-height: inherit
            }

            button,
            input {
                overflow: visible
            }

            button {
                text-transform: none
            }

            button {
                -webkit-appearance: button
            }

            button::-moz-focus-inner {
                padding: 0;
                border-style: none
            }

            .text-center {
                text-align: center!important
            }

            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box
            }

            a {
                font-family: sans-serif;
                font-size: 14px;
                line-height: 1.7;
                color: #666;
                margin: 0;
                transition: all .4s;
                -webkit-transition: all .4s;
                -o-transition: all .4s;
                -moz-transition: all .4s
            }

            a:focus {
                outline: 0!important
            }

            a:hover {
                text-decoration: none;
                color: #333
            }

            input {
                outline: 0;
                border: none
            }

            input:focus {
                border-color: transparent!important
            }

            input::-webkit-input-placeholder {
                color: #999
            }

            input:-moz-placeholder {
                color: #999
            }

            input::-moz-placeholder {
                color: #999
            }

            input:-ms-input-placeholder {
                color: #999
            }

            button {
                outline: 0!important;
                border: none;
                background: 0 0
            }

            button:hover {
                cursor: pointer
            }

            .txt1 {
                font-family: sans-serif;
                font-size: 13px;
                line-height: 1.4;
                color: #999
            }

            .txt2 {
                font-family: sans-serif;
                font-size: 13px;
                line-height: 1.4;
                color: #00ad5f
            }

            .txt3 {
                font-family: sans-serif;
                font-size: 15px;
                line-height: 1.4;
                color: #00ad5f;
                text-transform: uppercase
            }

            .container-login100 {
                width: 100%;
                min-height: 100vh;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                align-items: center;
                padding: 15px;
                background-image: linear-gradient(to right, #92fe9d 0, #00c9ff 100%)
            }

            .wrap-login100 {
                width: 65%;
                background: #fff;
                overflow: hidden;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                flex-wrap: wrap;
                align-items: stretch;
                flex-direction: row-reverse
            }

            .login100-more {
                width: 50%;
                background-repeat: no-repeat;
                background-size: cover;
                background-position: center;
                position: relative;
                z-index: 1
            }

            .login100-more::before {
                content: "";
                display: block;
                position: absolute;
                z-index: -1;
                width: 100%;
                height: 100%;
                top: 0;
                left: 0;
                background: rgba(0, 0, 0, .3)
            }

            .login100-form {
                width: 50%;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                flex-wrap: wrap;
                padding: 100px 65px 40px 65px
            }

            .login100-form-title {
                font-family: sans-serif;
                font-size: 20px;
                color: #555;
                line-height: 1.2;
                text-transform: uppercase;
                letter-spacing: 2px;
                text-align: center;
                width: 100%;
                display: block
            }

            .wrap-input100 {
                width: 100%;
                position: relative;
                border: 1px solid #e6e6e6
            }

            .rs1-wrap-input100,
            .rs2-wrap-input100 {
                width: 100%
            }

            .rs2-wrap-input100 {
                border-left: none
            }

            .input100 {
                display: block;
                width: 100%;
                background: 0 0;
                font-family: sans-serif;
                font-size: 18px;
                color: #666;
                line-height: 1.2;
                padding: 0 25px
            }

            input.input100 {
                height: 55px
            }

            .focus-input100 {
                position: absolute;
                display: block;
                width: calc(100% + 2px);
                height: calc(100% + 2px);
                top: -1px;
                left: -1px;
                pointer-events: none;
                border: 1px solid #00ad5f;
                visibility: hidden;
                opacity: 0;
                -webkit-transition: all .4s;
                -o-transition: all .4s;
                -moz-transition: all .4s;
                transition: all .4s;
                -webkit-transform: scaleX(1.1) scaleY(1.3);
                -moz-transform: scaleX(1.1) scaleY(1.3);
                -ms-transform: scaleX(1.1) scaleY(1.3);
                -o-transform: scaleX(1.1) scaleY(1.3);
                transform: scaleX(1.1) scaleY(1.3)
            }

            .input100:focus+.focus-input100 {
                visibility: visible;
                opacity: 1;
                -webkit-transform: scale(1);
                -moz-transform: scale(1);
                -ms-transform: scale(1);
                -o-transform: scale(1);
                transform: scale(1)
            }

            .container-login100-form-btn {
                width: 100%;
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                flex-wrap: wrap;
                justify-content: center
            }

            .login100-form-btn {
                display: -webkit-box;
                display: -webkit-flex;
                display: -moz-box;
                display: -ms-flexbox;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 0 20px;
                width: 100%;
                height: 50px;
                border-radius: 3px;
                background: #00ad5f;
                font-family: Montserrat-Bold;
                font-size: 12px;
                color: #fff;
                line-height: 1.2;
                text-transform: uppercase;
                letter-spacing: 1px;
                -webkit-transition: all .4s;
                -o-transition: all .4s;
                -moz-transition: all .4s;
                transition: all .4s
            }

            .login100-form-btn:hover {
                background: #333
            }

            @media (max-width:992px) {
                .login100-form {
                    width: 60%;
                    padding-left: 30px;
                    padding-right: 30px
                }
                .login100-more {
                    width: 40%
                }
            }

            @media (max-width:768px) {
                .login100-form {
                    width: 100%
                }
                .login100-more {
                    width: 100%
                }
            }

            @media (max-width:576px) {
                .login100-form {
                    padding-left: 15px;
                    padding-right: 15px;
                    padding-top: 150px
                }
                .rs1-wrap-input100,
                .rs2-wrap-input100 {
                    width: 100%
                }
                .rs2-wrap-input100 {
                    border-left: 1px solid #e6e6e6
                }
            }

            .validate-input {
                position: relative
            }
            /*! CSS Used from: file:///C:/Users/creativecriminal/Desktop/Login_v17/Login_v17/css/util.css */

            .p-t-27 {
                padding-top: 27px
            }

            .p-b-34 {
                padding-bottom: 34px
            }

            .p-b-239 {
                padding-bottom: 239px
            }

            .m-b-20 {
                margin-bottom: 20px
            }

            .text-center {
                text-align: center
            }

            .w-full {
                width: 100%
            }

            .user_list li {
                list-style: none;
                font-family: sans-serif;
                cursor: pointer;
                padding: 10px;
                text-align: left;
                font-size: 12px;
            }

            .user_list {
                position: fixed;
                top: 0;
                right: 0;
                padding: 10px 10px 10px 7px;
                background: #ffffff;
                border-radius: 10px 0 0 10px
            }
            .successMes{
                padding: 10px 5px;
                color: green;
                font-size: 20px;
                font-weight: bold;
                text-align: center;
                margin-bottom: 10px;
                border: 2px solid;
                border-style: dashed;
            }
            .warningMes{
                padding: 10px 5px;
                color: #ff7e00;
                font-size: 20px;
                font-weight: bold;
                text-align: center;
                margin-bottom: 10px;
                border: 2px solid;
                border-style: dashed;
            }
     
        </style>

        <form action="<c:url value='/login' />" method='POST' >
            <div class="limiter">
                <div class="container-login100">
                    <div class="wrap-login100">
                        <div class="login100-form validate-form">
                            <span class="login100-form-title p-b-34">
                                <!--<img src="resources/img/logo_dark.png" alt="">-->
                                Civil Project
                            </span>
                            <div class="row">
                                <div class="col-md-3 col-sm-3 col-xs-3"></div>
                                <div class="col-md-7 col-sm-6 col-xs-6 form_User">
                                    ${errorMsg}
                                </div>
                            </div>
                            <div class="wrap-input100 rs1-wrap-input100 validate-input m-b-20" data-validate="Type user name">
                                <input id="username" class="input100" type="text" name="username" placeholder="User name">
                                <span class="focus-input100"></span>
                            </div>                            
                            <div class="wrap-input100 rs2-wrap-input100 validate-input m-b-20" data-validate="Type password">
                                <input class="input100" type="password" name="password" placeholder="Password" id="password" >
                                <span class="focus-input100"></span>
                            </div>

                            <div class="container-login100-form-btn">
                                <button class="login100-form-btn" onclick="auth(document.getElementById('username').value)">
                                    Sign in
                                </button>
                            </div>

                            <div class="w-full text-center p-t-27">
                                <span class="txt1">
                                    Forgot
                                </span>

                                <a href="#" class="txt2">
                                    User name / password?
                                </a>
                            </div>
                            <div class="w-full text-center p-t-27">
                                <p>Don't have account ? <a href="${Contexpath}/user/register"> Sign Up Here</a></p>
                            </div>


                        </div>

                        <div class="login100-more" style="background-image: url('${picture}');"></div>
                    </div>
                </div>
            </div>

        </form>


    </body>

</html>