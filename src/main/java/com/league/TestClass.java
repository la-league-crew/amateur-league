package com.league;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.league.entity.ClubRepresentation;

public class TestClass {

	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(new Person("e1", "l1"), new Person("e2", "l1"), new Person("e3", "l2"),
				new Person("e4", "l2"));

//		List<Employee> employees = persons.stream().filter(p -> p.getLastName().equals("l1"))
//				.map(p -> new Employee(p.getName(), p.getLastName(), 1000))
//				.collect(Collectors.toList());

		//System.out.println(employees);
		List<ClubRepresentation> reps = new ArrayList<>();
		int i = 0;
		List<Person> reps2;
		//persons.stream().map(ps -> ps.setId(10)).co; //forEach(p -> p.setId(i++));
		
	}

}

class Person {

	private Integer id;
	private String name;
	private String lastName;

	public Person(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}

class Employee extends Person {

	private double salary;

	public Employee(String name, String lastName, double salary) {
		super(name, lastName);
		this.salary = salary;
	}

	// Getter & Setter
}
