package com.investMessage.web.DTO;

public class DocumentDriveDTO {
	public String id;

	public byte[] content;

	public DocumentDriveDTO(String id, byte[] content) {
		super();
		this.id = id;
		this.content = content;
	}

}
