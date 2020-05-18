
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="baseURL" value="${pageContext.servletContext.contextPath}" />
<!-- Left Panel -->
<!-- sidebar -->



<aside class="main-sidebar">

    <section class="sidebar">
        <div class="user-panel">
            <div class="image">
                <!--<img class="profile-user-img img-responsive" src="${Contexpath}/resources/img/user2-160x160.jpg" alt="User Image">-->
                <img class="profile-user-img img-responsive" src="${base64_pf}" alt="User Avatar">
            </div>
            <div class="info">
               
            
            </div>
        </div>
        <ul class="sidebar-menu" data-widget="tree">
            <li class="active">
                <a href="${Contexpath}/welcome">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                </a>
            </li>

            ${menu}


        </ul>
        <div id="yourId" class="jalendar">
            <div class="added-event" data-type="holiday" data-link="http://pikselmatik.com" data-date="19-11-2015" data-title="WWDC 13 on San Francisco, LA"></div>
            <div class="added-event" data-type="task" data-link="http://pikselmatik.com" data-date="19-11-2015" data-title="Hazal ve Bora Nikah Töreni"></div>
            <div class="added-event" data-type="holiday" data-link="http://pikselmatik.com" data-date="21-11-2015" data-title="Hazal ve Bora Nikah Töreni"></div>
            <div class="added-event" data-date="22-11-2015" data-title="Hazal ve Bora Nikah Töreni at 21"></div>
            <div class="added-event" data-link="http://pikselmatik.com" data-date="10-12-2015" data-title="Tarkan ?stanbul Concert on Harbiye Aç?k Hava Tiyatrosu"></div>
        </div>
    </section>
</aside>
<!-- /.sidebar -->