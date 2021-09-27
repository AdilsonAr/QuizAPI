package com.dev.service;

import com.dev.configuration.GoogleServicesProvider;
import com.dev.dto.TestGradesViewDto;
import com.dev.model.TestGradesView;
import com.dev.model.User;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SheetWriterService {
    private DateTimeFormatter formatterDateOnly = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Sheets sheets;
    private Drive drive;
    private TestGradesViewService testGradeViewService;

    public void SheetsWriterService() throws IOException, GeneralSecurityException {
        sheets=GoogleServicesProvider.getSheets();
        drive= GoogleServicesProvider.getDrive();
        TestGradesViewService tgv =new TestGradesViewService();
    }

    public String createReportByUserId(int userId, String email) throws IOException {
        String title = "Test Grades by user "+ email;
        List<TestGradesViewDto> listDtos = new ArrayList<>();
        (testGradeViewService.readByUserId(userId)).forEach(x->listDtos.add(TestGradesViewDto.toDto(x)));
        return createReport(listDtos, title, email);
    }

    private String createReport(List<TestGradesViewDto> listDtos, String title, String email) throws IOException {
        Spreadsheet spreadsheet=create(title);
        List<List<Object>> values=new ArrayList<>();

        values.add(Arrays.asList(title));
        values.add(Arrays.asList(""));
        values.add(Arrays.asList("This is a report of all test completed by the user"));
        values.add(Arrays.asList(""));

        TestGradesViewMapper.toSheets(listDtos, values);
        ValueRange body = new ValueRange().setValues(values);

        write(spreadsheet.getSpreadsheetId(), body);
        createPermission(spreadsheet.getSpreadsheetId(), email);

        String message = "The report you requested is already available with this URL: " + spreadsheet.getSpreadsheetUrl();
        return message;
    }

    private void createPermission(String spreadSheetId, String email) throws IOException {
        Permission newPermission = new Permission();
        newPermission.setType("user");
        newPermission.setRole("owner");
        newPermission.setEmailAddress(email);
        drive.permissions().create(spreadSheetId, newPermission).setTransferOwnership(true).execute();
    }

    private Spreadsheet create(String title) throws IOException {
        Spreadsheet spreadsheet = new Spreadsheet()
                .setProperties(new SpreadsheetProperties()
                        .setTitle(title));
        spreadsheet = sheets.spreadsheets().create(spreadsheet)
                .execute();
        return spreadsheet;
    }

    private void write(String spreadSheetId, ValueRange body) throws IOException {
        sheets.spreadsheets().values()
                .update(spreadSheetId, "A1", body)
                .setValueInputOption("RAW")
                .execute();
    }

}
