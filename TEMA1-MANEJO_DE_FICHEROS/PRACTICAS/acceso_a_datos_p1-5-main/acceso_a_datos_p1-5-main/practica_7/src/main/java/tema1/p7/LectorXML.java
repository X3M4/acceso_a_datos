package tema1.p7;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

public class LectorXML {

    public static void main(String[] args) {
        try {
            // Crear un parser SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Crear un manejador SAX personalizado
            DefaultHandler handler = new DefaultHandler() {
                private StringBuilder currentData = new StringBuilder();

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    currentData.setLength(0);
                }

                public void characters(char[] ch, int start, int length) throws SAXException {
                    currentData.append(ch, start, length);
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (!currentData.toString().trim().isEmpty()) {
                        System.out.println(currentData.toString().trim());
                    }
                }
            };

            // Leer el archivo XML
            InputStream xmlInput = LectorXML.class.getResourceAsStream("/home/chema/Documentos/ACCESO_A_DATOS/TEMA1-MANEJO_DE_FICHEROS/PRACTICAS/acceso_a_datos_p1-5-main/acceso_a_datos_p1-5-main/DAM_AD_UD01_P6_GOT_Ini.xml");
            saxParser.parse(xmlInput, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//Este código acumula el contenido de texto dentro de las etiquetas y luego lo imprime sin las etiquetas. Asegúrate de reemplazar "got.xml" con la ubicación real de tu archivo XML.





