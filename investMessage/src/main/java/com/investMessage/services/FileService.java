package com.investMessage.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.activation.MimetypesFileTypeMap;

import com.dropbox.core.DbxException;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.model.FileList;
import com.investMessage.domain.FileDto;
import com.investMessage.web.DTO.UserDTO;

public class FileService {
	private FileClient fileClient;
	private static final String parentId = "0B6zgJkAQOR7yNUFVaG5qcUhqblk";

	public FileService() {
		try {
			this.fileClient = new FileClient();
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FileService(FileClient fileClient) {
		this.fileClient = fileClient;
	}

	public List<FileDto> findByUser(UserDTO userDTO) throws DriveErrorException {
		List<FileDto> fileDtos = new ArrayList<>();

		try {
			this.fileClient = fileClient.getDriveService();
			FileList result = drive.files().list().setFields("nextPageToken, files(id,name,description,createdTime)")
					.execute();
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

	private List<FileDto> findListForUser(String username, List<FileDto> listDto, String role) {
		return listDto.stream().filter(e -> e.user.equals(username) || e.roles.contains(role))
				.collect(Collectors.toList());

	}

	private List<FileDto> createListFileDto(List<File> files) {
		List<FileDto> fileDtos = new ArrayList<>();
		for (File file : files) {
			try {
				fileDtos.add(split(file));
			} catch (DriveErrorException e) {
			}
		}
		return fileDtos;
	}

	private FileDto split(File file) throws DriveErrorException {
		FileDto fileDto = new FileDto();
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
			List<String> roles = Arrays.asList(elements.get(1).split(","));
			for (String string : roles) {
				fileDto.roles.add(string);
			}
			return fileDto;

		} else {
			// THROW
			return fileDto;
		}
	}

	public void insertFile(String title, String description, String filename) throws DriveErrorException {
		// File's metadata.
		try {
			this.drive = fileClient.getDriveService();
			File body = new File();
			body.setName(title);
			body.setDescription(description);

			// Set the parent folder.
			if (parentId != null && parentId.length() > 0) {
				body.setParents(Collections.singletonList(parentId));
			}
			MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

			// File's content.
			java.io.File fileContent = new java.io.File(filename);
			FileContent mediaContent = new FileContent(mimeTypesMap.getContentType(filename), fileContent);

			drive.files().create(body, mediaContent).execute();
		} catch (IOException e) {
			e.printStackTrace();
			throw new DriveErrorException();
		}

	}

}
