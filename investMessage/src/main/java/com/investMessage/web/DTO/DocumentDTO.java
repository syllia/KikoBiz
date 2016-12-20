package com.investMessage.web.DTO;

import java.util.List;

import com.investMessage.domain.Document;

public class DocumentDTO {
	public String id;
	public String name;
	public byte[] content;
	public String date;
	public UserDTO creator;
	public List<DocumentDTO> documents;
	public String description;

	public DocumentDTO(String name, byte[] bytes, String date, UserDTO creator, String description) {

		this.name = name;
		this.date = date;
		this.creator = creator;
		this.content = bytes;
		this.description = description;
	}

	public DocumentDTO(Document document) {
		id = document.getId();
		name = document.getName();

		date = document.getDate();
		creator = new UserDTO(document.getCreator());
		description = document.getDescription();
	}

	public DocumentDTO(Document document, byte[] content) {
		id = document.getId();
		name = document.getName();
		this.content = content;
		date = document.getDate();
		creator = new UserDTO(document.getCreator());
		description = document.getDescription();

	}

	public String getName() {
		return name;
	}

	public byte[] getContent() {
		return content;
	}

	public String getDate() {
		return date;
	}

	public UserDTO getCreator() {
		return creator;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof DocumentDTO) {
			DocumentDTO documentDTO = (DocumentDTO) object;
			return (this.name.equals(documentDTO.name));
		} else {
			return false;
		}
	}

}
