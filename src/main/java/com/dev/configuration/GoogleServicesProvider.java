package com.dev.configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;

public class GoogleServicesProvider {
    private static final String APPLICATION_NAME = "ControlTower";

    public static Drive getDrive() throws GeneralSecurityException, IOException {
        // Build a new authorized API client service.
        Drive service = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), GoogleCredentialConfiguration.getDriveInstance())
                .setApplicationName(APPLICATION_NAME)
                .build();
        return service;
    }

    public static Sheets getSheets() throws IOException, GeneralSecurityException {
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), GoogleCredentialConfiguration.getSheetsInstance())
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
