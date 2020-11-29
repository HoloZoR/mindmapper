package Model;

public class Liaison {
    private Noeud noeudPere;
    private Noeud noeudFils;

    public Liaison(Noeud noeudPere, Noeud noeudFils) {
        this.noeudPere = noeudPere;
        this.noeudFils = noeudFils;
    }
    public Noeud getNoeudPere() {
        return noeudPere;
    }

    public Noeud getNoeudFils() {
        return noeudFils;
    }

}
