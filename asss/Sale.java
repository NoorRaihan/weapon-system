//IO stuffs
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

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

    //add pruchase to the textfile
    static void add(Queue data) {
        try{
            PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter("sales.txt", true)));
            
            while(!data.isEmpty()) {
                Sale obj = (Sale)data.dequeue();
                add.println(obj.getCustomerIC() + ";" + obj.getCustomerName() + ";" + obj.getWeaponID() + ";" + obj.getQuantity());
            }
            add.close();
            add.flush();
        }
        catch(IOException ioe){
            System.err.println(ioe);
        }
    }

    //delete the sale record tetfile
    static void delete(Queue data) {
        File oriFile = new File("sales.txt");
        oriFile.delete();
        
        try{
            PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter("sales.txt", true)));
            
            while(!data.isEmpty()) {
                Sale obj = (Sale)data.dequeue();
                add.println(obj.getCustomerIC() + ";" + obj.getCustomerName() + ";" + obj.getWeaponID() + ";" + obj.getQuantity());
            }
            add.close();
            add.flush();
        }
        catch(IOException ioe){
            System.err.println(ioe);
        }
    }

    //get all sales record
    static LinkedList getAllSales() {
        LinkedList saleList = new LinkedList();

        try {
            BufferedReader in = new BufferedReader(new FileReader("sales.txt"));
            String data = in.readLine();

            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data, ";");

                String ic = inputs.nextToken();
                String name = inputs.nextToken();
                String weaID = inputs.nextToken();
                int quantity = Integer.parseInt(inputs.nextToken());

                //get obj for weapon
                Weapon weaObj = Weapon.search(weaID);
                //get obj for category
                Category catObj = Category.search(weaObj.getCID());
                
                Sale obj = new Sale(catObj,weaObj,ic,name,quantity);
                saleList.insertAtBack(obj);
                data = in.readLine();
            }
            in.close();
        }catch (IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe);
        }

        return saleList;
    }

    //search customer sale record
    static LinkedList searchRecord(String ic) {

        LinkedList record = new LinkedList();
        LinkedList saleList = getAllSales();

        Sale data = (Sale)saleList.getHead();
        while(data != null) {

            if(data.getCustomerIC().equals(ic)) {
                record.insertAtBack(data);
            }

            data = (Sale)saleList.getNext();
        }
        return record;
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