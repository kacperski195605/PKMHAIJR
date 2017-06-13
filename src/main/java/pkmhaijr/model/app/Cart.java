package pkmhaijr.model.app;

import javafx.util.Pair;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pkmhaijr.model.dbEntities.Product;

import java.util.HashMap;

/**
 * Created by Asasello on 12-Jun-17.
 */
@Component
@Log4j2
public class Cart {
    private final HashMap<Product,Integer> products;
    private double price;
    private int size;

    @Autowired
    public Cart(){
        price = 0;
        size = 0;
        this.products = new HashMap<>();
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

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }
}

