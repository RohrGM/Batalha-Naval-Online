package panel;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import panel.PanelController;
import util.Communication;
import util.SizePattern;

public class Match extends JPanel {

	private static final long serialVersionUID = -5776263174068026435L;
	private JTextField txtIp = new JTextField();
	private JTextField txtPort = new JTextField();
	private JButton btnFindMatch = new JButton("Buscar");
	private JButton btnBack = new JButton("Voltar");
	private PanelController panelController;

	public Match(PanelController panelController) {
		this.panelController = panelController;
		this.setPreferredSize(new Dimension(SizePattern.screenWidth, SizePattern.screenHeight));	
		this.setFocusable(true);
		
		initComponents();
	}

	private void initComponents() {
		setLayout(null);

		int wIpTxt = 300;
		int hIpTxt = 40;
		txtIp.setLocation(SizePattern.getXCenterPosition(SizePattern.screenWidth, wIpTxt), 125);
		txtIp.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtIp.setSize(new Dimension(wIpTxt, hIpTxt));
		txtIp.setText("localhost");
		txtIp.setColumns(10);
		add(txtIp);

		int wPortTxt = 200;
		int hPortTxt = 40;
		txtPort.setLocation(SizePattern.getXCenterPosition(SizePattern.screenWidth, wPortTxt), 170);
		txtPort.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtPort.setSize(new Dimension(wPortTxt, hPortTxt));
		txtPort.setText("1099");
		txtPort.setColumns(10);
		add(txtPort);

		int wFindBtn = 100;
		int hFindBtn = 40;
		btnFindMatch.addActionListener(findMatchBtnAction());
		btnFindMatch.setLocation(SizePattern.getXCenterPosition(SizePattern.screenWidth, wFindBtn), 220);
		btnFindMatch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFindMatch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFindMatch.setSize(new Dimension(wFindBtn, hFindBtn));
		add(btnFindMatch);

		int wBackBtn = 100;
		int hBackBtn = 40;
		btnBack.addActionListener(backToMenuBtnAction());
		btnBack.setLocation(SizePattern.getXCenterPosition(SizePattern.screenWidth, wBackBtn), 265);
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBack.setSize(new Dimension(wBackBtn, hBackBtn));
		add(btnBack);

	}
	
	private ActionListener findMatchBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelController.setCommunication(Communication.getRemoteService(txtIp.getText(), txtPort.getText()));
				panelController.changePanel("Lobby");
			}
		};
	}

	private ActionListener backToMenuBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelController.changePanel("Menu");
			}
		};
	}

}