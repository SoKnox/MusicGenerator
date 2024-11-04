package com.linked_list_music_template;

import java.util.ArrayList;

import java.util.ArrayList;

public class TreeMelody 
{
    private TreeMelodyNode root;
    private TreeMelodyManager melodyManager;

    public TreeMelody(TreeMelodyManager melodyManager) 
    {
        this.melodyManager = melodyManager;
    }

    public TreeMelodyNode getRoot() 
    {
        return root;
    }

    public TreeMelodyManager getMelodyManager() 
    {
        return melodyManager;
    }

    public void setRoot(TreeMelodyNode root)
     {
        this.root = root;
    }

    public void setMelodyManager(TreeMelodyManager melodyManager) 
    {
        this.melodyManager = melodyManager;
    }

    public void train(int noteMotiveCount, int rootIndex)
     {
        ArrayList<MelodyPlayer> motives = melodyManager.convertToMotives(noteMotiveCount);
        root = new TreeMelodyNode(melodyManager, rootIndex, motives.get(rootIndex).getMelody());

        for (int i = 0; i < motives.size(); i++) 
        {
            ArrayList<Integer> motive = motives.get(i).getMelody();
            TreeMelodyNode currentNode = root;
            while (currentNode != null) {
                if (motive.get(0).equals(currentNode.getMelody().get(currentNode.getMelody().size() - 1))) 
                {
                    currentNode.addNextNode(new TreeMelodyNode(melodyManager, i, motive));
                }
                currentNode = currentNode.getNextNodes().isEmpty() ? null : currentNode.getNextNodes().get(0);
            }
        }
    }

    public void play() 
    {
        TreeMelodyNode currentNode = root;
        while (currentNode != null) 
        {
            MelodyPlayer player = melodyManager.getPlayer(currentNode.getIndex());
            player.play();
            ArrayList<TreeMelodyNode> nextNodes = currentNode.getNextNodes();
            if (nextNodes.size() > 0) 
            {
                int nextIndex = (int) (Math.random() * nextNodes.size());
                currentNode = nextNodes.get(nextIndex);
            } else 
            {
                currentNode = null;
            }
        }
    }

    public void printTree(TreeMelodyNode node, String indent) 
    {
        if (node == null) return;
        System.out.println(indent + node.getIndex() + ": " + node.getMelody());
        for (TreeMelodyNode nextNode : node.getNextNodes()) 
        {
            printTree(nextNode, indent + "    ");
        }
    }

    public void printTree() 
    {
        printTree(root, "");
    }
}