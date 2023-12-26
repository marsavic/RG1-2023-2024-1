package topic9_particle_systems;

import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetDoubleLogarithmic;
import mars.geometry.Vector;
import mars.utils.Graphics;

import java.util.ArrayList;
import java.util.List;


class Star {
	private Vector p;
	private Vector v;
	private double timeStart;
	private Color color;
	private double size;
	private double duration;
	

	
	public Star(double timeStart, double pStD, double vStD, double size, double duration, double hue) {
		this.timeStart = timeStart;
		this.size = size;
		this.duration = duration;

		p = Vector.randomGaussian(pStD);
		v = Vector.randomGaussian(vStD);
		
		color = Color.hsb(
				hue,
				0.4 + 0.4 * Math.random(),
				0.6 + 0.4 * Math.random()
			);
	}
	
	
	public void draw(View view, double time) {
		double t = time - timeStart;
		double k = t / duration;
		
		if (k >= 0 && k <= 1) {
			Vector q = p.add(v.mul(t));
			view.setFill(Graphics.scaleOpacity(color, 1-k));
			view.fillCircleCentered(q, k * size);
		}
	}
	
	
	public boolean finished(double time) {
		return time - timeStart > duration;
	}
}


public class Starfield implements Drawing {

	@GadgetAnimation(start = true)
	double time = 0.0;
	
	@GadgetDoubleLogarithmic(min = 0.000001, max = 1)
	double timeInterval = 0.0001;

	@GadgetDouble(min = 0, max = 10)
	double duration = 0.5;
	
	@GadgetDouble(min = 0, max = 600)
	double pStD = 20.0;

	@GadgetDouble(min = 0, max = 600)
	double vStD = 120.0;
	
	@GadgetDouble(min = 0, max = 300)
	double size = 4.0;

	@GadgetDouble(min = 0, max = 360)
	double hue = 30.0;
	
	
	List<Star> stars = new ArrayList<>();
	double timeNext = 0;
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0.0, 0.0));
		
		while (time >= timeNext) {
			stars.add(new Star(timeNext, pStD, vStD, size, duration, hue));
			timeNext += timeInterval;
		}
		
		stars.removeIf(star -> star.finished(time));
		stars.forEach(star -> star.draw(view, time));

		view.applyEffect(new Bloom());
	}

	
	public static void main(String[] args) {
		DrawingApplication.launch(700, 700);
	}

}
