/*
 * c2024  [Sophie Knox] using a template by Dr. Courtney Brown
 * Class: LinkedListMelody
 * Description: The LinkedListMelody class manages a sequence of musical nodes
 *  (like notes) in a linked list, letting you add, remove, play, and modify parts of a melody.
 *  It supports features like looping the melody, reversing the order of notes, and weaving new notes into the sequence. 
 * This class connects with LinkedListMelodyManager to handle these actions and control the melody’s playback.
 * 
 */
package com.linked_list_music_template;

import java.util.ArrayList;

public class LinkedListMelody implements Drawable 

{
    private MelodyNode head;
    private MelodyNode curPlayNode = null;
    private boolean loopEnabled = false;
    private TreeMelodyManager manager;

    public LinkedListMelody(TreeMelodyManager manager)
     {
        this.manager = manager;
    }

    public TreeMelodyManager getManager() 
    {
        return manager;
    }

    public boolean isEmpty() 
    {
        return head == null;
    }

    public void insert(int index, MelodyNode node) 
    {
        if (index == 0) 
        {
            insertAtStart(node);
        } else 
        {
            MelodyNode current = head;
            for (int i = 0; i < index - 1 && current != null; i++) 
            {
                current = current.getNext();
            }
            if (current != null) 
            {
                node.setNext(current.getNext());
                current.setNext(node);
            }
        }
    }

    public void insertAtStart(MelodyNode node) 
    {
        if (isEmpty()) 
        {
            head = node;
        } else 
        {
            node.setNext(head);
            head = node;
        }
    }

    public void insertAtEnd(MelodyNode node) 
    {
        if (isEmpty()) 
        {
            head = node;
        } else 
        {
            MelodyNode current = head;
            while (current.getNext() != null) 
            {
                current = current.getNext();
            }
            current.setNext(node);
        }
    }

    public void loop(boolean enable) 
    {
        loopEnabled = enable;
    }

    public void draw() 
    {
        play();
    }

    public void start()
     {
        if (head != null) 
        {
            curPlayNode = head;
            curPlayNode.start();
            System.out.println("Playback started.");
        }
    }

    public void play() 
    {
        if (curPlayNode != null && curPlayNode.atEnd()) 
        {

            MelodyNode next = curPlayNode.getNext();
            if (next != null) 
            {
                curPlayNode = next;
                curPlayNode.start();
            } else if (loopEnabled) 
            {
                curPlayNode = head;
                curPlayNode.start();
            }
        }
    }

    public void stop()
     {
        curPlayNode = null;
        System.out.println("Playback stopped.");
    }

    
    public void print() 
    {
        MelodyNode temp = head;
        StringBuilder melodyOutput = new StringBuilder("Melody: ");
        while (temp != null) 
        {
            melodyOutput.append(temp.getMelodyValue()).append(", ");
            temp = temp.getNext();
        }
        if (melodyOutput.length() > 0)
         {
            melodyOutput.setLength(melodyOutput.length() - 2);
        }
        System.out.println(melodyOutput.toString());
    }

    public void clear() 
    {
        head = null;
        System.out.println("The Melody List Is Cleared");
    }


    public void addNodes() 
    {
        for (int i = 0; i < manager.size(); i++)
         {
            insertAtEnd(new MelodyNode(manager, i));
        }
    }
}
