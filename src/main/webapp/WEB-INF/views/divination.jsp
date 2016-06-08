<%-- 
    Document   : divination
    Created on : 8 juin 2016, 13:48:13
    Author     : ajc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
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
              ${joueurCo.type}
            </FONT>
            </td>
      
    </div>
</c:forEach>
                                </TR>
 </TABLE>
    </CENTER>