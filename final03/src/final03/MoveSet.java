package final03;

public class MoveSet {
	Robot r1 = new Robot();
	public boolean f8 = false;
	public void figure8(){
		f8=true;	//is figure 8 occurring?
		try{
			r1.setSpeed(-720);	//first straight away
			Thread.sleep(1500);
			r1.turn(-12);	//turn all the way to the left
			Thread.sleep(6000);
			r1.turn(0);	//straighten out
			Thread.sleep(1000);	//straight away
			r1.turn(12);	//turn right
			Thread.sleep(6000);
			r1.turn(0);	//Straighten out again
			r1.setSpeed(0);	//stop motion
		}catch(Exception e)
		{
			System.out.println(e);
		}
		f8=false;	//is figure 8 occurring?
	}

}