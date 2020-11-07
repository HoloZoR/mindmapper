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

    }
}
