package Controleur;

import Utils.Message;
import Utils.Observateur;
import Vue.FenetrePrincipal;
import javax.swing.*;

import java.io.File;

/**
 * Controller de l'application
 * il relie la vue et le model
 */
public class Controleur implements Observateur {
    private FenetrePrincipal fenetre;
    private String pathname = "";

    public Controleur() {
        this.fenetre = new FenetrePrincipal();
        fenetre.setVisible(true);
        fenetre.addObsevateur(this);
        fenetre.getToolBar().addObsevateur(this);
    }

    /**
     * traite les messages du pattern observeur
     * @param m
     * @return void
     */
    @Override
    public void traiterMessage(Message m) {
        final JFileChooser fc = new JFileChooser();


        switch (m.type) {
            case NEW:
                fenetre.dispose();
                this.fenetre = new FenetrePrincipal();
                fenetre.setVisible(true);
                fenetre.addObsevateur(this);
                fenetre.getToolBar().addObsevateur(this);
                pathname="";
                break;

            case OUVRIR:
                int open = fc.showOpenDialog(fenetre);
                if (open == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    pathname = file.getAbsolutePath();
                } else {
                    break;
                }
                fenetre.dispose();
                this.fenetre = new FenetrePrincipal();
                fenetre.setVisible(true);
                fenetre.addObsevateur(this);
                fenetre.getToolBar().addObsevateur(this);
                SaveAndLoad.ModelToView(SaveAndLoad.XmlToModel(pathname),fenetre);
                fenetre.revalidate();
                break;
            case SAVE:
                fenetre.allLabelToTextfield();
                if (pathname.isEmpty()) {
                    int save = fc.showSaveDialog(fenetre);

                    if (save == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        pathname = file.getAbsolutePath();
                    } else {
                        fenetre.allTextFieldToLabel();
                        break;
                    }
                }
                SaveAndLoad.ModelToXml(SaveAndLoad.ViewToModel(fenetre), pathname);
                fenetre.allTextFieldToLabel();
                break;
            case SAVEAS:
                fenetre.allLabelToTextfield();
                int saveAs = fc.showSaveDialog(fenetre);

                if (saveAs == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    pathname = file.getAbsolutePath();
                } else {
                    fenetre.allTextFieldToLabel();
                    break;
                }
                SaveAndLoad.ModelToXml(SaveAndLoad.ViewToModel(fenetre), pathname);
                fenetre.allTextFieldToLabel();
                break;
            case CREERNOEUD:
                fenetre.ajouterNoeud();
                break;
            case CREERLIEN:
                fenetre.creerLien();
                break;
            case SUPPRIMER:
                fenetre.supprimerNoeud();
                break;
            case RECHERCHER:
                fenetre.allLabelToTextfield();
                Recherche.removeHighLights(fenetre);
                String mot = fenetre.getToolBar().getSearchField().getText();
                if (!mot.equals("")){
                    boolean founded = Recherche.rechercherMot(mot, fenetre);
                    if (founded){
                        fenetre.getToolBar().setReplaceVisible(true);
                    }else{
                        fenetre.allTextFieldToLabel();
                    }

                }else{
                    Recherche.removeHighLights(fenetre);
                    fenetre.getToolBar().setReplaceVisible(false);
                    fenetre.allTextFieldToLabel();
                }
                break;

            case REMPLACER:

                String oldWord = fenetre.getToolBar().getSearchField().getText();
                String newWord = fenetre.getToolBar().getReplaceField().getText();
                if (!oldWord.equals("") && !newWord.equals("")){
                    fenetre.allLabelToTextfield();
                    Recherche.remplacerParMot(oldWord, newWord, fenetre);
                    Recherche.removeHighLights(fenetre);
                    fenetre.getToolBar().getSearchField().setText("");
                    fenetre.getToolBar().getReplaceField().setText("");
                    fenetre.getToolBar().setReplaceVisible(false);
                    fenetre.allTextFieldToLabel();


                }else if (oldWord.equals("")){
                    Recherche.removeHighLights(fenetre);
                    fenetre.getToolBar().getReplaceField().setText("");
                    fenetre.getToolBar().setReplaceVisible(false);
                    fenetre.allTextFieldToLabel();
                }
                break;
            default:
                break;
        }

    }

}