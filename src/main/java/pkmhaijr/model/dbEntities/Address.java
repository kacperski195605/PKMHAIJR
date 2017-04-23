package pkmhaijr.model.dbEntities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by patry on 23/04/17.
 */

@Data
@Entity
@Table(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    @Column
    @Size(min = 1, max = 80)
    private String street;

    @NonNull
    @Column
    @Size(min = 1, max = 80)
    private String country;

    @NonNull
    @Column
    @Size(min = 1, max = 80)
    private String city;

    @NonNull
    @Column
    @Size(min = 1, max = 10)
    private String houseNumber;

    @NonNull
    @Column
    @Size(min = 1, max = 10)
    private String apartmentNumber;

    @NonNull
    @Column
    @Size(min = 1, max = 10)
    private String postalCode;
}
