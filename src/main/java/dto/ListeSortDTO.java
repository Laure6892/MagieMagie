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
    
    private Carte carteEchangeable;

    public int getNumAttack() {
        return numAttack;
    }

    public void setNumAttack(int numAttack) {
        this.numAttack = numAttack;
    }

    public Carte getCarteEchangeable() {
        return carteEchangeable;
    }

    public void setCarteEchangeable(Carte carteEchangeable) {
        this.carteEchangeable = carteEchangeable;
    }

    public String getEnnemi() {
        return ennemi;
    }

    public void setEnnemi(String ennemi) {
        this.ennemi = ennemi;
    }


    
    
}
