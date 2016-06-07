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
            <c:import url="_TABLEAUDEBORD.jsp"/>
       
        </div>
        <div class="contenu">
            
    <c:forEach items="${listeJoueurs}" var="unJoueur">
    
        <div>
             ${unJoueur.pseudo}   
             ${unJoueur.nbreCarte}         
            ${unJoueur.numAvatar}
         
        </div>
    </c:forEach>
        </div>
               
        <div class="menuJeux">
            <c:import url="_MENUJEUX.jsp"/>    
        </div>
    </body>
</html>