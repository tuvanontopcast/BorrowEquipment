<%-- 
    Document   : BorrowEquipment
    Created on : Oct 9, 2019, 1:42:39 PM
    Author     : Top
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%--jsp:include page="/BorrowEquipment"/--%>
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
                    <td><a href="/PracticeBorrow/BorrowEquipment?id=${e.id}">${(e.borrower == null)? "borrow":"borrowed"}</a></td>
                </tr>
                </c:forEach>
            </table>
        
    </body>
</html>
