package Vue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * c'est l'interface graphique de la carte mental les noeuds et liaisons sont dessiner dans celle-ci
 */
public class Surface extends JPanel {
    private ArrayList<Component> liaisons;

    public Surface() {
        super();
        this.setLayout(null);
        this.setLocation(0,0);
        liaisons = new ArrayList<>();
    }


    public ArrayList<Component> getLiaisons() {
        return liaisons;
    }

    public void setLiaisons(ArrayList<Component> liaisons) {
        this.liaisons = liaisons;
    }

    /**
     * Dessine les liaisons de l'application
     * @param g
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.darkGray);
        g2d.setStroke(new BasicStroke(4));
        if(liaisons.size() >= 2) {
            for (int i = 0; i < liaisons.size()-1; i+=2) {
                Component noeud1 = liaisons.get(i);
                Component noeud2 = liaisons.get(i+1);
                int centre1X = noeud1.getWidth()/2;
                int centre1Y = noeud1.getHeight()/2;
                int centre2X = noeud2.getWidth()/2;
                int centre2Y = noeud2.getHeight()/2;
                g2d.drawLine(noeud1.getX() + centre1X,noeud1.getY() +centre1Y,noeud2.getX() + centre2X,noeud2.getY()+centre2Y);
            }
        }
    }
}
