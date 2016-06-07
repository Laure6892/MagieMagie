<%-- 
    Document   : exo2
    Created on : 7 juin 2016, 15:27:48
    Author     : ajc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="JS/jquery-2.2.4.js" type="text/javascript"></script>
        methode de load
        <script lang="javascript">
            function loadPage() {
//                $('#sortilege').load("ajax_sort.jsp");
                $('#sortilege').load("ajax_sort.jsp");
//                renvoyer vers la page du formulaire du lancement de jeux
            }
        </script>
    </head>
    <body>
        créer un div nommé "sorts" avec un contenu ou non
        créer un bouton lancer sort, quand clic dessus, fait un load vers une page "ajax_sort.jsp" qui contient par exemple un texte
        <br><br>
        <input type="button" id="buttonSort" value="Lancer un sort" onclick="loadPage()"/>
        <div id="sortilege">hufdffhnhjj</div>

    </body>
</html>
