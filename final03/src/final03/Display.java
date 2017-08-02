package final03;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lejos.nxt.Sound;

public class Display implements ActionListener, ChangeListener{
	static Robot r1 = new Robot();
	static MoveSet m1 = new MoveSet();
	static MyPanel p1 = new MyPanel();
	static JFrame panel;
	static JPanel contentPane;	//holds all other JPanels
	static JPanel buttons;	//preprogrammed moves
	static JPanel sliders;	//speed and steering
	static JButton figure8;	//triggers figure8
	static JButton fireButton;	//fire from GUI
	static JButton horn;	//Play horn
	static JSlider speedSlide;	//Speed Slider
	static JSlider steerSlide;	//Steering Slider
	
	public void init(){
		
		panel = new JFrame("Rover Controller");
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// the box layout puts objects in a vertical column
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		contentPane.setBackground(Color.blue);
		
		// Add content pane to frame
		panel.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1,0));
		
		buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.PAGE_AXIS));
		buttons.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		buttons.setBackground(Color.WHITE);
		buttons.setLayout(new GridLayout(0,1));
		contentPane.add(buttons);
		
		sliders = new JPanel();
		sliders.setLayout(new BoxLayout(sliders,BoxLayout.PAGE_AXIS));
		sliders.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		sliders.setBackground(Color.GREEN);
		sliders.setLayout(new GridLayout(0,1));
		contentPane.add(sliders);
		
		contentPane.add(new MyPanel());	//add paint surface	(far right)
		
		// Size and then display the window.
		panel.setSize(800,500);
		panel.setLocation(300,100);
		panel.setVisible(true);
		
		//Buttons
		figure8 = new JButton("Figure Eight");
		buttons.add(figure8);
		figure8.addActionListener(this);
		
		fireButton = new JButton("Fire (trigger)");
		buttons.add(fireButton);
		fireButton.addActionListener(this);
		
		horn = new JButton("Horn (Button 3)");
		horn.addActionListener(this);
		buttons.add(horn);
		
		//Sliders
		speedSlide = new JSlider(JSlider.VERTICAL, -720,720, 0);
		speedSlide.addChangeListener(this);

		speedSlide.setMajorTickSpacing(180);
		speedSlide.setPaintTicks(true);
		speedSlide.setPaintLabels(true);
		speedSlide.setSnapToTicks(true);
		speedSlide.setName("Speed");
		sliders.add(speedSlide);
		
		steerSlide = new JSlider(JSlider.HORIZONTAL,-10, 10, 0);
		steerSlide.addChangeListener(this);

		steerSlide.setMajorTickSpacing(1);
		steerSlide.setPaintTicks(true);
		steerSlide.setPaintLabels(false);
		steerSlide.setSnapToTicks(true);
		sliders.add(steerSlide);
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==figure8)
		{
			m1.figure8();	//activate figure 8
		}
		if(arg0.getSource()==fireButton)	//fire button is pressed
		{
			r1.fire();
		}
		if(arg0.getSource() == horn)
		{
			Sound.playTone(500, 500);	//play tone from robot speaker
		}
	}
	public void stateChanged(ChangeEvent a) {
		if(a.getSource()==speedSlide)
		{
			r1.setSpeed(speedSlide.getValue()*-1);		//drive motor is reversed, value must be flipped
		}
		if(a.getSource()==steerSlide)
		{
			r1.turn(steerSlide.getValue());	//turn to the value of the steering slider
		}
	}

}
class MyPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	Timer timer1 = new Timer(10,this);
	public static int xpos=45,ypos=45;
	public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        timer1.start();
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //JStick graphics
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 1000);
        if(final03.JStickAvail==false)
        {
        	g.setColor(Color.BLUE);
        	g.drawString("No Joystick Found", 15,45);
        }else{
        	g.setColor(Color.WHITE);
        	g.drawLine(50,0,50,100);	//grid for JS position
        	g.drawLine(0,50,100,50);
        	g.setColor(Color.RED);
        	g.fillOval(xpos, ypos, 10, 10);	//circle for JS position
        	g.drawString("Joystick input", 15, 111);
        }
    }
    
	public void actionPerformed(ActionEvent arg0) {
		repaint();	//repaint on timer
	}
}