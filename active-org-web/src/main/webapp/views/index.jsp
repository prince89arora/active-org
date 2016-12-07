<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <html>
 <head>
    <title>${title}</title>
    <%@include file="common/head.jsp" %>
 </head>
 <body class="claro">
    <div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="gutters:true, liveSplitters:false"
    id="container">

         <!-- Main Toolbar -->
         <%@include file="common/toolbar.jsp" %>


         <%@include file="common/leftPane.jsp" %>

         <div data-dojo-type="dijit/layout/ContentPane"
            data-dojo-props="splitter:true, region:'center'" id="main-container">

          </div>

         <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="splitter:true, region:'right'">Chat Section</div>
         <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'bottom'">Bottom pane</div>

    </div>

    <%@include file="common/login.jsp" %>
 </body>
 </html>
