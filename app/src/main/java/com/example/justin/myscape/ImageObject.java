package com.example.justin.myscape;

import android.graphics.Bitmap;


public class ImageObject {

    private Bitmap bitmap = null;
    private float Xpos, Ypos, Width, Height;
    private boolean highlighted;

    public ImageObject (float X, float Y, float W, float H, boolean HL, Bitmap image) {
        setXpos(X);
        setYpos(Y);
        setHeight(H);
        setWidth(W);
        setHighlight(HL);
        setBitmap(image);
    }

    public float getXpos() {return Xpos;}
    public float getYpos() {return Ypos;}
    public boolean getHighlight() {return highlighted;}
    public float getWidth() {return Width;}
    public float getHeight() {return Height;}
    public Bitmap getBitmap() {return bitmap;}

    public void setXpos(float X) { Xpos = X; }
    public void setYpos(float Y) { Ypos = Y; }
    public void setHighlight(boolean highL) { highlighted = highL;}
    public void setWidth(float W) {if( W > 0) {Width = W;} else {Width = 50;} }
    public void setHeight(float H) {if( H > 0) {Height = H;} else {Height = 50;} }
    public void setBitmap(Bitmap image) {if(image != null) {bitmap = image;} }
}
