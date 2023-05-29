package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.BookingController;
import database.DataAccessException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

public class GuestCreate extends JDialog {

	private LocalDate start;
	private LocalDate end;
	private String apartmentNo;
	private JTextField famNameTxt;
	private JTextField firstNameTxt;
	private JTextField streetTxt;
	private JTextField zipcodeTxt;
	private JTextField phoneTxt;
	private JTextField countryTxt;
	private JTextField houseNoTxt;
	private JTextField cityTxt;
	private JTextField emailTxt;
	private BookingController bookingController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GuestCreate dialog = new GuestCreate(null, null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GuestCreate(LocalDate startI, LocalDate endI, String apartmentNoI) {
		setBounds(100, 100, 960, 540);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel north = new JPanel();
			north.setBackground(new Color(0, 0, 0));
			getContentPane().add(north, BorderLayout.NORTH);
			{
				JLabel headerLabel = new JLabel("MOROCCO HOLIDAY CENTRE");
				headerLabel.setForeground(new Color(176, 88, 0));
				headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
				north.add(headerLabel);
			}
		}
		{
			JPanel right = new JPanel();
			right.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			getContentPane().add(right, BorderLayout.EAST);
			right.setLayout(new MigLayout("", "[]", "[][][][][][][][][][][][][][][]"));
			{
				JButton confirmBookingButton = new JButton("<html>Confirm<br />Booking</html>");
				confirmBookingButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							bookingConfirmedClicked();
						} catch (DataAccessException e1) {
							JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
						}
					}
				});
				confirmBookingButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
				right.add(confirmBookingButton, "cell 0 0");
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelClicked();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
				right.add(cancelButton, "cell 0 14,alignx center");
			}
		}
		{
			JPanel rest = new JPanel();
			getContentPane().add(rest, BorderLayout.CENTER);
			rest.setLayout(new MigLayout("", "[87.00][grow][][][][][][][][grow]",
					"[50.00][50.00][50.00][50.00][50.00][50.00][50.00][50.00][50.00]"));
			{
				JLabel lblNewLabel = new JLabel("First name");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
				rest.add(lblNewLabel, "cell 0 0,alignx left");
			}
			{
				firstNameTxt = new JTextField();
				firstNameTxt.setFont(new Font("Tahoma", Font.PLAIN, 25));
				rest.add(firstNameTxt, "cell 1 0,growx");
				firstNameTxt.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("Family name");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
				rest.add(lblNewLabel, "cell 8 0,alignx left");
			}
			{
				famNameTxt = new JTextField();
				famNameTxt.setFont(new Font("Tahoma", Font.PLAIN, 25));
				rest.add(famNameTxt, "cell 9 0,growx");
				famNameTxt.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("Street");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
				rest.add(lblNewLabel, "cell 0 2,alignx left");
			}
			{
				streetTxt = new JTextField();
				streetTxt.setFont(new Font("Tahoma", Font.PLAIN, 25));
				streetTxt.setColumns(10);
				rest.add(streetTxt, "cell 1 2,growx");
			}
			{
				JLabel lblNewLabel = new JLabel("House no");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
				rest.add(lblNewLabel, "cell 8 2,alignx left");
			}
			{
				houseNoTxt = new JTextField();
				houseNoTxt.setFont(new Font("Tahoma", Font.PLAIN, 25));
				houseNoTxt.setColumns(10);
				rest.add(houseNoTxt, "cell 9 2,growx");
			}
			{
				JLabel lblNewLabel = new JLabel("Zipcode");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
				rest.add(lblNewLabel, "cell 0 4,alignx left");
			}
			{
				zipcodeTxt = new JTextField();
				zipcodeTxt.setFont(new Font("Tahoma", Font.PLAIN, 25));
				zipcodeTxt.setColumns(10);
				rest.add(zipcodeTxt, "cell 1 4,growx");
			}
			{
				JLabel lblNewLabel = new JLabel("City");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
				rest.add(lblNewLabel, "cell 8 4,alignx left");
			}
			{
				cityTxt = new JTextField();
				cityTxt.setFont(new Font("Tahoma", Font.PLAIN, 25));
				cityTxt.setColumns(10);
				rest.add(cityTxt, "cell 9 4,growx");
			}
			{
				JLabel lblNewLabel = new JLabel("Phone no");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
				rest.add(lblNewLabel, "cell 0 6,alignx left");
			}
			{
				phoneTxt = new JTextField();
				phoneTxt.setFont(new Font("Tahoma", Font.PLAIN, 25));
				phoneTxt.setColumns(10);
				rest.add(phoneTxt, "cell 1 6,growx");
			}
			{
				JLabel lblNewLabel = new JLabel("Email");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
				rest.add(lblNewLabel, "cell 8 6,alignx left");
			}
			{
				emailTxt = new JTextField();
				emailTxt.setFont(new Font("Tahoma", Font.PLAIN, 25));
				emailTxt.setColumns(10);
				rest.add(emailTxt, "cell 9 6,growx");
			}
			{
				JLabel lblNewLabel = new JLabel("Country");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
				rest.add(lblNewLabel, "cell 0 8,alignx left");
			}
			{
				countryTxt = new JTextField();
				countryTxt.setFont(new Font("Tahoma", Font.PLAIN, 25));
				countryTxt.setColumns(10);
				rest.add(countryTxt, "cell 1 8,growx");
			}
		}
		try {
			init(startI, endI, apartmentNoI);
		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
			cancelClicked();
		}
	}

	private void cancelClicked() {
		ApartmentOverview aView = new ApartmentOverview();
		aView.setVisible(true);
		super.setVisible(false);
		super.dispose();

	}

	private void bookingConfirmedClicked() throws DataAccessException {

		String fName = firstNameTxt.getText();
		String famName = famNameTxt.getText();
		String street = streetTxt.getText();
		String houseNo = houseNoTxt.getText();
		String zipCode = zipcodeTxt.getText();
		String city = cityTxt.getText();
		String phoneNo = phoneTxt.getText();
		String email = emailTxt.getText();
		String country = countryTxt.getText();

		if (validateEmail(email)) {

			if (bookingController.checkSingleApartment(start, end, apartmentNo)) {
				bookingController.createGuest(fName, famName, street, houseNo, zipCode, city, phoneNo, email, country);
				bookingController.bookingConfirm();
				PayBookingDeposit pbd = new PayBookingDeposit();
				pbd.setVisible(true);
				super.setVisible(false);
				super.dispose();
			} else {
				cancelClicked();
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Email format not valid");
		}
	}

	private boolean validateEmail(String emailInput) {
		boolean res = false;
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(emailInput);
		return matcher.matches();
	}

	private void init(LocalDate start, LocalDate end, String apartmentNo) throws DataAccessException {
		this.start = start;
		this.end = end;
		this.apartmentNo = apartmentNo;
		bookingController = new BookingController();
		bookingController.startBooking(start, end, apartmentNo);
	}

}
