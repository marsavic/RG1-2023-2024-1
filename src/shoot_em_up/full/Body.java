package shoot_em_up.full;

import mars.drawingx.drawing.View;
import mars.geometry.Vector;


/**
 * Svako telo je kruznog oblika i krece se ravnomerno pravolinijski izmedju svake dve promene brzine.
 */
public abstract class Body {
	/** Trenutak u kome je zabelezeno poslednje stanje (polozaj i brzina). */
	private double t;

	/** Polozaj tela. */
	private Vector p;
	
	/** Brzina tela. */
	private Vector v;
	
	/** Poluprecnik tela koji se koristi za proveru sudara. */
	double r;
	
	
	
	public Body(double t, Vector p, Vector v, double r) {
		this.t = t;
		this.p = p;
		this.v = v;
		this.r = r;
	}


	public abstract void draw(View view, double time);
	
	
	
	private void update(double time) {
		p = positionAt(time);
		t = time;
	}

	
	public void setSpeed(Vector v, double time) {
		update(time);
		this.v = v;
	}
	
	
	/** Vraca polozaj u trenutku time. */
	public Vector positionAt(double time) {
		return p.add(v.mul(time - t));
	}
	
	
	/** Vraca brzinu u trenutku time. */
	public Vector velocityAt(double time) {
		return v;
	}
	
	
	/** Proverava da li se u trenutku time sece sa telom o */
	public boolean collidesWith(Body o, double time) {
		Vector p1 = positionAt(time);
		Vector p2 = o.positionAt(time);
		return p1.distanceTo(p2) <= r + o.r;
	}
}
