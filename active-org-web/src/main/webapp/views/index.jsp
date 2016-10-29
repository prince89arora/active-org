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

         <div id="mainToolbar" dojoType="dijit.Toolbar" data-dojo-props="region:'top'">
            <div data-dojo-type="dijit/form/ToggleButton" id="mainToolbar.login"
            data-dojo-props="iconClass:'Pan', showLabel:true" style="float:right;">Login</div>
         </div>
         <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'leading'">Leading pane</div>
         <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'center'">Center pane</div>
         <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'bottom'">Bottom pane</div>

    </div>
 </body>
 </html>