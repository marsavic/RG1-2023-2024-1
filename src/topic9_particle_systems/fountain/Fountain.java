package topic9_particle_systems.fountain;

import javafx.scene.paint.Color;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.geometry.Vector;
import mars.random.sampling.Sampling;
import topic9_particle_systems.particles.ParticleSystemInterval;


public class Fountain extends ParticleSystemInterval<Drop> {
	
	static double density = 1000.0;
	@GadgetDouble(min = 0, max = 0.3)
	static double sdV = 0.02;
	@GadgetDouble(min = 0, max = 20)
	static double rMin = 2.0;
	@GadgetDouble(min = 0, max = 20)
	static double rMax = 3.0;
	@GadgetDouble(min = 0, max = 360)
	static double hue = 210.0;
	@GadgetDouble(min = 0, max = 1)
	static double satMin = 0.4;
	@GadgetDouble(min = 0, max = 1)
	static double satMax = 0.6;
	@GadgetDouble(min = 0, max = 1)
	static double briMin = 0.8;
	@GadgetDouble(min = 0, max = 1)
	static double briMax = 0.9;
	@GadgetDouble(min = 0, max = 1)
	static double opacity = 0.1;

	
	private Vector p, v, a;
	private double yFloor;
	
	
	
	public Fountain(double timeStart, Vector p, Vector v, Vector a, double yFloor) {
		super(timeStart, 1 / density);
		this.p = p;
		this.v = v;
		this.a = a;
		this.yFloor = yFloor;
	}
	
	
	@Override
	protected Drop generateNext() {
		return new Drop(
				timeNext(),
				p,
				v.add(Vector.randomGaussian(v.norm() * sdV)),
				a,
				Sampling.uniform(rMin, rMax),
				yFloor,
				Color.hsb(
						hue,
						Sampling.uniform(satMin, satMax),
						Sampling.uniform(briMin, briMax),
						opacity
						)
			);
	}
}
