/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.crawler;

import cuonghn.DAO.DataHandler;
import cuonghn.DAO.FilmDAO;
import cuonghn.DAO.TempDataDAO;
import cuonghn.DTO.ReportDTO;
import cuonghn.jaxb.Film;
import cuonghn.jaxb.ListFilm;
import cuonghn.jaxb.system.config.SystemConfig;
import cuonghn.utils.Constant;
import cuonghn.utils.XMLUtilities;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhatc
 */
public class CrawlerMaster {

    private String realPath;
    private int totalCrawled = 0;
    private int totalValidData = 0;
    private int totalInvalidData = 0;
    private int totalInserTemp = 0;
    private int totalRawData = 0;

    public CrawlerMaster(String realPath) {
        this.realPath = realPath;
    }

    // hàm chạy bằng tay, dùng khi dev hoặc muốn kích hoạt việc cào dữ liệu bằng tay
    public void run() {
        // start crawling phephim 
        PhePhimCrawler phephim = new PhePhimCrawler(realPath);
        ListFilm listFimPhephim = phephim.crawl();
        ListFilm validDataPhephim = null;
        // start crawling khoaitv 
        KhoaiTV khoai = new KhoaiTV(realPath);
        ListFilm listFimkhoai = khoai.crawl();
        ListFilm validDatakhoai = null;
        // end crawl
        
        // xử lý dữ liệu cào về - đưa dữ liệu cào về dưới dạng xml vào các bảng tạm.
        FilmDAO dao = new FilmDAO(true);
        TempDataDAO tempDAO = new TempDataDAO();
        dao.clearTemptable();
        tempDAO.clearRawData();
        int countSuccess;
        
        // validate dữ liệu thô cào về
        if (listFimkhoai != null) {
            tempDAO.masterInsertRawData(listFimkhoai);
            phephim.validateData();
            validDataPhephim = phephim.getValidData();
        }
        if (listFimPhephim != null) {
            tempDAO.masterInsertRawData(listFimPhephim);
            khoai.validateData();
            validDatakhoai = khoai.getValidData();
        }

        // dữ liệu đã validate đợi người dùng accept mới lưu vào trong bảng chính,
        //đảm bảo được tính toàn vẹn của dữ liệu
        if (validDataPhephim != null) {
            countSuccess = tempDAO.masterInsertTemp(validDataPhephim);
            totalInserTemp = countSuccess;
            dao.insertNewWebsite(phephim.getHostName(), phephim.getHost());

        }
        if (validDatakhoai != null) {
            countSuccess = tempDAO.masterInsertTemp(validDatakhoai);
            dao.insertNewWebsite(khoai.getHostName(), khoai.getHost());
            totalInserTemp += countSuccess;
        }
        // in ra các thông tin của việc crawl để làm report
        totalCrawled = phephim.getTotalCrawled() + khoai.getTotalCrawled();
        totalValidData = phephim.getTotalValidData() + khoai.getTotalValidData();
        totalInvalidData = phephim.getTotalInvalidData() + khoai.getTotalInvalidData();

    }
    
    // hàm chạy auto để crawl dữ liệu
    public void runAuto() {
        PhePhimCrawler phephim = new PhePhimCrawler(realPath);
        ListFilm listFimPhephim = phephim.crawl();
        ListFilm validDataPhephim = phephim.getValidData();
        
        // tiến hành cào và validate
        KhoaiTV khoai = new KhoaiTV(realPath);
        ListFilm listFimkhoai = khoai.crawl();
        ListFilm validDatakhoai = khoai.getValidData();
        FilmDAO dao = new FilmDAO(true);
        TempDataDAO tempDAO = new TempDataDAO();
        dao.clearTemptable();
        int countSuccess;
        // insert vô bảng tạm
        if (validDataPhephim != null) {
            countSuccess = tempDAO.masterInsertTemp(validDataPhephim);
            totalInserTemp = countSuccess;
            dao.insertNewWebsite(phephim.getHostName(), phephim.getHost());

        }
        if (validDatakhoai != null) {
            countSuccess = tempDAO.masterInsertTemp(validDatakhoai);
            dao.insertNewWebsite(khoai.getHostName(), khoai.getHost());
            totalInserTemp += countSuccess;

        }
        totalCrawled = phephim.getTotalCrawled() + khoai.getTotalCrawled();
        totalValidData = phephim.getTotalValidData() + khoai.getTotalValidData();
        totalInvalidData = phephim.getTotalInvalidData() + khoai.getTotalInvalidData();

    }

    // hàm chạy auto làm tất cả mọi thứ, hàm chạy theo file config viết bằng XML
    public static boolean autoCrawlingData(String realPath, SystemConfig config) {
        try {
            // bước 1: cào dữ liệu về lưu vào trong bảng tạm.
            CrawlerMaster master = new CrawlerMaster(realPath);
            ReportDTO report = new ReportDTO();
            master.run();
            // bước 2: sau khi cào xong dữ liệu thì xuất ra 1 cái report để làm file FDF sau này.
            report.setTotalCrawled(master.getTotalCrawled());
            report.setTotalValid(master.getTotalValidData());
            report.setTotalInvalid(master.getTotalInvalidData());
            report.setTotalInsetTemp(master.getTotalInserTemp());
            // bước 3: so sánh với dữ liệu cũ xem cái nào thêm mới, cái nào cũ, cái nào update
            DataHandler DThandler = new DataHandler();
            DThandler.compareOldAndNewData();
            // bước 4: khi đã phân loại dữ liệu thì tiến hành thực hiện thao tác đưa vào bảng chính.
            if (config.isAutoDeleteOutOfDateData()) {
                DThandler.handleDelData();
            }
            if (config.isAutoAddNewData()) {
                DThandler.handleAddIncommingData();
            }
            if (config.isAutoUpdateOldData()) {
                DThandler.handleUpdateData();
            }
            // bước 5: vì do có dữ liệu mới tham gia vào, nên phải chạy lại những film recommandation.
            if (config.isAutoRemakeRecommandation()) {
                DThandler.genarateRecommandationDefault();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getTotalCrawled() {
        return totalCrawled;
    }

    public int getTotalValidData() {
        return totalValidData;
    }

    public int getTotalInvalidData() {
        return totalInvalidData;
    }

    public int getTotalInserTemp() {
        return totalInserTemp;
    }

    public void setTotalCrawled(int totalCrawled) {
        this.totalCrawled = totalCrawled;
    }

    public void setTotalValidData(int totalValidData) {
        this.totalValidData = totalValidData;
    }

    public void setTotalInvalidData(int totalInvalidData) {
        this.totalInvalidData = totalInvalidData;
    }

    public void setTotalInserTemp(int totalInserTemp) {
        this.totalInserTemp = totalInserTemp;
    }

}
