package com.investMessage.Ui.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.investMessage.Ui.DashboardUI;

public class DashboardEventBus implements SubscriberExceptionHandler {
	private final EventBus eventBus = new EventBus(this);

	public static void post(final Object event) {
		DashboardUI.getDashboardEventbus().eventBus.post(event);
	}

	public static void register(final Object object) {
		DashboardUI.getDashboardEventbus().eventBus.register(object);
	}

	public static void unregister(final Object object) {
		DashboardUI.getDashboardEventbus().eventBus.unregister(object);
	}

	@Override
	public final void handleException(final Throwable exception, final SubscriberExceptionContext context) {
		exception.printStackTrace();
	}
}
