package fcu.sep.fcushop.controller;

import fcu.sep.fcushop.model.Product;
import fcu.sep.fcushop.service.ProductService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sql2o.Connection;

@RestController

public class ProductController{
  @Autowired
  ProductService productManager;

  @GetMapping("/products")
  public List<Product> getProducts() {
    try (org.sql2o.Connection connection = sql2oDbHandler.getConnector().open()) {
      String query = "select ID id, NAME name, IMAGE_URL imageUrl, PRICE price, DESCRIPTION description"
          + " from PRODUCT";

      return connection.createQuery(query).executeAndFetch(Product.class);
    }
  }
  public List<Product> getProducts(String keyword){
    try(Connection connection = sql2oDbHandler.getConnector().open()){
      String query = "select ID id , NAME name,IMAGE_URL imageUrl,PRICE price, DESCRIPTION description"
          + "form PRODUCT where name = : keyword";

      return connection.createQuery(query)
          .addParameter(keyword, keyword)
          .executeAndFetch(Product.class);

    }
  }
}
