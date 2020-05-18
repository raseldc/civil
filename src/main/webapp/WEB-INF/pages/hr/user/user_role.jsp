<%-- 
    Document   : user_role
    Created on : Feb 3, 2019, 2:43:32 PM
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

        <!-- Content -->
        <div class="content">
            <div class="animated fadeIn">
                <c:url var="updateUrl" value="${action}"/>

                <div class="col-lg-12">
                    <div class="box">
                        <div class="box-header bg-color">
                           <h3 class="box-title">Role Page Relation</h3>
                        </div>
                        <div class="box-body card-block">
                            <div class="form-group">
                                <label for="company" class=" form-control-label">Page</label>                                    
                                <select id="ddpage" class="form-control" style="background-color: white" >

                                    <c:if  test="${!empty userList}">
                                        <option  Value="0">select</option>
                                        <c:forEach items="${userList}" var="user">
                                            <option  Value="${user.id}">${user.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>  
                            <div class="form-group">

                                <label for="company" class=" form-control-label">Role</label>                                    
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
                                <!--<label for="vat" class=" form-control-label">VAT</label>-->
                                <input type="button" id="btnSave" class="btn btn-success" value="Submit" onclick="submit()">
                            </div>
                        </div>
                    </div>
                </div>



                <div class="row">

                    <div class="col-md-12">
                        <div class="box">
                            <div class="box-header bg-color">
                                <h3 class="box-title">Role List</h3>
                            </div>
                            <div class="box-body">
                                <table id="pageListTB" class="table table-striped table-bordered" style="width: 100%">
                                    <thead>
                                        <tr>
                                            <th>Name</th>                                           
                                            <th>url</th>    

                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>

                                            <th>Name</th>                                            
                                            <th>url</th>    

                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
        <script src="${Contexpath}/resources/assets/js/lib/data-table/datatables.min.js"></script>
        <script src="${Contexpath}/resources/assets/js/lib/data-table/dataTables.bootstrap.min.js"></script>

    </tiles:putAttribute>
</tiles:insertDefinition>