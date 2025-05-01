import java.util.*;
import java.time.LocalDate;

public class OnlineShoppingSystem {
    private static List<ShoppingItem> items = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<ShoppingCart> carts = new ArrayList<>();
    private static List<Payment> payments = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Advanced Online Shopping System ---");
            System.out.println("1. Register Customer");
            System.out.println("2. Add Shopping Item");
            System.out.println("3. Create Shopping Cart");
            System.out.println("4. Add Item to Cart");
            System.out.println("5. Process Payment");
            System.out.println("6. Generate Reports");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1: registerCustomer(); break;
                case 2: addShoppingItem(); break;
                case 3: createShoppingCart(); break;
                case 4: addItemToCart(); break;
                case 5: processPayment(); break;
                case 6: generateReports(); break;
                case 7: System.exit(0);
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void registerCustomer() {
        System.out.println("\n--- Customer Registration ---");
        System.out.print("Customer ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();

        try {
            Customer customer = new Customer(id, name, email, address, phone);
            customers.add(customer);
            System.out.println("Customer registered successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addShoppingItem() {
        System.out.println("\n--- Add Shopping Item ---");
        System.out.println("Select Category: 1-Electronics 2-Clothing 3-Groceries 4-Books 5-Accessories");
        int cat = sc.nextInt(); sc.nextLine();
        System.out.print("Item ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Item Name: "); String name = sc.nextLine();
        System.out.print("Description: "); String desc = sc.nextLine();
        System.out.print("Price: "); double price = sc.nextDouble();
        System.out.print("Stock Available: "); int stock = sc.nextInt(); sc.nextLine();

        try {
            ShoppingItem item = null;
            switch (cat) {
                case 1:
                    System.out.print("Warranty (months): "); int warranty = sc.nextInt(); sc.nextLine();
                    item = new ElectronicsItem(id, name, desc, price, stock, warranty);
                    break;
                case 2:
                    System.out.print("Sizes (comma separated): "); List<String> sizes = Arrays.asList(sc.nextLine().split(","));
                    System.out.print("Discount (0-0.5): "); double discount = sc.nextDouble(); sc.nextLine();
                    item = new ClothingItem(id, name, desc, price, stock, sizes, discount);
                    break;
                case 3:
                    System.out.print("Expiration date (YYYY-MM-DD): "); LocalDate exp = LocalDate.parse(sc.nextLine());
                    System.out.print("Bulk Discount (0-0.5): "); double bulk = sc.nextDouble(); sc.nextLine();
                    item = new GroceriesItem(id, name, desc, price, stock, exp, bulk);
                    break;
                case 4:
                    System.out.print("ISBN: "); String isbn = sc.nextLine();
                    System.out.print("Edition: "); String edition = sc.nextLine();
                    System.out.print("Print Quality: "); String pq = sc.nextLine();
                    item = new BooksItem(id, name, desc, price, stock, isbn, edition, pq);
                    break;
                case 5:
                    System.out.print("Varieties (comma separated): "); List<String> varieties = Arrays.asList(sc.nextLine().split(","));
                    item = new AccessoriesItem(id, name, desc, price, stock, varieties);
                    break;
                default: System.out.println("Invalid category."); return;
            }
            items.add(item);
            System.out.println("Item added successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createShoppingCart() {
        System.out.println("\n--- Create Shopping Cart ---");
        System.out.print("Cart ID: ");
        int cartId = sc.nextInt(); sc.nextLine();
        System.out.print("Customer ID: ");
        int customerId = sc.nextInt(); sc.nextLine();

        Customer customer = findCustomer(customerId);
        if (customer != null) {
            ShoppingCart cart = new ShoppingCart(cartId, customer);
            carts.add(cart);
            System.out.println("Shopping cart created successfully!");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void addItemToCart() {
        System.out.println("\n--- Add Item to Cart ---");
        System.out.print("Cart ID: ");
        int cartId = sc.nextInt(); sc.nextLine();
        System.out.print("Item Name: ");
        String itemName = sc.nextLine();

        ShoppingCart cart = findCart(cartId);
        ShoppingItem item = findItem(itemName);

        if (cart != null && item != null) {
            cart.addItem(item);
        } else {
            System.out.println("Cart or Item not found.");
        }
    }

    private static void processPayment() {
        System.out.println("\n--- Process Payment ---");
        System.out.print("Cart ID: ");
        int cartId = sc.nextInt(); sc.nextLine();
        System.out.print("Payment ID: ");
        int paymentId = sc.nextInt(); sc.nextLine();
        System.out.print("Payment Method (CREDIT_CARD/DEBIT_CARD/PAYPAL/BANK_TRANSFER): ");
        String paymentMethod = sc.nextLine();

        ShoppingCart cart = findCart(cartId);
        if (cart != null) {
            double amount = cart.calculateTotal();
            System.out.println("Total Amount: $" + amount);
            System.out.print("Enter amount to pay: ");
            double amountPaid = sc.nextDouble(); sc.nextLine();

            Payment payment = new Payment(paymentId, paymentMethod, amountPaid);
            if (payment.processPayment(amount)) {
                payments.add(payment);
                System.out.println("Payment successful!");
            } else {
                System.out.println("Payment failed.");
            }
        } else {
            System.out.println("Cart not found.");
        }
    }

    private static void generateReports() {
        System.out.println("\n--- System Reports ---");
        System.out.println("1. Total Revenue");
        System.out.println("2. Item Sales");
        System.out.println("3. Customer Orders");
        System.out.println("4. Payment Breakdown");
        System.out.print("Choose report type: ");
        
        int choice = sc.nextInt(); sc.nextLine();
        
        switch (choice) {
            case 1:
                double totalRevenue = payments.stream()
                    .filter(Payment::isProcessed)
                    .mapToDouble(Payment::getAmountPaid)
                    .sum();
                System.out.println("Total Revenue: $" + totalRevenue);
                break;
            case 2:
                System.out.println("\nItem Sales:");
                for (ShoppingItem item : items) {
                    System.out.println(item.getItemName() + ": $" + item.getPrice());
                }
                break;
            case 3:
                System.out.println("\nCustomer Orders:");
                for (ShoppingCart cart : carts) {
                    System.out.println(cart.toString());
                }
                break;
            case 4:
                System.out.println("\nPayment Breakdown:");
                Map<String, Double> paymentBreakdown = new HashMap<>();
                for (Payment payment : payments) {
                    if (payment.isProcessed()) {
                        paymentBreakdown.merge(payment.getPaymentMethod(), 
                            payment.getAmountPaid(), Double::sum);
                    }
                }
                paymentBreakdown.forEach((method, amount) -> 
                    System.out.println(method + ": $" + amount));
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static Customer findCustomer(int customerId) {
        return customers.stream()
            .filter(c -> c.getCustomerId() == customerId)
            .findFirst()
            .orElse(null);
    }

    private static ShoppingCart findCart(int cartId) {
        return carts.stream()
            .filter(c -> c.getCartId() == cartId)
            .findFirst()
            .orElse(null);
    }

    private static ShoppingItem findItem(String itemName) {
        return items.stream()
            .filter(i -> i.getItemName().equalsIgnoreCase(itemName))
            .findFirst()
            .orElse(null);
    }
}