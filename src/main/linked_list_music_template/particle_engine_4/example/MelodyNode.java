package com.linked_list_music_template;

public class MelodyNode 
{
    private MelodyManager melodyManager;  //reference to MelodyManager class
    private MelodyNode next;//next mode in list
    private int whichMelody;//index of the melody

    //constructor
    public MelodyNode(MelodyManager melodyManager, int whichMelody) 
    {
        this.melodyManager = melodyManager;
        this.whichMelody = whichMelody;
    }

    //next node getter + setter
    public MelodyNode getNext()
     {
        return next;
    }

    public void setNext(MelodyNode next)
     {
        this.next = next;
    }

    //starts the melody playing
    public void start()
     {
        if (melodyManager != null)
         {
            melodyManager.playMelody(whichMelody);
        }
    }

    //Creates copy of this MelodyNode with the same data
    public MelodyNode copy() 
    {
        MelodyNode copyNode = new MelodyNode(this.melodyManager, this.whichMelody);
        copyNode.setNext(this.next); 
        return copyNode;
    }

    //returns index of whichMelody
    public int getMelodyIndex() 
    {
        return whichMelody;
    }
}
