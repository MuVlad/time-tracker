package com.muslimov.vlad.timetracker.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "method_tracker")
public class MethodTracker {

    private static final String SEQ_NAME = "method_tracker_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(
        name = SEQ_NAME,
        sequenceName = SEQ_NAME,
        allocationSize = 1
    )
    private Long id;
    private String methodName;
    private Long executionTime;
}
