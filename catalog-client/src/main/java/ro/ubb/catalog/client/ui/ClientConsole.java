package ro.ubb.catalog.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.core.OrdersList;
import ro.ubb.catalog.core.ProductList;
import ro.ubb.catalog.core.model.OrderProduct;
import ro.ubb.catalog.core.model.Orders;
import ro.ubb.catalog.core.repository.OrderProductSet;

import java.util.Objects;
import java.util.Scanner;

@Component
public class ClientConsole {

    String url = "http://localhost:8080/api/";
    @Autowired
    private RestTemplate restTemplate;

    public void runConsole() {
//        StudentsDto students = restTemplate.getForObject(url, StudentsDto.class);
//        System.out.println(students);
//
//        StudentDto savedStudent = restTemplate.postForObject(url,
//                new StudentDto("saved-st", 10),
//                StudentDto.class);
//        System.out.println("saved student:");
//        System.out.println(savedStudent);
//
//        savedStudent.setName("update-st");
//        restTemplate.put(url + "/{id}", savedStudent, savedStudent.getId());
//        System.out.println("update:");
//        System.out.println(restTemplate.getForObject(url, StudentsDto.class));
//
//        restTemplate.delete(url + "/{id}", savedStudent.getId());
//        System.out.println("delete:");
//        System.out.println(restTemplate.getForObject(url, StudentsDto.class));
        Scanner s = new Scanner(System.in);

        while (true) {
            try {
                printMainMenu();
                System.out.print("option= ");
                int option = s.nextInt();
                switch (option) {
                    case 1 -> PrintOrders();
                    case 2 -> CreateOrder();
                    case 3 -> DeleteOrder();
                    case 4 -> AddOrderProduct();
                    case 5 -> DeleteProductFromOrder();
                    case 6 -> UpdateProductInOrder();
                    case 7 -> GetProductsFromOrder();
                    case 8 -> PrintProducts();
                    case 9 -> FilterByName();
                    case 10 -> FilterByPrice();
                    case 11 -> SortByPrice();
                    case 12 -> {
                        System.out.println("Exiting application...");
                        return;
                    }
                    default -> System.out.println("Invalid option");
                }
            } catch (Exception exception) {
                System.out.println("Exception: " + exception.getMessage());
            }
        }
    }

    private void PrintProducts() {
        var products = Objects.requireNonNull(restTemplate.getForObject(url + "products", ProductList.class)).list;
        if (products != null) {
            if (products.isEmpty())
                System.out.println("The are no products!");
            else {
                System.out.println("Products:");
                products.forEach(System.out::println);
            }
        } else {
            System.out.println("There was an error while trying to retrieve the list of products!");
        }
    }

    private void UpdateProductInOrder() {
        Long orderProductId;
        Integer orderId, quantity;

        var scanner = new Scanner(System.in);
        try {
            System.out.print("id= ");
            orderId = scanner.nextInt();

            var orderProducts = Objects.requireNonNull(this.restTemplate.getForObject(url + "orderProducts/order/{orderId}", OrderProductSet.class, orderId)).set;

            if (orderProducts.isEmpty()) {
                System.out.println("There are no products in this order");
                return;
            }

            orderProducts.forEach(System.out::println);

            System.out.print("orderProductId= ");
            orderProductId = scanner.nextLong();
            System.out.print("newQuantity= ");
            quantity = scanner.nextInt();
            var productId = orderProducts.stream().filter(p -> Objects.equals(p.getId(), orderProductId)).findFirst();

            if (productId.isEmpty()) {
                throw new Exception("There in no product with this Id");
            }

            var op = new OrderProduct();
            op.setId(orderProductId);
            op.setQuantity(quantity);
            op.setOrderID(orderId);
            op.setProductID(productId.get().getProductID());

            this.restTemplate.put(url + "orderProducts", op, OrderProduct.class);
            System.out.println("Product was added to the order!");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void GetProductsFromOrder() {
        int orderId;

        var scanner = new Scanner(System.in);
        try {
            System.out.print("id= ");
            orderId = scanner.nextInt();

            var set = Objects.requireNonNull(this.restTemplate.getForObject(url + "orderProducts/order/{orderId}", OrderProductSet.class, orderId)).set;

            if (set.isEmpty()) {
                System.out.println("There are no products for this order");
                return;
            }

            System.out.println("Products list:");
            set.forEach(System.out::println);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void DeleteProductFromOrder() {
        Long orderId, orderProductId;

        var scanner = new Scanner(System.in);
        try {
            System.out.print("id= ");
            orderId = scanner.nextLong();

            var set = Objects.requireNonNull(this.restTemplate.getForObject(
                    url + "orderProducts/order/{orderId}", OrderProductSet.class, orderId)).set;
            ;
            if (set.isEmpty()) {
                System.out.println("There are not products for this order");
                return;
            }
            System.out.println("Products list:");
            set.forEach(System.out::println);

            System.out.print("orderProductId= ");
            orderProductId = scanner.nextLong();

            this.restTemplate.delete(url + "orderProducts/{orderProductId}", orderProductId);
            System.out.println("Order product was deleted successfully!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void AddOrderProduct() {
        Integer orderId, productId;
        Integer quantity;
        var scanner = new Scanner(System.in);
        try {
            System.out.print("orderId= ");
            orderId = scanner.nextInt();
            System.out.print("productId= ");
            productId = scanner.nextInt();
            System.out.print("quantity= ");
            quantity = scanner.nextInt();

            this.restTemplate.postForEntity(url + "orderProducts", new OrderProduct(orderId, productId, quantity), OrderProduct.class);
            System.out.println("Order product was added successfully!!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void DeleteOrder() {
        Long id;
        var scanner = new Scanner(System.in);
        try {
            System.out.print("id= ");
            id = scanner.nextLong();


            this.restTemplate.delete(url + "orders/{orderId}", id);
            System.out.println("Order was deleted successfully!");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void PrintOrders() {
        var res = Objects.requireNonNull(restTemplate.getForObject(url + "orders", OrdersList.class)).list;
        if (res.isEmpty()) {
            System.out.println("There are no orders!");
        } else {
            System.out.println("Orders: ");
            res.forEach(System.out::println);
        }
    }

    private void CreateOrder() {
        String details;
        var scanner = new Scanner(System.in);

        System.out.print("details= ");
        details = scanner.next();

        var order = new Orders(details);

        this.restTemplate.postForEntity(url + "orders", order, Orders.class);
        System.out.println("Order was created successfully!!");
    }

    private void FilterByName() {
        String name;
        var scanner = new Scanner(System.in);

        System.out.print("search by= ");
        name = scanner.nextLine();

        var products = Objects.requireNonNull(restTemplate.getForObject(url + "products/filterByName/{name}", ProductList.class, name)).list;
        if (products != null) {
            if (products.isEmpty())
                System.out.println("The are no products!");
            else {
                System.out.println("Products:");
                products.forEach(System.out::println);
            }
        } else {
            System.out.println("There was an error while trying to retrieve the list of products!");
        }
    }

    private void FilterByPrice() {
        double price;
        var scanner = new Scanner(System.in);

        System.out.print("minimum price= ");
        price = scanner.nextDouble();

        var products = Objects.requireNonNull(restTemplate.getForObject(url + "products/filterByPrice/{price}", ProductList.class, price)).list;
        if (products != null) {
            if (products.isEmpty())
                System.out.println("The are no products!");
            else {
                System.out.println("Products:");
                products.forEach(System.out::println);
            }
        } else {
            System.out.println("There was an error while trying to retrieve the list of products!");
        }
    }

    private void SortByPrice() {
        var products = Objects.requireNonNull(restTemplate.getForObject(url + "sortProducts", ProductList.class)).list;
        if (products != null) {
            if (products.isEmpty())
                System.out.println("The are no products!");
            else {
                System.out.println("Sorted products:");
                products.forEach(System.out::println);
            }
        } else {
            System.out.println("There was an error while trying to retrieve the list of products!");
        }
    }

    private void printMainMenu() {
        System.out.println(
                "1: Print Orders\n" +
                        "2: Create Order\n" +
                        "3: Delete Order\n" +
                        "4: Add Product in order\n" +
                        "5: Delete Product from order\n" +
                        "6: Update product in order\n" +
                        "7: Get products for order\n" +
                        "8: Print all products\n" +
                        "9: Search products by name\n" +
                        "10: Filter products by minimum price\n" +
                        "11: Sort products by price\n" +
                        "12: exit\n");
    }
}
