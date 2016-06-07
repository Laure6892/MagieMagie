<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Bravo vous avez rejoins une partie</h1>
        <p>Voici vos adversaires, veuillez appuyer sur demarrer pour lancer la partie </p>
        <H1>Adversaires</H1>
    <CENTER>
<TABLE BORDER >
    <div id="plateau">
                <TR>
        <c:forEach items="${listeJoueurs}" var="unJoueur">

            <div>
                <td> ${unJoueur.pseudo}   </td>
            
           
                
            
                <td>
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
                </td>
                
            
               

        </c:forEach>
                 </TR>
 </TABLE>
    </CENTER>
    
    <CENTER>    <h1 >Vous etes   ${joueurActuel.pseudo} </h1> </center>
    <CENTER>
     <c:if test="${joueurActuel.numAvatar==1}">
                    <img src="images/sorcier1.jpg"  width="120" height="200">
                </c:if>
                <c:if test="${joueurActuel.numAvatar==2}">                 
                    <img src="images/sorcier2.png"  width="120" height="200">
                </c:if>
                    <c:if test="${joueurActuel.numAvatar==3}">
               <img src="images/sorcier3.png"  width="120" height="200">
                </c:if>
               <c:if test="${joueurActuel.numAvatar==4}">

                    <img src="images/sorcier4.png"  width="120" height="200">
                </c:if>
                    <c:if test="${joueurActuel.numAvatar==5}">            
                    <img src="images/sorcier5.png"  width="120" height="200">
                </c:if>
                    <c:if test="${joueurActuel.numAvatar==6}">      
                    <img src="images/sorcier6.png"  width="120" height="200">
                </c:if>
                <c:if test="${joueurActuel.numAvatar==7}">
                    <img src="images/sorcier7.png"  width="120" height="200">
                </c:if>
                <c:if test="${joueurActuel.numAvatar==8}">                  
                    <img src="images/sorcier8.png"  width="120" height="200">
                </c:if>
                    <br>
                     <a href="<c:url value="/page_jeu"/>">GO</a>
    </CENTER>
    </div>
