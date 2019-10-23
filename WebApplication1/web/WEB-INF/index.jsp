<%-- 
    Document   : index
    Created on : Oct 8, 2019, 11:38:30 PM
    Author     : Top
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ${user.username}!</h1>
        <a href="/PracticeBorrow/BorrowEquipment">BORROW</a><br>
        <a href="/PracticeBorrow/ReturnEquipment">Return</a>
        <form action="Logout" method="post"><input type="submit" value="Logout"></form><br>
        <hr>
        
    </body>
</html>
