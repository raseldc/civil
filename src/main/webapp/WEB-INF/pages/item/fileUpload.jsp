<%-- 
    Document   : fileUpload
    Created on : Jan 6, 2020, 12:49:46 AM
    Author     : Rasel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<% request.getSession().setAttribute("Contexpath", request.getContextPath());%>

<tiles:insertDefinition name="DefaultTemplate">
    <tiles:putAttribute name="body">
        <div class="content">
            <div >
                <c:url var="updateUrl" value="${action}"/>
                <div class="row">
                    <div class="col-md-12">




                        <div class="box">
                            <div class="box-header bg-color">
                                <h3 class="box-title">Item Add Edit</h3>
                            </div>
                            <div class="box-body box-block">
                                <form method="POST" action="${Contexpath}${actionPath}" enctype="multipart/form-data">
                                    <input type="hidden" id="cmpId"  name="cmpId" value="0"/>
                                  
                                        <div class="row" style="margin-bottom: 20px">
                                            <font color="${color_duplicate}"><h2>${message_NidDuplicate}</h2></font>
                                        </div>


                                        <fieldset>
                                            <legend>Add Employee in Batch</legend>

                                            <div class="row" style="margin-bottom: 20px">
                                                ${message}

                                            </div>

                                            <div class="row">

                                                <div class="col-lg-1">
                                                    Item List
                                                </div>


                                                <div class="col-lg-3">Please select a file to upload :</div>
                                                <div class="col-lg-3">
                                                    <input type="file" name="file" />

                                                </div>

                                            </div>
                                            <div  style="display: none"> File Name: <input type="text" name="name">
                                            </div>
                                            </br>
                                            <div class="row" style="text-align: center">
                                                <input type="submit" value="Press here to upload the file!"> 
                                            </div>
                                        </fieldset>






                                </form>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>





    </tiles:putAttribute>

</tiles:insertDefinition>
