import java.time.LocalDate;
import java.util.*;

public class StockManagementSystem {
    private static List<StockItem> stockItems = new ArrayList<>();
    private static Set<String> productNames = new HashSet<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Stock Management System ---");
            System.out.println("1. Add Stock Item");
            System.out.println("2. Update Stock");
            System.out.println("3. Generate Inventory Report");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: addStockItem(); break;
                case 2: updateStock(); break;
                case 3: generateReport(); break;
                case 4: System.exit(0);
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void addStockItem() {
        System.out.println("Select Category: 1-Electronics 2-Clothing 3-Grocery 4-Furniture 5-Perishable");
        int cat = sc.nextInt(); sc.nextLine();
        System.out.print("Item ID: "); String id = sc.nextLine();
        System.out.print("Item Name: "); String name = sc.nextLine();
        if (productNames.contains(name)) { System.out.println("Product name must be unique."); return; }
        System.out.print("Quantity: "); int qty = sc.nextInt();
        System.out.print("Price per unit: "); double price = sc.nextDouble(); sc.nextLine();
        System.out.print("Supplier: "); String supplier = sc.nextLine();

        try {
            StockItem item = null;
            switch (cat) {
                case 1:
                    System.out.print("Warranty (months): "); int warranty = sc.nextInt();
                    System.out.print("Discount (0-0.5): "); double ediscount = sc.nextDouble(); sc.nextLine();
                    item = new ElectronicsItem(id, name, qty, price, supplier, warranty, ediscount);
                    break;
                case 2:
                    System.out.print("Sizes (comma separated): "); List<String> sizes = Arrays.asList(sc.nextLine().split(","));
                    System.out.print("Colors (comma separated): "); List<String> colors = Arrays.asList(sc.nextLine().split(","));
                    System.out.print("Discount (0-0.5): "); double cdiscount = sc.nextDouble(); sc.nextLine();
                    item = new ClothingItem(id, name, qty, price, supplier, sizes, colors, cdiscount);
                    break;
                case 3:
                    System.out.print("Expiration date (YYYY-MM-DD): "); LocalDate exp = LocalDate.parse(sc.nextLine());
                    item = new GroceryItem(id, name, qty, price, supplier, exp);
                    break;
                case 4:
                    System.out.print("Weight (kg): "); double weight = sc.nextDouble();
                    System.out.print("Packed (true/false): "); boolean packed = sc.nextBoolean(); sc.nextLine();
                    item = new FurnitureItem(id, name, qty, price, supplier, weight, packed);
                    break;
                case 5:
                    System.out.print("Shelf life (days): "); int shelf = sc.nextInt(); sc.nextLine();
                    System.out.print("Added date (YYYY-MM-DD): "); LocalDate added = LocalDate.parse(sc.nextLine());
                    item = new PerishableItem(id, name, qty, price, supplier, shelf, added);
                    break;
                default: System.out.println("Invalid category."); return;
            }
            stockItems.add(item);
            productNames.add(name);
            System.out.println("Item added successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateStock() {
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        for (StockItem item : stockItems) {
            if (item.getItemName().equalsIgnoreCase(name)) {
                System.out.print("Enter quantity to add/remove: ");
                int qty = sc.nextInt(); sc.nextLine();
                try {
                    item.updateStock(qty);
                    System.out.println("Stock updated.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Item not found.");
    }

    private static void generateReport() {
        System.out.println("\n--- Inventory Report ---");
        for (StockItem item : stockItems) {
            item.generateStockReport();
        }
        System.out.println("--- End of Report ---");
    }
}