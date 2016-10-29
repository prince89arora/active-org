<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <html>
 <head>
    <title>${httpStatus}</title>
    <%@include file="common/head.jsp" %>
 </head>
 <body>
    <h1>${httpStatus} Error</h1>
    <p>${message} </p>
 </body>
 </html>