package com.investMessage.web.DTO;

import java.util.List;

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

	public DocumentDTO() {
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
