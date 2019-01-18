<%-- 
    Document   : deviceDetails
    Created on : 30 Oct, 2018, 11:22:29 AM
    Author     : ZMF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="http://momentjs.com/downloads/moment.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBXkgqkWCA8ZEg8M5ouvz1YFq_MFtwPtHc">
</script>

<style type="text/css">
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
    }

    #map{
        height: 300px;
        width: 100%;  
    }
</style>

<style>
    .CellWithComment{
        position:relative;

    }

    .CellComment{
        display:none;
        position:absolute; 
        z-index:100;
        border:1px;
        background-color: #FAFCC7;
        border-style:solid;
        border-width:1px;
        border-color: #000000;
        padding:3px;
        color: #000000; 
        top:25px; 
        left:20px;
    }

    .CellWithComment:hover span.CellComment{
        display:block;
        width: max-content
    }
</style>


<style type="text/css">
    .selectRow {
        display : block;
        padding : 0px;
    }
    .select2-container {
        width: 200px;
    }
</style>
<style type = "text/css">

    .fleetList {
        float:left;padding:10px;margin:10px; -moz-user-select:none;
    }
    .fleetList { background-color: #66afe9; width:100%; height:75px;  }
    /*#boxB { background-color: #FF6699; width:150px; height:150px; }*/
</style>

<jsp:include page="header.jsp" />

<div class="content-wrapper">
    <section class="content">
        <div class="main-page">
            <div class="col-xs-12">
                <div class="fleetListDiv col-md-12" id="fleetList"  ondrop="drop(event)" ondragover="allowDrop(event)">
                    DROP HERE!!
                </div>
            </div>
            <!--<div class="tables col-md-12">-->
            <!--<h2 class="title1">Device details:</h2>-->
            <!--<button><div class=""><a href="#my_modal" data-toggle="modal" class="btn btn-primary btn-block"><b>ADD</b></a></div></button>-->
            <!--<div class="panel-body widget-shadow">-->
            <!--<div class="row" style="overflow: auto;">-->

            <div class="col-xs-12"  >                            
                <!-- /.box -->

                <!--                <div class="box" style="display:none;width:1093px" id="datatable">-->

                <div class="box" id="datatable">


                    <div class="box-header" >
                        <h3 class="box-title">Device</h3>
                    </div>
                    <!--<img src="<c:url value='iconImages/com.zero.aepsmerchantonboarding1.0.3.png' />" class="user-image">-->
                    <!-- /.box-header -->
                    <div class="box-body" style="overflow: auto" >
                        <table id="example" class="table table-hover table-striped" style="width: 100%" >
                            <thead>
                                <tr>
                                    <th class="CellWithComment"><a href="#deviceInfo_modal" data-toggle="modal"><span style="font-weight: normal" class="CellComment">After selecting the device, drag this icon to the fleet you want to put the device into.</span><img class="addToFleet" id="null" draggable="true" ondragstart="drag(event)" style="width:30px; height:30px" src="images/mobile.png"/></a></th>
                                    <th>IMEI</th>
                                    <th>OWNER NAME</th>
                                    <th>LINK TO FLEET</th>                                            
                                    <th>LAST SYNC</th>
                                    <th>STATE</th>
                                    <th>DISTRICT</th>
                                    <th>DELETE</th>
                                </tr>
                            </thead> 

                        </table>
                    </div>
                    <!-- /.box-body -->
                </div>


                <!-- /.box -->
                <!--</div>-->
                <!-- /.col -->
            </div>
            <!--</div>-->
            <!--</div>-->
        </div>

        <!--modal code for device info-->
        <div class="modal" id="deviceInfo_modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">Device info</h4>
                    </div>
                    <div class="modal-body">

                        <div class="box-body">
                            <div class="row">

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">Owner Name:<red style="color: red"></red></label>
                                        <!--<input type="text" path="name" class="form-control" maxlength="40" name="name" id="updateOwnerName" placeholder=""/>-->
                                        <weak id="updateOwnerName"></weak>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">IMEI: <red style="color: red"></red></label>
                                        <!--<input type="text" path="imei" class="form-control" maxlength="40" name="name" id="updateIMEI" disabled="true" placeholder=""/>-->
                                        <weak id="updateIMEI"></weak>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">Fleet name: <red style="color: red"></red></label>
                                        <!--<input type="text" path="fleetName" class="form-control" maxlength="40" name="name" id="updateFleetName" placeholder=""/>-->
                                        <weak id="updateFleetName"></weak>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">Device Model: <red style="color: red"></red></label>
                                        <!--<input type="text" class="form-control" maxlength="40" name="name" id="updateDeviceModel" placeholder=""/>-->
                                        <weak id="updateDeviceModel"></weak>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">OS Version: <red style="color: red"></red></label>
                                        <!--<input type="text" class="form-control" maxlength="40" name="name" id="updateOsVersion" placeholder=""/>-->
                                        <weak id="updateOsVersion"></weak>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">OS Api Level: <red style="color: red"></red></label>
                                        <!--<input type="text" class="form-control" maxlength="40" name="name" id="updateOsApiLevel" placeholder=""/>-->
                                        <weak id="updateOsApiLevel"></weak>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">Sync Status: <red style="color: red"></red></label>
                                        <!--<input type="text" class="form-control" maxlength="40" name="name" id="updateSyncStatus" placeholder=""/>-->
                                        <weak id="updateSyncStatus"></weak>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">Last Sync: <red style="color: red"></red></label>
                                        <!--<input type="text" class="form-control" maxlength="40" name="name" id="updateLastSync" placeholder=""/>-->
                                        <weak id="updateLastSync"></weak>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">Device Location: <red style="color: red"></red></label>
                                        <a class="openmodal" href="#contact" onclick="initMap()"  data-toggle="modal" data-id="Peggy Guggenheim Collection - Venice">View</a>
                                        <input type="text"  hidden="true"  name="name" id="updateLongitude" placeholder=""/>
                                        <input type="text"  hidden="true"  name="name" id="updateLatitude" placeholder=""/>

                                    </div>
                                </div>
                                <div class="col-md-5" id="deviceInfoLoader" style="display: none">
                                    <img  src="images/load.gif" style="display: block;position: absolute;margin-left: -71px;margin-right: auto;width: 50% " alt="Please wait...."  /> 
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <center> <strong> APPLICATIONS</strong></center>
                                    </div>

                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">Installed App: <red style="color: red"></red></label>
                                        <!--<input type="text" class="form-control" maxlength="40" name="name" id="updateInstalledApp" placeholder=""/>-->
                                        <weak id="updateInstalledApp"></weak>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="name">Not installed App: <red style="color: red"></red></label>
                                        <!--<input type="text" class="form-control" maxlength="40" name="name" id="updateUnInstalledApp" placeholder=""/>-->
                                        <weak id="updateUnInstalledApp"></weak>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>

        <!--modal for view map-->
        <div class="modal fade" id="contact" role="dialog" >
            <div class="modal-dialog modal-lg">
                <div class="modal-content" id="back" >  
                    <div class="modal-header">
                        <h4>Location</h4>
                    </div>
                    <div class="modal-body">    
                        <div id="map"></div>
                    </div>
                    <div class="modal-footer">
                        <a class="btn btn-default" data-dismiss="modal">Close</a>
                    </div>      
                </div>
            </div>

            <!--modal code for updating profile info-->
            <div class="modal" id="locInfo_modal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title">Location</h4>
                        </div>
                        <div class="modal-body">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<%@include file="../jsp/footer.jsp" %>
<script>
    function initMap() {
        var lati = parseFloat(document.getElementById("updateLatitude").value);
        var longi = parseFloat(document.getElementById("updateLongitude").value);
        var myLatLng = {lat: lati, lng: longi};

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 7,
            center: myLatLng
        });

        var marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            title: 'Hello World!'
        });
    }

//                                $('#contact').on('shown.bs.modal', function () {
//                                    google.maps.event.trigger(map, "resize");
//                                });
</script>
<script type = "text/javascript">

    function allowDrop(ev) {
        ev.preventDefault();
    }

    function drag(ev) {
        ev.dataTransfer.setData("text", ev.target.id);
    }

    function drop(ev) {
        ev.preventDefault();
        var data = ev.dataTransfer.getData("text");
//               alert(data);
        var fleetId = ev.target.id;
        if (data == "null") {
            alert("You have not selected any device");
            return false;
        }
        intCheck = data.split(",");
        for (i in intCheck) {
//            alert(isNaN(intCheck[i]));
            if (isNaN(intCheck[i])) {
                alert("You are not dropping the mobile icon, Please select the devices you want to transfer to the fleet and then drag the mobile icon to that fleet space.");
                return false;
            }
        }
        var response = confirm("You are going to transfer the selected device to this fleet");
        if (response == true) {
//                  alert("id: " + data); 
            $.ajax({
                url: "updateDeviceFleet",
                type: "POST",
                data: {
                    "id": data,
                    "fleetId": fleetId
                },
                success: function (data) {
                    alert(data);
                    window.location.reload();
                }
            });
        } else {
            return false;
        }

//                ev.target.appendChild(document.getElementById(data));
    }
</script>

<script>
    window.onload = listOfDevices;
    function listOfDevices() {

        $.ajax({
            url: "getFleetList",
            type: "POST",
            success: function (data) {
//                        alert(data);
                var variable = JSON.parse(data);
                var divWidth = (100 / 4) - 3;
                var result = "";
                var counter = 0;
                for (i in variable) {
                    counter = counter + 1;
                    var info = variable[i];
//                            alert(info.id);
                    result = result + '<div class="fleetList" style="display: inline-block; width: ' + divWidth + '%" id="' + info.id + '"   ondrop="drop(event)" ondragover="allowDrop(event)">' + info.name + '</div>';
                    if (counter >= 4) {
                        result = result + '<div class="row"></div>';
                        counter = 0;
                    }
                }
                $("div.fleetListDiv").replaceWith(result);
            }
        });

        $.fn.dataTable.ext.errMode = 'none';
        var table = $('#example').DataTable({
            paging: false,
            retrieve: false,
            bInfo: false,
            searching: false,
            "ajax": {
                "url": "getDeviceDetailList",
                "type": "post",
                "dataSrc": ""},
            columnDefs: [
                {
                    targets: [1, 2, 3, 4, 5, 6, 7],
                    className: 'text-center'
                },
                {
                    "targets": [],
                    "visible": false
                }
            ],
            "columns": [
                {"className": 'dev-info',
                    "data": function (data, type, row, meta) {
                        return '<input type="checkbox" class="editor-active">&nbsp<a href="#deviceInfo_modal" data-toggle="modal"><img style="width:30px; height:30px" src="images/info.png"/></a>';
                    }
                },
                {"data": "imei"},
                {"data": "ownerName"},
                {"data": "status",
                    sDefaultContent: ""}, //instead of projectfleetid, this will display name**
                {"data": function (data) {
                        var d = moment(data.lastSync).format('DD-MM-YY hh:mm A');
                        return d;
                    }
                },
                {"data": "state"},
                {"data": "district"},
                {"className": 'dev-del',
                    "data": function (data, type, row, meta) {
                        return '<a class="btn btn-info btn-sm" href="#">DELETE</a>';
                    }
                    //                    "defaultContent": "<a href=\"#\" ><b>View</b> </a>"
                }
            ]

                    //            $('#example').empty();
        });
        //on click of a check box in data table
        $('#example tbody').on('click', 'input[type="checkbox"]', function (e) {
            var $row = $(this).closest('tr');
            var data = table.row($row).data();
            var rowId = data["id"];
            if (this.checked) {
                var id = document.getElementsByClassName("addToFleet")[0].id;
                if (id == "null") {
                    document.getElementsByClassName("addToFleet")[0].id = rowId;
//                          alert("edited id: "+document.getElementsByClassName("addToFleet")[0].id);
                } else {
                    document.getElementsByClassName("addToFleet")[0].id = document.getElementsByClassName("addToFleet")[0].id + "," + rowId;
//                          alert("row id: " + document.getElementsByClassName("addToFleet")[0].id); 
                }
            } else if (!this.checked) {
                var id = document.getElementsByClassName("addToFleet")[0].id;
                document.getElementsByClassName("addToFleet")[0].id = id.replace(rowId, "");
                document.getElementsByClassName("addToFleet")[0].id = document.getElementsByClassName("addToFleet")[0].id.replace(",,", ",");
//                      alert(document.getElementsByClassName("addToFleet")[0].id);
            }

        });
        $('#example tbody').on('click', 'td.dev-del', function () {
            var $tr = $(this).closest('tr');
            rowData = $('#example').DataTable().row($tr).data();
            var id = rowData["id"];
            var conf = confirm("You are going to delete the device from the database.")
            if (conf == false) {
                return false;
            }
            $.ajax({
                url: "deleteDevice",
                type: "POST",
                data: {
                    id: "" + id + ""
                },
                success: function (data) {
                    alert(data);
                    window.location.reload();
                }
            });
        });
        $('#example tbody').on('click', 'td.dev-info', function () {
            $('#deviceInfoLoader').show();
            document.getElementById("updateUnInstalledApp").innerHTML = "";
            document.getElementById("updateInstalledApp").innerHTML = "";
            var $tr = $(this).closest('tr');
            rowData = $('#example').DataTable().row($tr).data();
            var id = rowData["id"];
            //                alert(id);
            $.ajax({
                url: "getDeviceInfo",
                type: "POST",
                data: {
                    id: "" + id + ""
                },
                success: function (data) {
//                            alert(data);
                    var data = JSON.parse(data);
                    document.getElementById("updateOwnerName").innerHTML = data.ownerName;
                    document.getElementById("updateIMEI").innerHTML = data.imei;
                    document.getElementById("updateFleetName").innerHTML = data.fleetName;
                    document.getElementById("updateDeviceModel").innerHTML = data.deviceModel;
                    document.getElementById("updateOsVersion").innerHTML = data.osVersion;
                    document.getElementById("updateOsApiLevel").innerHTML = data.osApiLevel;
                    var instApp = data.installedApp;
//                            alert(instApp);
                    if (instApp != null) {
                        var processedInstalledApp = instApp.split(",");
//                            alert("processed: " + processedInstalledApp);
                        var result = "";
                        for (index = 0; index < processedInstalledApp.length; index++) {
                            result = result + "<li>" + processedInstalledApp[index] + "</li>";
                        }
                        document.getElementById("updateInstalledApp").innerHTML = result;
                    }

                    var uninstApp = data.unInstalledApp;
                    if (uninstApp != null) {

                        var processedUninstalledApp = uninstApp.split(",");
//                            alert("processed: " + processedUninstalledApp);
                        var result = "";
                        for (index = 0; index < processedUninstalledApp.length; index++) {
                            result = result + "<li>" + processedUninstalledApp[index] + "</li>";
                        }
//                            alert("result: " + result);
                        document.getElementById("updateUnInstalledApp").innerHTML = result;
                    }
                    document.getElementById("updateSyncStatus").innerHTML = data.syncStatus;
                    document.getElementById("updateLastSync").innerHTML = data.lastSync;
                    document.getElementById("updateLongitude").value = data.longitude;
                    document.getElementById("updateLatitude").value = data.latitude;
//                                                document.getElementById("updateDeviceToken").value = data.deviceToken;
                    //                        window.location.reload();
                    $('#deviceInfoLoader').hide();
                }
            });
        });
    }


</script>
<script type="text/javascript">
    $(document).ready(
            function () {
                $("#accessToApp").select2();
                $("#UpdateaccessToApp").select2();
            }
    );
    $(document).ready(function () {

        var result;
        $.ajax({
            type: "POST",
            url: 'getApkNamesList',
            success: function (response) {

                var result = null;
                variable = JSON.parse(response);
                for (i in variable)
                {
                    result += "<option value=" + i + ">" + variable[i] + "</option>";
                }
                $('#accessToApp').html(result);
                $('#UpdateaccessToApp').html(result);
            },
            error: function (response) {
            }
        });
    });
    function validate() {

        if (document.getElementById("profileName").value.trim() === "") {
            alert("Profile name cannot be blank");
            return false;
        } else {
            var profileName = document.getElementById("profileName").value;
//            alert(profileName);
            var syncInterval = document.getElementById("syncInterval").value;
            var description = document.getElementById("description").value;
            var selectedValues = [];
            $("#accessToApp :selected").each(function () {
                selectedValues.push($(this).val());
            });
//            alert(selectedValues);
            $.ajax({
                url: "addNewProfile",
                type: "POST",
                data: {
                    profileName: profileName,
                    syncInterval: syncInterval,
                    description: description,
                    selectedValues: selectedValues
                },
                success: function (data) {
                    alert(data);
                    window.location.reload();
                }
            });
        }
    }

    function Update() {

        if (document.getElementById("UpdateprofileName").value === "") {
            alert("profile name cannot be blank");
            return false;
        } else {
            var profileName = document.getElementById("UpdateprofileName").value;
//            alert(profileName);
            var syncInterval = document.getElementById("UpdatesyncInterval").value;
            var description = document.getElementById("Updatedescription").value;
            var profileId = document.getElementById("profileId").value;
            var selectedValues = [];
            $("#UpdateaccessToApp :selected").each(function () {
                selectedValues.push($(this).val());
            });
//            alert(selectedValues);
            $.ajax({
                url: "updateProfile",
                type: "POST",
                data: {
                    profileName: profileName,
                    syncInterval: syncInterval,
                    description: description,
                    selectedValues: selectedValues,
                    profileId: profileId
                },
                success: function (data) {
                    alert(data);
                    window.location.reload();
                }
            });
        }
    }

</script>

