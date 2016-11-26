package com.kouri.App;

import com.kouri.OfferServer.OfferServer;
import com.kouri.VaadinUi.VaadinServer;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Thread offerThread = new Thread(new OfferServer());
		Thread vaadinUi = new Thread(new VaadinServer());

		offerThread.start();
		vaadinUi.start();

		offerThread.join();
		vaadinUi.join();
	}
}
