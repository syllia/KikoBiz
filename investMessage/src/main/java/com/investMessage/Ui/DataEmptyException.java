package com.investMessage.Ui;

public class DataEmptyException extends Exception {
	public String message;

	public DataEmptyException(String string) {
		message = string;
	}

}
