package flappy_bird;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.application.Options;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.View;
import mars.geometry.Transformation;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;
import mars.random.RNG;
import mars.time.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Pticica implements Drawing {
	
	final int nObstacles = 1000;
	final double d = 400;
	final double TICK_INTERVAL = 1.0/300;
	
	
	Bird bird;
	Obstacle[] obstacles;
	Background background;
	
	List<Thing> things;
	Timer timer;
	double t;
	
	
	{
		restart();
	}
	
	
	void restart() {
		RNG rng = new RNG();
		
		things = new ArrayList<>();
		
		background = new Background();
		things.add(background);
		things.add(new Trees(3.7, 400));
		things.add(new Trees(2.8, 0));
		
		bird = new Bird();
		things.add(bird);
		
		obstacles = new Obstacle[nObstacles];
		for (int i = 0; i < nObstacles; i++) {
			obstacles[i] = new Obstacle(new Vector((i+1)*d, rng.nextDouble(-400, 400)), rng.nextDouble(200, 400));
			things.add(obstacles[i]);
		}
		
		things.add(new Bushes(0.6, 20));
		things.add(new Bushes(0.4, -80));
		
		timer = new Timer();
		t = 0;
		
	}
	
	
	void update() {
		bird.update(TICK_INTERVAL);
		t += TICK_INTERVAL;
		
		boolean gameOver = Arrays.stream(obstacles).anyMatch(o -> o.crash(bird));
		if (gameOver) {
			timer.pause();
		}
	}
	
	
	Font font = new Font("Consolas", 40);
	
	@Override
	public void draw(View view) {
		double time = timer.getTime();
		while (t + TICK_INTERVAL <= time) {
			update();
		}
		
		view.stateStore();
		
		view.setUsingWorldSpace(true);
		view.stateStore();
		
		things.forEach(t -> {
			view.setTransformation(Transformation.identity()
					.translate(new Vector(-bird.p.x - 400, 0))
					.scale(1.0 / t.depth())
			);
			t.draw(view, time);
		});
		
		view.stateRestore();
		
		view.setUsingWorldSpace(false);
		
		view.setFont(font);
		view.setFill(Color.WHITE);
		view.fillText("" + (int)(bird.p.x / d), view.getCornerUpperLeft().add(new Vector(60, -60)));
		
		view.stateRestore();
	}
	
	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		if (event.isKeyPress(KeyCode.ESCAPE)) Platform.exit();
		if (event.isKeyPress(KeyCode.ENTER)) restart();
		bird.setDir(state.keyDir(KeyCode.SPACE) * 2 - 1);
	}
	
	
	public static void main(String[] args) {
		Options options = new Options();
		options.resizable = false;
		options.drawingSize = new Vector(1600, 900);
		options.windowTitle = "Ptičica";
		options.hideMouseCursor = true;
		options.constructGui = false;
		DrawingApplication.launch(options);
	}
}
