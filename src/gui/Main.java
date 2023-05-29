package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class Main extends JDialog {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Main dialog = new Main();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Main() {
		setBounds(100, 100, 960, 540);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel moroccoPane = new JPanel();
			moroccoPane.setBackground(new Color(0, 0, 0));
			getContentPane().add(moroccoPane, BorderLayout.NORTH);
			{
				JLabel headerLabel = new JLabel("MOROCCO HOLIDAY CENTRE");
				headerLabel.setForeground(new Color(176, 88, 0));
				headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
				moroccoPane.add(headerLabel);
			}
		}
		
		JPanel loginPane = new JPanel();
		getContentPane().add(loginPane, BorderLayout.CENTER);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginClicked();
			}
		});
		
		FlowLayout fl_loginPane = new FlowLayout(FlowLayout.CENTER, 5, 180);
		fl_loginPane.setAlignOnBaseline(true);
		loginPane.setLayout(fl_loginPane);
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		loginPane.add(loginButton);
	}

	private void loginClicked() {
		ApartmentOverview aView = new ApartmentOverview();
		aView.setVisible(true);
		super.setVisible(false);
		super.dispose();
	}

}
