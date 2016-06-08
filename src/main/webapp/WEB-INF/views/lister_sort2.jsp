<%-- 
    Document   : lister_sort2
    Created on : 7 juin 2016, 16:54:23
    Author     : ajc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <input type="button" id="buttonSort" value="Invisibilite" onclick="loadPage()"/>
    ${joueurActuel.pseudo}
    Cible 
    <c:forEach items="${listeJoueurs}" var="joueurCo">
        ${joueurCo.pseudo}
        <input type="button" id="buttonSort" value="${joueurCo.pseudo}" /><br>
    </foreach>
 



