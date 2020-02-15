package com.example.eLearning.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private int cId;
    private String cName;
    private String teacher;
    private String description;
    private double rating;
}
