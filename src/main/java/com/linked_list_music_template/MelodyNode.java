/* MelodyNode.java
 * Author: Sophie Knox
 * Date: 11/4/24
 * Course: CRCP3
 * Project: Music Generator with Trees
 *
 * Description:
 * This class represents a node in a linked list of melodies, each node corresponding 
 * to a specific melody managed by the TreeMelodyManager. MelodyNodes are linked to 
 * form a sequence, allowing melodies to be played in a structured order.
 * 

*/

package com.linked_list_music_template;

public class MelodyNode 
{
    private TreeMelodyManager melodies;//manages melody collection
    private int whichMelody;//specific melody index in manager
    private MelodyNode next;//next node linked list

    //creates melody node from specific melody manager and index
    public MelodyNode(TreeMelodyManager melodies, int whichMelody) 
    {
        this.melodies = melodies;
        this.whichMelody = whichMelody;
    }

    //returns melody manager
    public TreeMelodyManager getManager() 
    {
        return melodies;
    }

    //gets index of melody
    public int getMelodyValue() 
    {
        return whichMelody;
    }

    //gets next node in sequence
    public MelodyNode getNext() 
    {
        return next;
    }

    //places/sets the next node into sequence
    public void setNext(MelodyNode next) 
    {
        this.next = next;
    }

    //plays melody from this node
    public void start() 
    {
        melodies.start(whichMelody);
    }

    //sees if melody with this node has reached the end
    public boolean atEnd() 
    {
        return melodies.atEnd(whichMelody);
    }
}
