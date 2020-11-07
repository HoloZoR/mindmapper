import javax.swing.*;

public class Observe extends JFrame {
    private Observateur observateur;

    public void addObsevateur(Observateur o) {
        this.observateur = o;
    }

    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    }
}
