/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.listener;

import cuonghn.DAO.DataHandler;
import cuonghn.DTO.ReportDTO;
import cuonghn.crawler.CrawlerMaster;
import cuonghn.jaxb.system.config.SystemConfig;
import cuonghn.utils.XMLUtilities;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

/**
 * Web application lifecycle listener.
 *
 * @author nhatc
 */
public class myContextListener implements ServletContextListener {

    private SystemConfig config;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            String realPath = sce.getServletContext().getRealPath("/");
            config = XMLUtilities.unmarshallDataFromFile(realPath + "/config/systemConfig.xml", SystemConfig.class);
            if (config != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, config.getTimeLoopHour());
                calendar.set(Calendar.MINUTE, config.getTimeLoopMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                Date dateSchedule = calendar.getTime();
                long period = 24 * 60 * 60 * 1000;
                CrawlerMaster crawler = new CrawlerMaster(realPath);
                CrawlingTask task = new CrawlingTask(realPath, config);
                if(config.isCrawlOnDeploy()){
                    task.run();
                }           
                Timer timer = new Timer();
                timer.schedule(task, dateSchedule, period);
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

    private static class CrawlingTask extends TimerTask {

        private String realPath;
        private SystemConfig config;

        public CrawlingTask(String realPath, SystemConfig config) {
            this.realPath = realPath;
            this.config = config;
        }

        public void run() {
            try {
                System.out.println("auto crawling data ......");
                if (config != null) {
                    CrawlerMaster.autoCrawlingData(realPath, config);
                } else {
                    System.out.println("File config sai");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
