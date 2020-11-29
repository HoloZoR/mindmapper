package Model;

import java.util.ArrayList;

/**
 * Represente un noeud de la carte mental
 */
public class Noeud {
    private int id;
    private String titre;
    private String description;
    private double x;
    private double y;
    private ArrayList<Liaison> liaisonFils;

    public Noeud(int id, String titre, String description, double x, double y) {
        this.titre = titre;
        this.description = description;
        this.x = x;
        this.y = y;
        this.liaisonFils = new ArrayList<>();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public void addLiaisonFils(Liaison l ){
        this.liaisonFils.add(l);
    }

    public ArrayList<Liaison> getLiaisonFils() {
        return liaisonFils;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


}
