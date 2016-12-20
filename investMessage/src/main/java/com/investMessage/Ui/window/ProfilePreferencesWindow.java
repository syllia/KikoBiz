package com.investMessage.Ui.window;

import com.investMessage.Ui.event.DashboardEvent.CloseOpenWindowsEvent;
import com.investMessage.Ui.event.DashboardEventBus;
import com.investMessage.web.DTO.UserDTO;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
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
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class ProfilePreferencesWindow extends Window {

	public static final String ID = "profilepreferenceswindow";

	// private final BeanFieldGroup<UserDTO> fieldGroup;
	/*
	 * Fields for editing the User object are defined here as class members.
	 * They are later bound to a FieldGroup by calling
	 * fieldGroup.bindMemberFields(this). The Fields' values don't need to be
	 * explicitly set, calling fieldGroup.setItemDataSource(user) synchronizes
	 * the fields with the user object.
	 */
	@PropertyId("actuPassWordField")
	private TextField actuPassWordField;
	@PropertyId("passWordField")
	private TextField passWordField;
	@PropertyId("newPassWordField")
	private TextField newPassWordField;
	@PropertyId("emailAddress")
	private TextField emailField;
	@PropertyId("phoneNumber")
	private TextField phoneField;

	private ProfilePreferencesWindow(final UserDTO user, final boolean preferencesTabOpen) {
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
		actuPassWordField.setValue("");
		passWordField.setValue("");
		newPassWordField.setValue("");
		emailField.setValue(user.emailAddress);
		phoneField.setValue(user.phoneNumber);

		content.addComponent(buildFooter(user));

	}

	private Component buildProfileTab() {
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

		actuPassWordField = new TextField("Tapez votre mot de passe");
		details.addComponent(actuPassWordField);

		passWordField = new TextField("Nouveau mot de passe");
		details.addComponent(passWordField);

		newPassWordField = new TextField("Retapez votre nouveau mot de passe");
		details.addComponent(newPassWordField);

		Label section = new Label("Contact Info");
		section.addStyleName(ValoTheme.LABEL_H4);
		section.addStyleName(ValoTheme.LABEL_COLORED);
		details.addComponent(section);

		emailField = new TextField("Email");
		emailField.setWidth("100%");
		emailField.setRequired(true);
		details.addComponent(emailField);

		phoneField = new TextField("Phone");
		phoneField.setWidth("100%");
		details.addComponent(phoneField);

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

		Button ok = new Button("OK");
		ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
		Button cancel = new Button("ANNULER");
		ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// try {
				// if (!actuPassWordField.getValue().equals("") ||
				// !actuPassWordField.getValue().equals("")
				// || !newPassWordField.getValue().equals(""))
				// if (actuPassWordField.getValue().equals(user.passWord)) {
				// if
				// (passWordField.getValue().equals(newPassWordField.getValue())
				// && !passWordField.isEmpty()) {
				// user.passWord = passWordField.getValue();
				// } else {
				// throw new UserPasswordException();
				// }
				//
				// } else {
				// throw new UserPasswordException();
				// }
				//
				// user.emailAddress = emailField.getValue();
				// user.phoneNumber = phoneField.getValue();
				//
				// // DashboardUI.getDataProvider().saveUser(user);
				//
				// Notification success = new Notification("Profile updated
				// successfully");
				// success.setDelayMsec(2000);
				// success.setStyleName("bar success small");
				// success.setPosition(Position.BOTTOM_CENTER);
				// success.show(Page.getCurrent());
				//
				// DashboardEventBus.post(new ProfileUpdatedEvent());
				// close();
				// } catch (UserPasswordException | UserNotFoundException |
				// StoreNotFoundException e) {
				// Notification.show("Error while updating profile",
				// Type.ERROR_MESSAGE);
				// }

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
		Window w = new ProfilePreferencesWindow(user, preferencesTabActive);
		UI.getCurrent().addWindow(w);
		w.focus();
	}
}