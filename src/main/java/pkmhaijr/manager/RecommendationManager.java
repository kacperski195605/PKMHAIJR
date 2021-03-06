package pkmhaijr.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkmhaijr.model.Tuple;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.dbEntities.User;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.service.ProductService;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by patry on 12/06/17.
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class RecommendationManager {

    @Autowired
    private ProductService productService;

    public List<Product> recommendProductsByGenre(User user, int numberOfProducts) {
        Genre genre = findFavouriteGenre(user);
        List<Product> productsWithGenre = productService.getSortedProduct(genre);
        Collections.shuffle(productsWithGenre, new Random(System.nanoTime()));
        return productsWithGenre.subList(0, Math.min(productsWithGenre.size(), numberOfProducts));
    }

    Genre findFavouriteGenre(User user) {
        List<Product> orderHistory = user.getOrderHistory();

        //distinct set of genres
        Set<Genre> distinctGenres = getDistinctGenres(orderHistory);

        //priorityQueue that will check which genre is user's favourite
        Queue<Tuple<Genre, Long>> genresQueue = fitProductsIntoPriorityQueue(orderHistory, distinctGenres);

        return genresQueue.poll().getFirst();
    }

    Set<Genre> getDistinctGenres(List<Product> products) {
        return products.stream()
                .filter(Objects::nonNull)
                .map(Product::getGenre)
                .distinct()
                .collect(Collectors.toSet());
    }

    PriorityQueue<Tuple<Genre, Long>> fitProductsIntoPriorityQueue(List<Product> orderHistory, Set<Genre> genres) {
        PriorityQueue<Tuple<Genre, Long>> genresQueue = new PriorityQueue<>((a, b) -> Math.toIntExact(b.getSecond() - a.getSecond()));
        genres.stream()
            .filter(Objects::nonNull)
            .map(genre -> new Tuple<>(genre, orderHistory.stream()
                    .filter(Objects::nonNull)
                    .filter(product -> product.getGenre() == genre)
                    .count()
                ))
            .forEach(genresQueue::add);
        return genresQueue;
    }
}
