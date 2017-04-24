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
@Table(name = "CREDIT_CARDS")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column
    @Size(min = 1, max = 20)
    private String number;

    @NotNull
    @Column
    @Size(min = 1, max = 80)
    private String owner;

    //Trzeba cos wymyslic z datami
//    @NonNull
//    @Column
//    @Size(min = 1, max = 80)
//    private String expirationDate;

    //Nie wiem czy trzymanie tego w bazie jest dobrym pomyslem, czy nie lepiej prosic uzytkownika za kazdym razem o wpisanie
//    @NonNull
//    @Column
//    @Size(min = 1, max = 80)
//    private String safeCode;
}
