function chargerPlateau()
{
    $('#plateau').load('ajax_plateau');
    $('#plateau2').load('ajax_plateau2');
}

$( function(){
    
    setInterval(chargerPlateau, 2000);
} );