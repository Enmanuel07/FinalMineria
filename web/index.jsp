<%-- 
    Document   : index
    Created on : Jul 20, 2015, 11:29:44 AM
    Author     : Enmanuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Proyecto final Minería de datos. Reconocimiento del hablante</h1>
        <form action="./Configurando" method="POST">
            Cantidad de personas a grabar
            <input type="text" name="cantPersonas">
            <input type="submit" value="Enviar">
            <input type="hidden" name="formCantPersonas" value="formCantPersonas">
        </form>
        <a href="./GrabarAudio?accion=audios">Grabar audios</a>
    </body>
</html>
