import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

	public static void main(String[] args) {
		double maxSpeed = 0.01;
		double minSpeed = 0.005;
		double radius = 0.025;
		int numBalls = 3;
		int currentScore = 0;
		int highestScore = 0;
		double playerXLocation = 0.5;
		double playerYLocation = 0.5;
		double playerSpeed = 0.01;
		Ball[] balls = new Ball[numBalls];
		
		for(int i = 0; i < numBalls; i++) {
			balls[i] = new Ball(maxSpeed, minSpeed, radius);
		}
		
		StdDraw.enableDoubleBuffering();
		
		long scoreTimer = System.currentTimeMillis();
		long difficultyTimer = System.currentTimeMillis();
		
		while (true) {
			
			StdDraw.clear();
			boolean collision = false;
			for(int i = 0; i < numBalls; i++) {
				
				Ball ball = balls[i];
				ball.move();
				ball.bounceWalls();
				for(int j = 0; j < numBalls; j++) {
					if(i != j) {
						Ball otherBall = balls[j];
						ball.checkBallCollision(otherBall);
					}
				}
				
				if (ball.checkPlayerCollision(playerXLocation, playerYLocation, radius)) {
					collision = true;
				}
			}
			
			if(collision) {
				numBalls = 3;
				balls = new Ball[numBalls];
				for(int i = 0; i < numBalls; i++) {
					balls[i] = new Ball(maxSpeed, minSpeed, radius);
					currentScore = 0;
					scoreTimer = System.currentTimeMillis();
					difficultyTimer = System.currentTimeMillis();
					playerXLocation = 0.5;
					playerYLocation = 0.5;
				}
			}
			
			if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
				playerYLocation = playerYLocation + playerSpeed;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
				playerYLocation = playerYLocation - playerSpeed;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
				playerXLocation = playerXLocation - playerSpeed;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
				playerXLocation = playerXLocation + playerSpeed;
			}
			
			if(playerXLocation > 1) {
				playerXLocation = 1;
			}
			if(playerXLocation < 0) {
				playerXLocation = 0;
			}
			if(playerYLocation > 1) {
				playerYLocation = 1;
			}
			if(playerYLocation < 0) {
				playerYLocation = 0;
			}
			
			long now = System.currentTimeMillis();
			if(now > scoreTimer + 1000) {
				currentScore++;
				if(currentScore > highestScore) {
					highestScore = currentScore;
				}
				scoreTimer = now;
			}
			
			if(now > difficultyTimer + 10000) {
				numBalls++;
				Ball[] newBalls = new Ball[numBalls];
				for(int i = 0; i < numBalls - 1; i++) {
					newBalls[i] = balls[i];
				}
				newBalls[numBalls-1] = new Ball(maxSpeed, minSpeed, radius);
				balls = newBalls;
				difficultyTimer = now;
			}
			StdDraw.setPenColor(Color.red);
			for(int i = 0; i < numBalls; i++) {
				StdDraw.filledCircle(balls[i].getXLocation(), balls[i].getYLocation(), balls[i].getRadius());
			}
			
			StdDraw.setPenColor(Color.black);
			StdDraw.filledCircle(playerXLocation, playerYLocation, radius);
			StdDraw.text(0.5, 0.1, "Score: " + currentScore + " High Score: " + highestScore);
			
			StdDraw.show();
			StdDraw.pause(10);
			
		}
	}
}

