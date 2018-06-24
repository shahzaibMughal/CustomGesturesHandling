package com.shahzaib.customgestureshandling;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements GestureOverlayView.OnGesturePerformedListener{

    /**
     The Android SDK allows developer to draw custom gestures that used to trigger events when performed by the user.
     These gesture files which developer create(draw) are then stored in a gesture file bundled with an Android application package.
     These custom gesture files are
     most easily created using the Gesture Builder application.

     ***************** How to create Gestures
     launch the Gesture Builder application in your device, and draw the gesture that you want to save.
     Once the gestures have been designed, the file containing the gesture data can be pulled
     off the SD card of the device and added to the application project.

     Within the application code, the file is then loaded into an instance of the GestureLibrary class where
     it can be used to search for matches to any gestures performed by the user on the device
     display.


     ** The GestureOverlayView Class:
     In order to facilitate the detection of gestures within an application, the Android SDK
     provides the GestureOverlayView class. This is a transparent view that can be placed over
     other views in the user interface for the sole purpose of detecting gestures.
     matlb user GestureOverlayView Container k ander gesture draw kry ga.


     ** Detecting Gestures:
     Gestures are detected by loading the gestures file and then registering a GesturePerformedListener event listener
     on an instance of the GestureOverlayView class.
     The enclosing class is then declared to implement both the
     OnGesturePerformedListener interface and the corresponding onGesturePerformed
     callback method required by that interface. In the event that a gesture is detected by the
     listener, a call to the onGesturePerformed callback method is triggered by the Android
     runtime system.


     ** Identifying Specific Gestures:
     When a gesture is detected, the onGesturePerformed callback method is called and passed
     as arguments a reference to the GestureOverlayView object on which the gesture was
     detected, together with a Gesture object containing information about the gesture.
     With access to the Gesture object, the GestureLibrary can then be used to compare the
     detected gesture to those contained in the gestures file previously loaded into the
     application. The GestureLibrary reports the probability that the gesture performed by the
     user matches an entry in the gestures file by calculating a prediction score for each
     gesture. A prediction score of 1.0 or greater is generally accepted to be a good match
     between a gesture stored in the file and that performed by the user on the device display.




     ******  Tutorial
     --> sub sy pehly GestureBuilder app main custom gestures draw krny hain aur phr gesture ki file ko project k RAW folder main copy krna hy.
     --> phr activiy start hoty hi gesture file ko load kr lyna hy
     --> phr onGesturePerformListener ko GestureOverlayView k sath register krna hy aur user jo gesture perform kry ga us ko
         gestures file jo load ki hy us k andr mojood gestures sy match krna hy
         Remember: 1.0 sy greater prediction score IS GOOOOOOOOOOOOOOOOOOOOOOD



     */




    private GestureLibrary gestureLibrary;
    GestureOverlayView gestureOverlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureOverlayView = findViewById(R.id.gestureOverlayView);
        gestureOverlayView.addOnGesturePerformedListener(this);


        gestureLibrary = GestureLibraries.fromRawResource(this,R.raw.gestures);
        boolean isGestureFileLoaded = gestureLibrary.load();
        if(!isGestureFileLoaded)
        {
            throw new UnsupportedOperationException("Error while loading gesture file...");
        }

    }





    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = gestureLibrary.recognize(gesture);
        if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
            String action = predictions.get(0).name;
            Toast.makeText(this,action, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Not recognized", Toast.LENGTH_SHORT).show();
        }
    }
}
