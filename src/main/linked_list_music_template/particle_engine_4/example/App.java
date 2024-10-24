/*
* c2017-2024 Courtney Brown 
* Class: Main Class for Hello World for CC3 Class Projects streaming MIDI, etc.
* Description: Demonstration of MIDI file manipulations, etc. & 'MelodyPlayer' sequencer, 2024 - add processing/interactivity
* 
*/

package com.linked_list_music_template;


//importing the JMusic stuff
import jm.music.data.*;
import jm.util.*;
//import FileSystem for cross-platform file referencing
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;

//Processing import
import processing.core.*;


//make sure this class name matches your file name, if not fix.
public class App extends PApplet {

    //access to files -- I put this in my derived MelodyManager.java class
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid"  + sys.getSeparator();
    static String appendType = ".mid"  + sys.getSeparator();
    
    //all the objects that implement an onpress
    ArrayList<OnMousePress> presses = new ArrayList<>(); 

    //all the objects that implement a draw
    ArrayList<Drawable> draws = new ArrayList<>(); 

    public static void main(String[] args) {
        PApplet.main("com.linked_list_music_template.App");       
    }

    //setup stuff that should be done before Processing code is called
    public void settings()
    {
        size(500, 500);
        setupButtons();
        //in another function, setup your linked list and add it to the draws.
    }


    //adding

    //create & add your buttons to presses & draws 
    public void setupButtons()
    {
        //helpful variables for formatting -- use or not. if you don't use, delete.
        float centerX = width/2; 
        float centerY = height/2; 
        float spacer = 8;
    }

    //doing all the setup stuff for Processing
    public void setup() {
        background(0); //defaults by drawing a black blackground but you do you.
    }

    //play the melody in real-time & draw all the buttons
    public void draw()
    {
        for(Drawable drawer : draws)
        {
            drawer.draw();
        }
    }

    //handle all the mousePressed functions for all our objects.
    public void mousePressed()
    {
        for(OnMousePress press : presses )
        {
            press.mousePressed(mouseX, mouseY);
        }
    }


}
                     