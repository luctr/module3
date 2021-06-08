package service.product;

import model.ProductModel;
import service.connertion.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProducts{
    @Override
    public List<ProductModel> showAll() {
        Connection connection = ConnectionJDBC.getConnection();
        List<ProductModel> list = new ArrayList<>();
        String sql = "select idproduct,name,price,quantity,color,description,c.category_name from product inner join category c on c.category_id = product.category_id ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = ConnectionJDBC.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductModel productModel = new ProductModel();
                productModel.setId(resultSet.getInt("idproduct"));
                productModel.setProduct_name(resultSet.getString("name"));
                productModel.setPrice(resultSet.getInt("price"));
                productModel.setQuantity(resultSet.getInt("quantity"));
                productModel.setColor(resultSet.getString("color"));
                productModel.setDescription(resultSet.getString("description"));
                productModel.setCategory_name(resultSet.getString("category_name"));
                list.add(productModel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();

            }
        }
        return list;
    }

    @Override
    public ProductModel findById(int id) {
        Connection connection = ConnectionJDBC.getConnection();
        String sql = "select * from product where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ProductModel productModel = new ProductModel();
                productModel.setId(resultSet.getInt("id"));
                productModel.setProduct_name(resultSet.getString("name"));
                return productModel;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                if (connection != null){
                    connection.close();
                }
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void create(ProductModel productModel) {
        Connection connection = ConnectionJDBC.getConnection();
        String sql = "insert into product (name,price,quantity,color,description,category_id) value (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"name");
           preparedStatement.setInt(2, Integer.parseInt("price"));
           preparedStatement.setInt(3,Integer.parseInt("quantity"));
           preparedStatement.setString(4,"color");
           preparedStatement.setString(5,"description");
           preparedStatement.setString(6,"category_id");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void remove(int id) {
        Connection connection = ConnectionJDBC.getConnection();
        String sql = "delete from product where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void update(int id, ProductModel productModel) {
        Connection connection = ConnectionJDBC.getConnection();
        String sql = "update emoloyee set name = ?, price = ?,quantity = ?, color = ? ,description = ?,category_id=?  where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productModel.getProduct_name());
            preparedStatement.setInt(2, productModel.getPrice());
            preparedStatement.setInt(3, productModel.getQuantity());
            preparedStatement.setString(4, productModel.getColor());
            preparedStatement.setString(5, productModel.getDescription());
            preparedStatement.setString(5, productModel.getCategory_name());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
