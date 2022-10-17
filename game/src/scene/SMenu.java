package scene;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JPanel;

import component.Player;
import server.Server;
import util.Service;
import util.SizePattern;

public class SMenu extends JPanel {
	
	private static final long serialVersionUID = 7733357119540448468L;
	private Server server;
	private SPrincipal main;
	private SMenu menu = this;
	private JButton btnHostMatch = new JButton("Hospedar Partida");
	private JButton btnFindMatch = new JButton("Buscar Partida");

	public SMenu(Server server, SPrincipal main) {
		this.server = server;
		this.main = main;

		initComponents();
	}

	private void initComponents() {
		int wPanel = SizePattern.getWidht();
		int hPanel = SizePattern.getHeight();

		setBounds(-8, -32, wPanel, hPanel);
		setLayout(null);

		int wHostBtn = 300;
		int hHostBtn = 40;
		btnHostMatch.addActionListener(hostMatchBtnAction());
		btnHostMatch.setLocation(SizePattern.getXCenterPosition(wPanel, wHostBtn), 125);
		btnHostMatch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHostMatch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnHostMatch.setSize(new Dimension(wHostBtn, hHostBtn));
		add(btnHostMatch);

		int wFindBtn = 300;
		int hFindBtn = 40;
		btnFindMatch.addActionListener(findMatchBtnAction());
		btnFindMatch.setLocation(SizePattern.getXCenterPosition(wPanel, wFindBtn), 200);
		btnHostMatch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFindMatch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFindMatch.setSize(new Dimension(wFindBtn, hFindBtn));
		add(btnFindMatch);
	}

	private ActionListener hostMatchBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.start();
				try {
					Service.getLocalService().joinRoom(main.getPlayer());
				} catch (RemoteException | MalformedURLException | NotBoundException e1) {
					e1.printStackTrace();
				}
			}
		};
	}

	private ActionListener findMatchBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.changeScene("FindMatch");
			}
		};
	}

}
