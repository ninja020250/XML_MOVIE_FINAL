package cuonghn.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.film.com}Film" maxOccurs="unbounded"/>
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
    "film"
})
@XmlRootElement(name = "listFilm", namespace = "http://www.films.com")
public class ListFilm {

    @XmlElement(name = "Film", required = true)
    protected List<Film> film;

    /**
     * Gets the value of the film property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the film property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilm().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Film }
     *
     *
     */
    public ListFilm(List<Film> film) {
        this.film = film;
    }

    public ListFilm() {
    }

    public void addFilm(Film newFilm) {
        if (film == null) {
            film = new ArrayList<Film>();
        }
        this.film.add(newFilm);
    }

    public List<Film> getFilm() {
        if (film == null) {
            film = new ArrayList<Film>();
        }
        return this.film;
    }
}
