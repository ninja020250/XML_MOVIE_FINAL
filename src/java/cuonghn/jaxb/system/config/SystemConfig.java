
package cuonghn.jaxb.system.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="timeLoopHour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="timeLoopMinute" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="autoRemakeRecommandation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="autoUpdateOldData" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="autoAddNewData" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="autoDeleteOutOfDateData" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="crawlOnDeploy" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "timeLoopHour",
    "timeLoopMinute",
    "autoRemakeRecommandation",
    "autoUpdateOldData",
    "autoAddNewData",
    "autoDeleteOutOfDateData",
    "crawlOnDeploy"
})
@XmlRootElement(name = "SystemConfig")
public class SystemConfig {

    protected int timeLoopHour;
    protected int timeLoopMinute;
    @XmlElement(defaultValue = "true")
    protected boolean autoRemakeRecommandation;
    @XmlElement(defaultValue = "false")
    protected boolean autoUpdateOldData;
    @XmlElement(defaultValue = "true")
    protected boolean autoAddNewData;
    @XmlElement(defaultValue = "false")
    protected boolean autoDeleteOutOfDateData;
    @XmlElement(defaultValue = "false")
    protected boolean crawlOnDeploy;

    /**
     * Gets the value of the timeLoopHour property.
     * 
     */
    public int getTimeLoopHour() {
        return timeLoopHour;
    }

    /**
     * Sets the value of the timeLoopHour property.
     * 
     */
    public void setTimeLoopHour(int value) {
        this.timeLoopHour = value;
    }

    /**
     * Gets the value of the timeLoopMinute property.
     * 
     */
    public int getTimeLoopMinute() {
        return timeLoopMinute;
    }

    /**
     * Sets the value of the timeLoopMinute property.
     * 
     */
    public void setTimeLoopMinute(int value) {
        this.timeLoopMinute = value;
    }

    /**
     * Gets the value of the autoRemakeRecommandation property.
     * 
     */
    public boolean isAutoRemakeRecommandation() {
        return autoRemakeRecommandation;
    }

    /**
     * Sets the value of the autoRemakeRecommandation property.
     * 
     */
    public void setAutoRemakeRecommandation(boolean value) {
        this.autoRemakeRecommandation = value;
    }

    /**
     * Gets the value of the autoUpdateOldData property.
     * 
     */
    public boolean isAutoUpdateOldData() {
        return autoUpdateOldData;
    }

    /**
     * Sets the value of the autoUpdateOldData property.
     * 
     */
    public void setAutoUpdateOldData(boolean value) {
        this.autoUpdateOldData = value;
    }

    /**
     * Gets the value of the autoAddNewData property.
     * 
     */
    public boolean isAutoAddNewData() {
        return autoAddNewData;
    }

    /**
     * Sets the value of the autoAddNewData property.
     * 
     */
    public void setAutoAddNewData(boolean value) {
        this.autoAddNewData = value;
    }

    /**
     * Gets the value of the autoDeleteOutOfDateData property.
     * 
     */
    public boolean isAutoDeleteOutOfDateData() {
        return autoDeleteOutOfDateData;
    }

    /**
     * Sets the value of the autoDeleteOutOfDateData property.
     * 
     */
    public void setAutoDeleteOutOfDateData(boolean value) {
        this.autoDeleteOutOfDateData = value;
    }

    /**
     * Gets the value of the crawlOnDeploy property.
     * 
     */
    public boolean isCrawlOnDeploy() {
        return crawlOnDeploy;
    }

    /**
     * Sets the value of the crawlOnDeploy property.
     * 
     */
    public void setCrawlOnDeploy(boolean value) {
        this.crawlOnDeploy = value;
    }

}
