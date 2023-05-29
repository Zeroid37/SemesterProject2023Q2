package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.DataAccessException;
import model.Booking;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;

import controller.BookingController;

import java.awt.CardLayout;
import javax.swing.BoxLayout;

public class PayBookingDeposit extends JDialog {
	private JTextField bookingNoTxt;
	private JTextField amountPaidTxt;
	private JLabel dateFromLbl;
	private JLabel noOfNightsLbl;
	private JLabel pricePerNightsLbl;
	private JLabel apartmentTypeLbl;
	private JLabel amountOfBedsLbl;
	private JLabel floorLbl;
	private JLabel balconyLbl;
	private JLabel totalLbl;
	private JLabel expectedAmountLbl;
	private String bookingNoSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PayBookingDeposit dialog = new PayBookingDeposit();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PayBookingDeposit() {
		setBounds(100, 100, 960, 540);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel top = new JPanel();
			top.setBackground(new Color(0, 0, 0));
			getContentPane().add(top, BorderLayout.NORTH);
			{
				JLabel headerLabel = new JLabel("MOROCCO HOLIDAY CENTRE");
				headerLabel.setForeground(new Color(176, 88, 0));
				headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
				top.add(headerLabel);
			}
		}
		{
			JPanel west = new JPanel();
			west.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			getContentPane().add(west, BorderLayout.WEST);
			west.setLayout(new MigLayout("", "[54.00,grow][111.00,grow][7.00,grow]", "[][35.00][35.00][35.00][35.00][35.00][35.00][35.00][35.00][35.00][35.00][35.00][grow]"));
			{
				JLabel lblNewLabel = new JLabel("Booking Number");
				west.add(lblNewLabel, "cell 0 1,alignx trailing");
			}
			{
				bookingNoTxt = new JTextField();
				bookingNoTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
				west.add(bookingNoTxt, "cell 1 1,growx");
				bookingNoTxt.setColumns(10);
			}
			{
				JButton btnNewButton = new JButton("Find Booking");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							findBookingClicked();
						} catch (DataAccessException e1) {
							JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
						}
					}
				});
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				west.add(btnNewButton, "cell 1 2");
			}
			{
				JLabel lblfirst = new JLabel("Date From");
				west.add(lblfirst, "cell 0 5,alignx left");
			}
			{
				dateFromLbl = new JLabel("");
				west.add(dateFromLbl, "cell 1 5");
			}
			{
				JLabel lblSecond = new JLabel("No of nights");
				west.add(lblSecond, "cell 0 6,alignx left");
			}
			{
				noOfNightsLbl = new JLabel("");
				west.add(noOfNightsLbl, "cell 1 6");
			}
			{
				JLabel lblThird = new JLabel("Price per night");
				west.add(lblThird, "cell 0 7,alignx left");
			}
			{
				pricePerNightsLbl = new JLabel("");
				west.add(pricePerNightsLbl, "cell 1 7");
			}
		
			{
				JLabel lblFourth = new JLabel("Type");
				west.add(lblFourth, "cell 0 8,alignx left");
			}
			{
				apartmentTypeLbl = new JLabel("");
				west.add(apartmentTypeLbl, "cell 1 8");
			}
			{
				JLabel lblFifth = new JLabel("Beds amount");
				west.add(lblFifth, "cell 0 9,alignx left");
			}
			{
				amountOfBedsLbl = new JLabel("");
				west.add(amountOfBedsLbl, "cell 1 9");
			}
			{
				JLabel lblSicth = new JLabel("Floor");
				west.add(lblSicth, "cell 0 10,alignx left");
			}
			{
				floorLbl = new JLabel("");
				west.add(floorLbl, "cell 1 10");
			}
			{
				JLabel lblSeventh = new JLabel("Balcony");
				west.add(lblSeventh, "cell 0 11,alignx left");
			}
			{
				balconyLbl = new JLabel("");
				west.add(balconyLbl, "cell 1 11");
			}
		}
		{
			JPanel east = new JPanel();
			east.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			getContentPane().add(east, BorderLayout.EAST);
			east.setLayout(new MigLayout("", "[]", "[][][][][][][][][][][][][][][]"));
			{
				JButton confirmPaymentButton = new JButton("<html>Confirm<br />Payment</html>");
				confirmPaymentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							confirmPaymentClicked();
						} catch (DataAccessException e1) {
							JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
						}
					}
				});
				confirmPaymentButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
				east.add(confirmPaymentButton, "cell 0 0");
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelClicked();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
				east.add(cancelButton, "cell 0 14,alignx center");
			}
		}
		{
			JPanel center = new JPanel();
			center.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			getContentPane().add(center, BorderLayout.CENTER);
			center.setLayout(new MigLayout("", "[400.00,grow]", "[49px][][][][][]"));
			{
				JLabel lblNewLabel_1 = new JLabel("TOTAL");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
				center.add(lblNewLabel_1, "cell 0 0,alignx center,aligny center");
			}
			{
				totalLbl = new JLabel(" ");
				totalLbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
				center.add(totalLbl, "cell 0 1,alignx center,aligny center");
			}
			{
				JLabel lblNewLabel_1 = new JLabel("EXPECTED DEPOSIT");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
				center.add(lblNewLabel_1, "cell 0 2,alignx center,aligny center");
			}
			{
				expectedAmountLbl = new JLabel(" ");
				expectedAmountLbl.setFont(new Font("Tahoma", Font.PLAIN, 40));
				center.add(expectedAmountLbl, "cell 0 3,alignx center,aligny center");
			}
			{
				JLabel lblNewLabel_1 = new JLabel("AMOUNT PAID");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
				center.add(lblNewLabel_1, "cell 0 4,alignx center,aligny center");
			}
			{
				amountPaidTxt = new JTextField();
				amountPaidTxt.setFont(new Font("Tahoma", Font.PLAIN, 40));
				center.add(amountPaidTxt, "cell 0 5,growx");
				amountPaidTxt.setColumns(10);
			}
		}
	}

	private void findBookingClicked() throws DataAccessException {
		bookingNoSearch = bookingNoTxt.getText();
		BookingController bookingController = new BookingController();
		Booking b = bookingController.findBookingByBookingNo(bookingNoSearch);
		
		dateFromLbl.setText(b.getDateStart().toString());
		noOfNightsLbl.setText(String.valueOf(b.getNoOfNights()));
		pricePerNightsLbl.setText(String.valueOf(b.getApartment().getPricePerNight()));
		apartmentTypeLbl.setText(b.getApartment().getApartmentType());
		amountOfBedsLbl.setText(String.valueOf(b.getApartment().getNumberOfBeds()));
		floorLbl.setText(String.valueOf(b.getApartment().getFloorNo()));
		balconyLbl.setText(String.valueOf(b.getApartment().isHasBalcony()));
		
		totalLbl.setText(String.valueOf(b.getPrice()));
		expectedAmountLbl.setText(String.valueOf(b.getPrice()/2));
	}

	private void cancelClicked()  {
		// TODO Auto-generated method stub
		
	}

	private void confirmPaymentClicked() throws DataAccessException {
		double amountInput = Double.parseDouble(amountPaidTxt.getText());
		BookingController bookingController = new BookingController();
		if(bookingController.payDeposit(amountInput, bookingNoSearch)) {
			JOptionPane.showMessageDialog(new JFrame(), "Deposit has been paid for booking: " + bookingNoSearch);
			ApartmentOverview aView = new ApartmentOverview();
			aView.setVisible(true);
			super.setVisible(false);
			super.dispose();
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "The amount paid is less than the expected amount");
		}
	}

}
