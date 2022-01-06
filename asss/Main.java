//IO stuffs
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.text.*;

import javax.swing.*;

//normal stuff
import java.util.Scanner;

public class Main {
    

    // Define color constants
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";
    public static final DecimalFormat df = new DecimalFormat("0.00");
    public static final JFrame j = new JFrame();

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

    //display sale record
    static void displaySaleRecord() {
        System.out.print("\u000C");
        LinkedList saleList = Sale.getAllSales();
        saleList.head = saleList.mergeSortByName(saleList.head);
        saleList.displayList(saleList.head);
    }

    //display all the weapon
    static void displayAllWeapon() {
        System.out.print("\u000C");
        LinkedList weaList = Weapon.getAllWeapon();
        weaList.head = weaList.mergeSortByName(weaList.head);
        weaList.displayList(weaList.head);
    }

    //display all the weapon by the category
    static void displayAllWeaponByCategory() {
        System.out.print("\u000C");
        LinkedList arList = new LinkedList();
        LinkedList lmgList = new LinkedList();
        LinkedList srList = new LinkedList();
        LinkedList expList = new LinkedList();
        LinkedList meleeList = new LinkedList();
        LinkedList weaList = Weapon.getAllWeapon(); 
        Weapon weaData = (Weapon)weaList.getHead();

        //sort and seperate by category id... hurmmm must be fixed id :(
        while(weaData != null) {
            
            if(weaData.getCID().equalsIgnoreCase("SR300")) {
                srList.insertAtBack(weaData);
            }
            else if(weaData.getCID().equalsIgnoreCase("AR100")) {
                arList.insertAtBack(weaData);
            }
            else if(weaData.getCID().equalsIgnoreCase("LMG200")) {
                lmgList.insertAtBack(weaData);
            }
            else if(weaData.getCID().equalsIgnoreCase("ML400")) {
                meleeList.insertAtBack(weaData);
            }
            else if(weaData.getCID().equalsIgnoreCase("EXP500")) {
                expList.insertAtBack(weaData);
            }

            weaData = (Weapon)weaList.getNext();
            
        }

        //display the linkedlist
        Node arHead = arList.mergeSortByName(arList.head);
        Node lmgHead = lmgList.mergeSortByName(lmgList.head);
        Node srHead = srList.mergeSortByName(srList.head);
        Node expHead = expList.mergeSortByName(expList.head);
        Node melHead = meleeList.mergeSortByName(meleeList.head);

        Node tempAr = arList.head;
        Node tempLmg = lmgList.head;
        Node tempSr = srList.head;
        Node tempExp = expList.head;
        Node tempMel = meleeList.head;

        arHead = tempAr;
        System.out.println("\n\n=========== ASSAULT RIFLE ===========");
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        System.out.format("| ID         | CATEGORY             | NAME                 | PRICE (RM)      |%n");
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        while(arHead != null) {
            Weapon obj = (Weapon)arHead.data;
            // System.out.println(obj.toString());
            System.out.format("| %-10s | %-20s | %-20s | %-15s |\n", obj.getWeaponID(), obj.getCName(), obj.getWeaponName(), obj.getWeaponPrice());
            arHead = arHead.next;
        }
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");

        expHead = tempExp;
        System.out.println("\n\n=========== EXPLOSIVES ===========");
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        System.out.format("| ID         | CATEGORY             | NAME                 | PRICE (RM)      |%n");
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        while(expHead != null) {
            Weapon obj = (Weapon)expHead.data;
            // System.out.println(obj.toString());\
            System.out.format("| %-10s | %-20s | %-20s | %-15s |\n", obj.getWeaponID(), obj.getCName() , obj.getWeaponName(), "RM"+obj.getWeaponPrice());
            expHead = expHead.next;
        }
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");

        lmgHead = tempLmg;
        System.out.println("\n\n=========== LIGHT MACHINE GUN ===========");
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        System.out.format("| ID         | CATEGORY             | NAME                 | PRICE (RM)      |%n");
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        while(lmgHead != null) {
            Weapon obj = (Weapon)lmgHead.data;
            // System.out.println(obj.toString());
            System.out.format("| %-10s | %-20s | %-20s | %-15s |\n", obj.getWeaponID(), obj.getCName() , obj.getWeaponName(), "RM"+obj.getWeaponPrice());
            lmgHead = lmgHead.next;
        }
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");

        melHead = tempMel;
        System.out.println("\n\n=========== MELEE ===========");
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        System.out.format("| ID         | CATEGORY             | NAME                 | PRICE (RM)      |%n");
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        while(melHead != null) {
            Weapon obj = (Weapon)melHead.data;
            // System.out.println(obj.toString());
            System.out.format("| %-10s | %-20s | %-20s | %-15s |\n", obj.getWeaponID(), obj.getCName() , obj.getWeaponName(), "RM"+obj.getWeaponPrice());
            melHead = melHead.next;
        }
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");

        srHead = tempSr;
        System.out.println("\n\n=========== SNIPER RIFLE ===========");
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        System.out.format("| ID         | CATEGORY             | NAME                 | PRICE (RM)      |%n");
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
        while(srHead != null) {
            Weapon obj = (Weapon)srHead.data;
            // System.out.println(obj.toString());
            System.out.format("| %-10s | %-20s | %-20s | %-15s |\n", obj.getWeaponID(), obj.getCName() , obj.getWeaponName(), "RM"+obj.getWeaponPrice());
            srHead = srHead.next;
        }
        System.out.format("+------------+----------------------+----------------------+-----------------+%n");
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
            adminMenu();
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
        System.out.println("============= RESULT =============\n");

        System.out.format("+-----------------+----------------------+----------------------+-----------------+---------------------+%n");
        System.out.format("| IC NUMBER       | NAME                 | WEAPON  BOUGHT       | QUANTITY        | TOTAL PRICE (RM)    |%n");
        System.out.format("+-----------------+----------------------+----------------------+-----------------+---------------------+%n");
        if(data == null) {
            System.out.println("IC does not exist in the record!");
            pressAnyKey();
            adminMenu();
        } else {

            while(data != null) {
                System.out.format("| %-15s | %-20s | %-20s | %-15s | %-20s|\n", data.getCustomerIC(), data.getCustomerName(), data.getWeaponName(), data.getQuantity(), "RM"+ df.format(data.totalPrice()));
                total += data.totalPrice();
                data = (Sale)record.getNext();
            }
            System.out.format("+-----------------+----------------------+----------------------+-----------------+---------------------+%n");
            System.out.println("\n=========== Total Purchase: RM" + total + " ===========");
            pressAnyKey();
            adminMenu();
        }
    }

    //delete sales record
    static void deletePurchaseRecord() {
        
        Scanner in = new Scanner(System.in);
        LinkedList saleList = Sale.getAllSales();
        Queue saleQueue = new Queue();
        char choice = 'y';

        while(choice == 'y' || choice == 'Y') {
            System.out.print("\u000C");
            saleList.displayList(saleList.head);
            System.out.print("\nInsert Customer IC:");
            String ic = in.nextLine();
            
            saleList.deleteCust(ic);

            System.out.println("\nDelete more record ? \n[Y] - yessir \n[N] - nossir");
            choice = in.nextLine().charAt(0);
        }

        //insert the linkedlist into queue
        Sale data = (Sale)saleList.getHead();
        while(data != null) {
            saleQueue.enqueue(data);
            data = (Sale)saleList.getNext();
        }

        System.out.println("Are you confirm ? (⌐■_■)\n[Y] - yessir \n[N] - nossir");
        choice = in.nextLine().charAt(0);
        if(choice == 'y' || choice == 'Y' )
        {
            Sale.delete(saleQueue);
            System.out.println("Record has been updated successfully!");
            pressAnyKey();
            adminMenu();
        }
        else
            adminMenu();
        
    }

    //reporting menu
    static void reporting() {
        
        System.out.print("\u000C");
        System.out.print("\033[H\033[2J");
        Scanner in = new Scanner(System.in);
        System.out.println("[1] Total sale of each category");
        System.out.println("[2] Find highest weapon's category sold");
        System.out.println("[3] Count total weapon sold by each category");
        System.out.println("[4] Calculate the average sales by each category");
        System.out.println("[5] Check all the report");
        System.out.println("[99] Back to Admin Menu");

        boolean notValid = true;
        int choice = -1;

        while(notValid) {

            System.out.print("Enter choice: ");
            choice = Integer.parseInt(in.nextLine());
            switch(choice) {
                case 1:
                    notValid = false;
                    System.out.print("\u000C");
                    calcSaleByCategory();
                    pressAnyKey();
                    reporting();
                    break;
                case 2:
                    notValid = false;
                    System.out.print("\u000C");
                    FindHighestByCat();
                    pressAnyKey();
                    reporting();
                    break;
                case 3:
                    notValid = false;
                    System.out.print("\u000C");
                    CountWeaponSoldByCat();
                    pressAnyKey();
                    reporting();
                    break;
                case 4:
                    notValid = false;
                    System.out.print("\u000C");
                    CalcAverageSalesByCat();
                    pressAnyKey();
                    reporting();
                    break;
                case 5:
                    notValid = false;
                    System.out.print("\u000C");
                    displayAllReport();
                    pressAnyKey();
                    reporting();
                    break;
                case 99:
                    notValid = false;
                    adminMenu();
                default:
                    System.out.println("Ehem! Invalid choice..");
            }
        }
    }

    static void displayAllReport() {
        displayAllWeapon();
        calcSaleByCategory();
        FindHighestByCat();
        CountWeaponSoldByCat();
        CalcAverageSalesByCat();
    }

    static void calcSaleByCategory() {
        
        LinkedList saleList = Sale.getAllSales();
        LinkedList catList = Category.getAllCategory();
        catList.head = catList.mergeSortByName(catList.head);
        Node catHead = catList.head;
        Node temp = catList.head;

        catHead = temp;
        System.out.format("+------------+----------------------+-----------------+%n");
        System.out.format("| ID         | CATEGORY             | PRICE (RM)      |%n");
        System.out.format("+------------+----------------------+-----------------+%n");

        while(catHead != null) {
            double total = 0.00;
            Category catObj = (Category)catHead.data;
            
            // System.out.println("\n=================== " + catObj.getCName() + "=================== ");
            Sale saleData = (Sale)saleList.getHead();
            while(saleData != null) {
                if(saleData.getCID().equalsIgnoreCase(catObj.getCID())) {
                    total += saleData.totalPrice();
                }
                saleData = (Sale)saleList.getNext();
            }
            // System.out.println("Total Sales: RM" + total);
            System.out.format("| %-10s | %-20s | %-15s |\n", catObj.getCID(), catObj.getCName(), "RM" + df.format(total));
            catHead = catHead.next;
        }
        System.out.format("+------------+----------------------+-----------------+%n");
    }


    //display the main menu
    static void mainMenu() {
        System.out.print("\u000C");
        System.out.print("\033[H\033[2J");
        
        System.out.println("\t\t" + TEXT_RED + " ███▄ ▄███▓ ▒█████   ██▀███  ▄▄▄█████▓ ▄▄▄       ██▓    ");
        System.out.println("\t\t" + TEXT_RED + "▓██▒▀█▀ ██▒▒██▒  ██▒▓██ ▒ ██▒▓  ██▒ ▓▒▒████▄    ▓██▒    ");
        System.out.println("\t\t" + TEXT_RED + "▓██    ▓██░▒██░  ██▒▓██ ░▄█ ▒▒ ▓██░ ▒░▒██  ▀█▄  ▒██░    ");
        System.out.println("\t\t" + TEXT_RED + "▒██    ▒██ ▒██   ██░▒██▀▀█▄  ░ ▓██▓ ░ ░██▄▄▄▄██ ▒██░    ");
        System.out.println("\t\t" + TEXT_RED + "▒██▒   ░██▒░ ████▓▒░░██▓ ▒██▒  ▒██▒ ░  ▓█   ▓██▒░██████▒");
        System.out.println("\t\t" + TEXT_RED + "░ ▒░   ░  ░░ ▒░▒░▒░ ░ ▒▓ ░▒▓░  ▒ ░░    ▒▒   ▓▒█░░ ▒░▓  ░");
        System.out.println("\t\t" + TEXT_RED + "░         ░    ▒ ▒    ░▒ ░ ▒     ░      ▒   ▒▒ ░░ ░ ▒   " + TEXT_RESET);
        System.out.println("\n\t\t\t" + "FIRST WEAPON ARMORY STORE SALE SYSTEM V1.0");
        System.out.println("\t\t\t\t" + "[THE BIGGER, THE BETTER]");
        System.out.println("\t\t" + "  DEVELOPED BY: NOOR RAIHAN, IMTIAZ AZIZ, MUHD SYAKIR");

        Scanner in = new Scanner(System.in);
        System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "[1] Customer Section");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[2] Staff Section");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[99] Hasta la vista");

        boolean notValid = true;
        int choice;

        while(notValid) {

            System.out.print("\t\t\t"  + TEXT_YELLOW + "Enter choice: " + TEXT_RESET);
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
                    JOptionPane.showMessageDialog(null, "Invalid input.. Ehem! (⌐■_■)", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    static void customerMenu() {
        System.out.print("\u000C");
        System.out.print("\033[H\033[2J");
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
        System.out.print("\033[H\033[2J");
        Scanner in = new Scanner(System.in);
        System.out.println("\n[1] Register new Category");
        System.out.println("[2] Register new Weapon");
        System.out.println("[3] List of all Weapon");
        System.out.println("[4] All Customer's record");
        System.out.println("[5] Search customer record");
        System.out.println("[6] Delete customer record");
        System.out.println("[7] Reporting");
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
                    System.out.print("\u000C");
                    displaySaleRecord();
                    pressAnyKey();
                    adminMenu();
                    break;
                case 5:
                    notValid = false;
                    //search function go here
                    searchSalesRecord();
                    break;
                case 6:
                    notValid = false;
                    //search function go here
                    deletePurchaseRecord();
                    break;
                case 7:
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

    static void FindHighestByCat()
    {
        LinkedList saleList = Sale.getAllSales();
        LinkedList catList = Category.getAllCategory();
        
        Category catData = (Category)catList.getHead();
        int highest = 0;
        int count = 0;
        Category catHighest = null;
        while(catData != null)
        {
            Sale saleData = (Sale)saleList.getHead();
            count = 0;
            while(saleData != null)
            {
                if(saleData.getCID().equalsIgnoreCase(catData.getCID()))
                {
                    count++;
                }
                saleData = (Sale)saleList.getNext();
            }
            if(count > highest)
            {
                highest = count;
                catHighest = catData;
            }
            catData = (Category)catList.getNext();
        }
        System.out.println("The highest weapon's category sold is " + catHighest.getCName());
    }

    static void CountWeaponSoldByCat()
    {
        LinkedList saleList = Sale.getAllSales();
        LinkedList catList = Category.getAllCategory();
        catList.head = catList.mergeSortByName(catList.head);
        Node catHead = catList.head;
        Node temp = catList.head;
        
        catHead = temp;
        int count = 0;
        System.out.format("+------------+----------------------+-----------------+%n");
        System.out.format("| ID         | CATEGORY             | QUANTITY        |%n");
        System.out.format("+------------+----------------------+-----------------+%n");
        while(catHead != null)
        {
            Category catObj = (Category)catHead.data;
            // System.out.println("\n=================== " + catObj.getCName() + "=================== ");
            Sale saleData = (Sale)saleList.getHead();
            count = 0;
            while(saleData != null) {
                if(saleData.getCID().equalsIgnoreCase(catObj.getCID())) 
                {
                    count += saleData.getQuantity();
                }
                saleData = (Sale)saleList.getNext();
            }
            System.out.format("| %-10s | %-20s | %-15s |\n", catObj.getCID(), catObj.getCName(), count);
            catHead = catHead.next;
        }
        System.out.format("+------------+----------------------+-----------------+%n");
    }

    static void CalcAverageSalesByCat()
    {
        
        LinkedList saleList = Sale.getAllSales();
        LinkedList catList = Category.getAllCategory();
        catList.head = catList.mergeSortByName(catList.head);
        Node catHead = catList.head;
        Node temp = catList.head;

        double average = 0.00;
        int count = 0;
        catHead = temp;
        System.out.format("+------------+----------------------+-----------------+%n");
        System.out.format("| ID         | CATEGORY             | AVERAGE (RM)    |%n");
        System.out.format("+------------+----------------------+-----------------+%n");
        while(catHead != null) {
            double total = 0.00;
            Category catObj = (Category)catHead.data;
            // System.out.println("\n=================== " + catObj.getCName() + "=================== ");
            Sale saleData = (Sale)saleList.getHead();
            count = 0;
            average = 0.00;
            while(saleData != null) {
                if(saleData.getCID().equalsIgnoreCase(catObj.getCID())) {
                    total += saleData.totalPrice();
                    count += saleData.getQuantity();
                }
                saleData = (Sale)saleList.getNext();
            }
            if(count == 0)
                average = 0.00;
            else
                average = total/count;
            // System.out.println("The average sales : RM" + average);
            System.out.format("| %-10s | %-20s | %-15s |\n", catObj.getCID(), catObj.getCName(), "RM" + df.format(average));
            catHead = catHead.next;
        }
        System.out.format("+------------+----------------------+-----------------+%n");
    }

    public static void main(String []  args) {
        //code here
        mainMenu();
    }
}