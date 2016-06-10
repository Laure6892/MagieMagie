/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import streaming.entity.Carte;
import streaming.entity.Joueur;

/**
 *
 * @author ajc
 */
public class ListeSortDTO {
    
    
    private int numAttack;
    
    private String ennemi;
    
    private Long idCarteEchangeable;

    public Long getIdCarteEchangeable() {
        return idCarteEchangeable;
    }

    public void setIdCarteEchangeable(Long idCarteEchangeable) {
        this.idCarteEchangeable = idCarteEchangeable;
    }

    public int getNumAttack() {
        return numAttack;
    }

    public void setNumAttack(int numAttack) {
        this.numAttack = numAttack;
    }

    public String getEnnemi() {
        return ennemi;
    }

    public void setEnnemi(String ennemi) {
        this.ennemi = ennemi;
    }


    
    
}
