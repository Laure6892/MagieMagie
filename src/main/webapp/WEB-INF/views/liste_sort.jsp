<%-- 
    Document   : lancer_sort
    Created on : 7 juin 2016, 10:33:47
    Author     : ajc
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        
        <c:import url="_HEAD.jsp"/>
        <script src="JS/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="JS/load.js" type="text/javascript"></script>
        <title>Jeux</title>
    </head>
    <body>
        <h1>Welcome!</h1>
    
        <div id="plateau"> Le tableau de bord </div>
        <div>
        <form:form modelAttribute="monDto" method="post" >
            Invisibilité <form:radiobutton path="numAttack" value="1"/>
            <br>
            Filtre d'amour<form:radiobutton path="numAttack" value="2"/>
            <br>
            Hypnoze <form:radiobutton path="numAttack" value="3"/>
            <br>
            Divination <form:radiobutton path="numAttack" value="4"/>
            <br>  
            Sommeil profond <form:radiobutton path="numAttack" value="5"/>
            <br> <br>

            <label>Choisissez votre ennemi</label>
            <form:select items="${listeEnnemis}" itemLabel="pseudo" itemValue="id" path="ennemi"/>
            <br>

            <label>Choisissez la carte que vous voulez échanger</label>
            <form:select items="${cartesEchangeables}" itemLabel="type" itemValue="type" path="carteEchangeable"/>
            <br>

            <input type="submit"/>
        </form:form>
        </div>
    </body>
</html>
