package Controleur;

import Model.Noeud;
import Vue.FenetrePrincipal;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.util.Map;

/**
 * Permet de rechercher dans les noeuds de la carte mental
 */
public class Recherche {

    public static Boolean rechercherMot(String mot, FenetrePrincipal fenetre){
        Boolean founded = false;
        for (Map.Entry m : fenetre.getNoeuds().entrySet()) {
            JTextField textField = (JTextField) m.getKey();
            String text = textField.getText().toLowerCase();
            StringBuilder builder = new StringBuilder(text);

            int index = text.indexOf(mot.toLowerCase());
            int spaceDeleted = 0;
            while (index != - 1){
                founded=true;
                HighLight(textField,spaceDeleted+index,spaceDeleted+mot.length()+index);
                builder.delete(index, mot.length()+index);
                text =builder.toString();
                spaceDeleted+=mot.length();
                index = text.indexOf(mot.toLowerCase());
            }

        }
        return founded;
    }
    public static void rempalcerParMot(String oldWord, String newWord , FenetrePrincipal fenetre){
        for (Map.Entry m : fenetre.getNoeuds().entrySet()) {
            //JPanel panel = (JPanel) m.getValue();
            JTextField textField = (JTextField) m.getKey();

            String text = textField.getText();

            text = text.replaceAll("(?i)"+oldWord.toLowerCase(),newWord);
            textField.setText(text);

        }
    }
    public static void HighLight(JTextField text,int start,int end){
        Highlighter.HighlightPainter cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);

        try {
            text.getHighlighter().addHighlight(start, end, cyanPainter);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    public static void removeHighLights(FenetrePrincipal fenetre){

        for (Map.Entry m : fenetre.getNoeuds().entrySet()) {
            JTextField textField = (JTextField) m.getKey();
            textField.getHighlighter().removeAllHighlights();
        }

    }

}
