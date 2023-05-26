package gui;

import controller.LoginController;

public class tui {
	public static void main(String[] args) {
		LoginController lc = new LoginController();
		System.out.println(lc.getCurrEmployee());
	}
}
