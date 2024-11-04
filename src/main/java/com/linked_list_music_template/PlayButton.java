package com.linked_list_music_template;
import processing.core.*;


//public abstract?
class PlayButton extends Button 
{
    LinkedListMelody melody;

    PlayButton(PApplet main_, LinkedListMelody melody_, String label_, float x_, float y_) 
    {
        super(main_, label_, x_, y_);
        melody = melody_;
    }

    public void onPress() 
    {
        melody.start();
    }
}

class StopButton extends Button 
{
    LinkedListMelody melody;

    StopButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) 
    {
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

    LoopButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) 
    {
        super(main_, "Loop", x_, y_);
        melody = melody_;
        changeLoop = false;
    }

    public void onPress() 
    {
        changeLoop = !changeLoop;
        melody.loop(changeLoop);
        melody.play();
    }
}

class PrintMelodyButton extends Button 
{
    LinkedListMelody melody;

    PrintMelodyButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) 
    {
        super(main_, "Print Melody", x_, y_);

        melody = melody_;
    }

    public void onPress() 
    {
        melody.print();
    }
}

class RetrainMelodyButton extends Button 
{
    TreeMelody treeMelody;

    RetrainMelodyButton(PApplet main_, LinkedListMelody melody_, TreeMelody treeMelody_, float x_, float y_) {
        super(main_, "Retrain Melody", x_, y_);
        this.treeMelody = treeMelody_;
    }

    public void onPress() 
    {
        treeMelody.train(4, (int) (Math.random() * treeMelody.getMelodyManager().size()));
    }
}

class RetrainMelodyAtZeroButton extends Button 
{
    TreeMelody treeMelody;

    RetrainMelodyAtZeroButton(PApplet main_, LinkedListMelody melody_, TreeMelody treeMelody_, float x_, float y_) 
    {
        super(main_, "Retrain Melody at 0", x_, y_);
        this.treeMelody = treeMelody_;
    }

    public void onPress() {
        treeMelody.train(4, 0);
    }
}

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
        System.out.println("Melody Tree Tested with MaryHadALittleLamb");
    }
}