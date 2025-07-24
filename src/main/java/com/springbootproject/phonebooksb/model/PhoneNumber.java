package com.springbootproject.phonebooksb.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Schema(description = "Represents a phone number associated with a contact in the phonebook")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the phone number", example = "1")
    private Long id;

    @Schema(description = "Phone number in the format of a string", example = "123-456-7890")
    private String number;

    @Schema(description = "Type of the phone number", example = "MOBILE")
    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @Schema(description = "Contact associated with this phone number")
    @ManyToOne
    @JoinColumn(name = "contact_id")
    @JsonIgnore
    private Contact contact;
}
