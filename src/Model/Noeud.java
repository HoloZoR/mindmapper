package Model;

import java.util.ArrayList;

public class Noeud implements Actions {
    private String titre;
    private String couleur;
    private String description;
    private String forme;
    private double x;
    private double y;
    private ArrayList<Liaison> liaisonFils;
    private Liaison liaisonPere;
    private ArrayList<Noeud> noeudFils;
    private Noeud noeudPere;

    public Noeud(String titre, String couleur, String description, String forme, double x, double y, ArrayList<Liaison> liaisonFils, Liaison liaisonPere, ArrayList<Noeud> noeudFils, Noeud noeudPere) {
        this.titre = titre;
        this.couleur = couleur;
        this.description = description;
        this.forme = forme;
        this.x = x;
        this.y = y;
        this.liaisonFils = liaisonFils;
        this.liaisonPere = liaisonPere;
        this.noeudFils = noeudFils;
        this.noeudPere = noeudPere;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public ArrayList<Liaison> getLiaisonFils() {
        return liaisonFils;
    }

    public void setLiaisonFils(ArrayList<Liaison> liaisonFils) {
        this.liaisonFils = liaisonFils;
    }

    public Liaison getLiaisonPere() {
        return liaisonPere;
    }

    public void setLiaisonPere(Liaison liaisonPere) {
        this.liaisonPere = liaisonPere;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ArrayList<Noeud> getNoeudFils() {
        return noeudFils;
    }

    public void setNoeudFils(ArrayList<Noeud> noeudFils) {
        this.noeudFils = noeudFils;
    }

    public Noeud getNoeudPere() {
        return noeudPere;
    }

    public void setNoeudPere(Noeud noeudPere) {
        this.noeudPere = noeudPere;
    }

    @Override
    public boolean ajouterNoeud() {
        return false;
    }

    @Override
    public boolean supprimer() {
        return false;
    }
}
