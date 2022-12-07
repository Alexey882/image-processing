package com.image.processing.filters.morphology;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Pixel;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class BlackHatFilter implements Filter{
    @Override
    public WritableImage processImage(Image image){
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        int width= (int) writableImage.getWidth();
        int height= (int) writableImage.getHeight();
        Filter openingFilter = new OpeningFilter();
        Image openingImage = openingFilter.processImage(image);
        PixelReader pixelReader =image.getPixelReader();
        PixelReader openingImagePixelReader = openingImage.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width ; x++) {
                Pixel openingImagePixel = new Pixel(openingImagePixelReader.getArgb(x, y));
                Pixel imagePixel = new Pixel(pixelReader.getArgb(x, y));
                int openingPixel = openingImagePixel.getBinaryPixel();
                int pixel = imagePixel.getBinaryPixel();
                int min = pixel > openingPixel ? 1 : 0;
                Color newColor = min == 1 ? Color.WHITE : Color.BLACK;
                pixelWriter.setColor(x, y, newColor);
            }

        return writableImage;
    }
}
