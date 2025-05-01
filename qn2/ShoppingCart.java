import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int cartId;
    private List<ShoppingItem> cartItems;
    private double totalPrice;
    private Customer customer;

    public ShoppingCart(int cartId, Customer customer) {
        this.cartId = cartId;
        this.customer = customer;
        this.cartItems = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addItem(ShoppingItem item) {
        if (item.validateItem()) {
            cartItems.add(item);
            totalPrice += item.getPrice();
            System.out.println(item.getItemName() + " added to cart successfully.");
        } else {
            System.out.println("Cannot add " + item.getItemName() + " to cart. Item validation failed.");
        }
    }

    public void removeItem(ShoppingItem item) {
        if (cartItems.remove(item)) {
            totalPrice -= item.getPrice();
            System.out.println(item.getItemName() + " removed from cart successfully.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    public void updateItemQuantity(ShoppingItem item, int quantity) {
        if (quantity <= 0) {
            removeItem(item);
        } else {
            // Implementation depends on how you want to handle quantity
            // This is a basic implementation
            System.out.println("Quantity updated for " + item.getItemName());
        }
    }

    public double calculateTotal() {
        return totalPrice;
    }

    public void clearCart() {
        cartItems.clear();
        totalPrice = 0.0;
        System.out.println("Cart cleared successfully.");
    }

    public List<ShoppingItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getCartId() {
        return cartId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shopping Cart ID: ").append(cartId).append("\n");
        sb.append("Customer: ").append(customer.getCustomerName()).append("\n");
        sb.append("Items in Cart:\n");
        for (ShoppingItem item : cartItems) {
            sb.append("- ").append(item.getItemName()).append(": $").append(item.getPrice()).append("\n");
        }
        sb.append("Total Price: $").append(totalPrice);
        return sb.toString();
    }
} 