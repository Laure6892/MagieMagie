/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.controller;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import streaming.entity.Carte;
import streaming.service.CarteService;
import streaming.entity.Joueur;
import streaming.service.CarteCrudService;

/**
 *
 * @author ajc
 */
@Controller
public class CarteController {

    @Autowired
    private CarteCrudService carteCServ;

    @Autowired
    private CarteService carteServ;

    //methode des sorts
    @RequestMapping(method = RequestMethod.GET, value = "/sort")
    public String sortilegePost(Model model,int numAttack, Joueur jCible, Joueur jNow) {
        
        
        // liste des joueurs pour choisir la victime (en excluant le jour à qui c'est le tour) a mettre dans un model
//        List<Joueur> listeJoueurs;
//        model.addAttribute("JoueursCo", listeJoueurs);
        
        // vers jsp sort?
         return "";
    }
    
//    @RequestMapping(method = RequestMethod.POST, value = "/sort")
//    public String sortilegeGet(Model model,int numAttack, Joueur jCible, Joueur jNow) {
//
//        int nbCVole = 0;
//        int nbCDonne = 0;
//        
//        // numAttack permet d'identifier le sort sélectionné par le joueur
//        // a gérer au niveau de la jsp et du controller GET/POST methodes
//        switch (numAttack) {
//
//            //########################################################################
//            // INVISIBILITE
//            // need : corne de licorne + bave de crapaud
//            // effets : vole une carte au hasard chez un adversaire
//            // NON VERIFIEE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//            //########################################################################
//            case 1:
//
//                // supprimer les cartes consommées pour le sort après avoir vérifier que le joueur avait bien les cartes nécessaires
//                // et que l'adversaire sélectionné possède encore des cartes
//                carteServ.supprimerCarteConsommeesEtGestionRessourceDispoAttaquantEtCible(jNow, jCible, Carte.typeCarte.BAVE_CRAPAUD, Carte.typeCarte.CORNE_LICORNE);
//
//                // nombre de cartes à supprimer chez l'adversaire/creer chez l'attaquant
//                nbCVole = 1;
//                
//                // vole de carte
//                carteServ.volerCarte(jNow, jCible, nbCVole);
//                
//            //########################################################################
//            //FILTRE AMOUR
//            // need : licorne et sang de vierge
//            // effet : vole la moitier des cartes de l'adversaire
//            // conditions: si l'ennemi n'à qu'1 carte il perd, on prend le nombre entire supérieur
//            //########################################################################  
//            case 2:
//
//                // supprimer les cartes consommées pour le sort après avoir vérifier que le joueur avait bien les cartes nécessaires
//                // et que l'adversaire sélectionné possède encore des cartes
//                carteServ.supprimerCarteConsommeesEtGestionRessourceDispoAttaquantEtCible(jNow, jCible, Carte.typeCarte.SANG_VIERGE, Carte.typeCarte.CORNE_LICORNE);
//
//                // si le joueur adverse n'a qu'une carte, il perd
//            List<Carte> cartesAdversaire = carteCServ.findAllByJoueurId(jCible.getId());
//            
//            if (cartesAdversaire.size() == 1) {
//                nbCVole = 1;
//            }
//            // supprimer la moitier des cartes de l'adversaire cible de façon aleatoire et créer des cartes identiques chez l'attaquant
//            if (cartesAdversaire.size() > 1) {
//                // nbVole prend la valeur de l'entier le plus proche supérieur ou égal à l'argument
//                nbCVole = (int) Math.ceil(cartesAdversaire.size() / 2);
//            }
//            
//            // vole de carte
//            carteServ.volerCarte(jNow, jCible, nbCVole);
//            
//            
//            //########################################################################
//            // HYPNOSE
//            // need : bave de crapaud et lapis-lazuli
//            // effet : l'attaquant échange un carte de son choix contre 3 au hasard chez son adversaire
//            //########################################################################
//
//                // need un get et post methode pour avoir le typeCarteEchange
//            case 3:
//                
//                // supprimer les cartes consommées pour le sort après avoir vérifier que le joueur avait bien les cartes nécessaires
//                // et que l'adversaire sélectionné possède encore des cartes
//                carteServ.supprimerCarteConsommeesEtGestionRessourceDispoAttaquantEtCible(jNow, jCible, Carte.typeCarte.BAVE_CRAPAUD, Carte.typeCarte.LAPIS_LAZULI);
//                
//                 // verifier que l'attaquant possède bien 1 carte du type qu'il veut échanger
//                List<Carte> carteEchgeables = carteCServ.findAllByJoueurIdAndType(jNow.getId(), typeCarteEchange);
//                
//                if (carteEchgeables.isEmpty()) {
//                throw new RuntimeException("Tatata vous ne pouvez point échanger une carte que vous ne possédez pas !!! ");
//                  }
//                
//                 // effets : l'attaquant prend 3 cartes à son adversaire en échange d'une carte de son choix
//        // nombre de cartes à supprimer chez l'adversaire/ creer chez attaquant
//        nbCVole = 3;
//        // vole de carte
//            carteServ.volerCarte(jNow, jCible, nbCVole);
//            // donne 1 carte
//            carteServ.donnerCarte(jNow, jCible,typeCarteEchange );
//
////            //########################################################################
////            // DIVINATION
//            // need : lapis-lazuli et aile de chauve souris
//            // effet : le joueur peut voir les cartes des autres joueurs
//            // methode non définie
////            //########################################################################
////
////            case 4:
////
////            //########################################################################
////            // SOMMEIL PROFOND
//             // need : sang de vierge et bave de crapaud
//            // effet : le joueur choisit une victime qui ne pourra pas jouer pendant 2 tours
//            // methode non définie
////            //########################################################################
////            case 5:
//            
//        }
//        // retourne vers la page de jeu jsp
//        return "redirect:acceuil";
//    }
}

