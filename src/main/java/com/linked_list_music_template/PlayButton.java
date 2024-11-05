/**
 * PlayButton.java
 * Author: Sophie Knox
 * Date: 11/4/24
 * Course: CRCP3
 * Project: Music Generator with Trees
 *
 * Description:
 * This code defines various button classes that interact with melodies and the melody tree structure in the application.
 * Each button class provides specific functionality, such as starting, stopping, looping, and printing melodies, as well as retraining the melody tree.
 */

package com.linked_list_music_template;

import processing.core.PApplet;
import java.util.Random;

public class PlayButton extends Button 
{
    LinkedListMelody melody;
    TreeMelody treeMelody;
    TreeMelodyManager manager;

    PlayButton(PApplet main_, LinkedListMelody melody_, TreeMelody treeMelody_, TreeMelodyManager manager_, String label_, float x_, float y_) 
    {
        super(main_, label_, x_, y_);
        this.melody = melody_;
        this.treeMelody = treeMelody_;
        this.manager = manager_;
    }

    //plays melody from begining
    public void onPress() 
    {
        System.out.println("Play button pressed");

        //start playing the SoundLong melody from the beginning
        melody.start();
    }
}

// This is the PlayButton with Use a random index for the root motive. I could not get it to work. It would not play and then other buttons would not work :(
/* 
public class PlayButton extends Button {
    LinkedListMelody melody;
    TreeMelody treeMelody;
    TreeMelodyManager manager;

    PlayButton(PApplet main_, LinkedListMelody melody_, TreeMelody treeMelody_, TreeMelodyManager manager_, String label_, float x_, float y_) {
        super(main_, label_, x_, y_);
        this.melody = melody_;
        this.treeMelody = treeMelody_;
        this.manager = manager_;
    }

    public void onPress() {
        System.out.println("Play button pressed");
        Random random = new Random();
        int rootIndex = random.nextInt(manager.size());

        // Ensure the root index is not the same as the first melody
        while (manager.getMelodyPitches(rootIndex).equals(manager.getMelodyPitches(0))) {
            rootIndex = random.nextInt(manager.size());
        }

        treeMelody.train(4, rootIndex);
        melody.start();
    }
}

*/




//stops melody from playing
class StopButton extends Button 
{
    LinkedListMelody melody;

    StopButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
        super(main_, "Stop", x_, y_);
        melody = melody_;
    }

    public void onPress() 
    {
        melody.stop();
    }
}


class LoopButton extends Button
 {
    LinkedListMelody melody;
    boolean changeLoop;

    LoopButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
        super(main_, "Loop", x_, y_);
        melody = melody_;
        changeLoop = false; //set to false
    }
//Toggles looping for when melody is playing
    public void onPress() 
    {
        if (melody.isPlaying()) 
        {
            changeLoop = !changeLoop;
            melody.loop(changeLoop);
        } else 
        {
            System.out.println("Cannot enable loop. Melody is not playing.");
        }
    }
}

class PrintMelodyButton extends Button 
{
    LinkedListMelody melody;

    PrintMelodyButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
        super(main_, "Print Melody", x_, y_);
        melody = melody_;
    }
    //toggles melody printing note output
    public void onPress() 
    {
        melody.togglePrintNotes();
        melody.print();
    }
}

//retrains the melody tree with a random melody root
class RetrainMelodyButton extends Button 
{
    TreeMelody treeMelody;

    RetrainMelodyButton(PApplet main_, LinkedListMelody melody_, TreeMelody treeMelody_, float x_, float y_) 
    {
        super(main_, "Retrain Melody", x_, y_);
        this.treeMelody = treeMelody_;
    }

    public void onPress() 
    {
        treeMelody.train(4, (int) (Math.random() * treeMelody.getMelodyManager().size()));
    }
}

//retrains the melody tree starting from the melody at index 0
class RetrainMelodyAtZeroButton extends Button 
{
    TreeMelody treeMelody;

    RetrainMelodyAtZeroButton(PApplet main_, LinkedListMelody melody_, TreeMelody treeMelody_, float x_, float y_) {
        super(main_, "Retrain Melody at 0", x_, y_);
        this.treeMelody = treeMelody_;
    }

    public void onPress() 
    {
        treeMelody.train(4, 0);
    }
}

//clears melody list
class ClearMelodyButton extends Button 
{
    LinkedListMelody melody;

    ClearMelodyButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) 
    {
        super(main_, "Clear Melody", x_, y_);
        melody = melody_;
    }

    public void onPress() 
    {
        melody.clear();
    }
}

//Sets up and tests the melody tree using a sample file, then prints the structure of the tree
class TestMelodyTreeButton extends Button 
{
    TreeMelody treeMelody;

    TestMelodyTreeButton(PApplet main_, LinkedListMelody melody_, TreeMelody treeMelody_, float x_, float y_) 
    {
        super(main_, "Test Melody Tree", x_, y_);
        this.treeMelody = treeMelody_;
    }

    public void onPress() 
    {
        TreeMelodyManager manager = new TreeMelodyManager();
        String[] files = {"MaryHadALittleLamb"};
        manager.setFiles(files);
        manager.setup();
        manager.convertToMotivesAndReplace(4);
        treeMelody.setRoot(null);
        treeMelody.setMelodyManager(manager);
        treeMelody.train(4, 0);
        treeMelody.printTree();
        System.out.println("MaryHadALittleLamb: Melody Tree Tested");
    }
}