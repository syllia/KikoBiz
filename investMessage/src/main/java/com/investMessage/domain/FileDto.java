package com.investMessage.domain;

import java.util.ArrayList;
import java.util.List;

public class FileDto {
	public String id;
	public String name;
	public String date;
	public String user;
	public List<String> roles;
	public String description;

	public FileDto() {
		description = "";
		user = "";
		roles = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public String getUser() {
		return user;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getDescription() {
		return description;
	}

}
