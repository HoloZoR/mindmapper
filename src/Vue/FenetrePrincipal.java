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

    /**
     * Ajoute un noeud au workflow de l'application
     */
    public void ajouterNoeud() {
        nodeIds++;
        JLabel textLabel = new JLabel("Noeud");
        textLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JLabel text = (JLabel) e.getComponent();
                changeText(text);
            }
        });

        JTextArea textArea = new JTextArea("ecrit une descri\nption ici");

        JPanel pan = new JPanel();
        pan.setBorder(new BevelBorder(BevelBorder.RAISED));
        pan.setBackground(Color.decode("#FFE4C4"));
        pan.setLayout(new GridLayout(4, 1));
        pan.setSize(NOEUDWIDTH, NOEUDHEIGHT);
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

        pan.setName(""+nodeIds); // pour identifier les noeuds

        noeuds.put(textLabel, pan);
        surface.add(pan);
        mv.addListener(pan);

        revalidate();
    }

    /**
     * Change un JLabel du neoud en txtTextField
     * @parm text
     */
    public void changeText(JLabel text) {
        JTextField txtTextField = new JTextField(text.getText());
        txtTextField.addActionListener(FenetrePrincipal.this::actionPerformed);
        txtTextField.setActionCommand("TextNoeud");
        JPanel pan = noeuds.get(text);
        noeuds.remove(text, pan);
        noeuds.put(txtTextField, pan);
        int indice = getComponentIndex(text);
        pan.remove(text);
        pan.add(txtTextField, indice);
        revalidate();
        repaint();
    }

    public Commandes getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(Commandes typeAction) {
        this.typeAction = typeAction;
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
                int indice = getComponentIndex(text);
                pan.remove(text);
                pan.add(txt, indice);
                revalidate();
                repaint();
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
}
