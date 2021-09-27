package com.dev.service;

import com.dev.dto.TestGradesViewDto;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TestGradesViewMapper {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static List<List<Object>> toSheets(List<TestGradesViewDto> testGrades, List<List<Object>> values){
        for(TestGradesViewDto c: testGrades) {

            values.add(Arrays.asList("SubCategory", "Grade","Date"));
            values.add(
                    Arrays.asList(c.getSubcategory(), c.getGrade(),
                            (c.getDate()).format(FORMATTER))
            );
        }
        return values;
    }
}
