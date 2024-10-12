


/*
 * Sophie Knox
 * Particle Engine 3
 * 9/30/24
 * This project creates three sublasses of particles: an alien spaceship, cow, and stars that are confined to bounce around in the screen
 * This class controls the credit scene. There are two seperate ones for if you win or lose
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

//ending screen
//3.333% RUBRIC three game state implemented
public class CreditState extends GameState
 {
    private boolean playerWon;
    private int score;

    public CreditState(PApplet p, boolean playerWon, int score, MelodyManager melodyManager) {
        super(p, melodyManager);
        this.playerWon = playerWon;
        this.score = score;
    }

    @Override
    //drawscredit scene
    void drawState()
     {
        p.background(0);
        p.fill(255);
        p.textAlign(PApplet.CENTER);
        p.textSize(24);

        if (playerWon)
         {
            p.text("You win " + score + " stars shot!", p.width / 2, p.height / 2 - 20);
        } else {
            p.text("You lose, you hit a cow :( ", p.width / 2, p.height / 2 - 20);
            p.text("Score: " + score, p.width / 2, p.height / 2 + 20);
        }

        p.textSize(16);
        p.text("Press 'T' to go back to Title", p.width / 2, p.height / 2 + 60);
    }

    //get back to title screen
    @Override
    void handleInput() 
    {
        if (p.keyPressed && p.key == 't') 
        {
            App.currentState = new TitleState(p, melodyManager);
        }
    }

    //transition
    @Override
    GameState transition() 
    {
        return this;
    }
}

 
 