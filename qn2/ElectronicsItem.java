public class ElectronicsItem extends ShoppingItem {
    private int warrantyMonths;
    private boolean registered;

    public ElectronicsItem(int itemId, String itemName, String itemDescription, double price, int stockAvailable, int warrantyMonths) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        if (warrantyMonths < 0 || warrantyMonths > 60) throw new IllegalArgumentException("Warranty must be 0-60 months.");
        this.warrantyMonths = warrantyMonths;
        this.registered = false;
    }

    @Override
    public void updateStock(int quantity) {
        if (stockAvailable + quantity < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        stockAvailable += quantity;
    }

    @Override
    public void addToCart(Customer customer) {
        if (stockAvailable <= 0) throw new IllegalArgumentException("Out of stock.");
        System.out.println(itemName + " added to " + customer.getCustomerName() + "'s cart.");
    }

    @Override
    public void generateInvoice(Customer customer) {
        System.out.println("Invoice for " + customer.getCustomerName() + ": " + itemName + " - $" + price);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0;
    }
}