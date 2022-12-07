package com.image.processing.filters;

import javafx.scene.image.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface Filter {
    String PACKAGE_FILTERS_NAME = Filter.class.getPackageName();
    String PROCESS_METHOD_NAME = "processImage";
    default WritableImage processImage(Image image){
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for(int i = 0 ; i < writableImage.getWidth(); i++) {
            for (int j = 0; j < writableImage.getHeight(); j++) {
                pixelWriter.setColor(i,j, calculatePixelColor(pixelReader,i,j));
            }
        }
        return writableImage;
    }
    default Color calculatePixelColor(PixelReader pixelReader ,int x, int y){return Color.BLACK;}

    static String getFullFilterClassName(String filterName,String filterType){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PACKAGE_FILTERS_NAME);
        stringBuilder.append(".");
        stringBuilder.append(filterType);
        stringBuilder.append(".");
        stringBuilder.append(filterName);
        return stringBuilder.toString();
    }
    static Method getProcessFilterMethod(Class<?> filterClass) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return filterClass.getMethod(PROCESS_METHOD_NAME, Image.class);
    }
    static Filter getFilterByName(Class<?> filterClass) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = filterClass.getConstructor();
        return (Filter) constructor.newInstance();
    }
}
