/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuonghn.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author nhatc
 */
public class ImageUtils {

    public static boolean saveImageByURL(String filePath, String imageURL) {

        try {
            imageURL = imageURL.replaceAll(" ", "%20lcd%20");
            URL url = new URL(imageURL);

            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");

            String contentType = httpcon.getContentType();

            System.out.println("contentType:" + contentType);

            InputStream is = httpcon.getInputStream();
//            InputStream in = new URL(imageURL).openStream();
            Files.copy(is, Paths.get(TextUtilities.deAccent(filePath)));
            return true;
        } catch (Exception e) {
//            System.out.println("loi chuyen doi link anh");
//            System.out.println(e.getMessage());
        }
        return false;
    }

    public static String getNameImageFromUrl(String url) {

        String fileName = url.substring(url.lastIndexOf('/') + 1);
//        fileName = fileName.replaceAll(".", "");
        return fileName;
    }
}
