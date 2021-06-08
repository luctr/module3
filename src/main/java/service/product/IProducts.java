package service.product;

import model.ProductModel;

import java.util.List;

public interface IProducts  {
    List<ProductModel> showAll();
    ProductModel findById(int id);
    void create(ProductModel productModel);
    void remove(int id);
    void update(int id,ProductModel productModel);
}

