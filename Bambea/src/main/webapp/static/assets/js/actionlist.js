var oriId;
var oriName;
var oriVibrate;
var oriUrl;
var oriOpenApp;
var oriNoti;

function edit(id) {
    oriId = id;
    var tdName = document.getElementById("txtName" + oriId);
    var tdVibrate = document.getElementById("txtVibrate" + oriId);
    var tdUrl = document.getElementById("txtUrl" + oriId);
    var tdOpenApp = document.getElementById("txtOpenApp" + oriId);
    var tdNoti = document.getElementById("txtNoti" + oriId);
    var btnEdit = document.getElementById("edit" + oriId);
    var btnDelete = document.getElementById("delete" + oriId);

    var vibrate;

    oriName = tdName.value;
    oriVibrate = tdVibrate.value;
    oriUrl = tdUrl.value;
    oriOpenApp = tdOpenApp.value;
    oriNoti = tdNoti.value;

    if (oriVibrate === "true") {
        vibrate = true;
    } else {
        vibrate = false;
    }

    tdName.readOnly = false;
    tdName.className = "editField";
    tdVibrate.readOnly = false;
    tdVibrate.type = "checkbox";
    tdVibrate.checked = vibrate;
    tdVibrate.className = "";
    tdVibrate.style.width = "15px";
    tdUrl.readOnly = false;
    tdUrl.className = "editField";
    tdOpenApp.readOnly = false;
    tdOpenApp.className = "editField";
    tdNoti.readOnly = false;
    tdNoti.className = "editField";

    btnEdit.setAttribute('onclick', "javascript: save();");
    btnEdit.innerHTML = '<i class="fa fa-pencil"></i>Save';

    btnDelete.setAttribute('onclick', "javascript: endEdit();");
    btnDelete.removeAttribute('href');
    btnDelete.innerHTML = '<i class="fa fa-times"></i>Cancel';
}

function save() {
    var tdName = document.getElementById("txtName" + oriId);
    var tdVibrate = document.getElementById("txtVibrate" + oriId);
    var tdUrl = document.getElementById("txtUrl" + oriId);
    var tdOpenApp = document.getElementById("txtOpenApp" + oriId);
    var tdNoti = document.getElementById("txtNoti" + oriId);

    oriName = tdName.value;
    if (tdVibrate.checked) {
        oriVibrate = "true";
    } else {
        oriVibrate = "false";
    }
    oriUrl = tdUrl.value;
    oriOpenApp = tdOpenApp.value;
    oriNoti = tdNoti.value;

    $.ajax({
        url: "/Bambea/action/edit",
        data: {"id": oriId, "name": oriName, "vibrate": oriVibrate, "url": oriUrl,
            "openApp": oriOpenApp, "notification": oriNoti},
        type: "POST",
        success: function (XMLHttpRequest) {
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {

        }
    });
    endEdit();
}

function endEdit() {
    var tdName = document.getElementById("txtName" + oriId);
    var tdVibrate = document.getElementById("txtVibrate" + oriId);
    var tdUrl = document.getElementById("txtUrl" + oriId);
    var tdOpenApp = document.getElementById("txtOpenApp" + oriId);
    var tdNoti = document.getElementById("txtNoti" + oriId);
    var btnEdit = document.getElementById("edit" + oriId);
    var btnDelete = document.getElementById("delete" + oriId);

    tdName.readOnly = true;
    tdName.className = "asLabel";
    tdName.value = oriName;
    tdVibrate.readOnly = true;
    tdVibrate.type = "text";
    tdVibrate.className = "asLabel";
    tdVibrate.style.width = "50px";
    tdVibrate.value = oriVibrate;
    tdUrl.readOnly = true;
    tdUrl.className = "asLabel";
    tdUrl.value = oriUrl;
    tdOpenApp.readOnly = true;
    tdOpenApp.className = "asLabel";
    tdOpenApp.value = oriOpenApp;
    tdNoti.readOnly = true;
    tdNoti.className = "asLabel";
    tdNoti.value = oriNoti;

    btnEdit.setAttribute('onclick', "javascript: edit(" + oriId + ");");
    btnEdit.innerHTML = '<i class="fa fa-pencil"></i>Edit';

    btnDelete.setAttribute('onclick', "javascript: deleteAction(" + oriId + ");");
    btnDelete.innerHTML = '<i class="fa fa-times"></i>Delete';
}

function deleteAction(id) {
    if (confirmDelete(id)) {
        $.ajax({
            url: "/Bambea/action/remove",
            data: {"id": id},
            type: "GET",
            success: function (XMLHttpRequest) {
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {

            }
        });
        document.getElementById("txtName" + id).closest("tr").remove();
    }
}

function confirmDelete(id) {
    return confirm("Are you sure you want to delete trigger #" + id + "?");
}
