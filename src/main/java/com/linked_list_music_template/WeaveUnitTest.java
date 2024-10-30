/*Sophie Knox CRCP3 10/29/24 
Music Generator: With 8 midi files, creates generator using linked lists that allows you to weave, loop, clear, reverse, play, and stop the melody

The WeaveUnitTest class tests the weave functionality of the LinkedListMelody class. 
It inserts specific melody nodes into a linked list at set intervals, verifying that nodes are correctly woven into the sequence by
 printing the resulting list after each test.



*/


package com.linked_list_music_template;

//Weave Test
public class WeaveUnitTest
{
    private LinkedListMelody melodyList;
    private LinkedListMelodyManager manager;

    //constructor
    public WeaveUnitTest(LinkedListMelodyManager manager)
    {
        this.manager = manager; //initialize the manager
        this.melodyList = new LinkedListMelody(manager); //initialize the melody list with the manager
    }

    //tests weave 1
    public void testWeave1()
    {
        MelodyNode node = new MelodyNode(manager, 0); //create a new melody node with index 0
        for (int i = 0; i < 12; i++)
        {
            melodyList.insertAtEnd(new MelodyNode(manager, 3)); //add 12 melody nodes with index 3 to the end of the list
        }

        melodyList.weave(node, 3, 4); //weave the node into the list 3 times every 4 nodes
        melodyList.print(); //print the melody list to verify the result
        //expected output: Melody: 3, 3, 3, 0, 3, 3, 3, 0, 3, 3, 3, 0
    }

    //test the weave function with count * skip greater than melody length
    public void testWeave2()
    {
        MelodyNode node = new MelodyNode(manager, 0); //create a new melody node with index 0
        for (int i = 0; i < 12; i++)
        {
            melodyList.insertAtEnd(new MelodyNode(manager, 3)); //add 12 melody nodes with index 3 to the end of the list
        }

        melodyList.weave(node, 5, 10); //weave the node into the list 5 times every 10 nodes
        melodyList.print(); //print the melody list to verify the result
        //expected output: Melody: 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 0, 3, 3
    }
}