/**
 *
 * FaceController
 *
 * This class controls the changing of the colors of the face, eyes, and the hair. Along with implementing the appropriate interfaces required to
 * perform such changes.
 *
 * @author Devam Patel
 * Date: 3/2/21
 */



package com.example.facemaker;



import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.Random;

public class FaceController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {

    //instance variables to help with class to class refrences and their data storage
    public Face myFace;
    public FaceModel myFaceModel;

    //store seek bars as they are changed
    public SeekBar redBar;
    public SeekBar blueBar;
    public SeekBar greenBar;

    public Spinner myHairSpinner;

    //store radio group and id
    private RadioGroup myGroup;
    private int checkID;

    //rand generator to randomize random face colors features
    Random rand = new Random();

    //initializes faceView and faceModel objects
    public FaceController(Face faceObj){
        myFace = faceObj;
        myFaceModel = faceObj.getFaceModel();
    }

    @Override
    public void onClick(View v) {

        myFace.invalidate();

        //random button is pressed
        if(v.getId() == R.id.randomFaceButton){

            //randomize face color
            myFaceModel.Rvalue = rand.nextInt(256);
            myFaceModel.Gvalue = rand.nextInt(256);
            myFaceModel.Bvalue = rand.nextInt(256);

            //set progress on seekbars on start up
            redBar.setProgress(myFaceModel.Rvalue);
            greenBar.setProgress(myFaceModel.Gvalue);
            blueBar.setProgress(myFaceModel.Bvalue);

            //randomize eyes color
            myFaceModel.RvalueEyes = rand.nextInt(256);
            myFaceModel.GvalueEyes = rand.nextInt(256);
            myFaceModel.BvalueEyes = rand.nextInt(256);

            //randomize hair color
            myFaceModel.RvalueHair = rand.nextInt(256);
            myFaceModel.GvalueHair = rand.nextInt(256);
            myFaceModel.BvalueHair = rand.nextInt(256);

            //randomize hairstyle
            myFaceModel.hairStyle = rand.nextInt(3);

            //randomize eyestyle
            myFaceModel.eyeStyle = rand.nextInt(2);

            //set progress according to radio group
            onCheckedChanged(myGroup, checkID);

            //set hairspinner to current hairstyle after randomize
            myHairSpinner.setSelection(myFaceModel.hairStyle);


        }

        //redraw
        myFace.randomize();

    }




    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        myFace.invalidate();

        if (seekBar.getId() == R.id.redSeekBar) { //assign red seekbar to instance variable and set values accordingly
            redBar = seekBar;
            if(myFaceModel.changeEyesColor){
                myFaceModel.RvalueEyes = progress;
            } else if(myFaceModel.changeFaceColor){
                myFaceModel.Rvalue = progress;
            } else if(myFaceModel.changeHairColor){
                myFaceModel.RvalueHair = progress;
            }

        } else if (seekBar.getId() == R.id.greenSeekBar) { //assign green seekbar to instance variable and set values accordingly
            greenBar = seekBar;
            if (myFaceModel.changeEyesColor) {
                myFaceModel.GvalueEyes = progress;
            } else if (myFaceModel.changeFaceColor) {
                myFaceModel.Gvalue = progress;
            } else if (myFaceModel.changeHairColor) {
                myFaceModel.GvalueHair = progress;
            }
        }else if (seekBar.getId() == R.id.blueSeekBar) { //assign blue seekbar to instance variable and set values accordingly
            blueBar = seekBar;
            if(myFaceModel.changeEyesColor){
                myFaceModel.BvalueEyes = progress;
            }else if(myFaceModel.changeFaceColor){
                myFaceModel.Bvalue = progress;
            }else if(myFaceModel.changeHairColor){
                myFaceModel.BvalueHair = progress;
            }
        }

        myFace.randomize(); //redraw
    }


    //spinner method
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        myFace.invalidate();

        String currentHairstyle = parent.getItemAtPosition(position).toString(); //store current spinner item

        //change hair on the face according to currentHairstyle
        if(currentHairstyle.equalsIgnoreCase("Simple Hair")){
            myFaceModel.hairStyle = 0;

        } else if(currentHairstyle.equalsIgnoreCase("Grandpa")){
            myFaceModel.hairStyle = 1;

        } else if(currentHairstyle.equalsIgnoreCase("Comb Over")){
            myFaceModel.hairStyle = 2;
            
        }

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        //get and store radio group
        myGroup = group;
        checkID = checkedId;

        //set each bar progress according to which radiobutton is pressed in the radio group
        if(checkedId == R.id.eyesRadioButton){
            myFaceModel.changeEyesColor = true;
            myFaceModel.changeFaceColor = false;
            myFaceModel.changeHairColor = false;

            redBar.setProgress(myFaceModel.RvalueEyes);
            greenBar.setProgress(myFaceModel.GvalueEyes);
            blueBar.setProgress(myFaceModel.BvalueEyes);

        } else if(checkedId == R.id.hairRadioButton){
            myFaceModel.changeFaceColor = false;
            myFaceModel.changeEyesColor = false;
            myFaceModel.changeHairColor = true;

            redBar.setProgress(myFaceModel.RvalueHair);
            greenBar.setProgress(myFaceModel.GvalueHair);
            blueBar.setProgress(myFaceModel.BvalueHair);

        } else if(checkedId == R.id.skinRadioButton){
            myFaceModel.changeFaceColor = true;
            myFaceModel.changeEyesColor = false;
            myFaceModel.changeHairColor = false;

            redBar.setProgress(myFaceModel.Rvalue);
            greenBar.setProgress(myFaceModel.Gvalue);
            blueBar.setProgress(myFaceModel.Bvalue);

        }

    }

    //setter for hairspinner
    public void setSpinner(Spinner s){
        myHairSpinner = s;
    }


    //extra methods that needed to be in this class but not used
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
