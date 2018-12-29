<%-- 
    Document   : projectFleetDetails
    Created on : 22 Oct, 2018, 11:28:06 AM
    Author     : ZMF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
        <div class="main-page">

            <!--<div class="tables">-->
            <!--<h2 class="title1">Project Fleet details:</h2>-->
            <!--<button><div class=""><a href="#my_modal" data-toggle="modal" class="btn btn-primary btn-block"><b>ADD</b></a></div></button>-->
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
                    <h3 class="box-title">Project Fleet</h3>
                    <a href="#my_modal" style="float: right" data-toggle="modal" class="btn btn-primary"><b>ADD</b></a>
                </div>
                <!--<img src="<c:url value='iconImages/com.zero.aepsmerchantonboarding1.0.3.png' />" class="user-image">-->
                <!-- /.box-header -->
                <div class="box-body" style="overflow: auto" >
                    <table id="example" class="table table-hover table-striped" style="width: 100%">
                        <thead>
                            <tr>
                                <th></th>
                                <th>NAME</th>
                                <th>DESCRIPTION</th>
                                <th>PROFILE</th>
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
                        <h4 class="modal-title">Add Fleet</h4>
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
                                    <form  method="post" action="addNewProjectFleet">

                                        <div class="box-body">
                                            <div class="row">

                                                <div class="col-md-5">
                                                    <div class="form-group">
                                                        <label for="name">Fleet Name<red style="color: red">*</red></label>
                                                        <input type="text" path="name" class="form-control" maxlength="40" name="name" id="fleetName" placeholder="Enter fleet Name"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-5">
                                                    <div class="form-group">
                                                        <label for="description">Description<red style="color: red">*</red></label>
                                                        <input type="text" max="60" class="form-control" maxlength="50" path="description" name="description"  id="description" placeholder="Enter description"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-5">
                                                    <div class="form-group">
                                                        <label for="Profile">Map to profile<red style="color: red">*</red></label>
                                                        <div class="selectRow">
                                                            <!-- Using data-placeholder below to set place holder value versus putting in configuration -->
                                                            <select id="mapToProfile" name="mapToProfile" style="width:100%" class="col-md-5" data-placeholder="Select an option">

                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>



                                                <div class="col-md-8 row">
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
                        <h4 class="modal-title">Update Fleet</h4>
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
                                    <form  method="post" action="updateProjectFleet">

                                        <div class="box-body">
                                            <div class="row">

                                                <div class="col-md-5">
                                                    <div class="form-group">
                                                        <label for="name">Fleet Name<red style="color: red">*</red></label>
                                                        <input type="text" path="name" class="form-control" maxlength="40" name="name" id="UpdateFleetName" placeholder="Enter profile Name"/>
                                                    </div>
                                                </div>

                                                <div class="col-md-5">
                                                    <div class="form-group">
                                                        <label for="description">Description<red style="color: red">*</red></label>
                                                        <input type="text" max="60" class="form-control" maxlength="45" path="description" name="Updatedescription"  id="Updatedescription" placeholder="Enter description"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-5">
                                                    <div class="form-group">
                                                        <label for="applicationAccess">Map to Profile<red style="color: red">*</red></label>
                                                        <div class="selectRow">
                                                            <!-- Using data-placeholder below to set place holder value versus putting in configuration -->
                                                            <select id="UpdatemapToProfile" name="UpdatemapToProfile" style="width:100%" class="col-md-5" data-placeholder="Select an option">

                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <form:button type="button" id="verify" onclick="Update()" class="btn btn-primary">Update</form:button>
                                                    </div>
                                                </div>
                                                <input type="hidden" id="UpdateFleetId" name="" value="">
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
    window.onload = listOfFleet;
    function listOfFleet() {
        var table = $('#example').DataTable({
            paging: false,
            retrieve: false,
            bInfo: false,
            searching: false,
            "ajax": {
                "url": "getFleetList",
                "type": "post",
                "dataSrc": ""},
            columnDefs: [
                {
                    targets: [0, 1, 2, 3, 4],
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
                        return '<a href="#proInfo_modal" data-toggle="modal"><img style="width:20px; height:20px" src="images/edit.png"/></a>';
                    }
                },
                {"data": "name"},
                {"data": "description"},
                {"data": "profileId"},
                {"className": 'pro-del',
                    "data": function (data, type, row, meta) {
                        return '<center><a class="btn btn-info btn-sm" href="#">DELETE</a></center>';
                    }
                    //                    "defaultContent": "<a href=\"#\" ><b>View</b> </a>"
                }
            ]

                    //            $('#example').empty();
        });
        $('#example tbody').on('click', 'td.pro-del', function () {
            var $tr = $(this).closest('tr');
            rowData = $('#example').DataTable().row($tr).data();
            var projectFleetId = rowData["id"];
            var conf = confirm("You are going to delete this fleet, devices mapped to this fleet will not get sync.");
            if (conf == false) {
                return false;
            }
            $.ajax({
                url: "deleteProjectFleet",
                type: "POST",
                data: {
                    projectFleetId: "" + projectFleetId + ""
                },
                success: function (data) {
                    alert(data);
                    window.location.reload();
                }
            });
        });
        $('#example tbody').on('click', 'td.pro-info', function () {
            var $tr = $(this).closest('tr');
            rowData = $('#example').DataTable().row($tr).data();
            var fleetId = rowData["id"];
            $.ajax({
                url: "getFleetById",
                type: "POST",
                data: {
                    fleetId: "" + fleetId + ""
                },
                success: function (data) {
//                    alert(data);
                    var result = JSON.parse(data);
                    document.getElementById("UpdateFleetName").value = result.name;
                    document.getElementById("Updatedescription").value = result.description;
//                        document.getElementById("UpdateProfileId").value = result.profileId;
//                    alert("fleet id: " + result.fleetId);
                    document.getElementById("UpdateFleetId").value = result.fleetId;
                    var select = document.getElementById("UpdatemapToProfile");
//                     $('#UpdateaccessToApp').html("");
                    ResultListOfProfileName = null;
                    for (i in globalProfileNamesList)
                    {
                        if (i == result.profileId) {
                            ResultListOfProfileName += '<option selected = "selected" value=' + i + '>' + globalProfileNamesList[i] + '</option>';
                        } else {
                            ResultListOfProfileName += "<option value=" + i + ">" + globalProfileNamesList[i] + "</option>";
                        }
                    }
//                    alert(ResultListOfProfileName);
                    $('#UpdatemapToProfile').html(ResultListOfProfileName);

                    var option;
//                    for (var i = 0; i < select.options.length; i++) {
//                        option = select.options[i];
//                        for (re in result) {
//                            if (option.value == result.profileId) {
//                                option.selected = true;
//                            }
//                        }
//                    }
                    return true;
//                                window.location.reload();
                }
            });

        });


    }


</script>
<script type="text/javascript">
    $(document).ready(
            function () {
                $("#mapToProfile").select2();
                $("#UpdatemapToProfile").select2();

            }
    );

    var globalProfileNamesList = null;
    $(document).ready(function () {

        var result;
        $.fn.dataTable.ext.errMode = 'none';
        $.ajax({
            type: "POST",
            url: 'getProfileNamesList',
            success: function (response) {
//                alert(response);
                var result = null;
                variable = JSON.parse(response);
                globalProfileNamesList = variable;
                for (i in variable)
                {
                    result += "<option value=" + i + ">" + variable[i] + "</option>";
                }
                $('#mapToProfile').html(result);
                $('#UpdatemapToProfile').html(result);
            },
            error: function (response) {
            }
        });
    });

    function validate() {
        var nameRegex1 = /(<)/;
        var nameRegex2 = /(>)/;
        if (document.getElementById("fleetName").value.trim() === "" || nameRegex1.test(document.getElementById("fleetName").value) || nameRegex2.test(document.getElementById("fleetName").value)) {
            alert("Fleet name cannot be blank and cannot contain '<','>' symbol.");
            return false;
        }
        if (document.getElementById("description").value.trim().length === 0 || document.getElementById("description").value.trim().length > 45 || nameRegex1.test(document.getElementById("description").value) || nameRegex2.test(document.getElementById("description").value)) {
            alert("Description cannot be empty or greater than 45 characters and cannot contain '<','>' symbol.");
            return false;
        }


        {
            var fleetName = document.getElementById("fleetName").value;
            var description = document.getElementById("description").value;
            var selectedValues = [];
            $("#mapToProfile :selected").each(function () {
                selectedValues.push($(this).val());

            });
//            alert(selectedValues);
            $.ajax({
                url: "addNewFleet",
                type: "POST",
                data: {
                    fleetName: fleetName,
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
        var nameRegex1 = /(<)/;
        var nameRegex2 = /(>)/;
        if (document.getElementById("UpdateFleetName").value.trim() === "" || nameRegex1.test(document.getElementById("UpdateFleetName").value) || nameRegex2.test(document.getElementById("UpdateFleetName").value)) {
            alert("Fleet name cannot be blank and cannot contain '<','>' symbol.");
            return false;
        }
        if (document.getElementById("Updatedescription").value.trim().length === 0 || document.getElementById("Updatedescription").value.trim().length > 45 || nameRegex1.test(document.getElementById("Updatedescription").value) || nameRegex2.test(document.getElementById("Updatedescription").value)) {
            alert("Description cannot be empty or greater than 45 characters and cannot contain '<','>' symbol.");
            return false;
        }


        {
            var fleetName = document.getElementById("UpdateFleetName").value;
            var description = document.getElementById("Updatedescription").value;
//            var profileId = document.getElementById("UpdateProfileId").value;
            var fleetId = document.getElementById("UpdateFleetId").value;
            var selectedValues = document.getElementById("UpdatemapToProfile").value;

            if (selectedValues == "") {
                alert("Fleet cannot be kept without mapping with any profile.");
                return false;
            }
//            alert(selectedValues);
            $.ajax({
                url: "updateProjectFleet",
                type: "POST",
                data: {
                    fleetName: fleetName,
                    description: description,
                    selectedValues: selectedValues,
                    fleetId: fleetId
                },

                success: function (data) {
                    alert(data);
                    window.location.reload();
                }
            });

        }
    }

</script>
