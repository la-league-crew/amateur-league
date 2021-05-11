package com.league.entity;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class LoginAttempts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private LocalDateTime dateOfLastAttempt;
    @Builder.Default
    private Integer numberOfFailureAttempts=0;

    public void increaseNumberOfFailureAttempts(){
        numberOfFailureAttempts++;
    }
    public void resetNumberOfFailureAttempts(){
        numberOfFailureAttempts=0;
        dateOfLastAttempt =LocalDateTime.now();
    }

}
