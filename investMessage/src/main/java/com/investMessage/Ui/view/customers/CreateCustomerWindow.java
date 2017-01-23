package com.investMessage.Ui.view.customers;

import java.util.Collection;

import com.investMessage.Ui.DashboardUI;
import com.investMessage.Ui.DataEmptyException;
import com.investMessage.Ui.event.DashboardEvent.CloseOpenWindowsEvent;
import com.investMessage.Ui.event.DashboardEventBus;
import com.investMessage.services.CustomerIsAlreadyRegisteredException;
import com.investMessage.web.DTO.CustomerDTO;
import com.investMessage.web.DTO.StoreDTO;
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
import com.vaadin.ui.ComboBox;
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

public class CreateCustomerWindow extends Window {

	public static final String ID = "CreateCustomerWindow";

	@PropertyId("Name")
	private TextField name;
	@PropertyId("number")
	private TextField number;
	private ComboBox stores;

	private ProgressBar bar;

	private CreateCustomerWindow(final UserDTO user) {
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

		detailsWrapper.addComponent(buildProfileTab(user));

		content.addComponent(buildFooter(user));

	}

	private Component buildProfileTab(UserDTO user) {
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
		number = new TextField("Numero du client");
		Collection<StoreDTO> storesList = DashboardUI.getDataProvider().getStores();
		stores = new ComboBox("Magasin");
		for (StoreDTO st : storesList) {
			stores.addItem(st.store);
		}

		details.addComponent(name);
		details.addComponent(number);
		details.addComponent(stores);

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

	private Component buildFooter(UserDTO userDTO) {
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

					if (name.isValid() || number.isValid() || stores.isValid()) {
						CustomerDTO customerDTO = new CustomerDTO(number.getValue(), name.getValue(),
								(String) stores.getValue(), userDTO.username);
						DashboardUI.getDataProvider().saveClient(customerDTO);
					} else {
						throw new DataEmptyException("Remplissez les informations du client");
					}

					// bar.setVisible(false);
					Notification success = new Notification("Fichier téléchargé");
					success.setDelayMsec(2000);
					success.setStyleName("bar success small");
					success.setPosition(Position.BOTTOM_CENTER);
					success.show(Page.getCurrent());
					close();

				} catch (CustomerIsAlreadyRegisteredException e) {
					Notification.show("Ce client existe déja", Type.ERROR_MESSAGE);
				} catch (DataEmptyException e) {
					Notification.show(e.message, Type.ERROR_MESSAGE);
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

	public static void open(final UserDTO user) {
		DashboardEventBus.post(new CloseOpenWindowsEvent());
		Window w = new CreateCustomerWindow(user);
		UI.getCurrent().addWindow(w);
		w.focus();
	}
}