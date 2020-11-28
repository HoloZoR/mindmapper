package Controleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Model.Liaison;
import Model.Noeud;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class SaveAndLoad {

    public static void ModelToXml(ArrayList<Noeud> noeuds, String pathname) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document xml = builder.newDocument();
            Element mindMap = xml.createElement("MindMap");
            xml.appendChild(mindMap);

            Element noeudsXml = xml.createElement("Noeuds");
            mindMap.appendChild(noeudsXml);

            Element liaisonsXml = xml.createElement("Liaisons");
            mindMap.appendChild(liaisonsXml);
            //############# DEBUT DES AJOUT D'ELEMENTS################################

            for (Noeud n : noeuds) {

                Element titre = xml.createElement("titre");
                titre.appendChild(xml.createTextNode(n.getTitre()));

                Element couleur = xml.createElement("Couleur");
                couleur.appendChild(xml.createTextNode(n.getCouleur()));

                Element description = xml.createElement("Description");
                description.appendChild(xml.createTextNode(n.getDescription()));

                Element forme = xml.createElement("Forme");
                forme.appendChild(xml.createTextNode(n.getForme()));

                Element x = xml.createElement("X");
                x.appendChild(xml.createTextNode("" + n.getX()));

                Element y = xml.createElement("Y");
                y.appendChild(xml.createTextNode("" + n.getY()));

                Element noeud = xml.createElement("Noeud");


                Attr id = xml.createAttribute("id");
                id.setValue("" + n.getId());
                noeud.setAttributeNode(id);

                noeud.appendChild(titre);
                noeud.appendChild(couleur);
                noeud.appendChild(description);
                noeud.appendChild(forme);
                noeud.appendChild(x);
                noeud.appendChild(y);

                noeudsXml.appendChild(noeud);

                for (Liaison l : n.getLiaisonFils()) {

//                    Element couleurLiaison = xml.createElement("Couleur");
//                    couleurLiaison.appendChild(xml.createTextNode("Rouge"));

                    Element noeudPere = xml.createElement("Pere");
                    noeudPere.appendChild(xml.createTextNode("" + l.getNoeudPere().getId()));

                    Element noeudFils = xml.createElement("Fils");
                    noeudFils.appendChild(xml.createTextNode("" + l.getNoeudFils().getId()));

                    Element liaison = xml.createElement("Liaison");
                    noeudsXml.appendChild(noeud);

                    liaison.appendChild(noeudPere);
                    liaison.appendChild(noeudFils);

                    liaisonsXml.appendChild(liaison);
                }
            }
            //############# FIN DES AJOUT D'ELEMENTS################################
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(xml);
            StreamResult resultat = new StreamResult(new File(pathname + ".xml"));
            transformer.transform(source, resultat);
            System.out.println("Fichier sauvegardé avec succès!");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }

    public static ArrayList<Noeud> XmlToModel(String pathname) {

        HashMap<Integer, Noeud> noeudsMap = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fileXML = new File(pathname);

            Document xml = builder.parse(fileXML);

            Element MindMap = xml.getDocumentElement();

            Node noeudsXml = MindMap.getFirstChild();
            NodeList listNoeuds = noeudsXml.getChildNodes();

            for (int i = 0; i < listNoeuds.getLength(); i++) {
                Node noeudXml = listNoeuds.item(i);

                Node titre = noeudXml.getFirstChild();
                Node couleur = titre.getNextSibling();
                Node description = couleur.getNextSibling();
                Node forme = description.getNextSibling();
                Node x = forme.getNextSibling();
                Node y = x.getNextSibling();

                Noeud noeud = new Noeud(
                        titre.getTextContent(),
                        couleur.getTextContent(),
                        description.getTextContent(),
                        forme.getTextContent(),
                        Double.parseDouble(x.getTextContent()),
                        Double.parseDouble(y.getTextContent())
                );

                NamedNodeMap attr = noeudXml.getAttributes();
                noeud.setId(Integer.parseInt(attr.getNamedItem("id").getTextContent()));
                noeudsMap.put(noeud.getId(), noeud);
            }

            Node liaisonsXml = MindMap.getLastChild();
            NodeList listLiaisons = liaisonsXml.getChildNodes();

            for (int i = 0; i < listLiaisons.getLength(); i++) {
                Node liaisonXml = listLiaisons.item(i);

                Node noeudIdPere = liaisonXml.getFirstChild();
                Node noeudIdFils = noeudIdPere.getNextSibling();

                Liaison liaison = new Liaison(
                        noeudsMap.get(Integer.parseInt(noeudIdPere.getTextContent())),
                        noeudsMap.get(Integer.parseInt(noeudIdFils.getTextContent()))
                );
                noeudsMap.get(Integer.parseInt(noeudIdPere.getTextContent())).addLiaisonFils(liaison);
            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Vous n'avez pas choisi un fichier valid !");

        }

        return new ArrayList<>(noeudsMap.values());
    }

    // BROUILLON
    public static void ChangeXMLFile() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fileXML = new File("MindMaps.xml");

            Document xml = builder.parse(fileXML);
            Element MindMaps = xml.getDocumentElement();

            //############# DEBUT DES MODFICATIONS ################################
            Node noeud = MindMaps.getFirstChild().getFirstChild().getFirstChild();
            NamedNodeMap attributes = noeud.getAttributes();

            Node attr = attributes.getNamedItem("couleur");
            attr.setTextContent("vert");

            Element age = xml.createElement("age");
            age.appendChild(xml.createTextNode("28"));
            noeud.appendChild(age);
            //############# FIN DES MODFICATION #################################
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(xml);
            StreamResult resultat = new StreamResult(new File("MindMaps.xml"));
            transformer.transform(source, resultat);
            System.out.println("Fichier modifier avec succès!");


        } catch (ParserConfigurationException | IOException | TransformerException | SAXException e) {
            e.printStackTrace();
        }
    }

    // BROUILLON
    public static void AfficherXMLFile() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fileXML = new File("MindMap.xml");
            Document xml = builder.parse(fileXML);
            Element MindMap = xml.getDocumentElement();
            System.out.println(MindMap.getFirstChild());

            Node noeuds = MindMap.getFirstChild();
            System.out.println("\t" + noeuds.getNodeName());
            NodeList noeudsList = noeuds.getChildNodes();
            for (int n = 0; n < noeudsList.getLength(); n++) {
                Node noeud = noeudsList.item(n);
                System.out.println("\t\t" + noeud.getNodeName());
                NodeList attributesList = noeud.getChildNodes();
                for (int a = 0; a < attributesList.getLength(); a++) {
                    Node attribut = attributesList.item(a);
                    System.out.println("\t\t\t" + attribut.getNodeName() + " = " + attribut.getTextContent());
                }
            }
            Node liaisons = MindMap.getFirstChild();
            NodeList liaisonsList = liaisons.getChildNodes();
            for (int l = 0; l < liaisonsList.getLength(); l++) {
                Node liaison = liaisonsList.item(l);
                System.out.println("\t\t" + liaison.getNodeName());
                NodeList attributesList = liaison.getChildNodes();
                for (int a = 0; a < attributesList.getLength(); a++) {
                    Node attribut = attributesList.item(a);
                    System.out.println("\t\t\t" + attribut.getNodeName());
                }
            }
            //############# FIN DES l'affichage #################################


        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    // BROUILLON
    public static void CreateXMLFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document xml = builder.newDocument();
            Element mindMaps = xml.createElement("MindMaps");
            xml.appendChild(mindMaps);
            //############# DEBUT DES AJOUT D'ELEMENTS################################
            // STRCUTURE
            Element mindMap = xml.createElement("MindMap");
            mindMaps.appendChild(mindMap);

            Element noeuds = xml.createElement("Noeuds");
            mindMap.appendChild(noeuds);

            Element liaisons = xml.createElement("Liaisons");
            mindMap.appendChild(liaisons);

            // CONTENU

            //NOEUDS

            Element titre = xml.createElement("titre");
            titre.appendChild(xml.createTextNode("Noeud principale"));

            Element couleur = xml.createElement("Couleur");
            couleur.appendChild(xml.createTextNode("Rouge"));

            Element description = xml.createElement("Description");
            description.appendChild(xml.createTextNode("je suis entrant d'ecrire une description !"));

            Element forme = xml.createElement("Forme");
            forme.appendChild(xml.createTextNode("Rectangle"));

            Element x = xml.createElement("X");
            x.appendChild(xml.createTextNode("10"));

            Element y = xml.createElement("Y");
            y.appendChild(xml.createTextNode("20"));

            Element noeud1 = xml.createElement("Noeud");
            noeuds.appendChild(noeud1);

            Attr id = xml.createAttribute("id");
            id.setValue("1");
            noeud1.setAttributeNode(id);

            noeud1.appendChild(titre);
            noeud1.appendChild(couleur);
            noeud1.appendChild(description);
            noeud1.appendChild(forme);
            noeud1.appendChild(x);
            noeud1.appendChild(y);

            Element noeud2 = xml.createElement("Noeud");
            noeuds.appendChild(noeud2);

            Attr id2 = xml.createAttribute("id");
            id2.setValue("2");
            noeud2.setAttributeNode(id2);

            noeud2.appendChild(titre);
            noeud2.appendChild(couleur);
            noeud2.appendChild(description);
            noeud2.appendChild(forme);
            noeud2.appendChild(x);
            noeud2.appendChild(y);

            Element noeud3 = xml.createElement("Noeud");
            noeuds.appendChild(noeud3);

            Attr id3 = xml.createAttribute("id");
            id3.setValue("3");
            noeud3.setAttributeNode(id3);

            noeud3.appendChild(titre);
            noeud3.appendChild(couleur);
            noeud3.appendChild(description);
            noeud3.appendChild(forme);
            noeud3.appendChild(x);
            noeud3.appendChild(y);


            // LIAISONS
            Element type = xml.createElement("titre");
            titre.appendChild(xml.createTextNode("Noeud principale"));

            Element couleurLiaison = xml.createElement("Couleur");
            couleurLiaison.appendChild(xml.createTextNode("Rouge"));

            Element noeudPere = xml.createElement("NoeudPere");
            description.appendChild(xml.createTextNode("je suis entrant d'ecrire une description !"));

            Element noeudsFils = xml.createElement("NoeudsFils");
            forme.appendChild(xml.createTextNode("Rectangle"));


            //############# FIN DES AJOUT D'ELEMENTS################################
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(xml);
            StreamResult resultat = new StreamResult(new File("MindMaps.xml"));
            transformer.transform(source, resultat);
            System.out.println("Fichier sauvegardé avec succès!");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
}