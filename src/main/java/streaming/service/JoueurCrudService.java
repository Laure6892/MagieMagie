/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import streaming.entity.Joueur;

/**
 *
 * @author ajc
 */
public interface JoueurCrudService extends CrudRepository<Joueur, Long>
{
    
   public Joueur findOneByPseudo(String pseudo);
   public Joueur findOneByNumAvatar(int numAvatar);
 
    
}
