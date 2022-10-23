package panel;

import java.awt.Dimension;
import javax.swing.JPanel;

import panel.PanelController;
import util.Communication;
import util.SizePattern;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JPanel {
	private PanelController panelController;
	private JButton btnHostMatch = new JButton("Hospedar Partida");
	private JButton btnFindMatch = new JButton("Buscar Partida");

	public Menu(PanelController panelController) {
		this.panelController = panelController;
		this.setPreferredSize(new Dimension(SizePattern.screenWidth, SizePattern.screenHeight));	
		this.setFocusable(true);
		
		this.initComponents();
	}
	
	private void initComponents() {
		setBounds(0, 0, SizePattern.screenWidth, SizePattern.screenHeight);
		setLayout(null);

		int wHostBtn = 300;
		int hHostBtn = 40;
		btnHostMatch.addActionListener(hostMatchBtnAction());
		btnHostMatch.setLocation(SizePattern.getXCenterPosition(SizePattern.screenWidth, wHostBtn), 125);
		btnHostMatch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHostMatch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnHostMatch.setSize(new Dimension(wHostBtn, hHostBtn));
		add(btnHostMatch);

		int wFindBtn = 300;
		int hFindBtn = 40;
		btnFindMatch.addActionListener(findMatchBtnAction());
		btnFindMatch.setLocation(SizePattern.getXCenterPosition(SizePattern.screenWidth, wFindBtn), 200);
		btnHostMatch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFindMatch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFindMatch.setSize(new Dimension(wFindBtn, hFindBtn));
		add(btnFindMatch);
	}

	private ActionListener hostMatchBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelController.getServer().start();
				panelController.setCommunication(Communication.getLocalService());
				panelController.changePanel("Lobby");
			}
		};
	}

	private ActionListener findMatchBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelController.changePanel("Match");
			}
		};
	}
}
