/**
 *
 * FaceMaker app that initially designs a random face, then allows the user to interact and change its features such as skin tone, hair tone, and eye color.
 *
 * @author Devam Patel
 * Date: 3/2/21
 *
 * Note: This app is ment to run on android emulators, it is not scaled correctly for tablets.
 */






package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find face surface view and link
        Face faceView = (Face)findViewById(R.id.faceSurfaceView);
        FaceController myController = new FaceController(faceView);

        //find and set random face button listener
        Button randomFace = (Button)findViewById(R.id.randomFaceButton);
        randomFace.setOnClickListener(myController);

        //find and set seekBar listeners
        SeekBar redBar = (SeekBar)findViewById(R.id.redSeekBar);
        SeekBar greenBar = (SeekBar)findViewById(R.id.greenSeekBar);
        SeekBar blueBar = (SeekBar)findViewById(R.id.blueSeekBar);

        redBar.setOnSeekBarChangeListener(myController);
        greenBar.setOnSeekBarChangeListener(myController);
        blueBar.setOnSeekBarChangeListener(myController);

        //set starting values for each seekbar according to randomly generated values
        redBar.setProgress(faceView.getFaceModel().Rvalue);
        greenBar.setProgress(faceView.getFaceModel().Gvalue);
        blueBar.setProgress(faceView.getFaceModel().Bvalue);

        //find and set radioButton listeners
        RadioButton radioEyes = (RadioButton)findViewById(R.id.eyesRadioButton);
        RadioButton radioSkin = (RadioButton)findViewById(R.id.skinRadioButton);
        RadioButton radioHair = (RadioButton)findViewById(R.id.hairRadioButton);
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioButtonGroup);

        radioEyes.setOnClickListener(myController);
        radioSkin.setOnClickListener(myController);
        radioHair.setOnClickListener(myController);
        radioGroup.setOnCheckedChangeListener(myController);


        //find and set up spinner
        Spinner hairSpinner = (Spinner)findViewById(R.id.hairStyleSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hairstyles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSpinner.setAdapter(adapter);
        hairSpinner.setOnItemSelectedListener(myController);

        myController.setSpinner(hairSpinner);



    }



}