import java.util.ArrayList;

public class Contorleur {
    private FenetrePrincipal fenetre;
    private ArrayList<Noeud> noeuds;

    public Contorleur() {
        this.fenetre = new FenetrePrincipal();
        fenetre.setVisible(true);
        this.noeuds = new ArrayList<>();
    }
}
