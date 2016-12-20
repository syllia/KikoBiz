package com.investMessage.services;

import java.io.IOException;

import com.investMessage.web.DTO.DocumentDTO;
import com.investMessage.web.DTO.DocumentDriveDTO;

public interface DocumentDataProvider {

	DocumentDriveDTO saveDocument(DocumentDTO documentDTO) throws IOException;

	DocumentDriveDTO findDocumentById(String documentId) throws IOException;

}
