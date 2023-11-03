package tema1.p6;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Main {
    public static void main(String[] args) {
        primeraParte();
        //segundaParte();
        terceraParte();
        //La función del último ejercicio la llamo desde terceraParte() pasándole el Document como argumento.
    }
    /*
     * Intenta hacer la lectura del archivo utilizando los métodos:
     * getElementsbyTagName(). Al ser muy extenso sólo será
     * necesario sacar de cada carácter: id, name y titles.
     */

    public static void primeraParte() {
        System.out.println("\nLECTURA CON  getElementsbyTagName()\n");
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document documento = builder.parse(new File("DAM_AD_UD01_P6_GOT_Ini.xml"));
            documento.getDocumentElement().normalize();
            NodeList personajes = documento.getElementsByTagName("character");
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < personajes.getLength(); i++) {
                Node personaje = personajes.item(i);
                Element elemento = (Element) personaje;

                
                sb.append(devuelveNivel("id") + elemento.getElementsByTagName("id").item(0).getNodeName() + "->" +
                        elemento.getElementsByTagName("id").item(0).getTextContent() + "\n");

                sb.append(devuelveNivel("name") + elemento.getElementsByTagName("name").item(0).getNodeName() + "->" +
                        elemento.getElementsByTagName("name").item(0).getTextContent() + "\n");

                NodeList titles = elemento.getElementsByTagName("title");
                for (int j = 0; j < titles.getLength(); j++) {
                    sb.append(devuelveNivel("title") + titles.item(j).getNodeName() + "->" +
                            titles.item(j).getTextContent() + "\n");
                }
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    // Con esta función creo que simplifico la manera de indentar niveles
    public static String devuelveNivel(String nivel) {
        String level = null;
        switch (nivel) {
            case "GOT":
                level = " ".repeat(0);
                break;
            case "id":
                level = " ".repeat(1);
                break;

            case "name":
                level = " ".repeat(2);
                break;

            case "gender":
            case "culture":
            case "born":
            case "died":
            case "alive":
            case "father":
            case "mother":
                level = " ".repeat(4);
                break;

            case "title":
            case "alias":
            case "allegiances":
                level = " ".repeat(6);
                break;

            case "book":
            case "tvSeries":
            case "season":
                level = " ".repeat(8);
                break;

            default:
                break;
        }

        return level;
    }

    /*
     * getChildNodes() de forma iterativa hasta el nivel que necesites para sacar
     * todo el documento, y sin getElementsbyTagName.
     * (2 puntos) getChildNodes() de forma recursiva y sin getElementsbyTagName, lo
     * que no permitiría leer un documento XML sin conocer su estructura. (2 puntos)
     */

    public static void segundaParte() {
        System.out.println("\nLECTURA CON getChildNodes() de forma iterativa y recursiva\n");
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File("DAM_AD_UD01_P6_GOT_Ini.xml"));

            // Llamo a la función
            recorreNodos(document.getDocumentElement(), 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Recursividad para procesar nodos
    private static void recorreNodos(Node node, int nivel) {
        if (node.getNodeType() == Node.ELEMENT_NODE && node.hasChildNodes()) {
            Node nodoHijo = node.getFirstChild();

            if (nodoHijo.getNodeType() == Node.TEXT_NODE) {
                Element element = (Element) node;

                imprimeNivel(element.getNodeName(), element.getTextContent());
            }
            // Imprime por pantalla el texto del nodo. No he sabido hacerlo con
            // StringBuilder

            // Procesa los nodos hijos recursivamente
            NodeList hijos = node.getChildNodes();
            for (int i = 0; i < hijos.getLength(); i++) {
                recorreNodos(hijos.item(i), nivel + 1);
            }

        }
    }

    private static void imprimeNivel(String nodeName, String textContent) {
        if(nodeName.equals("GOT")){
            System.out.println(nodeName);
        }
        switch (nodeName) {
            case "id":
            case "name":
            case "gender":
            case "culture":
            case "born":
            case "died":
            case "alive":
            case "father":
            case "mother":
            case "title":
            case "alias":
            case "allegiances":
            case "book":
            case "tvSeries":
                System.out.println(devuelveNivel(nodeName) + nodeName + "->" + textContent);

            default:
                break;
        }
        

    }

    public static String interprete(String personaje) {
        String interprete = null;
        switch (personaje) {
            case "Arya Stark":
                interprete = "Alfie Allen";
                break;

            case "Brandon Stark":
                interprete = "Isaac Hempstead-Wright";
                break;

            case "Rickon Stark":
                interprete = "Art Parkinson";
                break;

            case "Robb Stark":
                interprete = "Richard Madden";
                break;

            case "Sansa Stark":
                interprete = "Sophie Turner";
                break;
        }
        return interprete;
    }
    /*
     * En la siguiente parte del código, añade a cada personaje el interprete en la
     * serie con la etiqueta <playedBy>.
     */

    public static void terceraParte() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document documento = builder.parse(new File("DAM_AD_UD01_P6_GOT_Ini.xml"));
            documento.getDocumentElement().normalize();
            NodeList personajes = documento.getElementsByTagName("character");

            for (int i = 0; i < personajes.getLength(); i++) {
                if (personajes.item(i).getNodeName().equals("character")) {
                    Element nombreNodo = documento.createElement("playedBy");
                    String nombre = documento.getElementsByTagName("name").item(i).getTextContent();

                    if (nombre.equals("Arya Stark") || nombre.equals("Robb Stark") || nombre.equals("Sansa Stark")
                            || nombre.equals("Rickon Stark") || nombre.equals("Brandon Stark")) {
                        Text actor = documento.createTextNode(interprete(nombre));

                        documento.getElementsByTagName("character").item(i).appendChild(nombreNodo).appendChild(actor);
                    }
                }

            }

            cuartaParte(documento);

        } catch (Exception e) {

        }
    }

    /*
     * Verás que falta uno de los hermanos Stark. Haz que el programa complete XML
     * con el carácter que falta
     * y con la siguiente información (se omite información obvia)
     * 
     * EN ESTA FUNCIÓN TAMBIÉN PONGO EL CÓDIGO DEL ÚLTIMO EJERICICIO.
     * Una vez relleno, lo tenemos que guardar en el archivo XML
     */

     public static void cuartaParte(Document documento) {
        try{
            //Creo el nodo ID
            documento.getDocumentElement().normalize();
            
            Element estructura = documento.getDocumentElement();
            Element raiz = documento.createElement("character");

            Element id = documento.createElement("id");
            Text idText = documento.createTextNode("583");
            raiz.appendChild(id).appendChild(idText);

            //Creo el nodo nombre
            Element nombre = documento.createElement("name");
            Text nombreText = documento.createTextNode("Jon Snow");
            raiz.appendChild(nombre).appendChild(nombreText);

            //Creo el nodo gender
            Element genero = documento.createElement("gender");
            Text generoText = documento.createTextNode("Male");
            raiz.appendChild(genero).appendChild(generoText);

            //Creo el nodo culture
            Element cultura = documento.createElement("culture");
            Text culturaText = documento.createTextNode("Northmen");
            raiz.appendChild(cultura).appendChild(culturaText);

            //Creo el nodo born
            Element nacimiento = documento.createElement("born");
            Text nacimientoText = documento.createTextNode("In 283 AC, at Winterfell");
            raiz.appendChild(nacimiento).appendChild(nacimientoText);

            //Creo el nodo died
            Element muerte = documento.createElement("died");
            raiz.appendChild(muerte);

            //Creo el nodo alive
            Element vivo = documento.createElement("alive");
            Text vivoText = documento.createTextNode("true");
            raiz.appendChild(vivo).appendChild(vivoText);

            //Creo el nodo titles
            Element titulos = documento.createElement("titles");
            raiz.appendChild(titulos);

            //Creo los nodos title
            Element titulo1 = documento.createElement("title");
            Text titulo1Text = documento.createTextNode("Lord Commander of the Night's Watch");
            titulos.appendChild(titulo1).appendChild(titulo1Text);

            Element titulo2 = documento.createElement("title");
            Text titulo2Text = documento.createTextNode("King in the North");
            titulos.appendChild(titulo2).appendChild(titulo2Text);

            //Creo el nodo aliases
            Element aliases = documento.createElement("aliases");
            raiz.appendChild(aliases);

            //Creo los nodos alias
            Element alias1 = documento.createElement("alias");
            Text alias1Text = documento.createTextNode("Lord Snow");
            aliases.appendChild(alias1).appendChild(alias1Text);

            Element alias2 = documento.createElement("alias");
            Text alias2Text = documento.createTextNode("Ned Stark's Bastard");
            aliases.appendChild(alias2).appendChild(alias2Text);

            Element alias3 = documento.createElement("alias");
            Text alias3Text = documento.createTextNode("The Snow of Winterfell");
            aliases.appendChild(alias3).appendChild(alias3Text);

            Element alias4 = documento.createElement("alias");
            Text alias4Text = documento.createTextNode("The Crow-Come-Over");
            aliases.appendChild(alias4).appendChild(alias4Text);

            Element alias5 = documento.createElement("alias");
            Text alias5Text = documento.createTextNode("The 998th Lord Commander of the Night's Watch");
            aliases.appendChild(alias5).appendChild(alias5Text);

            Element alias6 = documento.createElement("alias");
            Text alias6Text = documento.createTextNode("The Bastard of Winterfell");
            aliases.appendChild(alias6).appendChild(alias6Text);

            Element alias7 = documento.createElement("alias");
            Text alias7Text = documento.createTextNode("The Black Bastard of The Wall");
            aliases.appendChild(alias7).appendChild(alias7Text);

            Element alias8 = documento.createElement("alias");
            Text alias8Text = documento.createTextNode("Lord Crow");
            aliases.appendChild(alias8).appendChild(alias8Text);

            //Creo el nodo father
            Element padre = documento.createElement("father");
            Text padreText = documento.createTextNode("Rhaegar Targaryen");
            raiz.appendChild(padre).appendChild(padreText);

            //Creo el nodo mother
            Element madre = documento.createElement("mother");
            Text madreText = documento.createTextNode("Lyanna Stark");
            raiz.appendChild(madre).appendChild(madreText);

            //Creo el nodo spouse
            Element parienta = documento.createElement("spouse");
            raiz.appendChild(parienta);

            //Creo el nodo allegiances
            Element alianzas = documento.createElement("allegiances");
            Text alianzasText = documento.createTextNode("Night's Watch");
            raiz.appendChild(alianzas).appendChild(alianzasText);

            //Creo el nodo libros
            Element libros = documento.createElement("books");
            raiz.appendChild(libros);

            //Creo los nodos libros
            Element libro1 = documento.createElement("book");
            Text libro1Text = documento.createTextNode("A Game of Thrones (1996)");
            libros.appendChild(libro1).appendChild(libro1Text);

            Element libro2 = documento.createElement("book");
            Text libro2Text = documento.createTextNode("A Clash of Kings (1998)");
            libros.appendChild(libro2).appendChild(libro2Text);

            Element libro3 = documento.createElement("book");
            Text libro3Text =documento.createTextNode("A Storm of Swords (2000)");
            libros.appendChild(libro3).appendChild(libro3Text);

            Element libro4 = documento.createElement("book");
            Text libro4Text =documento.createTextNode("A Feast for Crows (2005)");
            libros.appendChild(libro4).appendChild(libro4Text);

            Element libro5 = documento.createElement("book");
            Text libro5Text =documento.createTextNode("A Feast for Crows (2005)");
            libros.appendChild(libro5).appendChild(libro5Text);

            //Creo el nodo series
            Element series = documento.createElement("tvSeries");
            raiz.appendChild(series);

            //Creo los nodos series
            Element serie1 = documento.createElement("tvSeries");
            Text serie1Text = documento.createTextNode("Season 1");
            series.appendChild(serie1).appendChild(serie1Text);

            Element serie2 = documento.createElement("tvSeries");
            Text serie2Text = documento.createTextNode("Season 2");
            series.appendChild(serie2).appendChild(serie2Text);

            Element serie3 = documento.createElement("tvSeries");
            Text serie3Text =documento.createTextNode("Season 3");
            series.appendChild(serie3).appendChild(serie3Text);

            Element serie4 = documento.createElement("tvSeries");
            Text serie4Text =documento.createTextNode("Season 4");
            series.appendChild(serie4).appendChild(serie4Text);

            Element serie5 = documento.createElement("tvSeries");
            Text serie5Text =documento.createTextNode("Season 5");
            series.appendChild(serie5).appendChild(serie5Text);

            Element serie6 = documento.createElement("tvSeries");
            Text serie6Text =documento.createTextNode("Season 6");
            series.appendChild(serie6).appendChild(serie6Text);

            Element serie7 = documento.createElement("tvSeries");
            Text serie7Text =documento.createTextNode("Season 7");
            series.appendChild(serie7).appendChild(serie7Text);
            
            Element serie8 = documento.createElement("tvSeries");
            Text serie8Text =documento.createTextNode("Season 8");
            series.appendChild(serie8).appendChild(serie8Text);

            //Creo el nodo playedBy
            Element prota = documento.createElement("playedBy");
            Text protaText = documento.createTextNode("Kit Harington");
            raiz.appendChild(prota).appendChild(protaText);

            estructura.appendChild(raiz);
            DOMSource fuente = new DOMSource(estructura);
            Result resultado = new StreamResult(new File("GOT_modified.xml"));
            Transformer transformador = TransformerFactory.newInstance().newTransformer();
            /*
             * La salida que obtenía para el nuevo personaje era en una sola línea. en la documentación de java encontré la solución
             * para indentar por niveles aunque no queda con los mismos espacios que los demás personajes.
             * https://docs.oracle.com/javase/8/docs/api/javax/xml/transform/Transformer.html#setOutputProperty-java.lang.String-java.lang.String-
             */
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.transform(fuente, resultado);

        }catch(Exception e){
            e.printStackTrace();
        }

     }

}