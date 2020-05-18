<%-- 
    Document   : controller
    Created on : Feb 7, 2019, 3:44:43 PM
    Author     : rasel
--%>




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%--<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>

<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>

<tiles:insertDefinition name="DefaultTemplate">
    <tiles:putAttribute name="body">
        <link rel="stylesheet" href="${Contexpath}/resources/assets/css/lib/datatable/dataTables.bootstrap.min.css">
        <c:url var="updateUrl" value="${action}"/>
        <!-- Content -->
        <div class="content">
            <div class="row">

                <sf:form action="${Contexpath}/${action}" modelAttribute="model" id="fTrainingForm" method="POST">
                    <div class="col-lg-12">
                        <div class="box">
                            <div class="box-header"><strong><spring:message code="controller.controllerAdd/Edit"/></strong><small> </small></div>
                            <div class="box-body box-block">
                                <div class="col-md-6 form-group">
                                    <label for="company" class=" form-control-label"><spring:message code="controller.controller"/></label>
                                    <sf:input type="hidden" id="idPage" class="form-control" path="id"/>
                                    <sf:input type="text" id="tbPageName" placeholder="Enter your page name" class="form-control" path="name"/>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label for="company" class=" form-control-label"><spring:message code="controller.controllerBangla"/></label>
                                    <sf:input type="hidden" id="idPage" class="form-control" path="id"/>
                                    <sf:input type="text" id="tbPageNameBangla" placeholder="Enter your page name" class="form-control" path="nameBangla"/>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label for="vat" class=" form-control-label"><spring:message code="controller.url"/></label>
                                    <sf:input type="text" id="tbPageUrl" placeholder="Enter your url" class="form-control" path="url"/>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label for="vat" class=" form-control-label">CSS class Name</label>
                                    <sf:input type="text" id="tbCssClass" placeholder="Enter your url" class="form-control" path="cssClass"/>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label for="vat" class=" form-control-label">Page type Select</label>

                                    <select id="ddPageTypeSelect" class="form-control" style="background-color: white;display: none"  >

                                        <c:if  test="${!empty pPageList_}">

                                            <option label="--- Select ---" />
                                            <c:forEach items="${pPageList_}" var="pageDetail">
                                                <option  Value="${pageDetail.id}" selected> ${pageDetail.name}</option>

                                            </c:forEach>
                                        </c:if>
                                    </select>

                                    <select id="ddPpageType" class="form-control" style="background-color : white; " onchange="loadPageByType()">
                                        <option value="4">Select </option>
                                        <option value="0">Parent page</option>
                                        <option value="9">Header</option>
                                        <option value="8">Sub Header</option>



                                    </select>
                                </div>
                                <div class="col-md-6 form-group">
                                    <label for="vat" class=" form-control-label"><spring:message code="controller.parentPage"/></label>
                                    <sf:select path="pageDetail" id="ddParentList" class="form-control" 
                                               style="background-color : white; " onchange="LoadPageUnderParent()">
                                        <sf:option value="0" label="--- Select ---" />
                                        <sf:options items="${pPageList}" itemValue="id" itemLabel="name" path="pageDetail" />
                                    </sf:select>
                                </div>

                                <div class="col-md-6 form-group">
                                    <label for="vat" class=" form-control-label"><spring:message code="controller.status"/></label>
                                    <sf:select path="type" class="form-control" style="background-color : white; ">
                                        <sf:option value="4" label="--- Select ---" />
                                        <sf:option value="3" label="Menu Page" />
                                        <sf:option value="0" label="Parent page" />
                                        <sf:option value="9" label="Header" />
                                        <sf:option value="8" label="Sub Header" />
                                        <sf:option value="4" label="other" />


                                    </sf:select>
                                </div>
                                <div class="col-md-12 form-group">
                                    <!--<label for="vat" class=" form-control-label">VAT</label>-->
                                    <input type="submit" id="btnSave" class="btn btn-success" value="<spring:message code="controller.submit"/>">
                                </div>
                            </div>
                        </div>
                    </div>

                </sf:form>



            </div>
            <div class="row">

                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header">
                            <strong class="box-title">Page List</strong>
                        </div>
                        <div class="box-body">
                            <table id="pageListTB" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th><spring:message code="controller.name"/></th>
                                        <th><spring:message code="controller.name(bangle)"/></th>
                                        <th><spring:message code="controller.url"/></th>
                                        <th><spring:message code="controller.parentPage"/></th>
                                        <th><spring:message code="controller.type"/></th>
                                        <th><spring:message code="controller.edit"/></th>

                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>

                                        <th><spring:message code="controller.name"/></th>
                                        <th><spring:message code="controller.name(bangle)"/></th>
                                        <th><spring:message code="controller.url"/></th>
                                        <th><spring:message code="controller.parentPage"/></th>
                                        <th><spring:message code="controller.type"/></th>
                                        <th><spring:message code="controller.edit"/></th>

                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>


            </div>
        </div>


        <script type="text/javascript">
            var pageList;
            function  loadPageByType()
            {

                var type_select = $("#ddPpageType :selected").val();
                $("#ddParentList").empty();
                var html_ = " <option value=0>-------Select-------</option>";
                $("#ddPageTypeSelect option").each(
                        function (i, e)
                        {
                            var type = $(e).html().split('|')[1];
                            if (type == type_select)
                            {
                                console.log($(e));
                                var name = $(e).html().split('|')[0];
                                console.log($(e).html().trim());
                                console.log("name");
                                console.log(name.trim());
                                html_ = html_ + "<option value=" + $(e).val() + ">" + name.trim() + "</option>";


                            }
                            // console.log($(e).html());
                        }
                );
                console.log(html_);
                $("#ddParentList").html(html_);
                loadPageListByType(type_select);

            }
            function LoadPageUnderParent()
            {
                var pID = $("#ddParentList :selected").val();
                loadPageList(pID);
            }


            function loadPageListByType(pageType)
            {
                $('#pageListTB').DataTable({
                    "processing": true,
                    "destroy": true,
                    "paging": true,
                    "pageLength": 10,
                    "serverSide": true,
                    "filter": false,
                    "ajax": "${Contexpath}/page/pageallbytype?pageType=" + pageType,
                    "columns": [
                        {"data": "name"},
                        {"data": "nameBangla"},
                        {"data": "url"},
                        {"data": "pPageName"},
                        {"data": "type"},
                        {"data": function (data) {
                                return "<a href='#' id='btnSelect' value='Edit'  onclick='editPage(\"" + data.id + "\",\"" + data.name + "\",\"" + data.nameBangla + "\",\"" + data.url + "\",\"" + data.pPageName + "\",\"" + data.type + "\")'>Select</a>"
                            }}

                    ]
                });
            }
            function loadPageList(pId)
            {
                $('#pageListTB').DataTable({
                    "processing": true,
                    "destroy": true,
                    "paging": true,
                    "pageLength": 10,
                    "serverSide": true,
                    "filter": false,
                    "ajax": "${Contexpath}/page/pageall?pId=" + pId,
                    "columns": [
                        {"data": "name"},
                        {"data": "nameBangla"},
                        {"data": "url"},
                        {"data": "pPageName"},
                        {"data": "type"},
                        {"data": function (data) {
                                var allData = JSON.stringify(data);
                                return "<a href='#' id='btnSelect' value='Edit'  onclick='editPage(" + allData + ")'>Select</a>"
//                                return "<a href='#' id='btnSelect' value='Edit'  onclick='editPage(\"" + data.id + "\",\"" + data.name + "\",\"" + data.nameBangla + "\",\"" + data.url + "\",\"" + data.pPageName + "\",\"" + data.type + "\")'>Select</a>"
                            }}

                    ]
                });
            }
//            function editPage(id, name, nameBangla, url, pPageName, type)
            function editPage(data)
            {
                console.log(data);
                $("#idPage").val(data.id);
                $("#tbPageName").val(data.name);
                $("#tbPageNameBangla").val(data.nameBangla);
                $("#tbPageUrl").val(data.url);
                $("#btnSave").html("Edit");
                $("#type").val(data.type);

                $("#tbCssClass").val(data.cssClass);

                type_select = 0;
                if (data.type == "8")
                    type_select = ["9"];
                else if (data.type == "0")
                    type_select = ["9", "8"];
                else
                    type_select = ["0"];
                $("#ddParentList").empty();
                var html_ = " <option value=0>-------Select-------</option>";
                $("#ddPageTypeSelect option").each(
                        function (i, e)
                        {
                            var type = $(e).html().split('|')[1];
                            if (type_select.includes(type))
                            {
                                console.log($(e));
                                var name = $(e).html().split('|')[0];
                                console.log($(e).html().trim());
                                console.log("name");
                                console.log(name.trim());
                                html_ = html_ + "<option value=" + $(e).val() + ">" + name.trim() + "</option>";


                            }
                            // console.log($(e).html());
                        }
                );
                console.log(html_);
                $("#ddParentList").html(html_);



                $("#ddParentList option").each(function () {
                    this.selected = $(this).text() === data.pPageName;
                });

            }
            $(document).ready(function ()
            {


                console.log("get ready");
                loadPageList(0);
            })
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>
