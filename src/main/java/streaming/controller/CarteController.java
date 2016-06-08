/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.controller;

import dto.ListeSortDTO;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import streaming.entity.Carte;
import streaming.service.CarteService;
import streaming.entity.Joueur;
import streaming.service.CarteCrudService;
import streaming.service.JoueurCrudService;

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

    @Autowired
    private JoueurCrudService joueurCServ;

    //methode des sorts
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // mode test: les id des joueurs a été entré manuellement, pour le jeux, remettre les bonnes lignes de commandes
    // mises en commantaires
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @RequestMapping(method = RequestMethod.GET, value = "/sort")
    public String sortilegeGet(Model model, HttpSession session) {

        //envoyer liste des joueurs pour choisir la victime (en excluant le jour à qui c'est le tour) a mettre dans un model) dans la jsp
        List<Joueur> listeEnnemis = (List<Joueur>) joueurCServ.findAll();

        // recuperer le joueur actuel et récuperer son id
//        Joueur joueurActuel = (Joueur) session.getAttribute("joueurNow");
//        Long idJActuel = joueurActuel.getId();
        // récupérer le joueur actuel en BD
//        Joueur jDelete = joueurCServ.findOne(idJActuel);
        Joueur jDelete = joueurCServ.findOne(1L);

        // retirer le joueur actuel de la liste des joueurs cibles potentiels
        listeEnnemis.remove(jDelete);

        // envoyer la liste des cartes du joueur actuel à la jsp pour qu'il puisse choisir celle qu'il veut echanger (cas du sort hypnoze)
        // chercher la liste des carte du joueur actuel
//        List<Carte> cartesJoueurActuel = carteCServ.findAllByJoueurId(idJActuel);
        List<Carte> cartesJoueurActuel = carteCServ.findAllByJoueurId(1L);

        // mettre dans un model la liste des joueurs cibles potentiels à la JSP
        model.addAttribute("listeEnnemis", listeEnnemis);
        // envoyer la liste des cartes que le joueur actuel peut echanger
        model.addAttribute("cartesEchangeables", cartesJoueurActuel);
        // passer par un dto pour pouvoir stocker l'information sur quel sort l'attaquant à choisi (numAttack)
        ListeSortDTO dto = new ListeSortDTO();
        model.addAttribute("monDto", dto);

        // aller vers une jsp
        return "liste_sort";
    }

    // methode en mode test : les id des joueurs sont entrées manuellement !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @RequestMapping(method = RequestMethod.POST, value = "/sort")
    public String sortilegeGet(@ModelAttribute("monDto") ListeSortDTO dto, HttpSession session) {

        int nbCVole = 0;
        int nbCDonne = 0;
        
          // récupérer l'attaquant (http session)
            Joueur jNow = (Joueur) session.getAttribute("joueurNow");
           //Joueur jNow = joueurCServ.findOne(1L);
        // récupérer la cible sélectionnée dans le formulaire (non fonctionnel) a rajouter en paramètre de la méthode
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Joueur jCible = joueurCServ.findOne(2L);
        
        // numAttack permet d'identifier le sort sélectionné par le joueur
        // a gérer au niveau de la jsp et du controller GET/POST methodes
        
        // récuperer le numAttack qui est stocké dans un model numAttackDto
        int numAttack = dto.getNumAttack();
//       // récupérer le joueur cible (dans le dto on récupère l'id du joueur en string)
//        long idEnnemi = (long) dto.getEnnemi();
        // chercher l'ennemi avec ce speudo (liste des joueurs)
        
        
        
        
        // tester si numAttack = 1 .... 
        switch (numAttack) {

            //########################################################################
            // INVISIBILITE: fonctionnel
            // need : corne de licorne + bave de crapaud
            // effets : vole une carte au hasard chez un adversaire
            //########################################################################
            case 1:

                // supprimer les cartes consommées pour le sort après avoir vérifier que le joueur avait bien les cartes nécessaires
                // et que l'adversaire sélectionné possède encore des cartes
                carteServ.supprimerCarteConsommeesEtGestionRessourceDispoAttaquantEtCible(jNow, jCible, Carte.typeCarte.BAVE_CRAPAUD, Carte.typeCarte.CORNE_LICORNE);

                // nombre de cartes à supprimer chez l'adversaire/creer chez l'attaquant
                nbCVole = 1;
                
                // vole de carte
                carteServ.volerCarte(jNow, jCible, nbCVole);
                break;
                
            //########################################################################
            //FILTRE AMOUR: fonctionnel
            // need : licorne et sang de vierge
            // effet : vole la moitier des cartes de l'adversaire
            // conditions: si l'ennemi n'à qu'1 carte il perd, on prend le nombre entire supérieur
            //########################################################################  
            case 2:

                // supprimer les cartes consommées pour le sort après avoir vérifier que le joueur avait bien les cartes nécessaires
                // et que l'adversaire sélectionné possède encore des cartes
                carteServ.supprimerCarteConsommeesEtGestionRessourceDispoAttaquantEtCible(jNow, jCible, Carte.typeCarte.SANG_VIERGE, Carte.typeCarte.CORNE_LICORNE);

                // si le joueur adverse n'a qu'une carte, il perd
            List<Carte> cartesAdversaire = carteCServ.findAllByJoueurId(jCible.getId());
            
            if (cartesAdversaire.size() == 1) {
                nbCVole = 1;
            }
            // supprimer la moitier des cartes de l'adversaire cible de façon aleatoire et créer des cartes identiques chez l'attaquant
            if (cartesAdversaire.size() > 1) {
                // nbVole prend la valeur de l'entier le plus proche inférieure ou égal à l'argument
                nbCVole = (int) Math.ceil(cartesAdversaire.size() / 2);
            }
            
            // vole de carte
            carteServ.volerCarte(jNow, jCible, nbCVole);
            
             break;
            //########################################################################
            // HYPNOSE
            // need : bave de crapaud et lapis-lazuli
            // effet : l'attaquant échange un carte de son choix contre 3 au hasard chez son adversaire
            //########################################################################

            // need un get et post methode pour avoir le typeCarteEchange
            case 3:
                
                
            break;
            //########################################################################
            // DIVINATION
            // need : lapis-lazuli et aile de chauve souris
            // effet : le joueur peut voir les cartes des autres joueurs
            // methode non définie
            //########################################################################

//            case 4:
//// break;
////            //########################################################################
////            // SOMMEIL PROFOND
//             // need : sang de vierge et bave de crapaud
//            // effet : le joueur choisit une victime qui ne pourra pas jouer pendant 2 tours
//            // methode non définie
////            //########################################################################
////            case 5:
//             break;
        }
        // retourne vers la page de jeu jsp
//        return "redirect:acceuil";
        return "";

    }
        
    
    
    
}
