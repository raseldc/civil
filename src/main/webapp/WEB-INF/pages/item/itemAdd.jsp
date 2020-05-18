<%-- 
    Document   : itemAdd
    Created on : Jan 3, 2020, 11:25:45 PM
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
        <!-- Content -->
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
                                <div class="row">
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <label for="inputEmail">Item Code</label>
                                            <input type="text" class="form-control" id="tbCode" placeholder="Name" name="code"/>
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="form-group ">
                                            <label for="inputEmail" class="control-label col-xs-4">Item Unit</label>
                                            <input type="text" class="form-control" id="tbUnit" placeholder="Name" name="name"/>
                                        </div>
                                    </div>

                                    <div class="col-lg-4">
                                        <div class="form-group ">
                                            <label class="control-label col-xs-4">Item Price</label>
                                            <input type="text" class="form-control" id="tbPrice" placeholder="Price in BDT" name="price"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-12">

                                        <label for="inputEmail">Item Description</label> 
                                        <textarea rows="4" cols="250" id="taDes">

                                        </textarea> 
                                        <!--<input type="text" class="form-control" id="tbDes" placeholder="Name" name="des"/>-->

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-3">
                                        <input type="button" value="Save/Edit" onclick="ItemAddEdit()"/>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <input type="hidden" id="itemId" value="0"/>
                    </div>
                </div>
                <div class="row">

                    <div class="col-md-12">
                        <div class="box">
                            <div class="card-header bg-color">
                                <h3 class="box-title">Role List</h3>
                            </div>
                            <div class="card-body">
                                <table id="itemListTB" class="table table-striped table-bordered" style="width: 100%">
                                    <thead>
                                        <tr>
                                            <th>Code</th>                                            
                                            <th>Description</th>
                                            <th>Unit</th>
                                            <th>Price</th>
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
            function ItemAddEdit()
            {
                var itemInfo = {
                    "code": $("#tbCode").val(),
                    "description": $("#taDes").val(),
                    "unit": $("#tbUnit").val(),
                    "price": $("#tbPrice").val(),
                    "id": $("#itemId").val()


                };
                $.ajax(
                        {
                            type: 'POST',
                            url: "${Contexpath}/item/itemadd",
                            contentType: 'application/json; charset=utf-8',
                            data: JSON.stringify(itemInfo),
                            dataType: 'json',
                            async: false,
                            success: function (data) {


                                $("#msg").html(data.errorMsg);
                                setTimeout(function () {
                                    $("#divMsg").hide();
                                }, 15000);
                                console.log("sucess\n");
                                console.log("Data\n" + data);
                                loadReservation();
                                $("#dvReserVationInfo").hide();
                                //window.location.replace("${Contexpath}/Reservation/ReservatonAll");
                                //   //console.log("data : " + data[0].mailAddress);
                                // searchReservation();
                            },
                            failure: function () {
                                //   //console.log("Failed");
                            }

                        });
            }
            function loadItemList()
            {
                $('#itemListTB').DataTable({
                    "processing": true,
                    "destroy": true,
                    "paging": true,
                    "pageLength": 10,
                    "serverSide": false,
                    "filter": true,
                    "ajax": "${Contexpath}/item/getitemlist",
                    "columns": [
                        {"data": "code"},
                        {"data": "description"},
                        {"data": "unit"},
                        {"data": "price"},
                        {"data": function (data) {
                                var alldata = JSON.stringify(data);
//                                return "<a href='#' id='btnSelect' value='Edit'  onclick='editRole(\"" + data.id + "\",\"" + data.name + "\")'>Select</a>"
                                return "<a href='#' id='btnSelect' value='Edit'  onclick='editItem(" + alldata + ")'>Select</a>";
                            }}

                    ]
                });
            }
            function editItem(info)
            {
                $("#tbCode").val(info.code);
                $("#taDes").val(info.description);
                $("#tbUnit").val(info.unit);
                $("#tbPrice").val(info.price);
                $("#itemId").val(info.id);
            }



            $(document).ready(function ()
            {
                console.log("123");
                loadItemList();
                $("#frUser").validate({
                    errorClass: "my-error-class",
                    rules: {
                        name: {
                            required: true
                        },
                        email: {
                            required: true
                        },
                        userName: {
                            required: true
                        },
                        passwoord: {required: true},
                        company: {required: true}
                    },
                    messages: {
                        name: {
                            required: "Name required",
                            color: "red"
                        },
                        email: {
                            required: "Email required"
                        },
                        userName: {
                            required: "User required"
                        },
                        passwoord: {
                            required: "Password required"
                        },
                        company: {required: "Select COmpany"}
                    }
                });




            });
        </script>
    </tiles:putAttribute>

</tiles:insertDefinition>