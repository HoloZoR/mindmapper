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
    private ArrayList<Noeud> noeuds;
    private String pathname = "";
/**/
    public Controleur() {
        this.fenetre = new FenetrePrincipal();
        fenetre.setVisible(true);
        fenetre.addObsevateur(this);
        fenetre.getToolBar().addObsevateur(this);
        this.noeuds = new ArrayList<>();
    }

    @Override
    public void traiterMessage(Message m) {
        final JFileChooser fc = new JFileChooser();


        switch (m.type) {
            case NEW:
                break;
            case OUVRIR:

                int open = fc.showOpenDialog(fenetre);

                if (open == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    pathname = file.getAbsolutePath();
                } else {
                    break;
                }
//                ArrayList<Noeud> noeads = new ArrayList<>();
//                noeads = SaveAndLoad.XmlToModel(pathname);
//                for (Noeud node: noeads) {
//                    node.affiche();
//                }
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
//               noeuds = SaveAndLoad.VueToModel(fenetre);
//                SaveAndLoad.ModelToXml(noeuds, pathname);


                break;
            case SAVEAS:
                int saveAs = fc.showSaveDialog(fenetre);

                if (saveAs == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    pathname = file.getAbsolutePath();
                } else {
                    break;
                }
//                SaveAndLoad.ModelToXml(noeuds, pathname);

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
