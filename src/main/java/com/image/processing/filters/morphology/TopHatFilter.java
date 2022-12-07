package com.image.processing.filters.morphology;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Pixel;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class TopHatFilter implements Filter{
    @Override
    public WritableImage processImage(Image image){
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        int width= (int) writableImage.getWidth();
        int height= (int) writableImage.getHeight();
        Filter closingFilter = new ClosingFilter();
        Image closingImage = closingFilter.processImage(image);
        PixelReader closingImagePixelReader = closingImage.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width ; x++) {
                Pixel closingImagePixel = new Pixel(closingImagePixelReader.getArgb(x, y));
                Pixel imagePixel = new Pixel(pixelReader.getArgb(x, y));
                int closingPixel = closingImagePixel.getBinaryPixel();
                int pixel = imagePixel.getBinaryPixel();
                int min = closingPixel > pixel ? 1 : 0;
                Color newColor = min == 1 ? Color.WHITE : Color.BLACK;
                pixelWriter.setColor(x, y, newColor);
            }

        return writableImage;
    }
}
