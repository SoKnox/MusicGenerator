/* MelodyPlayer.java
 * Author: Sophie Knox
 * Date: 11/04/24
 * Course: CRCP3
 * Project: Music Generator with Trees
The MelodyPlayer class is responsible for playing a melody by sending MIDI messages in real-time. 
It manages tempo, timing, note sequencing, and output to an external MIDI program, allowing precise playback control and synchronization
Code was provided by Courtney Brown in Particle Engine 4


 * c2017-c2023 Courtney Brown 
 * 
 * Class: MelodyPlayer
 * Description: Sends a melody of midi notes to an external player/midi channel, revised 2024 for polyphonic playing
 * 
 */
package com.linked_list_music_template;

import java.util.ArrayList;

public class MelodyPlayer {
    MidiBusCRCP outputMidiBus; // sends midi notes to an external program (eg. Ableton, Logic, etc.)

    int note_index = 0; // where we are in the notes

    float notems; // the value of a quaver or 1/4 note in millis
    float last_time; // the last time we called draw()
    boolean play; // should we play?
    float bpm; // beats per minute of melody

    double start_time; // time at creation of the melody player in milliseconds
    double playStart = 0;

    double rhythm_multiplier; // determines note length and onset of the next note

    ArrayList<Integer> melody; // list of pitches in order
    ArrayList<Double> rhythm; // list of note lengths (& thus time before next note, in order)
    ArrayList<Double> startTimes; // list of start times

    boolean hasRhythm; // has there been a list of rhythms assigned?
    boolean hasMelody; // has there been a list of pitches assigned?
    boolean hasStart = false;

    String outputBus; // bus to send MIDI to -- change if you have named it something else or you are using Windows

    // list of playing notes to keep track off for note offs
    ArrayList<Double> playingRhythms = new ArrayList<>();
    ArrayList<Double> playingTimes = new ArrayList<>();
    ArrayList<Integer> playingPitches = new ArrayList<>();

    boolean loop; // loop state
    boolean printNotes = false; // Flag to indicate if notes should be printed

    // constructor -- initializes data
    // input is the tempo - bpm (beats per minute) or how fast to play the music
    public MelodyPlayer(float tempo, String bus) {
        reset();
        setBPM(tempo);
        play = true;
        hasRhythm = false;
        rhythm_multiplier = 0.5f; // default is 1/8 notes
        start_time = System.currentTimeMillis();
        outputBus = bus;
        setupMidi();
    }

    public void setMelody(ArrayList<Integer> m) {
        melody = m;
        hasMelody = true;
    }

    public void setRhythm(ArrayList<Double> r) {
        rhythm = r;
        hasRhythm = true;
    }

    public void setStartTimes(ArrayList<Double> r) {
        startTimes = r;
        hasStart = true;
    }

    // display all ports available to the MidiBus -- only output ports are relevant, however
    // if OS X, best to choose the IAC bus we created (for mac/OS X) so can send to an external program (eg DAW/sampler)
    // if Windows, why are you LIKE this??! -- TODO: install/document virtual port on Windows via 3rd party software
    public void listDevices() {
        MidiBusCRCP.listDevices();
    }

    // create the Midi port and bus to send the notes to
    public void setupMidi() {
        outputMidiBus = new MidiBusCRCP(outputBus);
        // or if on windows - use a windows virtual port
    }

    public void setBPM(float tempo) {
        bpm = tempo;
        notems = (float) (((1.0 / (bpm / 60.0))) * 1000); // how many ms in a 1/4 note for this tempo
    }

    // time since creation of the melody player -- mimics the processing function millis()
    public int millis() {
        double millisNow = System.currentTimeMillis() - start_time;
        return (int) millisNow;
    }

    // send the melody out in MIDI messages, in correct timing, to the external program
    public void play() {
        int vel = 100; // midi velocity -- TODO: change/assign if want to vary
        double cur_time = millis(); // what time is it now?

        // send note off messages for any notes that have been playing
        sendNoteOff(cur_time);

        // just do nothing if there is no melody (pitches)
        if (!hasMelody) {
            System.out.println("There is no melody in the notes given.");
            return;
        }

        // if we're at the end of the melody also do nothing
        if (atEndOfMelody()) {
            return;
        }

        // playing the notes by sending the note ons at the correct times
        if (note_index < startTimes.size()) {
            play = cur_time - playStart >= notems * startTimes.get(note_index); // should we send the note now? based on prev. note's duration

            // send note on messages
            if (play && note_index < startTimes.size()) {
                sendNoteOn(note_index, cur_time, vel);
                note_index++;
                boolean sameTime = true;

                // send note-on messages for the notes that occur at the same time as well (eg. a chord, a dyad)
                while (note_index < startTimes.size() && sameTime) {
                    sameTime = (startTimes.get(note_index - 1).equals(startTimes.get(note_index)));
                    if (sameTime) {
                        sendNoteOn(note_index, cur_time, vel);
                        note_index++;
                    }
                }
            }
        }
    }

    // send a note on message and then add to playing note arrays
    public void sendNoteOn(int note_index, double cur_time, int vel) {
        if (printNotes) {
            System.out.println("note on:" + melody.get(note_index));
        }

        outputMidiBus.sendNoteOn(0, (int) melody.get(note_index), vel);
        playingRhythms.add(rhythm.get(note_index));
        playingTimes.add(cur_time);
        playingPitches.add(melody.get(note_index));
    }

    //send note off messages for the playing notes in our buffers
    public void sendNoteOff(double cur_time) {
        for (int i = playingRhythms.size() - 1; i >= 0; i--) {
            if (playingRhythms.get(i) * notems <= cur_time - playingTimes.get(i)) {
                outputMidiBus.sendNoteOff(0, (int) playingPitches.get(i), 0);
                playingRhythms.remove(i);
                playingPitches.remove(i);
                playingTimes.remove(i);
            }
        }
    }

    public void noteOffAllNotes() 
    {
        // TODO: implement
    }

    public ArrayList<Double> getRhythm() 
    {
        return rhythm;
    }

    public ArrayList<Integer> getMelody() 
    {
        return melody;
    }

    public ArrayList<Double> getStartTimes() 
    {
        return startTimes;
    }

    //reset note to 0
    public void reset()
     {
        note_index = 0;
        playStart = millis();

        playingRhythms.clear();
        playingPitches.clear();
        playingTimes.clear();
    }

    //set to ends
    public void setToEnd() 
    {
        note_index = melody.size();

        playingRhythms.clear();
        playingPitches.clear();
        playingTimes.clear();
    }

    //have we reached the end of the melody?
    public boolean atEndOfMelody() 
    {
        return note_index >= melody.size() && playingRhythms.size() <= 0;
    }

    //send note offs for all playing notes
    public void stopAllNotes() 
    {
        sendNoteOff(Double.MAX_VALUE);
    }

    //stop the melody
    public void stop() 
    {
        stopAllNotes();
        reset();
    }

    //set loop
    public void setLoop(boolean loop) 
    {
        this.loop = loop;
    }

    //Method to toggle printNotes flag
    public void togglePrintNotes() 
    {
        printNotes = !printNotes;
    }
}


