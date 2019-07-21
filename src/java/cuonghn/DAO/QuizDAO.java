/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.DAO;

import cuonghn.DTO.UserDTO;
import cuonghn.jaxb.Film;
import cuonghn.quiz.Answer;
import cuonghn.quiz.ListQuiz;
import cuonghn.quiz.Quiz;
import cuonghn.utils.DBUtil;
import cuonghn.utils.XMLUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author nhatc
 */
public class QuizDAO {

    public Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    String realPath = "";

    public QuizDAO() {
    }

    public QuizDAO(String realPath) {
        this.realPath = realPath;
    }

    private void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("some things happen prevent close connection");
        } finally {
        }
    }

//    public void AsynQuestion() {
//        try {
//            ListQuiz lq = XMLUtilities.unmarshallDataFromFile(realPath + "config/quizs.xml", ListQuiz.class);
//            boolean validate = validateQuestions(lq);
//            if (validate) {
//                truncateTable("quiz_tbl");
//                for (int i = 0; i < lq.getQuiz().size(); i++) {
//                    inserQuiz(lq.getQuiz().get(i));
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private boolean validateQuestions(ListQuiz lq) {
//        boolean validated = true;
//        try {
//            String xmlString = XMLUtilities.marsalData(lq);
//            validated = XMLUtilities.validateXMLToInsertDB(xmlString, realPath + "schema/quizs.xsd");
//            if (validated) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            validated = false;
//        }
//        return false;
//    }
//    public List<Quiz> getAllQuiz() {
//        List<String> quizXMLs = getAllQuizXML();
//        List<Quiz> result = null;
//        if (quizXMLs != null) {
//            for (int i = 0; i < quizXMLs.size(); i++) {
//                String xml = quizXMLs.get(i);
//                if (result == null) {
//                    result = new ArrayList<>();
//                }
//                try {
//                    Quiz q = XMLUtilities.unmarshallData(xml, Quiz.class);
//                    result.add(q);
//                } catch (Exception e) {
//                    System.out.println("sai cau truc XML quiz");
//                }
//            }
//        }
//        return result;
//    }
    public List<Quiz> getRandom5Quiz(List<Quiz> src) {
        List<Quiz> lq = new ArrayList<>();
        if (src != null && src.size() > 5) {
            Random rand = new Random();
            int index = -1;
            while (lq.size() <= 5) {
                index = rand.nextInt(src.size());
                if (index != -1) {
                    lq.add(src.get(index));
                    src.remove(index);
                }
            }
        }
        return lq;
    }

    public List<Quiz> getAllQuiz() {
        List<Quiz> quizs = null;
        try {
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "select  quiz_id,quiz from quiz";
                preparedStatement = connection.prepareStatement(sql);
                PreparedStatement preparedStatementChoice = null;
                resultSet = preparedStatement.executeQuery();
                ResultSet resultSetChoice = null;
                // get choice 

                while (resultSet.next()) {
                    if (quizs == null) {
                        quizs = new ArrayList<>();
                    }
                    String quizID = resultSet.getString("quiz_id");
                    String quiz = resultSet.getString("quiz");
                    Quiz q = new Quiz();
                    q.setQuestion(quiz);
                    q.setQuizID(quizID);
                    String sqlChoice = "SELECT c.choice, c.keywords FROM choice c, quiz q where c.quiz_id=q.quiz_id and c.quiz_id=?";
                    preparedStatementChoice = connection.prepareStatement(sqlChoice);
                    preparedStatementChoice.setString(1, quizID);
                    resultSetChoice = preparedStatementChoice.executeQuery();
                    while (resultSetChoice.next()) {
                        Answer a = new Answer();
                        String choice = resultSetChoice.getString("choice");
                        String keywords = resultSetChoice.getString("keywords");
                        a.setContent(choice);
                        a.setKeyword(keywords);
                        q.getAnswer().add(a);

                    }
                    quizs.add(q);
                }
                resultSetChoice.close();
                preparedStatementChoice.close();
                return quizs;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            closeConnection();
        }
        return quizs;
    }

    public boolean inserQuiz(Quiz quiz) {
        try {
//            String quizXML = XMLUtilities.marsalData(quiz);
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String key = DBUtil.GenerateKey();
                String sql = "insert into  quiz(quiz_id, quiz) values (?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, key);
                preparedStatement.setString(2, quiz.getQuestion());
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    List<Answer> la = quiz.getAnswer();
                    for (int i = 0; i < la.size(); i++) {
                        Answer a = la.get(i);
                        sql = "insert into choice(quiz_id, choice, keywords) values (?,?,?)";
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, key);
                        preparedStatement.setString(2, a.getContent());
                        preparedStatement.setString(3, a.getKeyword());
                        result = preparedStatement.executeUpdate();

                    }
                    if (result > 0) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean deleteAnswer(String quizID) {
        try {
//            String quizXML = XMLUtilities.marsalData(quiz);
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String key = DBUtil.GenerateKey();
                String sql = "Delete choice where quiz_id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, quizID);
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean deleteQuiz(String quizID) {
        try {
//            String quizXML = XMLUtilities.marsalData(quiz);
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String key = DBUtil.GenerateKey();
                String sql = "Delete quiz where quiz_id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, quizID);
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }
}
