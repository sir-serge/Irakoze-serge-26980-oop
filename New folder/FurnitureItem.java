public class FurnitureItem extends StockItem {
    private double weight; // in kg
    private boolean packed;

    public FurnitureItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, double weight, boolean packed) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Furniture", supplier);
        if (weight <= 0) throw new IllegalArgumentException("Weight must be positive.");
        this.weight = weight;
        this.packed = packed;
    }

    @Override
    public void updateStock(int quantity) {
        if (quantityInStock + quantity < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        quantityInStock += quantity;
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit;
    }

    @Override
    public void generateStockReport() {
        System.out.println("Furniture: " + itemName + " | Qty: " + quantityInStock + " | Value: " + calculateStockValue() + " | Weight: " + weight + "kg | Packed: " + (packed ? "Yes" : "No"));
    }

    @Override
    public boolean validateStock() {
        return packed;
    }
}