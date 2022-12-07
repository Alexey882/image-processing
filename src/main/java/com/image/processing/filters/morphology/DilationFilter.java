package com.image.processing.filters.morphology;

import com.image.processing.filters.Filter;
import com.image.processing.utils.Mask;
import com.image.processing.utils.Pixel;
import com.image.processing.utils.Utility;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
public class DilationFilter implements Filter {
    @Override
    public WritableImage processImage(Image image){
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        int width= (int) writableImage.getWidth();
        int height= (int) writableImage.getHeight();
        byte[][] binaryImage = Utility.getBinaryMatrix(pixelReader, width, height);
        Mask mask = new Mask();
        for (int x = mask.width() / 2; x < width - mask.width() / 2; x++) {
            for (int y = mask.height() / 2; y < height - mask.height() / 2; y++) {
                byte max = 0;
                for (int i = -mask.width() / 2; i <= mask.width() / 2; i++)
                    for (int j = -mask.height() / 2; j <= mask.height() / 2; j++)
                        if (mask.getElemIfMainElemInCenter(i, j) && (binaryImage[x + i][y + j] > max)) {
                            max = binaryImage[x + i][y + j];
                        }
                Color newColor = max == 1 ? Color.WHITE : Color.BLACK;
                pixelWriter.setColor(x, y, newColor);
            }
        }
        return writableImage;
    }
}