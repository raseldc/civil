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
                            <h3 class="box-title"><spring:message code="role.RolePageRelation"/></h3>
                        </div>
                        <div class="box-body box-block">
                            <div class="form-group">
                                <label id="msg" class=" form-control-label"> </label> <br/>        
                                <label for="company" class=" form-control-label"><spring:message code="role.Role"/></label>                                    
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
                                <div class="row col-lg-12">
                                    <label for="company" class=" form-control-label">Select Page</label>                                    
                                </div>
                                <div class="row">
                                    <c:if  test="${!empty pageList}">

                                        <c:forEach items="${pageList}" var="page">
                                            <div class="col-lg-3">
                                                <label>${page.name} </label>
                                                <input id="cb_${page.id}" value="${page.id}"  type="checkbox">
                                            </div>

                                        </c:forEach>
                                    </c:if>
                                </div>

                            </div> 
                            <!--                            <div class="form-group">
                                                            <label for="company" class=" form-control-label"><spring:message code="role.Page"/></label>                                    
                                                            <select id="ddpage" class="form-control" style="background-color: white" >
                            
                            <c:if  test="${!empty pageList}">
                                <option  Value="0">select</option>
                                <c:forEach items="${pageList}" var="page">
                                    <option  Value="${page.id}">${page.name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>  -->
                            <div class="form-group">
                                <!--<label for="vat" class=" form-control-label">VAT</label>-->
                                <input type="button" id="btnSave" class="btn btn-success"  onclick="submit()"value="<spring:message code="role.Add"/>">
                                <!--<input type="button" id="btnSave" class="btn btn-danger" onclick="delete_()"value="<spring:message code="role.Delete"/>">-->
                            </div>
                        </div>
                    </div>
                </div>





                <!--                <div class="col-md-12">
                                    <div class="box">
                                        <div class="box-header bg-color">
                                            <h3 class="box-title"><spring:message code="role.RoleList"/></h3>
                                        </div>
                                        <div class="box-body">
                                            <table id="pageListTB" class="table table-striped table-bordered" style="width: 100%">
                                                <thead>
                                                    <tr>
                                                        <th><spring:message code="role.Name"/></th>                                           
                                                        <th><spring:message code="controller.url"/></th>    
                
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                
                                                        <th><spring:message code="role.Name"/></th>                                           
                                                        <th><spring:message code="controller.url"/></th>    
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                </div>-->


            </div>
        </div>

        <script type="text/javascript">

            function delete_()
            {
                console.log("adf");
                var rId = $("#ddRole :selected").val();
                var pId = $("#ddpage :selected").val();
                $.ajax(
                        {
                            type: 'POST',
                            url: "${Contexpath}/role/pagebyroledelete?rId=" + rId + "&pId=" + pId,
                            async: false,
                            success: function (data, textStatus, jqXHR) {
                                $("#totalLeaveOfLeaveTypeInt").val(data);
                                console.log("sucess\n " + data);
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
                var checkedPage = "";
                $("input:checkbox").each(function (i, e) {
                    if (this.checked)
                    {
                        checkedPage = checkedPage + $(this).val() + " ";
                    }
                });


                $.ajax(
                        {
                            type: 'POST',
                            url: "${Contexpath}/role/savepagesforrole?rId=" + rId + "&pagesId=" + checkedPage,
                            async: false,
                            success: function (data, textStatus, jqXHR) {
                                $("#totalLeaveOfLeaveTypeInt").val(data);
                                console.log("sucess\n " + data);
                                $("#msg").html("Add Sucessufully");
                                $("#msg").hide().html("Add Sucessufully").fadeIn('slow').delay(5000).hide(1);
                                // loadPageList();
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
                $("input:checkbox").prop("checked", false);
                $.ajax({
                    type: 'POST',
                    url: "${Contexpath}/role/getpagebyrole?rId=" + rId,
                    dataType: 'JSON',
                    success: function (data) {
                        $(data).each(function (i, e) {
                            console.log(i);
                            console.log(e);
                            console.log("#cb_" + e.id);
                            $("#cb_" + e.id).prop("checked", "true");
                        });

                        console.log(data);

                    },
                    failure: function () {
                        console.log("Failed");
                    }

                });
//                $('#pageListTB').DataTable({
//                    "processing": true,
//                    "destroy": true,
//                    "paging": true,
//                    "pageLength": 50,
//                    "serverSide": true,
//                    "filter": false,
//                    "ajax": "${Contexpath}/role/pagebyrole?rId=" + rId,
//                    "columns": [
//                        {"data": "name"},
//                        {"data": "url"}
//
//
//                    ]
//                });
            }

            $(document).ready(function ()
            {
                //  loadRoleList();
            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>