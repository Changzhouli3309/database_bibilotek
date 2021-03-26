package model;

public class Employee {
	private int id;
	private String name;
	private String adress;
	private int tel1;
	private int tel2;
	private int tel3;
	private int salary;
	private int holidayLeft;

	public Employee(int id, String name, String adress, int tel1, int tel2, int tel3, int salary, int holidayLeft) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
		this.salary = salary;
		this.holidayLeft = holidayLeft;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getTel1() {
		return tel1;
	}

	public void setTel1(int tel1) {
		this.tel1 = tel1;
	}

	public int getTel2() {
		return tel2;
	}

	public void setTel2(int tel2) {
		this.tel2 = tel2;
	}

	public int getTel3() {
		return tel3;
	}

	public void setTel3(int tel3) {
		this.tel3 = tel3;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getHolidayLeft() {
		return holidayLeft;
	}

	public void setHolidayLeft(int holidayLeft) {
		this.holidayLeft = holidayLeft;
	}

}
