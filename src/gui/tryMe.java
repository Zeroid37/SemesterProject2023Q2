package gui;

import model.Address;
import model.Employee;
import model.Person;

public class tryMe {
	public static void main(String[] args) {
		Address a = new Address("Niggavej", "50", "8930", "RANDERS");
		Person p = new Employee("Smajo", "Omanovic", a, "123454678", "Mail@cancer.dk", 223, "Sales");
		
		System.out.println(p.getPassword());
		p.setPassword("1234");
		System.out.println(p.getPassword());
		
	}
}
