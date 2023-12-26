package topic9_particle_systems;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;

import java.util.LinkedHashSet;
import java.util.Set;


class Firework {
	private static final int n = 1000;          // Broj cestica.
	private static final double duration = 1.0; // Trajanje vatrometa.
	
	private Vector[] p;                         // Pocetni polozaji cestica.
	private Vector[] v;                         // Brzine cestica.

	private double timeStart;                   // Vreme pocetka vatrometa.
	private double hue;                         // Hue vatrometa.

	
	
	public Firework(double timeStart, Vector center, double hue) {
		this.timeStart = timeStart;
		this.hue = hue;

		p = new Vector[n];
		v = new Vector[n];

		for (int i = 0; i < n; i++) {
			p[i] = center;
			v[i] = Vector.randomInDisk(250);
		}
	}
	
	
	public void draw(View view, double time) {
		double t = (time - timeStart) / duration;       // Normalizovano vreme (od 0 do 1) toka vatrometa. 
		
		if (t >= 0 && t <= 1) {
			view.setFill(Color.hsb(hue, 0.5, 1, 1-t));  // Sto je vise proslo to je cestica providnija.
			
			for (int i = 0; i < n; i++) {
				Vector a = p[i].add(v[i].mul(t));
				view.fillCircleCentered(a, (1 + 6 * t));
			}
		}
	}
	
	
	public boolean finished(double time) {
		return time - timeStart > 1;
	}
}


public class Fireworks implements Drawing {
	
	@GadgetAnimation(start = true)
	double time = 0.0;
	

	Set<Firework> fireworks = new LinkedHashSet<>();
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0.0, 0.125));
		
		fireworks.removeIf(firework -> firework.finished(time));
		fireworks.forEach(firework -> firework.draw(view, time));
	}


	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		if (event.isMouseButtonPress(1)) {
			fireworks.add(new Firework(time, pointerWorld, 360 * Math.random()));
		}
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}

}
