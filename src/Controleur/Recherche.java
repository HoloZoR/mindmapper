package Controleur;

import Vue.FenetrePrincipal;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.util.Map;

public class Recherche {

    public static Boolean rechercherMot(String mot, FenetrePrincipal fenetre){
        Boolean founded = false;
        for (Map.Entry m : fenetre.getNoeuds().entrySet()) {
            JPanel panel = (JPanel) m.getValue();
            JTextField titre = (JTextField) panel.getComponent(0);
            JTextArea description = (JTextArea) panel.getComponent(1);

            String textTitre = titre.getText().toLowerCase();
            String textDescription = description.getText().toLowerCase();
            StringBuilder titrebuilder = new StringBuilder(textTitre);
            StringBuilder descriptionbuilder = new StringBuilder(textDescription);

            // recherche sur les titres
            int index = textTitre.indexOf(mot.toLowerCase());
            int spaceDeleted = 0;
            while (index != - 1){
                founded=true;
                highLight(titre,spaceDeleted+index,spaceDeleted+mot.length()+index, true);
                titrebuilder.delete(index, mot.length()+index);
                textTitre =titrebuilder.toString();
                spaceDeleted+=mot.length();
                index = textTitre.indexOf(mot.toLowerCase());
            }

            // recherche sur les descriptions
            index = textDescription.indexOf(mot.toLowerCase());
            spaceDeleted = 0;
            while (index != - 1){
                founded=true;
                highLight(description,spaceDeleted+index,spaceDeleted+mot.length()+index, false);
                descriptionbuilder.delete(index, mot.length()+index);
                textDescription =descriptionbuilder.toString();
                spaceDeleted+=mot.length();
                index = textDescription.indexOf(mot.toLowerCase());
            }

        }
        return founded;
    }
    public static void remplacerParMot(String oldWord, String newWord , FenetrePrincipal fenetre){
        for (Map.Entry m : fenetre.getNoeuds().entrySet()) {
            JPanel panel = (JPanel) m.getValue();
            JTextField titre = (JTextField) panel.getComponent(0);
            JTextArea description = (JTextArea) panel.getComponent(1);

            String textTitre = titre.getText().toLowerCase();
            String textDescription = description.getText().toLowerCase();

            textTitre = textTitre.replaceAll("(?i)"+oldWord.toLowerCase(),newWord);
            textDescription = textDescription.replaceAll("(?i)"+oldWord.toLowerCase(),newWord);

            titre.setText(textTitre);
            description.setText(textDescription);

        }
    }
    public static void highLight(Component c,int start,int end, boolean isTextField){
        Highlighter.HighlightPainter cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
        if(isTextField){
            JTextField text = (JTextField) c;
            try {
                text.getHighlighter().addHighlight(start, end, cyanPainter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }else{
            JTextArea text = (JTextArea) c;
            try {
                text.getHighlighter().addHighlight(start, end, cyanPainter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
    public static void removeHighLights(FenetrePrincipal fenetre){

        for (Map.Entry m : fenetre.getNoeuds().entrySet()) {
            JPanel panel = (JPanel) m.getValue();
            JTextField titre = (JTextField) panel.getComponent(0);
            JTextArea description = (JTextArea) panel.getComponent(1);
            titre.getHighlighter().removeAllHighlights();
            description.getHighlighter().removeAllHighlights();
        }

    }

}
