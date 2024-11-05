/*
 * c2024  [Sophie Knox] using a template by Dr. Courtney Brown
 * Class: LinkedListMelody
 * LinkedListMelody.java
 * Author: Sophie Knox
 * Date: 11/04/24
 * Course: CRCP3
 * Project: Music Generator with Trees
 *
 * Description:
 * Description: The LinkedListMelody class manages a sequence of musical nodes
 *  (like notes) in a linked list, letting you start,stop,loop,clear,andprint.
 *
 * 
 */
package com.linked_list_music_template;

import java.util.ArrayList;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class LinkedListMelody implements Drawable {
    
    ArrayList<MelodyPlayer> players;
    
    ArrayList<MidiFileToNotes> midiNotes;

    
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid" + sys.getSeparator();

    //array of MIDI file names to be loaded
    String[] files = {"SoundLong"};
    
    boolean isPlaying = false;
    boolean isLooping = false;

    //constructor 
    public LinkedListMelody(TreeMelodyManager manager) 
    {
        players = new ArrayList<>();
        midiNotes = new ArrayList<>();
        setup();
    }


    public void setup() 
    {
        for (int i = 0; i < files.length; i++) 
        {
            addMidiFile(prependPath + files[i] + appendType); // Load each MIDI file
        }
    }

    // Returns the number of melody players
    public int size() 
    {
        return players.size();
    }

    // Plays all melodies using their respective players
    public void playMelodies() 
    {
        if (isPlaying) {
            for (MelodyPlayer player : players) 
            {
                player.play();
                if (player.atEndOfMelody()) 
                {
                    if (isLooping) 
                    {
                        player.reset(); //if enabled rests looping
                    } else 
                    {
                        player.stop(); //if disabled, stops player from looping
                    }
                }
            }
        }
    }

    //adds a MIDI file to the manager, creating a corresponding MelodyPlayer
    public void addMidiFile(String filePath) 
    {
        int index = players.size(); //get the current index for the new player
        players.add(new MelodyPlayer(120, "Microsoft GS Wavetable Synth")); // Create and add a new MelodyPlayer
        midiNotes.add(new MidiFileToNotes(filePath)); // Create and add a MidiFileToNotes instance
        //set the melody, rhythm, and start times for the player based on the MIDI file
        players.get(index).setMelody(midiNotes.get(index).getPitchArray());
        players.get(index).setRhythm(midiNotes.get(index).getRhythmArray());
        players.get(index).setStartTimes(midiNotes.get(index).getStartTimeArray());
    }

    // plays all melodies
    public void start() 
    {
        isPlaying = true;
        for (MelodyPlayer player : players) 
        {
            player.reset(); //rests player to start
        }
    }

    //stops playing melodys
    public void stop() 
    {
        isPlaying = false;
        for (MelodyPlayer player : players) 
        {
            player.stop(); //stop all players
        }
    }

    //sets looping state
    public void loop(boolean changeLoop) 
    {
        isLooping = changeLoop;
    }

    //sees if melody is playing
    public boolean isPlaying() 
    {
        return isPlaying;
    }

    //clears midi notes and players
    public void clear() 
    {
        players.clear();
        midiNotes.clear();
    }

    //checks if the melody has reached the end by looking at index
    public boolean atEnd(int index) 
    {
        return players.get(index).atEndOfMelody();
    }

  
    public void draw() {
        playMelodies();
    }

    //prints the content of melody list
    public void print() 
    {
        StringBuilder melodyOutput = new StringBuilder("Melody Manager: ");
        for (int i = 0; i < players.size(); i++) 
        {
            melodyOutput.append("Melody ").append(i).append(", ");
        }
        if (melodyOutput.length() > 0) 
        {
            melodyOutput.setLength(melodyOutput.length() - 2);
        }
        System.out.println(melodyOutput.toString());
    }

    //plays melodies
    public void play() 
    {
        playMelodies();
    }

    //toggle printing of notes 
    public void togglePrintNotes() 
    {
        for (MelodyPlayer player : players) 
        {
            player.togglePrintNotes();
        }
    }
}
