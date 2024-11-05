/*MelodyManager.java
 * Author: Sophie Knox
 * Date: 11/04/24
 * Course: CRCP3
 * Project: Music Generator with Trees
 *
 * Description:
 * This class is responsible for managing a collection of MelodyPlayer instances and 
 * the corresponding MIDI note data. It provides functionality for adding new melodies 
 * from MIDI files, playing all melodies in sequence, and controlling playback of 
 * individual melodies. Additionally, it can reset a melody to the start or check if it 
 * has reached its end.

*/

 
package com.linked_list_music_template;

import java.util.ArrayList;

// Manages a collection of melody players and MIDI note data
public class MelodyManager 
{
    ArrayList<MelodyPlayer> players; // List of melody players
    ArrayList<MidiFileToNotes> midiNotes; // List of MIDI note sequences

    // Constructor to initialize the lists
    MelodyManager() 
    {
        players = new ArrayList<>();
        midiNotes = new ArrayList<>();
    }

    //plays all melodies by iterating over each player
    public void playMelodies() 
    {
        assert(players != null); //ensure players list is not null

        //loop through each player and call play on it
        for(int i = 0; i < players.size(); i++) 
        {
            players.get(i).play();
        }
    }

    // Adds a new MIDI file to the manager and configures a new MelodyPlayer
    void addMidiFile(String filePath) {
        int index = players.size(); // Get the current size for indexing

        // Create a new MelodyPlayer with a specific instrument
        players.add(new MelodyPlayer(100, "Microsoft GS Wavetable Synth"));
        midiNotes.add(new MidiFileToNotes(filePath)); // Add new MIDI notes from file

        int noteCount = midiNotes.get(index).getPitchArray().size(); // Get note count

        assert(noteCount > 0); // Ensure there are notes in the file

        // Set up melody, rhythm, and start times for the player using MIDI note data
        players.get(index).setMelody(midiNotes.get(index).getPitchArray());
        players.get(index).setRhythm(midiNotes.get(index).getRhythmArray());
        players.get(index).setStartTimes(midiNotes.get(index).getStartTimeArray());
    }

    //resets postion to start of melody
    void start(int index) 
    {
        players.get(index).reset();
    }

    //sees if melody is finished
    boolean atEnd(int index) 
    {
        return players.get(index).atEndOfMelody();
    }
}
