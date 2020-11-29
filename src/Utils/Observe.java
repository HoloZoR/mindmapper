package Utils;

import javax.swing.*;

/**
 * Implementé pour le pattern observeur
 */
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
