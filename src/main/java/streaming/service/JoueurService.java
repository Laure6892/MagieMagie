/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import streaming.entity.Joueur;

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
    
    @Transactional
    public void joueurSuivant()
    {
        
      Joueur jNow = joueurCServ.findOneByMain(1);
      // si le sort a fonctionné il faut passer la main au joueur suivant
        // passer à false la valeur de la main pour le joueur actuel
        jNow.setMain(0);
        // sauvegarde les modif
        joueurCServ.save(jNow);
        // passer la main au joueur dont l'ordre est le suivant
        // trouver le joueur dont ordre = ordre joueurNow + 1

      //  Joueur joueurSuivant = joueurCServ.findOneByOrdre(jNow.getOrdre() + 1);
        
        int ordreJoueurActuel =jNow.getOrdre();
        Joueur joueurSuivant=joueurCServ.findOneByOrdre(ordreJoueurActuel+1);
        
         // si joueurSuivant est null, on repasse la main au joueur d'ordre 1
        if (joueurSuivant == null) {
            // trouver le joueur avec ordre 1
            joueurSuivant = joueurCServ.findOneByOrdre(1);
        }
        
        // si il existe mais il reste que 1 ou 0 carte, il ne peut pas jouer
        //tant qu'aucun joueur suivant n'a plus de carte on continue jusqu'à un joueur avec des cartes
        // > Si joueurSuivant < 2 cartes => choisit le 1er joureur (dans l'ordre) qui en possède au moins 2
        // si aucun joueur n'a assez de cartes = throw exception
        
//        if( joueurCServ.countJoueursHavingMoreThanOneCard()<=0 )
//            throw new RuntimeException("Partie terminée - personne ne possède 2 cartes");
//        
//         while (joueurSuivant.getNbreCarte()<2)
//         {  
//            joueurSuivant=joueurCServ.findOneByOrdre(joueurSuivant.getOrdre()+1);
//            if (joueurSuivant == null) {
//                // trouver le joueur avec ordre 1
//                joueurSuivant = joueurCServ.findOneByOrdre(1);
//            }
//         }
        // si joueurSuivant est null, on repasse la main au joueur d'ordre 1

        
        joueurSuivant.setMain(1);
        // sauvegarder les modif
        joueurCServ.save(joueurSuivant);
    
}
}
