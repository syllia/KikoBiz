package com.investMessage.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investMessage.domain.Document;
import com.investMessage.domain.DocumentRepository;
import com.investMessage.domain.User;
import com.investMessage.web.DTO.DocumentDTO;
import com.investMessage.web.DTO.DocumentDriveDTO;

@Service
public class DocumentService {
	@Autowired
	private DocumentRepository documentRepository;
	private DocumentDataProvider documentDataProvider;

	public DocumentService() {
		this.documentDataProvider = new GoogleDriveDocumentDataProvider();
	}

	public DocumentService(DocumentRepository documentRepository, DocumentDataProvider documentDataProvider) {
		this.documentRepository = documentRepository;
		this.documentDataProvider = documentDataProvider;

	}

	public DocumentDTO findDocumentById(String documentId) throws DocumentNotFoundException {
		DocumentDriveDTO documentDriveDTO;
		try {
			documentDriveDTO = documentDataProvider.findDocumentById(documentId);
			return new DocumentDTO(documentRepository.findOne(documentDriveDTO.id), documentDriveDTO.content);
		} catch (IOException e) {
			throw new DocumentNotFoundException();
		}

	}

	public void saveDocument(DocumentDTO documentDTO) throws DriveErrorException {
		DocumentDriveDTO savedDocument;
		try {
			savedDocument = documentDataProvider.saveDocument(documentDTO);
		} catch (IOException e) {
			throw new DriveErrorException();
		}
		User user = new User(documentDTO.creator.userName, documentDTO.creator.lastName, documentDTO.creator.firstName,
				documentDTO.creator.passWord, documentDTO.creator.emailAddress, documentDTO.creator.phoneNumber,
				documentDTO.creator.documents);
		Document document = new Document(savedDocument.id, documentDTO.name, documentDTO.date, user,
				documentDTO.description);
		documentRepository.save(document);
	}

}
