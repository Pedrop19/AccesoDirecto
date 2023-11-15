<%-- 
    Document   : error
    Created on : 15 nov. 2023, 17:13:09
    Author     : pedro
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<html lang="es">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="CSS/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="vistaFinal-container">
            <h1>${error}</h1>
            <button id="botonFinal" onclick="window.location.href='${pageContext.request.contextPath}/index.html'"">Volver</button>
        </div>
    </body>
</html>