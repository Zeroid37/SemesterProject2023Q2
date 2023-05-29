package database;

import model.Guest;

public interface PersonDAO {
	public boolean addGuestToDB(Guest guest) throws DataAccessException;
	public Guest findGuestByGuestNo(int guestNo) throws DataAccessException;
}
