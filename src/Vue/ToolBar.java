package Vue;
import Utils.Observe;

import Utils.Commandes;
import Utils.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ToolBar extends Observe implements ActionListener {

    private JToolBar toolBar;
    private JButton btnNew;
    private JButton btnOpen;
    private JButton btnSave;
    private JButton btnSaveAs;
    private JButton btnAjouterNoeud;
    private JButton btnAjouterLiaison;
    private JButton btnSupprimerNoeud;
    private JButton btnLoop;
    private JButton btnReplace;
    private JTextField searchField;
    private JLabel replaceLabel;
    private JTextField replaceField;

    public ToolBar() {

        this.toolBar = new JToolBar();

        btnNew = new JButton(new ImageIcon( "icons/File.png" ));
        btnNew.setToolTipText( "New File" ); // l'indice lorsque la sourie est sur le bouton
        btnNew.addActionListener(this);
        btnNew.setActionCommand("New"); // variable permettant l'identification de l'action
        toolBar.add( btnNew, BorderLayout.WEST );

        btnOpen = new JButton(new ImageIcon( "icons/Folder.png" ));
        btnOpen.setToolTipText( "Open file" );
        btnOpen.addActionListener(this);
        btnOpen.setActionCommand("Open");
        toolBar.add( btnOpen, BorderLayout.WEST );

        btnSave = new JButton(new ImageIcon( "icons/Save.png" ));
        btnSave.setToolTipText( "Save" );
        btnSave.addActionListener(this);
        btnSave.setActionCommand("Save");
        toolBar.add( btnSave, BorderLayout.WEST );

        btnSaveAs = new JButton(new ImageIcon( "icons/SaveAs.png" ));
        btnSaveAs.setToolTipText( "Save As..." );
        btnSaveAs.addActionListener(this);
        btnSaveAs.setActionCommand("SaveAs");
        toolBar.add( btnSaveAs, BorderLayout.WEST );

        btnAjouterNoeud = new JButton(new ImageIcon( "icons/Plus.png" ));
        btnAjouterNoeud.setToolTipText( "Ajouter un Noeud" );
        btnAjouterNoeud.addActionListener(this);
        btnAjouterNoeud.setActionCommand("Noeud");
        toolBar.add( btnAjouterNoeud, BorderLayout.CENTER );

        btnAjouterLiaison = new JButton(new ImageIcon( "icons/Link.png" ));
        btnAjouterLiaison.setToolTipText( "Ajouter une liaison" );
        btnAjouterLiaison.addActionListener(this);
        btnAjouterLiaison.setActionCommand("Liaison");
        toolBar.add( btnAjouterLiaison, BorderLayout.CENTER );

        btnSupprimerNoeud = new JButton(new ImageIcon( "icons/Supp.png" ));
        btnSupprimerNoeud.setToolTipText( "Supprimer un noeuds" );
        btnSupprimerNoeud.addActionListener(this);
        btnSupprimerNoeud.setActionCommand("Supprimer");
        toolBar.add( btnSupprimerNoeud );


        btnLoop = new JButton(new ImageIcon( "icons/Loop.png" ));
        btnLoop.setToolTipText( "Rechercher un mot" );
        btnLoop.addActionListener(this);
        btnLoop.setActionCommand("Search");
        toolBar.add(btnLoop, BorderLayout.EAST);

        searchField = new JTextField( "" );
        searchField.setMaximumSize(new Dimension(150,40));
        toolBar.add(searchField, BorderLayout.EAST);

        replaceLabel = new JLabel("Remplacer par");
        replaceLabel.setVisible(false);
        toolBar.add(replaceLabel, BorderLayout.EAST);

        replaceField= new JTextField( "" );
        replaceField.setMaximumSize(new Dimension(150,40));
        replaceField.setVisible(false);
        toolBar.add(replaceField, BorderLayout.EAST);

        btnReplace = new JButton(new ImageIcon( "icons/Replace.png" ));
        btnReplace.setToolTipText( "remplacer les mots trouvés par" );
        btnReplace.addActionListener(this);
        btnReplace.setActionCommand("Replace");
        btnReplace.setVisible(false);
        toolBar.add(btnReplace, BorderLayout.EAST);
    }

    public JToolBar getToolBar(){
        return this.toolBar;
    }

    public int getHeight() {
        return this.toolBar.getHeight();
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
                m.type = Commandes.SAVE;
                super.notifierObservateur(m);
                break;
            case "SaveAs":
                m.type = Commandes.SAVEAS;
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
            case "Supprimer":
                m.type = Commandes.SUPPRIMER;
                super.notifierObservateur(m);
                break;
            case "Search":
                m.type = Commandes.RECHERCHER;
                super.notifierObservateur(m);
                break;
            case "Replace":
                m.type = Commandes.REMPLACER;
                super.notifierObservateur(m);
                break;
            default:
                // code block
        }
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }
    public void setReplaceVisible(boolean rep){
        replaceField.setVisible(rep);
        replaceLabel.setVisible(rep);
        btnReplace.setVisible(rep);
    }

    public JTextField getReplaceField() {
        return replaceField;
    }
}