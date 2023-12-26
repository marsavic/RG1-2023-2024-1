package topic9_particle_systems.fountain;

import javafx.scene.paint.Color;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.utils.camera.CameraSimple;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;


public class FountainDemo implements Drawing {

	@GadgetAnimation(start = true)
	double time = 0.0;

	CameraSimple camera = new CameraSimple();
	
	Vector g = new Vector(0, -500);
	Vector p = new Vector(0, -200);
	Vector l = new Vector(230, 0);
	double yFloor = p.y;
	
	Fountain[] fountains = new Fountain[] {
		new Fountain(0, p       , Vector.polar(600, 1.0 / 4), g, yFloor),
		new Fountain(0, p       , Vector.polar(400, 0.9 / 4), g, yFloor),
		new Fountain(0, p       , Vector.polar(400, 1.1 / 4), g, yFloor),
		new Fountain(0, p       , Vector.polar(300, 0.6 / 4), g, yFloor),
		new Fountain(0, p       , Vector.polar(300, 1.4 / 4), g, yFloor),
		new Fountain(0, p.add(l), Vector.polar(500, 1.2 / 4), g, yFloor),
		new Fountain(0, p.sub(l), Vector.polar(500, 0.8 / 4), g, yFloor),
	};
	
	
	
	@Override
	public void draw(View view) {
		view.setTransformation(camera.getTransformation());
		
		DrawingUtils.clear(view, Color.hsb(0, 0, 0.1));

		for (Fountain fountain : fountains) {
			fountain.draw(view, time);
		}
	}
	
	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		camera.receiveEvent(view, event, state, pointerWorld, pointerViewBase);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 500, Fountain.class);
	}

}
