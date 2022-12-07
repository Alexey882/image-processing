package com.image.processing.filters.global;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Utility;
import com.image.processing.utils.Pixel;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrayWorldFilter implements Filter {
    List<Integer> averageByColors = new ArrayList<>();
    @Override
    public WritableImage processImage(Image image) {
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        int width = (int) writableImage.getWidth(), height = (int) writableImage.getHeight();
        averageByColors = calculateAverageByColors(pixelReader,width,height);
        for(int i = 0 ; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixelWriter.setColor(i,j, calculatePixelColor(pixelReader,i,j));
            }
        }
        return writableImage;
    }

    @Override
    public Color calculatePixelColor(PixelReader pixelReader, int x, int y) {
        int color = pixelReader.getArgb(x,y);
        Pixel pixel = new Pixel(color);

        int averageColors = (averageByColors.get(0)+averageByColors.get(1)+averageByColors.get(2))/3;
        return Color.rgb(
                Utility.clamp(pixel.getR()*averageColors/averageByColors.get(0),255,0),
                Utility.clamp(pixel.getG()*averageColors/averageByColors.get(1),255,0),
                Utility.clamp(pixel.getB()*averageColors/averageByColors.get(2),255,0));
    }

    public List<Integer> calculateAverageByColors(PixelReader pixelReader , int width , int height){
        int avgR = 0 , avgG =0, avgB =0;
        int count = 0;
        for(int i = 0 ; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = pixelReader.getArgb(i, j);
                Pixel pixel = new Pixel(color);
                avgR += pixel.getR();
                avgG += pixel.getG();
                avgB += pixel.getB();
                count++;
            }
        }
            return Arrays.asList(avgR/count,avgG/count,avgB/count);
    }

}
