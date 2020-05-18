<%-- 
    Document   : useradd
    Created on : Jan 13, 2020, 11:45:58 AM
    Author     : rasel
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>

<tiles:insertDefinition name="DefaultTemplate">
    <tiles:putAttribute name="body">
        <script type="text/javascript" src="${baseURL}/resources/script/jquery.validate.js" />"></script>
    <script src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
    <!-- Content -->
    <script src="https://cdn.ckeditor.com/4.13.1/standard/ckeditor.js"></script>
    <div class="content">
        <form  id="formUserInfo" action="#" >
            <!--<textarea name="editor1"></textarea>-->
            <div class="col-lg-12">
                <div class="box">
                    <div class="box-header bg-color">
                        <h3 class="box-title">User Add</h3>
                    </div>
                    <div class="box-body card-block">
                        <div class="col-lg-12">

                            <label for="check-in" id="msg"></label> 
                        </div>
                        <div class="col-lg-6">
                            <div class="row">
                                <div class="col-lg-6 form-group">
                                    Name :
                                </div>
                                <div class="col-lg-6 form-group">
                                    <input type="text" id="tbName" name="name" class="form-group"/>
                                    <input type="hidden" value="" id="tbId"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6 form-group">
                                    E-Mail :
                                </div>
                                <div class="col-lg-6 form-group">
                                    <input type="email" id="tbEmail" name="email" class="form-group"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6 form-group">
                                    Contact Number :
                                </div>
                                <div class="col-lg-6 form-group">
                                    <input type="text" id="tbContact" name="phone" class="form-group"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-6 form-group">
                                    Designation :
                                </div>
                                <div class="col-lg-6 form-group">
                                    <input type="text" id="tbDesignation" name="phone" class="form-group"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-6 form-group">
                                    Join Date :
                                </div>
                                <div class="col-lg-6 form-group">
                                    <input type="text" id="tbDesignation" name="phone" class="form-group datetime"/>
                                    <!--                                    <span class="input-group-addon">
                                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                                        </span>-->
                                </div>
                            </div>

                        </div>
                        <div class="col-lg-6">
                            <div class="row">
                                <div class="col-lg-6">
                                    Reporter Type
                                </div>
                                <div class="col-lg-6">
                                    <select id="ddReporterType" class="form-control" style="background-color: white" onchange="showPageList()">

                                        <c:if  test="${!empty reporterList}">
                                            <option  Value="0">select</option>
                                            <c:forEach items="${reporterList}" var="reporter">
                                                <option  Value="${reporter.id}">${reporter.name}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    Role
                                </div>
                                <div class="col-lg-6">
                                    <select id="ddRole" class="form-control required" style="background-color: white" >

                                        <c:if  test="${!empty roleList}">
                                            <option  Value="0">select</option>
                                            <c:forEach items="${roleList}" var="role">
                                                <option  Value="${role.id}">${role.name}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    Status
                                </div>
                                <div class="col-lg-6">
                                    <select id="ddStatus" class="form-control required" style="background-color: white" >
                                        <option  Value="189">select</option>                                        
                                        <option  Value="1">Approve</option>
                                        <option  Value="0">Not Approve</option>
                                    </select>

                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    Password Reset(default Password 123456)
                                </div>
                                <div class="col-lg-6">
                                    <input type="checkbox" id="cbPasswordResete"/>
                                </div>
                            </div>

                        </div>
                        <div class="col-lg-12">
                            <div style="text-align: center">
                                <input type="button"  value="Add User" onclick="addClick()"/>
                                <input type="button"  value="Clear" onclick="clear()"/>
                            </div>
                        </div>
                    </div>


                </div>
            </div>

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
        </form>
    </div>
    <link rel="stylesheet" href="${Contexpath}/resources/bootstrap-datepicker/css/bootstrap-datepicker.min.css"/>
    <script src="${Contexpath}/resources/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet" href="${Contexpath}/resources/css/bootstrap-datetimepicker.min.css" type="text/css"    />
    <script src="${Contexpath}/resources/js/moment.min.js"></script>
    <script src="${Contexpath}/resources/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="${Contexpath}/resources/css/uploadfile.css"/>
    <script src="${Contexpath}/resources/js/jquery.uploadfile.min.js"></script>
    <script src="${Contexpath}/resources/js/nicEdit.js" type="text/javascript"></script>
    <script src="${Contexpath}/custom/assign_editors.js"></script> 
    <script type="text/javascript">
//                                    CKEDITOR.replace('editor1');
                                    function addClick()
                                    {
                                        $('#formUserInfo').submit();
                                    }
                                    function addUser()
                                    {
                                        var status = $("#ddStatus :selected").val();
                                        var roleId = $("#ddRole :selected").val();
                                        var reportId = $("#ddReporterType :selected").val();
                                        if (status > 1 || roleId == 0 || reportId == 0)
                                        {
                                            $("#msg").css("color", "red");
                                            $("#msg").html("Please Select Role/ReporterType/Staus");
                                            setTimeout(function () {
                                                $("#msg").html("");
                                            }, 5000);
                                            return;
                                        }
                                        var passwordReset = 0;
                                        if ($("#cbPasswordResete").is(":checked")) {
                                            passwordReset = 1;
                                        }

                                        var userInfo =
                                                {
                                                    "fullName": $("#tbName").val(),
                                                    "email": $("#tbEmail").val(),
                                                    "roleId": $("#ddRole :selected").val(),
                                                    "reporterTypeId": $("#ddReporterType :selected").val(),
                                                    "id": $("#tbId").val(),
                                                    "passwordReset": passwordReset,
                                                    "status": $("#ddStatus :selected").val(),
                                                };
                                        $.ajax(
                                                {
                                                    type: 'POST',
                                                    url: "${Contexpath}/user/useradd",
                                                    contentType: 'application/json; charset=utf-8',
                                                    data: JSON.stringify(userInfo),
                                                    dataType: 'json',
                                                    success: function (data) {
                                                        if (data.isError === false)
                                                        {
                                                            loadTb();
                                                            clear();
                                                            $("#msg").css("color", "green");
                                                            $("#msg").html("User Add Succefully");
                                                            setTimeout(function () {
                                                                $("#msg").html("");
                                                            }, 5000);
                                                            $("#tbId").val(0);

                                                        }
                                                        if (data.isError === true)
                                                        {

                                                            $("#msg").css("color", "red");
                                                            $("#msg").html(data.errorMsg);
                                                            setTimeout(function () {
                                                                $("#msg").html("");
                                                            }, 5000);
                                                        }



                                                    }
                                                });
                                    }
                                    function clear()
                                    {
                                        $("#tbName").val("");
                                        $("#tbEmail").val("");
                                        $("#tbContact").val("");
                                        $("#tbId").val(0);
                                        $("#ddRole").val(0);
                                        $("#ddReporterType").val(0);
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
                                                        return  data.fullname !== undefined ? data.fullname : "";
                                                    }},
                                                {"data": function (data) {

                                                        return  data.designation !== undefined ? data.designation : "";
                                                    }},
                                                {"data": function (data) {

                                                        return  data.reportername !== undefined ? data.reportername : "";
                                                    }},

                                                {"data": function (data) {
                                                        var status = "";
                                                        if (data.status == "1")
                                                        {
                                                            status = "Active";
                                                        } else if (data.status == "0")
                                                        {
                                                            status = "not Active";
                                                        }

                                                        return  status;
                                                    }},
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
                                    function Approve(data)
                                    {
                                        $("#tbName").val(data.fullname);
                                        $("#tbEmail").val(data.email);
                                        $("#tbId").val(data.userid);
                                        $("#ddRole option").each(function () {
                                            this.selected = $(this).text() == data.rolename;
                                        });
                                        $("#ddReporterType option").each(function () {
                                            this.selected = $(this).text() == data.reportername;
                                        });
                                        console.log(data.isTokenLogin);
                                        $("#cbTokenBased").prop('checked', data.isTokenLogin);
                                    }

                                    $(document).ready(function ()
                                    {
//                                        CKEDITOR.instances['editor1'].setData();
//                                        CKEDITOR.instances['editor1'].getData();
                                        $('.datetime').datetimepicker({
                                            format: 'DD/MM/YYYY HH:mm:ss'
                                        });
                                        $('#formUserInfo').validate({
                                            errorClass: "validator-error",
                                            rules: {
                                                'name': {
                                                    required: true
                                                },
                                                'phone': {
                                                    required: true
                                                },
                                                'email': {
                                                    required: true

                                                }


                                            },
                                            messages: {
                                                'name': {
                                                    required: "Name Required"
                                                },
                                                'phone': {
                                                    required: "Phone Number Required",
                                                    color: "red"
                                                },
                                                'email': {
                                                    required: "Required"
                                                }



                                            },
                                            submitHandler: function (form) {
                                                console.log("Validation working");
                                                addUser();

                                            }
                                        });


                                        loadTb();
                                    })

    </script>
</tiles:putAttribute>
</tiles:insertDefinition>