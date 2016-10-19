package com.investMessage.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.investMessage.model.Customer;
import com.investMessage.model.MessageDefine;
import com.investMessage.repositories.CustomerRepository;
import com.investMessage.services.ClickatellServices;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A simple example to introduce building forms. As your real application is
 * probably much more complicated than this example, you could re-use this form
 * in multiple places. This example component is only used in VaadinUI.
 * <p>
 * In a real world application you'll most likely using a common super class for
 * all your forms - less code, better UX. See e.g. AbstractForm in Virin
 * (https://vaadin.com/addon/viritin).
 */
@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout {

	private final CustomerRepository repository;

	/**
	 * The currently edited customer
	 */
	private Customer customer;

	/* Fields to edit properties in Customer entity */

	TextField name = new TextField("Nom du nouveau client");
	TextField numero = new TextField("Téléphone nouveau client");

	/* Action buttons */
	Button save = new Button("Sauvegarder", FontAwesome.SAVE);
	Button cancel = new Button("Annuler");
	Button achat = new Button("Enrégistrer achat", FontAwesome.SEND);
	Button delete = new Button("Supprimer", FontAwesome.TRASH_O);
	CssLayout actions = new CssLayout(save, cancel, delete, achat);

	@Autowired
	public CustomerEditor(CustomerRepository repository) {
		this.repository = repository;

		name.setMaxLength(20);
		numero.setMaxLength(8);
		name.addValidator(new StringLengthValidator("Nom invalide", 2, 20, false));
		numero.addValidator(new StringLengthValidator("Téléphone invalide", 8, 8, true));

		addComponents(numero, name, actions);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		// wire action buttons to save, delete and reset
		save.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					Integer.parseInt(numero.getValue());
					name.validate();
					numero.validate();
					customer.setNumber(numero.getValue());
					customer.setName(name.getValue());
					if (repository.findByNumero(numero.getValue()).isEmpty()) {
						repository.save(customer);
						try {
							ClickatellServices.sendMessage(MessageDefine.thanks, customer.getNumero());
						} catch (IOException | InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						Notification.show("Ce numéro existe déja");
					}

				} catch (InvalidValueException e) {
					Notification.show("Information client invalide");

				} catch (NumberFormatException e) {
					Notification.show("Le numéro est invalide");

				}

			}
		});

		delete.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				customer.setNumber(numero.getValue());
				customer.setName(name.getValue());
				repository.delete(customer);

			}
		});
		cancel.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				editCustomer(customer);

			}
		});
		achat.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				customer.setLastBillDate(LocalDateTime.now());
				customer = repository.save(customer);
				try {
					ClickatellServices.sendMessage(MessageDefine.thanks, customer.getNumero());
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editCustomer(Customer c) {
		final boolean persisted = c.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			customer = repository.findOne(c.getId());
		} else {
			customer = c;
		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		// BeanFieldGroup.bindFieldsUnbuffered(customer, this);
		numero.setValue(customer.getNumero());
		name.setValue(customer.getName());

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		// numero.selectAll();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
		achat.addClickListener(e -> h.onChange());
	}

}
