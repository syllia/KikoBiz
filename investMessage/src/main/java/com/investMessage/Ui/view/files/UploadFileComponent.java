package com.investMessage.Ui.view.files;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.investMessage.Ui.window.UploadFileWindow;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

public class UploadFileComponent extends CustomComponent {
	private static final long serialVersionUID = -4292553844521293140L;

	public void init(String context) {
		VerticalLayout layout = new VerticalLayout();

		if ("advanced".equals(context))
			advanced(layout);
		else
			layout.addComponent(new Label("Invalid context: " + context));
		setCompositionRoot(layout);
	}

	void advanced(VerticalLayout layout) {
		// BEGIN-EXAMPLE: component.upload.advanced
		class UploadBox extends CustomComponent
				implements Receiver, ProgressListener, FailedListener, SucceededListener {
			private static final long serialVersionUID = -46336015006190050L;

			// Put upload in this memory buffer that grows automatically
			ByteArrayOutputStream os = new ByteArrayOutputStream(10240);

			// Name of the uploaded file
			String filename;

			ProgressBar progress = new ProgressBar(0.0f);

			// Show uploaded file in this placeholder
			// Image image = new Image("Uploaded Image");

			public UploadBox() {
				// Create the upload component and handle all its events
				Upload upload = new Upload("Upload the image here", null);
				upload.setReceiver(this);
				upload.addProgressListener(this);
				upload.addFailedListener(this);
				upload.addSucceededListener(this);

				// Put the upload and image display in a panel
				Panel panel = new Panel("Cool Image Storage");
				panel.setWidth("400px");
				VerticalLayout panelContent = new VerticalLayout();
				panelContent.setSpacing(true);
				panel.setContent(panelContent);
				panelContent.addComponent(upload);
				panelContent.addComponent(progress);
				// panelContent.addComponent(image);

				progress.setVisible(false);
				// image.setVisible(false);

				setCompositionRoot(panel);
			}

			public OutputStream receiveUpload(String filename, String mimeType) {
				this.filename = filename;
				os.reset(); // Needed to allow re-uploading
				return os;
			}

			@Override
			public void updateProgress(long readBytes, long contentLength) {
				progress.setVisible(true);
				if (contentLength == -1)
					progress.setIndeterminate(true);
				else {
					progress.setIndeterminate(false);
					progress.setValue(((float) readBytes) / ((float) contentLength));
				}
			}

			public void uploadSucceeded(SucceededEvent event) {
				FileOutputStream fos;

				os.toByteArray();
				UploadFileWindow.filename = filename;
				UploadFileWindow.bytes = os.toByteArray();
				;
				/*
				 * try { IOUtils.copy(new
				 * ByteArrayInputStream(os.toByteArray()), new
				 * FileOutputStream(filename));
				 * 
				 * } catch (IOException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); } // FileInputStream inputStream = new
				 * // ByteArrayInputStream(os.toByteArray(); StreamSource source
				 * = new StreamSource() { private static final long
				 * serialVersionUID = -4905654404647215809L;
				 * 
				 * public InputStream getStream() { // return new
				 * ByteArrayInputStream(os.toByteArray()); } };
				 * 
				 * 
				 * File file = new StreamResource(source, filename); //if
				 * (image.getSource() == null) // Create a new stream resource
				 * image.setSource(); //else { // Reuse the old resource
				 * StreamResource resource = (StreamResource) image.getSource();
				 * resource.setStreamSource(source);
				 * resource.setFilename(filename); }
				 * 
				 * 
				 * // image.markAsDirty();
				 */ }

			@Override
			public void uploadFailed(FailedEvent event) {
				Notification.show("Upload failed", Notification.Type.ERROR_MESSAGE);
			}
		}

		UploadBox uploadbox = new UploadBox();
		layout.addComponent(uploadbox);
		// END-EXAMPLE: component.upload.advanced
	}
}
