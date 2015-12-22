package game.HomeMadePacMan;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.game.engine.Game;
import org.game.engine.GameApplication;

public class HomeMadePacMan extends Game{

	public static void main(String[] args) {
		GameApplication.start(new HomeMadePacMan());
	}
	
	final int STEP = 2;
	BufferedImage pacMan;
	int frame;
	int xPos, yPos; //position of pacMan
	int dir; //direction of pacMan
	
	public HomeMadePacMan(){
		title = "HomeMadePacMan";
		frame = 0;
		dir = KeyEvent.VK_RIGHT; //initialize to start facing left
		xPos = 300;
		yPos = 200;
		//width = height = 400;
		try{
		pacMan = ImageIO.read(new File("images/packman.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	//CHANGE DIRECTION BY GETTING KEY PRESSED
	public void keyPressed(KeyEvent e){
		dir = e.getKeyCode();
	}
	
	public void update() {
		// UPDATE THE FRAME TO UPDATE IMAGE AND ANIMATE
		frame++;
		if(frame > 2){
			frame = 0;
		}
		//Update Direction, speed of pacMan determined by STEP
		switch (dir){
		case KeyEvent.VK_LEFT: //37
			xPos -= STEP;
			break;
		case KeyEvent.VK_UP: //38
			yPos -= STEP;
			break;
		case KeyEvent.VK_RIGHT: //39
			xPos += STEP;
			break;
		case KeyEvent.VK_DOWN: //40
			yPos += STEP;
			break;
		}
		
		
		//BOUND PACMAN TO THE WINDOW
		if( xPos< 0){
			xPos = 0;
		}
		else if (xPos > width-45-STEP){
			xPos = width-45-STEP;
		}
		if( yPos< 0){
			yPos = 0;
		}
		else if (yPos > height-65-STEP){
			yPos = height-65-STEP;
		}
	}

	public void draw(Graphics2D g) {
		// SELECT PACMAN's IMAGE, using Frame to select image
		g.drawImage(pacMan.getSubimage(frame*30, (dir-37)*30, 28, 28), xPos, yPos, null);
	}

}
