
/*
 * Sophie Knox
 * Particle Engine 3
 * 9/30/24
 * This project creates three sublasses of particles: an alien spaceship, cow, and stars that are confined to bounce around in the screen
 * This abstract class handles the game states: title, play, and credit
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

 package particle_engine_4.example;

 import processing.core.PApplet;
 import java.util.ArrayList;
 
 abstract class GameState 
 {
     PApplet p;
     MelodyManager melodyManager; //RUBRIC MelodyManager implemation 
 
     public GameState(PApplet p, MelodyManager melodyManager)
      {
         this.p = p;
         this.melodyManager = melodyManager;
         playGameChange();
     }
 
     //RUBRIC 10% controller
     abstract void drawState();// displays current state
     abstract void handleInput(); //key and mouse control
     abstract GameState transition(); // transition logic
 

     //RUBRIC 10% sound for changing states
     public void playGameChange() 
     {
         int gameChangeIndex = 2;
         System.out.println("Playing GameChange.mid");
         melodyManager.start(gameChangeIndex);
 
         ArrayList<Integer> gameChangeMelody = melodyManager.players.get(gameChangeIndex).melody;
         if (gameChangeMelody != null && !gameChangeMelody.isEmpty())
          {
             System.out.println("Notes in GameChange.mid");
             for (int note : gameChangeMelody) 
             {
                 System.out.println("Note: " + note);
             }
         } else {
             System.out.println("No notes in GameChange.mid");
         }
     }
 
     //this will handle the pew midi file
     public void keyPressed()
      {
         //empty because it is overriden in playstate
     }
 
     public void keyReleased() 
     {
         //empty because it is overriden in playstate
     }
 }
 