/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import streaming.entity.Film;

/**
 *
 * @author tom
 */
@Controller
@RequestMapping("/film")
public class FilmController {
    
    @RequestMapping(value="find/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Film findById( @PathVariable("id") long id){
        
        Film f = new Film(1L, "Karate Kid", "blabla", 1989L, null);
        
        return f;
    }
}
