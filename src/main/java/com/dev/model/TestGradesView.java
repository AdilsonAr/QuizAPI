package com.dev.model;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Immutable
public class TestGradesView {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    String subcategory;
    @Getter
    private int grade;
    @Getter
    private LocalDateTime date;
    @Getter
    private int userId;
}
