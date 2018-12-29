<%@include file="../jsp/bootstrapCSS.jsp" %>
<script>
    (function (global) {

        if (typeof (global) === "undefined") {
            throw new Error("window is undefined");
        }

        var _hash = "!";
        var noBackPlease = function () {
            global.location.href += "#";

            // making sure we have the fruit available for juice (^__^)
            global.setTimeout(function () {
                global.location.href += "!";
            }, 50);
        };

        global.onhashchange = function () {
            if (global.location.hash !== _hash) {
                global.location.hash = _hash;
            }
        };

        global.onload = function () {
            noBackPlease();

            // disables backspace on page except on input fields and textarea..
            document.body.onkeydown = function (e) {
                var elm = e.target.nodeName.toLowerCase();
                if (e.which === 8 && (elm !== 'input' && elm !== 'textarea')) {
                    e.preventDefault();
                }
                // stopping event bubbling up the DOM tree..
                e.stopPropagation();
            };
        }

    })(window);
</script>

<body class="">
    <div class="main-content">

        <!-- main content start-->
        <div id="page-wrapper">
            <div class="main-page login-page ">
                <h2 class="title1">Login</h2>
                <div class="widget-shadow">
                    <div class="login-body">
                        <div style="color: red; text-align: center">${message}</div>
                        <form action="checkLogin" method="post">
                            <input type="email" class="user" name="email" placeholder="Enter Your Email" required="">
                            <input type="password" name="password" class="lock" placeholder="Password" required="">
                            <div class="forgot-grid">
                                <!--<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Remember me</label>-->
                                <div class="forgot">
                                    <!--<a href="#">forgot password?</a>-->
                                </div>
                                <div class="clearfix"> </div>
                            </div>
                            <input type="submit" name="Sign In" value="Sign In">
                            <!--							<div class="registration">
                                                                                            Don't have an account ?
                                                                                            <a class="" href="signup.html">
                                                                                                    Create an account
                                                                                            </a>
                                                                                    </div>-->
                        </form>
                    </div>
                </div>

            </div>
        </div>
        <!--footer-->
        <div class="footer">
            <p>&copy; 2018 All Rights Reserved | Design by <a href="#" target="_blank">Zero Mass Pvt Ltd.</a></p>
        </div>
        <!--//footer-->
    </div>

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
    <!-- //Bootstrap Core JavaScript -->

</body>
</html>