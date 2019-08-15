package com.itkang.itkang_utils.utis.base;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * 文档处理
 */
public class DocumentUtil {

    /**
     * Document 转 String
     * @param document org.w3c.dom.Document
     * @return
     */
    public static String documentToString(Document document) {
        String result = null;
        if (document != null) {
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            TransformerFactory factory = TransformerFactory.newInstance();
            try {
                javax.xml.transform.Transformer t = factory.newTransformer();
                t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                t.setOutputProperty(OutputKeys.INDENT, "yes");
                t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,
                t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                t.transform(new DOMSource(document.getDocumentElement()), streamResult);
            } catch (Exception e) {
                System.err.println("XML.toString(Document): " + e);
            }
            result = streamResult.getWriter().toString();
            try {
                stringWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //soapMessage -> String
    public static String soapMessageToString(SOAPMessage soapMessage) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer = factory.newTransformer();
        javax.xml.transform.Source source = soapMessage.getSOAPPart().getContent();
        ByteArrayOutputStream bos = new ByteArrayOutputStream(2048);
        transformer.transform(source, new StreamResult(bos));
        return new String(bos.toByteArray());
    }


    /**
     * obj 转 dom 文档
     * {String, byte[], File, InputSource} to org.w3c.dom.Document
     * @param obj
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static Document objToDocument(Object obj) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        if(obj instanceof org.xml.sax.InputSource)
            return documentBuilder.parse((org.xml.sax.InputSource)obj);
        else if (obj instanceof String){
            InputStream inputStream = new ByteArrayInputStream(obj.toString().getBytes());
            return documentBuilder.parse(inputStream);
        }
        else if(obj instanceof byte[]){
            InputStream inputStream = new ByteArrayInputStream((byte[])obj);
            return documentBuilder.parse(inputStream);
        }
        else if(obj instanceof File){
            return documentBuilder.parse((File) obj);
        }
        return null;
    }

}
