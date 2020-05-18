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

<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>

<tiles:insertDefinition name="DefaultTemplate">
    <tiles:putAttribute name="body">

        <!-- Content -->
        <div class="content">
            <div class="animated fadeIn">


                <div class="row">

                    <div class="col-md-12">
                        <div class="box">
                            <div class="box-header">
                                <label id="msg" class=" form-control-label"> </label> <br/> 
                            </div>
                            <div class="box-header">
                                <strong class="box-title">User List</strong>
                            </div>
                            <div class="box-body">
                                <table id="userListTB" class="table table-striped table-bordered" style="width: 100%">
                                    <thead>
                                        <tr>
                                            <th>Name</th>  
                                            <th>Status</th>
                                            <th>Role</th>
                                            <th>Edit</th>

                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>

                                            <th>Name</th>   
                                            <th>Status</th>
                                            <th>Role</th>
                                            <th>Edit</th>

                                        </tr>
                                    </tfoot>
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
                                        <select id="ddRole" class="form-control" style="background-color: white" onchange="showPageList()">

                                            <c:if  test="${!empty roleList}">
                                                <option  Value="0">select</option>
                                                <c:forEach items="${roleList}" var="role">
                                                    <option  Value="${role.id}">${role.name}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </div>

                                    <div class="form-group " style=" text-align: center;">
                                        <input type="button"   class="btn btn-info btn-block"  style="text-align: center"  value="Save" onclick="saveUser()"/>
                                    </div>




                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <script src="${Contexpath}/resources/assets/js/lib/data-table/datatables.min.js"></script>
        <script src="${Contexpath}/resources/assets/js/lib/data-table/dataTables.bootstrap.min.js"></script>

        <script type="text/javascript">
                            function saveUser() {

                                var id = $("#tbId").val();
                                var rId = $("#ddRole :selected").val()
                                $.ajax(
                                        {
                                            type: 'POST',
                                            url: "${Contexpath}/user/userapprove?uId=" + id + "&rId=" + rId,
                                            dataType: 'json',
                                            async: false,
                                            success: function (data) {
                                                if (data.isError === false)
                                                {
                                                    $("#msg").html("Add Sucessufully");
                                                    $("#msg").hide().html("Role assign Successufully").fadeIn('slow').delay(5000).hide(1);
                                                }
                                                console.log("sucess\n " + data);
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
                                    "pageLength": 50,
                                    "serverSide": true,
                                    "filter": false,
                                    "ajax": "${Contexpath}/user/userall",
                                    "columns": [
                                        {"data": "fullname"},
                                        {"data": "status_str"},
                                        {"data": "role"},
                                        {"data": function (data) {
                                                return "<a href='#' id='btnSelect' value='Edit'  onclick='Approve(\"" + data.id + "\",\"" + data.fullname + "\",\"" + data.role + "\")'>Select</a>"
                                            }}

                                    ]
                                });
                            }
                            function Approve(id, name, role)
                            {
                                $("#tbName").val(name);
                                $("#tbId").val(id);
                                $("#ddRole option").each(function () {
                                    this.selected = $(this).text() == role;
                                })
                            }
                            $(document).ready(function ()
                            {
                                loadTb();
                            })
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>