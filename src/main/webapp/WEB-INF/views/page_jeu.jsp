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
        <script src="<c:url value="/JS/jquery-2.2.4.js"/>" type="text/javascript"></script>
        <!--<script src="<c:url value="/JS/load.js"/>" type="text/javascript"></script>-->
        <script lang="javascript" >
// rafraichir  le menu des sorts
            function chargerPlateau() 
            {
                $('#plateau').load('ajax_plateau');
            }

            $(function () {

                setInterval(chargerPlateau, 7000);
                chargerPlateau() ;
            });
            

        </script>
        <title>Jeux</title>
    </head>
    <body>
        <h1>Welcome!</h1>

        <div id="plateau"> Le tableau de bord </div>
    </body>
</html>
