package database;

import model.Booking;

public interface BookingDAO {
	public boolean addBookingToDB(Booking booking);
	public boolean updateBookingInDB(Booking booking);
	public Booking findBookingByBookingNo(String bookingNo);
}
