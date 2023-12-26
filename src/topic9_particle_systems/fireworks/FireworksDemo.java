package topic9_particle_systems.fireworks;

import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;
import mars.random.sampling.Sampling;

import java.util.ArrayList;
import java.util.List;


public class FireworksDemo implements Drawing {

	@GadgetAnimation(start = true)
	double time = 0.0;
	
	@GadgetVector
	Vector g = new Vector(0, -100);
	
	List<Firework> fireworks = new ArrayList<>();
	
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.hsb(0, 0.0, 0.1));

		fireworks.removeIf(firework -> !firework.isAlive());
		fireworks.forEach(firework -> firework.draw(view, time));
		
		view.applyEffect(new Bloom());
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 600);
	}
	
	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		if (event.isMouseButtonPress(1)) {
			double hue = 360 * Sampling.uniform();
			fireworks.add(new Firework(
					600,                     // n
					time,                    // t 
					1,                       // tD
					pointerWorld,            // p
					pointerWorld.mul(0.3),   // v
					g,                       // a
					100,                     // speed
					hue,                     // hue
					4                        // r
			));
		}
	}

	
}
