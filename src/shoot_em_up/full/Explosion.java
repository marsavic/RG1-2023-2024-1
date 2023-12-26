package shoot_em_up.full;

import mars.geometry.Vector;
import mars.random.sampling.Sampling;
import topic9_particle_systems.particles.ParticleSystemInstant;


/**
 * Eksplozija koja nastaje prilikom udara projektila u protivnika.
 */
public class Explosion extends ParticleSystemInstant<ExplosionParticle> {
	Projectile projectile;
	Enemy enemy;
	
	
	
	public Explosion(double t, Projectile projectile, Enemy enemy) {
		super(t, 300);
		this.projectile = projectile;
		this.enemy = enemy;
	}
	
	
	@Override
	protected ExplosionParticle generateNext() {
		double t = timeNext();
		
		return new ExplosionParticle(
				t,
				Math.max(0, 0.4 * (0.7 + Sampling.exponential(0.3))),
				enemy.positionAt(t).add(Vector.randomInDisk(enemy.r)),
				enemy.velocityAt(t).add(Vector.randomOnCircle(150).mul(Sampling.gaussian(1, 0.3)))
		);
	}
	
}
