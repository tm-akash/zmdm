<%-- 
    Document   : ContentSamplePage
    Created on : 9 Oct, 2018, 3:19:47 PM
    Author     : ZMF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

<%@include file="../jsp/header.jsp" %>

<div class="content-wrapper">
    <section class="content">
        
        <div class="main-page">
            <div id="sub" style="display:none">
                <img id="loading-image" src="images/load.gif" alt="Loading..." />
            </div>

            <!--<div class="tables box">-->
            <!--<h2 class="title1 box-header">Applications: </h2>-->

            <!--<div class="panel-body widget-shadow">-->
            <!--<div class="row" style="overflow: auto;">-->

            <!--<div class="col-xs-12"  >-->                            
            <!-- /.box -->

            <!--                <div class="box" style="display:none;width:1093px" id="datatable">-->
            <div class="col-md-12">
                .
            </div>

            <div class="box"  id="datatable">


                <div class="box-header" >
                    <h3 class="box-title">Applications</h3>
                    <a href="#my_modal" data-toggle="modal" style="float: right" class="btn btn-primary"><b>ADD</b></a>

                </div>
                <!--<img src="<c:url value='iconImages/com.zero.aepsmerchantonboarding1.0.3.png' />" class="user-image">-->
                <!-- /.box-header -->
                <div class="box-body" style="overflow: auto"  >
                    <table id="example" class="table table-hover table-responsive table-striped" style="width: 100%">
                        <thead>
                            <tr>
                                <th></th>
                                <th>ICON</th>
                                <th>NAME</th>
                                <th>PACKAGE NAME</th>
                                <th>VERSION CODE</th>
                                <th>VERSION NAME</th>
                                <th>SIZE</th>
                                <th>UPLOAD DATE</th>
                                <th>UPLOAD BY</th>
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
                        <h4 class="modal-title">Upload APK</h4>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="uploadAPK" enctype="multipart/form-data" onsubmit="return loader()">
                            <div class="form-group">
                                <input type="file" name="fileUpload" accept=".apk" required="true" size="50" />
                            </div>

                            <input type="submit" class="btn btn-primary" value="UPLOAD"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!--modal code for displaying app info-->
        <div class="modal" id="appInfo_modal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title">APK is mapped in following profile:</h4>
                    </div>
                    <div class="modal-body">
                        <!--<form method="post" action="deleteAPK" >-->
                        <!--<div class="row" style="">-->
                            <div class="box" id="datatable">
                                <div class="box-header" >
                                    <input type="hidden" id="applicationId" value="" />
                                </div>

                                <div class="box-body"  style="overflow: auto">
                                    <table id="appInfo_table" style="overflow: auto" class="table-responsive table-striped table-hover" >
                                        <thead>
                                            <tr>
                                                <!--<th>ID</th>-->
                                                <th>PROFILE NAME</th>
                                                <th>DESCRIPTION</th>
                                                <th>UPDATED BY</th>
                                                <th>SYNC INTERVAL</th>
                                                <th>STATUS</th>
                                            </tr>
                                        </thead> 

                                    </table>
                                </div>
                            </div>
                        <!--</div>-->
                        <!--                        <div class="col-xs-4 btn">
                                                    <input id="deleteApk" type="button" onclick="deleteAPK()" disabled="true" class="btn btn-primary btn-block" value="DELETE APK"/>
                                                </div>-->
                        <!--<div><weak id="deleteInfoMessage" style="color: red">Application can only be deleted if it is not mapped to any profile.</weak></div>-->
                        <!--</form>-->
                    </div>
                </div>
            </div>
        </div>

    </section>
</div>
<%@include file="../jsp/footer.jsp" %>


<script>
    window.onload = listOfApplications;
    var applicationIdForDelete = null;
    function listOfApplications() {
        var displayMessage = "${message}";
        if (displayMessage != "") {
            alert(displayMessage);
        }
        applicationIdForDelete = null;
        var table = $('#example').DataTable({
            paging: false,
            retrieve: false,
            bInfo: false,
            searching: false,
            "ajax": {
                "url": "getApplicationList",
                "type": "post",
                "dataSrc": ""},
            columnDefs: [
                {
                    targets: [1, 2, 3, 4, 5, 6, 7, 8, 9],
                    className: 'text-center'
                },
                {
                    "targets": [7, 8],
                    "visible": false
                }
            ],
            "columns": [
                {"className": 'app-info',
                    "data": function (data, type, row, meta) {
                        return '<a href="#appInfo_modal" data-toggle="modal"><img style="width:20px; height:20px" src="images/info.png"/></a>';
                    }
                },
                {"data": function (data, type, row, meta) {
                        var path = "iconImages/" + data.icon + "";
                        return '<a><img style="width:25px; height:25px" src="' + path + '"/></a>';
                    }
                },
                {"data": function (data, type, row, meta) {
                        var downloadPath = "iconImages/" + data.name + "" + data.versionName + "";
                        return '<a href="downloadPath/' + data.name + '">' + data.name + '</a>';
                    }
                },
//                    {"data": "name"},
                {"data": "packageName"},
                {"data": "versionCode"},
                {"data": "versionName"},
                {"data": "size"},
                {"data": "uploadedDate"},
                {"data": "uploadedBy"},
                {
                    "className": 'app-delete',
                    "data": function (data, type, row, meta) {
                        return '<a class="btn btn-info btn-sm" href="#">DELETE</a>';
                    }
                    //                    "defaultContent": "<a href=\"#\" ><b>View</b> </a>"
                }
            ]

                    //            $('#example').empty();
        });
        $('#example tbody').on('click', 'td.app-info', function () {
            var $tr = $(this).closest('tr');
            rowData = $('#example').DataTable().row($tr).data();
            var applicationId = rowData["id"];
            applicationIdForDelete = applicationId;
            document.getElementById('applicationId').value = applicationIdForDelete;
            $('#sub').show();
            var table = $('#appInfo_table').DataTable({
                paging: false,
                retrieve: false,
                bInfo: false,
                "bDestroy": true,
                searching: false,
                "ajax": {
                    "url": "getAllInfoOfApk",
                    "type": "post",
                    "data": {
                        "applicationId": "" + applicationId + ""
                    },
                    "dataSrc": ""
                },
                "columns": [
//                        {"data": "id"},
                    {"data": "name"},
                    {"data": "description"},
                    {"data": "updatedBy"},
                    {"data": "syncInterval"},
                    {"data": "status"}
                ],
                "initComplete": function (settings, json) {
                    info = this.api().page.info();
//                        alert('Total records' + info.recordsTotal);
//                    if (info.recordsTotal != 0) {
//                        alert('Delete option for this application will only be available if it is not mapped to any profile');
//                        document.getElementById("deleteApk").disabled = true;
//                        document.getElementById("deleteInfoMessage").innerHTML = "Application can only be deleted if it is not mapped to any profile.";
//                    } else {
//                        document.getElementById("deleteApk").disabled = false;
//                        document.getElementById("deleteInfoMessage").innerHTML = "This button will delete the APK from the database permamnently.";
//                    }
                }
            });
            $('#appInfo_table').on('click', 'tr', function () {
                var $tr = $(this).closest('tr');
                rowData = $('#appInfo_table').DataTable().row($tr).data();
                var profileId = rowData["id"]; //incase if required to edit the profile from here only.
                window.location.href = "profileDetails";
            });
            $('#sub').hide();

        });

        $('#example tbody').on('click', 'td.app-delete', function () {
            var conf = confirm("You are going to delete this application.");
            if (conf == false) {
                return false;
            } else {
                var $tr = $(this).closest('tr');
                rowData = $('#example').DataTable().row($tr).data();
                var applicationId = rowData["id"];
                $.ajax({
                    url: "getAllInfoOfApk",
                    type: 'POST',
                    data: {
                        "applicationId": "" + applicationId + ""
                    },
                    dataSrc: "",
                    success: function (data, textStatus, jqXHR) {
                        var result = data;
                        if (result === "[]") {

                            $('#sub').show();
                            $.ajax({
                                url: "deleteAPK",
                                type: "POST",
                                data: {
                                    applicationId: "" + applicationId + ""
                                },
                                success: function () {
                                    $('#sub').hide();
                                    alert("The APK has been deleted successfully.");
                                    window.location.reload();
                                }
                            });

                        } else {
                            alert("The APK cannot be deleted as it is still mapped to profile, Please remove it from the profile and try again.");
                        }
                    }
                });
            }
        });

    }

    function deleteAPK(applicationId) {


        $.ajax({
            url: "getAllInfoOfApk",
            type: 'POST',
            data: {
                "applicationId": "" + applicationId + ""
            },
            dataSrc: "",
            success: function (data, textStatus, jqXHR) {
                alert(data);
            }
        });

        var conf = confirm("You are going to delete this application.");
        if (conf == false) {
            return false;
        }
        return false;


        $('#sub').show();
        $.ajax({
            url: "deleteAPK",
            type: "POST",
            data: {
                applicationId: "" + applicationIdForDelete + ""
            },
            success: function () {
                $('#sub').hide();
                alert("The APK has been deleted successfully.");
                window.location.reload();
            }
        });

    }

    function loader() {
        $('#sub').show();
    }

</script>
