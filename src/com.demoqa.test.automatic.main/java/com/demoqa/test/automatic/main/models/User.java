package com.demoqa.test.automatic.main.models;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String firstName;
	private String lastName;
	private String completeName;
	private String username;
	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompleteName() {
		return completeName;
	}

	private User(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 *
	 * @param names List of provided input names
	 * @return Required not random data for an user(first name, last name) and a String with the whole name
	 */
	public static List<User> returnListOfUsers(String[] names){
		List<User> res=new ArrayList<User>();
		String firstName,lastName;
		String lowerCase="abcdefghijklmnopqrstuvwxyz&";
		for(String name:names){
			String[]nameParts=name.split(" ");
			firstName=nameParts[0]; //We suppose first part of the name is with upper case
			int indexInsideName=1;
			while(lowerCase.contains(nameParts[indexInsideName].charAt(0)+""))
			{
				indexInsideName++;
			}
			lastName=nameParts[indexInsideName];
			User user=new User(firstName,lastName);
			user.completeName=name;
			res.add(user);
		}
		return res;
	}
	
		
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


	public boolean equals(Object u){
		if(u instanceof User){
			User user=(User)u;
			return (user.firstName.equals(firstName) && user.lastName.equals(lastName))|| (user.username.equals(username));
		}else
			return false; 
	}
	
}
