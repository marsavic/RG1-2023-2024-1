package shoot_em_up.full;

import mars.geometry.Vector;
import mars.random.sampling.Sampling;
import topic9_particle_systems.particles.ParticleSystemInterval;


public class ProjectileParticleSystem extends ParticleSystemInterval<ProjectileParticle> {
	public static double INTERVAL_PARTICLE_PROJECTILE = 0.002;

	Projectile projectile;
	
	
	
	public ProjectileParticleSystem(double t, Projectile projectile) {
		super(t, INTERVAL_PARTICLE_PROJECTILE);
		this.projectile = projectile;
	}
	
	
	@Override
	protected ProjectileParticle generateNext() {
		double time = timeNext();
		Vector p = projectile.positionAt(time);
		Vector v = projectile.velocityAt(time);
		
		return new ProjectileParticle(
				time,
				0.4 * (0.7 + Sampling.exponential(0.3)),
				p,
				v.add(Vector.randomGaussian(20)).mul(0.4 + 0.5 * Sampling.uniform())
		);
	}
	
}
