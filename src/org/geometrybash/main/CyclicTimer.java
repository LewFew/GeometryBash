package org.geometrybash.main;

public class CyclicTimer extends Timer{

	public CyclicTimer(Timable caller, int timeTo, int id) {
		super(caller, timeTo, id);
	}
	
	public void stop() {
		finished = true;
	}
	
	@Override
	public void update() {
		time++;
		
		if (time >= timeTo) {
			caller.timeAction(id);
			time = 0;
		}
	}

}
