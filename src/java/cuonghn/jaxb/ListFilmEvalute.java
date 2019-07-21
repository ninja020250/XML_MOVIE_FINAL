
package cuonghn.jaxb;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://www.film.com}Film"/>
 *         &lt;element ref="{http://www.filmEvalute.com}FilmEvalute" maxOccurs="unbounded"/>
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
    "film",
    "filmEvalute"
})
@XmlRootElement(name = "ListFilmEvalute", namespace = "http://www.filmEvalutes.com")
public class ListFilmEvalute {

    @XmlElement(name = "Film", required = true)
    protected Film film;
    @XmlElement(name = "FilmEvalute", namespace = "http://www.filmEvalute.com", required = true)
    protected List<FilmEvalute> filmEvalute;

    /**
     * Gets the value of the film property.
     * 
     * @return
     *     possible object is
     *     {@link Film }
     *     
     */
    
    public void setFilmEvalute(List<FilmEvalute> filmEvalute) {
        this.filmEvalute = filmEvalute;
    }

    public Film getFilm() {
        return film;
    }

    /**
     * Sets the value of the film property.
     * 
     * @param value
     *     allowed object is
     *     {@link Film }
     *     
     */
    public void setFilm(Film value) {
        this.film = value;
    }

    /**
     * Gets the value of the filmEvalute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filmEvalute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilmEvalute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FilmEvalute }
     * 
     * 
     */
    public List<FilmEvalute> getFilmEvalute() {
        if (filmEvalute == null) {
            filmEvalute = new ArrayList<FilmEvalute>();
        }
        return this.filmEvalute;
    }

}
