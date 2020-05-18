<header class="main-header">
    <!-- Logo -->
    <a href="${Contexpath}/welcome" class="logo">
        <!-- Mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>M</b>P</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><img src="${Contexpath}/resources/img/civil.png" alt="Logo" width="140px" height="auto"></span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>
        <!-- Page Menu Title -->
        <div id="pageTitle" class="sidebar-title"></div>
        <!-- Page Menu Title -->
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!--                <li class="notification dropdown messages-menu">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <i class="fa fa-bell"></i>
                                        <span class="label label-green">&nbsp;</span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li class="header">You have 2 messages</li>
                                        <li>
                                             inner menu: contains the actual data 
                                            <ul class="menu">
                                                <li>
                                                     start message 
                                                    <a href="#">
                                                        <div class="pull-left">
                                                            <img src="${Contexpath}/resources/img/blank_dp.png" class="img-circle" alt="User">
                                                        </div>
                                                        <h4>
                                                            Support Team
                                                        </h4>
                                                        <p>Why not buy a new awesome theme?</p>
                                                    </a>
                                                </li>
                                                 end message 
                                                <li>
                                                     start message 
                                                    <a href="#">
                                                        <div class="pull-left">
                                                            <img src="${Contexpath}/resources/img/blank_dp.png" class="img-circle" alt="User Image">
                                                            <img class="user-avatar rounded-circle" src="${base64_pf}" alt="User Avatar">
                                                        </div>
                                                        <h4>
                                                            Support Team
                                                        </h4>
                                                        <p>Why not buy a new awesome theme?</p>
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="footer"><a href="#">See All Messages</a></li>
                                    </ul>
                                </li>-->

                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <!--<img src="${Contexpath}/resources/img/user2-160x160.jpg" class="user-image" alt="User Image">-->
                        <img class="user-image" src="${base64_pf}" alt="User">
                        <span class="desig hidden-xs hidden-sm">${nameLoginUser}</span>
                        <span class="desig hidden-xs hidden-sm">${designation}</span>
                    </a>
                    <ul class="dropdown-menu top-right-menu">
                        <li class="top-right-menu-li"><a href="${Contexpath}/user/userdetail"><i class="fa fa-user-circle-o"></i> User Profile</a> </li>
                        <li class="top-right-menu-li"><a href="${Contexpath}/user/changepassword"><i class="fa fa-key"></i>Change Password</a> </li>
                        <li class="top-right-menu-li"><a href="${Contexpath}/logout"><i class="fa fa-power-off"></i>Logout</a> </li>

                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <script type="text/javascript">

//        bkLib.onDomLoaded(function () {
//            new nicEditor({
//                buttonList: ['fontSize', 'bold', 'italic', 'link', 'unlink', 'underline', 'strikeThrough', 'subscript',
//                    'superscript'
//                ]
//            }).panelInstance('taReportTxt');
//        });

    </script> 
    <script>
        var baseUrl = '';

        $(document).ready(function ()
        {
            baseUrl = '${Contexpath}';
            console.log("Print check");
        });
    </script>
</header>