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
        <style>
            div.scroll
            {
                width: 100%;
                height: 200px;
                overflow: auto;
                resize:  vertical;
            }
            .form-control {
                border-radius: 10px;
                box-shadow: none;
                border-color: 
                    #979899;
            }
            .table-bordered {
                border: 3px solid 
                    #7e7b7b;
            }
        </style>
        <!-- Content -->
        <div class="content">
            <div >
                <c:url var="updateUrl" value="${action}"/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="box">
                            <div class="box-header bg-color">
                                <h3 class="box-title">Project Deatail</h3>
                            </div>
                            <div class="box-body box-block">
                                <div class="col-lg-12" id="divMsg" style="text-align: center">
                                    <div class="col-lg-12" style="color: red">
                                        <label id="msg"></label>
                                    </div>
                                </div>

                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-lg-2">
                                            <%@include file="../searcHelp/projectSearch.jsp" %>     
                                        </div> 
                                        <div class="col-lg-2">
                                            <%@include file="../searcHelp/itemSearch.jsp" %> 
                                        </div> 
                                    </div>



                                    <br/>
                                    <div class="row">
                                        <label for="inputEmail">Project name</label>
                                        <input type="text" class="form-control" id="tbProjectName" readonly="true" placeholder="Name" name="tbProjectName"/>
                                        <input type="hidden" value="0" id="tbProjectId"/>



                                    </div>

                                </div>

                                <div class="row">

                                </div>

                                <div class="row">
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <label for="inputEmail">Item Name</label>
                                            <input type="text" class="form-control" id="tbItemName" readonly="true" placeholder="Name" name="tbItemName"/>
                                            <input type="hidden" value="0" id="tbItemId"/>

                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="form-group ">
                                            <label for="inputEmail" class="control-label">Item Unit</label>
                                            <input type="text" class="form-control" id="tbUnit" placeholder="Name" name="name"/>
                                        </div>
                                    </div>

                                    <div class="col-lg-4">
                                        <div class="form-group ">
                                            <label class="control-label ">Item Price</label>
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
                                        <input type="button" value="Save/Edit" onclick="projectDetailsSave()"/>

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
                            <div class="box-header bg-color">

                                <div class="col-lg-12">
                                    <div class="col-lg-4"> 
                                        <h3 class="box-title">Item List</h3></div>
                                    <div class="col-lg-8" style="text-align: right">
                                        <input type="button" value="Hide" style="color:black" onclick="showHideDv('dvItemTb')"/>
                                    </div>

                                </div>

                            </div>
                            <div class="box-body" id="dvItemTb">
                                <table id="itemListForProjectTB" class="table table-striped table-bordered" style="width: 100%">
                                    <thead>
                                        <tr>
                                            <th>CE's Item No</th>                                            
                                            <th>Description of Item</th>
                                            <th>Unit</th>
                                            <th>Price</th>
                                            <th>Calculation Result</th>
                                            <th>Edit</th>
                                            <th>Calculation</th>
                                        </tr>
                                    </thead>

                                </table>
                            </div>
                        </div>
                    </div>


                </div>

                <div class="row" id="dv-calculate" style="display: none">

                    <div class="col-md-12">
                        <div class="box">
                            <div class="box-header bg-color">
                                <h3 class="box-title">Calculation</h3>
                            </div>
                            <div class="box-body">

                                <ul class="nav nav-tabs" id="nav-tab" role="tablist">


                                    <li class="active">  <a data-toggle="tab" href="#nav-rft-sft-cft" role="tab" aria-controls="nav-rft-sft-cft" aria-selected="true">rft-sf-cft </a></li>
                                    <li><a id="nav-personalInfo-tab" data-toggle="tab" href="#nav-personalInfo" role="tab" aria-controls="nav-personalInfo" aria-selected="false">cal2</a></li>
                                    <li><a id="nav-EducationInfo-tab" data-toggle="tab" href="#nav-EducationInfo" role="tab" aria-controls="nav-EducationInfo" aria-selected="false">cal3</a></li>
                                    <li><a id="nav-JobInfo-tab" data-toggle="tab" href="#nav-CertificateInfo" role="tab" aria-controls="nav-JobInfo" aria-selected="false">Cal4</a></li>
                                    <li><a id="nav-JobInfo-tab" data-toggle="tab" href="#nav-JobInfo" role="tab" aria-controls="nav-JobInfo" aria-selected="false">Cal5</a></li>
                                </ul>
                                <div class="tab-content"  id="nav-tabContent">
                                    <div class="tab-pane active" id="nav-rft-sft-cft"> 
                                        <div class="content" style="min-height: auto">

                                            <div class="row col-lg-12">
                                                <div class="col-lg-2">
                                                    <label>Group</label>
                                                    <input type="text" id="tbGroupName"/>
                                                </div>
                                                <div class="col-lg-2">

                                                    <select id="ddGroup">
                                                        <option value="0"> Select Group</option>
                                                        <option value="1"> Addition</option>
                                                        <option value="2"> Deduction</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-2">
                                                    <input type="button" id="btnEntryData" value="Add Table" onclick="itemAddToTable()"/>
                                                </div>
                                                <div class="col-lg-2">
                                                    <input type="button" id="btnEntryData" value="Save" onclick="itemDetailsSave()"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="dv-tbTamplate" style="display: none">
                                            <div class="row container-fluid">
                                                <div class="col-lg-12">
                                                    <label id="lbTabName" style="font-size: 18px"></label>
                                                </div>
                                            </div>
                                            <table id="tb-template" class="table table-bordered stripe">
                                                <thead>
                                                    <tr>
                                                        <td> Text 1</td>
                                                        <td> in 1</td>
                                                        <td> in2 </td>
                                                        <td>in3</td>
                                                        <td> ft-in</td>
                                                        <td>result</td>                                                      
                                                        <td>Delete</td>
                                                        <td>Add New Row</td>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr id="tb-temp-subTotal">
                                                        <td>Total </td>
                                                        <td> in 1</td>
                                                        <td> in2 </td>
                                                        <td>in3</td>
                                                        <td> ft-in</td>
                                                        <td>result</td>

                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div  class="scroll" id="dvTableForAddiDeduc">


                                            <div id="divdetailsAddition">
                                            </div>
                                            <div id="divdetailsDeduction">
                                            </div>
                                        </div>

                                    </div>
                                    <div class="tab-pane active" id="nav-personalInfo"> 
                                        Personal  Info
                                    </div>
                                    <div class="tab-pane active" id="nav-EducationInfo"> 
                                        Personal  Infodfsd f
                                    </div>
                                </div>



                            </div>                       
                        </div> 
                    </div> 
                </div>  

            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.js "></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js "></script>
        <script src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js "></script>
        <script src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.flash.min.js "></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js "></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js "></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js "></script>
        <script src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.html5.min.js "></script>
        <script src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.print.min.js "></script>

        <link href="${Contexpath}/resources/editor.dataTables.min.css" rel="stylesheet" type="text/css" />

        <script src="${Contexpath}/resources/ColReorderWithResize.js" type="text/javascript"></script>

        <script type="text/javascript">

                                                        var itemObject = new Object();
                                                        var itemDetails = [];
                                                        var civilItemDetails = [];
                                                        var elecItemDetails = [];

                                                        var mainCivilItemDetails = [];
                                                        var mainElecItemDetails = [];


                                                        var additionTableName = [];
                                                        var deductionTableName = [];
                                                        var arryOfProjectItemDetailHitoryDetail = [];

                                                        function showHideDv(dv) {
                                                            var x = document.getElementById(dv);
                                                            if (x.style.display === "none") {
                                                                x.style.display = "block";
                                                            } else {
                                                                x.style.display = "none";
                                                            }
                                                        }
                                                        function projectDetailsSave() {
                                                            
                                                            var projectDetailInfo = {
                                                                "projectId": $("#tbProjectId").val(),
                                                                "itemList": $("#tbItemId").val(),
                                                                "quantity": $("#tbUnit").val(),
                                                                "totalPrice": $("#tbPrice").val()
                                                            };
                                                            $.ajax(
                                                                    {
                                                                        type: 'POST',
                                                                        url: "${Contexpath}/project/projectdetailadd",
                                                                        contentType: 'application/json; charset=utf-8',
                                                                        data: JSON.stringify(projectDetailInfo),
                                                                        dataType: 'json',
                                                                        async: false,
                                                                        success: function (data) {
                                                                            console.log("OK");
                                                                            loadProjectInfo($("#tbProjectId").val());

                                                                            $("#msg").html(data.errorMsg);
                                                                            setTimeout(function () {
                                                                                $("#divMsg").hide();
                                                                            }, 15000);
                                                                            //                                                                            console.log("sucess\n");
                                                                            //                                                                            console.log("Data\n" + data);
                                                                            //
                                                                            //                                                                            $("#dvReserVationInfo").hide();

                                                                            //window.location.replace("${Contexpath}/Reservation/ReservatonAll");
                                                                            //   //console.log("data : " + data[0].mailAddress);
                                                                            // searchReservation();
                                                                        },
                                                                        failure: function () {
                                                                            console.log("Failed");
                                                                        }

                                                                    });
                                                        }

                                                        //Calculation for value change and sub totla start
                                                        function calculateResultForFildValueChange(infoAboutTbale)
                                                        {
                                                            console.log(typeof (infoAboutTbale));
                                                            console.log("infoAboutTbale " + infoAboutTbale);
                                                            var ItemId = infoAboutTbale.tr_id;
                                                            console.log("ItemId  " + ItemId);
                                                            console.log("in1  " + $("#" + ItemId + " td:nth-child(2)").find("input").val());
                                                            var text = $("#" + ItemId + " td:nth-child(1)").find("input").val();
                                                            var in1 = $("#" + ItemId + " td:nth-child(2)").find("input").val() === "" ? 0 : $("#" + ItemId + " td:nth-child(2)").find("input").val();

                                                            var in2 = $("#" + ItemId + " td:nth-child(3)").find("input").val() === "" ? 0 : $("#" + ItemId + " td:nth-child(3)").find("input").val();

                                                            var in3 = $("#" + ItemId + " td:nth-child(4)").find("input").val() === "" ? 0 : $("#" + ItemId + " td:nth-child(4)").find("input").val();

                                                            var ftIn = $("#" + ItemId + " td:nth-child(5)").find("input").val() === "" ? 0 : $("#" + ItemId + " td:nth-child(5)").find("input").val();

                                                            var Fit = ftIn.split("-")[0];
                                                            var inc = 0;
                                                            if (typeof (ftIn.split('-')[1]) === "undefined") {
                                                                inc = 0;
                                                            } else
                                                                inc = ftIn.split('-')[1];
                                                            ftIn = parseInt(Fit) + (parseInt(inc) / 12);

                                                            console.log("fitin " + ftIn)
                                                            var result = in1 * in2 * in3 * ftIn;

                                                            $("#" + ItemId + " td:nth-child(6)").find("input").val(result);
                                                            console.log("in1 " + in1);
                                                            subtotalCalculat(infoAboutTbale.grpName);
                                                        }
                                                        function subtotalCalculat(grpName)
                                                        {
                                                            var subTotal = 0;
                                                            $("#tb-" + grpName + " tbody").find("tr").each(function (i, e) {
                                                                console.log(i);
                                                                console.log(e);
                                                                var result = $(e).find("td:nth-child(6)").find("input").val();
                                                                if (typeof (result) === "undefined")
                                                                    result = 0;

                                                                subTotal = subTotal + parseInt(result);
                                                            });
                                                            $("#tb-" + grpName + "-subTotal td:nth-child(6)").html(subTotal);

                                                        }

                                                        //Calculation for value change and sub totla end       
                                                        function crateRowAftrThis(infoABoutTbale)
                                                        {
                                                            var grpName = infoABoutTbale.grpName;
                                                            if (typeof (in1) === "undefined") {
                                                                in1 = in2 = in3 = ftin = 0;

                                                            }
                                                            var pre_tr_id = infoABoutTbale.tr_id;
                                                            var tr_count = $("#tb-" + grpName + " tbody").find("tr").length - 1;
                                                            var tr_id = "tr-" + grpName + "-" + tr_count + "";


                                                            var infoABoutTbale = {
                                                                "grpName": grpName,
                                                                "tr_id": tr_id
                                                            };
                                                            infoABoutTbale = JSON.stringify(infoABoutTbale);
                                                            var tr_html = " <tr id='" + tr_id + "'>" +
                                                                    "<td> <input class='form-control' type='text'  value='" + name + "'/></td>" +
                                                                    "<td> <input class='form-control' type='text'  onchange='calculateResultForFildValueChange(" + infoABoutTbale + ")' value=' " + in1 + "'/></td>" +
                                                                    "<td> <input class='form-control' type='text'  onchange='calculateResultForFildValueChange(" + infoABoutTbale + ")' value=' " + in2 + "'/></td>" +
                                                                    "<td> <input class='form-control' type='text'  onchange='calculateResultForFildValueChange(" + infoABoutTbale + ")' value=' " + in3 + "'/></td>" +
                                                                    "<td> <input class='form-control' type='text'  onchange='calculateResultForFildValueChange(" + infoABoutTbale + ")' value=' " + ftin + "'/></td>" +
                                                                    "<td> <input class='form-control' type='text'  value=' " + 0 + "'/></td>" +
                                                                    //                                                                    " <td><input type=\"button\" value=\"Edit\" onclick=\"edit_itemLine('" + tr_id + "')\"></td>" +
                                                                    "   <td><input class='btn btn-danger btn-sm' type=\"button\" value=\"Delete\" onclick='delete_itemLine(" + infoABoutTbale + ")'></td>" +
                                                                    //                                                                    "   <td><input class='btn btn-info btn-sm' type=\"button\" value=\"Add New Row\" onclick=\"addNewsRow('" + grpName + "')\"></td>" +
                                                                    "   <td><input class='btn btn-info btn-sm' type=\"button\" value=\"Add New Row\" onclick='crateRowAftrThis(" + infoABoutTbale + ")'></td>" +
                                                                    "</tr>";

                                                            console.log(tr_html);
                                                            $("#" + pre_tr_id).after(tr_html);


                                                            var infoABoutTbale = {
                                                                "grpName": grpName,
                                                                "tr_id": tr_id
                                                            };
                                                            calculateResultForFildValueChange(infoABoutTbale);
                                                        }
                                                        function addNewsRow(grpName, in1, in2, in3, ftin, name)
                                                        {
                                                            console.log($(this));
                                                            if (typeof (in1) === "undefined") {
                                                                in1 = in2 = in3 = ftin = 0;
                                                                name = "";
                                                            }
                                                            var tr_count = $("#tb-" + grpName + " tbody").find("tr").length - 1;
                                                            var tr_id = "tr-" + grpName + "-" + tr_count + "";


                                                            var infoABoutTbale = {
                                                                "grpName": grpName,
                                                                "tr_id": tr_id
                                                            };
                                                            infoABoutTbale = JSON.stringify(infoABoutTbale);
                                                            var tr_html = " <tr id='" + tr_id + "'>" +
                                                                    "<td> <input class='form-control' type='text'  value='" + name + "'/></td>" +
                                                                    "<td> <input class='form-control' type='text'  onchange='calculateResultForFildValueChange(" + infoABoutTbale + ")' value=' " + in1 + "'/></td>" +
                                                                    "<td> <input class='form-control' type='text'  onchange='calculateResultForFildValueChange(" + infoABoutTbale + ")' value=' " + in2 + "'/></td>" +
                                                                    "<td> <input class='form-control' type='text'  onchange='calculateResultForFildValueChange(" + infoABoutTbale + ")' value=' " + in3 + "'/></td>" +
                                                                    "<td> <input class='form-control' type='text'  onchange='calculateResultForFildValueChange(" + infoABoutTbale + ")' value=' " + ftin + "'/></td>" +
                                                                    "<td> <input class='form-control' type='text'  value=' " + 0 + "'/></td>" +
                                                                    //                                                                    " <td><input type=\"button\" value=\"Edit\" onclick=\"edit_itemLine('" + tr_id + "')\"></td>" +
                                                                    "   <td><input class='btn btn-danger btn-sm' type=\"button\" value=\"Delete\" onclick='delete_itemLine(" + infoABoutTbale + ")'></td>" +
                                                                    //                                                                    "   <td><input class='btn btn-info btn-sm' type=\"button\" value=\"Add New Row\" onclick=\"addNewsRow('" + grpName + "')\"></td>" +
                                                                    "   <td><input class='btn btn-info btn-sm' type=\"button\" value=\"Add New Row\" onclick='crateRowAftrThis(" + infoABoutTbale + ")'></td>" +
                                                                    "</tr>";

                                                            var subTotal_html = $("#tb-" + grpName + "-subTotal").html();
                                                            $("#tb-" + grpName + "-subTotal").remove();
                                                            subTotal_html = "<tr id='tb-" + grpName + "-subTotal'>" + subTotal_html + "</tr>";

                                                            $("#tb-" + grpName + " tbody").append(tr_html);
                                                            $("#tb-" + grpName + " tbody").append(subTotal_html);

                                                            var infoABoutTbale = {
                                                                "grpName": grpName,
                                                                "tr_id": tr_id
                                                            };
                                                            calculateResultForFildValueChange(infoABoutTbale);

                                                        }
                                                        function delete_itemLine(infoABoutTbale)
                                                        {

                                                            $("#" + infoABoutTbale.tr_id + "").remove();
                                                            subtotalCalculat(infoABoutTbale.grpName);

                                                        }

                                                        function clearItemFileds()
                                                        {
                                                            $("#tbName").val("");
                                                            $("#tbIn1").val("");
                                                            $("#tbIn2").val("");
                                                            $("#tbIn3").val("");
                                                            $("#tbFtIn").val("");
                                                        }
                                                        function crateTbaleIfNotExist(grpName, isAddition)
                                                        {
                                                            grpName = grpName.toLowerCase();

                                                            if ($("#tb-" + grpName).length === 0)
                                                            {
                                                                var html_ = $("#dv-tbTamplate").html();
                                                                html_ = html_.replace("tb-template", "tb-" + grpName);
                                                                html_ = html_.replace("tb-temp-subTotal", "tb-" + grpName + "-subTotal");
                                                                html_ = html_.replace("lbTabName", "lb-" + grpName + "-tableName");

                                                                if (isAddition === 1)
                                                                {

                                                                    $("#divdetailsAddition").append(html_);
                                                                    var tablNameCount = additionTableName.length;

                                                                    additionTableName[tablNameCount] = "tb-" + grpName;
                                                                } else if (isAddition === 2) {

                                                                    $("#divdetailsDeduction").append(html_);
                                                                    var tablNameCount = deductionTableName.length;
                                                                    deductionTableName[tablNameCount] = "tb-" + grpName;
                                                                }
                                                                $("#lb-" + grpName + "-tableName").html(grpName.toString().toUpperCase());


                                                                return 1;
                                                            } else
                                                            {
                                                                return 0;

                                                                //alert("Table already exist");
                                                            }
                                                        }

                                                        function itemAddToTable()
                                                        {

                                                            var grpName = $("#tbGroupName").val().toLowerCase();
                                                            var isAddition = 1;
                                                            if ($("#ddGroup :selected").val() === "2")
                                                            {
                                                                isAddition = 2;
                                                            }
                                                            var result = crateTbaleIfNotExist(grpName, isAddition);
                                                            console.log("Result " + result);
                                                            if (result === 1)
                                                            {
                                                                console.log("Result " + result);
                                                                addNewsRow(grpName, 0, 0, 0, 0, "");
                                                                clearItemFileds();

                                                            } else
                                                            {
                                                                alert("Table already exist");
                                                            }

                                                        }
                                                        function loadProjectInfo(projectId)
                                                        {
                                                            additionTableName = [];
                                                            deductionTableName = [];
                                                            arryOfProjectItemDetailHitoryDetail = [];
                                                            $('#itemListForProjectTB').DataTable({
                                                                "processing": true,
                                                                "destroy": true,
                                                                "paging": true,
                                                                "pageLength": 10,
                                                                "serverSide": false,
                                                                "filter": true,
                                                                dom: 'Bfrtip',
                                                                buttons: [
                                                                    //'copy', 'csv', 'excel', 'pdf'

                                                                    {extend: 'copyHtml5', footer: true},
                                                                    {extend: 'excelHtml5', footer: true},
                                                                    {extend: 'csvHtml5', footer: true},
                                                                    {extend: 'pdfHtml5', footer: true},
                                                                    {extend: 'print', footer: true}
                                                                ],
                                                                "ajax": "${Contexpath}/project/getitemlistforthisproject?projectid=" + projectId,
                                                                "columns": [
                                                                    {"data": "itemDetail.showCode"},
                                                                    {"data": "itemDetail.parentItemDescription"},
                                                                    {"data": "itemDetail.unit"},
                                                                    {"data": "itemDetail.price"},
                                                                    {"data": "calculationSummary",
                                                                        "width": "30%"},
                                                                    {"data": function (data) {
                                                                            var alldata = JSON.stringify(data.itemDetail);
                                                                            return "<a href='#' id='btnSelect' value='Edit'  onclick='detete(" + alldata + ")'>Detele</a>";
                                                                        }},
                                                                    {"data": function (data) {
                                                                            var alldata = JSON.stringify(data.itemDetail);
                                                                            return "<a href='#' id='btnSelect' value='Edit'  onclick='itemCalculation(" + alldata + ")'>Calculation</a>";
                                                                        }}

                                                                ]
                                                            });
                                                        }

                                                        var projectId;
                                                        var itemId;
                                                        function itemCalculation(itemDetail)
                                                        {
                                                            $("#divdetailsAddition").html("");
                                                            $("#divdetailsDeduction").html("");
                                                            $("#dv-calculate").show();
                                                            additionTableName = [];
                                                            deductionTableName = [];
                                                            projectId = $("#tbProjectId").val();
                                                            itemId = itemDetail.id;

                                                            var projectItemDetailInfo = {
                                                                "projectId": $("#tbProjectId").val(),
                                                                "itemId": itemId

                                                            };
                                                            $.ajax({
                                                                type: 'POST',
                                                                url: "${Contexpath}/project/getall-project-item-detail-history",
                                                                contentType: 'application/json; charset=utf-8',
                                                                data: JSON.stringify(projectItemDetailInfo),
                                                                dataType: 'json',
                                                                async: false,
                                                                success: function (data) {
                                                                    console.log(data);
                                                                    $.each(data, function (i, e) {
                                                                        console.log(e);
                                                                        crateTbaleIfNotExist(e.title, e.isAddition);
                                                                        addNewsRow(e.title.toLowerCase(), e.in1, e.in2, e.in3, e.ftIn, e.name);
                                                                        subtotalCalculat(e.title.toLowerCase());
                                                                    });

                                                                },
                                                                failure: function () {
                                                                    console.log("Failed");
                                                                }

                                                            });



                                                        }
                                                        var ProjectItemDetailHitoryDetail = function itemDetailHistoryFunction()
                                                        {

                                                            var projectId = 10;
                                                            var ItemId;
                                                            var int1;
                                                            var int2;
                                                            var int3;
                                                            var ft_in;
                                                            var title;
                                                            var isAddition;
                                                            var group;
                                                            var name;
                                                            function setProjectId(projectId_val)
                                                            {
                                                                projectId = projectId_val;
                                                            }
                                                            function getProjectId(projectId_val)
                                                            {
                                                                projectId = projectId_val;
                                                            }
                                                        };



                                                        function itemDetailsSave()
                                                        {


                                                            arryOfProjectItemDetailHitoryDetail = [];
                                                            var j = 0;
                                                            while (j < 2)
                                                            {
                                                                var loopCount = 0;
                                                                if (j === 0)
                                                                {
                                                                    loopCount = additionTableName.length;
                                                                } else
                                                                {
                                                                    loopCount = deductionTableName.length;
                                                                }

                                                                for (var i = 0; i < loopCount; i++)
                                                                {
                                                                    var add_tabl_id = "";
                                                                    var isAddition = 1;

                                                                    add_tabl_id = additionTableName[i];
                                                                    if (j === 1) {
                                                                        add_tabl_id = deductionTableName[i];
                                                                        isAddition = 2;

                                                                    }

                                                                    $("#" + add_tabl_id + " tbody").find("tr").each(function (i, e) {
                                                                        console.log(i);
                                                                        var ItemId = $(e).attr("id");
                                                                        if (ItemId === add_tabl_id + "-subTotal")
                                                                        {

                                                                        } else {
                                                                            var projectItemObject = new ProjectItemDetailHitoryDetail();
                                                                            projectItemObject.name = $("#" + ItemId + " td:nth-child(1)").find("input").val() === "" ? 0 : $("#" + ItemId + " td:nth-child(1)").find("input").val();
                                                                            projectItemObject.in1 = $("#" + ItemId + " td:nth-child(2)").find("input").val() === "" ? 0 : $("#" + ItemId + " td:nth-child(2)").find("input").val();
                                                                            projectItemObject.in2 = $("#" + ItemId + " td:nth-child(3)").find("input").val() === "" ? 0 : $("#" + ItemId + " td:nth-child(3)").find("input").val();
                                                                            projectItemObject.in3 = $("#" + ItemId + " td:nth-child(4)").find("input").val() === "" ? 0 : $("#" + ItemId + " td:nth-child(4)").find("input").val();
                                                                            projectItemObject.ftIn = $("#" + ItemId + " td:nth-child(5)").find("input").val() === "" ? 0 : $("#" + ItemId + " td:nth-child(5)").find("input").val();

                                                                            projectItemObject.group = add_tabl_id;
                                                                            projectItemObject.isAddition = isAddition;
                                                                            projectItemObject.title = add_tabl_id.slice(3);
                                                                            projectItemObject.projectId = projectId;
                                                                            projectItemObject.itemId = itemId;
                                                                            console.log(projectItemObject);

                                                                            arryOfProjectItemDetailHitoryDetail[arryOfProjectItemDetailHitoryDetail.length] = projectItemObject;
                                                                        }
                                                                    });

                                                                }

                                                                j = j + 1;

                                                            }


                                                            $.ajax({
                                                                type: 'POST',
                                                                url: "${Contexpath}/project/project-item-detail-history",
                                                                contentType: 'application/json; charset=utf-8',
                                                                data: JSON.stringify(arryOfProjectItemDetailHitoryDetail),
                                                                dataType: 'json',
                                                                async: false,
                                                                success: function (data) {
                                                                    console.log("OK");
                                                                    loadProjectInfo($("#tbProjectId").val());
                                                                    $("#dv-calculate").hide();
                                                                    $("#msg").html(data.errorMsg);
                                                                    setTimeout(function () {
                                                                        $("#divMsg").hide();
                                                                    }, 15000);
                                                                    //                                                                            console.log("sucess\n");
                                                                    //                                                                            console.log("Data\n" + data);
                                                                    //
                                                                    //                                                                            $("#dvReserVationInfo").hide();

                                                                    //window.location.replace("${Contexpath}/Reservation/ReservatonAll");
                                                                    //   //console.log("data : " + data[0].mailAddress);
                                                                    // searchReservation();
                                                                },
                                                                failure: function () {
                                                                    console.log("Failed");
                                                                }

                                                            });
                                                        }

                                                        function loadItem()
                                                        {

                                                            $.ajax(
                                                                    {
                                                                        type: 'POST',
                                                                        url: "${Contexpath}/item/get-all-item",
                                                                        dataType: 'json',
                                                                        async: false,
                                                                        success: function (data) {
                                                                            console.log(data);
                                                                            if (data.isError === false)
                                                                            {
                                                                                console.log(data.returnObject);
                                                                                $.merge(itemDetails, data.returnObject);

                                                                                itemDetails.forEach(function (e, i) {
                                                                                    e["child"] = [];
                                                                                    itemObject[e.id] = e;
                                                                                    if (e.parent_id !== null)
                                                                                    {
                                                                                        itemObject[e.parent_id].child.push(e.id);
                                                                                    }
                                                                                    if (e.code.startsWith("civil"))
                                                                                    {
                                                                                        civilItemDetails.push(e);
                                                                                        if (e.parent_id === null)
                                                                                        {
                                                                                            e["level"] = 1;
                                                                                            mainCivilItemDetails.push(e);
                                                                                        }
                                                                                    } else
                                                                                    {

                                                                                        elecItemDetails.push(e);
                                                                                        if (e.parent_id === null)
                                                                                        {
                                                                                            e["level"] = 1;
                                                                                            mainElecItemDetails.push(e);
                                                                                        }
                                                                                    }
                                                                                });
                                                                            }

                                                                        },
                                                                        failure: function () {
                                                                            console.log("Failed");
                                                                        }

                                                                    });

                                                        }
                                                        function createMap()
                                                        {

                                                            mainCivilItemDetails.forEach(function (e, i) {
                                                                console.log(typeof (e));
                                                                console.log(e);
                                                                e["child"] = [];

                                                                civilItemDetails.forEach(function (f, g)
                                                                {
                                                                    if (f.parent_id == e.id)
                                                                    {
                                                                        e.child.push(f);
                                                                    }
                                                                });
                                                            });


//                                                            itemDetails.forEach(function (e, i) {
//                                                                console.log(typeof (e));
//                                                                console.log(e);
//                                                                e["child"] = [];
//
//                                                                itemDetails.forEach(function (f, g)
//                                                                {
//                                                                    if (f.parent_id == e.id)
//                                                                    {
//                                                                        e.child.push(f);
//                                                                    }
//                                                                });
//                                                            });


                                                        }
                                                        $(document).ready(function () {

                                                            loadItem();
                                                        });
        </script>
    </tiles:putAttribute>

</tiles:insertDefinition>
