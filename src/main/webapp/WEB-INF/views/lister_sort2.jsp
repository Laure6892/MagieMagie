<%-- 
    Document   : lister_sort2
    Created on : 7 juin 2016, 16:54:23
    Author     : ajc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 
       <form:form modelAttribute="monDto" method="post" >
            <c:if test="${invisibilite==0}">  
            Invisibilité <form:radiobutton path="numAttack" value="1"/>
               </c:if>
            <br>
               <c:if test="${amour==0}">  
            Filtre d'amour<form:radiobutton path="numAttack" value="2"/>
                 </c:if>
            <br>
            <c:if test="${hypnose==0}">  
            Hypnoze <form:radiobutton path="numAttack" value="3"/>
               </c:if>
            <br>
            <c:if test="${divination==0}">  
            Divination <form:radiobutton path="numAttack" value="4"/>
               </c:if>
            <br>  
            <c:if test="${sommeil==0}">  
            Sommeil profond <form:radiobutton path="numAttack" value="5"/>
               </c:if>
            <br> <br>
             <label>Choisissez votre ennemi</label>
            <form:select items="${listeEnnemis}" itemLabel="pseudo" itemValue="pseudo" path="ennemi"/>
            <br>

         
          <input type="button" onclick= "$.post('lancer_sort', $('form').serialize())" value="Lancer le sort" />
        </form:form>