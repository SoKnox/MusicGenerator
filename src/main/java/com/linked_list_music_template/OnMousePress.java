package com.linked_list_music_template;


/*
/*Sophie Knox CRCP3 10/29/24 
Music Generator: With 8 midi files, creates generator using linked lists that allows you to weave, loop, clear, reverse, play, and stop the melody

 * c2024 Oct Courtney Brown 
 * 
 * Interface: OnMousePress
 * Description: Anything that reacts to a Processing mousepress
 * 
 */


public interface OnMousePress {
    abstract void onPress();
    abstract void mousePressed(float mx, float my);
}
