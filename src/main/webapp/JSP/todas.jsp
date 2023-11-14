<%-- 
    Document   : todas
    Created on : 14 nov. 2023, 18:29:01
    Author     : Pedro Lazaro
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html lang="es">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver una</title>
    </head>
    <body>
        <h1>Información del ave seleccionada</h1>
        <table>
            <tr>
                <th>Anilla</th>
                <th>Especie</th>
                <th>Lugar</th>
                <th>Fecha</th>
            <tr>
            <c:forEach var="ave" items="${aves}">
            <tr>
                <td>${ave.anilla}</td>
                <td>${ave.especie}</td>
                <td>${ave.lugar}</td>
                <td>${ave.fecha}</td>
            </tr>
            </c:forEach>
    </body>
</html>
