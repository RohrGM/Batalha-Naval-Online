package scene;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import util.SizePattern;
import server.Server;
import java.rmi.RemoteException;

public class Principal extends JFrame {

	private static final long serialVersionUID = -4536537793387275613L;
	private JPanel contentPane = new JPanel();
	private Principal main = this;
	private Server server;
	
	public Principal() {		
		initComponents();		
	}
	
	public void changeScene(String sceneName) {
		contentPane.removeAll();
		contentPane.revalidate();
		contentPane.repaint();
		
		switch(sceneName) {
		  case "FindMatch":
			  contentPane.add(new FindMatch(server, main));
		    break;
		  case "Menu":
			  contentPane.add(new Menu(server, main));
		  default:
		    break;
		}		
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
		setBounds(100, 100, wPanel, hPanel);
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		contentPane.setLayout(null);
		
		contentPane.add(new Menu(server, main));
	}
}
