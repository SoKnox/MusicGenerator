package com.linked_list_music_template;

import java.util.ArrayList;

public class TreeMelodyNode 
{
    private ArrayList<Integer> melody;
    private ArrayList<TreeMelodyNode> nextNodes;
    private TreeMelodyManager melodyManager;
    private int index;

    public TreeMelodyNode(TreeMelodyManager melodyManager, int index, ArrayList<Integer> melody)
     {
        this.melodyManager = melodyManager;
        this.index = index;
        this.melody = melody;
        this.nextNodes = new ArrayList<>();
    }

    public ArrayList<Integer> getMelody() 
    {
        return melody;
    }

    public ArrayList<TreeMelodyNode> getNextNodes()
     {
        return nextNodes;
    }

    public int getIndex() 
    {
        return index;
    }

    public TreeMelodyManager getMelodyManager()
     {
        return melodyManager;
    }

    public void addNextNode(TreeMelodyNode node) 
    {
        nextNodes.add(node);
    }
}
