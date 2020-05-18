<%-- 
    Document   : role
    Created on : Feb 3, 2019, 2:43:17 PM
    Author     : rasel
--%>


<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>

<tiles:insertDefinition name="DefaultTemplate">
    <tiles:putAttribute name="body">

        <!-- Content -->
        <div class="content">
            <div >
                <c:url var="updateUrl" value="${action}"/>
                <div class="row">
                    <div class="col-md-12">
                            <sf:form  id="fTrainingForm" action="${Contexpath}/${action}" modelAttribute="model" method="POST" >

                            <div class="box">
                                <div class="box-header bg-color">
                                    <h3 class="box-title">Role Add Edit</h3>
                                </div>
                                <div class="box-body box-block">

                                    <div class="form-group">
                                        <label for="company" class=" form-control-label">Role</label>
                                        <sf:input type="hidden" id="idRole" class="form-control" path="id"></sf:input>
                                        <sf:input type="text" id="tbRoleName" placeholder="Enter Role Name" class="form-control" path="name"></sf:input>
                                        <h4 style="color: red; padding: 5px">${msg}</h4>
                                    </div>                                    
                                    <div class="form-group">
                                        <!--<label for="vat" class=" form-control-label">VAT</label>-->
                                        <input type="submit" id="btnSave" class="btn btn-success" value="Submit">
                                    </div>
                                </div>
                            </div>


                        </sf:form>
                    </div>
                </div>
                <div class="row">

                    <div class="col-md-12">
                        <div class="box">
                            <div class="card-header bg-color">
                                <h3 class="box-title">Role List</h3>
                            </div>
                            <div class="card-body">
                                <table id="roleListTB" class="table table-striped table-bordered" style="width: 100%">
                                    <thead>
                                        <tr>
                                            <th>Name</th>                                            
                                            <th>Edit</th>

                                        </tr>
                                    </thead>

                                </table>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>


        <script type="text/javascript">
            function loadRoleList()
            {
                $('#roleListTB').DataTable({
                    "processing": true,
                    "destroy": true,
                    "paging": true,
                    "pageLength": 50,
                    "serverSide": true,
                    "filter": false,
                    "ajax": "${Contexpath}/role/roleall",
                    "columns": [
                        {"data": "name"},
                        {"data": function (data) {

                                return "<a href='#' id='btnSelect' value='Edit'  onclick='editRole(\"" + data.id + "\",\"" + data.name + "\")'>Select</a>"
                            }}

                    ]
                });
            }
            function editRole(id, name)
            {
                console.log(name);
                $("#idRole").val(id);
                $("#tbRoleName").val(name);
                $("#btnSave").html("Edit");
                $("#btnSave").val("Edit");
            }
            $(document).ready(function ()
            {
                console.log("123");
                loadRoleList();
            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>