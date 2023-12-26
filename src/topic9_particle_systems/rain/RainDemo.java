package topic9_particle_systems.rain;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Vector;


public class RainDemo implements Drawing {
	@GadgetAnimation(start = true)
	double time = 0.0;


	Rain rain = new Rain(
			0,
			0.0003,
			new Vector(-800, -600),
			new Vector(1600, 2400)
	);
	

	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.1));
	
		rain.draw(view, time);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 600);
	}
}
