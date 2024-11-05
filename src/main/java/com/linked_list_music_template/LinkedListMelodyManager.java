

/*LinkedListMelodyManager.java
 * Author: Sophie Knox
 * Date: 11/04/24
 * Course: CRCP3
 * Project: Music Generator with Trees
 *
 * Description:

this Java class, LinkedListMelodyManager, is designed to manage a collection of melody players and MIDI files. 
It implements the Drawable interface and uses ArrayList to store instances of MelodyPlayer and MidiFileToNotes


*/


package com.linked_list_music_template;

import java.util.ArrayList;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

// Class managing a collection of melody players and MIDI files
public class LinkedListMelodyManager implements Drawable {
    // List of MelodyPlayer instances that play the melodies
    ArrayList<MelodyPlayer> players;
    // List of MidiFileToNotes instances that convert MIDI files to notes
    ArrayList<MidiFileToNotes> midiNotes;

    // Static variables for constructing MIDI file paths
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid" + sys.getSeparator();

    // Array of MIDI file names to be loaded
    String[] files = {"SoundLong"};

    // Constructor initializing the players and midiNotes lists
    public LinkedListMelodyManager() {
        players = new ArrayList<>();
        midiNotes = new ArrayList<>();
    }

    // Method to set up the manager by adding MIDI files from the predefined list
    public void setup() {
        for (int i = 0; i < files.length; i++) {
            addMidiFile(prependPath + files[i] + appendType); // Load each MIDI file
        }
    }

    // Returns the number of melody players
    public int size() {
        return players.size();
    }

    // Plays all melodies using their respective players
    public void playMelodies() {
        for (MelodyPlayer player : players) {
            player.play(); // start play on each MelodyPlayer
        }
    }

    // Adds a MIDI file to the manager, creating a corresponding MelodyPlayer
    public void addMidiFile(String filePath) {
        int index = players.size(); // Get the current index for the new player
        players.add(new MelodyPlayer(120, "Microsoft GS Wavetable Synth")); // Create and add a new MelodyPlayer
        midiNotes.add(new MidiFileToNotes(filePath)); // Create and add a MidiFileToNotes instance
        // Set the melody, rhythm, and start times for the player based on the MIDI file
        players.get(index).setMelody(midiNotes.get(index).getPitchArray());
        players.get(index).setRhythm(midiNotes.get(index).getRhythmArray());
        players.get(index).setStartTimes(midiNotes.get(index).getStartTimeArray());
    }

    //starts at given index
    public void start(int index)
    {
        players.get(index).reset(); //resets back to start
    }

    //ends at given index
    public boolean atEnd(int index)
    {
        return players.get(index).atEndOfMelody(); //sees if melody is ended
    }

    public void draw()
    {
        playMelodies();
    }

    //prints melody list content
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
}