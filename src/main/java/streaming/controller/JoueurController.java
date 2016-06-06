/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.controller;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import streaming.entity.Joueur;
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
    private CarteCrudService carteCServ;

    // methode créer un joueur
    @RequestMapping(value = "/connexion",method = RequestMethod.GET)
    public String connexionGet( Model model) {
        // créer un nouveau joueur en BD pour avoir accés à ces attributs dans la JSP
        Joueur joueur = new Joueur();
        // envoyer à la jsp pour avoir un formulaire
        model.addAttribute("newJoueur", joueur);
        // vers la jsp
        return "connexion";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/connexion")
    public void connexionPost(@ModelAttribute("newJoueur") Joueur joueur ) {
        
        //Test si un avatar est déjà selectionner, il n'est plus disponible
        // récupérer l'information de l'avatar entré dans le formulaire
        int numAvatarSelectionne = joueur.getNumAvatar();
        
        // chercher si il y a un joueur avec cet avatar
        Joueur j = joueurCService.findOneByNumAvatar(numAvatarSelectionne);
        // si j n'est pas null alors l'avatar n'est pas dispo
        if(j != null)
        {
            throw new RuntimeException("cet avatar est déjà attribué");
        }
        
        // récupérer les données renseignées dans le formulaire et les sauvegarder en BD
       joueurCService.save(joueur);
    }
//
//    public void demarerJeux() {
//        
//
//        // crée 7 cartes aléatoires qui lui sont associées (boucle pour 7 tours/ 7 cartes)
//        for (int i = 0; i < 7; i++) {
//            // lancer un random pour générer un numéro comprit entre 1 et 5 qui correspond à une carte
//            Random r = new Random();
//            int numeroCarte = 1 + r.nextInt(5 - 1 + 1);
//
//        }

//    }

}
