package controller;

import model.ProductModel;
import service.product.IProducts;
import service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "/ProductController",
        value = "/product"
)
public class ProductController extends HttpServlet {
    private IProducts products = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                break;
            case "update":
                showFromEdit(req, resp);
                break;
            case "remove":
                remove(req, resp);
                break;
            default:
                showAll(req, resp);
        }
    }

    private void showFromEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employee/edit.jsp");
        int index = Integer.parseInt(req.getParameter("id"));
        ProductModel product = products.findById(index);
        req.setAttribute("edit", product);
        requestDispatcher.forward(req, resp);
    }


    private void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("products/list.jsp");
        List<ProductModel> list = products.showAll();
        req.setAttribute("product", list);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                break;
            case "update":
                showFromEdit(req, resp);
                break;
            case "remove":
                remove(req, resp);
                break;
            default:
                showAll(req, resp);
        }
    }
    private void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String color = req.getParameter("color");
        String description = req.getParameter("description");
        String category_id = req.getParameter("category_id");
        ProductModel productModel = new ProductModel(name, price, quantity, color,description,category_id);
        products.create(productModel);
        showAll(req, resp);
    }
    private void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index = Integer.parseInt(req.getParameter("id"));
        products.remove(index);
        req.setAttribute("message","Xóa Thành Công");
        showAll(req, resp);
    }
}
