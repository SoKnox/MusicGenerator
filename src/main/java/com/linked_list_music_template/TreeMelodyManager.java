package com.linked_list_music_template;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;

public class TreeMelodyManager extends MelodyManager implements Drawable 
{
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid";

    float tempo = 100;
    String bus = "Microsoft GS Wavetable Synth";

    String[] files = {"MaryHadALittleLamb"};

    TreeMelodyManager() 
    {
        super();
    }

    void setFiles(String[] files_) 
    {
        files = files_;
    }

    void addPlayers(ArrayList<MelodyPlayer> p)
     {
        players.addAll(p);
    }

    ArrayList<MelodyPlayer> copyPlayers() 
    {
        ArrayList<MelodyPlayer> list = new ArrayList<>();
        list.addAll(players);
        return list;
    }

    void setup()
     {
        for (int i = 0; i < files.length; i++) 
        {
            addMidiFile(prependPath + files[i] + appendType);
        }
    }

    void clear() 
    {
        players.clear();
    }

    int fileSize()
     {
        return files.length;
    }

    int melodySize()
     {
        return players.size();
    }

    ArrayList<Integer> getMelodyPitches(int i) 
    {
        return players.get(i).getMelody();
    }

    ArrayList<MelodyPlayer> convertToMotives(int noteCount) 
    {
        System.out.println("Converting to motives with " + noteCount + " notes. This will take time...");
        ArrayList<MelodyPlayer> newPlayers = new ArrayList<>();

        for (MidiFileToNotes notes : midiNotes) 
        {
            ArrayList<Double> rhythms = notes.getRhythmArray();
            ArrayList<Double> times = notes.getStartTimeArray();
            ArrayList<Integer> pitches = notes.getPitchArray();

            double startTime = 0;
            for (int i = 0; i < pitches.size(); i++) 
            {
                MelodyPlayer player = new MelodyPlayer(tempo, bus);

                ArrayList<Double> playingRhythms = new ArrayList<>();
                ArrayList<Double> playingTimes = new ArrayList<>();
                ArrayList<Integer> playingPitches = new ArrayList<>();

                double curZero = startTime;
                for (int j = 0; j < noteCount && i + j < pitches.size(); j++)
                 {
                    playingRhythms.add(rhythms.get(i + j));
                    playingPitches.add(pitches.get(i + j));

                    if (j == 0) 
                    {
                        playingTimes.add(0.0);
                        curZero += (times.get(i + j) - curZero);
                    } else 
                    {
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

    void convertToMotivesAndReplace(int noteCount)
     {
        players = convertToMotives(noteCount);
    }

    String melodyToString(int i) 
    {
        ArrayList<Integer> pitches = players.get(i).getMelody();
        return pitches.toString();
    }

    String startTimesToString(int i)
     {
        return players.get(i).getStartTimes().toString();
    }

    void popNoteFromMelody(int i)
     {
        players.get(i).getMelody().remove(0);
        players.get(i).getStartTimes().remove(0);
        players.get(i).getRhythm().remove(0);
    }

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

    public MelodyPlayer getPlayer(int index) 
    {
        return players.get(index);
    }

    public int size()
     {
        return players.size();
    }

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

