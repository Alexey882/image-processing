package com.image.processing.utils;

import javafx.scene.paint.Color;

public class Pixel {
    private int R, G, B;

    public Pixel(int r, int g, int b) {
        R = r;
        G = g;
        B = b;
    }

    public Pixel(int argbPixel){
        //int alpha = (pixel >> 24) & 0xff;
        this.R = (argbPixel >> 16) & 0xff;
        this.G = (argbPixel >> 8) & 0xff;
        this.B = (argbPixel) & 0xff;
    }

    public void setR(int r) {
        R = r;
    }
    public void setG(int g) {
        G = g;
    }
    public void setB(int b) {
        B = b;
    }

    public Pixel() {}

    public int getR() {
        return R;
    }
    public int getG() {
        return G;
    }
    public int getB() {
        return B;
    }

    public boolean isWhite(){
        return R == 255 && G == 255 && B == 255 ;
    }
    public boolean isBlack(){
        return !isWhite();
    }
    public int getBinaryPixel(){return (isWhite()|| getIntensity()>128)?1:0;}
    public int getIntensity(){
        return (int) (0.36*R + 0.53*G + 0.11*B);
    }
    public static Color getColor(double R ,double G , double B){
                return Color.rgb(Utility.clamp((int) R,255,0),
                Utility.clamp((int) G,255,0),
                Utility.clamp((int) B,255,0));
    }
    public static Color getColor(Pixel pixel){
        return Color.rgb(Utility.clamp(pixel.getR(),255,0),
                Utility.clamp(pixel.getG(),255,0),
                Utility.clamp(pixel.getB(),255,0));
    }
}
