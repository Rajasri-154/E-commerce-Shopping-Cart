import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private double price;
    private String description;

    public Product(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}

class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        items.add(product);
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public List<Product> getItems() {
        return items;
    }
}

class User {
    private String username;
    private String password;
    private ShoppingCart cart;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.cart = new ShoppingCart();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ShoppingCart getCart() {
        return cart;
    }
}

class ProductCatalog {
    private List<Product> products;

    public ProductCatalog() {
        products = new ArrayList<>();
        // Add some sample products
        products.add(new Product(1, "Laptop", 999.99, "High-performance laptop"));
        products.add(new Product(2, "Smartphone", 499.99, "Latest smartphone model"));
        products.add(new Product(3, "Headphones", 99.99, "Noise-canceling headphones"));
        products.add(new Product(4, "Tablet", 299.99, "10-inch tablet with touch screen"));
        products.add(new Product(5, "Smartwatch", 199.99, "Fitness tracker with heart rate monitor"));
        products.add(new Product(6, "Wireless Mouse", 29.99, "Ergonomic wireless mouse"));
        products.add(new Product(7, "External Hard Drive", 79.99, "1TB portable external hard drive"));
        products.add(new Product(8, "Wireless Earbuds", 149.99, "True wireless earbuds with charging case"));
        products.add(new Product(9, "Gaming Console", 399.99, "Next-gen gaming console with VR support"));
        products.add(new Product(10, "Printer", 149.99, "All-in-one inkjet printer with Wi-Fi connectivity"));
    }

    public List<Product> getProducts() {
        return products;
    }
}

public class ecommerce {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        System.out.println("Welcome to the E-Commerce System!");

        ProductCatalog productCatalog = new ProductCatalog();
        showProductCatalog(productCatalog);

        // Simulate user authentication
        authenticateUser();

        // Show user options
        showMenu(productCatalog);
    }

    private static void showProductCatalog(ProductCatalog productCatalog) {
        System.out.println("\nProduct Catalog:");
        List<Product> products = productCatalog.getProducts();
        for (Product product : products) {
            System.out.println("ID: " + product.getId() + " - " + product.getName() + " - $" + product.getPrice());
            System.out.println("Description: " + product.getDescription());
            System.out.println("-----------------------------------");
        }
    }

    private static void authenticateUser() {
        System.out.println("\nPlease login:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Simulate user authentication
        currentUser = new User(username, password);
    }

    private static void showMenu(ProductCatalog productCatalog) {
        boolean running = true;
        while (running) {
            System.out.println("\n1. Add product to cart");
            System.out.println("2. View cart");
            System.out.println("3. Checkout");
            System.out.println("4. Logout");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProductToCart(productCatalog);
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    checkout();
                    break;
                case 4:
                    running = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addProductToCart(ProductCatalog productCatalog) {
        System.out.print("Enter the ID of the product you want to add to cart: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Product> products = productCatalog.getProducts();
        Product selectedProduct = null;
        for (Product product : products) {
            if (product.getId() == productId) {
                selectedProduct = product;
                break;
            }
        }

        if (selectedProduct != null) {
            currentUser.getCart().addProduct(selectedProduct);
            System.out.println(selectedProduct.getName() + " added to cart successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void viewCart() {
        ShoppingCart cart = currentUser.getCart();
        List<Product> items = cart.getItems();

        System.out.println("\nCart:");
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (Product item : items) {
                System.out.println("ID: " + item.getId() + " - " + item.getName() + " - $" + item.getPrice());
            }
            System.out.println("Total: $" + cart.calculateTotal());
        }
    }

    private static void checkout() {
        ShoppingCart cart = currentUser.getCart();
        List<Product> items = cart.getItems();

        if (items.isEmpty()) {
            System.out.println("Your cart is empty. Cannot proceed to checkout.");
        } else {
            System.out.println("Checkout completed successfully. Thank you for shopping with us!");
            // Clear the cart after checkout
            items.clear();
        }
    }
}
