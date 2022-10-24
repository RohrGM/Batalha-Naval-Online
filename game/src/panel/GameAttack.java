package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Interface.IEntity;
import asset.ImageData;
import component.EntityManager;
import entity.Attacker;
import entity.PlayerAttack;

import util.SizePattern;

public class GameAttack extends JPanel implements Runnable {

	private Thread gameThread;
	private ImageData imageData = new ImageData();
	private EntityManager manager;
	private PanelController panelController;
	private static long frameCount = 1;
	private final int FPS = 30;

	public GameAttack(PanelController panelController) {
		this.panelController = panelController;
		this.manager = new EntityManager(this.panelController.getCommunication(),
				Long.toString(this.panelController.getPlayer().getId()));
		this.setPreferredSize(new Dimension(SizePattern.screenWidth, SizePattern.screenHeight));
		this.setDoubleBuffered(true);
		this.startGameThread();
		this.setFocusable(true);

		PlayerAttack player = new PlayerAttack(this.manager);
		this.manager.addEntity(player);
		this.panelController.addKeyListener(player.getKeyHandler());
	}

	private void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	private void updatecontext() {
		if (frameCount % 10 == 0) {
			this.manager.updateEntities();
		}
	}

	private void checkLife(IEntity entity) {
		if (entity.isAlive() == false) {
			this.manager.removeEntity(entity);
		}
	}

	private void update() {
		for (IEntity entity : new ArrayList<>(this.manager.getEntities())) {
			if (entity.getClass() == Attacker.class) {
				// System.out.println(entity);
			}
			entity.update();
			this.checkLife(entity);
		}
		// System.out.println("===============");
	}

	private void draw(Graphics2D graphics2d) {
		try {
			graphics2d.drawImage(ImageIO.read(getClass().getResourceAsStream(this.imageData.background)), 0, 0,
					SizePattern.screenWidth, SizePattern.screenHeight, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (IEntity entity : new ArrayList<>(manager.getEntities())) {
			entity.draw(graphics2d);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		this.draw(g2);
		g2.setColor(Color.WHITE);
	}

	@Override
	public void run() {

		double interval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / interval;
			lastTime = currentTime;

			if (delta >= 1) {
				frameCount++;
				this.updatecontext();
				this.update();
				this.repaint();
				delta = 0;
			}
		}
	}

	public static long getFrameCount() {
		return frameCount;
	}
}
