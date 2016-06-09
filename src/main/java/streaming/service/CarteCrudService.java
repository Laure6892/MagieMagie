/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import streaming.entity.Carte;

/**
 *
 * @author ajc
 */
public interface CarteCrudService extends CrudRepository<Carte, Long> {

    List<Carte> findAllByJoueurIdAndType(long idJoueur, Carte.typeCarte type);

    List<Carte> findAllByJoueurId(long idJoueur);

    Carte findOneByJoueurIdAndType(long idJoueur, Carte.typeCarte type);
  
}
