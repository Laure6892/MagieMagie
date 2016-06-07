/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

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

/**
 *
 * @author ajc
 */
/*
@WebFilter(filterName = "CommonFilter", urlPatterns = {"/*"})
public class filter implements Filter {

 

    // implement all abstract
    @Override
    public void init(FilterConfig fc) throws ServletException {

        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) sr;
        // si aucun joueur n'est connecté
    
        // la commande suivante permet de réorienter vers la servlet cible
     //   fc.doFilter(req, sr1);
    }

    @Override
    public void destroy() {
    }

}
*/