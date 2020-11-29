package Utils;

import Vue.FenetrePrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Mouvement implements MouseListener, MouseMotionListener {

    private int X, Y;
    private FenetrePrincipal fenetre;
    public Mouvement(FenetrePrincipal fenetre) {
        this.fenetre = fenetre;
    }

    public void addListener(JPanel panel) {
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (fenetre.getTypeAction() == Commandes.CREERLIEN && fenetre.incrementeCompteur()) {
            ArrayList<Component> liaisons = fenetre.getSurface().getLiaisons();
            liaisons.add(mouseEvent.getComponent());
        } else if (fenetre.getTypeAction() == Commandes.SUPPRIMER) {
            ArrayList<Component> liaisons = fenetre.getSurface().getLiaisons();
            if(liaisons.size() >= 2) {
                for (int i = 0; i < liaisons.size() - 1; i += 2) {
                    if (liaisons.get(i).equals(mouseEvent.getComponent()) || (liaisons.get(i + 1).equals(mouseEvent.getComponent()))) {
                        liaisons.remove(i + 1);
                        liaisons.remove(i);
                        i -= 2;
                    }
                }
            }
            mouseEvent.getComponent().removeMouseListener(this);
            fenetre.getNoeuds().values().remove(mouseEvent.getComponent());
            fenetre.getSurface().remove(mouseEvent.getComponent());
            fenetre.setTypeAction(null);
            fenetre.nodeIds-=1;
        }
        fenetre.repaint();
        fenetre.getSurface().repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        X = mouseEvent.getX();
        Y = mouseEvent.getY();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        int x = (mouseEvent.getX() + mouseEvent.getComponent().getX()) - X;
        int y = (mouseEvent.getY() + mouseEvent.getComponent().getY()) - Y;
        mouseEvent.getComponent().setLocation(x, y);
        fenetre.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
