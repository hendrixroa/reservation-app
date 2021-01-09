package com.reservation.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
public class ContactDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String type;

    @NotEmpty
    @Past
    private LocalDate birthdate;

    private String phone;

}

