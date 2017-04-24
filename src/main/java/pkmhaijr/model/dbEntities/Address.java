package pkmhaijr.model.dbEntities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column
    @Size(min = 1, max = 80)
    private String street;

    @NotNull
    @Column
    @Size(min = 1, max = 80)
    private String country;

    @NotNull
    @Column
    @Size(min = 1, max = 80)
    private String city;

    @NotNull
    @Column
    @Size(min = 1, max = 10)
    private String houseNumber;

    @NotNull
    @Column
    @Size(min = 1, max = 10)
    private String apartmentNumber;

    @NotNull
    @Column
    @Size(min = 1, max = 10)
    private String postalCode;
}
