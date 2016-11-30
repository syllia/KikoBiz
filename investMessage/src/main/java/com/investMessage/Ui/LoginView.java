package com.investMessage.Ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.investMessage.domain.Store;
import com.investMessage.domain.User;
import com.investMessage.services.CustomerIsAlreadyRegisteredException;
import com.investMessage.services.UserIsAlreadyRegisteredException;
import com.investMessage.services.UserService;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ErrorMessage;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SpringComponent
@UIScope
public class LoginView extends CustomComponent implements View, ClickListener {

	private UserInfo user;
	@Autowired
	private UserService userService;

	private TextField usernameField;
	private PasswordField passwordField;
	private Button loginButton;

	private Navigator navigator;

	@Autowired
	public LoginView(UserService service, UserInfo userInfo) {
		this.userService = service;
		this.user = userInfo;
	}

	@Override
	public void enter(ViewChangeEvent event) {

		navigator = getUI().getNavigator();
		usernameField = new TextField("Username");
		passwordField = new PasswordField("Password");
		loginButton = new Button("Login");
		loginButton.addClickListener(this);
		loginButton.setClickShortcut(KeyCode.ENTER);

		VerticalLayout layout = new VerticalLayout();
		setCompositionRoot(layout);
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);

		layout.addComponent(usernameField);
		layout.addComponent(passwordField);
		layout.addComponent(loginButton);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		String username = usernameField.getValue();
		String password = passwordField.getValue();
		try {
			userService.saveCustomer("firstName", "lastName", new Store("store"));
		} catch (CustomerIsAlreadyRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserIsAlreadyRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User loginUser = userService.getUserBy(username, password);
		if (loginUser == null) {
			new Notification("Wrong password", Notification.TYPE_ERROR_MESSAGE).show(getUI().getPage());
			return;
		}

		user.setUser(loginUser);

		if (navigator != null) {
			// navigator.navigateTo("hello");
		}
	}

	private static final class WrongPasswordErrorMessage implements ErrorMessage {

		@Override
		public ErrorLevel getErrorLevel() {
			return ErrorLevel.ERROR;
		}

		@Override
		public String getFormattedHtmlMessage() {
			return "Wrong password!";
		}
	}
}
