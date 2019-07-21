
package cuonghn.quiz;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cuonghn.quiz package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cuonghn.quiz
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListQuiz }
     * 
     */
    public ListQuiz createListQuiz() {
        return new ListQuiz();
    }

    /**
     * Create an instance of {@link Quiz }
     * 
     */
    public Quiz createQuiz() {
        return new Quiz();
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

}
