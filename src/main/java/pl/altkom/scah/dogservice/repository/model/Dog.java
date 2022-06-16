package pl.altkom.scah.dogservice.repository.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Dog {

    @Id
    @GeneratedValue(generator = "dogSeq")
    @SequenceGenerator(name = "dogSeq", sequenceName = "dog_seq", allocationSize = 20)
    private Long id;

    private String name;
    private LocalDate dateOfBirth;
    private String breed;
    private Long ownerId;
}
