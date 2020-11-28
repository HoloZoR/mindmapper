package Utils;

import Vue.FenetrePrincipal;

import javax.swing.*;
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
