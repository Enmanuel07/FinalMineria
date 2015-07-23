<%-- 
    Document   : nombrePersonas
    Created on : Jul 20, 2015, 12:47:50 PM
    Author     : Enmanuel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <!-- Latest compiled JavaScript -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h1>Nombres</h1>
        <form action="Configurando" method="POST">
            <c:forEach var="i" begin="1" end="${cantPersonas}">
                <div class="form-group">
                    <label>Persona#${i}</label>
                    <input type="text" name="Persona#${i}">
                <div>
            </c:forEach>
            <input type="submit" value="enviar"> 
            <input type="hidden" name="formCantPersonas" value="formCantNombres">
            <input type="hidden" name="cantPersonas" value="${cantPersonas}">
        </form>
    </body>
</html>
