
/*
 * Sophie Knox
 * Particle Engine 3
 * 9/30/24
 * This project creates three sublasses of particles: an alien spaceship, cow, and stars that are confined to bounce around in the screen
 * This abstract class handles the spaceship, cow and stars
 * 
 * I am attempting extra credit
 * Goal of game: Shoot all the stars. Each star shot is a point. If you shoot a cow you automatically loose.
 * Left and right arrows contol spaceship's x position
 * spacebar shoots bullet
 * IF YOU CANT BEAT GAME, CHANGE STAR # TO 1 IN PLAYSTATE
 * 
 * 
 * Cows collide with eachother
 */


//RUBRIC 5% Particle super class with data and behavior
 package particle_engine_4.example;

import processing.core.PApplet;
import processing.core.PVector;

public class Particle 
{
    protected PVector position;
    protected PVector velocity;
    protected PApplet p;

    public Particle(float x, float y, PApplet p)
     {
        this.position = new PVector(x, y);
        this.velocity = new PVector(p.random(-1, 1), p.random(-1, 1)); //gives particles random velocity
        this.p = p;
    }

    public void update(float speedFactor) 
    {
        position.add(velocity.copy().mult(speedFactor));
    }

    public void display()
     {
        //default display that is overriden in subclasses
        p.ellipse(position.x, position.y, 10, 10);
    }

    public PVector getPosition()
     {
        return position;
    }

    public void setVelocity(PVector velocity) 
    {
        this.velocity = velocity;
    }

    public float[] getBounds()
     {
        return new float[]{position.x - 5, position.y - 5, 10, 10};
    }

    public void reverse()
     {
        velocity.x *= -1;
        velocity.y *= -1;
    }
}

 