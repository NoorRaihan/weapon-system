//IO stuffs
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
//normal stuff
import java.util.Scanner;

public class Main {

    //return true or false depends on the choice yes or no
    static boolean choicePicker(String choice) {
        boolean flag = false;

        if(choice.equalsIgnoreCase("yes")) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    //check specific String element in queue
    static boolean inputValidate(LinkedList data, String key) {

        String temp = (String)data.getHead();
        while(temp != null) {
            if(temp.equalsIgnoreCase(key)) {
                return true;
            }
            temp = (String)data.getNext();
        }

        return false;
    }

    //insert category
    static void addCategory(){
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);

        int count = Category.getAllCategory().size();
        Queue data = new Queue();
        LinkedList tempID = new LinkedList();
        char choice = 'y';
        boolean check =  true;
        String cID = null;
        String cName = null;

        while(choice == 'y' || choice == 'Y')
        {
            
            
            if(count > 4)
            {
                System.out.println("Ehem..Can't exceed 5 categories <(｀^´)>");
                break;
            }
            else
            {
                while(check){
                    System.out.print("\nCategory ID : ");
                    cID = in.nextLine();
                    boolean exist = Category.checkExist(cID);
                    boolean inserted = inputValidate(tempID, cID);
    
                    if(exist || inserted)
                    {
                        System.out.println("Already exist !");
                    }
                    else
                    {
                        tempID.insertAtBack(cID);
                        check = false;
                        break;
                    }
                }
    
                System.out.print("Category Name : ");
                cName = in.nextLine();
    
                Category obj = new Category(cID, cName);
                data.enqueue(obj);

                System.out.println("Want to add more Category ? \n[Y] - yessir \n[N] - nossir");
                choice = in.nextLine().charAt(0);

            }
            count++;
        }

        //add function go here lmbfao
        if(cID != null)
        {
            System.out.println("Are you confirm ? (⌐■_■)\n[Y] - yessir \n[N] - nossir");
            choice = in.nextLine().charAt(0);
            if(choice == 'y' || choice == 'Y' )
            {
                Category.add(data);
                System.out.println("Succcessfully saved !");
                pressAnyKey();
                adminMenu();
            }
            else
                adminMenu();
        }
        else
        {
            pressAnyKey();
            adminMenu();
        }
        
    }

    //insert weapon 
    static void addWeapon(){
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);

        Queue data = new Queue();
        LinkedList tempID = new LinkedList();
        boolean check =  true;
        String cID = null;
        String wID = null;
        String wName = null;
        double wPrice = 0;
        Category catobj = null;
        char choice = 'y';

        while(choice == 'y' || choice == 'Y')
        {

            while(check){
                System.out.print("\nWeapon ID : ");
                wID = in.nextLine();
                boolean exist = Weapon.checkExist(wID);
                boolean inserted = inputValidate(tempID, wID);

                if(exist || inserted)
                {
                    System.out.println("Already exist !");
                }
                else
                {
                    tempID.insertAtBack(wID);
                    check = false;
                    break;
                }
            }

            while(catobj == null){
                System.out.print("Category ID : ");
                cID = in.nextLine();
                catobj = Category.search(cID);
                if(catobj == null)
                {
                    System.out.println("Category is not exist !");
                }
                
            }

            System.out.print("Weapon Name : ");
            wName = in.nextLine();

            System.out.print("Weapon Price : RM");
            wPrice = Double.parseDouble(in.nextLine());

            Weapon obj = new Weapon(catobj, wID, wName, wPrice);
            data.enqueue(obj);

            System.out.println("Want to add more Weapon ? \n[Y] - yessir \n[N] - nossir");
            choice = in.nextLine().charAt(0);
        }

        System.out.println("Are you confirm ? (⌐■_■)\n[Y] - yessir \n[N] - nossir");
        choice = in.nextLine().charAt(0);
        if(choice == 'y' || choice == 'Y' )
        {
            Weapon.add(data);
            System.out.println("Succcessfully saved !");
            pressAnyKey();
            adminMenu();
        }
        else
            adminMenu();
    }

    //delayed the process
     static void tunggu(int ms) {
        try {
            Thread.sleep(ms);
        }catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
       }
    }

    //for press any key to continue.... hehe
    static void pressAnyKey() {
        System.out.println("\nPress Enter key to continue... <(￣︶￣)>");
        try
        {
            System.in.read();
        }  
        catch(Exception e){
            System.err.println("went wrong!");
        }  
    }

    //display all the weapon
    static void displayAllWeapon() {
        System.out.print("\u000C");
        LinkedList weaList = Weapon.getAllWeapon();
        Weapon data = (Weapon)weaList.getHead();

        while(data != null) {
            System.out.println(data.toString());
            data = (Weapon)weaList.getNext();
        }
    }

    //display all the weapon by the category
    static void displayAllWeaponByCategory() {
        System.out.print("\u000C");
        LinkedList weaList = Weapon.getAllWeapon();
        LinkedList catList = Category.getAllCategory();
        Category catData = (Category)catList.getHead();
        Weapon weaData = (Weapon)weaList.getHead();
        

        while(catData != null) {
            System.out.println("\n\n===========" + catData.getCName() + "===========");
            weaData = (Weapon)weaList.getHead();
            while(weaData != null) {
                if(weaData.getCID().equalsIgnoreCase(catData.getCID())) {
                    System.out.println(weaData.toString());
                }
                weaData = (Weapon)weaList.getNext();
            }
            catData = (Category)catList.getNext();
        }
    }

    //add customer purchase to textfile
    static void purchase() {
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        Queue data = new Queue();

        String customerIC = null;
        String customerName = null;
        String weaID = null;
        int quantity = 0;
        double total = 0.00;
        Category catObj = null;
        Weapon weaObj = null;
        char choice = 'y';

        System.out.print("Customer's IC: ");
        customerIC = in.nextLine();

        System.out.print("Customer's Name: ");
        customerName = in.nextLine();
        while(choice == 'y' || choice == 'Y') {

            while(true) {
                System.out.print("Weapon ID: ");
                weaID = in.nextLine();
                weaObj = Weapon.search(weaID);

                if(weaObj != null) {
                    System.out.println("\n======== WEAPON DETAIL ======== ");
                    System.out.println(weaObj.toString());
                    break;
                } else {
                    System.out.println("Weapon ID is invalid or not exist!");
                }
            }

            System.out.print("Quantity: ");
            quantity = Integer.parseInt(in.nextLine());

            catObj = Category.search(weaObj.getCID());
            Sale obj = new Sale(catObj,weaObj,customerIC,customerName,quantity);
            data.enqueue(obj);

            total += obj.totalPrice();
            System.out.println("=========== Total Price: RM" + total + " ===========");
            System.out.println("\nShopping more ? \n[Y] - yessir \n[N] - nossir");
            choice = in.nextLine().charAt(0);
            if(choice == 'y' || choice == 'Y') {
                System.out.print("\u000C");
                System.out.println("Customer's IC: " + customerIC);
                System.out.println("Customer's Name: " + customerName + "\n");
            }
        }
        System.out.println("Are you confirm ? (⌐■_■)\n[Y] - yessir \n[N] - nossir");
        choice = in.nextLine().charAt(0);
        if(choice == 'y' || choice == 'Y' )
        {
            Sale.add(data);
            System.out.println("Purchase checkout successfully!");
            pressAnyKey();
            adminMenu();
        }
        else
            customerMenu();
    }

    //search the the sales record
    static void searchSalesRecord() {
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        String ic;
        double total = 0.00;

        System.out.println("Customer IC: ");
        ic = in.nextLine();

        LinkedList record = Sale.searchRecord(ic);
        Sale data = (Sale)record.getHead();
        System.out.println("============= RESULT =============");

        if(data == null) {
            System.out.println("IC does not exist in the record!");
            pressAnyKey();
            adminMenu();
        } else {

            while(data != null) {
                System.out.println(data.toString());
                total += data.totalPrice();
                data = (Sale)record.getNext();
            }
            System.out.println("\n=========== Total Purchase: RM" + total + " ===========");
            pressAnyKey();
            adminMenu();
        }
    }


    //display the main menu
    static void mainMenu() {
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        System.out.println("\n[1] Customer");
        System.out.println("[2] Terminator Guy");
        System.out.println("[99] Hasta la vista");

        boolean notValid = true;
        int choice;

        while(notValid) {

            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());
            switch(choice) {
                case 1:
                    notValid = false;
                    //Customer menu function go here
                    customerMenu();
                    break;
                case 2:
                    notValid = false;
                    //admin menu go here
                    adminMenu();
                    break;
                case 99:
                    notValid = false;
                    System.exit(0); //exit!
                default:
                    System.out.println("Ehem! Invalid choice..");
            }
        }
    }

    static void customerMenu() {
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        System.out.println("\n[1] Purchase Weapon");
        System.out.println("[2] Product List");
        System.out.println("[99] Back to main menu");

        boolean notValid = true;
        int choice;

        while(notValid) {

            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());
            switch(choice) {
                case 1:
                    notValid = false;
                    //purchae gun function go here
                    purchase();
                    break;
                case 2:
                    notValid = false;
                    //list product menu go here
                    // displayAllWeapon();
                    displayAllWeaponByCategory();
                    pressAnyKey();
                    customerMenu();
                    break;
                case 99:
                    notValid = false;
                    mainMenu();
                    break;
                default:
                    System.out.println("Ehem! Invalid choice..");
            }
        }
    }

    static void adminMenu() {
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        System.out.println("\n[1] Register new Category");
        System.out.println("[2] Register new Weapon");
        System.out.println("[3] List of all Weapon");
        System.out.println("[4] Search customer record");
        System.out.println("[5] Reporting");
        System.out.println("[99] Back to main menu");

        boolean notValid = true;
        int choice;

        while(notValid) {

            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());
            switch(choice) {
                case 1:
                    notValid = false;
                    //register new category go here
                    addCategory();
                    break;
                case 2:
                    notValid = false;
                    //register new weapon go here
                    addWeapon();
                    break;
                case 3:
                    notValid = false;
                    //list function go here
                    break;
                case 4:
                    notValid = false;
                    //search function go here
                    searchSalesRecord();
                    break;
                case 5:
                    notValid = false;
                    //reporting menu go here
                    break;
                case 99:
                    notValid = false;
                    mainMenu();
                    break;
                default:
                    System.out.println("Ehem! Invalid choice..");
            }
        }
    }
    public static void main(String []  args) {
        //code here
        mainMenu();
    }
}