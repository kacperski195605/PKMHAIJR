package pkmhaijr.service;

import antlr.StringUtils;
import org.junit.Before;
import org.junit.Test;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.enums.SortingType;
import pkmhaijr.util.ProductsSortingUtils;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;
/**
 * Created by Asasello on 09-Jun-17.
 */

public class ProductsSortingUtilsTest {
    ArrayList<Product> listOfProductsPrice,listOfProductsTitle;

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
        SortingType st = SortingType.PRICE_ASCENDING;
        ArrayList<Product> sortedList = ProductsSortingUtils.getSortedList(listOfProductsPrice, st);
        Product productBefore = sortedList.get(0);
        for (Product product : sortedList) {
            if(product.getPrice().doubleValue() < productBefore.getPrice().doubleValue()){
                assertFalse(true);
            }
            productBefore = product;
        }
        assertTrue(true);
    }

    @Test
    public void sortingProductsByPriceTestDesc(){
        SortingType st = SortingType.PRICE_DESCENDING;
        ArrayList<Product> sortedList = ProductsSortingUtils.getSortedList(listOfProductsPrice, st);
        Product productBefore = sortedList.get(0);
        for (Product product : sortedList) {
            if(product.getPrice().doubleValue() > productBefore.getPrice().doubleValue()){
                assertFalse(true);
            }
            productBefore = product;
        }
        assertTrue(true);
    }

    @Test
    public void sortingProductsByTitleAsc(){
        SortingType st = SortingType.TITLE_ASCENDING;
        ArrayList<Product> sortedList = ProductsSortingUtils.getSortedList(listOfProductsTitle, st);
        Product productBefore = sortedList.get(0);
        for (Product product : sortedList) {
            if(product.getTitle().compareToIgnoreCase(productBefore.getTitle()) < 0){
                assertFalse(true);
            }
            productBefore = product;
        }
        assertTrue(true);
    }

    @Test
    public void sortingProductsByTitleDesc(){
        SortingType st = SortingType.TITLE_DESCENDING;
        ArrayList<Product> sortedList = ProductsSortingUtils.getSortedList(listOfProductsTitle, st);
        Product productBefore = sortedList.get(0);
        for (Product product : sortedList) {
            if(product.getTitle().compareToIgnoreCase(productBefore.getTitle()) > 0){
                assertFalse(true);
            }
            productBefore = product;
        }
        assertTrue(true);
    }
}
