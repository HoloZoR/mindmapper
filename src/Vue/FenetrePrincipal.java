package Vue;

import Utils.Commandes;
import Utils.Mouvement;
import Utils.Observe;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class FenetrePrincipal extends Observe implements ActionListener {
    public final int WINDOWWIDTH = 640;
    public final int WINDOWHEIGHT = 480;
    public final int NOEUDWIDTH = 100;
    public final int NOEUDHEIGHT = 100;

    public int nodeIds = 0;
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
    public void allLabelToTextfield() {
        for (Map.Entry m : noeuds.entrySet()) {
            JPanel panel = (JPanel)  m.getValue();
            try {
                JTextField textField = (JTextField) panel.getComponent(0);
            } catch (ClassCastException e) {
                JLabel label = (JLabel) panel.getComponent(0);
                labelToTextField(label);
            }
        }
        revalidate();

    }
    public void allTextFieldToLabel() {
        for (Map.Entry m : noeuds.entrySet()) {
            JPanel panel = (JPanel)  m.getValue();
            try {
                JLabel label = (JLabel) panel.getComponent(0);
            } catch (ClassCastException e) {
                JTextField textField = (JTextField) panel.getComponent(0);
                textFieldToLabel(textField);
            }
        }
        revalidate();

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
        nodeIds++;

        JLabel textLabel = new JLabel("Noeud");
        textLabel.setName(""+nodeIds);
        textLabel.addMouseListener(labelListener());

        JTextArea textArea = new JTextArea("description");

        JPanel pan = new JPanel();
        pan.setBorder(new BevelBorder(BevelBorder.RAISED));
        pan.setBackground(Color.decode("#FFE4C4"));
        pan.setLayout(new GridLayout(3, 1));
        pan.setSize(NOEUDWIDTH, NOEUDHEIGHT);
        pan.setName(""+nodeIds); // pour identifier les noeuds

        pan.add(textLabel);
        pan.add(textArea);
        pan.add(new JLabel(""));

        noeuds.put(textLabel, pan);
        surface.add(pan);
        mv.addListener(pan);

        revalidate();
    }
    public MouseAdapter labelListener(){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JLabel label = (JLabel) e.getComponent();
                labelToTextField(label);
            }
        };
    }
    public void labelToTextField(JLabel label) {
        JTextField textField = new JTextField(label.getText());
        textField.setName(label.getName());
        textField.addActionListener(FenetrePrincipal.this::actionPerformed);
        textField.setActionCommand("TextNoeud");
        for (Map.Entry m : noeuds.entrySet()) {
            JPanel panel = (JPanel)  m.getValue();
            if (panel.getName().equalsIgnoreCase(label.getName())){
                panel.remove(0);
                panel.add(textField,0);
            }
        }
        revalidate();
    }
    public void textFieldToLabel(JTextField textField) {
        JLabel label = new JLabel(textField.getText());
        label.setName(textField.getName());
        label.addMouseListener(labelListener());
        for (Map.Entry m : noeuds.entrySet()) {
            JPanel panel = (JPanel)  m.getValue();
            if (panel.getName().equalsIgnoreCase(label.getName())){
                panel.remove(0);
                panel.add(label,0);
            }
        }
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
                textFieldToLabel(text);
                break;
            default:
                // code block
        }
    }
}
