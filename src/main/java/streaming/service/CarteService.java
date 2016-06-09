/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.service;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import streaming.entity.Carte;
import streaming.entity.Joueur;
import streaming.service.CarteCrudService;
import streaming.service.JoueurCrudService;

/**
 *
 * @author ajc
 */
@Service
public class CarteService {

    @Autowired
    private JoueurCrudService joueurCService;

    @Autowired
    private CarteCrudService carteCServ;

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

        carteCServ.save(carte);
    }

    public void creationCarte(long joueurId, Carte.typeCarte type) {
        // numeroCarte est spécifique à un type de carte, cela permet de créer des cartes aléatoirement
        // (contournement de la difficultée de créer une carte en focntion de son type)
        // numeroCarte est comprit entre 1 et 5

        Carte carte = new Carte();
        carte.setType(type);

        // sauvegarder la carte en BD du joueur correspondant (dans la table carte ET joueur)
        carte.setJoueur(joueurCService.findOne(joueurId));
        carte.getJoueur().getCartes().add(carte);

        carteCServ.save(carte);
    }

//    changer en id !!!! modification faite
            public void supprimerCarteConsommeesEtGestionRessourceDispoAttaquantEtCible(long idjNow, long idjCible, Carte.typeCarte typeCarte1, Carte.typeCarte typeCarte2) {
        // supprime les 2 cartes spécifiques du sort à jeter au joueur à qui c'est le tour
        // trouver les cartes du joueur à qui c'est le tour
        
        List<Carte> listeCarte1 = carteCServ.findAllByJoueurIdAndType(idjNow, typeCarte1);
        List<Carte> listeCarte2 = carteCServ.findAllByJoueurIdAndType(idjCible, typeCarte2);

        //  si le joueur n'a pas les cartes nécessaires pour lancer le sort
        if (listeCarte1.isEmpty() || listeCarte2.isEmpty()) {
            throw new RuntimeException("Vous n'avez pas les cartes nécessaires pour lancer ce sort");
        }

        // si l'adversaire n'a plus de carte/ a déjà perdu
        // liste des cartes de l'adversaire ciblé
        List<Carte> cartesAdversaire = carteCServ.findAllByJoueurId(idjCible);

        if (cartesAdversaire.isEmpty()) {
            throw new RuntimeException("Ce joueur n'a plus de cartes!! choisissez une autre victime!!!!!");
        }

        // si l'attaquant a les cartes nécessaires et que sont ennemi à encore des cartes, on supprimer la première carte de chaque liste
        for (int i = 0; i < 1; i++) {

            Carte carte1 = listeCarte1.get(i);
            Carte carte2 = listeCarte2.get(i);

            carteCServ.delete(carte1);
            carteCServ.delete(carte2);
        }
    }

    public void volerCarte(long idJVoleur, long idJPlume, int nbCVole) {

        Joueur jVoleur = joueurCService.findOne(idJVoleur);
        Joueur jPlume = joueurCService.findOne(idJPlume);
        
        
        List<Carte> cartesAdversaire = carteCServ.findAllByJoueurId(idJPlume);

        for (int i = 0; i < nbCVole; i++) {
            //decremente le nbre de carte du joueur cible

            //creer un chiffre aléatoire pour déterminer la position de la carte à supprimer au hasard chez l'adversaire
            Random r = new Random();
            // détermination de la position de la carte cible à récupérer de façon random
            int c = 1 + r.nextInt(cartesAdversaire.size());
            // récupérer la carte se situant à la position c de la liste
            Carte carteCible = cartesAdversaire.get(c);
            // dé-associer la carte au joueur plumé
            jPlume.getCartes().remove(carteCible);
            //dé-associer le joueur plumé a la carte
            carteCible.setJoueur(null);
            
            // Association de la carte au joueur voleur
            carteCible.setJoueur(jVoleur);
            jVoleur.getCartes().add(carteCible);
            
            // sauvegarder les modifications en BDD
            carteCServ.save(carteCible);
      
        }
    }

    public void donnerCarte(Joueur jNow, Joueur jCible, Carte.typeCarte typeCarteEchange) {

        // supprimer la carte a donner de la BD de l'attaquant
        Carte carteDonne = carteCServ.findOneByJoueurIdAndType(jNow.getId(), typeCarteEchange);
        // creer une carte identique en BD de l'adversaire
        Carte carte = new Carte();
        carte.setType(typeCarteEchange);

        // sauvegarder la carte en BD du joueur correspondant (dans la table carte ET joueur)
        carte.setJoueur(joueurCService.findOne(jCible.getId()));
        carte.getJoueur().getCartes().add(carte);
        int nbreCarte = jCible.getNbreCarte() + 1;
        carteCServ.save(carte);
        joueurCService.save(jCible);
        joueurCService.save(jNow);
    }

    ////////////////////TENTATIVE GESTION AUTORISATION DES SORTS
    // public int AutorisationSortInvisibilite(Joueur jNow, Joueur jCible, Carte.typeCarte typeCarte1, Carte.typeCarte typeCarte2)
    public int AutorisationSortInvisibilite(Joueur jNow) {
        // supprime les 2 cartes spécifiques du sort à jeter au joueur à qui c'est le tour
        // trouver les cartes du joueur à qui c'est le tour
        int invisibilite = 0;
        List<Carte> listeCarte1 = carteCServ.findAllByJoueurIdAndType(jNow.getId(), Carte.typeCarte.CORNE_LICORNE);
        List<Carte> listeCarte2 = carteCServ.findAllByJoueurIdAndType(jNow.getId(), Carte.typeCarte.BAVE_CRAPAUD);

        //  si le joueur n'a pas les cartes nécessaires pour lancer le sort
        if (listeCarte1.isEmpty() || listeCarte2.isEmpty()) {
            invisibilite = 1;
        }

        return invisibilite;
    }

    // public int AutorisationSortInvisibilite(Joueur jNow, Joueur jCible, Carte.typeCarte typeCarte1, Carte.typeCarte typeCarte2)
    public int AutorisationSortFiltreAmour(Joueur jNow) {
        // supprime les 2 cartes spécifiques du sort à jeter au joueur à qui c'est le tour
        // trouver les cartes du joueur à qui c'est le tour
        int invisibilite = 0;
        List<Carte> listeCarte1 = carteCServ.findAllByJoueurIdAndType(jNow.getId(), Carte.typeCarte.CORNE_LICORNE);
        List<Carte> listeCarte3 = carteCServ.findAllByJoueurIdAndType(jNow.getId(), Carte.typeCarte.SANG_VIERGE);

        //  si le joueur n'a pas les cartes nécessaires pour lancer le sort
        if (listeCarte1.isEmpty() || listeCarte3.isEmpty()) {
            invisibilite = 1;
        }

        return invisibilite;
    }

    // public int AutorisationSortInvisibilite(Joueur jNow, Joueur jCible, Carte.typeCarte typeCarte1, Carte.typeCarte typeCarte2)
    public int AutorisationSortHypnose(Joueur jNow) {
        // supprime les 2 cartes spécifiques du sort à jeter au joueur à qui c'est le tour
        // trouver les cartes du joueur à qui c'est le tour
        int invisibilite = 0;

        List<Carte> listeCarte2 = carteCServ.findAllByJoueurIdAndType(jNow.getId(), Carte.typeCarte.BAVE_CRAPAUD);
        List<Carte> listeCarte4 = carteCServ.findAllByJoueurIdAndType(jNow.getId(), Carte.typeCarte.LAPIS_LAZULI);

        //  si le joueur n'a pas les cartes nécessaires pour lancer le sort
        if (listeCarte2.isEmpty() || listeCarte4.isEmpty()) {
            invisibilite = 1;
        }

        return invisibilite;
    }

    // public int AutorisationSortInvisibilite(Joueur jNow, Joueur jCible, Carte.typeCarte typeCarte1, Carte.typeCarte typeCarte2)
    public int AutorisationSortDivination(Joueur jNow) {
        // supprime les 2 cartes spécifiques du sort à jeter au joueur à qui c'est le tour
        // trouver les cartes du joueur à qui c'est le tour
        int invisibilite = 0;

        List<Carte> listeCarte2 = carteCServ.findAllByJoueurIdAndType(jNow.getId(), Carte.typeCarte.BAVE_CRAPAUD);

        List<Carte> listeCarte4 = carteCServ.findAllByJoueurIdAndType(jNow.getId(), Carte.typeCarte.LAPIS_LAZULI);

        if (listeCarte2.isEmpty() || listeCarte4.isEmpty()) {
            invisibilite = 1;
        }
        return invisibilite;

    }

    // public int AutorisationSortInvisibilite(Joueur jNow, Joueur jCible, Carte.typeCarte typeCarte1, Carte.typeCarte typeCarte2)
    public int AutorisationSortSommeil(Joueur jNow) {
        // supprime les 2 cartes spécifiques du sort à jeter au joueur à qui c'est le tour
        // trouver les cartes du joueur à qui c'est le tour
        int invisibilite = 0;

        List<Carte> listeCarte2 = carteCServ.findAllByJoueurIdAndType(jNow.getId(), Carte.typeCarte.BAVE_CRAPAUD);
        List<Carte> listeCarte3 = carteCServ.findAllByJoueurIdAndType(jNow.getId(), Carte.typeCarte.SANG_VIERGE);

        if (listeCarte3.isEmpty() || listeCarte2.isEmpty()) {
            invisibilite = 1;
        }

        return invisibilite;
    }
}
