package final03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import lejos.nxt.*;
public class Robot implements ActionListener {
	TouchSensor t1 = new TouchSensor(SensorPort.S1);
	Timer timer1 = new Timer(200,this);
	static int speed1, steer1;
	//final03 f1 = new final03();
	public void init()
	{
		timer1.start();
	}
	public void turn(int angle){	//steers robot
		steer1 = angle;
		try{
		Motor.C.setPower(10);
		Motor.C.rotateTo(((int)(angle*4)),true);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		if(!(Motor.A.getLimitAngle()>angle-2)&&!(Motor.A.getLimitAngle()<angle+2))
		{
			turn(angle);
		}
	}
	public void setSpeed(int speed){
		speed1=speed;
		Motor.B.setPower(900);
		if(speed<0)	//negative speed means backwards
		{
			Motor.B.setSpeed(speed*-1);
			Motor.B.backward();
		}else{
			Motor.B.setSpeed(speed);
			Motor.B.forward();
		}	
	}
	public void fire()
	{
		try{
			if(!Motor.A.isMoving())	//avoid incomplete cycles
			{
				Motor.A.rotate(360);	//Rotate Firing motor 360
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		if(t1.isPressed())	//touch is pressed, play tone, update label
		{
			Sound.playTone(500, 500);
		}		
	}
}