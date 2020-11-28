package Vue;

import Utils.Commandes;
import Utils.Message;
import Utils.Mouvement;
import Utils.Observe;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class FenetrePrincipal extends Observe implements ActionListener {
    private final int WINDOWWIDTH = 640;
    private final int WINDOWHEIGHT = 480;
    private final int NOEUDWIDTH = 50;
    private final int NOEUDHEIGHT = 50;

    private ToolBar toolBar;
    private JPanel surface;
    private HashMap<JTextField, JPanel> noeuds;
    private Mouvement mv;


    public FenetrePrincipal() {
        super();
        build();
        buildSurface();

        noeuds = new HashMap<>();
        toolBar = new ToolBar();


        this.add(toolBar.getToolBar(), BorderLayout.NORTH);
        this.add(surface);
        mv = new Mouvement();

    }

    private void build() {
        setTitle("MindMapper");
        setSize(WINDOWWIDTH,WINDOWHEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buildSurface() {
        surface = new JPanel();
        surface.getSize(getSize());
        surface.setLayout(null);
        surface.setLocation(0,0);
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

        noeuds.put(textField, pan);
        surface.add(pan);
        mv.addListener(pan);

        revalidate();
        repaint();
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
