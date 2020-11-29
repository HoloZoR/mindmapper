package Controleur;

import Model.Liaison;
import Model.Noeud;
import Utils.Message;
import Utils.Observateur;
import Vue.FenetrePrincipal;
import javax.swing.*;
import java.awt.*;

import java.io.File;
import java.util.ArrayList;

public class Controleur implements Observateur {
    private FenetrePrincipal fenetre;
    private String pathname = "";
/**/
    public Controleur() {
        this.fenetre = new FenetrePrincipal();
        fenetre.setVisible(true);
        fenetre.addObsevateur(this);
        fenetre.getToolBar().addObsevateur(this);
    }

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
//                ArrayList<Noeud> noeads = new ArrayList<>();
//                noeads = SaveAndLoad.XmlToModel(pathname);
//                for (Noeud node: noeads) {
//                    node.affiche();
//                }
                SaveAndLoad.ModelToView(SaveAndLoad.XmlToModel(pathname),fenetre);
                fenetre.revalidate();
                break;
            case SAVE:
                if (pathname.isEmpty()) {
                    int save = fc.showSaveDialog(fenetre);

                    if (save == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        pathname = file.getAbsolutePath();
                    } else {
                        break;
                    }
                }
                SaveAndLoad.ModelToXml(SaveAndLoad.ViewToModel(fenetre), pathname);

                break;
            case SAVEAS:
                int saveAs = fc.showSaveDialog(fenetre);

                if (saveAs == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    pathname = file.getAbsolutePath();
                } else {
                    break;
                }
                SaveAndLoad.ModelToXml(SaveAndLoad.ViewToModel(fenetre), pathname);

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
                Recherche.removeHighLights(fenetre);
                String mot = fenetre.getToolBar().getSearchField().getText();
                if (!mot.equals("")){
                    boolean founded = Recherche.rechercherMot(mot, fenetre);
                    if (founded){
                        fenetre.getToolBar().setReplaceVisible(true);
                    }

                }else{
                    Recherche.removeHighLights(fenetre);
                    fenetre.getToolBar().setReplaceVisible(false);
                }
                break;
            case REMPLACER:

                String oldWord = fenetre.getToolBar().getSearchField().getText();
                String newWord = fenetre.getToolBar().getReplaceField().getText();
                if (!oldWord.equals("") && !newWord.equals("")){
                    Recherche.rempalcerParMot(oldWord, newWord, fenetre);
                    Recherche.removeHighLights(fenetre);
                    fenetre.getToolBar().getSearchField().setText("");
                    fenetre.getToolBar().getReplaceField().setText("");
                    fenetre.getToolBar().setReplaceVisible(false);

                }else if (oldWord.equals("")){
                    Recherche.removeHighLights(fenetre);
                    fenetre.getToolBar().getReplaceField().setText("");
                    fenetre.getToolBar().setReplaceVisible(false);
                }
                break;
            default:
                break;
        }

    }

}

// POUR TESTER :
//
//    Noeud n = new Noeud("pourquoi?", "rouge", "wesh les gens", "rectangle", 5, 5);
//    Noeud n1 = new Noeud("pourquoi?", "rouge", "wesh les gens", "rectangle", 5, 5);
//    Noeud n2 = new Noeud("pourquoi?", "rouge", "wesh les gens", "rectangle", 5, 5);
//    Noeud n1_1 = new Noeud("pourquoi?", "rouge", "wesh les gens", "rectangle", 5, 5);
//    Noeud n1_2 = new Noeud("pourquoi?", "rouge", "wesh les gens", "rectangle", 5, 5);
//    Noeud n2_1 = new Noeud("pourquoi?", "rouge", "wesh les gens", "rectangle", 5, 5);
//    Noeud n3 = new Noeud("pourquoi?", "rouge", "wesh les gens", "rectangle", 5, 5);
//
//    Liaison l1 = new Liaison(n, n1);
//    Liaison l2 = new Liaison(n, n2);
//    Liaison l3 = new Liaison(n, n3);
//    Liaison l2_1 = new Liaison(n2, n2_1);
//    Liaison l1_1 = new Liaison(n1, n1_1);
//    Liaison l1_2 = new Liaison( n1, n1_2);
//
//    ArrayList<Liaison> ln = new ArrayList<>();
//    ArrayList<Liaison> ln1 = new ArrayList<>();
//    ArrayList<Liaison> ln2 = new ArrayList<>();
//        ln.add(l1);
//                ln.add(l2);
//                ln.add(l3);
//                ln1.add(l1_1);
//                ln1.add(l1_2);
//                ln2.add(l2_1);
//
//                n.setLiaisonFils(ln);
//                n1.setLiaisonFils(ln1);
//                n2.setLiaisonFils(ln2);
//
//                ArrayList<Noeud> noeuds = new ArrayList<>();
//        noeuds.add(n);
//        noeuds.add(n1);
//        noeuds.add(n1_1);
//        noeuds.add(n2);
//        noeuds.add(n2_1);
//        noeuds.add(n1_2);
//        noeuds.add(n3);
