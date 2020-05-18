<%-- 
    Document   : userDetail
    Created on : Feb 13, 2019, 3:27:28 PM
    Author     : rasel
--%>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

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
                <sf:form method="post" id="frmReg" action="${Contexpath}/${action}" modelAttribute="model">


                    <div class="row">
                        <div class="col-lg-12 ">
                            <nav>
                                <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Basic Info</a>
                                    <a class="nav-item nav-link" id="nav-personalInfo-tab" data-toggle="tab" href="#nav-personalInfo" role="tab" aria-controls="nav-personalInfo" aria-selected="false">Personal Info</a>
                                    <a class="nav-item nav-link" id="nav-EducationInfo-tab" data-toggle="tab" href="#nav-EducationInfo" role="tab" aria-controls="nav-EducationInfo" aria-selected="false">Education Info</a>
                                    <a class="nav-item nav-link" id="nav-JobInfo-tab" data-toggle="tab" href="#nav-JobInfo" role="tab" aria-controls="nav-JobInfo" aria-selected="false">Job Info</a>
                                </div>
                            </nav>
                            <div class="box">
                                <div class="box-header">

                                </div>
                                <div class="box-body">
                                    <div class="tab-content py-3 px-3 px-sm-0" id="nav-tabContent">
                                        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">

                                            <div class="form-group row">
                                                <label class="col-lg-4">Name</label>
                                                <sf:input type="hidden" class="form-control col-lg-8" path="id"/>
                                                <sf:input type="text" class="form-control col-lg-8" placeholder="User Name" path="fullname"/>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-lg-4">Email</label>
                                                <sf:input type="email" readonly="true" class="form-control col-lg-8" placeholder="Email" path="email"/>
                                            </div>  


                                            <div class="form-group row">
                                                <label  class="col-lg-4">Profice Picture</label>
                                                <input type="file" id="filePicture" onchange="fileUploadPicture()" name="filePicture"/>

                                                <!--<button type="button" onclick="fileUploadPicture()" value="" class="btn btn-theme btn-line dark btn-block-xs" >Upload Picture</button>-->

                                                <div class="img-profile" id="dvImageProfilepic">
                                                    <img id="ImageProfilepic_all" style="width: 150px;height:120px" src="${Contexpath}/resources/profilePic/${model.email}.jpg" alt="">
                                                </div>
                                                <div class="col-md-4" id="resultPicture" style="text-align: center">

                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-pane fade" id="nav-personalInfo" role="tabpanel" aria-labelledby="nav-personalInfo-tab">

                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">Father Name</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="Father Name" path="employee.fatherName"/>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">Mother Name</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="Mother Name" path="employee.motherName"/>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">Mobile Number</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="Mobile Number" path="employee.mobileNumber"/>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">NID</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="NID" path="employee.nid"/>
                                                    </div>                                    
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">Passport Number</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="Passport Number" path="employee.passportNumber"/>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">Permanent Address</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="Permanent Address" path="employee.permanentAddress"/>
                                                    </div>                                    
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">Present Address</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="Present Address" path="employee.presentAddress"/>
                                                    </div>

                                                </div>

                                                <div class="col-lg-6">
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">Sex</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="Sex" path="employee.sex"/>
                                                    </div>                                    
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">DOB</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="DOB" path="employee.dob"/>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">place Of Birth</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="Place Of Birth" path="employee.placeOfBirth"/>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">Nationality</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="Nationality" path="employee.nationality"/>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">Religion</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="Religion" path="employee.religion"/>
                                                    </div>
                                                    <div class="form-group row">
                                                        <label  class="col-lg-4">Blood Group</label>
                                                        <sf:input type="text" class="form-control col-lg-8" placeholder="Blood Group" path="employee.bloodGroup"/>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="tab-pane fade" id="nav-EducationInfo" role="tabpanel" aria-labelledby="nav-EducationInfo-tab">
                                            <div class="form-group row">                                        
                                                <label class="col-lg-4">Degree</label>
                                                <sf:input  type="text" class="form-control col-lg-8"  placeholder="degree" path="employee.degree"/>
                                            </div>  

                                            <div class="form-group row">
                                                <label class="col-lg-4">Subject</label>
                                                <sf:input type="text" class="form-control col-lg-8" placeholder="subject" path="employee.subject"/>
                                            </div>   
                                            <div class="form-group row">
                                                <label  class="col-lg-4">Institution</label>
                                                <sf:input type="text" class="form-control col-lg-8" placeholder="institution" path="employee.institution"/>
                                            </div> 
                                            <div class="form-group row">
                                                <label  class="col-lg-4">Year</label>
                                                <sf:input type="text" class="form-control col-lg-8" placeholder="year" path="employee.year"/>
                                            </div> 
                                            <div class="form-group row">
                                                <label class="col-lg-4" >CGPA/Class</label>
                                                <input type="text" class="form-control col-lg-8" placeholder="CGPA" />
                                            </div> 
                                        </div>
                                        <div class="tab-pane fade" id="nav-JobInfo" role="tabpanel" aria-labelledby="nav-JobInfo-tab">

                                            <div class="form-group row">
                                                <label  class="col-lg-4">Join Date</label>
                                                <sf:input type="text" class="form-control col-lg-8" placeholder="year" path="employee.joinDate"/>
                                            </div> 
                                            <div class="form-group row">
                                                <label  class="col-lg-4">Post</label>
                                                <sf:input type="text" class="form-control col-lg-8" placeholder="year" path="employee.post"/>
                                            </div> 
                                            <div class="form-group row">
                                                <label  class="col-lg-4">Active</label>
                                                <sf:input type="text" class="form-control col-lg-8" placeholder="year" path="employee.active"/>
                                            </div> 
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>


                    <div class="form-group row">
                        <div class="col-lg-12 ">
                            <div class="box">
                                <div class="box-header">

                                </div>
                                <div class="box-body">
                                    <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30">Save</button>
                                </div>
                            </div>

                        </div>
                    </div>

                </sf:form>
            </div>
        </div>
        <script>
            function fileUploadPicture()
            {
                console.log("oMyForm");
                $('#resultPicture').html('');
                var oMyForm = new FormData();
                oMyForm.append("file", filePicture.files[0]);
                console.log(oMyForm);
                $.ajax({
                    url: '${Contexpath}/user/uploadPicture',
                    data: oMyForm,
                    dataType: 'text',
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function (data) {

                        console.log(data);
                        //  $('#resultPicture').html("File Upload SucessFully");
                        var base64_string = data;
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
                    }
                });
            }
        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>