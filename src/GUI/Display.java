package GUI;

import java.awt.*;
import javax.swing.*;


public class Display {

    private JFrame frame; //Display itself
    private Canvas canvas; //To render on
    private String title;
    private int width, height;

    public Display(String title, int width, int height){
    	//Set the title and size of the frame
    	this.title = title;
        this.width = width;
        this.height = height;
        
        createDisplay();
    }

    private void createDisplay(){
    	//Create a frame of display itself
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBackground(Color.black);

        //Create a canvas to render on the display
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        canvas.setBackground(Color.black);
        
        //add the canvas to the frame 
        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }

}