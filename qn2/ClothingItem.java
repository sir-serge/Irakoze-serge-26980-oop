import java.util.List;

public class ClothingItem extends ShoppingItem {
    private List<String> sizes;
    private double discount; // 0.0 to 0.5

    public ClothingItem(int itemId, String itemName, String itemDescription, double price, int stockAvailable, List<String> sizes, double discount) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        if (discount < 0 || discount > 0.5) throw new IllegalArgumentException("Discount cannot exceed 50%.");
        this.sizes = sizes;
        this.discount = discount;
    }

    @Override
    public void updateStock(int quantity) {
        if (stockAvailable + quantity < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        stockAvailable += quantity;
    }

    @Override
    public void addToCart(Customer customer) {
        if (stockAvailable <= 0) throw new IllegalArgumentException("Out of stock.");
        System.out.println(itemName + " (Clothing) added to " + customer.getCustomerName() + "'s cart.");
    }

    @Override
    public void generateInvoice(Customer customer) {
        double finalPrice = price * (1 - discount);
        System.out.println("Invoice for " + customer.getCustomerName() + ": " + itemName + " - $" + finalPrice);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && !sizes.isEmpty();
    }
}