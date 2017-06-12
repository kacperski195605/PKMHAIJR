package pkmhaijr.model;

import lombok.Data;

/**
 * Created by patry on 12/06/17.
 */

@Data
public class Tuple<X, Y> {

    private final X first;
    private final Y second;
}
