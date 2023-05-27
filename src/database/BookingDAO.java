package database;

import java.util.List;

import model.Booking;

public interface BookingDAO {
	public boolean addBookingToDB(Booking booking) throws DataAccessException;
	public boolean updateBookingInDB(Booking booking);
	public Booking findBookingByBookingNo(String bookingNo);
	public List<Booking> findBookingsByApartmentNo(String apartmentNo);
}
