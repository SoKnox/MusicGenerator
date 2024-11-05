/**
 * TreeMelodyNode.java
 * Author: Sophie Knox
 * Date: 11/4/24
 * Course: CRCP3
 * Project: Music Generator with Trees
 *
 * Description:
 * The TreeMelodyNode class represents a node in a melody generation tree.
 * Each node stores a melody (sequence of pitches) and references to child nodes, allowing for hierarchical 
 * organization of melodies. This class is used in the context of generating music through tree structures 
 * and enables traversal and printing of the melody tree.
 */

package com.linked_list_music_template;

import java.util.ArrayList;

public class TreeMelodyNode {
    private ArrayList<Integer> melody;
    private ArrayList<TreeMelodyNode> nextNodes; //list of child nodes connected to this node
    private TreeMelodyManager melodyManager;
    private int index;

    public TreeMelodyNode(TreeMelodyManager melodyManager, int index, ArrayList<Integer> melody) {
        this.melodyManager = melodyManager;
        this.index = index;
        this.melody = melody;
        this.nextNodes = new ArrayList<>();
    }

    //returns melody
    public ArrayList<Integer> getMelody() 
    {
        return melody;
    }


    //returns list of child nosed
    public ArrayList<TreeMelodyNode> getNextNodes() 
    {
        return nextNodes;
    }

    //returns index
    public int getIndex() 
    {
        return index;
    }

    //returns melody manager
    public TreeMelodyManager getMelodyManager() 
    {
        return melodyManager;
    }

    //adds child node to list of nextNodes
    public void addNextNode(TreeMelodyNode node) 
    {
        nextNodes.add(node);
    }

    //prints tree's structure starting fromtis node
    //displays each node index and melody
    public void printTree(String indent)
     {
        System.out.println(indent + index + ": " + melody);
        for (TreeMelodyNode nextNode : nextNodes) 
        {
            nextNode.printTree(indent + "    ");
        }
    }
}

