package topic9_particle_systems.rain;

import javafx.scene.paint.Color;
import mars.geometry.Vector;
import mars.random.sampling.Sampling;
import topic9_particle_systems.particles.ParticleSystemInterval;


public class Rain extends ParticleSystemInterval<Raindrop> {
	public static Vector v0 = new Vector(100, -400);
	public static Vector v1 = new Vector(  0,  -40);


	private Vector boundsP, boundsD;

	
	
	public Rain(double timeStart, double intensity, Vector boundsP, Vector boundsD) {
		super(timeStart, 1 / (intensity * boundsD.area()));
		this.boundsP = boundsP;
		this.boundsD = boundsD;
	}


	@Override
	protected Raindrop generateNext() {
		double r = Sampling.uniform(2, 4);
		return new Raindrop(
				timeNext(),
				Sampling.exponential(1),
				r*r / 9,
				Vector.randomInBox(boundsP, boundsD),
				v0.mul(r / 3),
				v1.mul(r / 3),
				r,
				Color.hsb(
						210,
						0.5,
						Sampling.uniform(0.4, 1.0),
						0.5
				)
		);
	}

}
