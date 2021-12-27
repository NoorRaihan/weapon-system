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
        weaList.head = weaList.mergeSortByID(weaList.head);
        weaList.displayList(weaList.head);
    }

    //display all the weapon by the category
    static void displayAllWeaponByCategory() {
        System.out.print("\u000C");
        LinkedList weaList = Weapon.getAllWeapon();
        weaList.head = weaList.mergeSortByID(weaList.head);
        LinkedList catList = Category.getAllCategory();
        catList.head = catList.mergeSortByID(catList.head);
        Node catHead = catList.head;
        Node weaHead = weaList.head;
        Node tempH = weaList.head;

        while(catHead != null) {
            Category obj1 = (Category)catHead.data;
            System.out.println("\n\n===========" + obj1.getCName() + "===========");

            weaHead = tempH;
            while(weaHead != null) {
                Weapon obj2 = (Weapon)weaHead.data;

                if(obj2.getCID().equalsIgnoreCase(obj1.getCID())) {
                    System.out.println(obj2.toString());
                }
                weaHead = weaHead.next;
            }
            catHead = catHead.next;
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

    //reporting menu
    static void reporting() {
        LinkedList arList = new LinkedList();
        LinkedList lmgList = new LinkedList();
        LinkedList srList = new LinkedList();
        LinkedList expList = new LinkedList();
        LinkedList meleeList = new LinkedList();
        LinkedList saleList = Sale.getAllSales();
        Weapon saleData = (Weapon)saleList.getHead();

        //sort and seperate by category id... hurmmm must be fixed id :(
        while(saleData != null) {
            
            if(saleData.getCID().equalsIgnoreCase("SR300")) {
                srList.insertAtBack(saleData);
            }
            else if(saleData.getCID().equalsIgnoreCase("AR100")) {
                arList.insertAtBack(saleData);
            }
            else if(saleData.getCID().equalsIgnoreCase("LMG200")) {
                lmgList.insertAtBack(saleData);
            }
            else if(saleData.getCID().equalsIgnoreCase("ML400")) {
                meleeList.insertAtBack(saleData);
            }
            else if(saleData.getCID().equalsIgnoreCase("EXP500")) {
                expList.insertAtBack(saleData);
            }

            saleData = (Weapon)saleList.getNext();


        }

        int choice = reportMenu();

        if (choice == 1) {
            System.out.print("\u000C");
            double arSale = calcTotalSale(arList);
            double lmgSale = calcTotalSale(lmgList);
            double srSale = calcTotalSale(srList);
            double expSale = calcTotalSale(expList);
            double meleeSale = calcTotalSale(meleeList);

            System.out.println("[1] ASSAULT RIFLE : RM" + arSale);
            System.out.println("[1] LIGHT MACHINE GUN : RM" + lmgSale);
            System.out.println("[1] SNIPER RIFLE : RM" + srSale);
            System.out.println("[1] MELEE : RM" + meleeSale);
            System.out.println("[1] EXPLOSIVES : RM" + expSale);
            pressAnyKey();
            reporting();
        }


        //code go here
    }

    static double calcTotalSale(LinkedList data) {
        double total = 0.00;

        Sale obj = (Sale)data.getHead();

        while(obj != null) {
            
            total += obj.totalPrice();
            obj = (Sale)data.getNext();
        }
        return total;
    }

    //display reporting menu
    static int reportMenu() {
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);
        System.out.println("[1] Total sale of each category");

        boolean notValid = true;
        int choice = -1;

        while(notValid) {

            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());
            switch(choice) {
                case 1:
                    choice = 1;
                    notValid = false;
                    break;
                case 2:
                    choice = 2;
                    notValid = false;
                    break;
                case 99:
                    notValid = false;
                    adminMenu();
                default:
                    System.out.println("Ehem! Invalid choice..");
            }
        }
        return choice;
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
                    reporting();
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