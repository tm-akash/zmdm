<%-- 
    Document   : header
    Created on : 13 Dec, 2018, 11:46:29 AM
    Author     : ZMF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="shortcut icon" href="resources/img/title_image.png" type="image/png">
        <title>Zero mobile device management</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="/ThemeApplication/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="/ThemeApplication/bower_components/select2/dist/css/select2.min.css">
        <!--<link rel="stylesheet" href="/ThemeApplication/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">-->
        <link rel="stylesheet" href="/ThemeApplication/bower_components/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="/ThemeApplication/bower_components/Ionicons/css/ionicons.min.css">
        <link rel="stylesheet" href="/ThemeApplication/dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="/ThemeApplication/dist/css/skins/_all-skins.min.css">

        <link rel="stylesheet" href="resources/css/smst.css">
        <link rel="stylesheet" href="/ThemeApplication/bower_components/jvectormap/jquery-jvectormap.css">
        <link rel="stylesheet" href="/ThemeApplication/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
        <link rel="stylesheet" href="/ThemeApplication/bower_components/bootstrap-daterangepicker/daterangepicker.css">        
        <link rel="stylesheet" href="/ThemeApplication/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">   
        <!--   Google Font -->

    </head>
    <body class="hold-transition skin-blue sidebar-mini" >
        <div class="wrapper">
            <header  class="main-header">
                <!-- Logo -->

                <a href="welcome" class="logo">                   
                    <span class="logo-mini"> <b>ZMD</b></span>
                    <span class="logo-lg"><b>ZMDM </b></span>
                    <span  class="logo-lg"> <img src="resources/img/bigger_logo.png" style="background-color: #ffffff"  class="logo"></span>
                </a>
                <nav  class="navbar navbar-static-top">
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>

                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <li class="dropdown user user-menu">
                                <!--                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="padding-right: 0px">                                    
                                    <img src="<c:url value='resources/img/logo.jpg' />" class="" onerror="this.src='resources/img/profilepic.png';">

                                </a>

                                <ul class="dropdown-menu">
                                    <li class="user-header">


                                    </li>
                                    <!-- Menu Body -->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <!--<a href="profile" class="btn btn-primary">Profile</a>-->
                                        </div>
                                        <div class="pull-right">
                                            <a href="logout" class="btn btn-primary">Sign out</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>

                        </ul>
                    </div>
                </nav>
            </header>


            <aside class="main-sidebar" style="height: 100%">
                <section class="sidebar">
                    <div class="user-panel">
                        <div class="pull-left image">
                            <!--<img src="<c:url value='resources/img/bigger_logo.jpg' />" class="img-circle" >-->
                        </div>
                        <div class="pull-left info">
                            <p class="CellWithComment"><span style="font-weight: normal" class="CellComment"></span></p>

                        </div>
                    </div>

                    <ul class="sidebar-menu" data-widget="tree">
                        <li class=" treeview">

                            <!--                            <a href="index">
                                                            <i class="fa fa-user"></i> <span>Application</span>
                                                            <span class="pull-right-container">
                                                                <i class="fa fa-angle-left pull-right"></i>
                                                            </span>
                                                        </a>-->
                            <!--                            <ul class="treeview-menu">
                                                            <li><a href="applicationsDetails"><i class="fa fa-angle-right"></i> Applications</a></li>
                                                            <li><a href="profileDetails"><i class="fa fa-angle-right"></i> Profile</a></li>
                                                            <li><a href="deviceDetails"><i class="fa fa-angle-right"></i> Device</a></li>
                                                            <li><a href="projectFleetDetails"><i class="fa fa-angle-right"></i> Project Fleet</a></li>
                                                        </ul>-->
                        <li><a href="applicationsDetails"><i class="fa fa-angle-right"></i> Applications</a></li>
                        <li><a href="profileDetails"><i class="fa fa-angle-right"></i> Profile</a></li>
                        <li><a href="deviceDetails"><i class="fa fa-angle-right"></i> Device</a></li>
                        <li><a href="projectFleetDetails"><i class="fa fa-angle-right"></i> Project Fleet</a></li>

                        </li>    
                    </ul>

                </section>
                <!-- /.sidebar -->
            </aside>

            <style>
                .navbar-nav>li>a {
                    padding-top: 0px;
                    padding-bottom: 0px;
                }
            </style>



