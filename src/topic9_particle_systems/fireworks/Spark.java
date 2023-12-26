package topic9_particle_systems.fireworks;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;
import topic9_particle_systems.particles.Particle;


public class Spark extends Particle {
	double t0, tD;
	private Vector p, v, a;
	double hue;
	double r;
	

	
	public Spark(double t0, double tD, Vector p, Vector v, Vector a, double hue, double r) {
		this.t0 = t0;
		this.tD = tD;
		this.p = p;
		this.v = v;
		this.a = a;
		this.hue = hue;
		this.r = r;
	}


	@Override
	public void draw(View view, double time) {
		double t = time - t0;
		if (t < 0) {
			return;
		}
		
		double k = t / tD;
		view.setFill(Color.hsb(
				hue,
				0.1 + 0.5 * (1 - Math.pow(k, 8)),
				0.5 + 0.5 * k,
				1.0
			));
		Vector q = p.add(v.mul(t)).add(a.mul(t*t/2));
		view.fillCircleCentered(q, r * Math.pow(k, 2));
	}
	

	@Override
	public boolean isAlive(double time) {
		return time - t0 < tD;
	}
	
}
