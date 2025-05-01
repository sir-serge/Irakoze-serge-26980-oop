public abstract class StockItem {
    protected String itemId, itemName, category, supplier;
    protected int quantityInStock;
    protected double pricePerUnit;

    public StockItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier) {
        if (quantityInStock < 0) throw new IllegalArgumentException("Stock quantity cannot be negative.");
        if (pricePerUnit <= 0) throw new IllegalArgumentException("Price per unit must be above zero.");
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantityInStock = quantityInStock;
        this.pricePerUnit = pricePerUnit;
        this.category = category;
        this.supplier = supplier;
    }

    public abstract void updateStock(int quantity);
    public abstract double calculateStockValue();
    public abstract void generateStockReport();
    public abstract boolean validateStock();

    public String getItemName() { return itemName; }
    public int getQuantityInStock() { return quantityInStock; }
    public String getCategory() { return category; }
}