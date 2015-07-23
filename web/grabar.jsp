<%-- 
    Document   : grabar
    Created on : Jul 20, 2015, 3:04:15 PM
    Author     : Enmanuel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grabaciones</title>
    </head>
    <body>
        <h1>Seleccione persona para grabar. Base de conocimiento</h1>
        <form action="GrabarAudio" method="POST" id="formGrabar">
            <select name="nombreSeleccionado" form="formGrabar">
                <c:forEach var="nombre" items="${nombres}">
                    <option value="${nombre}">${nombre}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Grabar" name="accion">
            <input type="submit" value="Detener" name="accion">
        </form>
        <h1>Seleccione persona para grabar. Reconocimiento</h1>
        <form action="Reconocimiento" method="POST" id="formReconocer">
            
            <input type="submit" value="Grabar" name="accion">
            <input type="submit" value="Detener" name="accion">
        </form>
    </body>
</html>
