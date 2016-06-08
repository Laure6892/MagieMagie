<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<H1> BONJOUR ${joueurActuel.pseudo}  </H1>
<h1>Adversaires statuts actuels</h1>

<TABLE >
    <div id="plateau">
                <TR>
<c:forEach items="${listeJoueurs}" var="joueurCo">
    <div>
        <td>  <FONT face="Verdana" color="#00008B" size="5">${joueurCo.pseudo}
            <br> 
         <c:if test="${joueurCo.numAvatar==1}">
            <img src="images/sorcier1.jpg"  width="150" height="240">
        </c:if>
        <c:if test="${joueurCo.numAvatar==2}">                 
            <img src="images/sorcier2.png"  width="150" height="240">
        </c:if>
        <c:if test="${joueurCo.numAvatar==3}">
            <img src="images/sorcier3.png"  width="150" height="240">
        </c:if>
        <c:if test="${joueurCo.numAvatar==4}">

            <img src="images/sorcier4.png"  width="150" height="240">
        </c:if>
        <c:if test="${joueurCo.numAvatar==5}">            
            <img src="images/sorcier5.png"  width="150" height="240">
        </c:if>
        <c:if test="${joueurCo.numAvatar==6}">      
            <img src="images/sorcier6.png"  width="150" height="240">
        </c:if>
        <c:if test="${joueurActuel.numAvatar==7}">
            <img src="images/sorcier7.png"  width="150" height="240">
        </c:if>
        <c:if test="${joueurActuel.numAvatar==8}">                  
            <img src="images/sorcier8.png"  width="150" height="240">
        </c:if>
            <br>
              Nombre de cartes :${joueurCo.nbreCarte}
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
