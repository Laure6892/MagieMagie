/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import streaming.service.CarteCrudService;
import streaming.service.CarteService;
import streaming.service.JoueurCrudService;

/**
 *
 * @author ajc
 */
@Service
public class JoueurService {
    
  @Autowired
    private CarteCrudService carteCServ;

    @Autowired
    private CarteService carteServ;

    @Autowired
    private JoueurCrudService joueurCServ;
    
    public void joueurSuivant(Joueur jNow)
    {
      // si le sort a fonctionné il faut passer la main au joueur suivant
        // passer à false la valeur de la main pour le joueur actuel
        jNow.setMain(false);
        // sauvegarde les modif
        joueurCServ.save(jNow);
        // passer la main au joueur dont l'ordre est le suivant
        // trouver le joueur dont ordre = ordre joueurNow + 1

      //  Joueur joueurSuivant = joueurCServ.findOneByOrdre(jNow.getOrdre() + 1);
        
        int i=jNow.getOrdre();
        Joueur joueurSuivant=joueurCServ.findOneByOrdre(i+1);
        
        // si il existe mais il reste que 1 ou 0 carte, il ne peut pas jouer
        //tant qu'aucun joueur suivant n'a plus de carte on continue jusqu'à un joueur avec des cartes
         while (joueurSuivant.getNbreCarte()<2)
         {  ++i;
     
            joueurSuivant=joueurCServ.findOneByOrdre(i+1);
         }
        // si joueurSuivant est null, on repasse la main au joueur d'ordre 1
        if (joueurSuivant == null) {
            // trouver le joueur avec ordre 1
            joueurSuivant = joueurCServ.findOneByOrdre(1);
        }

        
        joueurSuivant.setMain(true);
        // sauvegarder les modif
        joueurCServ.save(joueurSuivant);
    
}
}
