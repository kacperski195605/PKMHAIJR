package pkmhaijr.model.app;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import pkmhaijr.model.dbEntities.Product;

import java.util.HashMap;

/**
 * Created by Asasello on 12-Jun-17.
 */
public class Cart {
    @Autowired
    private HashMap<Product,Integer> products;
    private double price;
    private int size;

    public Cart(){
        price = 0;
        size = 0;
    }

    public void addToCart(Product product){
        products.put(product, products.getOrDefault(product, 1));
        price += product.getPrice().doubleValue();
        size++;
    }

    public void getOutOfCart(Product product){
        if(products.containsKey(product)){
            --size;
            price -= product.getPrice().doubleValue();
            if(products.get(product)==1){
                products.remove(product);
            }else{
                products.put(product,products.get(product)-1);
            }
        }
    }

    public void clearCart(){
        products.clear();
        size = 0;
        price = 0;
    }
}

