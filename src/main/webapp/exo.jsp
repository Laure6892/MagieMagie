<%-- 
    Document   : exo
    Created on : 7 juin 2016, 14:51:22
    Author     : ajc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


        jquery necessaire pour avoir acces au $

        <script src="JS/jquery-2.2.4.js" type="text/javascript"></script>


        <script lang="javascript">
            function logging(msg) {
                alert('Logging :' + msg);
            }
            function modifTitre() {
                $('#titre').html('texte de remplacement');
            }
            function modifTitre1() {

                $('#titre').html($('#texte').val());
            }
            .val permet de récupérer la valeur rensaignée dans #text
        </script>


    </head>
    <body>

        <h1 onclick="logging('message a afficher')">Hello World!</h1>
        nouveau titre avec input = text, en cliquant sur le bouton du bas, on prend la valeur du text et met dans titre
        <br>
        utiliser val() comme .html ou . load
        <br><br>
        Nouveau titre <input type="text" id="texte">
        <br><br>

        test pour modofier un titre dans une page, utilisez une fonction défini dans head, prendre une fonction html
        <br>
        <input type="button" id='bouton' value="Modifier titre" onclick="modifTitre1()"/>
        <div id="titre">**** titre </div>



    </body>
</html>
