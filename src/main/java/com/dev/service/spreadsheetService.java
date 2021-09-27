package com.dev.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.format.DateTimeFormatter;

import com.dev.configuration.GoogleServicesProvider;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;

public class spreadsheetService {
	private DateTimeFormatter formatterDateOnly = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private Sheets sheets;
	private Drive drive;
	
	public spreadsheetService() throws IOException, GeneralSecurityException {
		sheets=GoogleServicesProvider.getSheets();
		drive=GoogleServicesProvider.getDrive();
	}
}
