package shoot_em_up.full;

import mars.drawingx.drawing.View;
import mars.geometry.Vector;


public class Projectile extends Body {
	
	private ProjectileParticleSystem ps;
	private boolean finished = false;
	
	
	public Projectile(Ship ship, double time) {
		super(
				time,
				ship.positionAt(time).add(new Vector(0, ship.r)),
				new Vector(0, 400),
				2
		);
		
		ps = new ProjectileParticleSystem(time, this);
	}

	
	public void finish() {
		if (!finished) {
			finished = true;
			ps.stop();
		}
	}
	
	
	public boolean isFinished() {
		return finished;
	}
	
	
	public boolean shouldDraw(double time) {
		return ps.isAlive();
	}
	
	
	@Override
	public void draw(View view, double time) {
		ps.draw(view, time);
	}
	
}
