package topic6_animation;

import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.DrawingUtils;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.drawingx.gadgets.annotations.GadgetDouble;
import mars.drawingx.gadgets.annotations.GadgetVector;
import mars.geometry.Vector;
import mars.utils.Numeric;


public class Triangle implements Drawing {
	
	@GadgetAnimation
	double time = 0.0;

	@GadgetVector
	Vector p0 = new Vector(-200, -200);

	@GadgetVector
	Vector p1 = new Vector(300, -100);
	
	@GadgetVector
	Vector p2 = new Vector(100, 200);
	
	@GadgetDouble(min = 0, max = 1000)
	double speed = 300;                                 // Brzina kretanja loptice (u pikselima po sekundi).
	
	
	
	@Override
	public void draw(View view) {
		DrawingUtils.clear(view, Color.gray(0.125));
		
		view.setLineJoin(StrokeLineJoin.ROUND);
		view.setStroke(Color.BLUE);
		view.setLineWidth(5);
		view.strokePolygon(p0, p1, p2);
		
		double l0 = p0.distanceTo(p1);
		double l1 = p1.distanceTo(p2);
		double l2 = p2.distanceTo(p0);
		double l = l0 + l1 + l2;
		
		double t0 = 0;
		double t1 = t0 + l0;
		double t2 = t1 + l1;
		
		double t = Numeric.mod(time * speed, l);
		
		Vector q0, q1;
		double k;
		
		if(t < t1) {q0 = p0; q1 = p1; k = (t - t0)/l0;} else
		if(t < t2) {q0 = p1; q1 = p2; k = (t - t1)/l1;} else
				   {q0 = p2; q1 = p0; k = (t - t2)/l2;}
		
		Vector p = Vector.lerp(q0, q1, smootherstep(k));
		
		view.setFill(Color.ORANGE);
		view.fillCircleCentered(p, 20);
	}
	
	private double smootherstep(double x) {
		return x * x * x * (x * (x * 6 - 15) + 10);
	}
	
	
	public static void main(String[] args) {
		DrawingApplication.launch(800, 800);
	}
	
}
