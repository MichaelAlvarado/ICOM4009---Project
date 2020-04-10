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

	public static BufferedImage[] CharacterSpriteRight;
	public static BufferedImage[] CharacterSpriteLeft;


	public Images(int CharacterIndex) {
		CharacterSpriteRight = new BufferedImage[10];
		CharacterSpriteLeft = new BufferedImage[10];

		try {
			switch(CharacterIndex) {
			case 0:
				//Rights
				CharacterSpriteRight[0] = ImageIO.read(new File("res/animation_Images/boy/Idle (1).png")); //IDLE
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/boy/Walk (1).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/boy/Walk (2).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/boy/Walk (3).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/boy/Walk (4).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/boy/Walk (5).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/boy/Walk (6).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/boy/Walk (7).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/boy/Walk (8).png"));
				CharacterSpriteRight[9] =  ImageIO.read(new File("res/animation_Images/boy/Walk (9).png"));

				//Lefts
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/boy/IdleLeft (1).png")); //IDLE
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (1).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (2).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (3).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (4).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (5).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (6).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (7).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (8).png"));
				CharacterSpriteLeft[9] =  ImageIO.read(new File("res/animation_Images/boy/WalkLeft (9).png"));
				break;
			case 1:
				//Rights
				CharacterSpriteRight[0] = ImageIO.read(new File("res/animation_Images/girl/Idle (1).png")); //IDLE
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/girl/Walk (1).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/girl/Walk (2).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/girl/Walk (3).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/girl/Walk (4).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/girl/Walk (5).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/girl/Walk (6).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/girl/Walk (7).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/girl/Walk (8).png"));
				CharacterSpriteRight[9] =  ImageIO.read(new File("res/animation_Images/girl/Walk (9).png"));

				//Lefts
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/girl/IdleLeft (1).png")); //IDLE
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (1).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (2).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (3).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (4).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (5).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (6).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (7).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (8).png"));
				CharacterSpriteLeft[9] =  ImageIO.read(new File("res/animation_Images/girl/WalkLeft (9).png"));
				break;
			case 2:
				//Rights
				CharacterSpriteRight[0] = ImageIO.read(new File("res/animation_Images/a_boy/Idle (1).png")); //IDLE
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (1).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (2).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (3).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (4).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (5).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (6).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (7).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (8).png"));
				CharacterSpriteRight[9] =  ImageIO.read(new File("res/animation_Images/a_boy/Walk (9).png"));

				//Lefts
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/a_boy/IdleLeft (1).png")); //IDLE
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (1).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (2).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (3).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (4).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (5).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (6).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (7).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (8).png"));
				CharacterSpriteLeft[9] =  ImageIO.read(new File("res/animation_Images/a_boy/WalkLeft (9).png"));
				break;
			case 3:
				//Rights
				CharacterSpriteRight[0] = ImageIO.read(new File("res/animation_Images/a_girl/Idle (1).png")); //IDLE
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (1).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (2).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (3).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (4).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (5).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (6).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (7).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (8).png"));
				CharacterSpriteRight[9] =  ImageIO.read(new File("res/animation_Images/a_girl/Walk (9).png"));

				//Lefts
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/a_girlIdleLeft (1).png")); //IDLE
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (1).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (2).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (3).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (4).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (5).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (6).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (7).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (8).png"));
				CharacterSpriteLeft[9] =  ImageIO.read(new File("res/animation_Images/a_girl/WalkLeft (9).png"));
				break;
			case 4:
				//Rights
				CharacterSpriteRight[0] = ImageIO.read(new File("res/animation_Images/cat/Idle (1).png")); //IDLE
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/cat/Walk (1).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/cat/Walk (2).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/cat/Walk (3).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/cat/Walk (4).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/cat/Walk (5).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/cat/Walk (6).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/cat/Walk (7).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/cat/Walk (8).png"));
				CharacterSpriteRight[9] =  ImageIO.read(new File("res/animation_Images/cat/Walk (9).png"));

				//Lefts
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/cat/IdleLeft (1).png")); //IDLE
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (1).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (2).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (3).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (4).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (5).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (6).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (7).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (8).png"));
				CharacterSpriteLeft[9] =  ImageIO.read(new File("res/animation_Images/cat/WalkLeft (9).png"));
				break;
			case 5:
				//Rights
				CharacterSpriteRight[0] = ImageIO.read(new File("res/animation_Images/dog/Idle (1).png")); //IDLE
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/dog/Walk (1).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/dog/Walk (2).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/dog/Walk (3).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/dog/Walk (4).png"));
				CharacterSpriteRight[5] =  ImageIO.read(new File("res/animation_Images/dog/Walk (5).png"));
				CharacterSpriteRight[6] =  ImageIO.read(new File("res/animation_Images/dog/Walk (6).png"));
				CharacterSpriteRight[7] =  ImageIO.read(new File("res/animation_Images/dog/Walk (7).png"));
				CharacterSpriteRight[8] =  ImageIO.read(new File("res/animation_Images/dog/Walk (8).png"));
				CharacterSpriteRight[9] =  ImageIO.read(new File("res/animation_Images/dog/Walk (9).png"));

				//Lefts
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/dog/IdleLeft (1).png")); //IDLE
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (1).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (2).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (3).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (4).png"));
				CharacterSpriteLeft[5] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (5).png"));
				CharacterSpriteLeft[6] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (6).png"));
				CharacterSpriteLeft[7] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (7).png"));
				CharacterSpriteLeft[8] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (8).png"));
				CharacterSpriteLeft[9] =  ImageIO.read(new File("res/animation_Images/dog/WalkLeft (9).png"));
				break;
			} 
				
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
