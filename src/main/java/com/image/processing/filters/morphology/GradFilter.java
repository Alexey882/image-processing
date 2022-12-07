package com.image.processing.filters.morphology;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Mask;
import com.image.processing.utils.Pixel;
import com.image.processing.utils.Utility;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class GradFilter implements Filter{
    @Override
    public WritableImage processImage(Image image){
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        int width= (int) writableImage.getWidth();
        int height= (int) writableImage.getHeight();
        Filter dilationFilter = new DilationFilter();
        Filter erosionFilter = new ErosionFilter();
        Image dilationImage = dilationFilter.processImage(image);
        Image erosionImage = erosionFilter.processImage(image);
        PixelReader dilationImagePixelReader = dilationImage.getPixelReader();
        PixelReader erosionImagePixelReader = erosionImage.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width ; x++) {
                Pixel erosionImagePixel = new Pixel(erosionImagePixelReader.getArgb(x, y));
                Pixel dilationImagePixel = new Pixel(dilationImagePixelReader.getArgb(x, y));
                int dilationPixel = dilationImagePixel.getBinaryPixel();
                int erosionPixel = erosionImagePixel.getBinaryPixel();
                int min = erosionPixel < dilationPixel ? 1 : 0;
                Color newColor = min == 1 ? Color.WHITE : Color.BLACK;
                pixelWriter.setColor(x, y, newColor);
            }

        return writableImage;
    }
}
