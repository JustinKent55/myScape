package com.example.justin.myscape;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class ImageEditView extends View {

    //Objects
    Context myContext = null;
    Paint myPaintBrush = null;
    Bitmap m_background = null;
    ArrayList<ImageObject> m_movableObject = null;

    //Variables
    String m_ImagePath = null;
    Uri m_Uri = null;
    float m_offset = 5;

    //Movable objects X,Y Coordinates
    float X = 0, Y = 0;
    float newX = 450, newY = 550;
    int imageSelected = -1;

    public ImageEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        myContext = context;
    }

    //Initialize the custom View
    public void initialize(String backgroundImagePath, Bitmap image) {
        //m_movableObject = image;
        m_movableObject = new ArrayList<ImageObject>();
        m_ImagePath = backgroundImagePath;
        myPaintBrush = new Paint();
        myPaintBrush.setStrokeWidth(5);
        myPaintBrush.setColor(Color.BLUE);
        myPaintBrush.setAlpha(50);
        myPaintBrush.setAntiAlias(true);

        try {
            //Grab the background from the image string path
            //Resize background image to fit the custom view
            m_background = MediaStore.Images.Media.getBitmap(myContext.getContentResolver(), Uri.parse(m_ImagePath));
            m_background = Bitmap.createScaledBitmap(m_background, 1085, 1050, false);

        } catch(Exception e){
            e.printStackTrace();
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(m_background != null) {
            canvas.drawBitmap(m_background, 0, 275, null);
        }

        if(m_movableObject != null) {

            for(int i = 0; i < m_movableObject.size(); i++) {
                canvas.drawBitmap(m_movableObject.get(i).getBitmap(), m_movableObject.get(i).getXpos(), m_movableObject.get(i).getYpos(), null);

                if(m_movableObject.get(i).getHighlight()) {
                    //Highlight around the selected object
                    canvas.drawRect(m_movableObject.get(i).getXpos()-m_offset,
                            m_movableObject.get(i).getYpos()-m_offset,
                            m_movableObject.get(i).getXpos()+(m_movableObject.get(i).getWidth())+m_offset,
                            m_movableObject.get(i).getYpos()+(m_movableObject.get(i).getHeight())+m_offset,
                            myPaintBrush);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                X = event.getX();
                Y = event.getY();
                imageSelected = checkifClickedOnImage(X, Y);

                if(imageSelected != -1) {
                    m_movableObject.get(imageSelected).setHighlight(true);
                } else {
                    for(int i = 0; i < m_movableObject.size(); i++) {
                        m_movableObject.get(i).setHighlight(false);
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                X = event.getX();
                Y = event.getY();

                if(m_movableObject.get(imageSelected).getHighlight()) {
                    m_movableObject.get(imageSelected).setXpos(X);
                    m_movableObject.get(imageSelected).setYpos(Y);
                    m_movableObject.get(imageSelected).setXpos(m_movableObject.get(imageSelected).getXpos() - (m_movableObject.get(imageSelected).getWidth() / 2));
                    m_movableObject.get(imageSelected).setYpos(m_movableObject.get(imageSelected).getYpos() - (m_movableObject.get(imageSelected).getHeight() / 2));
                } else {
                    //do nothing...
                }
                invalidate();
                break;
        }
        return true;
    }

    private int getParentWidth() {
        return findViewById(R.id.customView).getLayoutParams().width;
    }

    private int getParentHeight() {
        return findViewById(R.id.customView).getLayoutParams().height;
    }

    private int checkifClickedOnImage(float X, float Y) {

        for(int i = 0; i < m_movableObject.size(); i++) {
            if(X >= m_movableObject.get(i).getXpos() &&
                    X <= m_movableObject.get(i).getXpos() + m_movableObject.get(i).getWidth() &&
                    Y >= m_movableObject.get(i).getYpos() &&
                    Y <= m_movableObject.get(i).getYpos() + m_movableObject.get(i).getHeight())
                return i;
        }
        return -1;
    }

    public void addImage(Bitmap image) {
        m_movableObject.add(new ImageObject(450,550,image.getWidth(),image.getHeight(),false, image));
    }
}
