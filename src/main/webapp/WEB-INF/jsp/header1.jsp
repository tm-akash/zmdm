<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
    <head>
        <title>Zero mobile device management</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Glance Design Dashboard Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />

        <!-- Custom CSS -->
        <link href="css/style.css" rel='stylesheet' type='text/css' />

        <!-- font-awesome icons CSS -->
        <link href="css/font-awesome.css" rel="stylesheet"> 
        <!-- //font-awesome icons CSS -->

        <!-- side nav css file -->
        <link href='css/SidebarNav.min.css' media='all' rel='stylesheet' type='text/css'/>
        <!-- side nav css file -->

        <!-- js-->
        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/modernizr.custom.js"></script>

        <!--webfonts-->
        <link href="//fonts.googleapis.com/css?family=PT+Sans:400,400i,700,700i&amp;subset=cyrillic,cyrillic-ext,latin-ext" rel="stylesheet">
        <!--//webfonts--> 

        <!-- Metis Menu -->
        <script src="js/metisMenu.min.js"></script>
        <script src="js/custom.js"></script>
        <script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
        <link href="css/custom.css" rel="stylesheet">
        <!--//Metis Menu -->

    </head> 
    <%         HttpSession session_home = request.getSession(false);

        try {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            if (session.getAttribute("role") == null) {
                out.println("<script>parent.location.href='index'</script>");
            }
            System.out.println("session: " + session_home.getAttribute("name"));
            if (session_home.getAttribute("name").equals(null)) {
                response.sendRedirect("index");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index");
        }
    %>

    <body class="cbp-spmenu-push">
        <div class="main-content">
            <div class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
                <!--left-fixed -navigation-->
                <aside class="sidebar-left">
                    <nav class="navbar navbar-inverse">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".collapse" aria-expanded="false">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand"><span class=""></span> Zero Mobile<span class="dashboard_text">Device Management</span></a>
                        </div>
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="sidebar-menu">
                                <li class="header">MAIN NAVIGATION</li>
                                <li class="treeview">
                                    <a href="#">
                                        <!--<i class="fa fa-dashboard"></i> <span>Dashboard</span>-->
                                    </a>
                                </li>
                                <li class="">   <!--treeview-->
                                    <a href="#">
                                        <i class="fa fa-laptop"></i>
                                        <span>Components</span>
                                        <i class="fa fa-angle-left pull-right"></i>
                                    </a>
                                    <ul class="treeview-menu">
                                        <li><a href="applicationsDetails"><i class="fa fa-angle-right"></i> Applications</a></li>
                                        <li><a href="profileDetails"><i class="fa fa-angle-right"></i> Profile</a></li>
                                        <li><a href="deviceDetails"><i class="fa fa-angle-right"></i> Device</a></li>
                                        <li><a href="projectFleetDetails"><i class="fa fa-angle-right"></i> Project Fleet</a></li>

                                    </ul>
                                </li>

                            </ul>
                        </div>
                        <!-- /.navbar-collapse -->
                    </nav>
                </aside>
            </div>
        </div>
        <!--left-fixed -navigation-->

        <!-- header-starts -->
        <div class="sticky-header header-section ">
            <div class="header-left">

                <!--toggle button start-->
                <button id="showLeftPush"><i class="fa fa-bars"></i></button>
                <!--				toggle button end
                                                <div class="profile_details_left">notifications of menu start 
                                                        <ul class="nofitications-dropdown">
                                                                <li class="dropdown head-dpdn">
                                                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-envelope"></i><span class="badge">4</span></a>
                                                                        <ul class="dropdown-menu">
                                                                                <li>
                                                                                        <div class="notification_header">
                                                                                                <h3>You have 3 new messages</h3>
                                                                                        </div>
                                                                                </li>
                                                                                <li><a href="#">
                                                                                   <div class="user_img"><img src="images/1.jpg" alt=""></div>
                                                                                   <div class="notification_desc">
                                                                                        <p>Lorem ipsum dolor amet</p>
                                                                                        <p><span>1 hour ago</span></p>
                                                                                        </div>
                                                                                   <div class="clearfix"></div>	
                                                                                </a></li>
                                                                                <li class="odd"><a href="#">
                                                                                        <div class="user_img"><img src="images/4.jpg" alt=""></div>
                                                                                   <div class="notification_desc">
                                                                                        <p>Lorem ipsum dolor amet </p>
                                                                                        <p><span>1 hour ago</span></p>
                                                                                        </div>
                                                                                  <div class="clearfix"></div>	
                                                                                </a></li>
                                                                                <li><a href="#">
                                                                                   <div class="user_img"><img src="images/3.jpg" alt=""></div>
                                                                                   <div class="notification_desc">
                                                                                        <p>Lorem ipsum dolor amet </p>
                                                                                        <p><span>1 hour ago</span></p>
                                                                                        </div>
                                                                                   <div class="clearfix"></div>	
                                                                                </a></li>
                                                                                <li>
                                                                                        <div class="notification_bottom">
                                                                                                <a href="#">See all messages</a>
                                                                                        </div> 
                                                                                </li>
                                                                        </ul>
                                                                </li>
                                                                <li class="dropdown head-dpdn">
                                                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-bell"></i><span class="badge blue">4</span></a>
                                                                        <ul class="dropdown-menu">
                                                                                <li>
                                                                                        <div class="notification_header">
                                                                                                <h3>You have 3 new notification</h3>
                                                                                        </div>
                                                                                </li>
                                                                                <li><a href="#">
                                                                                        <div class="user_img"><img src="images/4.jpg" alt=""></div>
                                                                                   <div class="notification_desc">
                                                                                        <p>Lorem ipsum dolor amet</p>
                                                                                        <p><span>1 hour ago</span></p>
                                                                                        </div>
                                                                                  <div class="clearfix"></div>	
                                                                                 </a></li>
                                                                                 <li class="odd"><a href="#">
                                                                                        <div class="user_img"><img src="images/1.jpg" alt=""></div>
                                                                                   <div class="notification_desc">
                                                                                        <p>Lorem ipsum dolor amet </p>
                                                                                        <p><span>1 hour ago</span></p>
                                                                                        </div>
                                                                                   <div class="clearfix"></div>	
                                                                                 </a></li>
                                                                                 <li><a href="#">
                                                                                        <div class="user_img"><img src="images/3.jpg" alt=""></div>
                                                                                   <div class="notification_desc">
                                                                                        <p>Lorem ipsum dolor amet </p>
                                                                                        <p><span>1 hour ago</span></p>
                                                                                        </div>
                                                                                   <div class="clearfix"></div>	
                                                                                 </a></li>
                                                                                 <li>
                                                                                        <div class="notification_bottom">
                                                                                                <a href="#">See all notifications</a>
                                                                                        </div> 
                                                                                </li>
                                                                        </ul>
                                                                </li>	
                                                                <li class="dropdown head-dpdn">
                                                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-tasks"></i><span class="badge blue1">8</span></a>
                                                                        <ul class="dropdown-menu">
                                                                                <li>
                                                                                        <div class="notification_header">
                                                                                                <h3>You have 8 pending task</h3>
                                                                                        </div>
                                                                                </li>
                                                                                <li><a href="#">
                                                                                        <div class="task-info">
                                                                                                <span class="task-desc">Database update</span><span class="percentage">40%</span>
                                                                                                <div class="clearfix"></div>	
                                                                                        </div>
                                                                                        <div class="progress progress-striped active">
                                                                                                <div class="bar yellow" style="width:40%;"></div>
                                                                                        </div>
                                                                                </a></li>
                                                                                <li><a href="#">
                                                                                        <div class="task-info">
                                                                                                <span class="task-desc">Dashboard done</span><span class="percentage">90%</span>
                                                                                           <div class="clearfix"></div>	
                                                                                        </div>
                                                                                        <div class="progress progress-striped active">
                                                                                                 <div class="bar green" style="width:90%;"></div>
                                                                                        </div>
                                                                                </a></li>
                                                                                <li><a href="#">
                                                                                        <div class="task-info">
                                                                                                <span class="task-desc">Mobile App</span><span class="percentage">33%</span>
                                                                                                <div class="clearfix"></div>	
                                                                                        </div>
                                                                                   <div class="progress progress-striped active">
                                                                                                 <div class="bar red" style="width: 33%;"></div>
                                                                                        </div>
                                                                                </a></li>
                                                                                <li><a href="#">
                                                                                        <div class="task-info">
                                                                                                <span class="task-desc">Issues fixed</span><span class="percentage">80%</span>
                                                                                           <div class="clearfix"></div>	
                                                                                        </div>
                                                                                        <div class="progress progress-striped active">
                                                                                                 <div class="bar  blue" style="width: 80%;"></div>
                                                                                        </div>
                                                                                </a></li>
                                                                                <li>
                                                                                        <div class="notification_bottom">
                                                                                                <a href="#">See all pending tasks</a>
                                                                                        </div> 
                                                                                </li>
                                                                        </ul>
                                                                </li>	
                                                        </ul>
                                                        <div class="clearfix"> </div>
                                                </div>-->
                <!--notification menu end -->
                <div class="clearfix"> </div>
            </div>
            <div class="header-right">



                <div class="profile_details">		
                    <ul>
                        <li class="dropdown profile_details_drop">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <div class="profile_img">	
                                    <span class="prfil-img"><img src="images/2.jpg" alt=""> </span> 
                                    <div class="user-name">
                                        <p><%=session_home.getAttribute("name")%></p>
                                        <span>Administrator</span>
                                    </div>
                                    <i class="fa fa-angle-down lnr"></i>
                                    <i class="fa fa-angle-up lnr"></i>
                                    <div class="clearfix"></div>	
                                </div>	
                            </a>
                            <ul class="dropdown-menu drp-mnu">
                                <!--								<li> <a href="#"><i class="fa fa-cog"></i> Settings</a> </li> 
                                                                                                <li> <a href="#"><i class="fa fa-user"></i> My Account</a> </li>
                                                                                                <li> <a href="#"><i class="fa fa-suitcase"></i> Profile</a> </li> -->
                                <li> <a href="logout"><i class="fa fa-sign-out"></i> Logout</a> </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="clearfix"> </div>				
            </div>
            <div class="clearfix"> </div>	
        </div>
        <!-- //header-ends -->
        <!-- main content start-->

        <!--footer-->

        <!-- side nav js -->
        <script src='js/SidebarNav.min.js' type='text/javascript'></script>
        <script>
            $('.sidebar-menu').SidebarNav()
        </script>
        <!-- //side nav js -->

        <!-- Classie --><!-- for toggle left push menu script -->
        <script src="js/classie.js"></script>
        <script>
    var menuLeft = document.getElementById('cbp-spmenu-s1'),
            showLeftPush = document.getElementById('showLeftPush'),
            body = document.body;

    showLeftPush.onclick = function () {
        classie.toggle(this, 'active');
        classie.toggle(body, 'cbp-spmenu-push-toright');
        classie.toggle(menuLeft, 'cbp-spmenu-open');
        disableOther('showLeftPush');
    };

    function disableOther(button) {
        if (button !== 'showLeftPush') {
            classie.toggle(showLeftPush, 'disabled');
        }
    }
        </script>
        <!-- //Classie --><!-- //for toggle left push menu script -->

        <!--scrolling js-->
        <script src="js/jquery.nicescroll.js"></script>
        <script src="js/scripts.js"></script>
        <!--//scrolling js-->

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.js"></script>