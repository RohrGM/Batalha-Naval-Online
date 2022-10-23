package main;

import javax.swing.JFrame;
import panel.PanelController;

public class Main {

	public static void main(String[] args) {
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		
		jframe.add(new PanelController());
		jframe.pack();
		
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
	}
}
