package final03;

import javax.swing.JOptionPane;
import lejos.nxt.Sound;
import com.centralnexus.input.*;

public class final03 implements JoystickListener{
	Joystick j1;
	Robot r1 = new Robot();
	MyPanel p1 = new MyPanel();
	static Display d1 = new Display();
	MoveSet m1 = new MoveSet();
	static int angle;
	static int speed;
	public static boolean JStickAvail = true;
	public final03(){
		d1.init();
		r1.init();
		try{ 
			 j1 = Joystick.createInstance();	//get first avail JStick
			 j1.addJoystickListener(this);
			 j1.setPollInterval(200);
			 j1.setDeadZone(0.15);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"No Joystick Found.");
			JStickAvail = false;
		}
		
		
	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		final03 g1 = new final03();
	}

	public void joystickAxisChanged(Joystick e) {
		
		if(angle!=(int)(e.getX()*10)&&m1.f8==false)
		{
			angle = (int)(e.getX()*10);
			r1.turn(angle);
			System.out.println(e.getX()*30);
			MyPanel.xpos=(int) (e.getX()*30)+45;
		}
		if(speed!=(int)(e.getY()*720)&&m1.f8==false)
		{
			speed = (int)(e.getY()*900);	//degrees per second
			r1.setSpeed(speed);
			MyPanel.ypos=(int) (e.getY()*30)+45;
		}
	}

	public void joystickButtonChanged(Joystick j) {
		if(j.isButtonDown(1))	//trigger
		{
			r1.fire();	//fire
		}
		if(j.isButtonDown(6))	//top middle button
		{
			Sound.playTone(500, 500);	//play horn
			System.out.println("beep");
		}
	}

}