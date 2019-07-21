/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.utils;

import cuonghn.jaxb.Film;
import cuonghn.jaxb.ListFilm;
import cuonghn.jaxb.config.CrawlerConfig;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

;

/**
 *
 * @author bachtoan
 */
public class XMLUtilities implements Serializable {

    public static boolean validateXMLToInsertDB(String xmlString, String schemaFilePath) {
        try {

            SchemaFactory sFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sFactory.newSchema(new File(schemaFilePath));
            Validator validator = schema.newValidator();
            InputSource inputSource = new InputSource(new StringReader(xmlString));
            validator.validate(new SAXSource(inputSource));
            return true;
        } catch (SAXException e) {
            Logger.getLogger(XMLUtilities.class.getName()).log(Level.SEVERE, null, e);
        } catch (FileNotFoundException e) {
            Logger.getLogger(XMLUtilities.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(XMLUtilities.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    public static Document parseDOMFromFile(String filePath) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(filePath);
        return doc;
    }

    public static XPath createXPath() {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        return xPath;
    }

    public static <T> T unmarshallDataFromFile(String filePath, Class<T> clazz)
            throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File f = new File(filePath);
        T obj = clazz.cast(unmarshaller.unmarshal(f));
        return obj;
    }

    public static Film unmarshallingFilm(String XML) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Film.class);
            Unmarshaller u = jc.createUnmarshaller();
            StringReader reader = new StringReader(XML);
            Film item = (Film) u.unmarshal(reader);
            return item;
        } catch (Exception e) {
            System.out.println("Failed when try to unmarshall String xml");
        }
        return null;
    }

    public static void transformDOMtoFile(Node node, String filePath) throws TransformerConfigurationException, TransformerException {
        Source src = new DOMSource(node);
        File file = new File(filePath);
        Result result = new StreamResult(file);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.transform(src, result);

    }

    public static Element createElement(Document doc, String elementName,
            String elementVal, Map<String, String> attributes) {
        if (doc != null) {
            Element element = doc.createElement(elementName);
            if (elementVal != null) {
                element.setTextContent(elementVal);
            }
            if (attributes != null) {
                if (!attributes.isEmpty()) {
                    for (Map.Entry<String, String> entry : attributes.entrySet()) {
                        element.setAttribute(entry.getKey(), entry.getValue());
                    }
                }
            }
            return element;
        }
        return null;
    }

    public static <T> String marsalData(T t) {
        try {
            JAXBContext context = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = context.createMarshaller();
            //marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter sw = new StringWriter();
            marshaller.marshal(t, sw);
            return sw.toString();
        } catch (JAXBException ex) {
            return null;
        }
    }

    public static <T> T unmarshallData(String xml, Class<T> clazz)
            throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        T obj = clazz.cast(unmarshaller.unmarshal(new StringReader(xml)));
        return obj;
    }

//    public static String marshallingToString(Monitor monitor) {
//        try {
//            JAXBContext jaxb = JAXBContext.newInstance(Monitor.class);
//            Marshaller mar = jaxb.createMarshaller();
//            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//
//            StringWriter sw = new StringWriter();
//            mar.marshal(monitor, sw);
//
//            return sw.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    public static void saveToXML(String xmlDataFilePath, Object object) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller ms = context.createMarshaller();
            ms.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            ms.marshal(object, new File(xmlDataFilePath));
        } catch (JAXBException ex) {
            Logger.getLogger(XMLUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static XMLStreamReader parserFiletoXMLcursor(InputStream inputStream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(inputStream);
        return reader;

    }

    public static String getTextContentStaxCursor(String elementName, XMLStreamReader reader) throws XMLStreamException {
        if (reader == null) {
            return "";
        }

        if (elementName == null) {
            return "";
        }
        if (elementName.trim().isEmpty()) {
            return "";
        }
        while (reader.hasNext()) {
            int currentCursor = reader.getEventType();
            if (currentCursor == XMLStreamConstants.START_ELEMENT) {
                String currentTagname = reader.getLocalName();
                if (elementName.equals(currentTagname)) {
                    reader.next();
                    String result = reader.getText();
                    reader.nextTag();
                    return result;
                }// end if element
            }// end if current cursor
            reader.next();
        }// end while
        return "";
    }

    public static CrawlerConfig readXMLCrawlerConfig(String filePath) {
        try {
            CrawlerConfig crawlerConfig = unmarshallDataFromFile(filePath, CrawlerConfig.class);
            return crawlerConfig;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
