package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Surface extends JPanel {
    public Surface() {
        super();
        this.setLayout(null);
        this.setLocation(0,0);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);
        for (Component noeud1 : getComponents()) {
            for (Component noeud2 : getComponents()) {
                g2d.drawLine(noeud1.getX(),noeud1.getY(),noeud2.getX(),noeud2.getY());
            }
        }
    }
}
