package particle_engine_4.example;

/*
 * Sophie Knox
 * Particle Engine 3
 * 9/30/24
 * This project creates three sublasses of particles: an alien spaceship, cow, and stars that are confined to bounce around in the screen
 * This class draws star particle, creates bounding box for bullet collisons
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



 

 import processing.core.PApplet;
 import processing.core.PConstants;
 import java.util.ArrayList;
 
 public class Star extends Particle 
 {
     private float radius;
     private int points;
     private float angleOffset;
     private MelodyManager melodyManager; //reference to MelodyManager
     private boolean played = false; //sound plays once
 
     public Star(float x, float y, PApplet p, MelodyManager melodyManager)
      {
         super(x, y, p);
         this.radius = 10;  //size
         this.points = 5;   //# of points
         this.angleOffset = PConstants.TWO_PI / points;  //angle between two points
         this.melodyManager = melodyManager; //handles melody manager
     }
 
     @Override
     public void update(float speedFactor) 
     {
         super.update(speedFactor);
         if (checkWallCollision())
          {
             reverse();
             if (!played) 
             {
                 playStarMidi();
                 played = true; //prevents sound from playing imediatly
             }
         } else 
         {
             played = false; //no collision it resets
         }
     }
 
     @Override
     public void display() {
         p.fill(255, 255, 51);
         p.pushMatrix();
         p.translate(position.x, position.y); //moves origin
         p.beginShape();
         for (int i = 0; i < points * 2; i++) {
             float angle = i * angleOffset / 2;
             float r = (i % 2 == 0) ? radius : radius / 2;//got this from the processing website
             float x = PApplet.cos(angle) * r;
             float y = PApplet.sin(angle) * r;
             p.vertex(x, y);
         }
         p.endShape(PConstants.CLOSE);
         p.popMatrix();
     }
 
     public float getRadius() 
     {
         return radius;
     }
 
     @Override
     public float[] getBounds() 
     {
         return new float[] { position.x - radius, position.y - radius, radius * 2, radius * 2 };
     }
 
     //star wall collision
     private boolean checkWallCollision()
      {
         float particleSize = radius;//radius for star size
         boolean collided = false;
 
         //l and r walls
         if (position.x < particleSize / 2 || position.x > p.width - particleSize / 2)
          {
             collided = true;
         }
 
         //top and bottom walls
         if (position.y < particleSize / 2 || position.y > p.height - particleSize / 2)
          {
             collided = true;
         }
 
         return collided;
     }
 
     //plays Star.mid print notes to the terminal
     private void playStarMidi() 
     {
         int starMidiIndex = 3;  
         System.out.println("Playing Star.mid");
         melodyManager.start(starMidiIndex);
 
         ArrayList<Integer> starMelody = melodyManager.players.get(starMidiIndex).melody;
         if (starMelody != null && !starMelody.isEmpty())
          {
             System.out.println("Notes in Star MIDI:");
             for (int note : starMelody)
              {
                 System.out.println("Note: " + note);
             }
         } else
          {
             System.out.println("No notes in Star MIDI.");
         }
     }
 }
 
 