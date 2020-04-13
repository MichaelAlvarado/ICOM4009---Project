package Resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Description - This class loads all the game related Images
 * @author 
 * @date
 */
public class Images {

	private static BufferedImage starEffectSprite;
	public static BufferedImage[] starEffect;
	public static BufferedImage[] correct;
	public static BufferedImage[] CharacterSpriteRight;
	public static BufferedImage[] CharacterSpriteLeft;
	public static BufferedImage[] CharacterSpriteIdleRight;
	public static BufferedImage[] CharacterSpriteIdleLeft;
	public static BufferedImage[] incorrect;
	public static BufferedImage[] playAgain;



	public Images(int CharacterIndex) {
		
		CharacterSpriteRight = new BufferedImage[9];
		CharacterSpriteLeft = new BufferedImage[9];
		CharacterSpriteIdleRight = new BufferedImage[9];
		CharacterSpriteIdleLeft = new BufferedImage[9];
		starEffect = new BufferedImage[5];
		correct = new BufferedImage[5];
		incorrect = new BufferedImage[5];
		playAgain = new BufferedImage[5];
		
		try {
			switch(CharacterIndex) {
			//BOY SPRITE
			case 0:
				//Rights
				CharacterSpriteIdleRight[0] = ImageIO.read(new File("res/animation_Images/boy/Idle (1).png")); //IDLE
				CharacterSpriteIdleRight[1] = ImageIO.read(new File("res/animation_Images/boy/Idle (2).png")); //IDLE
				CharacterSpriteIdleRight[2] = ImageIO.read(new File("res/animation_Images/boy/Idle (3).png")); //IDLE
				CharacterSpriteIdleRight[3] = ImageIO.read(new File("res/animation_Images/boy/Idle (4).png")); //IDLE
				CharacterSpriteIdleRight[4] = ImageIO.read(new File("res/animation_Images/boy/Idle (5).png")); //IDLE
				CharacterSpriteIdleRight[5] = ImageIO.read(new File("res/animation_Images/boy/Idle (6).png")); //IDLE
				CharacterSpriteIdleRight[6] = ImageIO.read(new File("res/animation_Images/boy/Idle (7).png")); //IDLE
				CharacterSpriteIdleRight[7] = ImageIO.read(new File("res/animation_Images/boy/Idle (8).png")); //IDLE
				CharacterSpriteIdleRight[8] = ImageIO.read(new File("res/animation_Images/boy/Idle (9).png")); //IDLE
				
				CharacterSpriteRight[0] =  ImageIO.read(new File("res/animation_Images/boy/Walk (1).png"));
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/boy/Walk (2).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/boy/Walk (3).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/boy/Walk (4).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/boy/Walk (5).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/boy/Walk (6).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/boy/Walk (7).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/boy/Walk (8).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/boy/Walk (9).png"));

				//Lefts
				CharacterSpriteIdleLeft[0] = ImageIO.read(new File("res/animation_Images/boy/IdleLeft (1).png")); //IDLE
				CharacterSpriteIdleLeft[1] = ImageIO.read(new File("res/animation_Images/boy/IdleLeft (2).png")); //IDLE
				CharacterSpriteIdleLeft[2] = ImageIO.read(new File("res/animation_Images/boy/IdleLeft (3).png")); //IDLE
				CharacterSpriteIdleLeft[3] = ImageIO.read(new File("res/animation_Images/boy/IdleLeft (4).png")); //IDLE
				CharacterSpriteIdleLeft[4] = ImageIO.read(new File("res/animation_Images/boy/IdleLeft (5).png")); //IDLE
				CharacterSpriteIdleLeft[5] = ImageIO.read(new File("res/animation_Images/boy/IdleLeft (6).png")); //IDLE
				CharacterSpriteIdleLeft[6] = ImageIO.read(new File("res/animation_Images/boy/IdleLeft (7).png")); //IDLE
				CharacterSpriteIdleLeft[7] = ImageIO.read(new File("res/animation_Images/boy/IdleLeft (8).png")); //IDLE
				CharacterSpriteIdleLeft[8] = ImageIO.read(new File("res/animation_Images/boy/IdleLeft (9).png")); //IDLE
				
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (1).png"));
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (2).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (3).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (4).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (5).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (6).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (7).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (8).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (9).png"));
				break;
			//GIRL SPRITE	
			case 1:
				//Rights
				CharacterSpriteIdleRight[0] = ImageIO.read(new File("res/animation_Images/girl/Idle (1).png")); //IDLE
				CharacterSpriteIdleRight[1] = ImageIO.read(new File("res/animation_Images/girl/Idle (2).png")); //IDLE
				CharacterSpriteIdleRight[2] = ImageIO.read(new File("res/animation_Images/girl/Idle (3).png")); //IDLE
				CharacterSpriteIdleRight[3] = ImageIO.read(new File("res/animation_Images/girl/Idle (4).png")); //IDLE
				CharacterSpriteIdleRight[4] = ImageIO.read(new File("res/animation_Images/girl/Idle (5).png")); //IDLE
				CharacterSpriteIdleRight[5] = ImageIO.read(new File("res/animation_Images/girl/Idle (6).png")); //IDLE
				CharacterSpriteIdleRight[6] = ImageIO.read(new File("res/animation_Images/girl/Idle (7).png")); //IDLE
				CharacterSpriteIdleRight[7] = ImageIO.read(new File("res/animation_Images/girl/Idle (8).png")); //IDLE
				CharacterSpriteIdleRight[8] = ImageIO.read(new File("res/animation_Images/girl/Idle (9).png")); //IDLE
				
				CharacterSpriteRight[0] =  ImageIO.read(new File("res/animation_Images/girl/Walk (1).png"));
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/girl/Walk (2).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/girl/Walk (3).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/girl/Walk (4).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/girl/Walk (5).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/girl/Walk (6).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/girl/Walk (7).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/girl/Walk (8).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/girl/Walk (9).png"));

				//Lefts
				CharacterSpriteIdleLeft[0] =  ImageIO.read(new File("res/animation_Images/girl/IdleLeft (1).png"));
				CharacterSpriteIdleLeft[1] =  ImageIO.read(new File("res/animation_Images/girl/IdleLeft (2).png"));
				CharacterSpriteIdleLeft[2] =  ImageIO.read(new File("res/animation_Images/girl/IdleLeft (3).png"));
				CharacterSpriteIdleLeft[3] =  ImageIO.read(new File("res/animation_Images/girl/IdleLeft (4).png"));
				CharacterSpriteIdleLeft[4] =  ImageIO.read(new File("res/animation_Images/girl/IdleLeft (5).png"));
				CharacterSpriteIdleLeft[5] =  ImageIO.read(new File("res/animation_Images/girl/IdleLeft (6).png"));
				CharacterSpriteIdleLeft[6] =  ImageIO.read(new File("res/animation_Images/girl/IdleLeft (7).png"));
				CharacterSpriteIdleLeft[7] =  ImageIO.read(new File("res/animation_Images/girl/IdleLeft (8).png"));
				CharacterSpriteIdleLeft[8] =  ImageIO.read(new File("res/animation_Images/girl/IdleLeft (9).png"));
				
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (1).png"));
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (2).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (3).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (4).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (5).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (6).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (7).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (8).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (9).png"));
				break;
			//ADVENTURER BOY SPRITE	
			case 2:
				//Rights
				CharacterSpriteIdleRight[0] = ImageIO.read(new File("res/animation_Images/a_boy/Idle (1).png")); //IDLE
				CharacterSpriteIdleRight[1] = ImageIO.read(new File("res/animation_Images/a_boy/Idle (2).png")); //IDLE
				CharacterSpriteIdleRight[2] = ImageIO.read(new File("res/animation_Images/a_boy/Idle (3).png")); //IDLE
				CharacterSpriteIdleRight[3] = ImageIO.read(new File("res/animation_Images/a_boy/Idle (4).png")); //IDLE
				CharacterSpriteIdleRight[4] = ImageIO.read(new File("res/animation_Images/a_boy/Idle (5).png")); //IDLE
				CharacterSpriteIdleRight[5] = ImageIO.read(new File("res/animation_Images/a_boy/Idle (6).png")); //IDLE
				CharacterSpriteIdleRight[6] = ImageIO.read(new File("res/animation_Images/a_boy/Idle (7).png")); //IDLE
				CharacterSpriteIdleRight[7] = ImageIO.read(new File("res/animation_Images/a_boy/Idle (8).png")); //IDLE
				CharacterSpriteIdleRight[8] = ImageIO.read(new File("res/animation_Images/a_boy/Idle (9).png")); //IDLE
				
				CharacterSpriteRight[0] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (1).png"));
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (2).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (3).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (4).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (5).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (6).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (7).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (8).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (9).png"));

				//Lefts
				CharacterSpriteIdleLeft[0] = ImageIO.read(new File("res/animation_Images/a_boy/IdleLeft (1).png")); //IDLE
				CharacterSpriteIdleLeft[1] = ImageIO.read(new File("res/animation_Images/a_boy/IdleLeft (2).png")); //IDLE
				CharacterSpriteIdleLeft[2] = ImageIO.read(new File("res/animation_Images/a_boy/IdleLeft (3).png")); //IDLE
				CharacterSpriteIdleLeft[3] = ImageIO.read(new File("res/animation_Images/a_boy/IdleLeft (4).png")); //IDLE
				CharacterSpriteIdleLeft[4] = ImageIO.read(new File("res/animation_Images/a_boy/IdleLeft (5).png")); //IDLE
				CharacterSpriteIdleLeft[5] = ImageIO.read(new File("res/animation_Images/a_boy/IdleLeft (6).png")); //IDLE
				CharacterSpriteIdleLeft[6] = ImageIO.read(new File("res/animation_Images/a_boy/IdleLeft (7).png")); //IDLE
				CharacterSpriteIdleLeft[7] = ImageIO.read(new File("res/animation_Images/a_boy/IdleLeft (8).png")); //IDLE
				CharacterSpriteIdleLeft[8] = ImageIO.read(new File("res/animation_Images/a_boy/IdleLeft (9).png")); //IDLE
				
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (1).png"));
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (2).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (3).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (4).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (5).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (6).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (7).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (8).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (9).png"));
				break;
			//ADVENTURER GIRL SPRITE	
			case 3:
				//Rights
				CharacterSpriteIdleRight[0] = ImageIO.read(new File("res/animation_Images/a_girl/Idle (1).png")); //IDLE
				CharacterSpriteIdleRight[1] = ImageIO.read(new File("res/animation_Images/a_girl/Idle (2).png")); //IDLE
				CharacterSpriteIdleRight[2] = ImageIO.read(new File("res/animation_Images/a_girl/Idle (3).png")); //IDLE
				CharacterSpriteIdleRight[3] = ImageIO.read(new File("res/animation_Images/a_girl/Idle (4).png")); //IDLE
				CharacterSpriteIdleRight[4] = ImageIO.read(new File("res/animation_Images/a_girl/Idle (5).png")); //IDLE
				CharacterSpriteIdleRight[5] = ImageIO.read(new File("res/animation_Images/a_girl/Idle (6).png")); //IDLE
				CharacterSpriteIdleRight[6] = ImageIO.read(new File("res/animation_Images/a_girl/Idle (7).png")); //IDLE
				CharacterSpriteIdleRight[7] = ImageIO.read(new File("res/animation_Images/a_girl/Idle (8).png")); //IDLE
				CharacterSpriteIdleRight[8] = ImageIO.read(new File("res/animation_Images/a_girl/Idle (9).png")); //IDLE
				
				CharacterSpriteRight[0] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (1).png"));
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (2).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (3).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (4).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (5).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (6).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (7).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (8).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (9).png"));

				//Lefts
				CharacterSpriteIdleLeft[0] = ImageIO.read(new File("res/animation_Images/a_girl/IdleLeft (1).png")); //IDLE
				CharacterSpriteIdleLeft[1] = ImageIO.read(new File("res/animation_Images/a_girl/IdleLeft (2).png")); //IDLE
				CharacterSpriteIdleLeft[2] = ImageIO.read(new File("res/animation_Images/a_girl/IdleLeft (3).png")); //IDLE
				CharacterSpriteIdleLeft[3] = ImageIO.read(new File("res/animation_Images/a_girl/IdleLeft (4).png")); //IDLE
				CharacterSpriteIdleLeft[4] = ImageIO.read(new File("res/animation_Images/a_girl/IdleLeft (5).png")); //IDLE
				CharacterSpriteIdleLeft[5] = ImageIO.read(new File("res/animation_Images/a_girl/IdleLeft (6).png")); //IDLE
				CharacterSpriteIdleLeft[6] = ImageIO.read(new File("res/animation_Images/a_girl/IdleLeft (7).png")); //IDLE
				CharacterSpriteIdleLeft[7] = ImageIO.read(new File("res/animation_Images/a_girl/IdleLeft (8).png")); //IDLE
				CharacterSpriteIdleLeft[8] = ImageIO.read(new File("res/animation_Images/a_girl/IdleLeft (9).png")); //IDLE
				
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (1).png"));
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (2).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (3).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (4).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (5).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (6).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (7).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (8).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (9).png"));
				break;
			//CAT SPRITE	
			case 4:
				//Rights
				CharacterSpriteIdleRight[0] = ImageIO.read(new File("res/animation_Images/cat/Idle (1).png")); //IDLE
				CharacterSpriteIdleRight[1] = ImageIO.read(new File("res/animation_Images/cat/Idle (2).png")); //IDLE
				CharacterSpriteIdleRight[2] = ImageIO.read(new File("res/animation_Images/cat/Idle (3).png")); //IDLE
				CharacterSpriteIdleRight[3] = ImageIO.read(new File("res/animation_Images/cat/Idle (4).png")); //IDLE
				CharacterSpriteIdleRight[4] = ImageIO.read(new File("res/animation_Images/cat/Idle (5).png")); //IDLE
				CharacterSpriteIdleRight[5] = ImageIO.read(new File("res/animation_Images/cat/Idle (6).png")); //IDLE
				CharacterSpriteIdleRight[6] = ImageIO.read(new File("res/animation_Images/cat/Idle (7).png")); //IDLE
				CharacterSpriteIdleRight[7] = ImageIO.read(new File("res/animation_Images/cat/Idle (8).png")); //IDLE
				CharacterSpriteIdleRight[8] = ImageIO.read(new File("res/animation_Images/cat/Idle (9).png")); //IDLE
				
				CharacterSpriteRight[0] =  ImageIO.read(new File("res/animation_Images/cat/Walk (1).png"));
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/cat/Walk (2).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/cat/Walk (3).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/cat/Walk (4).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/cat/Walk (5).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/cat/Walk (6).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/cat/Walk (7).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/cat/Walk (8).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/cat/Walk (9).png"));

				//Lefts
				CharacterSpriteIdleLeft[0] = ImageIO.read(new File("res/animation_Images/cat/IdleLeft (1).png")); //IDLE
				CharacterSpriteIdleLeft[1] = ImageIO.read(new File("res/animation_Images/cat/IdleLeft (2).png")); //IDLE
				CharacterSpriteIdleLeft[2] = ImageIO.read(new File("res/animation_Images/cat/IdleLeft (3).png")); //IDLE
				CharacterSpriteIdleLeft[3] = ImageIO.read(new File("res/animation_Images/cat/IdleLeft (4).png")); //IDLE
				CharacterSpriteIdleLeft[4] = ImageIO.read(new File("res/animation_Images/cat/IdleLeft (5).png")); //IDLE
				CharacterSpriteIdleLeft[5] = ImageIO.read(new File("res/animation_Images/cat/IdleLeft (6).png")); //IDLE
				CharacterSpriteIdleLeft[6] = ImageIO.read(new File("res/animation_Images/cat/IdleLeft (7).png")); //IDLE
				CharacterSpriteIdleLeft[7] = ImageIO.read(new File("res/animation_Images/cat/IdleLeft (8).png")); //IDLE
				CharacterSpriteIdleLeft[8] = ImageIO.read(new File("res/animation_Images/cat/IdleLeft (9).png")); //IDLE
				
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (1).png"));
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (2).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (3).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (4).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (5).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (6).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (7).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (8).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (9).png"));
				break;
			//DOG SPRITE	
			case 5:
				//Rights
				CharacterSpriteIdleRight[0] = ImageIO.read(new File("res/animation_Images/dog/Idle (1).png")); //IDLE
				CharacterSpriteIdleRight[1] = ImageIO.read(new File("res/animation_Images/dog/Idle (2).png")); //IDLE
				CharacterSpriteIdleRight[2] = ImageIO.read(new File("res/animation_Images/dog/Idle (3).png")); //IDLE
				CharacterSpriteIdleRight[3] = ImageIO.read(new File("res/animation_Images/dog/Idle (4).png")); //IDLE
				CharacterSpriteIdleRight[4] = ImageIO.read(new File("res/animation_Images/dog/Idle (5).png")); //IDLE
				CharacterSpriteIdleRight[5] = ImageIO.read(new File("res/animation_Images/dog/Idle (6).png")); //IDLE
				CharacterSpriteIdleRight[6] = ImageIO.read(new File("res/animation_Images/dog/Idle (7).png")); //IDLE
				CharacterSpriteIdleRight[7] = ImageIO.read(new File("res/animation_Images/dog/Idle (8).png")); //IDLE
				CharacterSpriteIdleRight[8] = ImageIO.read(new File("res/animation_Images/dog/Idle (9).png")); //IDLE
				
				CharacterSpriteRight[0] =  ImageIO.read(new File("res/animation_Images/dog/Walk (1).png"));
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/dog/Walk (2).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/dog/Walk (3).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/dog/Walk (4).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/dog/Walk (5).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/dog/Walk (6).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/dog/Walk (7).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/dog/Walk (8).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/dog/Walk (9).png"));

				//Lefts
				CharacterSpriteIdleLeft[0] = ImageIO.read(new File("res/animation_Images/dog/IdleLeft (1).png")); //IDLE
				CharacterSpriteIdleLeft[1] = ImageIO.read(new File("res/animation_Images/dog/IdleLeft (2).png")); //IDLE
				CharacterSpriteIdleLeft[2] = ImageIO.read(new File("res/animation_Images/dog/IdleLeft (3).png")); //IDLE
				CharacterSpriteIdleLeft[3] = ImageIO.read(new File("res/animation_Images/dog/IdleLeft (4).png")); //IDLE
				CharacterSpriteIdleLeft[4] = ImageIO.read(new File("res/animation_Images/dog/IdleLeft (5).png")); //IDLE
				CharacterSpriteIdleLeft[5] = ImageIO.read(new File("res/animation_Images/dog/IdleLeft (6).png")); //IDLE
				CharacterSpriteIdleLeft[6] = ImageIO.read(new File("res/animation_Images/dog/IdleLeft (7).png")); //IDLE
				CharacterSpriteIdleLeft[7] = ImageIO.read(new File("res/animation_Images/dog/IdleLeft (8).png")); //IDLE
				CharacterSpriteIdleLeft[8] = ImageIO.read(new File("res/animation_Images/dog/IdleLeft (9).png")); //IDLE
				
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (1).png"));
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (2).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (3).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (4).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (5).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (6).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (7).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (8).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (9).png"));
				break;
			} 
			starEffectSprite = ImageIO.read(new File("res/effects/star effect.png"));
			starEffect[0] = starEffectSprite.getSubimage(8, 500, 30, 45);
			starEffect[1] = starEffectSprite.getSubimage(108, 500, 30, 45);
			starEffect[2] = starEffectSprite.getSubimage(210, 500, 30, 45);
			starEffect[3] = starEffectSprite.getSubimage(307, 500, 33, 45);
			starEffect[4] = starEffectSprite.getSubimage(405, 500, 36, 45);
			
			correct[0]= ImageIO.read(new File("res/effects/correct1.png"));
			correct[1]= ImageIO.read(new File("res/effects/correct2.png"));
			correct[2]= ImageIO.read(new File("res/effects/correct3.png"));
			correct[3]= ImageIO.read(new File("res/effects/correct4.png"));
			correct[4]= ImageIO.read(new File("res/effects/correct5.png"));
			
			incorrect[0] = ImageIO.read(new File("res/effects/incorrect.png"));
			incorrect[1] = ImageIO.read(new File("res/effects/incorrect.png"));
			incorrect[2] = ImageIO.read(new File("res/effects/incorrect.png"));
			incorrect[3] = ImageIO.read(new File("res/effects/incorrect.png"));
			incorrect[4] = ImageIO.read(new File("res/effects/incorrect.png"));
			
			playAgain[0] = ImageIO.read(new File("res/effects/playagain.png"));
			playAgain[1] = ImageIO.read(new File("res/effects/playagain.png"));
			playAgain[2] = ImageIO.read(new File("res/effects/playagain.png"));
			playAgain[3] = ImageIO.read(new File("res/effects/playagain.png"));
			playAgain[4] = ImageIO.read(new File("res/effects/playagain.png"));


		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
