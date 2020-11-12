package Vue;
import Utils.Observe;

import Utils.Commandes;
import Utils.Message;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ToolBar extends Observe implements ActionListener {

    private JToolBar toolBar;
    private JButton btnNew;
    private JButton btnOpen;
    private JButton btnSave;
    private JButton btnSaveAs;
    private JButton btnAjouterNoeud;
    private JButton btnAjouterLiaison;
    private JButton btnLoop;
    private JTextField searchField;


    public ToolBar() {

        this.toolBar = new JToolBar();

        btnNew = new JButton(new ImageIcon( "icons/File.png" ));
        btnNew.setToolTipText( "New File" ); // l'indice lorsque la sourie est sur le bouton
        btnNew.addActionListener(this);
        btnNew.setActionCommand("New"); // variable permettant l'identification de l'action
        toolBar.add( btnNew );

        btnOpen = new JButton(new ImageIcon( "icons/Folder.png" ));
        btnOpen.setToolTipText( "Open file" );
        btnOpen.addActionListener(this);
        btnOpen.setActionCommand("Open");
        toolBar.add( btnOpen );

        btnSave = new JButton(new ImageIcon( "icons/Save.png" ));
        btnSave.setToolTipText( "Save" );
        btnSave.addActionListener(this);
        btnSave.setActionCommand("Save");
        toolBar.add( btnSave );

        btnSaveAs = new JButton(new ImageIcon( "icons/SaveAs.png" ));
        btnSaveAs.setToolTipText( "Save As..." );
        btnSaveAs.addActionListener(this);
        btnSaveAs.setActionCommand("SaveAs");
        toolBar.add( btnSaveAs );

        btnAjouterNoeud = new JButton(new ImageIcon( "icons/Plus.png" ));
        btnAjouterNoeud.setToolTipText( "Ajouter un Noeud" );
        btnAjouterNoeud.addActionListener(this);
        btnAjouterNoeud.setActionCommand("Noeud");
        toolBar.add( btnAjouterNoeud );

        btnAjouterLiaison = new JButton(new ImageIcon( "icons/Link.png" ));
        btnAjouterLiaison.setToolTipText( "Ajouter une liaison" );
        btnAjouterLiaison.addActionListener(this);
        btnAjouterLiaison.setActionCommand("Liaison");
        toolBar.add( btnAjouterLiaison );


        btnLoop = new JButton(new ImageIcon( "icons/Loop.png" ));
        btnLoop.setToolTipText( "Rechercher un mot" );
        btnLoop.addActionListener(this);
        btnLoop.setActionCommand("Search");
        toolBar.add(btnLoop);

        searchField = new JTextField( "" );
        searchField.setMaximumSize(new Dimension(200,40));
        toolBar.add(searchField);
    }
    public JToolBar getToolBar(){
        return this.toolBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        Message m = new Message();
        switch(action) {
            case "New":
                m.type = Commandes.NEW;
                super.notifierObservateur(m);
                break;
            case "Open":
                m.type = Commandes.OUVRIR;
                super.notifierObservateur(m);
                break;
            case "Save":
                m.type = Commandes.SAUVEGARDER;
                super.notifierObservateur(m);
                break;
            case "SaveAs":
                m.type = Commandes.SAUVEGARDERIMAGE;
                super.notifierObservateur(m);
                break;
            case "Noeud":
                m.type = Commandes.CREERNOEUD;
                super.notifierObservateur(m);
                break;
            case "Liaison":
                m.type = Commandes.CREERLIEN;
                super.notifierObservateur(m);
                break;
            case "Search":
                m.type = Commandes.RECHERCHER;
                super.notifierObservateur(m);
                break;
            default:
                // code block
        }
    }
}