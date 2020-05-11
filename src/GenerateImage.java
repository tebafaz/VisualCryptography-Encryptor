import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Random;

public class GenerateImage {
    public static BufferedImage getRandomizedImage(int width, int height){
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = result.getRaster();
        int[] pixels = new int[width];
        for(int y = 0; y < height; y++){
            for(int i = 0; i < width; i++){
                if(new Random().nextBoolean()){
                    pixels[i] = 255;
                }
                else{
                    pixels[i] = 0;
                }
            }
            raster.setPixels(0, y, width, 1, pixels);
        }
        return result;
    }

    public static BufferedImage getExcludedImage(BufferedImage first, BufferedImage second, boolean isEven){
        int width = first.getWidth();
        int height = first.getHeight();
        BufferedImage result = new BufferedImage(first.getWidth(), first.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = result.getRaster();
        WritableRaster firstRaster = first.getRaster();
        WritableRaster secondRaster = second.getRaster();
        int[] pixels1 = new int[width];
        int[] pixels2 = new int[width];
        int[] pixels = new int[width];

        if(isEven){

            for(int y = 0; y < height; y++){
                firstRaster.getPixels(0, y, width, 1, pixels1);
                secondRaster.getPixels(0, y, width, 1, pixels2);
                for(int i = 0; i < width; i++){
                    if(pixels1[i] == pixels2[i]){
                        pixels[i] = 255;
                    }
                    else {
                        pixels[i] = 0;
                    }
                }
                raster.setPixels(0, y, width, 1, pixels);
            }
        }
        else{
            for(int y = 0; y < height; y++){
                firstRaster.getPixels(0, y, width, 1, pixels1);
                secondRaster.getPixels(0, y, width, 1, pixels2);
                for(int i = 0; i < width; i++){
                    if(pixels1[i] == pixels2[i]){
                        pixels[i] = 0;
                    }
                    else {
                        pixels[i] = 255;
                    }
                }
                raster.setPixels(0, y, width, 1, pixels);
            }
        }
        return result;
    }

    public static BufferedImage thresholdImage(BufferedImage image, int threshold) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        result.getGraphics().drawImage(image, 0, 0, null);
        WritableRaster raster = result.getRaster();
        int[] pixels = new int[image.getWidth()];
        for (int y = 0; y < image.getHeight(); y++) {
            raster.getPixels(0, y, image.getWidth(), 1, pixels);
            for (int i = 0; i < pixels.length; i++) {
                if (pixels[i] < threshold) pixels[i] = 0;
                else pixels[i] = 255;
            }
            raster.setPixels(0, y, image.getWidth(), 1, pixels);
        }
        return result;
    }
}
