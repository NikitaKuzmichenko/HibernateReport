package com.reports.hibernate.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TestCase {

    @Id
    @GeneratedValue
    private Long id;

    private String caseName;
}
