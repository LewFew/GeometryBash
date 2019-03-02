package org.geometrybash.main;

public class Timer {
	
	protected int id;
	protected int time;
	protected int timeTo;
	protected boolean finished = false;
	
	protected Timable caller;
	
	public Timer(Timable caller, int timeTo, int id) {
		this.caller = caller;
		this.timeTo = timeTo;
		this.id = id;
	}
	
	public int getTime() {
		return time;
	}
	
	public int timeRemaining() {
		return timeTo - time;
	}
	
	public void update() {
		time++;
		
		if (time >= timeTo) {
			finished = true;
			caller.timeAction(id);
		}
	}
	
	public boolean isFinished() {
		return finished;
	}

}
