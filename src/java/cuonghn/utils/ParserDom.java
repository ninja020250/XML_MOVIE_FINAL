/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author nhatc
 */
public class ParserDom {

    public static void parseListHTML(String filePath, String uri) {
//        declare variable
        Writer writer = null;
        boolean check = false;
        int count = 1;
        int countDiv = 1;
        int countUl = 0;
        int coutLi = 0;
//       end declare variable
        try {
            // for hết toàn bộ uri mà đã định danh sẵn để đọc nội dung từng uri đó ra thành file html

            //khai báo connection với phiên bản chrome sử dụng để đọc từng line của html ra đưa vô inputLine
            URL url = new URL(uri);
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-agent", "Chrome/61.0.3163.100 (compatible; MSIE 6.0; Windows NT 5.0");
            InputStream is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String inputLine;
            String htmlContent = "";
            boolean begin = false, end = false;
            while ((inputLine = br.readLine().trim()) != null) {
                if (inputLine.contains("<body>")) {
                    begin = true;
                    htmlContent = htmlContent + inputLine;
                    continue;
                }
                if (begin && !end) {
                    if (inputLine.contains("</body>")) {

                        break;
                    }
                    htmlContent = htmlContent + inputLine;
                }
            }
            String welformedHTML = TextUtilities.refineHtml(htmlContent); //welform html truoc khi parse 
            String[] wellformedLine = welformedHTML.split("\\r?\\n");
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath), "UTF-8"));
            for (int j = 0; j < wellformedLine.length; j++) {
                writer.write(wellformedLine[j] + "\n");
            }
            br.close();
            writer.close();
        } catch (Exception ex) {
            Logger.getLogger(ParserDom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String parseListHTMLToStringCPN(String uri) {
//        declare variable
        Writer writer = null;
        boolean check = false;
        int count = 1;
        int countDiv = 1;
        int countUl = 0;
        int coutLi = 0;
//       end declare variable
        try {
            // for hết toàn bộ uri mà đã định danh sẵn để đọc nội dung từng uri đó ra thành file html

            //khai báo connection với phiên bản chrome sử dụng để đọc từng line của html ra đưa vô inputLine
            URL url = new URL(uri);
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-agent", "Chrome/61.0.3163.100 (compatible; MSIE 6.0; Windows NT 5.0");
            InputStream is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String inputLine;
            String htmlContent = "";
            boolean begin = false, end = false;
            while ((inputLine = br.readLine().trim()) != null) {
                if (inputLine.contains("<body>")) {
                    begin = true;
                    htmlContent = htmlContent + inputLine;
                    continue;
                }
                if (begin && !end) {
                    if (inputLine.contains("</body>")) {

                        break;
                    }
                    htmlContent = htmlContent + inputLine;
                }
            }
            String welformedHTML = TextUtilities.refineHtmlCPN(htmlContent); //welform html truoc khi parse 
            br.close();
            is.close();
            return welformedHTML;
        } catch (Exception ex) {
            Logger.getLogger(ParserDom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//
//    public static float GetReted(String host, String i) {
//        String a = host + ":3004/movie/detail?id=" + i + "&logged=0";
//        float result = 0;
//        try {
//            URL url = new URL(a);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/json");
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    (conn.getInputStream())));
//            String output;
//            while ((output = br.readLine()) != null) {
//                String[] words = output.split(":");
//                return TextUtilities.getfloatByRegex(words[words.length - 1]);
//            }
//            conn.disconnect();
//
//        } catch (Exception e) {
//        }
//        return result;
//    }

    public static String parseListHTMLToString(String uri) {
//        declare variable
        Writer writer = null;
        boolean check = false;
        int count = 1;
        int countDiv = 1;
        int countUl = 0;
        int coutLi = 0;
//       end declare variable
        try {
            // for hết toàn bộ uri mà đã định danh sẵn để đọc nội dung từng uri đó ra thành file html

            //khai báo connection với phiên bản chrome sử dụng để đọc từng line của html ra đưa vô inputLine
            URL url = new URL(uri);
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-agent", "Chrome/61.0.3163.100 (compatible; MSIE 6.0; Windows NT 5.0");
            InputStream is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String inputLine;
            String htmlContent = "";
            boolean begin = false, end = false;
            while ((inputLine = br.readLine().trim()) != null) {
                if (inputLine.contains("<body>")) {
                    begin = true;
                    htmlContent = htmlContent + inputLine;
                    continue;
                }
                if (begin && !end) {
                    if (inputLine.contains("</body>")) {

                        break;
                    }
                    htmlContent = htmlContent + inputLine;
                }
            }
            String welformedHTML = TextUtilities.refineHtml(htmlContent); //welform html truoc khi parse 
            br.close();
            is.close();
            return welformedHTML;
        } catch (Exception ex) {
            Logger.getLogger(ParserDom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String parseStringToUTF8Uri(String uri) throws UnsupportedEncodingException {
        String[] arr = uri.split("\\=");
        String url = arr[0] + "=" + URLEncoder.encode(arr[1], "UTF-8");
        return url;
    }

    public static Document parserXMLFromFile(String filePath) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(filePath);
        return doc;
    }

    public static Document getDocumentFromStringXML(String source) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(source));
        Document doc = builder.parse(is);
        return doc;
    }

    public static String getStringFromHTMLByXPath(String host, String xpathString) throws SAXException, ParserConfigurationException, IOException {
        String result = "";
        String wellformerHomePage = parseListHTMLToString(host);
        Document doc = ParserDom.getDocumentFromStringXML(wellformerHomePage);

        XPath xpath = XMLUtilities.createXPath();
        String exp = xpathString;
        try {
            result = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
        } catch (Exception e) {
            System.out.println("Khong the lay text tu xpath nay: detail->>>>>" + e.getMessage());
        }
        System.out.println(result);
        return result;
    }
}
