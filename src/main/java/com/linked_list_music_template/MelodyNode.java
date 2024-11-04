/*Sophie Knox CRCP3 10/29/24 
Music Generator: With 8 midi files, creates generator using linked lists that allows you to weave, loop, clear, reverse, play, and stop the melody

The MelodyNode class represents a node in a linked list structure for managing melodies. 
Each node stores a reference to a melody (via an index) and can link to the next node in the list, enabling ordered playback and navigation.



*/

package com.linked_list_music_template;

public class MelodyNode {
    private TreeMelodyManager melodies;
    private int whichMelody;
    private MelodyNode next;

    public MelodyNode(TreeMelodyManager melodies, int whichMelody) {
        this.melodies = melodies;
        this.whichMelody = whichMelody;
    }

    public TreeMelodyManager getManager() {
        return melodies;
    }

    public int getMelodyValue() {
        return whichMelody;
    }

    public MelodyNode getNext() {
        return next;
    }

    public void setNext(MelodyNode next) {
        this.next = next;
    }

    public void start() {
        melodies.start(whichMelody);
    }

    public boolean atEnd() {
        return melodies.atEnd(whichMelody);
    }
}
