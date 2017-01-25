package com.investMessage.Ui.view.users;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.google.common.eventbus.Subscribe;
import com.investMessage.Ui.DashboardUI;
import com.investMessage.Ui.event.DashboardEvent.BrowserResizeEvent;
import com.investMessage.Ui.event.DashboardEventBus;
import com.investMessage.Ui.window.DownloadFileWindow;
import com.investMessage.web.DTO.DocumentDTO;
import com.investMessage.web.DTO.UserDTO;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class UsersViews extends VerticalLayout implements View {

	private final Grid grid;
	private Button createUser;
	private String filterValue;

	private static final Set<Column> collapsibleColumns = new LinkedHashSet<>();

	public UsersViews() {
		setSizeFull();
		addStyleName("transactions");
		DashboardEventBus.register(this);

		addComponent(buildToolbar());

		grid = buildGrid();

		addComponent(grid);
		setExpandRatio(grid, 1);
	}

	@Override
	public void detach() {
		super.detach();
		// A new instance of TransactionsView is created every time it's
		// navigated to so we'll need to clean up references to it on detach.
		DashboardEventBus.unregister(this);
	}

	private Component buildToolbar() {
		HorizontalLayout header = new HorizontalLayout();
		header.addStyleName("viewheader");
		header.setSpacing(true);
		Responsive.makeResponsive(header);

		Label title = new Label("Clients");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H1);
		title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		header.addComponent(title);

		createUser = buildCreateReport();

		HorizontalLayout tools = new HorizontalLayout(buildFilter(), createUser);
		tools.setSpacing(true);
		tools.addStyleName("toolbar");
		header.addComponent(tools);

		return header;
	}

	private Button buildCreateReport() {
		final Button createReport = new Button("Ajouter un utilisateur");
		createReport.setDescription("Ajouter un utilisateur");
		createReport.addClickListener(event -> createNewReportFromSelection());
		createReport.setEnabled(true);
		return createReport;
	}

	@SuppressWarnings("rawtypes")
	private Component buildFilter() {
		final TextField filter = new TextField();

		// TODO improve filtering
		filter.addValueChangeListener(event -> {

			if (!StringUtils.isEmpty(filter.getValue())) {
				UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
				Collection<UserDTO> userDTOs = DashboardUI.getDataProvider().FindAllUsers().stream().filter(u -> {
					filterValue = filter.getValue().trim().toLowerCase();
					return passesFilter(u.lastName) || passesFilter(u.firstName) || passesFilter(u.phoneNumber);
				}).collect(Collectors.toList());

				grid.setContainerDataSource(new BeanItemContainer(UserDTO.class, userDTOs));
			} else {
				UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
				Collection<UserDTO> userDTos = DashboardUI.getDataProvider().FindAllUsers().stream()
						.collect(Collectors.toList());
				grid.setContainerDataSource(new BeanItemContainer(UserDTO.class, userDTos));
			}
			// grid.setDataSource(dataSource.sortingBy(Comparator.comparing(Transaction::getTime).reversed()));
		});

		// filter.setPlaceholder("Filter");
		filter.setIcon(FontAwesome.SEARCH);
		filter.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		filter.addShortcutListener(new ShortcutListener("Clear", KeyCode.ESCAPE, null) {
			@Override
			public void handleAction(final Object sender, final Object target) {
				filter.setValue("");
			}
		});
		return filter;
	}

	private Grid buildGrid() {
		final Grid grid = new Grid();
		grid.setSizeFull();

		// grid.addColumn("Time", transaction ->
		// DATEFORMAT.format(transaction.getTime())).setHidable(true);

		grid.addColumn("username", String.class);
		collapsibleColumns.add(grid.addColumn("lastName", String.class));
		collapsibleColumns.add(grid.addColumn("firstName", String.class));// ;
		// grid.addColumn("Price", transaction -> "$" +
		// DECIMALFORMAT.format(transaction.getPrice())).setHidable(true);

		grid.setColumnReorderingAllowed(true);

		UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
		grid.setContainerDataSource(new BeanItemContainer(UserDTO.class, DashboardUI.getDataProvider().FindAllUsers()));
		// TODO either add these to grid or do it with style generators here
		// grid.setColumnAlignment("seats", Align.RIGHT);
		// grid.setColumnAlignment("price", Align.RIGHT);

		// TODO add when footers implemented in v8
		// grid.setFooterVisible(true);
		// grid.setColumnFooter("time", "Total");
		// grid.setColumnFooter("price", "$" + DECIMALFORMAT
		// .format(DashboardUI.getDataProvider().getTotalSum()));

		// TODO add this functionality to grid?
		// grid.addActionHandler(new TransactionsActionHandler());

		// grid.addSelectionListener(event ->
		// createReport.setEnabled(!grid.getSelectedItems().isEmpty()));

		grid.addSelectionListener(e -> {
			if (e.getSelected().isEmpty()) {
			} else {
				UserDTO userDTO = (UserDTO) grid.getSelectedRow();
				// jjjjjjjjjjjjopenDownloadView(documentDTO);
			}
		});
		return grid;
	}

	private boolean defaultColumnsVisible() {
		boolean result = true;
		for (Column column : collapsibleColumns) {
			if (column.isHidden() == Page.getCurrent().getBrowserWindowWidth() < 800) {
				result = false;
			}
		}
		return result;
	}

	@Subscribe
	public void browserResized(final BrowserResizeEvent event) {
		// Some columns are collapsed when browser window width gets small
		// enough to make the table fit better.

		if (defaultColumnsVisible()) {
			for (Column column : collapsibleColumns) {
				column.setHidden(Page.getCurrent().getBrowserWindowWidth() < 800);
			}
		}
	}

	void createNewReportFromSelection() {
		UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
	}

	void openDownloadView(DocumentDTO documentDTO) {
		DownloadFileWindow.open(documentDTO);
	}

	private boolean passesFilter(String subject) {
		if (subject == null) {
			return false;
		}
		return subject.trim().toLowerCase().contains(filterValue);
	}

	@Override
	public void enter(final ViewChangeEvent event) {
	}
}
