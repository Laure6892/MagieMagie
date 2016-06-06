/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import streaming.service.CarteCrudService;
import streaming.service.JoueurCrudService;

/**
 *
 * @author ajc
 */
@Service
public class CarteService {

    @Autowired
    private CarteCrudService carteCService;

    @Autowired
    private JoueurCrudService joueurCService;

    public void creationCarteAleatoire(long joueurId, int numeroCarte) {
        // numeroCarte est spécifique à un type de carte, cela permet de créer des cartes aléatoirement
        // (contournement de la difficultée de créer une carte en focntion de son type)
        // numeroCarte est comprit entre 1 et 5

        Carte carte = new Carte();

        switch (numeroCarte) {
            case 1:
               
                carte.setType(Carte.typeCarte.SANG_VIERGE);
                break;
            case 2:
                carte.setType(Carte.typeCarte.AILE_CHAUVE_SOURIS);
                break;
            case 3:
            
                carte.setType(Carte.typeCarte.CORNE_LICORNE);
                break;
            case 4:
                carte.setType(Carte.typeCarte.BAVE_CRAPAUD);
                break;

            case 5:
                carte.setType(Carte.typeCarte.LAPIS_LAZULI);
                break;
        }
        // sauvegarder la carte en BD du joueur correspondant (dans la table carte ET joueur)
        carte.setJoueur(joueurCService.findOne(joueurId));
        carte.getJoueur().getCartes().add(carte);

        carteCService.save(carte);
    }
}
