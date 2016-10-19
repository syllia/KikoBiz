package com.investMessage.services;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.investMessage.repositories.CustomerRepository;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private static int INTERVALL_HOUR = 1;
	@Autowired
	private CustomerRepository customerRepository;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	/*
	 * @Scheduled(cron = "0 0 6-23 * * *") public void reportCurrentTime()
	 * throws IOException, InterruptedException {
	 * log.info("Compilation merci {}", dateFormat.format(new Date()));
	 * List<Customer> customers = customerRepository.findAll(); send(customers);
	 * 
	 * }
	 * 
	 * @Async public void send(List<Customer> customers) throws IOException,
	 * InterruptedException { for (Customer customer : customers) { if
	 * (isDateValideSend(customer.getLastBillDate())) {
	 * log.info("Clients remerciés ::" + customer.toString());
	 * ClickatellServices.sendMessage(MessageDefine.thanks,
	 * customer.getNumero()); } } }
	 * 
	 * private boolean isDateValideSend(LocalDateTime customerDate) {
	 * LocalDateTime now = LocalDateTime.now(); if (customerDate != null) { if
	 * (now.getYear() == customerDate.getYear()) { if (now.getMonthValue() ==
	 * customerDate.getMonthValue()) { if (now.getDayOfMonth() ==
	 * customerDate.getDayOfMonth()) { if (customerDate.getHour() +
	 * INTERVALL_HOUR == now.getHour()) { return true; } } } } } return false; }
	 * 
	 * boolean isGood(Customer customer) { if (customer.getLastBillDate() !=
	 * null) { if (customer.getLastBillDate().getYear() ==
	 * LocalDateTime.now().getYear()) { if
	 * (customer.getLastBillDate().plusWeeks(1).getDayOfYear() >
	 * LocalDateTime.now().getDayOfYear()) { return true; }
	 * 
	 * } } return false; }
	 * 
	 * 
	 * @Scheduled(cron = "30 8 * * * SUN") public void changeInfo() {
	 * log.info("Compilation merci {}", dateFormat.format(new Date()));
	 * List<Customer> customers = customerRepository.findAll();
	 * 
	 * for (Customer customer : customers) { if (!isGood(customer)) {
	 * customer.setInfo("Ce client n'a pas acheté la semaine passée");
	 * customerRepository.save(customer); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Scheduled(cron = "30 9 5 * * ?") public void sendRappels() throws
	 * IOException, InterruptedException { log.info("Compilation merci {}",
	 * dateFormat.format(new Date())); List<Customer> customers =
	 * customerRepository.findAll();
	 * 
	 * for (Customer customer : customers) { if (customer.getNumberBill()>50) {
	 * ClickatellServices.sendMessage(MessageDefine.thanks,
	 * customer.getNumber()); }
	 * 
	 * }
	 * 
	 * }
	 */

}
