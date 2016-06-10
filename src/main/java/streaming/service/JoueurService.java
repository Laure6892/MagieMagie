/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import streaming.entity.Joueur;
import streaming.entity.Joueur_;

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

    public void joueurSuivant() {
        // récupère le joueur qui a la main et qui la passe au joueur suivant
        Joueur jActuel = joueurCServ.findOneByMain(1);
        // stocker l'ordre sont ordre pour gérer l'entré/sortie de la boucle while
        int ordreJInitial = jActuel.getOrdre();
        // passer la valeur de la main pour le joueur actuel à 0 = plus personne n'a la main
        // jusqu'à ce qu'on trouve joueur existant pouvant jouer
        jActuel.setMain(0);
        // sauvegarde les modif
        joueurCServ.save(jActuel);

        // début de la boucle while
        do {
            // passer la main au joueur dont l'ordre est le suivant
            // trouver le joueur dont ordre = ordre joueurNow + 1

            jActuel = joueurCServ.findOneByOrdre(jActuel.getOrdre() +1);

            // si joueurSuivant est null, on repasse la main au joueur d'ordre 1
            if (jActuel == null) {
                // trouver le joueur avec ordre 1
                jActuel = joueurCServ.findOneByOrdre(1);
            }

            //  si le jActuel possède au moin 1 carte
            if ( carteCServ.findAllByJoueurId(jActuel.getId()).size() >= 1) {

                // Décrémente pénalité so pénalité
                if (jActuel.getTourSommeilProfond() > 0){
                    
                    jActuel.setTourSommeilProfond(jActuel.getTourSommeilProfond()-1);
                    joueurCServ.save(jActuel);
                }else{
                    
                    
                    jActuel.setMain(1);
                    joueurCServ.save(jActuel);
                    return;
                }
            }
        } while (jActuel.getOrdre() != ordreJInitial || joueurCServ.countByTourSommeilProfondGreaterThan(0) >= 1);

        // Partie terminée
        throw new RuntimeException("PARTIE TERMINEE");
    }
}
