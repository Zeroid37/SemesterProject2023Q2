package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.border.LineBorder;

import controller.BookingController;
import database.DataAccessException;
import model.Apartment;
import model.Booking;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ApartmentOverview extends JDialog {
	private JTextField dateToTxt;
	private JTextField maxPriceTxt;
	private JTextField dateFromTxt;
	private JTextField minPriceTxt;
	private JComboBox apartmentTypeTxt;
	private JComboBox bedsTxt;
	private JComboBox floorTxt;
	private JComboBox balconyTxt;
	private JList<Apartment> list;

	private LocalDate dateStart;
	private LocalDate dateTo;
	private double minPriceDouble;
	private double maxPriceDouble;
	private String typeGet;
	private int bedsAmountGet;
	private int floorNoGet;
	private boolean hasBalcony;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ApartmentOverview dialog = new ApartmentOverview();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ApartmentOverview() {
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
			JPanel left = new JPanel();
			left.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			getContentPane().add(left, BorderLayout.WEST);
			left.setLayout(new MigLayout("", "[54.00,grow][111.00,grow][7.00,grow]",
					"[][35.00][35.00][35.00][35.00][35.00][35.00][35.00][35.00][grow]"));
			{
				JLabel formatLbl = new JLabel("(Format: YYYY-MM-DD)");
				left.add(formatLbl, "cell 1 0");
			}
			{
				JLabel dateFromLbl = new JLabel("Date From");
				left.add(dateFromLbl, "cell 0 1,alignx left");
			}
			{
				dateFromTxt = new JTextField();
				left.add(dateFromTxt, "cell 1 1,growx");
				dateFromTxt.setColumns(10);
			}
			{
				JLabel dateToLbl = new JLabel("Date To");
				left.add(dateToLbl, "cell 0 2,alignx left");
			}
			{
				dateToTxt = new JTextField();
				dateToTxt.setColumns(10);
				left.add(dateToTxt, "cell 1 2,growx");
			}
			{
				JLabel minPriceLbl = new JLabel("Min Price");
				left.add(minPriceLbl, "cell 0 3,alignx left");
			}
			{
				minPriceTxt = new JTextField();
				minPriceTxt.setColumns(10);
				left.add(minPriceTxt, "cell 1 3,growx");
			}
			{
				JLabel maxPriceLbl = new JLabel("Max Price");
				left.add(maxPriceLbl, "cell 0 4,alignx left");
			}
			{
				maxPriceTxt = new JTextField();
				maxPriceTxt.setColumns(10);
				left.add(maxPriceTxt, "cell 1 4,growx");
			}
			{
				JLabel apartmentTypeLbl = new JLabel("Type");
				left.add(apartmentTypeLbl, "cell 0 5,alignx left");
			}
			{
				apartmentTypeTxt = new JComboBox();
				apartmentTypeTxt.setModel(new DefaultComboBoxModel(new String[] { "Single", "Family", "Suite" }));
				left.add(apartmentTypeTxt, "cell 1 5,growx");
			}
			{
				JLabel numberOfBedsLbl = new JLabel("Beds amount");
				left.add(numberOfBedsLbl, "cell 0 6,alignx left");
			}
			{
				bedsTxt = new JComboBox();
				bedsTxt.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
				left.add(bedsTxt, "cell 1 6,growx");
			}
			{
				JLabel floorNoLbl = new JLabel("Floor");
				left.add(floorNoLbl, "cell 0 7,alignx left");
			}
			{
				floorTxt = new JComboBox();
				floorTxt.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
				left.add(floorTxt, "cell 1 7,growx");
			}
			{
				JLabel balconyLbl = new JLabel("Balcony");
				left.add(balconyLbl, "cell 0 8,alignx left");
			}
			{
				balconyTxt = new JComboBox();
				balconyTxt.setModel(new DefaultComboBoxModel(new String[] { "Yes", "No" }));
				left.add(balconyTxt, "cell 1 8,growx");
			}
			{
				JPanel searchPane = new JPanel();
				FlowLayout flowLayout = (FlowLayout) searchPane.getLayout();
				flowLayout.setVgap(25);
				left.add(searchPane, "cell 0 9 2 1,growx,aligny top");
				{
					JButton btnSearch = new JButton("Search");
					btnSearch.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								rememberedCriteria();
								searchClicked();
							} catch (DataAccessException e1) {
								JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
							}
						}
					});
					btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
					searchPane.add(btnSearch);
				}
			}
		}
		{
			JPanel right = new JPanel();
			right.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			getContentPane().add(right, BorderLayout.EAST);
			right.setLayout(new MigLayout("", "[]", "[][][][][][][][][][][][][][][]"));
			{
				JButton startBookingButton = new JButton("<html>Start New<br />Booking</html>");
				startBookingButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				startBookingButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
				right.add(startBookingButton, "cell 0 0");
			}
			{
				JButton logOutButton = new JButton("Log Out");
				logOutButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
				right.add(logOutButton, "cell 0 14");
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				list = new JList();
				list.setFont(new Font("Tahoma", Font.PLAIN, 25));
				list.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				scrollPane.setViewportView(list);
			}
		}
	}

	private void searchClicked() throws DataAccessException {
		BookingController bookingController = new BookingController();
		List<Apartment> aList = new ArrayList<>();
		aList = bookingController.searchForApartments(dateStart, dateTo, minPriceDouble, maxPriceDouble, typeGet, bedsAmountGet, floorNoGet, hasBalcony);
		displayApartments(aList);
	}
	
	private void rememberedCriteria() {
		String dateStartGet = dateFromTxt.getText();
		dateStart = LocalDate.parse(dateStartGet);
		
		String dateToGet = dateToTxt.getText();
		dateTo = LocalDate.parse(dateToGet);
		
		String minPriceGet = minPriceTxt.getText();
		minPriceDouble = Double.parseDouble(minPriceGet);
		
		String maxPriceGet = maxPriceTxt.getText();
		maxPriceDouble = Double.parseDouble(maxPriceGet);
		
		typeGet = String.valueOf(apartmentTypeTxt.getSelectedItem());
		
		bedsAmountGet = Integer.valueOf(String.valueOf(bedsTxt.getSelectedItem()));
		
		floorNoGet = Integer.valueOf(String.valueOf(floorTxt.getSelectedItem()));
		
		String balconyGet = String.valueOf(balconyTxt.getSelectedItem());
		hasBalcony = isBoolean(balconyGet);
	}
	
	private void displayApartments(List<Apartment> aList) {
		list.setCellRenderer(new ApartmentsListCellRenderer());
		DefaultListModel<Apartment> dlm = new DefaultListModel<>();
		dlm.addAll(aList);
		list.setModel(dlm);
	}
	
	private boolean isBoolean(String s) {
		boolean res = false;
		if(s.equals("Yes")) {
			res = true;
		}
		return res;
	}

}
