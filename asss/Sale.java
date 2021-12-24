public class Sale extends Weapon {

    private String customerIC;
    private String customerName;
    private int quantity;


    //constructor
    public Sale() { this(null,null,null,null,0); }
    
    public Sale(Category catObj, Weapon weaObj, String customerIC,String customerName, int quantity) {
        super(catObj, weaObj.getWeaponID(), weaObj.getWeaponName(), weaObj.getWeaponPrice());
        this.customerIC = customerIC;
        this.customerName = customerName;
        this.quantity = quantity;
    }

    //accessor
    public String getCustomerIC() { return customerIC; }
    public String getCustomerName() { return customerName; }
    public int getQuantity() { return quantity; }

    //mutator
    public void setCustomerIC(String customerIC) { this.customerIC = customerIC; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setQuantity(int quantity) { this.quantity = quantity; } 

    //process
    public double totalPrice() {
        double total = 0.00;

        total = super.getWeaponPrice() * quantity; 

        return total;
    }

    //toString
    public String toString() {
        return "\nCustomer IC: " + customerIC +
        "\nCustomer Name: " + customerName +
        "\nWeapon Bought: " + super.getWeaponName() + "(" + super.getWeaponID() + ")" +
        "\nQuantity: " + quantity +
        "\nTotal Price: RM" + totalPrice(); 
    }
}