/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.crawler;

import cuonghn.DAO.FilmDAO;
import cuonghn.DAO.TempDataDAO;
import cuonghn.jaxb.Film;
import cuonghn.jaxb.ListFilm;
import cuonghn.jaxb.config.CrawlerConfig;
import cuonghn.utils.Constant;
import cuonghn.utils.ImageUtils;
import cuonghn.utils.ParserDom;
import cuonghn.utils.TextUtilities;
import cuonghn.utils.XMLUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author nhatc
 */
public class PhePhimCrawler {

    private ListFilm listFilm = new ListFilm();
    private String currentCategory = "";
    private List<String> listfilmUrl = new ArrayList<>();
    private String realPath = "";
    private ListFilm validData = null;
    private int totalCrawled = 0;
    private int totalValidData = 0;
    private int totalInvalidData = 0;
    private int totalInserTemp = 0;
    private CrawlerConfig crawlerConfig;
    private int totalRecord = 0;

    public PhePhimCrawler(String realPath) {
//        this.hostName = this.host.replaceAll("http://", "");
        this.realPath = realPath;
        this.crawlerConfig = XMLUtilities.readXMLCrawlerConfig(realPath + "/" + Constant.CRAWLER_CONFIG_FOLDER + "/" + Constant.PHIM33_FILE_NAME);
    }

    public PhePhimCrawler() {
        this.crawlerConfig = XMLUtilities.readXMLCrawlerConfig(realPath + "/" + Constant.CRAWLER_CONFIG_FOLDER + "/" + Constant.PHIM33_FILE_NAME);
    }

    public ListFilm unmarshallingListFilm(String filePath) {
        try {
            JAXBContext jc = JAXBContext.newInstance(ListFilm.class);
            Unmarshaller u = jc.createUnmarshaller();
            File f = new File(filePath);
            ListFilm item = (ListFilm) u.unmarshal(f);
            return item;
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("Failed when try to unmarshall file xml");
        }
        return null;
    }

    public void insertToDatabase() {
        if (validData != null) {
            FilmDAO dao = new FilmDAO(true);
            dao.masterInsertDB(validData);
        }
    }

    public void insertToDatabaseTemp() {
        if (validData != null) {
            FilmDAO dao = new FilmDAO(true);
            TempDataDAO tempDao = new TempDataDAO();
            int countSuccess = tempDao.masterInsertTemp(validData);
            totalInserTemp = countSuccess;
        }
    }

    public ListFilm crawl() {
        if (crawlerConfig != null) {
            try {
                currentCategory = ParserDom.getStringFromHTMLByXPath(crawlerConfig.getWebURL(), crawlerConfig.getXPathMainCategory());
                if (!currentCategory.equals("")) {
                    getListUriOfProduct(currentCategory);
                    if (listfilmUrl.size() > 0) {
                        for (int i = 0; i < listfilmUrl.size(); i++) {
                            totalRecord++;
                            System.out.println("crawling number stt: " + totalRecord);
//                    for (int i = 0; i < 10; i++) {

                            Film film = getFilmFromUri(listfilmUrl.get(i));
                            if (film != null) {
                                film.setFilmURL(listfilmUrl.get(i));
                                listFilm.addFilm(film);
                            }
                        }
                    }
                }
                System.out.println("total record: " + totalRecord);
                System.out.println(currentCategory);
//                XMLUtilities.saveToXML(realPath + "/test/phim33.xml", listFilm);
                totalCrawled = listFilm.getFilm().size();
                return listFilm;
//                validateData();
//            insertToDatabaseTemp();
//            insertToDatabase();
            } catch (Exception e) {
                e.printStackTrace();
//                System.out.println("web loi");
            }
            return listFilm;
        } else {
            Logger.getLogger(PhePhimCrawler.class.getName()).log(Level.SEVERE, null, "Loi cau truc config");
//            System.out.println("Loi cau truc config");
        }
        return listFilm;
    }

    public ListFilm getRawData() {
        if (listFilm.getFilm().size() > 0) {
            return listFilm;
        } else {
            return listFilm;
        }
    }
//    public ListFilm run() {
//        try {
//            currentCategory = ParserDom.getStringFromHTMLByXPath(crawlerConfig.getWebURL(), crawlerConfig.getXPathMainCategory());
//            if (!currentCategory.equals("")) {
//                getListUriOfProduct(currentCategory);
//                if (listfilmUrl.size() > 0) {
//                    for (int i = 0; i < listfilmUrl.size(); i++) {
//                        System.out.println("crawling number stt: " + i);
////                    for (int i = 0; i < 10; i++) {
//                        Film film = getFilmFromUri(listfilmUrl.get(i));
//                        if (film != null) {
//                            listFilm.addFilm(film);
//                        }
//                    }
//                }
//            }
//            System.out.println(currentCategory);
////            XMLUtilities.saveToXML(realPath + "/test/phephim.xml", listFilm);
//            totalCrawled = listFilm.getFilm().size();
//            validateData();
//            insertToDatabaseTemp();
////            insertToDatabase();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("web loi");
//        }
//        return listFilm;
//    }

    public boolean validateData() {
        List<Film> validFilms = new ArrayList<>();
        List<Film> invalidFilms = new ArrayList<>();
        for (int i = 0; i < listFilm.getFilm().size(); i++) {
            Film f = listFilm.getFilm().get(i);
            boolean result = validateFilm(f);
            if (result) {
                validFilms.add(f);
            } else {
                invalidFilms.add(f);
            }
        }
        validData = new ListFilm(validFilms);
//        XMLUtilities.saveToXML(this.realPath + Constant.VALIDATED_FOLDER + "phim33Valid.xml", new ListFilm(validFilms));
//        XMLUtilities.saveToXML(this.realPath + Constant.VALIDATED_FOLDER + "phim33Invalid.xml", new ListFilm(invalidFilms));
        totalValidData = validFilms.size();
        totalInvalidData = invalidFilms.size();
        return (invalidFilms.size() <= 0) ? true : false;

    }

    public int getPagination(String uri) {
        int pagi = 0;
        try {
            String wellformXML = ParserDom.parseListHTMLToString(uri);
            Document doc = ParserDom.getDocumentFromStringXML(wellformXML);
            XPath xpath = XMLUtilities.createXPath();
            String exp = crawlerConfig.getXpathPagination();
            NodeList pagiNodelist = (NodeList) xpath.evaluate(exp, doc, XPathConstants.NODESET);
            String lastPagi = pagiNodelist.item(pagiNodelist.getLength() - 1).getTextContent();
            pagi = Integer.parseInt(lastPagi);
            return pagi;
        } catch (Exception e) {
            Logger.getLogger(PhePhimCrawler.class.getName()).log(Level.SEVERE, null, "Can't get paginnation");
//            System.out.println("Can't get paginnation");
            e.printStackTrace();
        }
        return pagi;
    }

    public void getListUriOfProduct(String uri) throws SAXException, ParserConfigurationException, IOException, XPathExpressionException {
        int Maxpagi = getPagination(uri);
        // make maxpagi alway  =  1
        Maxpagi = 1;
        if (Maxpagi != 0) {
            try {
                for (int i = 1; i <= Maxpagi; i++) {
                    String httpUrl = uri.replace(Constant.HTML_EXTENSION, "") + "/" + Constant.PAGI_KEYWORD + "-" + i + Constant.HTML_EXTENSION;
                    String wellformXML = ParserDom.parseListHTMLToString(httpUrl);
                    if (wellformXML != null) {
                        Document doc = ParserDom.getDocumentFromStringXML(wellformXML);
                        XPath xpath = XMLUtilities.createXPath();
                        String exp = crawlerConfig.getXpathLinkEachItem();
                        NodeList urlFilms = (NodeList) xpath.evaluate(exp, doc, XPathConstants.NODESET);
                        System.out.println("count film: " + urlFilms.getLength() + "/" + i);
                        for (int j = 0; j < urlFilms.getLength(); j++) {
                            exp = Constant.XPATH_HREF;
                            String urlFilm = (String) xpath.evaluate(exp, urlFilms.item(j), XPathConstants.STRING);
                            if (urlFilm != null && !urlFilm.equals("")) {
                                listfilmUrl.add(urlFilm);
                            }
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("không lấy được danh sách url của product");
            }
        }

    }

    public Film getFilmFromUri(String uri) throws SAXException, ParserConfigurationException, IOException, XPathExpressionException {
        Film film = null;
        try {
            String wellformXML = ParserDom.parseListHTMLToString(uri);
            if (wellformXML != null) {
                Document doc = ParserDom.getDocumentFromStringXML(wellformXML);
                XPath xpath = XMLUtilities.createXPath();
                //Get name
                String exp = crawlerConfig.getXpathNameFilm();
                String filmName = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
                System.out.println("filmName:  " + filmName + " " + uri);
                //End getName
                //Get name English
                exp = crawlerConfig.getXpathNameFilmEnglish();
                String engName = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
                engName = engName.replace("(", "");
                engName = engName.replace("'", "");
                engName = engName.replace(")", "");
//                System.out.println("filmName English:  " + engName);
                //End getName English
                if (filmName != null && !filmName.equals("")) {
                    film = new Film(filmName.trim());
                    film.setWebsiteName(crawlerConfig.getHost());
                    film.setWebsiteURL(crawlerConfig.getWebURL());
                    film.setEngName(engName);
                } else {
                    return null;
                }
                //get description

                exp = crawlerConfig.getXpathDescription();
                Node descriptionNode = (Node) xpath.evaluate(exp, doc, XPathConstants.NODE);
                if (descriptionNode != null) {
                    film.setDescription(descriptionNode.getTextContent());
                }
                // end get description
                exp = crawlerConfig.getXpathGroupInfo();
                NodeList infoRows = (NodeList) xpath.evaluate(exp, doc, XPathConstants.NODESET);
                int lenght = infoRows.getLength();
                for (int i = 0; i < infoRows.getLength(); i++) {
                    String title = infoRows.item(i).getTextContent().toLowerCase();

                    if (title.contains(crawlerConfig.getDirectorKeyword())) {
                        // get director
                        exp = crawlerConfig.getXpathDirector();
                        NodeList directorNodeList = (NodeList) xpath.evaluate(exp, infoRows.item(i), XPathConstants.NODESET);
                        List<String> directors = new ArrayList<>();
                        for (int j = 0; j < directorNodeList.getLength(); j++) {
                            if (directorNodeList.item(j).getTextContent() != null) {
                                String director = directorNodeList.item(j).getTextContent().toLowerCase();
//                                System.out.println(director.trim());
                                directors.add(director.trim());
                            }
                        }
                        film.setDirectors(directors);
                    }

                    if (title.contains(crawlerConfig.getActorKeyword())) {
                        // get actor
                        exp = crawlerConfig.getXpathActor();
                        NodeList actors = (NodeList) xpath.evaluate(exp, infoRows.item(i), XPathConstants.NODESET);
                        List<String> actorList = new ArrayList<>();
//                        System.out.println("director:");
                        for (int j = 0; j < actors.getLength(); j++) {
                            if (actors.item(j).getTextContent() != null) {
//                                System.out.println(actors.item(j).getTextContent());
                                actorList.add(actors.item(j).getTextContent());
                            }
                        }
                        film.setActors(actorList);
                        // end get actor
                    }

                    if (title.contains(crawlerConfig.getKindKeyword())) {
                        // get kind
                        exp = crawlerConfig.getXpathKind();
                        NodeList kindNodeList = (NodeList) xpath.evaluate(exp, infoRows.item(i), XPathConstants.NODESET);
                        List<String> kinds = new ArrayList<>();
                        for (int j = 0; j < kindNodeList.getLength(); j++) {
                            if (kindNodeList.item(j).getTextContent() != null) {
                                String kind = kindNodeList.item(j).getTextContent().toLowerCase();
                                kind = kind.replaceAll(Constant.FILM_CHARACTER, "");
//                                System.out.println(kind.trim());
                                kinds.add(kind.trim());
                            }
                        }
                        film.setKindOfFilms(kinds);
                        //end get kind
                    }

                    if (title.contains(crawlerConfig.getNationKeyword())) {
                        // get nation
                        exp = crawlerConfig.getXpathNation();
                        String national = (String) xpath.evaluate(exp, infoRows.item(i), XPathConstants.STRING);
//                        System.out.println("nation: " + national);
                        // end get nation
                        film.setNation(national);
                    }
                    if (title.contains(crawlerConfig.getDurationKeyword())) {
                        //get duration
                        exp = crawlerConfig.getXpathDuration();
                        String duration = (String) xpath.evaluate(exp, infoRows.item(i), XPathConstants.STRING);

                        duration = TextUtilities.getOnlyNumber(duration);
                        film.setDuration(Integer.parseInt(duration));
//                        System.out.println("duration: " + duration);
                        // end get duration
                    }
                    if (title.contains(crawlerConfig.getYearKeyword())) {
                        // get time
                        exp = crawlerConfig.getXpathYear();
                        Node yearNode = (Node) xpath.evaluate(exp, infoRows.item(i), XPathConstants.NODE);
//                        String year = TextUtilities.getOnlyNumber(yearNode.getTextContent());
                        String year = TextUtilities.getOnlyNumber(yearNode.getTextContent());
//                        System.out.println("year: " + year);
                        // end get time
                        film.setTime(Integer.parseInt(year));
                    }
                    if (title.contains(crawlerConfig.getViewKeyword())) {
                        //get views 
                        exp = crawlerConfig.getXpathView();
                        String views = (String) xpath.evaluate(exp, infoRows.item(i), XPathConstants.STRING);
                        views = TextUtilities.getOnlyNumber(views);
                        if (film != null) {
                            film.setNumberOfView(Integer.parseInt(views));
                        }
                        //end get view 
                    }
                    if (i == (infoRows.getLength() - 1)) {
                        // get rate
                        exp = crawlerConfig.getXpathRate();
                        String rate = (String) xpath.evaluate(exp, infoRows.item(i), XPathConstants.STRING);
                        float floatrate = TextUtilities.getfloatByRegex(rate);

                        if (floatrate == 0.0) {
                            String[] ar = rate.split("/");
                            floatrate = Float.parseFloat(TextUtilities.getOnlyNumber(ar[0]));
                        }
//                        System.out.println("rate: " + floatrate);
                        film.setRate(floatrate);
                        // end get rate

                    }

                }

                // getImage 
                exp = crawlerConfig.getXpathImgURL();
                String imageURL = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
//                if (imageURL == null || imageURL.equals("")) {
//                    return null;
//                }
                String imageName = ImageUtils.getNameImageFromUrl(imageURL);
                String filePath = new File(realPath).getParentFile().getAbsoluteFile().getParent();
                boolean isDownloaded = ImageUtils.saveImageByURL(filePath + Constant.IMAGE_FOLDER + crawlerConfig.getHost() + imageName, imageURL); //lấy ảnh 
//                System.out.println("lay anh: " + isDownloaded);
                film.setImageURL(Constant.IMAGE_FOLDER_SHOWING + crawlerConfig.getHost() + imageName);

                // get Image 
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                return film;
            }
        } catch (Exception e) {
            System.out.println("trang film nay lỗi, không lấy được");
        }
        return film;
    }

    private boolean validateFilm(Film film) {
        boolean validated = true;
        try {
            String xmlString = XMLUtilities.marsalData(film);
            validated = XMLUtilities.validateXMLToInsertDB(xmlString, realPath + Constant.FILE_PATH_SCHEMA_FILM);
            if (validated) {
                System.out.println("Film: " + film.getFilmName() + " is " + "valid");
            } else {
                System.out.println("Film: " + film.getFilmName() + " is " + "invalid");
            }
        } catch (Exception e) {
            validated = false;
            System.out.println("Film: " + film.getFilmName() + " is " + "invalid");
        }
        return validated;
    }

    public int getTotalCrawled() {
        return totalCrawled;
    }

    public void setTotalCrawled(int totalCrawled) {
        this.totalCrawled = totalCrawled;
    }

    public int getTotalValidData() {
        return totalValidData;
    }

    public void setTotalValidData(int totalValidData) {
        this.totalValidData = totalValidData;
    }

    public int getTotalInvalidData() {
        return totalInvalidData;
    }

    public void setTotalInvalidData(int totalInvalidData) {
        this.totalInvalidData = totalInvalidData;
    }

    public void setTotalInserTemp(int totalInserTemp) {
        this.totalInserTemp = totalInserTemp;
    }

    public int getTotalInserTemp() {
        return totalInserTemp;
    }

    public ListFilm getValidData() {
        return validData;
    }

    public String getHost() {
        return crawlerConfig.getWebURL();
    }

    public String getHostName() {
        return crawlerConfig.getHost();
    }

}
