<%-- 
    Document   : _TEMPLATE
    Created on : 6 juin 2016, 09:31:54
    Author     : ajc
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="_HEAD.jsp"/>
    </head>
    <body>
        <div class="cartes">
            <c:import url="_CARTE_DISPO.jsp"/>
        </div>
        <div class="adversaires">
            <c:import url="_ADVERSAIRE.jsp"/>
        </div>
        <div class="contenu">
        </div>
        <div class="menuJeux">
            <c:import url="_MENUJEUX.jsp"/>    </div>
    </body>
</html>