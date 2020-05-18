<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modaItem">Item Search</button>
<input type="checkbox" id="cb" onchange="select()" />
<div class="modal fade bd-example-modal-lg" id="modaItem" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="box">
                            <div class="box-header bg-color">
                                <h3 class="box-title">Item Search</h3>
                            </div>
                            <div class="box-body box-block">
                                <div class="col-lg-12">
                                    <lable id="lb-msg-item-select"/>

                                </div>
                                <div class="col-lg-3">
                                    <label for="inputEmail">Item Group</label>
                                    <select class="form-control" id="ddItemGroup" onchange="loadItemList(0)">
                                        <option value="0">Select Item Group</option>
                                        <option value="1">Civil Item</option>
                                        <option value="2">Electrical Item </option>
                                    </select>
                                </div>

                                <div class="col-lg-3">
                                    <label for="inputEmail">Main Item</label>
                                    <select class="form-control" id="ddMainItemGroup" onchange="loadItemList(1)">
                                        <option value="0">Select Item Group</option>
                                        <option value="1">Civil Item</option>
                                        <option value="2">Electrical Item </option>
                                    </select>
                                </div>
                                <div class="col-lg-3">
                                    <label for="inputEmail">Sub Item</label>
                                    <select class="form-control" id="ddSubItemGroup" onchange="loadItemList(2)">
                                        <option value="0">Select Item Group</option>
                                        <option value="1">Civil Item</option>
                                        <option value="2">Electrical Item </option>
                                    </select>
                                </div>
                                <div class="col-lg-3">
                                    <label for="inputEmail">Item Select</label>
                                    <input type="button" value="Slect Item" onclick="verifySelectedItemAndSelect()"/>
                                </div>
                                <!--                                <div class="col-lg-3">
                                                                    <label for="inputEmail">Sub Sub Item</label>
                                                                    <select class="form-control" id="ddSubSubItemGroup">
                                                                        <option value="0">Select Item Group</option>
                                                                        <option value="1">Civil Item</option>
                                                                        <option value="2">Electrical Item </option>
                                                                    </select>
                                                                </div>-->
                            </div>
                        </div>
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
                                            <th>Has Child </th>
                                            <th>Select Box</th>
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
                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="btn_close_item_modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="selecSelctedItems()">Get Selected Items</button>
            </div>
        </div>
    </div>
</div>





<script type="text/javascript">

    itemType = 0;
    var seletedItemId = [];
    function selecSelctedItems()
    {
        $("#tbItemId").val(seletedItemId.toString());
        $("#btn_close_item_modal").click();
        projectDetailsSave();


    }
    function verifySelectedItemAndSelect()
    {
        if (seletedItemId.length == 0)
            $("#lb-msg-item-select").html("Please Slect Item");
    }
    function loadItemList(type)
    {
        console.log(type);
        var loadData = itemDetails;
        if (type === 0)
        {
            $("#ddMainItemGroup").html("");
            if ($("#ddItemGroup :selected").val() === "1")
            {
                console.log("select main civil Item");
                loadData = mainCivilItemDetails;
                htm_option = " <option value='0'>Select Item Group</option>";
                mainCivilItemDetails.forEach(function (e, i) {
                    htm_option = htm_option + "<option value='" + e.id + "' > " + e.code + "</option>";
                });
                $("#ddMainItemGroup").append(htm_option);
            } else if ($("#ddItemGroup :selected").val() === "2")
            {
                console.log("select main elc Item");
                loadData = mainElecItemDetails;
                htm_option = " <option value='0'>Select Item Group</option>";
                mainElecItemDetails.forEach(function (e, i) {
                    htm_option = htm_option + "<option value='" + e.id + "' > " + e.code + "</option>";
                });
                $("#ddMainItemGroup").append(htm_option);
            }

        }
        if (type === 1)
        {
            loadData = [];
            var selectedParentId = $("#ddMainItemGroup").val();

            var childsItem = itemObject[selectedParentId].child;
            console.log(childsItem);
            $("#ddSubItemGroup").html("");
            htm_option = " <option value='0'>Select Item Group</option>";
            childsItem.forEach(function (e, i)
            {
                var itemId = e;
                var item = itemObject[itemId];

                loadData.push(itemObject[itemId]);
                itemObject[itemId]["level"] = 2;
                htm_option = htm_option + "<option value='" + item.id + "' > " + item.code + "</option>";
            });
            $("#ddSubItemGroup").append(htm_option);


//            if ($("#ddItemGroup :selected").val() === "1")
//            {
//                console.log("select main civil Item");
//                //  loadData = mainCivilItemDetails;
//                htm_option = " <option value='0'>Select Item Group</option>";
//                civilItemDetails.forEach(function (e, i) {
//                    if (e.parent_id == selectedParentId)
//                    {
//                        loadData.push(e);
//                        htm_option = htm_option + "<option value='" + e.id + "' > " + e.code + "</option>";
//                    }
//                    //  htm_option = htm_option + "<option value='" + e.id + "' > " + e.code + "</option>";
//
//                });
//                $("#ddSubItemGroup").append(htm_option);
//            } else if ($("#ddItemGroup :selected").val() === "2")
//            {
//                console.log("select main civil Item");
//                // loadData = mainCivilItemDetails;
//                htm_option = " <option value='0'>Select Item Group</option>";
//                elecItemDetails.forEach(function (e, i) {
//                    if (e.parent_id == selectedParentId)
//                    {
//                        loadData.push(e);
//                        htm_option = htm_option + "<option value='" + e.id + "' > " + e.code + "</option>";
//                    }
//                    //  htm_option = htm_option + "<option value='" + e.id + "' > " + e.code + "</option>";
//
//                });
//                $("#ddSubItemGroup").append(htm_option);
//            }

        }

        if (type === 2)
        {
            loadData = [];
            var selectedParentId = $("#ddSubItemGroup").val();

            var childsItem = itemObject[selectedParentId].child;
            console.log(childsItem);

            childsItem.forEach(function (e, i)
            {
                var itemId = e;
                var item = itemObject[itemId];

                loadData.push(itemObject[itemId]);
                itemObject[itemId]["level"] = 3;

            });



        }
        console.log(loadData.length);
        console.log(loadData);
        var searchInfo = {};
        $('#itemListTB').DataTable().clear();
        $('#itemListTB').DataTable().destroy();
        $('#itemListTB').DataTable({
            "processing": true,
            "destroy": true,
            "paging": true,
            "pageLength": 10,
            // "serverSide": true,
            "filter": true,
            //"ajax": "${Contexpath}/item/getitemlist",
            data: loadData,
            "columns": [
                {"data": "code"},
                {"data": "description"},
                {"data": "unit"},
                {"data": "price"},
                {"data": function (data) {
                        if (data.child.length == 0)
                            return "No";
                        else if (data.child.length > 0)
                        {
                            return "Yes"
                        }

                    }},
                {"data": function (data) {

                        if (data.child.length == 0)
                            return "<input type='checkbox' id='cb-" + data.id + "' onchange='manageSeletedItemID(" + data.id + ")' />";
                        else if (data.child.length > 0)
                        {
                            return "You can not select this item"
                        }

                    }},
                {"data": function (data) {
                        var alldata = JSON.stringify(data);
//                                return "<a href='#' id='btnSelect' value='Edit'  onclick='editRole(\"" + data.id + "\",\"" + data.name + "\")'>Select</a>"
                        return "<a href='#' id='btnSelect' value='Select'  onclick='editItem(" + alldata + ")'>Select</a>";
                    }}

            ]
        });
    }


    function manageSeletedItemID(itemId)
    {
        console.log(itemId);
        console.log($("#cb-" + itemId).is(":checked"));
        if ($("#cb-" + itemId).is(":checked") === true)
        {
            seletedItemId.push(itemId);
        } else
        {
            seletedItemId.pop(itemId);
        }
        console.log(seletedItemId);

    }

    function editItem(info)
    {
        if (info.child.length > 0)
        {
            if (info.level == 1)
                $("#ddMainItemGroup").val(info.id)
            if (info.level == 2)
                $("#ddSubItemGroup").val(info.id)
            loadItemList(info.level);
        }

        if (info.child.length > 0)
        {
            // $("#ddMainItemGroup").val(info.id)
            //  loadItemList(2);
        } else {
            $("#tbItemName").val(info.code);
            $("#taDes").val(info.description);
            $("#tbUnit").val(info.unit);
            $("#tbPrice").val(info.price);
            $("#tbItemId").val(info.id);
        }

    }
    function hasChildItem(id)
    {
        console.log("has child call for " + id);
        flag = 0;
        if ($("#ddItemGroup :selected").val() === "1")
        {
            civilItemDetails.forEach(function (e, i) {
                if (e.parent_id === id)
                {
                    flag = 1;
                    return true;
                }

            });
        } else if ($("#ddItemGroup :selected").val() === "2")
        {
            mainElecItemDetails.forEach(function (e, i) {
                if (e.parent_id === id)
                {
                    flag = 1;
                    return true;
                }

            });
        }

        if (flag == 1) {
            return true;
        }
        return false;
    }

    $(document).ready(function ()
    {
        console.log("123");
        loadItemList();
    });
</script>