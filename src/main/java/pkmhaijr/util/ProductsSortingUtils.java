package pkmhaijr.util;

import pkmhaijr.model.dbEntities.Author;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.enums.SortingType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Created by Asasello on 23-Apr-17.
 */
public class ProductsSortingUtils {
    public static ArrayList<Product> getSortedList(ArrayList<Product> productsList, SortingType sortingType) {
        switch (sortingType) {
            case TITLE_ASCENDING:
                return getSortedListByTitle(productsList,true);
            case TITLE_DESCENDING:
                return getSortedListByTitle(productsList,false);
            case AUTHOR_ASCENDING:
                return getSortedListByAuthor(productsList,true);
            case AUTHOR_DESCENDING:
                return getSortedListByAuthor(productsList,false);
            case RELEASE_DATE_ASCENDING:
                return getSortedListByReleaseDate(productsList,true);
            case RELEASE_DATE_DESCENDING:
                return getSortedListByReleaseDate(productsList,false);
            case PRICE_ASCENDING:
                return getSortedListByPrice(productsList,true);
            case PRICE_DESCENDING:
                return getSortedListByPrice(productsList,false);
            default:
                return productsList;
        }
    }

    private static ArrayList<Product> getSortedListByTitle(ArrayList<Product> productsList,boolean ascending){
       return ascending?productsList.stream().sorted(Comparator.comparing(Product::getTitle)).collect(Collectors.toCollection(ArrayList::new)):productsList.stream().sorted(Comparator.comparing(Product::getTitle).reversed()).collect(Collectors.toCollection(ArrayList::new));
    }
    private static ArrayList<Product> getSortedListByReleaseDate(ArrayList<Product> productsList,boolean ascending){
        //TODO: release date in product
//        return ascending?productsList.stream().sorted(Comparator.comparing(Product::getReleaseDate)).collect(Collectors.toCollection(ArrayList::new)):productsList.stream().sorted(Comparator.comparing(Product::getReleaseDate).reversed()).collect(Collectors.toCollection(ArrayList::new));
        return productsList;
    }
    private static ArrayList<Product> getSortedListByPrice(ArrayList<Product> productsList,boolean ascending){
        return ascending?productsList.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toCollection(ArrayList::new)):productsList.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<Product> getSortedListByAuthor(ArrayList<Product> productsList,boolean ascending){
        return ascending?productsList.stream().sorted(Comparator.comparing(product -> product.getAuthor().getName())).collect(Collectors.toCollection(ArrayList::new)):productsList.stream().sorted(Comparator.comparing(product -> product.getAuthor().getName())).collect(Collectors.toCollection(ArrayList::new));
    }
}
