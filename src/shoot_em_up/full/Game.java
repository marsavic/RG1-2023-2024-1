package shoot_em_up.full;

import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import mars.drawingx.application.DrawingApplication;
import mars.drawingx.application.Options;
import mars.drawingx.application.parameters.WindowState;
import mars.drawingx.drawing.Drawing;
import mars.drawingx.drawing.View;
import mars.drawingx.gadgets.annotations.GadgetAnimation;
import mars.geometry.Vector;
import mars.input.InputEvent;
import mars.input.InputState;

import java.util.HashSet;
import java.util.Set;


public class Game implements Drawing {

	final double TIME_TICK_INTERVAL = 0.001;
	final double TIME_ENEMY_INTERVAL = 1.0;

	final Font FONT_SCORE = new Font("Consolas", 30);
	final Font FONT_GAMEOVER = new Font("Consolas", 90);

	
	@GadgetAnimation(start = true)
	double time = 0.0;
	
	Background background; 
	Ship ship;
	Set<Enemy> enemies;
	Set<Projectile> projectiles;
	Set<Explosion> explosions;

	double timeLastUpdate;
	double timeNextEnemy;
	int projectilesRemaining;
	int enemiesDestroyed;
	boolean gameOver;

	
	
	void reset() {
		time = 0.0;
		background = new Background();
		ship = new Ship();
		enemies     = new HashSet<>();
		projectiles = new HashSet<>();
		explosions  = new HashSet<>();
		timeLastUpdate = 0;
		timeNextEnemy = 0;
		projectilesRemaining = 10;
		enemiesDestroyed = 0;
		gameOver = false;
	}
	
	
	@Override
	public void init(View view) {
		reset();
	}
	
	
	void update(double t) {
		// Azuriranje stanja u trenutku t.
		
		
		// Dodavanje novih neprijateljskih brodova.
		while (timeNextEnemy <= t) {
			enemies.add(new Enemy(t));
			timeNextEnemy += TIME_ENEMY_INTERVAL;
		}

		
		// Uklanjanje stvari koje nam vise ne trebaju.

		explosions.removeIf(e -> !e.isAlive());
		projectiles.removeIf(p -> p.isFinished());

		Set<Enemy> enemiesToRemove = new HashSet<>();

		for (Projectile projectile : projectiles) {
			if (!projectile.isFinished() && projectile.positionAt(t).y > 500) {
				projectile.finish();
			}
		}
		
		
		for (Enemy enemy : enemies) {
			// Uklanjamo protivnika ako se spustio ispod vidnog polja.
			if (enemy.positionAt(t).y < -450) {
				enemiesToRemove.add(enemy);
				addProjectiles(-1);
			}
			
			for (Projectile projectile : projectiles) {
				// Proveravanje za svaki par (enemy, projectile) da li je doslo do sudara. Ako jeste,
				// pravimo eksploziju na mestu neprijateljskog broda i uklanjamo i njega i projektil.
				if (!projectile.isFinished()) {
					if (enemy.collidesWith(projectile, t)) {
						explosions.add(new Explosion(t, projectile, enemy));
						enemiesToRemove.add(enemy);
						projectile.finish();
						addProjectiles(1);
						enemiesDestroyed++;
					}
				}
			}
		}
		
		enemies.removeAll(enemiesToRemove);
		
		// Proveravamo da li je game over.
		checkGameOver();
	}
	
	
	void checkGameOver() {
		if (projectilesRemaining <= 0 && projectiles.isEmpty()) {
			gameOver = true;
		}
	}
	
	
	void addProjectiles(int change) {
		projectilesRemaining += change;
	}
	
	
	void fireProjectile() {
		if (projectilesRemaining > 0) {
			projectiles.add(new Projectile(ship, time));
			addProjectiles(-1);
		}
	}

	
	@Override
	public void draw(View view) {
		
		// Azuriranje stanja, tick po tick.
		
		while (timeLastUpdate + TIME_TICK_INTERVAL <= time) {
			timeLastUpdate += TIME_TICK_INTERVAL;
			update(timeLastUpdate);
		}

		
		// Iscrtavanje.

		view.stateStore();
		
		view.setUsingWorldSpace(true);   // Crtamo u "svetu igre", koordinatni pocetak je u centru prozora.
		background.update(time);
		background.draw(view, time);

		for (Enemy enemy : enemies) {
			enemy.draw(view, time);
		}
		
		ship.draw(view, time);

		for (Projectile projectile : projectiles) {
			projectile.draw(view, time);
		}
		
		for (Explosion explosion : explosions) {
			explosion.draw(view, time);
		}


		view.setUsingWorldSpace(false);   // Crtamo po "displeju".
		
		view.setFill(Color.hsb(0, 0, 1, 1));
		Vector p = new Vector(16, 16);
		Vector d = new Vector(10, 30);
		Vector l = new Vector(20, 0);
		for (int i = 0; i < projectilesRemaining; i++) {
			view.fillRect(p.add(l.mul(i)), d);
		}
		
		view.setFont(FONT_SCORE);
		view.setTextAlign(TextAlignment.RIGHT);
		view.setTextBaseline(VPos.BASELINE);
		view.fillText("" + enemiesDestroyed, view.getCornerLowerRight().add(new Vector(-p.x, p.y)));
		
		if (gameOver) {
			view.setFont(FONT_GAMEOVER);
			view.setFill(Color.hsb(0, 0, 1));
			view.setTextAlign(TextAlignment.CENTER);
			view.setTextBaseline(VPos.CENTER);
			view.fillText("Game over", view.getCenter());
		}
		
		view.stateRestore();
	}
	
	
	
	@Override
	public void receiveEvent(View view, InputEvent event, InputState state, Vector pointerWorld, Vector pointerViewBase) {
		if (event.isKey(KeyCode.LEFT) || event.isKey(KeyCode.RIGHT)) {
			int dir = 0;
			dir += state.keyPressed(KeyCode.LEFT) ? -1 : 0;
			dir += state.keyPressed(KeyCode.RIGHT) ? 1 : 0;
			ship.setSpeed(Vector.UNIT_X.mul(dir * 300), time);
		}
		if (event.isKeyPress(KeyCode.SPACE)) {
			fireProjectile();
		}
		if (event.isKeyPress(KeyCode.ENTER)) {
			reset();
		}
	}



	public static void main(String[] args) {
		Options options = new Options();
		
		options.constructGui = false;
		options.hideMouseCursor = true;
		options.drawingSize = new Vector(800, 800);
		options.resizable = false;
		options.initialWindowState = WindowState.NORMAL;
		
		DrawingApplication.launch(options);
	}
	
}
