package com.investMessage.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;

public class FileClient {
	private DbxClientV2 client;

	public FileClient() throws DbxException {
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
		client = new DbxClientV2(config, "JP0mR0D-eyQAAAAAAAAy7kYMjWbEY8TtHYnXDPJBZOgv3w7Abpj4BQmvYsnvt-B4");

		// FullAccount account = client.users().getCurrentAccount();
		// System.out.println(account.getName().getDisplayName());
	}

	public ListFolderResult getFiles() throws ListFolderErrorException, DbxException {
		// Get files and folder metadata from Dropbox root directory
		ListFolderResult result = client.files().listFolder("");
		while (true) {
			for (Metadata metadata : result.getEntries()) {
				// metadata.newBuilder("").
				System.out.println(metadata.getPathLower());
			}

			if (!result.getHasMore()) {
				break;
			}
			result = client.files().listFolderContinue(result.getCursor());
		}
		return result;
	}

	void post(String filename) throws FileNotFoundException {
		InputStream in = new FileInputStream(filename);
		try {
			FileMetadata metadata = client.files().uploadBuilder("/test.txt").uploadAndFinish(in);
		} catch (UploadErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
