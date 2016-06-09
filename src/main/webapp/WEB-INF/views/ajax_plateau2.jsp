<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Bravo vous avez rejoint une partie</h1>
<p>Voici vos adversaires, veuillez appuyer sur demarrer pour lancer la partie </p>
<H1>Adversaires</H1>
<div id="plateau">
    <table align="center">
        
        <c:if test="${partiEnCours ==0}"> 
            <a href="<c:url value="/page_jeu"/>">GO</a>
        </c:if>
        <c:if test="${partiEnCours ==1}">
            !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        </c:if>
            
        <tr>
            <td align="center">
                Vous etes   ${joueurActuel.pseudo}
            </td>
        </tr>
        <tr>
            <td align="center">
                <img src="images/sorcier${joueurActuel.numAvatar}.png"  width="120" height="200">
            </td>
        </tr>
    </table>
    <br>
    
    <table align="center">
        <tr>
            <c:forEach items="${listeJoueurs}" var="unJoueur">
                <td  align="center">${unJoueur.pseudo}
                    <br>
                   <img src="images/sorcier${unJoueur.numAvatar}.png"  width="120" height="200">
                </td>
            </c:forEach>
        </tr>
    </table>
</div>
