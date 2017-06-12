package pkmhaijr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pkmhaijr.model.Tuple;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.dbEntities.User;
import pkmhaijr.model.enums.Genre;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by patry on 12/06/17.
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class RecommendationService {

    public Genre findFavouriteGenre(User user) {
        List<Product> orderHistory = user.getOrderHistory();

        //creating distinct set of products
        Set<Product> distinctProducts = getDistinctProducts(orderHistory);

        Queue<Tuple<Genre, Integer>> genresSet = new PriorityQueue<>(Comparator.comparingInt(Tuple::getSecond));

//        orderHistory
//                .stream()
//                .filter(Objects::nonNull)
//                .map(product -> new Tu)

        return null;
    }

    protected Set<Product> getDistinctProducts(List<Product> products) {
        return products.stream().distinct().collect(Collectors.toSet());
    }
}
