

/*Sophie Knox CRCP3 10/29/24 
Music Generator: With 8 midi files, creates generator using linked lists that allows you to weave, loop, clear, reverse, play, and stop the melody

This class provides abstract functionality for melody buttons in a music generator application. 
 * It allows for controlling a linked list of melodies, enabling actions such as playing, stopping, 
 * weaving, looping, clearing, and reversing melodies through various button implementations.



*/

package com.linked_list_music_template;
import processing.core.*;

public abstract class MelodyButton extends Button {


    LinkedListMelody melody; // the linked list melody to control
    Boolean changeLoop;


    //overload the constructor for the MelodyButton - use the default constructor for h & w & color
    MelodyButton(PApplet main_, LinkedListMelody melody_, String label_,float x_, float y_)
    {
        super(main_, label_, x_, y_);
        melody = melody_;
        changeLoop = false;
    }
}


//created button that is then displayed in app
class PlayButton extends MelodyButton
 {

    PlayButton(PApplet main_, LinkedListMelody melody_,float x_, float y_)
    {
        super(main_, melody_,"Play", x_, y_);
    }
    //when start button is pressed it activates start
    public void onPress()
    {
        melody.start();
    }
}


//created button that is then displayed in app
class StopButton extends MelodyButton
 {
    StopButton(PApplet main_, LinkedListMelody melody_,float x_, float y_)
    {
        super(main_, melody_,"Stop", x_, y_);
    }
    //if button is pressed stop
    public void onPress()
    {
        melody.stop();
    }
}




//created button that is then displayed in app
class LoopButton extends MelodyButton 
{

    LoopButton(PApplet main_, LinkedListMelody melody_, float x_, float y_)
     {
        super(main_, melody_, "Loop", x_, y_);
    }

    //if button is pressed, the melody loops
    public void onPress() 
    {
        changeLoop = !changeLoop;
        melody.loop(changeLoop);
        melody.play();
    }
}

//weave
class WeaveButton extends MelodyButton
 {
    private int option; 
    WeaveButton(PApplet main_, LinkedListMelody melody_, int option_, float x_, float y_) {
        super(main_, melody_, "Weave" + option_, x_, y_);
        this.option = option_;
    }


    public void onPress() 
    {
        MelodyNode node = new MelodyNode(null, 0); 
        switch (option) {
            case 1:
                melody.weave(node, 3, 4); 
                break;
            case 2:
                melody.weave(node, 5, 2);
                break;
            case 3:
                melody.weave(node, 1, 3); //create own for three
                break;
            default:
                System.out.println("Invalid weave");
        }
        System.out.println("Weave" + option + " applied");
    }
}




//clearlist
class ClearListButton extends MelodyButton 
{

    ClearListButton(PApplet main_, LinkedListMelody melody_, float x_, float y_)
    {
        super(main_, melody_, "Clear The List", x_, y_);
    }

    @Override
    public void onPress() 
    {
        melody.clear();
        System.out.println("The Melody List Is Cleared.");
    }
}


class ReverseButton extends MelodyButton 
{

    ReverseButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) 
    {
        super(main_, melody_, "Reverse The List", x_, y_);
    }

    @Override
    public void onPress() 
    {
        melody.reverse();
        System.out.println("The Melody is Reversed.");
    }
}


class UnitTestButton extends MelodyButton
 {
    private WeaveUnitTest test;

    UnitTestButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) 
    {
        super(main_, melody_, "Run Tests", x_, y_);
        this.test = new WeaveUnitTest(melody.getManager());
    }

    @Override
    public void onPress() 
    {
        System.out.println("Running The Unit Tests.");
        test.testWeave1();
        test.testWeave2();
        System.out.println("UUnit Tests Finished");
    }
}