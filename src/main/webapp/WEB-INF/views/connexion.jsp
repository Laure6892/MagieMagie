<%-- 
    Document   : connexion
    Created on : 6 juin 2016, 10:11:51
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
        <script src="<c:url value="/JS/jquery-2.2.4.js"/>" type="text/javascript"></script>
    </head>
    <body>

        Entrez-un pseudo et choisissez votre avatar
        <br> <br>

        <c:set var="maroute"><c:url value="/connexion"/></c:set>

        <form:form modelAttribute="newJoueur" method="post" action="${maroute}">

            <label>Pseudo</label>
            <form:input path="pseudo"/>

            <input type="submit"/>
        <center> 
            <TABLE BORDER >
                <TR>
                    <TD>  <img src="images/sorcier1.png"  width="120" height="200"></TD> 
                    <TD>  <img src="images/sorcier2.png"  width="120" height="200"></TD> 
                    <TD>  <img src="images/sorcier3.png"  width="120" height="200"></TD> 
                    <TD>  <img src="images/sorcier4.png"  width="120" height="200"></TD> 
                    <TD>  <img src="images/sorcier5.png"  width="120" height="200"></TD> 
                    <TD>  <img src="images/sorcier6.png"  width="120" height="200"></TD> 
                    <TD>  <img src="images/sorcier7.png"  width="120" height="200"></TD> 
                    <TD>  <img src="images/sorcier8.png"  width="120" height="200"></TD> 
                </TR>

                <TR>
                    <TD >    <form:radiobutton path="numAvatar" value="1"/></TD> 
                    <TD>  <form:radiobutton path="numAvatar" value="2"/></TD> 
                    <TD>   <form:radiobutton path="numAvatar" value="3"/></TD> 
                    <TD>   <form:radiobutton path="numAvatar" value="4"/></TD> 
                    <TD>    <form:radiobutton path="numAvatar" value="5"/></TD> 
                    <TD>    <form:radiobutton path="numAvatar" value="6"/></TD> 
                    <TD>   <form:radiobutton path="numAvatar" value="7"/></TD>
                    <TD>   <form:radiobutton path="numAvatar" value="8"/></TD> 


                </TR>
            </TABLE>
        </center> 
    </form:form>
    <INPUT type="button" onclick="$.get('<c:url value="/finJeux"/>')" value="Vider les tables"/>
</body>
</html>
