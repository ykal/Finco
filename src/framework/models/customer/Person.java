package framework.models.customer;

import java.time.LocalDate;

public class Person extends Customer implements IPerson {
	private LocalDate birthDate;
	private Company company;

	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}
