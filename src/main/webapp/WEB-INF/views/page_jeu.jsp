<%-- 
    Document   : _TEMPLATEAJAX
    Created on : 6 juin 2016, 16:04:59
    Author     : ajc
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

        <c:import url="_HEAD.jsp"/>
        <script src="JS/jquery-2.2.4.js" type="text/javascript"></script>
        <script src="JS/load.js" type="text/javascript"></script>
        <script lang="javascript" >
            function loadPage()
            {
                $('#sortilege').load("lister_sort2");
            }
        </script>
        <title>Jeux</title>
    </head>
    <body>
        <h1>Welcome!</h1>

        <div id="plateau"> Le tableau de bord </div>


        <h2>Que voulez vous faire Jeune sorcier?</h2>
<!--        <a href="<c:url value="/sort"/>">Lancer Sort</a><br>-->
        <input type="button" id="buttonSort" value="Lancer un sort" onclick="loadPage()"/>
       
        <div id="sortilege"> Essai </div>
        <a href="#">Passer mon tour</a><br>


    </body>
</html>
