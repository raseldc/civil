<%-- 
    Document   : controller
    Created on : Feb 7, 2019, 3:44:43 PM
    Author     : rasel
--%>




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
        <section class="content">
            <div class="row">
            <div class="col-md-12">

                <sf:form action="${Contexpath}/${action}" modelAttribute="model" id="fPageForm" method="POST">
                        <div class="box">
                            <div class="box-header bg-color">
                                 <h3 class="box-title">Controller Add/Edit</h3><small> </small></div>
                            <div class="box-body card-block">
                                <div class="form-group">
                                    <label for="company" class=" form-control-label">Controller(ENG)</label>
                                    <sf:input type="hidden" id="idPage" class="form-control" path="id"/>
                                    <sf:input type="text" id="tbPageName" placeholder="Enter your page name" class="form-control" path="name"/>
                                </div>
<!--                                <div class="form-group">
                                    <label for="company" class=" form-control-label">Controller(BAN)</label>
                                    <%--<sf:input type="hidden" id="idPage" class="form-control" path="id"/>--%>
                                    <%--<sf:input type="text" id="tbPageNameBangla" placeholder="Enter your page name" class="form-control" path="nameBangla"/>--%>
                                </div>-->
                                <div class="form-group">
                                    <label for="vat" class=" form-control-label">URL</label>
                                    <sf:input type="text" id="tbPageUrl" placeholder="Enter your url" class="form-control" path="url"/>
                                </div>
                                <div class="form-group">
                                    <label for="vat" class=" form-control-label">Parent Page</label>
                                    <sf:select path="page" id="ddParentList" class="form-control" style="background-color : white; " onchange="LoadPageUnderParent()">
                                        <sf:option value="0" label="--- Select ---" />
                                        <sf:options items="${pPageList}" itemValue="id" itemLabel="name" path="page" />
                                    </sf:select>
                                </div>

                                <div class="form-group">
                                    <label for="vat" class=" form-control-label">Status</label>
                                    <sf:select path="type" class="form-control" style="background-color : white; ">
                                        <sf:option value="4" label="--- Select ---" />
                                        <sf:option value="3" label="Menu Page" />
                                        <sf:option value="0" label="Parent page" />
                                        <sf:option value="4" label="other" />


                                    </sf:select>
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
                        <div class="box-header">
                             <h3 class="box-title">Controller</h3>
                        </div>
                        <div class="box-body">
                            <table id="pageListTB" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <!--<th>Name(BAN)</th>-->
                                        <th>URL</th>
                                        <th>Parent Page</th>
                                        <th>Type</th>
                                        <th>Edit</th>

                                    </tr>
                                </thead>
                      
                            </table>
                        </div>
                    </div>
                </div>


            </div>
        </section>

    <script src="${Contexpath}/resources/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="${Contexpath}/resources/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>

        <script type="text/javascript">
            function LoadPageUnderParent()
            {
                var pID = $("#ddParentList :selected").val();
                loadPageList(pID);
            }
            function loadPageList(pId)
            {
                console.log("get");
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
//                        {"data": "nameBangla"},
                        {"data": "url"},
                        {"data": "pPageName"},
                        {"data": "type"},
                        {"data": function (data) {
                                  var allData = JSON.stringify(data);
                                  console.log(allData);
                                  return "<a href='#' id='btnSelect' value='Edit'  onclick='editPage(" + allData + ")'>Edit</a>"
                            //    return "<a href='#' id='btnSelect' value='Edit'  onclick='editPage(\"" + data.id + "\",\"" + data.name + "\",\"" + data.nameBangla + "\",\"" + data.url + "\",\"" + data.pPageName + "\",\"" + data.type + "\")'>Select</a>"
                            }}

                    ]
                });
            }
          //  function editPage(id, name, nameBangla, url, pPageName, type)
            function editPage(data)
            {
                console.log("Edit page");
                $("#idPage").val(data.id);
                $("#tbPageName").val(data.name);
//                $("#tbPageNameBangla").val(data.nameBangla);
                $("#tbPageUrl").val(data.url);
                $("#btnSave").html("Edit");
                $("#type").val(data.type);
                $("#ddParentList option").each(function () {
                    this.selected = $(this).text() == pPageName;
                });
            }
            $(document).ready(function ()
            {
                console.log("get ready")
                loadPageList(0);
            })
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>
