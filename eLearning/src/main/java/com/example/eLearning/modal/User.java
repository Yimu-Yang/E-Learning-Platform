package com.example.eLearning.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "bigint")
    private Integer id;

    @Column(name = "user_name", nullable = false, columnDefinition = "nvarchar(100)")
    private String courseName;

    @Column(name = "password", nullable = false, columnDefinition = "nvarchar(100)")
    private String password;
}
