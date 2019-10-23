<%-- 
    Document   : ReturnEquipment
    Created on : Oct 17, 2019, 4:14:18 PM
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
        <h1>Borrower ${user.username}!</h1>
        <a href="/PracticeBorrow/index">BACK</a>
        <hr>${msg}<br>
            <table border="3">
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>borrow</th>
                </tr>
                <c:forEach items="${equipments}" var="e">
                <tr> 
                    <td>${e.id}</td>
                    <td>${e.name}</td>
                    <td><a href="/PracticeBorrow/ReturnEquipment?id=${e.id}">${(e.borrower == null)? "":"return"}</a></td>
                </tr>
                </c:forEach>
            </table>
        
    </body>
</html>
