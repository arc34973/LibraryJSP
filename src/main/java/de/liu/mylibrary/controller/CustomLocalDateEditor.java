package de.liu.mylibrary.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateEditor extends PropertyEditorSupport {

	private DateTimeFormatter formatter;

	public CustomLocalDateEditor(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && text.length() > 0) {
//convert String to LocalDate
			LocalDate date = LocalDate.parse(text, formatter);
			setValue(date);
		} else {
			setValue(null);
		}
	}

	@Override
	public String getAsText() {
		LocalDate date = (LocalDate) getValue();
		if (date != null) {
			String formattedDate = date.format(formatter);
			return formattedDate;
		} else {
			return null;
		}
	}
}
