//IO stuffs
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

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


     //check the product ID already exist or not in the textfile
     static boolean checkExist(String ID) { //check if product exist or not
        boolean flag = false;

        File chkfile = new File("product.txt");
        boolean exists = chkfile.exists();

        if(exists) {
            try {
                BufferedReader inpt = new BufferedReader(new FileReader("weapon.txt"));
                String data = inpt.readLine();
                
                while(data != null) {
                    StringTokenizer inputs = new StringTokenizer(data,";");
                    String tempID = inputs.nextToken();
    
                    if(tempID.equalsIgnoreCase(ID)) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                    data = inpt.readLine();
                }
                inpt.close();
            } catch (IOException ioe) {
                System.err.println("Something went wrong!");
            }
        } else {
            flag = false;
        }
        return flag;
    }


    //toString
    public String toString() {
        return "\nWeapon ID: " + weaponID +
        "\nWeapon Category ID: " + super.getCatID() + "(" + super.getCatName() + ")" +
        "\nWeapon Name: " + weaponName +
        "\nWeapon Price : RM" + weaponPrice;
    }
}