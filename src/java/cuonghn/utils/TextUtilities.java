/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cuonghn.wellformer.SyntaxChecker;
import java.text.Normalizer;

/**
 *
 * @author nhatc
 */
public class TextUtilities {

    public static final String EXPRESSION_CONTAINS_NUMBER = ".*\\d.*";

    public static int getYearFromStringDate(String src) {
        String str[] = src.split("/");
        int day = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int year = Integer.parseInt(str[2]);
        return year;
    }

    public static String removeSpecialCharacter(String src) {
        src = src.replace("(", "");
        src = src.replace(")", "");
        src = src.replace("'", "");
        return src;
    }

    public static String getOnlyNumber(String src) {
        src = src.replaceAll("\\D+", "");
        return src;
    }

    public static float getfloatByRegex(String src) {
        float result = 0;
        Pattern p = Pattern.compile("[0-9]*\\.+[0-9]+");

        Matcher m = p.matcher(src);

        while (m.find()) {
            src = m.group();
        }
        try {
            result = Float.parseFloat(src);
        } catch (Exception e) {
        }
        return result;
    }

    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static String stringTOUTF8(String src) {
        try {
            byte[] ptext = src.getBytes("UTF8");
            src = new String(ptext, "UTF8");
            return src;
        } catch (Exception e) {
            System.out.println("parse UTF-8 error");
        }
        return "";
    }

    public static String removeWhiteSpace(String src) {
        src = src.replace(" ", "");
        return src;
    }

    public static String removeSymbols(String src) {
        src = src.replace("•", "");
        src = src.replace("~", "");
        src = src.replace(",", ".");
        return src;
    }

    public static String refineHtml(String src) {
        src = getBody(src);
        src = removeMiscellaneousTags(src);

        SyntaxChecker xmlSyntaxChecker = new SyntaxChecker();
        src = xmlSyntaxChecker.check(src);

        //crop one more time
        src = getBody(src);
        return src;
    }

    public static String refineHtmlCPN(String src) {
        src = getBody(src);
        src = removeMiscellaneousTagsSpecialForCPN(src);

        SyntaxChecker xmlSyntaxChecker = new SyntaxChecker();
        src = xmlSyntaxChecker.check(src);

        //crop one more time
        src = getBody(src);
        return src;
    }

    public static String getByExpression(String src, String expression) {
        String result = "";
        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(src);

        if (matcher.find()) {
            result = matcher.group();
        }

        return result;
    }

    private static String getBody(String src) {
        String result = src;
        String expression = "<body.*?</body>";
        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(result);

        if (matcher.find()) {
            result = matcher.group(0);
        }

        return result;
    }

    public static String removeMiscellaneousTagsSpecialForCPN(String src) {
        String result = src;

        //Remove all <script> tags
        String expression = "<script.*?</script>";
        result = result.replaceAll(expression, "");

        expression = "<noscript.*?</noscript>";
        result = result.replaceAll(expression, "");
//        
        // remove alt>
        expression = "alt=\".*?\"";
        result = result.replaceAll(expression, "");

        //Remove all comments
        expression = "<!--.*?-->";
        result = result.replaceAll(expression, "");

        //Remove all <script> tags
        expression = "&nbsp;?";
        result = result.replaceAll(expression, "");

        expression = "&amp;?";
        result = result.replaceAll(expression, "");

        return result;
    }

    public static String removeMiscellaneousTags(String src) {
        String result = src;

        //Remove all <script> tags
        String expression = "<script.*?</script>";
        result = result.replaceAll(expression, "");

        expression = "<noscript.*?</noscript>";
        result = result.replaceAll(expression, "");
//        
//        // remove alt>
//        expression = "alt=\".*?\"";
//        result = result.replaceAll(expression, "");

        //Remove all comments
        expression = "<!--.*?-->";
        result = result.replaceAll(expression, "");

        //Remove all <script> tags
        expression = "&nbsp;?";
        result = result.replaceAll(expression, "");

        expression = "&amp;?";
        result = result.replaceAll(expression, "");

        expression = "&amp;";
        result = result.replaceAll(expression, "");
        return result;
    }
}
