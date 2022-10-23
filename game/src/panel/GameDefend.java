package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import Interface.IEntity;
import asset.ImageData;
import component.EntityManager;
import entity.PlayerDefend;
import panel.PanelController;
import service.PlayerDefendKeyHandler;
import util.SizePattern;

public class GameDefend extends JPanel implements Runnable{

	private Thread gameThread;
	private ImageData imageData = new ImageData();
	private EntityManager manager = new EntityManager();
	private PanelController panelController;
	final private int FPS = 30;

	public GameDefend(PanelController panelController) {
		this.panelController = panelController;
		this.setPreferredSize(new Dimension(SizePattern.screenWidth, SizePattern.screenHeight));
		this.setDoubleBuffered(true);		
		this.startGameThread();
		this.setFocusable(true);
		
		PlayerDefend player = new PlayerDefend(this.manager);
		this.manager.addEntity(player);
		this.addKeyListener(player.getKeyHandler());
	}
	
	private void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	private void update() {
		for (IEntity entity : manager.getEntities()) {
			entity.update();
		}
	}
	
	private void draw(Graphics2D graphics2d) {
		graphics2d.drawImage(this.imageData.background, 0, 0, SizePattern.screenWidth, SizePattern.screenHeight, null);
		
		for (IEntity entity : manager.getEntities()) {
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
		
		double interval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / interval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				this.update();
				this.repaint();
				delta = 0;
			}
		}
	}
}
