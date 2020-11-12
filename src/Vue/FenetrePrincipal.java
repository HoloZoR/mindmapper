package Vue;

import Utils.Observe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FenetrePrincipal extends Observe {

    private ToolBar toolBar;
    private DrawingSurface drawingSurface;
    private ArrayList<JButton> noeuds;

    public FenetrePrincipal() {
        super();
        build();

        toolBar = new ToolBar();
        drawingSurface = new DrawingSurface();
        noeuds = new ArrayList<>();

        this.add(toolBar.getToolBar(), BorderLayout.NORTH);
        this.add(drawingSurface);
    }

    public void build() {
        setTitle("MindMapper");
        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    private Graphics g() {
        return drawingSurface.getGraphics();
    }

    public void ajouterNoeud(String titre) {
        noeuds.add(new JButton(titre));
    }

    public void drawNoeud() {
        drawingSurface.doDrawing(g());
    }
}
