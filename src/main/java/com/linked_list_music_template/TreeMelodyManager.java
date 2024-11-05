/**
 * TreeMelodyManager.java
 * Author: Sophie Knox
 * Date: 11/4/24
 * Course: CRCP3
 * Project: Music Generator with Trees
 *
 * Description:
 * The TreeMelodyManager class is responsible for managing a collection of melodies and handling their 
 * processing, transformation, and playback. It can load MIDI files, convert melodies to smaller "motives," 
 * and support operations like clearing, copying, and printing melody information. This manager integrates 
 * with MelodyPlayer objects to play and manipulate musical sequences.
 */

package com.linked_list_music_template;

import java.util.ArrayList;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class TreeMelodyManager extends MelodyManager implements Drawable {
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid";

    float tempo = 100;
    String bus = "Microsoft GS Wavetable Synth";

    String[] files = {"MaryHadALittleLamb"};
    //everything above loads up MaryMadALittleLamb

    //constructor
    TreeMelodyManager()
    {
        super();
    }
    
    //loads and sets list of Midi files
    void setFiles(String[] files_) 
    {
        files = files_;
    }

    //adds melodyplayer to manager player list
    void addPlayers(ArrayList<MelodyPlayer> p) 
    {
        players.addAll(p);
    }

    //creates and returns copy of player list
    ArrayList<MelodyPlayer> copyPlayers() 
    {
        ArrayList<MelodyPlayer> list = new ArrayList<>();
        list.addAll(players);
        return list;
    }

    //loads Midi files into manager
    void setup() 
    {
        for (int i = 0; i < files.length; i++) 
        {
            addMidiFile(prependPath + files[i] + appendType);
        }
    }

    //clears surrent list
    void clear() 
    {
        players.clear();
    }

    //returns number of Midi files
    int fileSize() 
    {
        return files.length;
    }

    //returns number of loaded melodies
    int melodySize() 
    {
        return players.size();
    }

    //gets pitches from specific player
    ArrayList<Integer> getMelodyPitches(int i) 
    {
        return players.get(i).getMelody();
    }
    //converts melodies to smaller motives
    ArrayList<MelodyPlayer> convertToMotives(int noteCount) {
        System.out.println("Converting to motives with " + noteCount + " notes. This will take time...");
        ArrayList<MelodyPlayer> newPlayers = new ArrayList<>();

        for (MidiFileToNotes notes : midiNotes) {
            ArrayList<Double> rhythms = notes.getRhythmArray();
            ArrayList<Double> times = notes.getStartTimeArray();
            ArrayList<Integer> pitches = notes.getPitchArray();

            double startTime = 0;
            for (int i = 0; i < pitches.size(); i++) {
                MelodyPlayer player = new MelodyPlayer(tempo, bus);

                ArrayList<Double> playingRhythms = new ArrayList<>();
                ArrayList<Double> playingTimes = new ArrayList<>();
                ArrayList<Integer> playingPitches = new ArrayList<>();

                double curZero = startTime;
                for (int j = 0; j < noteCount && i + j < pitches.size(); j++) {
                    playingRhythms.add(rhythms.get(i + j));
                    playingPitches.add(pitches.get(i + j));

                    if (j == 0) {
                        playingTimes.add(0.0);
                        curZero += (times.get(i + j) - curZero);
                    } else {
                        playingTimes.add(times.get(i + j) - curZero);
                    }
                    startTime = times.get(i + j);
                }

                player.setStartTimes(playingTimes);
                player.setMelody(playingPitches);
                player.setRhythm(playingRhythms);
                newPlayers.add(player);

                i += (noteCount - 1);
            }
        }
        System.out.println("Finished converting. There are now " + newPlayers.size() + " melodies.");
        return newPlayers;
    }

    //replaces current player with new motives generated from og melodies
    void convertToMotivesAndReplace(int noteCount) 
    {
        players = convertToMotives(noteCount);
    }

    //returns melody of specific player as a string
    String melodyToString(int i) 
    {
        ArrayList<Integer> pitches = players.get(i).getMelody();
        return pitches.toString();
    }

    //returns the start times of a specific player’s melody as a string
    String startTimesToString(int i) 
    {
        return players.get(i).getStartTimes().toString();
    }

    //Removes the first note from the specified melody and updates its timing and rhythm
    void popNoteFromMelody(int i) 
    {
        players.get(i).getMelody().remove(0);
        players.get(i).getStartTimes().remove(0);
        players.get(i).getRhythm().remove(0);
    }

    //stops all notes from playing
    void stopAll() 
    {
        for (MelodyPlayer player : players) 
        {
            player.stopAllNotes();
        }
    }

    public void draw() 
    {
        playMelodies();
    }

    //retrieves a specific MelodyPlayer
    public MelodyPlayer getPlayer(int index) 
    {
        return players.get(index);
    }

    //returns the total number of loaded players
    public int size() 
    {
        return players.size();
    }

    //prints information about all managed melodies
    public void print() 
    {
        StringBuilder melodyOutput = new StringBuilder("Tree Melody Manager: ");
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


