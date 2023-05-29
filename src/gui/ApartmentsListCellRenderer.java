package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Apartment;

public class ApartmentsListCellRenderer implements ListCellRenderer<Apartment> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Apartment> list, Apartment value, int index,
			boolean isSelected, boolean cellHasFocus) {
		String res = "No: " + value.getApartmentNo() + " - Price per night: $" + value.getPricePerNight(); 
		return new DefaultListCellRenderer().getListCellRendererComponent(list, res, index, isSelected, cellHasFocus);
	}
}
