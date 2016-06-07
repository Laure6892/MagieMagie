
function chargerPlateau()
{$('#plateau').load('plateau',callback); }

 function callback()
{setTimeout(chargerPlateau,2000);}

chargerPlateau();