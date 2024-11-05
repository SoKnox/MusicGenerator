/**
 * TreeMelody.java
 * Author: Sophie Knox
 * Date: 11/4/24
 * Course: CRCP3
 * Project: Music Generator with Trees
 *
 * Description:
 * The TreeMelody class extends LinkedListMelody and represents a structure where melodies are organized in a tree-like hierarchy. 
 * This class allows for the training of melody sequences by linking motives based on melodic similarities, 
 * starting from a root node. It supports playing through the melody tree and printing its structure.
 */

package com.linked_list_music_template;

import java.util.ArrayList;

public class TreeMelody extends LinkedListMelody {
    private TreeMelodyNode root;
    private TreeMelodyManager melodyManager;

    //constructor
    public TreeMelody(TreeMelodyManager melodyManager) 
    {
        super(melodyManager);
        this.melodyManager = melodyManager;
    }

    //retrieves and returns root node from melody tree
    public TreeMelodyNode getRoot() 
    {
        return root;
    }

    //retreives melody manager used in this tree
    public TreeMelodyManager getMelodyManager() 
    {
        return melodyManager;
    }

    //sets root node of melody tree
    public void setRoot(TreeMelodyNode root) 
    {
        this.root = root;
    }

    //sets the melody manager for tree
    public void setMelodyManager(TreeMelodyManager melodyManager) 
    {
        this.melodyManager = melodyManager;
    }

    //trains the melody tree by creating a root node and linking motives 
    public void train(int noteMotiveCount, int rootIndex) 
    {
        //converts melodies into smaller motives using note count
        ArrayList<MelodyPlayer> motives = melodyManager.convertToMotives(noteMotiveCount);
        //initialize root node with melody of the specific root motive
        root = new TreeMelodyNode(melodyManager, rootIndex, motives.get(rootIndex).getMelody());

        //loop through each motive and connect in melody tree
        for (int i = 0; i < motives.size(); i++) 
        {
            ArrayList<Integer> motive = motives.get(i).getMelody();
            TreeMelodyNode currentNode = root;
            //go through nodes and link matching ending and starting notes
            while (currentNode != null) 
            {
                if (motive.get(0).equals(currentNode.getMelody().get(currentNode.getMelody().size() - 1))) 
                {
                    currentNode.addNextNode(new TreeMelodyNode(melodyManager, i, motive));
                }
                currentNode = currentNode.getNextNodes().isEmpty() ? null : currentNode.getNextNodes().get(0);
            }
        }
    }

   //Plays the melody by traversing the tree from the root, randomly selecting next nodes.
     
    public void play() 
    {
        TreeMelodyNode currentNode = root;
        //go through and play each node in the tree until reaching a leaf
        while (currentNode != null) 
        {
            MelodyPlayer player = melodyManager.getPlayer(currentNode.getIndex());
            player.play(); //play current node melody
            
            ArrayList<TreeMelodyNode> nextNodes = currentNode.getNextNodes();
            //randomly pick one to continue playing
            if (nextNodes.size() > 0) 
            {
                int nextIndex = (int) (Math.random() * nextNodes.size());
                currentNode = nextNodes.get(nextIndex);
            } else 
            {
                //if no more nodes, stop
                currentNode = null;
            }
        }
    }


    //prints melody tree structure from the root 
    public void printTree() 
    {
        if (root != null) 
        {
            root.printTree("");
        }
    }
}

