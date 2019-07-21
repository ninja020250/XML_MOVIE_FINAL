/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.utils;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import cuonghn.jaxb.config.CrawlerConfig;
import cuonghn.jaxb.system.config.SystemConfig;
import cuonghn.quiz.Answer;
import cuonghn.quiz.ListQuiz;
import cuonghn.quiz.Quiz;
import java.io.File;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 *
 * @author nhatc
 */
public class XJCGenerateJavaObj {

    public static void main(String[] args) {
        try {
            String output = "src/java";
            SchemaCompiler sc = XJC.createSchemaCompiler();

            sc.setErrorListener(new ErrorListener() {
                @Override
                public void error(SAXParseException saxpe) {
                    System.out.println("error: " + saxpe.getMessage());
                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    System.out.println("fatal: " + saxpe.getMessage());
                }

                @Override
                public void warning(SAXParseException saxpe) {
                    System.out.println("warming: " + saxpe.getMessage());
                }

                @Override
                public void info(SAXParseException saxpe) {
                    System.out.println("info: " + saxpe.getMessage());
                }
            });
            sc.forcePackageName("cuonghn.jaxb.system.config");
            File schema = new File("web/configSchema/system/systemConfig.xsd");
            InputSource is =  new  InputSource(schema.toURI().toString());
            sc.parseSchema(is);
            S2JJAXBModel model =  sc.bind();
            JCodeModel code =  model.generateCode(null, null);
            code.build(new File(output));
            System.out.println(" create jaxb obj is Finised");

//            Quiz q = new Quiz();
//            q.setQuestion("Cuong dap chai");
//            for (int i = 0; i < 3; i++) {
//                Answer a = new Answer();
//                a.setContent("content");
//                a.setKeyword("keyword");
//                q.getAnswer().add(a);
//            }
//            ListQuiz lq = new ListQuiz();
//            lq.getQuiz().add(q);
//            String lqXML = XMLUtilities.marsalData(lq);
//            System.out.println(lqXML);
//            CrawlerConfig cc = new CrawlerConfig();
//            cc.setDirectorKeyword("đạo diễn");
//            cc.setActorKeyword("diễn viên");
//            cc.setKindKeyword("thể loại");
//            cc.setNationKeyword("quốc gia");
//            cc.setDurationKeyword("thời lượng");
//            cc.setYearKeyword("năm phát hành");
////            cc.setViewKeyword("lượt xem");
//            cc.setXPathMainCategory("//*[@id='menu']//div/ul/li[3]/a/@href");
//            cc.setXpathLinkEachItem("//*[@class='list-film row']/div[contains(@class,'item')]/div/a");
//            cc.setXpathNameFilm("//*[contains(@class,'info-film')]//h2/text()");
//            cc.setXpathNameFilmEnglish("//*[contains(@class,'info-film')]//div[@class='name2']/dfn/text()");
//            cc.setHost("phim24h");
//            cc.setWebURL("https://24hphim.com");
//            cc.setXpathPagination("//*[@class='page_nav']/span");
//            cc.setXpathDescription("//*[contains(@class,'block-body')]//p");
//            cc.setXpathGroupInfo("//dl/dt");
//            cc.setXpathDirector("../dd");
//            cc.setXpathActor("../dd");
//            cc.setXpathKind("../dd");
//            cc.setXpathNation("../dd");
//            cc.setXpathDuration("../dd");
//            cc.setXpathYear("../dd");
//            cc.setXpathView("//*[@class='extra-info']/div[@class='views']/span/text()");
//            cc.setXpathRate("//*[contains(@itemprop,'average')]");
//            cc.setXpathImgURL("//*[contains(@class,'info-film')]//div[@class='poster']/img/@src");
//            String ccXML = XMLUtilities.marsalData(cc);
//            System.out.println(ccXML);

//            SystemConfig s = new SystemConfig();
//            s.setTimeLoop(1000);
//            s.setAutoAddNewData(true);
//            s.setAutoDeleteOutOfDateData(false);
//            s.setAutoRemakeRecommandation(true);
//            s.setAutoUpdateOldData(false);
//            String ccXML = XMLUtilities.marsalData(s);
//            System.out.println(ccXML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
