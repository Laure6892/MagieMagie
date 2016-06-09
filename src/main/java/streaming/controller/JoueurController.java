/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.controller;

import dto.ListeSortDTO;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import streaming.entity.Carte;
import streaming.service.CarteService;

import streaming.entity.Joueur;
import streaming.service.JoueurService;
import streaming.service.CarteCrudService;
import streaming.service.JoueurCrudService;

/**
 *
 * @author ajc
 */
@Controller
public class JoueurController {

    @Autowired
    private JoueurCrudService joueurCService;
    @Autowired
    private JoueurCrudService joueurCServ;
    @Autowired
    private CarteCrudService carteCServ;
    @Autowired
    private CarteService carteServ;
    @Autowired
    private JoueurService joueurServ;

    // methode créer un joueur
    @RequestMapping(value = "/connexion", method = RequestMethod.GET)
    public String connexionGet(Model model) {
        // créer un nouveau joueur en BD pour avoir accés à ces attributs dans la JSP
        Joueur joueur = new Joueur();
        // envoyer à la jsp pour avoir un formulaire
        model.addAttribute("newJoueur", joueur);
        // vers la jsp
        return "connexion";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/connexion")
    public String connexionPost(@ModelAttribute("newJoueur") Joueur joueur, HttpSession session) {

        //Test si un avatar est déjà selectionner, il n'est plus disponible
        // récupérer l'information de l'avatar entré dans le formulaire
        int numAvatarSelectionne = joueur.getNumAvatar();

        // chercher si il y a un joueur avec cet avatar
        Joueur j = joueurCService.findOneByNumAvatar(numAvatarSelectionne);
        // si j n'est pas null alors l'avatar n'est pas dispo
        if (j != null) {
            throw new RuntimeException("cet avatar est déjà attribué");
        }

        // récupérer les données renseignées dans le formulaire et les sauvegarder en BD
        //  joueurCService.save(joueur);
        session.setAttribute("joueurNow", joueur);

        // sauvegarder le joueur pour générer son id en BD
        joueurCService.save(joueur);

        // crée 7 cartes aléatoirement qui sont associées à un joueur(boucle pour 7 tours/ 7 cartes)
        for (int i = 1; i < 8; i++) {
            // Les cartes sont identifiée par un numéro (dans le fichier CarteService)
            // lancer un random pour générer un numéro comprit entre 1 et 5 
            Random r = new Random();
            // max - min + 1
            int numeroCarte = 1 + r.nextInt(5 - 1 + 1);

            // créer la carte correspondante en récupérant l'id du joueur en question
            long idJour = joueur.getId();
            carteServ.creationCarteAleatoire(idJour, numeroCarte);
        }
        // initialiser la valeur de l'ordre de passage des joueurs, le premier inscrit
        // est le premier a jouer
        // l'ordre correspond à la position du joueur en BD
        // recuper la liste des joueurs inscrit pour avoir la taille de la liste
        List<Joueur> joueurs = (List<Joueur>) joueurCService.findAll();

        // setter la valeur de la taille de la liste pour l'ordre
        joueur.setOrdre(joueurs.size());

        // si l'ordre du joueur = 1 alors on lui donne la main (démarage du jeux)
        if (joueur.getOrdre() == 1) {
            joueur.setMain(1);
        }

        // initialiser la valeur de jeuEnCour à false pour que le plateau de jeu ne s'affiche pas
        joueur.setJeuEnCour(0);
        // sauvegarder les modifications de façon persistante en BD
        joueurCService.save(joueur);

        return "/start";
        // return "start";
    }

    @RequestMapping(value = "/page_jeu", method = RequestMethod.GET)
    public String demarerJeux(Model model, HttpSession session) {

        // enregistrer en BD un paramètre pour lancer le jeu
        // passer en true la valeur de jeuEnCour pour tous les joueurs connectées
        List<Joueur> joueurs2 = (List<Joueur>) joueurCService.findAll();

        for (Joueur joueur : joueurs2) {
            joueur.setJeuEnCour(1);
            joueurCServ.save(joueur);
        }

        // recupere le joueur actuel
        Joueur joueurActuel = (Joueur) session.getAttribute("joueurNow");
        Long idJ = joueurActuel.getId();
        //  System.out.println("ICI LE JOUEUR A VIRER ID="+ idJ);  
        // Joueur joueurDelete=joueurCService.findOne(joueurActuel.getId());
        Joueur jDelete = joueurCService.findOne(idJ);
        joueurs2.remove(jDelete);

        model.addAttribute("listeJoueurs", joueurs2);

//        model.addAttribute("mainJoueurActuel", joueurActuel.getMain());
        // vers la page jsp du lancement du jeu
        return "page_jeu";
    }

    // mettre a jour le plateau mes ressources
    @RequestMapping(value = "/ajax_plateau", method = RequestMethod.GET)
    public String ajaxPlateau(Model model, HttpSession session) {
        List<Joueur> listeEnnemis = (List<Joueur>) joueurCService.findAll();
        Joueur joueurActuel = (Joueur) session.getAttribute("joueurNow");
        joueurActuel = joueurCServ.findOne(joueurActuel.getId());
        Long idJ = joueurActuel.getId();

        Joueur jDelete = joueurCService.findOne(idJ);
        List<Carte> cartes = (List<Carte>) carteCServ.findAllByJoueurId(idJ);
        listeEnnemis.remove(jDelete);

        model.addAttribute("listeCarte", cartes);
        model.addAttribute("listeJoueurs", listeEnnemis);
        model.addAttribute("joueurActuel", joueurActuel);
        model.addAttribute("mainJoueurActuel", joueurActuel.getMain());

        //envoyer liste des joueurs pour choisir la victime (en excluant le jour à qui c'est le tour) a mettre dans un model) dans la jsp
        model.addAttribute("monDto", new ListeSortDTO());

        //Affichera que les sorts ppossibles
        model.addAttribute("amour", carteServ.AutorisationSortFiltreAmour(jDelete));
        model.addAttribute("divination", carteServ.AutorisationSortDivination(jDelete));
        model.addAttribute("hypnose", carteServ.AutorisationSortHypnose(jDelete));
        model.addAttribute("invisibilite", carteServ.AutorisationSortInvisibilite(jDelete));
        model.addAttribute("sommeil", carteServ.AutorisationSortSommeil(jDelete));
        // retirer le joueur actuel de la liste des joueurs cibles potentiels
        listeEnnemis.remove(jDelete);

        // envoyer la liste des cartes du joueur actuel à la jsp pour qu'il puisse choisir celle qu'il veut echanger (cas du sort hypnoze)
        // chercher la liste des carte du joueur actuel
        List<Carte> cartesJoueurActuel = carteCServ.findAllByJoueurId(joueurActuel.getId());

        // mettre dans un model la liste des joueurs cibles potentiels à la JSP
        model.addAttribute("listeEnnemis", listeEnnemis);
        // envoyer la liste des cartes que le joueur actuel peut echanger
        model.addAttribute("cartesEchangeables", cartesJoueurActuel);

        // vers la jsp
        return "ajax_plateau";
    }
// mettre a jour la page pour demarrer le jeu

    @RequestMapping(value = "/ajax_plateau2", method = RequestMethod.GET)
    public String ajax2(Model model, HttpSession session) {

        List<Joueur> joueurs2 = (List<Joueur>) joueurCService.findAll();
        Joueur joueurActuel = (Joueur) session.getAttribute("joueurNow");
        Long idJ = joueurActuel.getId();

        Joueur jDelete = joueurCService.findOne(idJ);
        joueurs2.remove(jDelete);

        model.addAttribute("listeJoueurs", joueurs2);
        model.addAttribute("joueurActuel", joueurActuel);
        // si personne n'a cliqué sur "go", alors la bouton go s'affiche
        // au cas contraire, il redirige directement vers la page de jeu
        model.addAttribute("partiEnCours", joueurActuel.getJeuEnCour());

        return "ajax_plateau2";
    }

    @RequestMapping(value = "/divination", method = RequestMethod.GET)
    public String divination(Model model, HttpSession session) {

        List<Joueur> joueurs2 = (List<Joueur>) joueurCService.findAll();
        Joueur joueurActuel = (Joueur) session.getAttribute("joueurNow");
        Long idJ = joueurActuel.getId();

        Joueur jDelete = joueurCService.findOne(idJ);
        joueurs2.remove(jDelete);

        model.addAttribute("listeJoueurs", joueurs2);
        model.addAttribute("joueurActuel", joueurActuel);

        return "divination";
    }

    @RequestMapping(value = "/lister_sort2", method = RequestMethod.GET)
    public String ajax3(Model model, HttpSession session) {

        //envoyer liste des joueurs pour choisir la victime (en excluant le jour à qui c'est le tour) a mettre dans un model) dans la jsp
        List<Joueur> listeEnnemis = (List<Joueur>) joueurCService.findAll();

        // recuperer le joueur actuel et récuperer son id
        Joueur joueurActuel = (Joueur) session.getAttribute("joueurNow");
        Long idJActuel = joueurActuel.getId();
        // récupérer le joueur actuel en BD
        Joueur jDelete = joueurCServ.findOne(idJActuel);
        model.addAttribute("joueurActuel", joueurActuel.getMain());
        model.addAttribute("monDto", new ListeSortDTO());

        //Affichera que les sorts ppossibles
        model.addAttribute("amour", carteServ.AutorisationSortFiltreAmour(jDelete));
        model.addAttribute("divination", carteServ.AutorisationSortDivination(jDelete));
        model.addAttribute("hypnose", carteServ.AutorisationSortHypnose(jDelete));
        model.addAttribute("invisibilite", carteServ.AutorisationSortInvisibilite(jDelete));
        model.addAttribute("sommeil", carteServ.AutorisationSortSommeil(jDelete));
        // retirer le joueur actuel de la liste des joueurs cibles potentiels
        listeEnnemis.remove(jDelete);

        // envoyer la liste des cartes du joueur actuel à la jsp pour qu'il puisse choisir celle qu'il veut echanger (cas du sort hypnoze)
        // chercher la liste des carte du joueur actuel
        List<Carte> cartesJoueurActuel = carteCServ.findAllByJoueurId(idJActuel);

        // mettre dans un model la liste des joueurs cibles potentiels à la JSP
        model.addAttribute("listeEnnemis", listeEnnemis);
        // envoyer la liste des cartes que le joueur actuel peut echanger
        model.addAttribute("cartesEchangeables", cartesJoueurActuel);

        // vers la jsp
        return "lister_sort2";
    }

    @RequestMapping(value = "/lancer_sort", method = RequestMethod.GET)
    public String ajaxLancerSortGET(Model model, HttpSession session) {

        List<Joueur> joueurs2 = (List<Joueur>) joueurCService.findAll();
        Joueur joueurActuel = (Joueur) session.getAttribute("joueurNow");
        Long idJ = joueurActuel.getId();
// 
        Joueur jDelete = joueurCService.findOne(idJ);
//         List<Carte> cartes=( List<Carte> )carteCServ.findAllByJoueurId(idJ);
        joueurs2.remove(jDelete);
//  
//         model.addAttribute("listeCarte", cartes);

        model.addAttribute("listeJoueurs", joueurs2);
        model.addAttribute("joueurActuel", joueurActuel);

        // vers la jsp
        return "vide";
    }

    @RequestMapping(value = "/lancer_sort", method = RequestMethod.POST)
    @Transactional
    public String ajaxLancerSortPOST(@ModelAttribute("monDto") ListeSortDTO dto, HttpSession session) {
        int nbCVole = 0;
        int nbCDonne = 0;

        // récupérer l'attaquant (celui qui a la main, ou celui du httpsession = identique)
        Joueur jNow = joueurCServ.findOneByMain(1);

        // récupérer la cible sélectionnée dans le formulaire 
        Joueur jCible = joueurCServ.findOneByPseudo(dto.getEnnemi());

        // numAttack permet d'identifier le sort sélectionné par le joueur
        // a gérer au niveau de la jsp et du controller GET/POST methodes
        // récuperer le numAttack qui est stocké dans un model numAttackDto
        int numAttack = dto.getNumAttack();
//       // récupérer le joueur cible (dans le dto on récupère l'id du joueur en string)
//        long idEnnemi = (long) dto.getEnnemi();
        // chercher l'ennemi avec ce speudo (liste des joueurs)
        switch (dto.getNumAttack()) {
            case 1:
                // supprimer les cartes consommées pour le sort après avoir vérifier que le joueur avait bien les cartes nécessaires
                // et que l'adversaire sélectionné possède encore des cartes
                carteServ.supprimerCarteConsommeesEtGestionRessourceDispoAttaquantEtCible(jNow.getId(), jCible.getId(), Carte.typeCarte.BAVE_CRAPAUD, Carte.typeCarte.CORNE_LICORNE);

                // nombre de cartes à supprimer chez l'adversaire/creer chez l'attaquant
                nbCVole = 1;
                // vole de carte
                carteServ.volerCarte(jNow.getId(), jCible.getId(), nbCVole);
                break;

            case 2:
                // supprimer les cartes consommées pour le sort après avoir vérifier que le joueur avait bien les cartes nécessaires
                // et que l'adversaire sélectionné possède encore des cartes
                carteServ.supprimerCarteConsommeesEtGestionRessourceDispoAttaquantEtCible(jNow.getId(), jCible.getId(), Carte.typeCarte.SANG_VIERGE, Carte.typeCarte.CORNE_LICORNE);

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
                carteServ.volerCarte(jNow.getId(), jCible.getId(), nbCVole);
                break;

            case 3:
                return "divination";

            default:
                throw new RuntimeException("pas de sort sélectionné");

        }

        joueurServ.joueurSuivant();
        // vers la jsp
        return "vide";
    }

    @RequestMapping(value = "/passer_tour", method = RequestMethod.GET)
    public String ajaxPasserTour(HttpSession session) {
        joueurServ.joueurSuivant();
        return "ajax_vide";
    }

    //    @RequestMapping(value = "/finJeux", method = RequestMethod.GET)
//    public String finJeux() {
//        // supprimer toutes les données en BD
//        // trouver les listes des cartes et des joueurs
//        List<Carte> cartes = (List<Carte>) carteCServ.findAll();
//        List<Joueur> joueurs = (List<Joueur>) joueurCService.findAll();
//        // supprimer d'abord la liste des cartes puis des joueurs
//        for (Carte carte : cartes) {
//            carteCServ.delete(carte);
//        }
//        for (Joueur joueur : joueurs) {
//            joueurCService.delete(joueur);
//        }
//
//        // repartir pour lancer une nouvelle partie
//        return "redirect:/connexion";
//    }
}
