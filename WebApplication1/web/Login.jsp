<%-- 
    Document   : Login
    Created on : Oct 8, 2019, 11:16:27 PM
    Author     : Top
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="Login" method="post">
            user: <input type="text" name="username"><br>
            pass: <input type="text" name="password"><br>
            <input type="submit">
            ${msg}
        </form>
    </body>
</html>
