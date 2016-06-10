
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<H1> BONJOUR ${joueurActuel.pseudo}  </H1>
<h1>Adversaires statuts actuels</h1>

<TABLE >
    <div id="plateau">
        <TR>
            <c:forEach items="${listeJoueurs}" var="joueurCo">
            <div>
                <td>  <FONT face="Verdana" color="#00008B" size="5">${joueurCo.pseudo}
                    <br> 

                    <img src="images/sorcier${joueurCo.numAvatar}.png"  width="150" height="240">

                    <br>
                    Nombre de cartes :${joueurCo.nbreCarte}
                    At'il la main? :${joueurCo.main}
                    </FONT>
                </td>

            </div>
        </c:forEach>
        </TR>
</TABLE>
</CENTER>
<h1>Mes Cartes Disponibles</h1>
<c:forEach items="${listeCarte}" var="maCarte">

    <c:if test="${maCarte.type=='SANG_VIERGE'}">                  
        <img src="images/sang.png"  width="120" height="200">
    </c:if>
    <c:if test="${maCarte.type=='CORNE_LICORNE'}">                  
        <img src="images/cornes.png"  width="120" height="200">
    </c:if>
    <c:if test="${maCarte.type=='BAVE_CRAPAUD'}">                  
        <img src="images/bave.png"  width="120" height="200">
    </c:if>
    <c:if test="${maCarte.type=='LAPIS_LAZULI'}">                  
        <img src="images/lapis.png"  width="120" height="200">
    </c:if>

    <c:if test="${maCarte.type=='AILE_CHAUVE_SOURIS'}">                  
        <img src="images/chauve.png"  width="120" height="200">
    </c:if>
</c:forEach>
<h2>Que voulez vous faire Jeune sorcier?</h2>
***valeur de la main ${mainJoueurActuel}***
<c:if test="${mainJoueurActuel == 1}"> 
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
            Carte à échanger <form:select items="${listeCarte}" itemLabel="type" itemValue="id" path="idCarteEchangeable"/>
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

    <div id="sortilege"> * </div>
    <INPUT type="button" onclick="$.get('<c:url value="/finJeux"/>')" value="Vider les tables"/>

    <INPUT type="button" onclick="$.get('<c:url value="/ajax_piocher"/>')" value="Piocher 2 cartes"/>

</c:if>

<c:if test="${mainJoueurActuel ==0}"> 
    Ce n'est pas ton tour
</c:if>


