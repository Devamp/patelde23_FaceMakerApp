/**
 * Face class (SurfaceView) to paint the face on a given canvas
 *
 * @autrhor Devam Patel
 * Date: 2/28/2021
 *
 */


package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;

import java.util.Random;

public class Face extends SurfaceView {

    //instance variables for face features and refrence to FaceModel
    private FaceModel myFaceModel;
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public int hairStyle;

    //Paints to help with coloring of each feature
    Paint faceColor = new Paint();
    Paint eyeColorPaint = new Paint();
    Paint hairColorPaint = new Paint();
    Paint colorBlack = new Paint();
    Paint colorRed = new Paint();


    public Face(Context context, AttributeSet attrs){
        super(context, attrs);
        myFaceModel = new FaceModel();

        randomize(); //randomly initialize all instance variables for face attributes

        setWillNotDraw(false); //this will allow for onDraw to be called
        setBackgroundColor(Color.WHITE); //make surface view background white

    }

    //getter method for getting faceModel object
    public FaceModel getFaceModel(){
        return myFaceModel;
    }


    public void randomize(){
        //randomize initial colors of each facial feature using helper classes
        skinColor = Color.argb(255, myFaceModel.Rvalue, myFaceModel.Gvalue, myFaceModel.Bvalue);
        eyeColor = Color.argb(255, myFaceModel.RvalueEyes, myFaceModel.GvalueEyes, myFaceModel.BvalueEyes);
        hairColor = Color.argb(255, myFaceModel.RvalueHair, myFaceModel.GvalueHair, myFaceModel.BvalueHair);
        hairStyle = myFaceModel.hairStyle;
    }

    @Override
    public void onDraw(Canvas canvas){
        drawFace(canvas); //draw the face first
        drawMouth(canvas); //draw a mouth

        //conditional eyestyles
        if(myFaceModel.eyeStyle == 0){
            drawWideEyes(canvas);
        }

        if(myFaceModel.eyeStyle == 1){
            drawEyes(canvas);
        }

        //conditional hairstyles
        if(myFaceModel.hairStyle == 0){
            drawSimpleHair(canvas);
        }
        if(myFaceModel.hairStyle == 1){
            drawHairGrandpa(canvas);
        }
        if(myFaceModel.hairStyle == 2){
            drawHairCombOver(canvas);
        }

    }

    public void drawFace(Canvas canvas){
        faceColor.setColor(skinColor);
        canvas.drawCircle(1350,700,450, faceColor);
    }

    public void drawEyes(Canvas canvas){
        eyeColorPaint.setColor(eyeColor);
        colorBlack.setColor(Color.BLACK);

        canvas.drawOval(1050, 500, 1175,675, eyeColorPaint); //left eye
        canvas.drawOval(1525, 500, 1650,675, eyeColorPaint); //right eye

        canvas.drawOval(1075,550, 1150,650, colorBlack); //left pupil
        canvas.drawOval(1550, 550, 1625,650, colorBlack); //right pupil
    }

    public void drawWideEyes(Canvas canvas){
        eyeColorPaint.setColor(eyeColor);
        colorBlack.setColor(Color.BLACK);

        canvas.drawOval(1025, 550, 1200,625, eyeColorPaint); //left eye
        canvas.drawOval(1500, 550, 1675,625, eyeColorPaint); //right eye

        canvas.drawOval(1075,550, 1150,600, colorBlack); //left pupil
        canvas.drawOval(1550, 550, 1625,600, colorBlack); //right pupil

    }

    public void drawMouth(Canvas canvas){
        colorRed.setColor(Color.RED);
        canvas.drawArc(1100, 750, 1600, 1000, 0,180,false, colorBlack);
        canvas.drawArc(1175, 825, 1550, 975, 0,180,false, colorRed);
    }

    public void drawHairGrandpa(Canvas canvas){
        hairColorPaint.setColor(hairColor);
        canvas.drawOval(830, 550, 930,650, hairColorPaint);
        canvas.drawOval(830, 500, 930,600, hairColorPaint);
        canvas.drawOval(850, 450, 950,550, hairColorPaint);
        canvas.drawOval(780, 450, 880,550, hairColorPaint);
        canvas.drawOval(800, 400, 900,500, hairColorPaint);
        canvas.drawOval(850, 350, 950,450, hairColorPaint);
        canvas.drawOval(920, 350, 1020,450, hairColorPaint);
        canvas.drawOval(890, 300, 990,400, hairColorPaint);
        canvas.drawOval(890, 400, 990,500, hairColorPaint);

        canvas.drawOval(1750, 550, 1850,650, hairColorPaint);
        canvas.drawOval(1750, 500, 1850,600, hairColorPaint);
        canvas.drawOval(1800, 450, 1900,550, hairColorPaint);
        canvas.drawOval(1750, 400, 1850,500, hairColorPaint);
        canvas.drawOval(1650, 330, 1750,430, hairColorPaint);
        canvas.drawOval(1700, 370, 1800,470, hairColorPaint);
        canvas.drawOval(1650, 400, 1750,500, hairColorPaint);
        canvas.drawOval(1700, 450, 1800,550, hairColorPaint);
        canvas.drawOval(1750, 400, 1850,500, hairColorPaint);
        canvas.drawOval(1720, 500, 1820,600, hairColorPaint);
    }

    public void drawSimpleHair(Canvas canvas){
        hairColorPaint.setColor(hairColor);
        canvas.drawArc(900,150,1800,700,0,-180, false, hairColorPaint);
        canvas.drawRect(900,400,1000,600,hairColorPaint); //left
        canvas.drawRect(1700,400,1800,600,hairColorPaint); //right

    }

    public void drawHairCombOver(Canvas canvas){
        hairColorPaint.setColor(hairColor);
        canvas.drawArc(900,150,1800,650,0,-180, false, hairColorPaint);
        canvas.drawOval(925, 350,1075,500, hairColorPaint);
        canvas.drawOval(850, 375, 1000, 800, hairColorPaint);
    }
}
