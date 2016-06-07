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


        <h1>Bravo vous avez rejoins une partie</h1>
        <p>Voici vos adversaires, veuillez appuyer sur demarrer pour lancer la partie </p>

        <c:forEach items="${listeJoueurs}" var="unJoueur">

            <div>
                ${unJoueur.pseudo}   

                <c:if test="${unJoueur.numAvatar==1}">
                    <img src="images/sorcier1.jpg"  width="120" height="200">
                </c:if>
                <c:if test="${unJoueur.numAvatar==2}">                 
                    <img src="images/sorcier2.png"  width="120" height="200">
                </c:if>
                    <c:if test="${unJoueur.numAvatar==3}">
               <img src="images/sorcier3.png"  width="120" height="200">
                </c:if>
               <c:if test="${unJoueur.numAvatar==4}">

                    <img src="images/sorcier4.png"  width="120" height="200">
                </c:if>
                    <c:if test="${unJoueur.numAvatar==5}">            
                    <img src="images/sorcier5.png"  width="120" height="200">
                </c:if>
                    <c:if test="${unJoueur.numAvatar==6}">      
                    <img src="images/sorcier6.png"  width="120" height="200">
                </c:if>
                <c:if test="${unJoueur.numAvatar==7}">
                    <img src="images/sorcier7.png"  width="120" height="200">
                </c:if>
                <c:if test="${unJoueur.numAvatar==8}">                  
                    <img src="images/sorcier8.png"  width="120" height="200">
                </c:if>
            </div>
        </c:forEach>



    </body>
</html>