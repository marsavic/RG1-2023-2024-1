package topic9_particle_systems.rain;

import javafx.scene.paint.Color;
import mars.drawingx.drawing.View;
import mars.geometry.Vector;
import mars.utils.Graphics;
import topic9_particle_systems.particles.Particle;


public class Raindrop extends Particle {
	private double t0, td0, td1;
	private Vector p0, v0, v1;
	private double size;
	private Color color;
	
	
	public Raindrop(double t0, double td0, double td1, Vector p0, Vector v0, Vector v1, double size, Color color) {
		this.t0 = t0;
		this.td0 = td0;
		this.td1 = td1;
		this.p0 = p0;
		this.v0 = v0;
		this.v1 = v1;
		this.size = size;
		this.color = color;
	}


	@Override
	public void draw(View view, double time) {
		if (time < t0) {
			return;
		}
		
		if (time < t0 + td0) {
			double t = time - t0;
			double k = t / td0;
			
			view.setFill(color);
			view.fillCircleCentered(p0.add(v0.mul(t)), k * size);
		} else {
			double t = time - t0 - td0;
			double k = t / td1;
			view.setFill(Graphics.scaleOpacity(color, (1-k) / 8));
			Vector a = v1.inverse().div(td1);
			Vector p = p0.add(v0.mul(td0));
			p = p.add(v1.mul(t)).add(a.mul(t*t/2));
			view.fillCircleCentered(p, 4*(1-k) * size);
			view.fillCircleCentered(p, 3*(1+k) * size);
		}
	}
	

	@Override
	public boolean isAlive(double time) {
		return time - t0 < + td0 + td1;
	}
	
}
