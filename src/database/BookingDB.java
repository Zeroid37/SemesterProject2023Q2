package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Booking;

public class BookingDB implements BookingDAO {
	private static final String FIND_BY_BOOKING_NO = "select * from Booking where bookingNo = ?";
	private static final String FIND_BY_BOOKING_ID = "select * from Booking where id = ?";
	private static final String FIND_BOOKING_ID_BY_APARTMENT_NO = "select booking_id from ApartmentBooking where apartmentNo = ?";
	private static final String UPDATE_IS_DEPOSIT_PAID_BY_BOOKING_NO = "update Booking set isDepositPaid = ? where bookingNo = ?";
	private static final String INSERT_BOOKING_INTO_DB = "insert into Booking values (bookingNo, travelAgency, checkInDate, noOfNights,"
			+ "discount, isDepositPaid, activityQuantityToday, employeeNo_FK, guestNo_FK, price) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_APARTMENT_BOOKING_INTO_DB = "instert into ApartmentBooking (apartmentNo, bookingId) values (?, ?)";
	private PreparedStatement findByBookingNo, findByBookingId, findBookingIdByApartmentNo,
			updateIsDepositPaidByBookingNo, insertBookingIntoDB, insertApartmentBookingIntoDB;

	public BookingDB() throws DataAccessException {
		try {
			DBConnection dbc = DBConnection.getInstance();
			Connection con = dbc.getConnection();
			this.findByBookingNo = con.prepareStatement(FIND_BY_BOOKING_NO);
			this.findByBookingId = con.prepareStatement(FIND_BY_BOOKING_ID);
			this.findBookingIdByApartmentNo = con.prepareStatement(FIND_BOOKING_ID_BY_APARTMENT_NO);
			this.updateIsDepositPaidByBookingNo = con.prepareStatement(UPDATE_IS_DEPOSIT_PAID_BY_BOOKING_NO);
			this.insertBookingIntoDB = con.prepareStatement(INSERT_BOOKING_INTO_DB, PreparedStatement.RETURN_GENERATED_KEYS);
			this.insertApartmentBookingIntoDB = con.prepareStatement(INSERT_APARTMENT_BOOKING_INTO_DB);
		} catch (SQLException e) {
			throw new DataAccessException("Statements could not prepare.", e);
		}
	}

	@Override
	public boolean addBookingToDB(Booking booking) throws DataAccessException {
		boolean res = false;

		try {
			this.insertBookingIntoDB.setString(1, booking.getBookingNo());
			this.insertBookingIntoDB.setString(2, booking.getTravelAgency());
			this.insertBookingIntoDB.setDate(3, Date.valueOf(booking.getDateStart()));
			this.insertBookingIntoDB.setInt(4, booking.getNoOfNights());
			this.insertBookingIntoDB.setInt(5, booking.getDiscount());
			this.insertBookingIntoDB.setBoolean(6, booking.isDepositPaid());
			this.insertBookingIntoDB.setInt(7, booking.getActivityQuantityToday());
			this.insertBookingIntoDB.setInt(8, booking.getEmployee().getEmployeeNo());
			this.insertBookingIntoDB.setInt(9, booking.getGuest().getGuestNo());
			this.insertBookingIntoDB.setDouble(10, booking.getPrice());

			int bookingId = DBConnection.getInstance().executeInsertWithIdentity(insertBookingIntoDB);
			String apartmentNo = booking.getApartment().getApartmentNo();
			addApartmentBookingToDB(apartmentNo, bookingId);
			
			
			

		} catch (SQLException e) {
			throw new DataAccessException("Booking could not be saved.", e);
		}

		return res;
	}
	
	private void addApartmentBookingToDB(String apartmentNo, int bookingId) throws DataAccessException {
		try {
		this.insertApartmentBookingIntoDB.setString(1, apartmentNo);
		this.insertApartmentBookingIntoDB.setInt(2, bookingId);
		} catch (SQLException e) {
			throw new DataAccessException("Booking could not be saved.", e);
		}
	}

	@Override
	public boolean updateBookingInDB(Booking booking) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Booking findBookingByBookingNo(String bookingNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> findBookingsByApartmentNo(String apartmentNo) {
		List<Booking> bookings = new ArrayList<>();
		// TODO Auto-generated method stub
		return bookings;
	}

	private Booking buildObject(ResultSet rs) {
		Booking booking = new Booking(null, null, 0);

		// Build object

		return booking;
	}

	private List<Booking> buildObjects(ResultSet rs) {
		List<Booking> bookings = new ArrayList<>();

		// Build objects

		return bookings;
	}
}
