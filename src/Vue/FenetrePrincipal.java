package Vue;

import Utils.Commandes;
import Utils.Mouvement;
import Utils.Observe;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class FenetrePrincipal extends Observe implements ActionListener {
    public final int WINDOWWIDTH = 640;
    public final int WINDOWHEIGHT = 480;
    public final int NOEUDWIDTH = 100;
    public final int NOEUDHEIGHT = 100;

    public int nodeIds =0;
    private ToolBar toolBar;
    private Surface surface;
    private HashMap<Component, JPanel> noeuds;
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

    public HashMap<Component, JPanel> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(HashMap<Component, JPanel> noeuds) {
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
        JLabel textLabel = new JLabel("Noeud");
        textLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JLabel text = (JLabel) e.getComponent();
                changeText(text);
            }
        });

        JPanel pan = new JPanel();
        pan.setBorder(new BevelBorder(BevelBorder.RAISED));
        pan.setBackground(Color.decode("#FFE4C4"));
        pan.setLayout(new GridLayout(3, 1));
        pan.setSize(NOEUDWIDTH, NOEUDHEIGHT);
        pan.add(textLabel);
        pan.add(new JLabel(""));
        pan.add(new JLabel(""));

        nodeIds++;
        pan.setName(""+nodeIds); // pour identifier les noeuds

        noeuds.put(textLabel, pan);
        surface.add(pan);
        mv.addListener(pan);

        revalidate();
    }

    public void changeText(JLabel text) {
        JTextField txtTextField = new JTextField(text.getText());
        txtTextField.addActionListener(FenetrePrincipal.this::actionPerformed);
        txtTextField.setActionCommand("TextNoeud");
        JPanel pan = noeuds.get(text);
        noeuds.remove(text, pan);
        noeuds.put(txtTextField, pan);
        pan.removeAll();
        pan.add(txtTextField);
        pan.add(new JLabel(""));
        pan.add(new JLabel(""));
        revalidate();
        repaint();
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
                JLabel txt = new JLabel(text.getText());
                txt.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        JLabel text = (JLabel) e.getComponent();
                        changeText(text);
                    }
                });
                JPanel pan = noeuds.get(text);
                noeuds.remove(text, pan);
                noeuds.put(txt, pan);
                pan.removeAll();
                pan.add(txt);
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
