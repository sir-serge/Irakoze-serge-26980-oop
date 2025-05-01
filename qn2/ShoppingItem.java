public abstract class ShoppingItem {
    protected int itemId;
    protected String itemName;
    protected String itemDescription;
    protected double price;
    protected int stockAvailable;

    public ShoppingItem(int itemId, String itemName, String itemDescription, double price, int stockAvailable) {
        if (price <= 0) throw new IllegalArgumentException("Price must be above zero.");
        if (stockAvailable < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.stockAvailable = stockAvailable;
    }

    public abstract void updateStock(int quantity);
    public abstract void addToCart(Customer customer);
    public abstract void generateInvoice(Customer customer);
    public abstract boolean validateItem();

    public int getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public int getStockAvailable() { return stockAvailable; }
    public double getPrice() { return price; }
}