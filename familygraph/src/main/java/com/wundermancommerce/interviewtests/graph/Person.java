package com.wundermancommerce.interviewtests.graph;

import java.util.Objects;

import lombok.Data;

@Data
public class Person {

	private String name;
	private String email;
	private int age;

	public Person(String name, String email, int age) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
	}
	
	
	   @Override
	    public int hashCode() {
	        return Objects.hashCode(this.email);
	    }
	 
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Person other = (Person) obj;
	        return Objects.equals(this.email, other.getEmail());
	    }

}
