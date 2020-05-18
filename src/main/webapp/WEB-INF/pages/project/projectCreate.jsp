<%-- 
    Document   : projectCreate
    Created on : Jan 20, 2020, 10:18:49 PM
    Author     : rasel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
                        <div class="box">
                            <div class="box-header bg-color">
                                <h3 class="box-title">Project Create/Edit</h3>
                            </div>
                            <div class="box-body box-block">
                                <div class="row" id="divMsg">
                                    <div class="col-lg-12" style="color: green">

                                        <label id="errorMsg"></label>
                                    </div>


                                </div>
                                <div class="row">
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <label for="inputEmail">Projcet name</label>
                                            <input type="text" class="form-control" id="tbProjectName" />
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <label for="inputEmail">Start Date</label>
                                            <input type="text" class="form-control datetime" id="tbStartDate" name="code"/>
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <label for="inputEmail">End Date</label>
                                            <input type="text" class="form-control datetime" id="tbEndDate"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <label for="inputEmail">Ministry</label>
                                            <input type="text" class="form-control" id="tbMinistry" />
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <label for="inputEmail">Status</label>
                                            <input type="text" class="form-control" id="tbStatus" />
                                        </div>
                                    </div>

                                </div>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="form-group">
                                            <label for="inputEmail">Description</label>
                                            <textarea id="tbDescription">
                                                
                                            </textarea>

                                        </div>
                                    </div>


                                </div>

                                <div class="row">
                                    <div class="col-lg-3">
                                        <input type="button" value="Save/Edit" onclick="ProjectAddEdit()"/>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <input type="hidden" id="projectId" value="0"/>
                    <div class="col-md-12">
                        <div class="box">
                            <div class="box-header bg-color">
                                <h3 class="box-title">Project List</h3>
                            </div>
                            <div class="box-body box-block">
                                <table id="itemListTB" class="table table-striped table-bordered" style="width: 100%">
                                    <thead>
                                        <tr>
                                            <th>Name</th>                                            
                                            <th>Start Date</th>
                                            <th>End Date</th>
                                            <th>Ministry </th>
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
        <link rel="stylesheet" href="${Contexpath}/resources/bootstrap-datepicker/css/bootstrap-datepicker.min.css"/>
        <script src="${Contexpath}/resources/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
        <link rel="stylesheet" href="${Contexpath}/resources/css/bootstrap-datetimepicker.min.css" type="text/css"    />
        <script src="${Contexpath}/resources/js/moment.min.js"></script>
        <script src="${Contexpath}/resources/js/bootstrap-datetimepicker.min.js"></script>
        <script type="text/javascript">
                                            function loadProjectList()
                                            {
                                                $('#itemListTB').DataTable({
                                                    "processing": true,
                                                    "destroy": true,
                                                    "paging": true,
                                                    "pageLength": 10,
                                                    "serverSide": false,
                                                    "filter": true,
                                                    "ajax": "${Contexpath}/project/getallproject",
                                                    "columns": [
                                                        {"data": "projectName"},
                                                        {"data": "startDate"},
                                                        {"data": "endDate"},
                                                        {"data": "ministry"},
                                                        {"data": function (data) {
                                                                var alldata = JSON.stringify(data);
//                                return "<a href='#' id='btnSelect' value='Edit'  onclick='editRole(\"" + data.id + "\",\"" + data.name + "\")'>Select</a>"
                                                                return "<a href='#' id='btnSelect' value='Edit'  onclick='editProject(" + alldata + ")'>Select</a>";
                                                            }}

                                                    ]
                                                });
                                            }
                                            function editProject(info)
                                            {
                                                $("#tbProjectName").val(info.projectName);
                                                $("#tbStartDate").val(info.startDate);
                                                $("#tbEndDate").val(info.endDate);
                                                $("#tbMinistry").val(info.ministry);
                                                $("#projectId").val(info.id);

                                            }
                                            function clear()
                                            {

                                                $("#tbProjectName").val("");
                                                $("#tbStartDate").val("");
                                                $("#tbEndDate").val("");
                                                $("#tbMinistry").val("");
                                                $("#projectId").val(0);
                                            }

                                            function ProjectAddEdit()
                                            {
                                                var priojectInfo = {
                                                    "projectName": $("#tbProjectName").val(),
                                                    "startDate": $("#tbStartDate").val(),
                                                    "endDate": $("#tbEndDate").val(),
                                                    "ministry": $("#tbMinistry").val(),
                                                    "id": $("#projectId").val()


                                                };
                                                console.log(JSON.stringify(priojectInfo));
                                                $.ajax(
                                                        {
                                                            type: 'POST',
                                                            url: "${Contexpath}/project/projectaddedit",
                                                            contentType: 'application/json; charset=utf-8',
                                                            data: JSON.stringify(priojectInfo),
                                                            dataType: 'json',
                                                            async: false,
                                                            success: function (data) {

                                                                if (data.isError == false)
                                                                {
                                                                    $("#projectId").val(0);
                                                                    clear();
                                                                    loadProjectList();
                                                                }
                                                                else
                                                                {
                                                                    $("#msg").html(data.errorMsg);
                                                                    setTimeout(function () {
                                                                        $("#divMsg").hide();
                                                                    }, 15000);
                                                                }
                                                            },
                                                            failure: function () {
                                                                //   //console.log("Failed");
                                                            }

                                                        });
                                            }
                                            $(document).ready(function ()
                                            {
                                                console.log("123");
                                                loadProjectList();

                                                $('.datetime').datetimepicker({

                                                    format: 'DD-MM-YYYY'

                                                });


                                            });
        </script>
    </tiles:putAttribute>

</tiles:insertDefinition>
