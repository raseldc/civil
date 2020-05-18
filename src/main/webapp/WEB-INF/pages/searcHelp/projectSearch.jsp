<%-- 
    Document   : projectSearch
    Created on : Feb 1, 2020, 6:52:14 PM
    Author     : rasel
--%>
<div class="row">
    <div class="col-lg-12">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#projectModal">
            Project search
        </button>
    </div>
</div>

<!-- Modal -->
<div class="modal fade bd-example-modal-xl" id="projectModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="btnClose_projectSearch">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

                <div class="row">
                    <input type="hidden" id="projectId" value="0"/>
                    <div class="col-md-12">
                        <div class="box">
                            <div class="box-header bg-color">
                                <h3 class="box-title">Project List</h3>
                            </div>
                            <div class="box-body box-block">
                                <table id="projectListTB" class="table table-striped table-bordered" style="width: 100%">
                                    <thead>
                                        <tr>
                                            <th>Name</th>                                            
                                            <th>Start Date</th>
                                            <th>End Date</th>
                                            <th>Ministry </th>
                                            <th>Edit</th>
                                        </tr>
                                    </thead>

                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" >Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>

</div>



<script type="text/javascript">
    function loadProjectList()
    {
        $('#projectListTB').DataTable({
            "processing": true,
            "destroy": true,
            "paging": true,
            "pageLength": 10,
            "serverSide": false,
            "filter": true,
            "ajax": "${Contexpath}/project/getallproject",
            "columns": [
                {"data": "projectName"},
                {"data": "startDate"},
                {"data": "endDate"},
                {"data": "ministry"},
                {"data": function (data) {
                        var alldata = JSON.stringify(data);
//                                return "<a href='#' id='btnSelect' value='Edit'  onclick='editRole(\"" + data.id + "\",\"" + data.name + "\")'>Select</a>"
                        return "<a href='#' id='btnSelect' value='Edit'  onclick='editProject(" + alldata + ")'>Select</a>";
                    }}

            ]
        });
    }
    function editProject(info)
    {
        $("#tbProjectName").val(info.projectName);
        $("#tbProjectId").val(info.id);

        $("#btnClose_projectSearch").click();
        loadProjectInfo(info.id);

    }
    $(document).ready(function ()
    {
        console.log("123");
        loadProjectList();



    });
</script>