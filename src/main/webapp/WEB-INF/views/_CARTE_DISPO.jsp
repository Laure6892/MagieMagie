<%-- 
    Document   : _CARTE_DISPO
    Created on : 6 juin 2016, 12:26:21
    Author     : ajc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cartes Disponibles</h1>
         <c:forEach items="${listeCarte}" var="uneCarte">
           <div>
               ${uneCarte.typeCarte}
           </div>
         </c:foreach>
    </body>
</html>
