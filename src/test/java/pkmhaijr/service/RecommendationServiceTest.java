package pkmhaijr.service;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pkmhaijr.model.Tuple;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.model.enums.ProductType;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by patry on 12/06/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Log4j2
public class RecommendationServiceTest {

    @Autowired
    private RecommendationService recommendationService;

    private Product product1;
    private Product product2;
    private Product product3;

    @Before
    public void init() {
        product1 = new Product(new BigDecimal("14.99"), "title1", ProductType.CD, "desc1", Genre.ALTERNATIVE);
        product2 = new Product(new BigDecimal("12.99"), "title2", ProductType.CD, "desc2", Genre.ALTERNATIVE);
        product3 = new Product(new BigDecimal("16.99"), "title3", ProductType.CD, "desc3", Genre.BLUES);
    }

    private List<Product> prepareOrderHistoryWithRepetitions() {
        return Arrays.asList(
            product1.clone(), product2.clone(), product2.clone(), product1.clone(), product1.clone(),
                product3.clone(), product1.clone()
        );
    }

    private Set<Genre> prepareOrderHistorySet() {
        Set<Genre> orderHistorySet = new HashSet<>();
        orderHistorySet.add(Genre.ALTERNATIVE);
        orderHistorySet.add(Genre.BLUES);
        return orderHistorySet;
    }

    private PriorityQueue<Tuple<Genre, Long>> prepareGenresQueue() {
        PriorityQueue<Tuple<Genre, Long>> genresQueue = new PriorityQueue<>((a, b) -> Math.toIntExact(b.getSecond() - a.getSecond()));
        genresQueue.add(new Tuple<>(Genre.ALTERNATIVE, 6L));
        genresQueue.add(new Tuple<>(Genre.BLUES, 1L));
        return genresQueue;
    }

    @Test
    public void getDistinctProductsTest() {
        //preparation
        List<Product> orderHistory = prepareOrderHistoryWithRepetitions();
        Set<Genre> expectedSet = prepareOrderHistorySet();

        //action
        Set<Genre> actualSet = recommendationService.getDistinctGenres(orderHistory);

        //assertion
        Assertions.assertThat(expectedSet).hasSameElementsAs(actualSet);
        Assertions.assertThat(expectedSet).hasSameSizeAs(actualSet);
    }


    @Test
    public void fitProductsIntoPriorityQueueTest() {
        //preparation
        List<Product> orderHistory = prepareOrderHistoryWithRepetitions();
        Set<Genre> genres = prepareOrderHistorySet();
        PriorityQueue<Tuple<Genre, Long>> expectedQueue = prepareGenresQueue();

        //action
        PriorityQueue<Tuple<Genre, Long>> actualQueue = recommendationService.fitProductsIntoPriorityQueue(orderHistory, genres);

        //assertion
        Assertions.assertThat(expectedQueue).hasSameElementsAs(actualQueue);
        Assertions.assertThat(expectedQueue).hasSameSizeAs(actualQueue);
    }
}
