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
        super(catObj.getCID(),catObj.getCName());
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


     //check the weapon ID already exist or not in the textfile
     static boolean checkExist(String ID) {
        boolean flag = false;

        File chkfile = new File("weapon.txt");
        boolean exists = chkfile.exists();

        if(exists) {

            LinkedList weaList = getAllWeapon();
            Weapon data = (Weapon)weaList.getHead();

            while(data != null) {
                if(data.getWeaponID().equalsIgnoreCase(ID)) {
                    return true;
                }
                data = (Weapon)weaList.getNext();
            }
        } else {
            flag = false;
        }
        return flag;
    }

    //add weapon to text file
    static void add(Queue data){

        try{
            PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter("weapon.txt", true)));

            while(!data.isEmpty()) {
                Weapon obj = (Weapon)data.dequeue();
                add.println(obj.getWeaponID() + ";" + obj.getWeaponName() + ";" + obj.getCID() + ";" + obj.getWeaponPrice());
            }
            add.close();
            add.flush();
        }
        catch(IOException ioe){
            System.err.println(ioe);
        }
    }

    //extract all data from textfile
    static LinkedList getAllWeapon() {
        LinkedList weaponList = new LinkedList();

        try {
            BufferedReader in = new BufferedReader(new FileReader("weapon.txt"));
            String data = in.readLine();

            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data, ";");

                String weaID =  inputs.nextToken();
                String weaName = inputs.nextToken();
                String catID = inputs.nextToken();
                Double weaPrice = Double.parseDouble(inputs.nextToken());

                //get whole object for category
                Category catObj = Category.search(catID);
                Weapon obj = new Weapon(catObj,weaID,weaName,weaPrice); 
                weaponList.insertAtBack(obj);
                data = in.readLine();
            }
            in.close();
        }catch (IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe);
        }

        return weaponList;
    }

    //search weapon by it's id and return the object
    static Weapon search(String id) {
        
        LinkedList list = getAllWeapon();
        Weapon data = (Weapon)list.getHead();

        while(data != null) {

            if(data.getWeaponID().equalsIgnoreCase(id)) {
                return data;
            }
            data = (Weapon)list.getNext();
        }
        return null;
    }

    //toString
    public String toString() {
        return "\nWeapon ID: " + weaponID +
        "\nWeapon Category ID: " + super.getCID() + "(" + super.getCName() + ")" +
        "\nWeapon Name: " + weaponName +
        "\nWeapon Price : RM" + weaponPrice;
    }
}