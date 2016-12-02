package com.investMessage.Ui;

import com.investMessage.Ui.DashboardEvent.CloseOpenWindowsEvent;
import com.investMessage.Ui.event.DashboardEventBus;
import com.investMessage.services.DriveErrorException;
import com.investMessage.web.DTO.UserDTO;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class DownloadFileWindow extends Window {

	public static final String ID = "downloadFileWindow";

	@PropertyId("description")
	private TextField description;
	private OptionGroup multi;
	public static String filename = "";

	private UploadFileComponent uploadFileView;

	private DownloadFileWindow(final UserDTO user, final boolean preferencesTabOpen) {
		addStyleName("profile-window");
		setId(ID);
		Responsive.makeResponsive(this);

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

		detailsWrapper.addComponent(buildProfileTab());

		if (preferencesTabOpen) {
			detailsWrapper.setSelectedTab(1);
		}
		content.addComponent(buildFooter(user));

	}

	private Component buildProfileTab() {
		HorizontalLayout root = new HorizontalLayout();
		root.setCaption("Téléchargement");
		root.setIcon(FontAwesome.FILE);
		root.setWidth(100.0f, Unit.PERCENTAGE);
		root.setSpacing(true);
		root.setMargin(true);
		root.addStyleName("profile-form");

		// VerticalLayout pic = new VerticalLayout();
		// pic.setSizeUndefined();
		// pic.setSpacing(true);
		// Image profilePic = new Image(null, new
		// ThemeResource("img/profile-pic-300px.jpg"));
		// profilePic.setWidth(100.0f, Unit.PIXELS);
		// pic.addComponent(profilePic);

		// Button upload = new Button("Change…", new ClickListener() {
		// @Override
		// public void buttonClick(ClickEvent event) {
		// Notification.show("Not implemented in this demo");
		// }
		// });
		// upload.addStyleName(ValoTheme.BUTTON_TINY);
		// pic.addComponent(upload);

		// root.addComponent(pic);

		FormLayout details = new FormLayout();
		details.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		root.addComponent(details);
		root.setExpandRatio(details, 1);

		description = new TextField("Description du fichier");
		details.addComponent(description);

		Label section = new Label("Visibilité");
		section.addStyleName(ValoTheme.LABEL_H4);
		section.addStyleName(ValoTheme.LABEL_COLORED);
		details.addComponent(section);

		multi = new OptionGroup("Multiple Selection");
		// multi.setRequired(true);
		// multi.addItems("Privé", "Public");

		uploadFileView = new UploadFileComponent();
		uploadFileView.init("advanced");
		uploadFileView.setVisible(false);
		details.addComponent(uploadFileView);

		// section = new Label("Additional Info");
		// section.addStyleName(ValoTheme.LABEL_H4);
		// section.addStyleName(ValoTheme.LABEL_COLORED);
		// details.addComponent(section);
		return root;
	}

	private Component buildFooter(UserDTO user) {
		HorizontalLayout footer = new HorizontalLayout();
		footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		footer.setWidth(100.0f, Unit.PERCENTAGE);

		Button ok = new Button("Ajouter fichier");
		ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
		Button cancel = new Button("ANNULER");
		ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					// if info valide

					uploadFileView.setVisible(true);
					if (!filename.isEmpty()) {
						DashboardUI.getDataProvider().post(filename, "lola", filename);
					} else {
						throw new DriveErrorException();
					}

					Notification success = new Notification("Fichier télécharger");
					success.setDelayMsec(2000);
					success.setStyleName("bar success small");
					success.setPosition(Position.BOTTOM_CENTER);
					success.show(Page.getCurrent());
					filename = "";
					close();

				} catch (DriveErrorException e) {
					Notification.show("Erreur fichier non télécharger", Type.ERROR_MESSAGE);
				}

			}
		});
		cancel.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});
		ok.focus();
		cancel.focus();
		footer.addComponent(ok);
		footer.addComponent(cancel);
		footer.setComponentAlignment(ok, Alignment.TOP_RIGHT);
		footer.setComponentAlignment(cancel, Alignment.TOP_RIGHT);
		return footer;
	}

	public static void open(final UserDTO user, final boolean preferencesTabActive) {
		DashboardEventBus.post(new CloseOpenWindowsEvent());
		Window w = new DownloadFileWindow(user, preferencesTabActive);
		UI.getCurrent().addWindow(w);
		w.focus();
	}
}