/**
 *
 * FaceModel class
 *
 * This class holds variables that help build the face. This class also helps with cross-class refrences between FaceController and Face class
 * Initially this class holds values for random face generate at start of app
 *
 * @author Devam Patel
 * Date: 3/2/21
 *
 */
package com.example.facemaker;

import java.util.Random;

public class FaceModel {

    Random rand = new Random(); //rand generator used to randomize colors at startup

    //randomly generate initial values for face features at start of app
    public int Rvalue = rand.nextInt(256);
    public int Bvalue = rand.nextInt(256);
    public int Gvalue = rand.nextInt(256);

    public int RvalueEyes = rand.nextInt(256);
    public int BvalueEyes = rand.nextInt(256);
    public int GvalueEyes = rand.nextInt(256);

    public int hairStyle = rand.nextInt(3);
    public int eyeStyle = rand.nextInt(2);

    public int RvalueHair = rand.nextInt(256);
    public int GvalueHair = rand.nextInt(256);
    public int BvalueHair = rand.nextInt(256);

    //values to help create condition in FaceController
    public boolean changeFaceColor = true; // set to true so it changes on start if user desires
    public boolean changeEyesColor = false;
    public boolean changeHairColor = false;

}
