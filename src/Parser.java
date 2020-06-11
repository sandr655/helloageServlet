
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


public class Parser {
    static String xmlStrDok;
    static String xmlStr;
    static Document doc;
    static String transfer = "ok";

    public void parse(){

            doc = stringToDocument(xmlStrDok);

            if(doc.hasChildNodes()){
                print(doc.getChildNodes());
            }

//            saveXML(xmlPath);
            xmlStr = documentToString(doc);
        System.out.println(xmlStrDok);

    }
    private void print(NodeList nodeList){
        for(int i = 0; i<nodeList.getLength(); i++) {
            Node tempNode = nodeList.item(i);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                if (tempNode.getNodeName().equals("t:CurrentDate")) {
                    addDate(tempNode);
                }

                if(tempNode.hasChildNodes()){
                    print(tempNode.getChildNodes());
                }
            }
        }
    }
    private void addDate(Node node) {
        Node dateNode = doc.createElement("result");
        int per = Integer.parseInt(getTagValue("transfer"));
        if (per % 2 == 0) {
            dateNode.appendChild(doc.createTextNode("ok"));
            node.appendChild(dateNode);
        } else {
            dateNode.appendChild(doc.createTextNode("fail"));
            node.appendChild(dateNode);
        }
    }


    private static String documentToString(Document doc) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            // здесь мы указываем, что хотим убрать XML declaration:
            // тег <?xml ... ?> и его содержимое
//            transfObject.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));

            // возвращаем преобразованный  в строку XML Document
            return writer.getBuffer().toString();

        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return null;
    }
    // парсим строку в XML Document
    private static Document stringToDocument(String xmlStr) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = builderFactory.newDocumentBuilder();
            // парсим переданную на вход строку с XML разметкой
            return docBuilder.parse(new InputSource(new StringReader(xmlStr)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String getTagValue(String tag) {
        NodeList nodeList = doc.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}
