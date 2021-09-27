package com.dev.dto;

import com.dev.model.TestGradesView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestGradesViewDto {

    String subcategory;
    private int grade;
    private LocalDateTime date;
    private int userId;

    public static TestGradesViewDto toDto(TestGradesView testGradesView){
        return new TestGradesViewDto(testGradesView.getSubcategory(), testGradesView.getGrade(), testGradesView.getDate(), testGradesView.getUserId());
    }

}
