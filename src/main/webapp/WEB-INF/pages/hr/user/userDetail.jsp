<%-- 
    Document   : userDetail
    Created on : Feb 13, 2019, 3:27:28 PM
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
        <style type="text/css">
            div.container {
                margin: 15px;   
            }


            div.left, div.right,label.left,input.right {
                float: left;
                padding: 10px;    
            }

        </style>
        <!-- Content -->
        <div class="content">
            <div class="animated fadeIn">
                <sf:form method="post"   id="frmReg" action="${Contexpath}/${action}" modelAttribute="model">


                    <div class="row">
                        <div class="col-lg-12 ">
                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs" id="nav-tab" role="tablist">


                                    <li class="active">  <a data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true"><spring:message code="applicant.BasicInfo"/> </a></li>
                                    <li><a id="nav-personalInfo-tab" data-toggle="tab" href="#nav-personalInfo" role="tab" aria-controls="nav-personalInfo" aria-selected="false"><spring:message code="applicant.PersonalInfo"/> </a></li>
                                    <li><a id="nav-EducationInfo-tab" data-toggle="tab" href="#nav-EducationInfo" role="tab" aria-controls="nav-EducationInfo" aria-selected="false"><spring:message code="applicant.EducationInfo"/> </a></li>
                                    <li><a id="nav-JobInfo-tab" data-toggle="tab" href="#nav-CertificateInfo" role="tab" aria-controls="nav-JobInfo" aria-selected="false"><spring:message code="applicant.CertificationInfo"/></a></li>
                                    <li><a id="nav-JobInfo-tab" data-toggle="tab" href="#nav-JobInfo" role="tab" aria-controls="nav-JobInfo" aria-selected="false"><spring:message code="applicant.JobInfo"/></a></li>
                                </ul>




                                <div class="tab-content"  id="nav-tabContent">
                                    <div class="tab-pane active" id="nav-home">
                                        <div class="form-group">
                                            <div class="col-lg-4">
                                                <label ><spring:message code="applicant.Name"/></label>
                                            </div>

                                            <div class="col-sm-8">
                                                <sf:input type="hidden" class="form-control col-lg-8" path="id"/>
                                                <sf:input type="text" class="form-control col-lg-8" placeholder="User Name" path="fullName"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-4">
                                                <label  ><spring:message code="applicant.Email"/></label>
                                            </div>
                                            <div class="col-sm-8">
                                                <sf:input type="email" readonly="true" class="form-control col-lg-8" placeholder="Email" path="email"/>
                                            </div>
                                        </div>  
                                

                                        <div class="form-group">
                                            <div class="col-lg-4">
                                                <label  ><spring:message code="applicant.ProficePicture"/></label>
                                            </div>
                                            <div class="col-sm-8">
                                                <input type="file" id="filePicture" onchange="fileUploadPicture()" name="filePicture" />

                                                <!--<button type="button" onclick="fileUploadPicture()" value="" class="btn btn-theme btn-line dark btn-block-xs" >Upload Picture</button>-->

                                                <div class="img-profile" id="dvImageProfilepic">
                                                    <img id="ImageProfilepic_all"  src="${base64_pf}" style="width: 150px;height:120px" src="${Contexpath}/resources/profilePic/${model.email}.jpg" alt="">
                                                </div>
                                                <div class="col-md-4" id="resultPicture" style="text-align: center">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="nav-personalInfo">

                                        <div class="row">
                                            <div class="col-lg-12" style="padding-top: 10px;">
                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.FatherName"/> </label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control " placeholder="Father Name" path="employee.fatherName"/>
                                                        </div>

                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.MotherName"/> </label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control" placeholder="Mother Name" path="employee.motherName"/>
                                                        </div>

                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.MobileNumber"/></label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control " placeholder="Mobile Number" path="employee.mobileNumber"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.NID"/></label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control " placeholder="NID" path="employee.nid"/>
                                                        </div>
                                                    </div>                                    
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.PassportNumber"/></label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control " placeholder="Passport Number" path="employee.passportNumber"/>
                                                        </div>

                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.PermanentAddress"/></label>
                                                        </div>
                                                        <div class="col-lg-8">

                                                            <sf:input type="text" class="form-control" placeholder="Permanent Address" path="employee.permanentAddress"/>
                                                        </div>
                                                    </div>                                    
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.PresentAddress"/></label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control " placeholder="Present Address" path="employee.presentAddress"/>
                                                        </div>
                                                    </div>

                                                </div>

                                                <div class="col-lg-6">
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.Sex"/></label>
                                                        </div>

                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control col-lg-8" placeholder="Sex" path="employee.sex"/>
                                                        </div>


                                                    </div>                                    
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.DOB"/></label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control " placeholder="DOB" path="employee.dob"/>
                                                        </div>

                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.placeOfBirth"/></label>
                                                        </div>
                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control " placeholder="Place Of Birth" path="employee.placeOfBirth"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.Nationality"/></label>
                                                        </div>

                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control " placeholder="Nationality" path="employee.nationality"/>
                                                        </div>

                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.Religion"/></label>
                                                        </div>

                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control " placeholder="Religion" path="employee.religion"/>
                                                        </div>

                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-4">
                                                            <label  ><spring:message code="applicant.BloodGroup"/></label>
                                                        </div>

                                                        <div class="col-lg-8">
                                                            <sf:input type="text" class="form-control " placeholder="Blood Group" path="employee.bloodGroup"/>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane " id="nav-EducationInfo">
                                        <div class="form-group">              
                                            <div  class="col-lg-4">
                                                <label><spring:message code="applicant.Degree"/></label>
                                            </div>

                                            <div  class="col-lg-8">
                                                <sf:input  type="text" class="form-control "  placeholder="degree" path="employee.degree"/>
                                            </div>

                                        </div>  

                                        <div class="form-group ">
                                            <div  class="col-lg-4">
                                                <label ><spring:message code="applicant.Subject"/></label>
                                            </div>
                                            <div  class="col-lg-8">
                                                <sf:input type="text" class="form-control col-lg-8" placeholder="subject" path="employee.subject"/>
                                            </div>

                                        </div>   
                                        <div class="form-group">
                                            <div  class="col-lg-4">
                                                <label  ><spring:message code="applicant.Institution"/></label>
                                            </div>
                                            <div  class="col-lg-8">
                                                <sf:input type="text" class="form-control" placeholder="institution" path="employee.institution"/>
                                            </div>
                                        </div>

                                        <div class="form-group ">
                                            <div  class="col-lg-4">
                                                <label  ><spring:message code="applicant.Year"/></label>
                                            </div>
                                            <div  class="col-lg-8">
                                                <sf:input type="text" class="form-control " placeholder="year" path="employee.year"/>
                                            </div>
                                        </div> 
                                        <div class="form-group ">
                                            <div  class="col-lg-4">
                                                <label ><spring:message code="applicant.CGPA/Class"/></label>
                                            </div>
                                            <div  class="col-lg-8">
                                                <input type="text" class="form-control col-lg-8" placeholder="CGPA" />
                                            </div>
                                        </div> 
                                    </div>

                                    <div class="tab-pane " id="nav-CertificateInfo" >
                                        <div class="form-group ">
                                            <div  class="col-lg-4">  <label  ><spring:message code="applicant.Certificate"/></label></div>
                                            <div  class="col-lg-8">
                                                <select id="ddCertificate" class="form-control" style="background-color: white" >

                                                    <c:if  test="${!empty certList}">
                                                        <option  Value="0">Select</option>
                                                        <c:forEach items="${certList}" var="cert">
                                                            <option  Value="${cert.id}">${cert.name}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>

                                        </div> 
                                        <div class="form-group ">
                                            <div  class="col-lg-4">
                                                <label  ><spring:message code="applicant.Result"/></label>
                                            </div>

                                            <div  class="col-lg-8">
                                                <input type="text" class="form-control col-lg-4" placeholder="Enter Result"  id="tbCertificationResult"/>
                                            </div>


                                        </div> 
                                        <div class="form-group ">
                                            <div  class="col-lg-4"> 
                                                <label  ><spring:message code="applicant.ExpiredDate"/></label>
                                            </div>
                                            <div  class="col-lg-8">
                                                <input type="date" class="form-control col-lg-4" id="tbExpDate"/>
                                            </div>

                                        </div> 
                                        <div class="form-group ">
                                            <div  class="col-lg-4">
                                                <label  ><spring:message code="applicant.File"/></label>
                                            </div>
                                            <div  class="col-lg-8">
                                                <input  multiple type="file" id="files" name="files-cert" id="files-cert" />
                                                <input type="button" class="btn btn-success button btn col-lg-4" value="Add Certification info" style="margin-top: 10px;" onclick="addCertification()"/>
                                                <output id="selectedFiles"></output>
                                            </div>


                                        </div> 
                                        <div class="form-group  ">
                                            <div class="col-lg-12">
                                                <table class="table" id="tblCertificate" style="width:100%;">
                                                    <thead>
                                                        <tr>
                                                            <th>
                                                                <spring:message code="applicant.Certificate"/>

                                                            </th>

                                                            <th>
                                                                <spring:message code="applicant.Result"/>

                                                            </th>
                                                            <th>
                                                                <spring:message code="applicant.ExpiredDate"/>

                                                            </th>
                                                            <th>
                                                                <spring:message code="applicant.File"/>

                                                            </th>

                                                            <th>
                                                                <spring:message code="applicant.Delete"/>

                                                            </th>
                                                        </tr>
                                                    </thead>


                                                </table>
                                            </div>
                                        </div>


                                    </div>

                                    <div class="tab-pane " id="nav-JobInfo" role="tabpanel">

                                        <div class="form-group">
                                            <div class="col-lg-4">
                                                <label>Join Date</label>
                                            </div>

                                            <div class="col-lg-8">
                                                <sf:input type="text" class="form-control " placeholder="Enter Join Date" path="employee.joinDate"/>
                                            </div>

                                        </div> 
                                        <div class="form-group">
                                            <div class="col-lg-4">
                                                <label ><spring:message code="applicant.Post"/></label>
                                            </div>

                                            <div class="col-lg-8">
                                                <sf:input type="text" class="form-control " placeholder="Enter Post Name" path="employee.post"/>
                                            </div>

                                        </div> 
                                        <div class="form-group">
                                            <div class="col-lg-4">
                                                <label  ><spring:message code="applicant.Active"/></label>
                                            </div>

                                            <div class="col-lg-8">
                                                <sf:input type="text" class="form-control " placeholder="Yes / No" path="employee.active"/>
                                            </div>

                                        </div> 
                                    </div>


                                </div>
                            </div>

                        </div>
                    </div>




                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30"><spring:message code="all.submit"/></button>
                    </div>

                </sf:form>
            </div>
        </div>
        <script>

            function addCertification()
            {
                console.log("start");
                var objFormData = new FormData();
                var files_object = $("#files")[0].files[0];
                var i = 0;
                while (i < $("#files")[0].files.length)
                {
                    objFormData.append('file', $("#files")[0].files[i]);
                    console.log(i);
                    i = i + 1;
                }

                var cId = $("#ddCertificate :selected").val();
                var result = $("#tbCertificationResult").val();
                var expDate = $("#tbExpDate").val();
                $.ajax({
                    url: '${Contexpath}/user/addcertificateedu?certificateId=' + cId + '&result=' + result + '&expDate=' + expDate + '&files=' + files_object,
                    data: objFormData,
                    type: "POST",
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        loadCertificateData();
                    },
                    error: function () {
                        console.log("failure");
                        //location.reload();
                    }
                });



            }

            function loadCertificateData()
            {

                $('#tblCertificate').DataTable({
                    "processing": true,
                    "destroy": true,
                    "paging": true,
                    "searching": true,
                    "pageLength": 10,
                    "serverSide": true,
                    "filter": false,
                    "ajax": "${Contexpath}/user/getcertificatelist",
                    "columns": [
                        {"data": "certificationName"},
                        {"data": "result"},
                        {"data": "expired"},
                        {"data": function (data) {
                                return "<a href='${Contexpath}/news/filedownload?filepath=" + data.directories + "' id='btnSelect' value='Edit'>File Download</a>"
                            }},
                        {"data": function (data) {
                                return "<a href='#' id='btnSelect' value='Edit'  onclick='deleteCertificate(" + data.id + ")'>Delete</a>"
                            }}

                    ]
                });
            }

            function deleteCertificate(id)
            {

                $.ajax({
                    url: '${Contexpath}/user/deletecertificateedu?id=' + id,
                    dataType: 'text',
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function (data) {
                        loadCertificateData();

                    }
                });
            }

            function fileUploadPicture()
            {
                console.log("oMyForm");
                $('#resultPicture').html('');
                var oMyForm = new FormData();
                oMyForm.append("file", filePicture.files[0]);
                console.log(oMyForm);
                $.ajax({
                    url: '${Contexpath}/user/uploadpicture',
                    data: oMyForm,
                    dataType: 'text',
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function (data) {

                        console.log(data);
                        //  $('#resultPicture').html("File Upload SucessFully");
                        var base64_string = data;
                        console.log(base64_string);
                        var img = document.createElement("img");
                        // added `width` , `height` properties to `img` attributes
                        img.width = "250px";
                        img.height = "250px";
                        // $()
                        $("#ImageProfilepic_all").attr("src", base64_string);
                        //  $("#ImageProfilepic_all").src = "data:image/png;base64," + base64_string;
                        var preview = document.getElementById("dvImageProfilepic");
                        preview.appendChild(img);
                        //document.getElementById("ItemPreview").src = "data:image/png;base64," + YourByte;

                        //location.reload(true);
                    },
                    error: function () {
                        console.log("failure");
                        //location.reload();
                    }

                });
            }
            $(document).ready(function ()
            {
                loadCertificateData();
            });
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>