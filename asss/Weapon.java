public class Weapon extends Category {

    private String weaponID;
    private String weaponName;
    private double weaponPrice;

    public Weapon() { this(null,null,null,0.00); }

    public Weapon(Category catObj, String weaponID, String weaponName, double weaponPrice) {
        super(catObj.getCatID(),catObj.getCatName());
        this.weaponID = weaponID;
        this.weaponName = weaponName;
        this.weaponPrice = weaponPrice;
    }

    //accessor
    public String getWeaponID() { return weaponID; }
    public String getWeaponName() { return weaponName; }
    public double getWeaponPrice() { return weaponPrice; }

    //mutator
    public void setWeaponID(String weaponID) { this.weaponID = weaponID; }
    public void setWeaponName(String weaponName) { this.weaponName = weaponName; }
    public void setWeaponPrice(double weaponPrice) { this.weaponPrice = weaponPrice; }

    //toString
    public String toString() {
        return "\nWeapon ID: " + weaponID +
        "\nWeapon Category ID: " + super.getCatID() + "(" + super.getCatName() + ")" +
        "\nWeapon Name: " + weaponName +
        "\nWeapon Price : RM" + weaponPrice;
    }
}