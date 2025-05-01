import java.util.ArrayList;
import java.util.List;

public class AccessoriesItem extends ShoppingItem {
    private List<String> varieties;
    private List<String> reviews;
    private List<Integer> ratings;

    public AccessoriesItem(int itemId, String itemName, String itemDescription, double price, int stockAvailable, List<String> varieties) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.varieties = varieties;
        this.reviews = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    @Override
    public void updateStock(int quantity) {
        if (stockAvailable + quantity < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        stockAvailable += quantity;
    }

    @Override
    public void addToCart(Customer customer) {
        if (stockAvailable <= 0) throw new IllegalArgumentException("Out of stock.");
        System.out.println(itemName + " (Accessory) added to " + customer.getCustomerName() + "'s cart.");
    }

    @Override
    public void generateInvoice(Customer customer) {
        System.out.println("Invoice for " + customer.getCustomerName() + ": " + itemName + " - $" + price);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && !varieties.isEmpty();
    }

    public void addReview(String review, int rating) {
        reviews.add(review);
        ratings.add(rating);
    }
}