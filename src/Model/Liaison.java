package Model;

public class Liaison {
    private String type;
    private String couleur;
    private Noeud noeudPere;
    private Noeud noeudFils;

    public Liaison(Noeud noeudPere, Noeud noeudFils) {
        this.noeudPere = noeudPere;
        this.noeudFils = noeudFils;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Noeud getNoeudPere() {
        return noeudPere;
    }

    public void setNoeudPere(Noeud noeudPere) {
        this.noeudPere = noeudPere;
    }

    public Noeud getNoeudFils() {
        return noeudFils;
    }

    public void setNoeudFils(Noeud noeudFils) {
        this.noeudFils = noeudFils;
    }
}
