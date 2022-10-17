package scene;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import component.Player;
import util.SizePattern;
import server.Server;

import java.io.Serializable;
import java.rmi.RemoteException;

public class SPrincipal extends JFrame {

	private static final long serialVersionUID = -7071831597520421441L;
	private JPanel contentPane = new JPanel();
	private Player player = new Player("Player");
	private SPrincipal main = this;
	private Server server;

	public SPrincipal() {
		initComponents();
	}

	public void changeScene(String sceneName) {
		contentPane.removeAll();
		contentPane.revalidate();
		contentPane.repaint();

		switch (sceneName) {
		case "FindMatch":
			contentPane.add(new SFindMatch(server, main));
			break;
		case "Menu":
			contentPane.add(new SMenu(server, main));
		default:
			break;
		}
	}

	public Player getPlayer() {
		return player;
	}

	private void initComponents() {
		try {
			server = new Server();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		int wPanel = SizePattern.getWidht();
		int hPanel = SizePattern.getHeight();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 100, wPanel, hPanel);
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		contentPane.add(new SMenu(server, main));
	}
}
