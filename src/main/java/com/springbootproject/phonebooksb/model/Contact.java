package com.springbootproject.phonebooksb.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Represents a contact in the phonebook")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the contact", example = "1")
    private Long id;

    @Schema(description = "First name of the contact", example = "John")
    private String firstName;

    @Schema(description = "Last name of the contact", example = "Doe")
    private String lastName;

    @Schema(description = "Email of the contact" , example = "Doe@gmail.com")
    private String email;

    @Schema(description = "List of phone numbers associated with the contact")
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumber> phoneNumberList;

}
