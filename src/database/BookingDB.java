package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import controller.LoginController;
import model.Booking;

public class BookingDB implements BookingDAO {
	private static final String FIND_BY_BOOKING_NO = "select * from Booking where bookingNo = ?";
	private static final String FIND_BY_BOOKING_ID = "select * from Booking where id = ?";
	private static final String FIND_BOOKING_ID_BY_APARTMENT_NO = "select bookingId from ApartmentBooking where apartmentNo = ?";
	private static final String FIND_APARTMENT_NO_BY_BOOKING_ID = "select apartmentNo from ApartmentBooking where bookingId = ?";
	private static final String UPDATE_IS_DEPOSIT_PAID_BY_BOOKING_NO = "update Booking set isDepositPaid = ? where bookingNo = ?";
	private static final String INSERT_BOOKING_INTO_DB = "insert into Booking (bookingNo, travelAgency, checkInDate, noOfNights,"
			+ "discount, isDepositPaid, activityQuantityToday, employeeNo_FK, guestNo_FK, price) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_APARTMENT_BOOKING_INTO_DB = "insert into ApartmentBooking (apartmentNo, bookingId) values (?, ?)";
	private PreparedStatement findByBookingNo, findByBookingId, findBookingIdByApartmentNo, findApartmentNoByBookingId,
			updateIsDepositPaidByBookingNo, insertBookingIntoDB, insertApartmentBookingIntoDB;

	public BookingDB() throws DataAccessException {
		try {
			DBConnection dbc = DBConnection.getInstance();
			Connection con = dbc.getConnection();
			this.findByBookingNo = con.prepareStatement(FIND_BY_BOOKING_NO);
			this.findByBookingId = con.prepareStatement(FIND_BY_BOOKING_ID);
			this.findBookingIdByApartmentNo = con.prepareStatement(FIND_BOOKING_ID_BY_APARTMENT_NO);
			this.findApartmentNoByBookingId = con.prepareStatement(FIND_APARTMENT_NO_BY_BOOKING_ID);
			this.updateIsDepositPaidByBookingNo = con.prepareStatement(UPDATE_IS_DEPOSIT_PAID_BY_BOOKING_NO);
			this.insertBookingIntoDB = con.prepareStatement(INSERT_BOOKING_INTO_DB,
					PreparedStatement.RETURN_GENERATED_KEYS);
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
			res = true;
		} catch (SQLException e) {
			throw new DataAccessException("Booking could not be saved.", e);
		}

		return res;
	}

	private void addApartmentBookingToDB(String apartmentNo, int bookingId) throws DataAccessException {
		try {
			this.insertApartmentBookingIntoDB.setString(1, apartmentNo);
			this.insertApartmentBookingIntoDB.setInt(2, bookingId);
			this.insertApartmentBookingIntoDB.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Booking could not be saved.", e);
		}
	}

	@Override
	public boolean updateBookingInDB(Booking booking) throws DataAccessException {
		boolean res = false;
		try {
			this.updateIsDepositPaidByBookingNo.setBoolean(1, booking.isDepositPaid());
			this.updateIsDepositPaidByBookingNo.setString(2, booking.getBookingNo());
			this.updateIsDepositPaidByBookingNo.executeUpdate();
			res = true;
		} catch (SQLException e) {
			throw new DataAccessException("Couldn't update deposit paid state.", e);
		}
		return res;
	}

	@Override
	public Booking findBookingByBookingNo(String bookingNo) throws DataAccessException {
		Booking b = null;
		try {
			this.findByBookingNo.setString(1, bookingNo);
			ResultSet rsBooking = this.findByBookingNo.executeQuery();
			if (rsBooking.next()) {
				this.findApartmentNoByBookingId.setInt(1, rsBooking.getInt("id"));
				ResultSet rsApartmentBooking = this.findApartmentNoByBookingId.executeQuery();
				if (rsApartmentBooking.next()) {
					b = buildObject(rsBooking, rsApartmentBooking);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Couldn't find booking.", e);
		}
		return b;
	}

	@Override
	public List<Booking> findBookingsByApartmentNo(String apartmentNo) throws DataAccessException {
		List<Booking> bookings = new ArrayList<>();

		try {
			this.findBookingIdByApartmentNo.setString(1, apartmentNo);
			ResultSet rs = this.findBookingIdByApartmentNo.executeQuery();
			bookings = buildObjects(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Couldn't find bookings", e);
		}

		return bookings;
	}

	private ResultSet findByBookingId(int id) throws DataAccessException {
		ResultSet rs = null;
		try {
			this.findByBookingId.setInt(1, id);
			rs = this.findByBookingId.executeQuery();
		} catch (SQLException e) {
			throw new DataAccessException("Couldn't find booking", e);
		}

		return rs;
	}

	private Booking buildObject(ResultSet rsBooking, ResultSet rsApartmentBooking) throws DataAccessException {
		Booking b = null;
		try {
			Date date = rsBooking.getDate("checkInDate");
			LocalDate dateStart = date.toLocalDate();
			b = new Booking(rsBooking.getString("bookingNo"), rsBooking.getString("travelAgency"), dateStart,
					rsBooking.getInt("noOfNights"), rsBooking.getInt("discount"), rsBooking.getBoolean("isDepositPaid"),
					rsBooking.getInt("activityQuantityToday"), rsBooking.getDouble("price"));

			ApartmentDAO apartmentDAO = new ApartmentDB();
			b.setApartment(apartmentDAO.findApartmentByApartmentNo(rsApartmentBooking.getString("apartmentNo")));
			PersonDAO personDAO = new PersonDB();
			b.setGuest(personDAO.findGuestByGuestNo(rsBooking.getInt("guestNo_FK")));
			LoginController loginController = new LoginController();
			b.setEmployee(loginController.getCurrEmployee());
		} catch (SQLException e) {
			throw new DataAccessException("Couldn't build booking.", e);
		}

		return b;
	}

	private List<Booking> buildObjects(ResultSet rs) throws DataAccessException {
		List<Booking> bookings = new ArrayList<>();
		try {
			while (rs.next()) {
				Booking b = null;
				ResultSet rsBooking = findByBookingId(rs.getInt("bookingId"));
				rsBooking.next();
				this.findApartmentNoByBookingId.setInt(1, rsBooking.getInt("id"));
				ResultSet rsApartmentBooking = this.findApartmentNoByBookingId.executeQuery();
				if (rsApartmentBooking.next()) {
					b = buildObject(rsBooking, rsApartmentBooking);
					bookings.add(b);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Couldn't build booking.", e);
		}
		return bookings;
	}
}
