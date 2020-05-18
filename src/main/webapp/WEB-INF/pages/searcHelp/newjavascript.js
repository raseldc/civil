function loadItemList(type)
{
    console.log(type);
    var loadData = itemDetails;
    if (type === 1)
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
    if (type === 2)
    {
        var selectedParentId = $("#ddMainItemGroup").val();
        if ($("#ddItemGroup :selected").val() === "1")
        {
            console.log("select main civil Item");
            //  loadData = mainCivilItemDetails;
            htm_option = " <option value='0'>Select Item Group</option>";
            civilItemDetails.forEach(function (e, i) {
                if (e.parent_id === selectedParentId)
                {
                    loadData.push(e);
                    htm_option = htm_option + "<option value='" + e.id + "' > " + e.code + "</option>";
                }
                //  htm_option = htm_option + "<option value='" + e.id + "' > " + e.code + "</option>";

            });
            $("#ddSubItemGroup").append(htm_option);
        } else if ($("#ddItemGroup :selected").val() === "2")
        {
            console.log("select main civil Item");
            // loadData = mainCivilItemDetails;
            htm_option = " <option value='0'>Select Item Group</option>";
            elecItemDetails.forEach(function (e, i) {
                if (e.parent_id === selectedParentId)
                {
                    loadData.push(e);
                    htm_option = htm_option + "<option value='" + e.id + "' > " + e.code + "</option>";
                }
                //  htm_option = htm_option + "<option value='" + e.id + "' > " + e.code + "</option>";

            });
            $("#ddSubItemGroup").append(htm_option);
        }

    }
    console.log(loadData.length);
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
                    var alldata = JSON.stringify(data);
//                                return "<a href='#' id='btnSelect' value='Edit'  onclick='editRole(\"" + data.id + "\",\"" + data.name + "\")'>Select</a>"
                    return "<a href='#' id='btnSelect' value='Edit'  onclick='editItem(" + alldata + ")'>Select</a>";
                }}

        ]
    });
}
function editItem(info)
{

    if (hasChildItem(info.id))
    {
        $("#ddMainItemGroup").val(info.id)
        loadItemList(2);
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