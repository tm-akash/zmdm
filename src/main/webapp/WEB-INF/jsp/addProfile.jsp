<%-- 
    Document   : addProfile
    Created on : 19 Oct, 2018, 2:15:12 PM
    Author     : ZMF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jsp/header.jsp" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>

<script type="text/javascript">
    $(document).ready(
            function () {
                $("#multipleSelectExample").select2();
            }
    );

</script>
<style type="text/css">
    .selectRow {
        display : block;
        padding : 0px;
    }
    .select2-container {
        width: 200px;
    }
</style>
<div id="page-wrapper">

    <div class="row panel-body widget-shadow">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-4">
                    <h3 class="box-title">Add Profile:</h3>
                </div>
                <div class="col-md-6">
                    <h4 style="color: slateblue"> ${successMessage}</h4>
                    <h4 style="color: red"> ${errorMessage}</h4>
                    <h4 style="color: red"> ${internalErrorMessage}</h4>
                </div>
            </div>
            <form:form  method="post" action="addNewProfile" commandName="profile">

                <div class="box-body">
                    <div class="row">

                        <div class="col-md-5">
                            <div class="form-group">
                                <label for="name">Profile Name<red style="color: red">*</red></label>
                                        <form:input type="text" path="name" class="form-control" maxlength="40" name="name" id="name" placeholder="Enter profile Name"/>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group">
                                <label for="syncInterval">Sync Interval<red style="color: red">*</red></label>
                                        <form:input type="number" min="0" max="60" path="syncInterval" class="form-control" maxlength="10" name="syncInterval" id="syncInterval" placeholder="Sync interval in minutes" onkeypress="return isNumberKey(event)"/>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group">
                                <label for="description">Description<red style="color: red">*</red></label>
                                        <form:input type="text" max="60" class="form-control" maxlength="50" path="description" name="description"  id="description" placeholder="Enter description"/>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group">
                                <label for="applicationAccess">Access to App<red style="color: red">*</red></label>
                                <div class="selectRow">
                                    <!-- Using data-placeholder below to set place holder value versus putting in configuration -->
                                    <select id="multipleSelectExample" class="col-md-12" data-placeholder="Select an option" multiple>
                                        <option value="1">Option 1</option>
                                        <option value="2">Option 2</option>
                                        <option value="3">Option 3</option>
                                        <option value="4">Option 4</option>
                                        <option value="5">Option 5</option>
                                    </select>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-5">
                            <div class="form-group">
                                <form:button type="button" id="verify" onclick="validate()" class="btn btn-primary">Submit</form:button>
                                </div>
                            </div>

                        </div>
                </form:form>
            </div>
        </div>

    </div>

