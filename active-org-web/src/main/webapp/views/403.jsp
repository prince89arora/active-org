<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <html>
 <head>
    <title>${title}</title>
    <%@include file="common/head.jsp" %>
 </head>
 <body class="soria">
    <div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="gutters:true, liveSplitters:false"
    id="container">
        <h2>You are not authorized to view this page.</h2>
    </div>

    <%@include file="common/login.jsp" %>
 </body>
 </html>