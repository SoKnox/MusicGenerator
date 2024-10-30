/*Sophie Knox CRCP3 10/29/24 
Music Generator: With 8 midi files, creates generator using linked lists that allows you to weave, loop, clear, reverse, play, and stop the melody

The MelodyNode class represents a node in a linked list structure for managing melodies. 
Each node stores a reference to a melody (via an index) and can link to the next node in the list, enabling ordered playback and navigation.



*/


package com.linked_list_music_template;

//Node class 
public class MelodyNode 
{
    private LinkedListMelodyManager melodies; //manager
    private int whichMelody;//value represent the melodies index
    private MelodyNode next; //references next node in the list

    //constructor 
    public MelodyNode(LinkedListMelodyManager melodies, int whichMelody) 
    {
        this.melodies = melodies;
        this.whichMelody = whichMelody;
    }

    
    public LinkedListMelodyManager getManager() 
    {
        return melodies;
    }

    //returns index number
    public int getMelodyValue()
     {
        return whichMelody;
    }

    //next node getter
    public MelodyNode getNext() 
    {
        return next;
    }

    //next node setter
    public void setNext(MelodyNode next)
    {
        this.next = next;
    }

    //starts the melody
    public void start() 
    {
        melodies.start(whichMelody);
    }

    //sees if melody is at end
    public boolean atEnd() 
    {
        return melodies.atEnd(whichMelody);
    }

    public int getMelodyIndex() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMelodyIndex'");
    }
}