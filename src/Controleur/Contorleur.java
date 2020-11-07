package Controleur;

import Model.Noeud;
import Utils.Message;
import Utils.Observateur;
import Vue.FenetrePrincipal;

import java.util.ArrayList;

public class Contorleur implements Observateur {
    private FenetrePrincipal fenetre;
    private ArrayList<Noeud> noeuds;

    public Contorleur() {
        this.fenetre = new FenetrePrincipal();
        fenetre.setVisible(true);
        fenetre.addObsevateur(this);
        this.noeuds = new ArrayList<>();
    }

    @Override
    public void traiterMessage(Message m) {
        switch (m.type) {
            case OUVRIR:
                break;
            case SAUVEGARDER:
                break;
            case CREERNOEUD:
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
