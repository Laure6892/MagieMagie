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

            image avatar 1 <form:radiobutton path="numAvatar" value="1"/>
            <br>
            image avatar 2 <form:radiobutton path="numAvatar" value="2"/>
            <br>
            image avatar 3 <form:radiobutton path="numAvatar" value="3"/>
            <br>
            image avatar 4 <form:radiobutton path="numAvatar" value="4"/>
            <br>
            image avatar 5 <form:radiobutton path="numAvatar" value="5"/>
            <br>
            image avatar 6 <form:radiobutton path="numAvatar" value="6"/>
            <br>
            image avatar 7 <form:radiobutton path="numAvatar" value="7"/>
            <br>
            image avatar 8 <form:radiobutton path="numAvatar" value="8"/>
            <br>
            
            <input type="submit"/>

        </form:form>
    </body>
</html>
