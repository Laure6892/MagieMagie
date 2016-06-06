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
    </head>
    <body>

        Entrez-un pseudo et choisissez votre avatar
        <br> <br>
        <form:form modelAttribute="newJoueur" method="post">

            <label>Pseudo</label>
            <form:input path="pseudo"/>

            
            <input type="submit"/>

        </form:form>
    </body>
</html>
