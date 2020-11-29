package Vue;

import Utils.Commandes;
import Utils.Mouvement;
import Utils.Observe;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;

public class FenetrePrincipal extends Observe implements ActionListener {
    public final int WINDOWWIDTH = 640;
    public final int WINDOWHEIGHT = 480;
    public final int NOEUDWIDTH = 100;
    public final int NOEUDHEIGHT = 100;

    private int nodeIds =0;
    private ToolBar toolBar;
    private Surface surface;
    private HashMap<JTextField, JPanel> noeuds;
    private Mouvement mv;
    private int compteur = -1;
    private Commandes typeAction;

    public FenetrePrincipal() {
        super();
        build();
        buildSurface();

        noeuds = new HashMap<>();
        toolBar = new ToolBar();


        this.add(surface);
        this.add(toolBar.getToolBar(), BorderLayout.NORTH);
        mv = new Mouvement(this);

    }

    private void build() {
        setTitle("MindMapper");
        setSize(WINDOWWIDTH, WINDOWHEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buildSurface() {
        surface = new Surface();
        surface.setSize(getSize());
    }

    public HashMap<JTextField, JPanel> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(HashMap<JTextField, JPanel> noeuds) {
        this.noeuds = noeuds;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public Mouvement getMv() {
        return mv;
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public void ajouterNoeud() {

        JTextField textField = new JTextField("Noeud Principale");
        textField.addActionListener(this);
        textField.setActionCommand("TextNoeud");

        JPanel pan = new JPanel();
        pan.setBackground(Color.lightGray);
        pan.setLayout(new GridLayout(3, 1));
        pan.setSize(NOEUDWIDTH, NOEUDHEIGHT);
        pan.add(textField);
        pan.add(new JLabel(""));
        pan.add(new JLabel(""));


        nodeIds++;
        pan.setName(""+nodeIds); // pour identifier les noeud

        noeuds.put(textField, pan);
        surface.add(pan);
        mv.addListener(pan);

        revalidate();
    }

    public Commandes getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(Commandes typeAction) {
        this.typeAction = typeAction;
    }

    public void creerLien() {
        setTypeAction(Commandes.CREERLIEN);

        if(compteur == -1) {
            compteur = 0;
        }
    }

    public void supprimerNoeud(){
        setTypeAction(Commandes.SUPPRIMER);
    }

    public boolean incrementeCompteur() {
        if(compteur == -1) {
            return false;
        } else if (compteur == 0) {
            compteur++;
            return true;
        } else {
            compteur = -1;
            return true;
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        switch(action) {
            case "TextNoeud":
                JTextField text = (JTextField) actionEvent.getSource();
                JLabel txtLabel = new JLabel(text.getText());
                JPanel pan = noeuds.get(text);
                pan.removeAll();
                pan.add(txtLabel);
                pan.add(new JLabel(""));
                pan.add(new JLabel(""));
                revalidate();
                repaint();
                break;
            default:
                // code block
        }
    }
}
