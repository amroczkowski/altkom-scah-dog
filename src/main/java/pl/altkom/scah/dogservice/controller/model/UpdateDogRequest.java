package pl.altkom.scah.dogservice.controller.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateDogRequest {

    private String name;
    private LocalDate dateOfBirth;
    private String breed;
    private Long ownerId;
}
