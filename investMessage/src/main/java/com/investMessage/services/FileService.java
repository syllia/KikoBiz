package com.investMessage.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.activation.MimetypesFileTypeMap;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.investMessage.Ui.window.FileDownloadFailure;
import com.investMessage.domain.FileDTO;
import com.investMessage.web.DTO.UserDTO;

public class FileService {
	private Drive drive;
	private FileClient fileClient;
	private static final String parentId = "0B6zgJkAQOR7yNUFVaG5qcUhqblk";

	public FileService() {
		this.fileClient = new FileClient();
	}

	public FileService(FileClient fileClient) {
		this.fileClient = fileClient;
	}

	public List<FileDTO> findByUser(UserDTO userDTO) throws DriveErrorException {
		List<FileDTO> fileDtos = new ArrayList<>();

		try {
			this.drive = fileClient.getDriveService();
			FileList result = drive.files().list().setQ("'" + parentId + "' in parents")
					.setFields("nextPageToken, files(id,name,description,createdTime)").execute();
			List<File> files = result.getFiles();
			if (files != null) {
				fileDtos = createListFileDto(files);
			}

			// return findListForUser(userDTO.userName, fileDtos, userDTO.role);

			return fileDtos;

		} catch (IOException e) {
			throw new DriveErrorException();
		}
	}

	private List<FileDTO> findListForUser(String username, List<FileDTO> listDto) {
		return listDto.stream().filter(e -> e.user.equals(username)).collect(Collectors.toList());

	}

	private List<FileDTO> createListFileDto(List<File> files) {
		List<FileDTO> fileDtos = new ArrayList<>();
		for (File file : files) {
			try {
				fileDtos.add(split(file));
			} catch (DriveErrorException e) {
			}
		}
		return fileDtos;
	}

	public byte[] getFileStreamById(String fileId) throws FileDownloadFailure {
		OutputStream outputStream = new ByteArrayOutputStream();
		try {
			this.drive = fileClient.getDriveService();
			drive.files().get(fileId).executeMediaAndDownloadTo(outputStream);
		} catch (IOException e) {
			throw new FileDownloadFailure();
		}
		return ((ByteArrayOutputStream) outputStream).toByteArray();
	}

	private FileDTO split(File file) throws DriveErrorException {
		FileDTO fileDto = new FileDTO();
		fileDto.name = file.getName();
		fileDto.id = file.getId();

		fileDto.date = file.getCreatedTime().toString();
		List<String> elements = new ArrayList<>();
		if (file.getDescription() != null) {
			elements = Arrays.asList(file.getDescription().split("/"));
		}

		if (elements.size() == 3) {
			fileDto.user = elements.get(0);
			fileDto.description = elements.get(1);
			List<String> roles = Arrays.asList(elements.get(2).split(","));
			for (String string : roles) {
				fileDto.roles.add(string);
			}
			return fileDto;

		} else {
			// THROW
			return fileDto;
		}
	}

	private String formatDescription(String username, String fileTitle, List<String> stores) {
		String description = "";
		description += username + "/" + fileTitle + "/";
		for (String store : stores) {
			description += store + ",";
		}
		description = description.substring(0, description.length() - 1);
		return description;
	}

	public void insertFile(String title, String description, String filename) throws DriveErrorException {
		// File's metadata.
		File body = new File();
		body.setName(title);
		body.setDescription(description);

		// Set the parent folder.
		if (parentId != null && parentId.length() > 0) {
			body.setParents(Arrays.asList(parentId));
		}
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

		// File's content.
		java.io.File fileContent = new java.io.File(filename);
		FileContent mediaContent = new FileContent(mimeTypesMap.getContentType(filename), fileContent);

		try {
			drive.files().create(body, mediaContent).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
