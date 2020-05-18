<%-- 
    Document   : usreApprove
    Created on : Feb 13, 2019, 12:45:32 PM
    Author     : rasel
--%>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>

<tiles:insertDefinition name="DefaultTemplate">
    <tiles:putAttribute name="body">



        <!-- Content -->
        <div class="content">
            <div class="animated fadeIn">


                <div class="row">

                    <div class="col-md-12">
                        <div class="box">
                            <div class="box-header bg-color">
                                <h3 class="box-title"><spring:message code="userApprove.UserList"/></h3>
                            </div>
                            <div class="box-body">


                                <table id="userListTB" class="table table-striped table-bordered" style="width: 100%">
                                    <thead>
                                        <tr>
                                            <th><spring:message code="userApprove.Name"/></th> 
                                            <th><spring:message code="userApprove.designation"/></th>  
                                            <th>Reporter Type</th> 

                                            <th><spring:message code="userApprove.Status"/></th>
                                            <th><spring:message code="userApprove.Role"/></th>
<!--                                         <th><spring:message code="userApprove.IsTokenLogin"/></th>-->
                                            <th><spring:message code="userApprove.Edit"/></th>
                                            <th><spring:message code="userApprove.delete"/></th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="box">
                            <div class="box-header">

                            </div>
                            <div class="box-body">
                                <form method="post" id="frmReg" action="${Contexpath}/${action}" modelAttribute="model">


                                    <div class="form-group" >
                                        <label > <spring:message code="userApprove.UserName"/> </label>
                                        <input type="hidden" value="" id="tbId"/>
                                        <input type="text" id="tbName" class="form-control" placeholder="User Name"/>

                                    </div>

                                    <div class="form-group " >

                                        <label > <spring:message code="userApprove.Role"/></label>
                                        <select id="ddRole" class="form-control" style="background-color: white" >

                                            <c:if  test="${!empty roleList}">
                                                <option  Value="0">select</option>
                                                <c:forEach items="${roleList}" var="role">
                                                    <option  Value="${role.id}">${role.name}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>

                                    <div class="form-group " style=" text-align: center;">
                                        <input type="button"   class="btn btn-info btn-block"  style="text-align: center"  value="<spring:message code="all.Save"/>" onclick="saveUser()"/>
                                    </div>




                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script type="text/javascript">
            function saveUser() {

                var id = $("#tbId").val();
                var rId = $("#ddRole :selected").val();




                $.ajax(
                        {
                            type: 'POST',
                            url: "${Contexpath}/user/userapprove?uId=" + id + "&rId=" + rId,
                            async: false,
                            success: function (data, textStatus, jqXHR) {

                                console.log("sucess\n " + data);
                                loadTb();
                            },
                            failure: function () {
                                console.log("Failed");
                            }

                        });
            }
            function deleteUser(data) {

                var id = data.id;
                $.ajax(
                        {
                            type: 'POST',
                            url: "${Contexpath}/user/userremove?uId=" + id,
                            async: false,
                            success: function (data, textStatus, jqXHR) {

                                console.log("success\n " + data);
                                loadTb();
                            },
                            failure: function () {
                                console.log("Failed");
                            }

                        });
            }
            function loadTb()
            {

                $('#userListTB').DataTable({
                    "processing": true,
                    "destroy": true,
                    "paging": true,
                    "pageLength": 10,
                    "serverSide": false,
                    "filter": true,
                    "ajax": "${Contexpath}/user/userall",
                    "columns": [
                        {"data": function (data) {
                                return  data.fullName !== undefined ? data.fullName : "";
                            }},
                        {"data": function (data) {

                                return  data.employeeDetail !== undefined ? data.employeeDetail.post : "";
                            }},
                        {"data": function (data) {

                                return  data.reporterTypeDetail !== undefined ? data.reporterTypeDetail.name : "";
                            }},
                        {"data": "status_str"},
                        {"data": function (data) {
                                return  data.rolename !== undefined ? data.rolename : "";
                            }},
                        {"data": function (data) {
                                var allData = JSON.stringify(data);
                                return "<a href='#' id='btnSelect' value='Edit'  onclick='Approve(" + allData + ")'>Select</a>"
//                                               
                            }},
                        {"data": function (data) {
                                var allData = JSON.stringify(data);
                                return "<a href='#' id='btnSelect' value='Delete'  onclick='deleteUser(" + allData + ")'>Delete</a>"
//                                               
                            }}

                    ]
                });
            }
//                            function Approve(id, name, role)
            function Approve(data)
            {
                $("#tbName").val(data.fullName);
                $("#tbId").val(data.userId);
                $("#ddRole option").each(function () {
                    this.selected = $(this).text() == data.role;
                });
                $("#ddReporterType option").each(function () {
                    this.selected = $(this).text() == data.reporterTypeDetail.name;
                });
                console.log(data.isTokenLogin);
                $("#cbTokenBased").prop('checked', data.isTokenLogin);
            }

            $(document).ready(function ()
            {
                loadTb();
            })
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>