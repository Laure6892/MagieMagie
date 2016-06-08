<%-- 
    Document   : lister_sort2
    Created on : 7 juin 2016, 16:54:23
    Author     : ajc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


 
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
            <form:select items="${listeEnnemis}" itemLabel="pseudo" itemValue="pseudo" path="ennemi"/>
            <br>

         
          <input type="button" onclick= "$.post('lancer_sort', $('form').serialize())" value="Lancer le sort" />
        </form:form>