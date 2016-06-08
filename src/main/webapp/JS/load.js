function chargerPlateau()
{
    $('#plateau').load('ajax_plateau');
    $('#plateau2').load('ajax_plateau2');
}

 function callback()
{setTimeout(chargerPlateau,2000);}

chargerPlateau();


   
