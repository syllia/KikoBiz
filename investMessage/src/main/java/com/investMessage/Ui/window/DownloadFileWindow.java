package com.investMessage.Ui.window;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.investMessage.Ui.DashboardUI;
import com.investMessage.Ui.event.DashboardEvent.CloseOpenWindowsEvent;
import com.investMessage.Ui.event.DashboardEventBus;
import com.investMessage.domain.FileDTO;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class DownloadFileWindow extends Window {

	public static final String ID = "downloadFileWindow";
	private String fileId;

	@PropertyId("FileName")
	private TextField filename;
	@PropertyId("Date")
	private TextField date;
	@PropertyId("Description")
	private TextField description;

	private ProgressBar bar;

	private DownloadFileWindow(final FileDTO fileDTO) {
		addStyleName("profile-window");
		setId(ID);
		Responsive.makeResponsive(this);
		fileId = fileDTO.id;
		setModal(true);
		setCloseShortcut(KeyCode.ESCAPE, null);
		setResizable(false);
		setClosable(false);
		setHeight(90.0f, Unit.PERCENTAGE);

		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setMargin(new MarginInfo(true, false, false, false));
		setContent(content);

		TabSheet detailsWrapper = new TabSheet();
		detailsWrapper.setSizeFull();
		detailsWrapper.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		detailsWrapper.addStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
		detailsWrapper.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
		content.addComponent(detailsWrapper);
		content.setExpandRatio(detailsWrapper, 1f);

		detailsWrapper.addComponent(buildProfileTab(fileDTO));

		content.addComponent(buildFooter(fileDTO));

	}

	private Component buildProfileTab(FileDTO fileDTO) {
		HorizontalLayout root = new HorizontalLayout();
		root.setCaption("Profile");
		root.setIcon(FontAwesome.USER);
		root.setWidth(100.0f, Unit.PERCENTAGE);
		root.setSpacing(true);
		root.setMargin(true);
		root.addStyleName("profile-form");

		VerticalLayout pic = new VerticalLayout();
		pic.setSizeUndefined();
		pic.setSpacing(true);
		Image profilePic = new Image(null, new ThemeResource("img/profile-pic-300px.jpg"));
		profilePic.setWidth(100.0f, Unit.PIXELS);
		pic.addComponent(profilePic);

		Button upload = new Button("Change…", new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show("Not implemented in this demo");
			}
		});
		upload.addStyleName(ValoTheme.BUTTON_TINY);
		pic.addComponent(upload);

		root.addComponent(pic);

		FormLayout details = new FormLayout();
		details.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		root.addComponent(details);
		root.setExpandRatio(details, 1);

		filename = new TextField("Nom du fichier");
		details.addComponent(filename);
		filename.setValue(fileDTO.name);

		date = new TextField("Date");
		details.addComponent(date);
		date.setValue(fileDTO.date);

		description = new TextField("Description");
		details.addComponent(description);
		description.setValue(fileDTO.description);

		Label section = new Label("Contact Info");
		section.addStyleName(ValoTheme.LABEL_H4);
		section.addStyleName(ValoTheme.LABEL_COLORED);
		details.addComponent(section);

		return root;
	}

	private Component buildFooter(FileDTO fileDTO) {
		HorizontalLayout footer = new HorizontalLayout();
		footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		footer.setWidth(100.0f, Unit.PERCENTAGE);

		Button downloadButton = createDownloadButton(fileDTO.name);
		downloadButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		Button cancel = new Button("Annuler");
		Button delete = new Button("Supprimer");
		downloadButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		downloadButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
			}
		});
		cancel.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});

		delete.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// delete file
			}
		});
		downloadButton.focus();
		cancel.focus();
		delete.focus();
		footer.addComponent(downloadButton);
		footer.addComponent(cancel);
		footer.addComponent(delete);
		footer.setComponentAlignment(delete, Alignment.TOP_LEFT);
		footer.setComponentAlignment(downloadButton, Alignment.TOP_RIGHT);
		footer.setComponentAlignment(cancel, Alignment.TOP_RIGHT);
		return footer;
	}

	private Button createDownloadButton(final String fileName) {
		final Button downloadButton = new Button("Télécharger le fichier");

		@SuppressWarnings("serial")
		StreamResource.StreamSource streamSource = new StreamResource.StreamSource() {
			public InputStream getStream() {
				try {
					return new ByteArrayInputStream(DashboardUI.getDataProvider().getFileFromId(fileId));
				} catch (FileDownloadFailure e) {
					Notification.show("Échec du téléchargement");
				}
				return null;
			}
		};

		if (streamSource != null) {
			StreamResource resource = new StreamResource(streamSource, fileName);
			FileDownloader fileDownloader = new FileDownloader(resource);
			fileDownloader.setOverrideContentType(false);
			fileDownloader.extend(downloadButton);
		}

		return downloadButton;
	}

	public static void open(final FileDTO fileDTO) {
		DashboardEventBus.post(new CloseOpenWindowsEvent());
		Window w = new DownloadFileWindow(fileDTO);
		UI.getCurrent().addWindow(w);
		w.focus();
	}

}
