/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import streaming.filter.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import streaming.entity.Joueur;
import streaming.service.RessourceCrudService;
import streaming.service.RessourceService;

/**
 *
 * @author ajc
 */
@WebFilter(filterName = "TableauBordFiltre", urlPatterns = {"/*"})
public class TableauBordFiltre implements Filter {

    // le filtre s'affiche avant chaque servlets
    @Autowired
    private RessourceCrudService ressourceCrudService;
    @Autowired
    private RessourceService ressourceServ;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) sr;
        // si aucun joueur n'est connecté
        if (req.getSession().getAttribute("joueurCo") == null) {

            // forward directement vers les servlets
    

        } // si un joueur est connecté
        else {

            Joueur j = (Joueur) req.getSession().getAttribute("joueurCo");

            req.setAttribute("nbBles", ressourceServ.affichageBles(j.getId()));
            req.setAttribute("nbCarotte", ressourceServ.affichageCarotte(j.getId()));
            req.setAttribute("nbFromage", ressourceServ.affichageFromage(j.getId()));
            req.setAttribute("nbChevre", ressourceServ.affichageChevre(j.getId()));
            req.setAttribute("nbFermier", ressourceServ.affichageFermier(j.getId()));
            
             // produire régulièrement du fromage
//            ressourceServ.productionFromage(j.getId());

        }
                // la commande suivante permet de réorienter vers la servlet cible
            fc.doFilter(req, sr1);
    }

    @Override
    public void destroy() {
    }

}
