/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import streaming.filter.*;
import java.io.IOException;
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
import streaming.service.CycleService;

/**
 *
 * @author ajc
 */
@WebFilter(filterName = "CommonFilter", urlPatterns = {"/*"})
public class filter implements Filter {

    // but, ce filtre s'applique avant chaque servlets
    @Autowired
    private CycleService cycleService;

    // implement all abstract
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
        } else {
            // si un joueur est connecté

            req.setAttribute("numCycle", cycleService.getCycleActuel());

        }
        // la commande suivante permet de réorienter vers la servlet cible
        fc.doFilter(req, sr1);
    }

    @Override
    public void destroy() {
    }

}
