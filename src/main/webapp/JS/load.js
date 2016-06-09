////////////////////////////////////////////////////////////////////////////////////////
// // première méthode qui rafraichit le menu avant de lancer le jeu
// et le menu du jeu contenant le nombre de carte des joueurs
////////////////////////////////////////////////////////////////////////////////////////
function chargerPlateau()
{
    $('#plateau').load('ajax_plateau');
    $('#plateau2').load('ajax_plateau2');

}

// Appelé qd la page est complètement chargée
// Recharge à intervalle régulier les blocs 
$(function () {
    callback();
    $('#plateau2').load('ajax_plateau2');
});

function callback()
{
    setTimeout(chargerPlateau, 100000);
}

// appel la fonction chargerPlateau pour la lancer
chargerPlateau();

////////////////////////////////////////////////////////////////////////////////////////
// deuxième méthode qui permet de rafraichir  l'affichage des sorts
////////////////////////////////////////////////////////////////////////////////////////
function chargerPlateau2()
{
    $('#sortilege').load('lister_sort2');
}

$(function () {

//    setInterval(chargerPlateau, 10000);
    callback2();
});
function callback2()
{
    setTimeout(chargerPlateau2, 10000);
}
chargerPlateau2();