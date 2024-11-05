/*
 * c2024 Oct Courtney Brown 
 * 
/*Sophie Knox CRCP3 10/29/24 
Music Generator: With 8 midi files, creates generator using linked lists that allows you to weave, loop, clear, reverse, play, and stop the melody

 * Class: Button
 * Description: A generic button object. If you want to spice it up by adding things like changing colors on mouse press, changing fonts,
 * etc. etc. feel free dudes, but this is NOT required.
 * 
 * To use -- inherit from this class & implement in the 'onPressed' method
 * Class: Button
 * Button.java
 * Author: Sophie Knox
 * Date: 11/04/24
 * Course: CRCP3
 * Project: Music Generator with Trees
 *

 */
package com.linked_list_music_template;

import processing.core.PApplet;

abstract class Button implements OnMousePress, Drawable {
    protected PApplet main;
    protected String label;
    protected float x, y;

    public Button(PApplet main_, String label_, float x_, float y_) {
        main = main_;
        label = label_;
        x = x_;
        y = y_;
    }

    //basis for shape
    public void draw() 
    {
        main.fill(255);
        main.rect(x - 50, y - 20, 100, 40);
        main.fill(0);
        main.textAlign(main.CENTER, main.CENTER);
        main.text(label, x, y);
    }


    public void mousePressed(float mouseX, float mouseY) {
        if (mouseX > x - 50 && mouseX < x + 50 && mouseY > y - 20 && mouseY < y + 20) {
            System.out.println(label + " button pressed");
            onPress();
        }
    }

    public abstract void onPress();
}


