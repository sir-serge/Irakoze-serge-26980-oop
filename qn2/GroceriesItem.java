import java.time.LocalDate;

public class GroceriesItem extends ShoppingItem {
    private LocalDate expirationDate;
    private double bulkDiscount; // 0.0 to 0.5

    public GroceriesItem(int itemId, String itemName, String itemDescription, double price, int stockAvailable, LocalDate expirationDate, double bulkDiscount) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.expirationDate = expirationDate;
        this.bulkDiscount = bulkDiscount;
    }

    @Override
    public void updateStock(int quantity) {
        if (stockAvailable + quantity < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        stockAvailable += quantity;
    }

    @Override
    public void addToCart(Customer customer) {
        if (stockAvailable <= 0) throw new IllegalArgumentException("Out of stock.");
        if (expirationDate.isBefore(LocalDate.now())) throw new IllegalArgumentException("Item expired.");
        System.out.println(itemName + " (Groceries) added to " + customer.getCustomerName() + "'s cart.");
    }

    @Override
    public void generateInvoice(Customer customer) {
        double finalPrice = price * (1 - bulkDiscount);
        System.out.println("Invoice for " + customer.getCustomerName() + ": " + itemName + " - $" + finalPrice);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && !expirationDate.isBefore(LocalDate.now());
    }
}