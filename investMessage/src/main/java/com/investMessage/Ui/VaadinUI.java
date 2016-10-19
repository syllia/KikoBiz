package com.investMessage.Ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.investMessage.model.Customer;
import com.investMessage.model.Store;
import com.investMessage.repositories.CustomerRepository;
import com.investMessage.repositories.StoreRepository;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	private final CustomerRepository repo;
	private final StoreRepository repoStore;

	private final CustomerEditor editor;

	final Grid grid;

	final TextField filter;
	private Button validate;
	private TextField store;
	private TextField passwordStore;
	private Label result;
	private com.vaadin.ui.Label connexion;

	private final Button addNewBtn;

	@Autowired
	public VaadinUI(CustomerRepository repo, CustomerEditor editor, StoreRepository storeRepository) {
		this.repo = repo;
		this.repoStore = storeRepository;
		this.editor = editor;
		this.grid = new Grid();
		this.validate = new Button("Se connecter", FontAwesome.SIGN_IN);
		this.store = new TextField("Magasin");
		this.result = new Label("");
		this.passwordStore = new TextField("Mot de passe");
		this.connexion = new Label("Connexion Employé");
		this.filter = new TextField();
		this.addNewBtn = new Button("New customer", FontAwesome.PLUS);
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout

		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		VerticalLayout login = new VerticalLayout(connexion, store, passwordStore, validate, result);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);

		setContent(login);

		// Configure layouts and components
		actions.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		login.setMargin(true);
		login.setSpacing(true);

		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "name", "numero");

		grid.getColumn("name").setHeaderCaption("Nom du client");
		grid.getColumn("numero").setHeaderCaption("Téléphone");

		validate.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				if (isCredentialValid()) {
					result.setValue("valide");
					setContent(mainLayout);
					listCustomers(null);

				} else {
					result.setValue("non valide");
				}

			}

		});
		filter.setInputPrompt("Rechercher par nom ");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.addTextChangeListener(e -> listCustomers(e.getText()));

		// Connect selected Customer to editor or hide if none is selected
		grid.addSelectionListener(e -> {
			if (e.getSelected().isEmpty()) {
				editor.setVisible(false);

			} else {
				editor.editCustomer((Customer) grid.getSelectedRow());
			}
		});

		// Instantiate and edit new Customer the new button is clicked
		addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "", store.getValue())));

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listCustomers(filter.getValue());
		});

	}

	private boolean isCredentialValid() {
		if (repoStore.findByStore(store.getValue()).isEmpty()) {
			return false;
		} else {
			Store myStore = repoStore.findByStore(store.getValue()).get(0);
			if (myStore.getCode().equals(passwordStore.getValue())) {
				return true;
			}
			return false;
		}

	}

	// tag::listCustomers[]
	void listCustomers(String text) {
		if (StringUtils.isEmpty(text)) {

			grid.setContainerDataSource(new BeanItemContainer(Customer.class, repo.findByShop(store.getValue())));
		} else {
			grid.setContainerDataSource(
					new BeanItemContainer(Customer.class, repo.findByShopAndNumeroStartsWithIgnoreCase(text, text)));
		}
	}
	// end::listCustomers[]

}
