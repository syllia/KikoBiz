package com.kouri.VaadinUI.principalUi;

import org.springframework.beans.factory.annotation.Autowired;

import com.kouri.VaadinUI.servicesOffer.ServiceOffer;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class PrincipalUi extends UI {
	private Button validate;
	private ServiceOffer service;

	@Autowired
	public PrincipalUi(ServiceOffer service) {
		this.service = service;
	}

	@Override
	protected void init(VaadinRequest request) {
		this.validate = new Button("Se connecter", FontAwesome.SIGN_IN);
		VerticalLayout login = new VerticalLayout(validate);
		setContent(login);

		validate.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
service.postCategory();
			}
		});
	}

}
