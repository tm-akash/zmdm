<%-- 
    Document   : welcome
    Created on : 9 Oct, 2018, 3:24:20 PM
    Author     : ZMF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jsp/header.jsp" %>
<div class="content-wrapper">

    <section class="content">
     <span class="logo-lg"><b>Welcome </b></span><span class="hidden-xs"><%=(session_home.getAttribute("name"))%></span>
    </section>
</div>
<%@include file="../jsp/footer.jsp" %>