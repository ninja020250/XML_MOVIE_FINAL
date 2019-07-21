/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.DTO;

/**
 *
 * @author nhatc
 */
public class ReportDTO {

    private int totalCrawled = 0;
    private int totalValid = 0;
    private int totalInvalid = 0;
    private int totalInsetTemp = 0;
    public ReportDTO() {
    }

    public int getTotalCrawled() {
        return totalCrawled;
    }

    public int getTotalInsetTemp() {
        return totalInsetTemp;
    }

    public void setTotalInsetTemp(int totalInsetTemp) {
        this.totalInsetTemp = totalInsetTemp;
    }

    public void setTotalCrawled(int totalCrawled) {
        this.totalCrawled = totalCrawled;
    }

    public int getTotalValid() {
        return totalValid;
    }

    public void setTotalValid(int totalValid) {
        this.totalValid = totalValid;
    }

    public int getTotalInvalid() {
        return totalInvalid;
    }

    public void setTotalInvalid(int totalInvalid) {
        this.totalInvalid = totalInvalid;
    }

}
