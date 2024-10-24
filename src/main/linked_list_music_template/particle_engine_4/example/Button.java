/*
 * c2024 Oct Courtney Brown 
 * 
 * Class: Button
 * Description: A generic button object. If you want to spice it up by adding things like changing colors on mouse press, changing fonts,
 * etc. etc. feel free dudes, but this is NOT required.
 * 
 * To use -- inherit from this class & implement in the 'onPressed' method
 * 
 */


package com.linked_list_music_template;

import processing.core.*;

public abstract class Button implements OnMousePress, Drawable {
    PApplet main; //access to Processing
    float height; //how tall the button is
    float width; //how wide the button is

    float x; //location
    float y;

    int color; //color of the button
    String label; //the text that will be writton on top of the button

    //indents for placement of text inside the button. maximized for the default sizes -- write a get/set to change these if you want.
    int textIndentX = 10;
    int textIndexY = 5;
    

    //initializes all these variables & the main object reference
    Button(PApplet main_, String label_, float x_, float y_, float w, float h, int c )
    {
        main = main_;
        x = x_;
        y = y_;
        width = w; 
        height = h; 
        color = c; 
        label = label_;
    }

    //overloading the constructor for a default color white
    Button(PApplet main_, String label_, float x_, float y_, float w, float h)
    {
        this(main_, label_, x_, y_, w, h, main_.color(255)); 
    }

    //overload the constructor for default sizes 150 x 25
    Button(PApplet main_, String label_,float x_, float y_)
    {
        this(main_, label_, x_, y_, 150, 25, main_.color(255)); 
    }


    //draws the button according to parameters.
    public void draw()
    {
        main.fill(color);
        main.rectMode(PApplet.CENTER);
        main.rect(x, y, width, height);
        main.fill(0); 
        main.text(label, textIndentX+(x-width/2), y+textIndexY);
    }

    //call this in the Processing main mousePressed (or mouseClicked)
    public void mousePressed(float mx, float my)
    {
        if( mx < x + width/2 && mx > x - width/2 && my < y + height/2 && my > y - height/2  )
        {
            onPress();
        }
    }

    //some get/sets -- useful for formatting
    float getWidth(){return width;}
    float getHeight(){return height;}
    float getX(){return x;}
    float getY(){return y;}

}
