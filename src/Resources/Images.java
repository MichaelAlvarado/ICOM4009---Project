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
		CharacterSpriteRight = new BufferedImage[5];
		CharacterSpriteLeft = new BufferedImage[5];

		try {
			switch(CharacterIndex) {
			case 0:
				//Rights
				CharacterSpriteRight[0] = ImageIO.read(new File("res/animation_Images/Idle (1).png")); //IDLE
				CharacterSpriteRight[1] =  ImageIO.read(new File("res/animation_Images/Walk (1).png"));
				CharacterSpriteRight[2] =  ImageIO.read(new File("res/animation_Images/Walk (3).png"));
				CharacterSpriteRight[3] =  ImageIO.read(new File("res/animation_Images/Walk (5).png"));
				CharacterSpriteRight[4] =  ImageIO.read(new File("res/animation_Images/Walk (7).png"));
				//Lefts
				//CharacterSpriteLeft[0] = ImageIO.read(new File("res/animation_Images/Idle (1).png")); //IDLE
				CharacterSpriteLeft[0] =  ImageIO.read(new File("res/animation_Images/WalkLeft (1).png")); //Testing please delete
				CharacterSpriteLeft[1] =  ImageIO.read(new File("res/animation_Images/WalkLeft (1).png"));
				CharacterSpriteLeft[2] =  ImageIO.read(new File("res/animation_Images/WalkLeft (3).png"));
				CharacterSpriteLeft[3] =  ImageIO.read(new File("res/animation_Images/WalkLeft (5).png"));
				CharacterSpriteLeft[4] =  ImageIO.read(new File("res/animation_Images/WalkLeft (7).png"));
			} 
				
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
