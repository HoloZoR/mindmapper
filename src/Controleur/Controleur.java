package Controleur;

import Model.Noeud;
import Utils.Message;
import Utils.Observateur;
import Vue.FenetrePrincipal;
import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

public class Controleur implements Observateur {
    private FenetrePrincipal fenetre;
    private ArrayList<Noeud> noeuds;
/**/
    public Controleur() {
        this.fenetre = new FenetrePrincipal();
        fenetre.setVisible(true);
        fenetre.addObsevateur(this);
        fenetre.getToolBar().addObsevateur(this);
        this.noeuds = new ArrayList<>();
    }

    @Override
    public void traiterMessage(Message m) {
        switch (m.type) {
            case NEW:
                break;
            case OUVRIR:
                break;
            case SAUVEGARDER:
                break;
            case SAUVEGARDERIMAGE:
                break;
            case CREERNOEUD:
                fenetre.drawNoeud();
                break;
            case CREERLIEN:
                break;
            case RECHERCHER:
                break;
            default:
                break;
        }

    }
}
