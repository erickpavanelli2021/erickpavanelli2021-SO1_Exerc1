package view;

import javax.swing.JOptionPane;
import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController redeControl = new RedesController();
		String processo = JOptionPane.showInputDialog("Qual processo gostaria de verificar?\n"
				+ "1 - Ip \n2 - Ping");
		int option = Integer.parseInt(processo);
		
		if(option == 1) {
			String ipValidation = "";
			redeControl.ip(ipValidation);
		}else {
			String pingValidation = "";
			redeControl.ping(pingValidation);
		}

	}

}
