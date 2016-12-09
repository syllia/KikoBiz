package com.investMessage.Ui;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.springframework.stereotype.Component;

import com.vaadin.spring.server.SpringVaadinServlet;

@SuppressWarnings("serial")
@WebServlet(value = "/*", asyncSupported = true)
@Component("vaadinServlet")
public class DashboardServlet extends SpringVaadinServlet {

	@Override
	protected final void servletInitialized() throws ServletException {
		super.servletInitialized();
		getService().addSessionInitListener(new DashboardSessionInitListener());
	}
}