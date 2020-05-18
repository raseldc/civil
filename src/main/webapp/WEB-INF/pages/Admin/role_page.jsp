<%-- 
    Document   : role_page
    Created on : Feb 11, 2019, 3:43:58 PM
    Author     : rasel
--%>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>

<tiles:insertDefinition name="DefaultTemplate">
    <tiles:putAttribute name="body">

        <!-- Content -->
        <div class="content">
            <div class="animated fadeIn">
                <c:url var="updateUrl" value="${action}"/>

                <div class="col-lg-12">
                    <div class="box">
                        <div class="box-header bg-color">
                            <h3 class="box-title">Role Page Relation </h3>
                        </div>
                        <div class="box-body box-block">
                            <div class="form-group">
                                <label id="msg" class=" form-control-label"> </label> <br/>        
                                <label for="company" class=" form-control-label">Role </label>                                    
                                <select id="ddRole" class="form-control" style="background-color: white" onchange="showPageList()">

                                    <c:if  test="${!empty roleList}">
                                        <option  Value="0">select</option>
                                        <c:forEach items="${roleList}" var="role">
                                            <option  Value="${role.id}">${role.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>   
                            <div class="form-group">
                                <label for="company" class=" form-control-label">Page </label>                                    
                                <select id="ddpage" class="form-control" style="background-color: white" >

                                    <c:if  test="${!empty pageList}">
                                        <option  Value="0">select</option>
                                        <c:forEach items="${pageList}" var="page">
                                            <option  Value="${page.id}">${page.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>  
                            <div class="form-group">
                                <!--<label for="vat" class=" form-control-label">VAT</label>-->
                                <input type="button" id="btnSave" class="btn btn-success"  onclick="submit()"value="Add ">
                                <input type="button" id="btnSave" class="btn btn-danger" onclick="delete_()"value="Delete ">
                            </div>
                        </div>
                    </div>
                </div>





                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header bg-color">
                            <h3 class="box-title">Role List</h3>
                        </div>
                        <div class="box-body">
                            <table id="pageListTB" class="table table-striped table-bordered" style="width: 100%">
                                <thead>
                                    <tr>
                                        <th>Name </th>                                           
                                        <th>URL </th>    

                                    </tr>
                                </thead>

                            </table>
                        </div>
                    </div>
                </div>


            </div>
        </div>

        <script type="text/javascript">

            function delete_()
            {
                console.log("adf");
                var rId = $("#ddRole :selected").val();
                var pId = $("#ddpage :selected").val();
                if (pId == 0)
                {
                    $("#msg").html("Please select Page");
                    return;
                }
                if (rId == 0)
                {
                    $("#msg").html("Please select Role");
                    return;
                }
                $.ajax(
                        {
                            type: 'POST',
                            url: "${Contexpath}/role/pagebyroledelete?rId=" + rId + "&pId=" + pId,
                            dataType: 'json',
                            async: false,
                            success: function (data) {
                                $("#totalLeaveOfLeaveTypeInt").val(data);
                                console.log("sucess\n " + data);
                                if (data.isError === false)
                                {
                                    $("#msg").html("Add Sucessufully");
                                    $("#msg").hide().html("Delete Successufully").fadeIn('slow').delay(5000).hide(1);
                                }
                                loadPageList();
                            },
                            failure: function () {
                                console.log("Failed");
                            }

                        });
            }
            function submit()
            {
                console.log("adf");
                var rId = $("#ddRole :selected").val();
                var pId = $("#ddpage :selected").val();
                if (pId == 0)
                {
                    $("#msg").html("Please select Page");
                    return;
                }
                if (rId == 0)
                {
                    $("#msg").html("Please select Role");
                    return;
                }
                $.ajax(
                        {
                            type: 'POST',
                            url: "${Contexpath}/role/pagebyrolesubmuit?rId=" + rId + "&pId=" + pId,
                            dataType: 'json',
                            async: false,
                            success: function (data) {
                                $("#totalLeaveOfLeaveTypeInt").val(data);
                                if (data.isError === false)
                                {
                                    $("#msg").html("Add Sucessufully");
                                    $("#msg").hide().html("Add Successufully").fadeIn('slow').delay(5000).hide(1);
                                }
                                console.log("sucess\n " + data);
                                console.log("sucess\n " + data.isError);
                                loadPageList();
                            },
                            failure: function () {
                                console.log("Failed");
                            }

                        });
            }
            function showPageList()
            {
                loadPageList();
            }
            function loadPageList()
            {
                var rId = $("#ddRole :selected").val();
                $('#pageListTB').DataTable({
                    "processing": true,
                    "destroy": true,
                    "paging": true,
                    "pageLength": 50,
                    "serverSide": true,
                    "filter": false,
                    "ajax": "${Contexpath}/role/pagebyrole?rId=" + rId,
                    "columns": [
                        {"data": "name"},
                        {"data": "url"}


                    ]
                });
            }

            $(document).ready(function ()
            {
                console.log("123");

                //   loadPageList();
            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>