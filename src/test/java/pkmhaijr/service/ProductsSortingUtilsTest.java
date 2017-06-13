package pkmhaijr.service;

import antlr.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.enums.SortingType;
import pkmhaijr.util.ProductsSortingUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by Asasello on 09-Jun-17.
 */

public class ProductsSortingUtilsTest {
    private ArrayList<Product> listOfProductsPrice, listOfProductsTitle;

    private Product getProduct(String price){
        Product product = new Product();
        product.setPrice(new BigDecimal(price));
        return product;
    }
    private Product getProductWithTitle(String title){
        Product product = new Product();
        product.setTitle(title);
        return product;
    }
    @Before
    public void init(){
        listOfProductsPrice = new ArrayList<>();
        listOfProductsPrice.add(getProduct("5.94"));
        listOfProductsPrice.add(getProduct("8.94"));
        listOfProductsPrice.add(getProduct("14.94"));
        listOfProductsPrice.add(getProduct("5.97"));
        listOfProductsPrice.add(getProduct("5.34"));
        listOfProductsPrice.add(getProduct("1.24"));
        listOfProductsPrice.add(getProduct("65.94"));
        listOfProductsPrice.add(getProduct("95.94"));
        listOfProductsPrice.add(getProduct("12.94"));

        listOfProductsTitle = new ArrayList<>();
        listOfProductsTitle.add(getProductWithTitle("ALA"));
        listOfProductsTitle.add(getProductWithTitle("AAS"));
        listOfProductsTitle.add(getProductWithTitle("123Quest"));
        listOfProductsTitle.add(getProductWithTitle("MoreFun"));
        listOfProductsTitle.add(getProductWithTitle("moreFun"));
        listOfProductsTitle.add(getProductWithTitle("JAVA"));
        listOfProductsTitle.add(getProductWithTitle("ANDROID"));
        listOfProductsTitle.add(getProductWithTitle("android"));
        listOfProductsTitle.add(getProductWithTitle("ANDROID2233"));
        listOfProductsTitle.add(getProductWithTitle("333JaVa"));
    }
    @Test
    public void sortingProductsByPriceTest(){

        //preparation
        SortingType st = SortingType.PRICE_ASCENDING;

        //action
        List<Product> sortedList = ProductsSortingUtils.getSortedList(listOfProductsPrice, st);

        //assertion
        Assertions.assertThat(sortedList).isSortedAccordingTo(Comparator.comparing(Product::getPrice));
    }

    @Test
    public void sortingProductsByPriceTestDesc(){

        //preparation
        SortingType st = SortingType.PRICE_DESCENDING;

        //action
        List<Product> sortedList = ProductsSortingUtils.getSortedList(listOfProductsPrice, st);

        //assertion
        Assertions.assertThat(sortedList).isSortedAccordingTo((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
    }

    @Test
    public void sortingProductsByTitleAsc(){

        //preparation
        SortingType st = SortingType.TITLE_ASCENDING;

        //action
        List<Product> sortedList = ProductsSortingUtils.getSortedList(listOfProductsTitle, st);

        //assertion
        Assertions.assertThat(sortedList).isSortedAccordingTo((o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()));
    }

    @Test
    public void sortingProductsByTitleDesc(){

        //preparation
        SortingType st = SortingType.TITLE_DESCENDING;

        //action
        List<Product> sortedList = ProductsSortingUtils.getSortedList(listOfProductsTitle, st);

        //assertion
        Assertions.assertThat(sortedList).isSortedAccordingTo((o1, o2) -> o2.getTitle().compareToIgnoreCase(o1.getTitle()));
    }
}
