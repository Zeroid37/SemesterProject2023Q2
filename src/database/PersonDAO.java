package database;

import model.Employee;
import model.Guest;

public interface PersonDAO {
	public Employee findEmployeeByEmail(String email);
	public boolean addGuestToDB(Guest guest);
}
