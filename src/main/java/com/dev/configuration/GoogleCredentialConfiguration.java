package com.dev.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.sheets.v4.SheetsScopes;

import lombok.Getter;

@Getter
public class GoogleCredentialConfiguration {
    private static Credential SheetsCredential=null;
    private static Credential DriveCredential=null;

    public static Credential getDriveInstance() throws IOException {
        if(DriveCredential==null) {
            List<String> scopes = Arrays.asList(DriveScopes.DRIVE);
            // You can specify a credential file by providing a path to GoogleCredentials.
            // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS
            // environment variable.
            DriveCredential = GoogleCredential.getApplicationDefault().createScoped(scopes);
        }
        return DriveCredential;
    }

    public static Credential getSheetsInstance() throws IOException {
        if(SheetsCredential==null) {
            List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
            // You can specify a credential file by providing a path to GoogleCredentials.
            // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS
            // environment variable.
            SheetsCredential = GoogleCredential.getApplicationDefault().createScoped(scopes);
        }
        return SheetsCredential;
    }

}
