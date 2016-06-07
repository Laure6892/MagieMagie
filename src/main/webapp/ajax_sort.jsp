<%-- 
    Document   : ajax_sort
    Created on : 7 juin 2016, 15:38:18
    Author     : ajc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JS/jquery-2.2.4.js" type="text/javascript"></script>
        <script lang="javascript">
            function lancerSortInvisibiliteOrFiltreAmour(type) {
                // utiliser la commande suivante pour oriente le system vers la contoller
                if (type == 1)
                {
//                    $.get('/sort');
                    alert('Invisibilite');
                }

                if (type == 2)
                {
                    alert('Filtre amour');
                }
            }
        </script>

    </head>
    <body>
        <input type="button" id="sortilege" value="Invisibilité"  onclick="lancerSortInvisibiliteOrFiltreAmour(1)">
        <br>
        <input type="button"   value="Filtre d'amour" onclick="lancerSortInvisibiliteOrFiltreAmour(2)">
        <br>
        <input type="button" value="Hypnose">
        <br>
        <input type="button" value="Divination">
        <br>
        <input type="button" value="Sommeil profond">
    </body>
</html>
