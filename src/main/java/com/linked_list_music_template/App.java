
/*Sophie Knox CRCP3 10/29/24 
Music Generator: With 8 midi files, creates generator using linked lists that allows you to weave, loop, clear, reverse, play, and stop the melody

This file is the main application of this music generator. It sets up buttons to contol the musical operations in real time




*/


package com.linked_list_music_template;
import processing.core.PApplet;
import java.util.ArrayList;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class App extends PApplet 
{
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid" + sys.getSeparator();

    ArrayList<OnMousePress> presses = new ArrayList<>();
    ArrayList<Drawable> draws = new ArrayList<>();

    LinkedListMelodyManager manager = new LinkedListMelodyManager();
    LinkedListMelody melody = new LinkedListMelody(manager);

    public static void main(String[] args) 
    {
        PApplet.main("com.linked_list_music_template.App");
    }

    public void settings()
     {
        size(500, 500);
        manager.setup();
        addNodes();
        setupButtons();
        addMelodyDraw();
        manager.print();
        melody.print();
    }

    public void addMelodyDraw() {
        draws.add(melody);
        draws.add(manager);
    }

    void addNodes() {
        for (int i = 0; i < manager.size(); i++) {
            melody.insertAtEnd(new MelodyNode(manager, i));
        }
    }

    //draws buttons on main page
    public void setupButtons() {
        float centerX = width / 2;
        float centerY = height / 2;
        float spacer = 50;

        PlayButton play = new PlayButton(this, melody, centerX, centerY);
        draws.add(play);
        presses.add(play);

        StopButton stop = new StopButton(this, melody, centerX, centerY + spacer);
        draws.add(stop);
        presses.add(stop);

        LoopButton loop = new LoopButton(this, melody, centerX, centerY + 2 * spacer);
        draws.add(loop);
        presses.add(loop);

        WeaveButton weave1 = new WeaveButton(this, melody, 1, centerX, spacer + 50);
        draws.add(weave1);
        presses.add(weave1);

        WeaveButton weave2 = new WeaveButton(this, melody, 2, centerX, spacer + 90);
        draws.add(weave2);
        presses.add(weave2);

        WeaveButton weave3 = new WeaveButton(this, melody, 3, centerX, spacer + 130);
        draws.add(weave3);
        presses.add(weave3);

        ClearListButton clear = new ClearListButton(this, melody, centerX, spacer + 170);
        draws.add(clear);
        presses.add(clear);

        ReverseButton reverse = new ReverseButton(this, melody, centerX, spacer + 400);
        draws.add(reverse);
        presses.add(reverse);

        UnitTestButton test = new UnitTestButton(this, melody, centerY, spacer);
        draws.add(test);
        presses.add(test);
    }

    public void setup() {
        background(0); // Defaults by drawing a black background but you do you.
    }

    public void draw() {
        for (Drawable drawer : draws) {
            drawer.draw();
        }
    }

    public void mousePressed() {
        for (OnMousePress press : presses) {
            press.mousePressed(mouseX, mouseY);
        }
    }
}

