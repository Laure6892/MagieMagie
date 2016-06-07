<%-- 
    Document   : lancer_sort
    Created on : 7 juin 2016, 10:33:47
    Author     : ajc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

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

    </body>
</html>
