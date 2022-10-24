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
import entity.PlayerDefend;
import entity.Sentinel;
import util.SizePattern;

public class GamePanel extends JPanel implements Runnable {

	protected Thread gameThread;
	protected ImageData imageData = new ImageData();
	protected EntityManager manager;
	protected PanelController panelController;
	protected static long frameCount = 1;
	protected final int FPS = 30;

	public GamePanel(PanelController panelController) {
		this.panelController = panelController;
		this.manager = new EntityManager(this.panelController.getCommunication(),
				Long.toString(this.panelController.getPlayer().getId()));
		this.setPreferredSize(new Dimension(SizePattern.screenWidth, SizePattern.screenHeight));
		this.setDoubleBuffered(true);
		this.startGameThread();
		this.setFocusable(true);
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

	private void update() {
		for (IEntity entity : new ArrayList<>(this.manager.getEntities())) {
			entity.update();
		}
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
