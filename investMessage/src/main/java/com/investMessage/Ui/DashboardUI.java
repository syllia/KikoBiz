package com.investMessage.Ui;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.Subscribe;
import com.investMessage.Ui.DashboardEvent.BrowserResizeEvent;
import com.investMessage.Ui.DashboardEvent.CloseOpenWindowsEvent;
import com.investMessage.Ui.DashboardEvent.UserLoggedOutEvent;
import com.investMessage.Ui.DashboardEvent.UserLoginRequestedEvent;
import com.investMessage.Ui.event.DashboardEventBus;
import com.investMessage.services.CustomerService;
import com.investMessage.services.UserService;
import com.investMessage.web.DTO.UserDTO;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.Page.BrowserWindowResizeEvent;
import com.vaadin.server.Page.BrowserWindowResizeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
@SpringUI
@Theme("dashboard")
@Title("InvestDrink Dashboard")
public class DashboardUI extends UI {

	private final DashboardEventBus dashboardEventbus = new DashboardEventBus();
	private DataProvider dataProvider;

	@Autowired
	public DashboardUI(UserService repo, CustomerService repoCustumer) {
		dataProvider = new DummyDataProvider(repo, repoCustumer);
	}

	@Override
	protected void init(final VaadinRequest request) {
		setLocale(Locale.US);

		DashboardEventBus.register(this);
		Responsive.makeResponsive(this);
		addStyleName(ValoTheme.UI_WITH_MENU);

		updateContent();

		// Some views need to be aware of browser resize events so a
		// BrowserResizeEvent gets fired to the event bus on every occasion.
		Page.getCurrent().addBrowserWindowResizeListener(new BrowserWindowResizeListener() {
			@Override
			public void browserWindowResized(final BrowserWindowResizeEvent event) {
				DashboardEventBus.post(new BrowserResizeEvent());
			}
		});
	}

	/**
	 * Updates the correct content for this UI based on the current user status.
	 * If the user is logged in with appropriate privileges, main view is shown.
	 * Otherwise login view is shown.
	 */
	private void updateContent() {
		UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
		if (user != null) {
			// Authenticated user
			setContent(new MainView());
			removeStyleName("loginview");
			getNavigator().navigateTo(getNavigator().getState());
		} else {
			setContent(new LoginView());
			addStyleName("loginview");
		}
	}

	@Subscribe
	public void userLoginRequested(final UserLoginRequestedEvent event) {
		UserDTO userDTO = getDataProvider().authenticate(event.getUserName(), event.getPassword());
		VaadinSession.getCurrent().setAttribute(UserDTO.class.getName(), userDTO);
		updateContent();
	}

	@Subscribe
	public void userLoggedOut(final UserLoggedOutEvent event) {
		// When the user logs out, current VaadinSession gets closed and the
		// page gets reloaded on the login screen. Do notice the this doesn't
		// invalidate the current HttpSession.
		VaadinSession.getCurrent().close();
		Page.getCurrent().reload();

	}

	@Subscribe
	public void closeOpenWindows(final CloseOpenWindowsEvent event) {
		for (Window window : getWindows()) {
			window.close();
		}
	}

	/**
	 * @return An instance for accessing the (dummy) services layer.
	 */
	public static DataProvider getDataProvider() {
		return ((DashboardUI) getCurrent()).dataProvider;
	}

	public static DashboardEventBus getDashboardEventbus() {
		return ((DashboardUI) getCurrent()).dashboardEventbus;
	}

}
