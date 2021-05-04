package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.logging.Logger;

public class Util {
    public final static Logger logger = Logger.getLogger("Default Logger");

    public final static String COUNTRY_LIST_FILE_NAME = "country_list.txt";

    public final static String COUNTRY_IMAGE_FILE_NAME = "country_image.txt";

    public static Image scale(Image source, int targetWidth, int targetHeight, boolean preserveRatio) {
        ImageView imageView = new ImageView(source);
        imageView.setPreserveRatio(preserveRatio);
        imageView.setFitWidth(targetWidth);
        imageView.setFitHeight(targetHeight);
        return imageView.snapshot(null, null);
    }

    public static byte[] imageToByteArray(Image image){
        int w = (int)image.getWidth();
        int h = (int)image.getHeight();
        byte [] buf = new byte[w*h*4];
        image.getPixelReader().getPixels(0,0,w,h, PixelFormat.getByteBgraInstance(), buf, 0,w*4);
        return buf;
    }

    public static Image byteArrayToImage(byte [] byteArray) {
        return new Image(new ByteArrayInputStream(byteArray));
    }

    public static String byteArrayToString(byte [] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }

    public static byte[] stringToByteArray(String str) {
        return Base64.getDecoder().decode(str);
    }

}
