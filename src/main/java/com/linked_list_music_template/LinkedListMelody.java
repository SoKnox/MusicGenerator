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
 public class LinkedListMelody implements Drawable {
     private MelodyNode head; //variab;e
     private MelodyNode curPlayNode = null; //initialize curent node playing to null
     private boolean loopEnabled = false; //initialize to looping to fals
     private LinkedListMelodyManager manager; //refrences manager
 
     //constructor
     public LinkedListMelody(LinkedListMelodyManager manager) {
         this.manager = manager;
     }
 
     //gtter
     public LinkedListMelodyManager getManager() {
         return manager;
     }
 
     //sees if head is empty
     public boolean isEmpty() 
     {
         return head == null;
     }
 
     //places node in specific index
     public void insert(int index, MelodyNode node)
      {
         if (index == 0) 
         {
             insertAtStart(node); 
         } else 
         {
             MelodyNode current = head;
             for (int i = 0; i < index - 1 && current != null; i++) {
                 current = current.getNext();
             }
             if (current != null) {
                 node.setNext(current.getNext()); //node nect to the current
                 current.setNext(node); //links new noce to current
             }
         }
     }
 
     //places melody at start of list
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
 
     //places node at end of list
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
 
     //loop boolean sees if enabled
     public void loop(boolean enable) 
     {
         loopEnabled = enable;
     }
 
    
     public void draw() 
     {
         play();
     }
 
     //starts from head of list
     public void start()
      {
         if (head != null) 
         {
             curPlayNode = head;
             curPlayNode.start();
         }
     }
 
     //plays each node after eachother, if looping == true, starts back at head
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
              {//restarts if looping is happeing
                 curPlayNode = head;
                 curPlayNode.start();
             }
         }
     }
 
     //makes node playing == null and therefore stops the audio
     public void stop() 
     {
         curPlayNode = null;
         System.out.println("Playback stopped.");
     }
 
     //weaves the input
     public void weave(MelodyNode node, int count, int skip) 
     {
         MelodyNode temp = head;
         int index = 0;
         int inserts = 0;
 
         //goes through this list and inserts nodes
         while (temp != null && inserts < count) 
         {
             //skips nodes in list
             for (int i = 0; i < skip && temp.getNext() != null; i++)
             {
                 temp = temp.getNext();
                 index++;
             }
 
             //insert node at current position
             MelodyNode copyNode = new MelodyNode(node.getManager(), node.getMelodyValue());
             copyNode.setNext(temp.getNext());
             temp.setNext(copyNode);
             temp = copyNode.getNext();
             inserts++;
         }
     }
 
     //prints out melody
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
             melodyOutput.setLength(melodyOutput.length() - 2); // Remove trailing comma
         }
         System.out.println(melodyOutput.toString());
     }
 
     //clears list by setting head to null thus removing all the nodes
     public void clear() 
     {
         head = null; 
         System.out.println("The Melody List Is Cleared");
     }
 
     
     //Reverses list
     public void reverse() 
     {
         MelodyNode prev = null;
         MelodyNode current = head;
         MelodyNode next = null;
 
         while (current != null)
         {
             next = current.getNext();//finds next nide
             current.setNext(prev); //goes to previous node
             prev = current; //the previous node moves foward
             current = next; //the current node is moved to the nect
         }
         head = prev; //upstaed head to front
         System.out.println("Melody list reversed.");
     }
 
     //adds nodes ro list
     public void addNodes() 
     {
         for (int i = 0; i < manager.size(); i++) 
         {
             insertAtEnd(new MelodyNode(manager, i));
         }
     }
 }
 