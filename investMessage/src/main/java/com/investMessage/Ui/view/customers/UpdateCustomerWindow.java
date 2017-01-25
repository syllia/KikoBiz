package com.investMessage.Ui.view.customers;

import com.investMessage.Ui.DashboardUI;
import com.investMessage.Ui.DataEmptyException;
import com.investMessage.Ui.event.DashboardEvent.CloseOpenWindowsEvent;
import com.investMessage.Ui.event.DashboardEventBus;
import com.investMessage.services.CustomerNotFoundException;
import com.investMessage.web.DTO.CustomerDTO;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class UpdateCustomerWindow extends Window {

	public static final String ID = "CreateCustomerWindow";

	@PropertyId("Name")
	private TextField name;
	@PropertyId("number")
	private TextField number;

	private ProgressBar bar;

	private UpdateCustomerWindow(final CustomerDTO customerDTO) {
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

		detailsWrapper.addComponent(buildProfileTab(customerDTO));

		content.addComponent(buildFooter(customerDTO));

	}

	private Component buildProfileTab(CustomerDTO customerDTO) {
		HorizontalLayout root = new HorizontalLayout();
		root.setCaption("Créer un client");
		root.setIcon(FontAwesome.FILE);
		root.setWidth(100.0f, Unit.PERCENTAGE);
		root.setSpacing(true);
		root.setMargin(true);
		root.addStyleName("profile-form");

		FormLayout details = new FormLayout();
		HorizontalLayout visibility = new HorizontalLayout();
		details.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		root.addComponent(details);
		root.setExpandRatio(details, 1);

		name = new TextField("Nom du client");
		name.setValue(customerDTO.name);
		number = new TextField("Numero du client");
		number.setValue(customerDTO.number);

		details.addComponent(name);
		details.addComponent(number);

		// Label section = new Label("Visibilité");
		// section.addStyleName(ValoTheme.LABEL_H4);
		// section.addStyleName(ValoTheme.LABEL_COLORED);
		//
		// details.addComponent(section);
		// bar = new ProgressBar();
		// bar.setIndeterminate(true);
		// bar.setVisible(false);

		details.addComponent(visibility);
		return root;
	}

	private Component buildFooter(CustomerDTO customerDTO) {
		HorizontalLayout footer = new HorizontalLayout();
		footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		footer.setWidth(100.0f, Unit.PERCENTAGE);

		Button achat = new Button("Enrégistrer achat", FontAwesome.SEND);
		Button delete = new Button("Supprimer", FontAwesome.TRASH_O);

		Button ok = new Button("Sauvegarder", FontAwesome.SAVE);
		ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
		Button cancel = new Button("Annuler");
		ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {

				try {

					if (name.isValid() || number.isValid()) {
						customerDTO.name = name.getValue();
						customerDTO.number = number.getValue();
						DashboardUI.getDataProvider().updateClient(customerDTO);
					} else {
						throw new DataEmptyException("Informations du client incorectes");
					}

					// bar.setVisible(false);
					Notification success = new Notification("Fichier téléchargé");
					success.setDelayMsec(2000);
					success.setStyleName("bar success small");
					success.setPosition(Position.BOTTOM_CENTER);
					success.show(Page.getCurrent());
					close();
					Page.getCurrent().reload();

				} catch (DataEmptyException | CustomerNotFoundException e) {
					Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
				}

			}
		});
		cancel.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				close();

			}
		});
		achat.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					DashboardUI.getDataProvider().purchaseClient(customerDTO);
					Notification success = new Notification("Message envoyé");
					success.setDelayMsec(2000);
					success.setStyleName("bar success small");
					success.setPosition(Position.BOTTOM_CENTER);
					success.show(Page.getCurrent());

					close();
					Page.getCurrent().reload();
				} catch (CustomerNotFoundException e) {
					Notification success = new Notification("Message non envoyé");
					success.setDelayMsec(2000);
					success.setStyleName("bar success small");
					success.setPosition(Position.BOTTOM_CENTER);
					success.show(Page.getCurrent());
				}
			}
		});

		delete.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					DashboardUI.getDataProvider().deleteClient(customerDTO);
					Notification success = new Notification("Client supprimé");
					success.setDelayMsec(2000);
					success.setStyleName("bar success small");
					success.setPosition(Position.BOTTOM_CENTER);
					success.show(Page.getCurrent());
					close();
				} catch (CustomerNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		ok.focus();
		cancel.focus();
		footer.addComponent(ok);
		footer.addComponent(cancel);
		footer.addComponent(achat);
		footer.setComponentAlignment(ok, Alignment.TOP_RIGHT);
		footer.setComponentAlignment(cancel, Alignment.TOP_RIGHT);
		footer.setComponentAlignment(achat, Alignment.TOP_RIGHT);
		// footer.setComponentAlignment(delete, Alignment.TOP_RIGHT);
		return footer;
	}

	public static void open(final CustomerDTO customerDTO) {
		DashboardEventBus.post(new CloseOpenWindowsEvent());
		Window w = new UpdateCustomerWindow(customerDTO);
		UI.getCurrent().addWindow(w);
		w.focus();
	}
}