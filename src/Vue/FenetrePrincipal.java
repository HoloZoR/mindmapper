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

/**
 * Fenetre principal de l'application elle gere tous l'interaction
 */
public class FenetrePrincipal extends Observe implements ActionListener {
    public final int WINDOWWIDTH = 640;
    public final int WINDOWHEIGHT = 480;
    public final int NOEUDWIDTH = 120;
    public final int NOEUDHEIGHT = 120;

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

    /**
     * Ajoute un noeud au workflow de l'application
     */
    public void ajouterNoeud() {
        nodeIds++;
        for (Map.Entry m : noeuds.entrySet()) {
            JPanel panel = (JPanel) m.getValue();
            int id = Integer.parseInt(panel.getName());
            if (id>=nodeIds){
                nodeIds = id + 1;
            }
        }

        JLabel textLabel = new JLabel("Noeud");
        textLabel.setName(""+nodeIds);
        textLabel.addMouseListener(labelListener());

        JTextArea textArea = new JTextArea("description");

        JPanel pan = new JPanel();
        pan.setBorder(new BevelBorder(BevelBorder.RAISED));
        pan.setBackground(Color.decode("#FFE4C4"));
        pan.setLayout(new GridLayout(4, 1));
        pan.setSize(NOEUDWIDTH, NOEUDHEIGHT);
        pan.setName(""+nodeIds); // pour identifier les noeuds

        pan.add(textLabel);
        pan.add(textArea);

        JButton btnUpSize = new JButton(new ImageIcon( "icons/up.png" ));
        btnUpSize.addActionListener(this);
        btnUpSize.setActionCommand("up");
        btnUpSize.setName(""+nodeIds);

        JButton btnDownSize = new JButton(new ImageIcon( "icons/down.png" ));
        btnDownSize.addActionListener(this);
        btnDownSize.setActionCommand("down");
        btnDownSize.setName(""+nodeIds);

        JPanel pan1 = new JPanel();
        pan1.setBackground(Color.decode("#FFE4C4"));
        pan1.setLayout(new GridLayout(1, 5));
        pan1.add(new JLabel(""));
        pan1.add(new JLabel(""));
        pan1.add(new JLabel(""));
        pan1.add(btnUpSize);
        pan1.add(btnDownSize);

        pan.add(new JLabel(""));
        pan.add(pan1);

        noeuds.put(textLabel, pan);
        surface.add(pan);
        mv.addListener(pan);

        revalidate();
    }
    public void ajouterNoeudWithModel(int id ,String titre, String description,double x,double y) {

        JLabel textLabel = new JLabel(titre);
        textLabel.setName("" + id);
        textLabel.addMouseListener(labelListener());

        JTextArea textArea = new JTextArea(description);

        JPanel pan = new JPanel();
        pan.setBorder(new BevelBorder(BevelBorder.RAISED));
        pan.setBackground(Color.decode("#FFE4C4"));
        pan.setLocation((int) x, (int) y);
        pan.setLayout(new GridLayout(4, 1));
        pan.setSize(NOEUDWIDTH, NOEUDHEIGHT);
        pan.setName("" + id); // pour identifier les noeuds

        pan.add(textLabel);
        pan.add(textArea);

        JButton btnUpSize = new JButton(new ImageIcon("icons/up.png"));
        btnUpSize.addActionListener(this);
        btnUpSize.setActionCommand("up");
        btnUpSize.setName("" + id);

        JButton btnDownSize = new JButton(new ImageIcon("icons/down.png"));
        btnDownSize.addActionListener(this);
        btnDownSize.setActionCommand("down");
        btnDownSize.setName("" + id);

        JPanel pan1 = new JPanel();
        pan1.setBackground(Color.decode("#FFE4C4"));
        pan1.setLayout(new GridLayout(1, 5));
        pan1.add(new JLabel(""));
        pan1.add(new JLabel(""));
        pan1.add(new JLabel(""));
        pan1.add(btnUpSize);
        pan1.add(btnDownSize);

        pan.add(new JLabel(""));
        pan.add(pan1);

        noeuds.put(textLabel, pan);
        surface.add(pan);
        mv.addListener(pan);
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

    /**
     * Change un JLabel du neoud en txtTextField
     * @parm text
     */
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

    /**
     * utilise un compteur pour créer un lien
     */
    public void creerLien() {
        setTypeAction(Commandes.CREERLIEN);
        if(compteur == -1) {
            compteur = 0;
        }
    }
    /**
     * Permet de supprimer un noeud de l'application
     */
    public void supprimerNoeud(){
        setTypeAction(Commandes.SUPPRIMER);

    }

    /**
     * Gère le compteur pour créer des liens
     */
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
    private void changeSize(JButton button, int val1, int val2) {
        for (Map.Entry m : noeuds.entrySet()) {
            JPanel panel = (JPanel) m.getValue();
            if(panel.getName().equals(button.getName()) ) {
                panel.setSize(panel.getWidth()+val1, panel.getHeight()+val2);
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Donne l'index d'un composant dans sont parent
     * @param component
     */
    public int getComponentIndex(Component component) {
        if (component != null && component.getParent() != null) {
            Container c = component.getParent();
            for (int i = 0; i < c.getComponentCount(); i++) {
                if (c.getComponent(i) == component)
                    return i;
            }
        }
        return -1;
    }

    /**
     * Gère event grandir raptissir noeud et JFieldText
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        switch(action) {
            case "TextNoeud":
                JTextField text = (JTextField) actionEvent.getSource();
                textFieldToLabel(text);
                break;
            case "up":
                JButton button = (JButton) actionEvent.getSource();
                changeSize(button, 10, 10);
                break;
            case "down":
                JButton buttonDown = (JButton) actionEvent.getSource();
                changeSize(buttonDown, -10, -10);
                break;
            default:
                // code block
        }
    }

    public HashMap<Component, JPanel> getNoeuds() {
        return noeuds;
    }


    public Surface getSurface() {
        return surface;
    }


    public ToolBar getToolBar() {
        return toolBar;
    }

    public Commandes getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(Commandes typeAction) {
        this.typeAction = typeAction;
    }

}
