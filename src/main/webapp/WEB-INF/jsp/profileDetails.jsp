<%-- 
    Document   : ContentSamplePage
    Created on : 9 Oct, 2018, 3:19:47 PM
    Author     : ZMF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script type="text/javascript" src="http://momentjs.com/downloads/moment.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>


<style type="text/css">
    .selectRow {
        display : block;
        padding : 0px;
    }
    .select2-container {
        width: 200px;
    }
</style>


<jsp:include page="header.jsp" />

<div class="content-wrapper">
    <section class="content">
        <div id="sub" style="display:none">
            <img id="loading-image" src="images/load.gif" alt="Loading..." />
        </div>
        <div class="main-page">


            <!--<div class="tables">-->
            <!--                <h2 class="title1">Profile details:</h2>
                            <button><div class=""></div></button>-->
            <!--<div class="panel-body widget-shadow">-->
            <!--<div class="row" style="overflow: auto;">-->

            <!--<div class="col-xs-12"  >-->                            
            <!-- /.box -->

            <!--                <div class="box" style="display:none;width:1093px" id="datatable">-->
            
            <div class="col-md-12">
                .
            </div>

            <div class="box" id="datatable">


                <div class="box-header" >
                    <h3 class="box-title">Profile</h3>
                    <a href="#my_modal" style="float: right" data-toggle="modal" class="btn btn-primary"><b>ADD</b></a>
                </div>
                <!--<img src="<c:url value='iconImages/com.zero.aepsmerchantonboarding1.0.3.png' />" class="user-image">-->
                <!-- /.box-header -->
                <div class="box-body" style="overflow: auto"  >
                    <table id="example" class="table table-hover table-striped" style="width: 100%">
                        <thead>
                            <tr>
                                <th></th>
                                <th>NAME</th>
                                <th>DESCRIPTION</th>
                                <th>CREATED ON</th>
                                <th>UPDATED ON</th>
                                <th>UPDATED BY</th>
                                <th>SYNC INTERVAL(MIN)</th>
                                <th>STATUS</th>
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
            <!--</div>-->
            <!--</div>-->
            <!--</div>-->
        </div>
        <!--modal code for uploading-->
        <div class="modal" id="my_modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">Add Profile</h4>
                    </div>
                    <div class="modal-body">

                        <div id="">

                            <div class="row panel-body widget-shadow">
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <h3 class="box-title"></h3>
                                        </div>
                                        <div class="col-md-6">
                                            <h4 style="color: slateblue"> ${successMessage}</h4>
                                            <h4 style="color: red"> ${errorMessage}</h4>
                                            <h4 style="color: red"> ${internalErrorMessage}</h4>
                                        </div>
                                    </div>
                                    <form  method="post" action="addNewProfile">

                                        <div class="box-body">
                                            <div class="row">

                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="name">Profile Name<red style="color: red">*</red></label>
                                                        <input type="text" path="name" class="form-control" maxlength="40" name="name" id="profileName" placeholder="Enter profile Name"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="syncInterval">Sync Interval<red style="color: red">*</red></label>
                                                        <input type="number" min="0" max="15" path="syncInterval" class="form-control" maxlength="10" name="syncInterval" id="syncInterval" placeholder="Sync interval in minutes" onkeypress="return isNumberKey(event)"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="description">Description<red style="color: red">*</red></label>
                                                        <input type="text" max="45" class="form-control" maxlength="50" path="description" name="description"  id="description" placeholder="Enter description"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="applicationAccess">Access to App<red style="color: red">*</red></label>
                                                        <div class="selectRow">
                                                            <!-- Using data-placeholder below to set place holder value versus putting in configuration -->
                                                            <select id="accessToApp" name="accessToApp" style="width:100%" class="col-md-5" data-placeholder="Select an option" multiple>

                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <form:button type="button" id="verify" onclick="validate()" class="btn btn-primary">Submit</form:button>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </form>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--modal code for updating profile info-->
        <div class="modal" id="proInfo_modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">Update Profile Info</h4>
                    </div>
                    <div class="modal-body">

                        <div id="">

                            <div class="row panel-body widget-shadow">
                                <div class="col-md-12">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <h3 class="box-title"></h3>
                                        </div>
                                        <div class="col-md-6">
                                            <h4 style="color: slateblue"> ${successMessage}</h4>
                                            <h4 style="color: red"> ${errorMessage}</h4>
                                            <h4 style="color: red"> ${internalErrorMessage}</h4>
                                        </div>
                                    </div>
                                    <form  method="post">

                                        <div class="box-body">
                                            <div class="row">

                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="name">Profile Name<red style="color: red">*</red></label>
                                                        <input type="text" path="name" class="form-control" maxlength="40" name="name" id="UpdateprofileName" placeholder="Enter profile Name"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="syncInterval">Sync Interval<red style="color: red">*</red></label>
                                                        <input type="number" min="0" max="60" path="UpdatesyncInterval" class="form-control" maxlength="10" name="UpdatesyncInterval" id="UpdatesyncInterval" placeholder="Sync interval in minutes" onkeypress="return isNumberKey(event)"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="description">Description<red style="color: red">*</red></label>
                                                        <input type="text" max="60" class="form-control" required="true" maxlength="45" path="description" name="Updatedescription"  id="Updatedescription" placeholder="Enter description"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label for="applicationAccess">Access to App<red style="color: red">*</red></label>
                                                        <div class="selectRow">
                                                            <!-- Using data-placeholder below to set place holder value versus putting in configuration -->
                                                            <select id="UpdateaccessToApp" name="UpdateaccessToApp" style="width:100%" class="col-md-5" data-placeholder="Select an option" multiple>

                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <form:button type="button" id="verify" onclick="Update()" class="btn btn-primary">Update</form:button>
                                                    </div>
                                                </div>
                                                <input type="hidden" id="profileId" name="profileId" value="">
                                            </div>


                                            <!--this will show the list of fleet mapped to this profile-->
                                            <div class="row" style="overflow: no-content">
                                                <strong> List of fleet mapped to this profile:</strong>
                                                <div class="box" id="datatable">
                                                    <div class="box-header" >
                                                        <input type="hidden" id="applicationId" value="" />
                                                    </div>

                                                    <div class="box-body"  >
                                                        <table id="pro_fleet_mapp_table" class="table-responsive table-bordered table-striped table-hover" >
                                                            <thead>
                                                                <tr>
                                                                    <!--<th>ID</th>-->
                                                                    <th>FLEET NAME</th>
                                                                    <th>DESCRIPTION</th>
                                                                </tr>
                                                            </thead> 

                                                        </table>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </form>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<%@include file="../jsp/footer.jsp" %>

<script>
    window.onload = listOfProfile;
    function listOfProfile() {
        $('#sub').show();
        var table = $('#example').DataTable({
            paging: false,
            retrieve: false,
            bInfo: false,
            searching: false,
            "ajax": {
                "url": "getProfileList",
                "type": "post",
                "dataSrc": ""},
            columnDefs: [
                {
                    targets: [1, 2, 3, 4, 5, 6, 7, 8],
                    className: 'text-center'
                },
                {
                    "targets": [],
                    "visible": false
                }
            ],
            "columns": [
                {"className": 'pro-info',
                    "data": function (data, type, row, meta) {
                        return '<a href="#proInfo_modal" data-toggle="modal"><img style="width:30px; height:30px" src="images/edit.png"/></a>';
                    }
                },
                {"data": "name"},
                {"data": "description"},
                {"data": function (data) {
                        var d = moment(data.createdOn).format('DD-MM-YY hh:mm A');
                        return d;
                    }
                },
//                {"data": "createdOn"},
//                {"data": "updatedOn"},
                {"data": function (data) {
                        var d = moment(data.updatedOn).format('DD-MM-YY hh:mm A');
                        return d;
                    }
                },
                {"data": "updatedBy"},
                {"data": "syncInterval"},
                {"data": "status"},
                {"className": 'pro-del',
                    "data": function (data, type, row, meta) {
                        return '<a class="btn btn-info btn-sm" href="#">DELETE</a>';
                    }
                    //                    "defaultContent": "<a href=\"#\" ><b>View</b> </a>"
                }
            ]

                    //            $('#example').empty();
        });
        $('#example tbody').on('click', 'td.pro-del', function () {
            var $tr = $(this).closest('tr');
            rowData = $('#example').DataTable().row($tr).data();
            var profileId = rowData["id"];
            var conf = confirm("If you delete this profile, All the fleet mapped to this profile will not get sync");
            if (conf == false) {
                return false;
            }
            $('#sub').show();
            $.ajax({
                url: "deleteProfile",
                type: "POST",
                data: {
                    profileId: "" + profileId + ""
                },
                success: function (data) {
                    $('#sub').hide();
                    alert(data);
                    window.location.reload();
                }
            });
        });
        $('#example tbody').on('click', 'td.pro-info', function () {
            var $tr = $(this).closest('tr');
            rowData = $('#example').DataTable().row($tr).data();
            var profileId = rowData["id"];
            $.ajax({
                url: "getProfileById",
                type: "POST",
                data: {
                    profileId: "" + profileId + ""
                },
                success: function (data) {
//                        alert(data);
                    var result = JSON.parse(data);
                    document.getElementById("UpdateprofileName").value = result.name;
                    document.getElementById("Updatedescription").value = result.description;
                    document.getElementById("UpdatesyncInterval").value = result.syncInterval;
                    document.getElementById("profileId").value = result.profileId;
                    var select = document.getElementById("UpdateaccessToApp");

                    apkDropDownList = null;
                    for (var i in globalAllApkNamesList) {
                        apkDropDownList += "<option id=" + variable[i] + " value=" + i + ">" + variable[i] + "</option>";
                    }
                    $('#UpdateaccessToApp').html(apkDropDownList);
                    var option;
                    for (var i = 0; i < select.options.length; i++) {
                        option = select.options[i];
//                        alert(option.id);
                        for (re in result) {

                            if (option.value == re) {
                                option.selected = true;
//                                result += "<option id=" + variable[i] +" value=" + i + ">" + variable[i] + "</option>"
                            }
                        }
                    }

                    return true;
//                                window.location.reload();
                }
            });

            //this ajax call to get the list of fleet mapped to this profile
            var table_map = $('#pro_fleet_mapp_table').DataTable({
                paging: false,
                retrieve: false,
                bInfo: false,
                "bDestroy": true,
                searching: false,
                "ajax": {
                    "url": "getListOfFleetMappedToProfile",
                    "type": "post",
                    "data": {
                        "profileId": "" + profileId + ""
                    },
                    "dataSrc": ""
                },
                columnDefs: [
                    {
                        targets: [0, 1],
                        className: 'text-center'
                    }],
                "columns": [
//                        {"data": "id"},
                    {"data": "name"},
                    {"data": "description"}
                ]
            });

        });
        $('#sub').hide();

    }


</script>
<script type="text/javascript">
    $(document).ready(
            function () {
                $("#accessToApp").select2();
                $("#UpdateaccessToApp").select2();

            }
    );

    var globalAllApkNamesList = null;
    $(document).ready(function () {

        var result;
        $.ajax({
            type: "POST",
            url: 'getApkNamesList',
            success: function (response) {

                var result = null;
                variable = JSON.parse(response);
                globalAllApkNamesList = variable;
                for (i in variable)
                {
                    result += "<option id=" + variable[i] + " value=" + i + ">" + variable[i] + "</option>";
                }
                $('#accessToApp').html(result);
                $('#UpdateaccessToApp').html(result);
            },
            error: function (response) {
            }
        });
    });

    function validate() {
        var nameRegex1 = /(<)/;
        var nameRegex2 = /(>)/;
        if (document.getElementById("profileName").value === "" || nameRegex1.test(document.getElementById("profileName").value) || nameRegex2.test(document.getElementById("profileName").value)) {
            alert("profile name cannot be blank and cannot contain '<','>' symbol.");
            return false;
        }
        if (document.getElementById("syncInterval").value < 1 || document.getElementById("syncInterval").value > 15) {
            alert("Sync interval cannot be 0 and cannot be greater than 15");
            return false;
        }
         if (document.getElementById("description").value.trim().length === 0 || document.getElementById("description").value.trim().length > 45) {
            alert("Description cannot be empty or greater than 45 characters.");
            return false;
        }


        var profileName = document.getElementById("profileName").value;
//            alert(profileName);
        var syncInterval = document.getElementById("syncInterval").value;
        var description = document.getElementById("description").value;
        var selectedValues = [];
        $("#accessToApp :selected").each(function () {
            selectedValues.push($(this).val());

        });
        
        if( selectedValues.length < 1){
             alert("Access to application cannot be empty.");
            return false;
        }
            
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

    function Update() {
        var nameRegex1 = /(<)/;
        var nameRegex2 = /(>)/;
        if (document.getElementById("UpdateprofileName").value.trim() === "" || nameRegex1.test(document.getElementById("UpdateprofileName").value) || nameRegex2.test(document.getElementById("UpdateprofileName").value)) {
            alert("profile name cannot be blank and cannot contain '<','>' symbol.");
            return false;
        }
        if (document.getElementById("UpdatesyncInterval").value < 1 || document.getElementById("UpdatesyncInterval").value > 15) {
            alert("Sync interval cannot be 0 and cannot be greater than 15");
            return false;
        }
        if (document.getElementById("Updatedescription").value.trim().length === 0 || document.getElementById("Updatedescription").value.trim().length > 45 || nameRegex1.test(document.getElementById("Updatedescription").value) || nameRegex2.test(document.getElementById("Updatedescription").value)) {
            alert("Description cannot be empty or greater than 45 characters and cannot contain '<','>' symbol.");
            return false;
        }
//        alert("sync: "+document.getElementById("UpdatesyncInterval").value);


        {
            var profileName = document.getElementById("UpdateprofileName").value;
//            alert(profileName);
            var syncInterval = document.getElementById("UpdatesyncInterval").value;
            var description = document.getElementById("Updatedescription").value;
            var profileId = document.getElementById("profileId").value;
            var selectedValues = [];
            $("#UpdateaccessToApp :selected").each(function () {
                selectedValues.push($(this).val());

            });
            if (selectedValues == "") {
                alert("Profile cannot be kept without mapping with any application.");
                return false;
            }
//            alert(selectedValues);
            var conf = confirm("Changes in the profile will effect all the fleet mapped to this profile");
            if (conf == false) {
                return false;
            }
            $('#sub').show();
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
                    $('#sub').hide();
                    alert(data);
                    window.location.reload();
                }
            });

        }
    }

</script>
