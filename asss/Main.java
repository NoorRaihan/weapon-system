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
import javax.swing.table.DefaultTableModel;

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
        System.out.print("\033[H\033[2J");
        Scanner in = new Scanner(System.in);

        System.out.println("\t\t" + TEXT_YELLOW + " █████  ██████  ██████       ██████  █████  ████████ ███████  ██████   ██████  ██████  ██    ██ ");
        System.out.println("\t\t" + TEXT_YELLOW + "██   ██ ██   ██ ██   ██     ██      ██   ██    ██    ██      ██       ██    ██ ██   ██  ██  ██  ");
        System.out.println("\t\t" + TEXT_YELLOW + "███████ ██   ██ ██   ██     ██      ███████    ██    █████   ██   ███ ██    ██ ██████    ████   ");
        System.out.println("\t\t" + TEXT_YELLOW + "██   ██ ██   ██ ██   ██     ██      ██   ██    ██    ██      ██    ██ ██    ██ ██   ██    ██    ");
        System.out.println("\t\t" + TEXT_YELLOW + "██   ██ ██████  ██████       ██████ ██   ██    ██    ███████  ██████   ██████  ██   ██    ██    " + TEXT_RESET);

        int count = Category.getAllCategory().size();
        Queue data = new Queue();
        LinkedList tempID = new LinkedList();
        int ch = 0;
        boolean check =  true;
        String cID = null;
        String cName = null;
        System.out.println();
        while(ch == 0)
        {
            
            
            if(count > 4)
            {
                // System.out.println("Ehem..Can't exceed 5 categories <( `^`)>");
                JOptionPane.showMessageDialog(null, "Ehem..Can't exceed 5 categories <( `^`)>", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
            }
            else
            {
                while(check){
                    System.out.print("\n\t\t\tCategory ID : ");
                    cID = in.nextLine();
                    boolean exist = Category.checkExist(cID);
                    boolean inserted = inputValidate(tempID, cID);
    
                    if(exist || inserted)
                    {
                        // System.out.println("Already exist !");
                        JOptionPane.showMessageDialog(null, "Category ID already exist !", "ERROR", JOptionPane.ERROR_MESSAGE);
                        
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

                ch = JOptionPane.showConfirmDialog(null, "Add more category? ", "Confirmation", JOptionPane.YES_NO_OPTION);
                // choice = in.nextLine().charAt(0);

            }
            count++;
        }

        //add function go here 
        if(cID != null)
        {
            ch = JOptionPane.showConfirmDialog(null, "Are you confirm? (⌐■_■)", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
            // choice = in.nextLine().charAt(0);
            if(ch == 0 )
            {
                Category.add(data);
                // System.out.println("Succcessfully saved !");
                JOptionPane.showMessageDialog(null, "Category successfully saved!", "Info", JOptionPane.INFORMATION_MESSAGE);
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
        System.out.print("\033[H\033[2J");
        Scanner in = new Scanner(System.in);

        System.out.println("\t\t" + TEXT_YELLOW + " █████  ██████  ██████      ██     ██ ███████  █████  ██████   ██████  ███    ██");
        System.out.println("\t\t" + TEXT_YELLOW + "██   ██ ██   ██ ██   ██     ██     ██ ██      ██   ██ ██   ██ ██    ██ ████   ██");
        System.out.println("\t\t" + TEXT_YELLOW + "███████ ██   ██ ██   ██     ██  █  ██ █████   ███████ ██████  ██    ██ ██ ██  ██");
        System.out.println("\t\t" + TEXT_YELLOW + "██   ██ ██   ██ ██   ██     ██ ███ ██ ██      ██   ██ ██      ██    ██ ██  ██ ██");
        System.out.println("\t\t" + TEXT_YELLOW + "██   ██ ██████  ██████       ███ ███  ███████ ██   ██ ██       ██████  ██   ████" + TEXT_RESET);

        System.out.println("\n\n\t\t\t\t"  + "=========== CATEGORY LIST ===========");
        displayCategory();

        Queue data = new Queue();
        LinkedList tempID = new LinkedList();
        boolean check =  true;
        String cID = null;
        String wID = null;
        String wName = null;
        double wPrice = 0;
        Category catobj = null;
        int ch = 0;

        while(ch == 0)
        {
            check = true;
            while(check){
                System.out.print("\n\t\t\t\t"  + "Weapon ID : ");
                wID = in.nextLine();
                boolean exist = Weapon.checkExist(wID);
                boolean inserted = inputValidate(tempID, wID);

                if(exist || inserted)
                {
                    // System.out.println("Already exist !");
                    JOptionPane.showMessageDialog(null, "Weapon ID already exist !", "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                }
                else
                {
                    tempID.insertAtBack(wID);
                    check = false;
                    break;
                }
            }

            while(catobj == null){
                System.out.print("\t\t\t\t"  +"Category ID : ");
                cID = in.nextLine();
                catobj = Category.search(cID);
                if(catobj == null)
                {
                    // System.out.println("Category is not exist !");
                    JOptionPane.showMessageDialog(null, "Category is not exist !", "ERROR", JOptionPane.ERROR_MESSAGE);
                    
                }
                
            }

            System.out.print("\t\t\t\t"  + "Weapon Name : ");
            wName = in.nextLine();

            System.out.print("\t\t\t\t"  + "Weapon Price : RM");
            wPrice = Double.parseDouble(in.nextLine());

            Weapon obj = new Weapon(catobj, wID, wName, wPrice);
            data.enqueue(obj);

            ch = JOptionPane.showConfirmDialog(null, "Add more weapon? ", "Confirmation", JOptionPane.YES_NO_OPTION);
            // choice = in.nextLine().charAt(0);
        }

        ch = JOptionPane.showConfirmDialog(null, "Are you confirm? (⌐■_■)", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        // choice = in.nextLine().charAt(0);
        if(ch == 0 )
        {
            Weapon.add(data);
            // System.out.println("Succcessfully saved !");
            JOptionPane.showMessageDialog(null, "Weapon successfully saved!", "Info", JOptionPane.INFORMATION_MESSAGE);
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
        Scanner in = new Scanner(System.in);
        System.out.println("\nPress Enter key to continue... <(￣︶￣)>");
        try
        {
            in.nextLine();
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

    //display all weapon by price
    static void displayAllWeaponByPrice() {
        System.out.print("\u000C");
        LinkedList weaList = Weapon.getAllWeapon();
        weaList.head = weaList.mergeSortByPrice(weaList.head);
        weaList.displayList(weaList.head);
    }

    //display all category
    static void displayCategory() {
        System.out.print("\u000C");
        LinkedList catList = Category.getAllCategory();
        catList.head = catList.mergeSortByName(catList.head);
        catList.displayList(catList.head);
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

        // arHead = tempAr;
        System.out.println("\n\n\t\t\t\t"  + "=========== ASSAULT RIFLE ===========");
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");
        System.out.format("\t\t| ID         | CATEGORY             | NAME                 | PRICE (RM)      |%n");
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");
        while(arHead != null) {
            Weapon obj = (Weapon)arHead.data;
            // System.out.println(obj.toString());
            System.out.format("\t\t| %-10s | %-20s | %-20s | %-15s |\n", obj.getWeaponID(), obj.getCName(), obj.getWeaponName(), obj.getWeaponPrice());
            arHead = arHead.next;
        }
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");

        // expHead = tempExp;
        System.out.println("\n\n\t\t\t\t"  + "=========== EXPLOSIVES ===========");
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");
        System.out.format("\t\t| ID         | CATEGORY             | NAME                 | PRICE (RM)      |%n");
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");
        while(expHead != null) {
            Weapon obj = (Weapon)expHead.data;
            // System.out.println(obj.toString());\
            System.out.format("\t\t| %-10s | %-20s | %-20s | %-15s |\n", obj.getWeaponID(), obj.getCName() , obj.getWeaponName(), "RM"+obj.getWeaponPrice());
            expHead = expHead.next;
        }
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");

        // lmgHead = tempLmg;
        System.out.println("\n\n\t\t\t\t"  + "=========== LIGHT MACHINE GUN ===========");
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");
        System.out.format("\t\t| ID         | CATEGORY             | NAME                 | PRICE (RM)      |%n");
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");
        while(lmgHead != null) {
            Weapon obj = (Weapon)lmgHead.data;
            // System.out.println(obj.toString());
            System.out.format("\t\t| %-10s | %-20s | %-20s | %-15s |\n", obj.getWeaponID(), obj.getCName() , obj.getWeaponName(), "RM"+obj.getWeaponPrice());
            lmgHead = lmgHead.next;
        }
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");

        // melHead = tempMel;
        System.out.println("\n\n\t\t\t\t"  + "=========== MELEE ===========");
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");
        System.out.format("\t\t| ID         | CATEGORY             | NAME                 | PRICE (RM)      |%n");
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");
        while(melHead != null) {
            Weapon obj = (Weapon)melHead.data;
            // System.out.println(obj.toString());
            System.out.format("\t\t| %-10s | %-20s | %-20s | %-15s |\n", obj.getWeaponID(), obj.getCName() , obj.getWeaponName(), "RM"+obj.getWeaponPrice());
            melHead = melHead.next;
        }
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");

        // srHead = tempSr;
        System.out.println("\n\n\t\t\t\t"  + "=========== SNIPER RIFLE ===========");
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");
        System.out.format("\t\t| ID         | CATEGORY             | NAME                 | PRICE (RM)      |%n");
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");
        while(srHead != null) {
            Weapon obj = (Weapon)srHead.data;
            // System.out.println(obj.toString());
            System.out.format("\t\t| %-10s | %-20s | %-20s | %-15s |\n", obj.getWeaponID(), obj.getCName() , obj.getWeaponName(), "RM"+obj.getWeaponPrice());
            srHead = srHead.next;
        }
        System.out.format("\t\t+------------+----------------------+----------------------+-----------------+%n");
    }

    //display weapon title hoho :3
    static void displayWeaTitle() {
        System.out.println("\t\t" + TEXT_YELLOW + "██     ██ ███████  █████  ██████   ██████  ███    ██");
        System.out.println("\t\t" + TEXT_YELLOW + "██     ██ ██      ██   ██ ██   ██ ██    ██ ████   ██");
        System.out.println("\t\t" + TEXT_YELLOW + "██  █  ██ █████   ███████ ██████  ██    ██ ██ ██  ██");
        System.out.println("\t\t" + TEXT_YELLOW + "██ ███ ██ ██      ██   ██ ██      ██    ██ ██  ██ ██");
        System.out.println("\t\t" + TEXT_YELLOW + " ███ ███  ███████ ██   ██ ██       ██████  ██   ████" + TEXT_RESET);
    }

    //display weapon menu hehe :3
    static void displayWeapon() {
        System.out.print("\u000C");
        System.out.print("\033[H\033[2J");
        Scanner in = new Scanner(System.in);
        displayWeaTitle();

        System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "[1] All Weapon By Category");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[2] All Weapon By Price");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[3] All Weapon By ID");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[99] Back to menu");

        boolean notValid = true;
        int choice = -1;
        System.out.println();
        while(notValid) {

            System.out.print("\n\t\t\t"  + TEXT_YELLOW + "Enter choice: " + TEXT_RESET);
            choice = Integer.parseInt(in.nextLine());
            switch(choice) {
                case 1:
                    notValid = false;
                    System.out.print("\u000C");
                    System.out.print("\033[H\033[2J");
                    displayWeaTitle();
                    displayAllWeaponByCategory();
                    pressAnyKey();
                    displayWeapon();
                    break;
                case 2:
                    notValid = false;
                    System.out.print("\u000C");
                    System.out.print("\033[H\033[2J");
                    displayWeaTitle();
                    displayAllWeaponByPrice();
                    pressAnyKey();
                    displayWeapon();
                    break;
                case 3:
                    notValid = false;
                    System.out.print("\u000C");
                    System.out.print("\033[H\033[2J");
                    displayWeaTitle();
                    displayAllWeapon();
                    pressAnyKey();
                    displayWeapon();
                    break;
                case 99:
                    notValid = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid input.. Ehem! (⌐■_■)", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //add customer purchase to textfile
    static void purchase() {
        System.out.print("\u000C");
        System.out.print("\033[H\033[2J");
        Scanner in = new Scanner(System.in);
        JFrame f = new JFrame();

        String header[] = new String[] {"IC NUMBER", "NAME", "WEAPON BOUGHT", "QUANTITY", "TOTAL PRICE (RM)"};
        DefaultTableModel dtm = new DefaultTableModel(header,0);
        JTable table = new JTable(dtm);
        f.setSize(550, 350);
        f.add(new JScrollPane(table));
        f.setVisible(true);
        dtm.setRowCount(0);

        System.out.println("\t\t" + TEXT_YELLOW + "██████  ██    ██ ██████   ██████ ██   ██  █████  ███████ ███████");
        System.out.println("\t\t" + TEXT_YELLOW + "██   ██ ██    ██ ██   ██ ██      ██   ██ ██   ██ ██      ██     ");
        System.out.println("\t\t" + TEXT_YELLOW + "██████  ██    ██ ██████  ██      ███████ ███████ ███████ █████  ");
        System.out.println("\t\t" + TEXT_YELLOW + "██      ██    ██ ██   ██ ██      ██   ██ ██   ██      ██ ██     ");
        System.out.println("\t\t" + TEXT_YELLOW + "██       ██████  ██   ██  ██████ ██   ██ ██   ██ ███████ ███████" + TEXT_RESET);
        displayAllWeapon();

        Queue data = new Queue();
        String customerIC = null;
        String customerName = null;
        String weaID = null;
        int quantity = 0;
        double total = 0.00;
        Category catObj = null;
        Weapon weaObj = null;
        int ch = 0;

        System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "=========== PURCHASE DETAIL ===========" + TEXT_RESET);
        System.out.print("\n\t\t\t\t"  + "Customer's IC: ");
        customerIC = in.nextLine();

        System.out.print("\t\t\t\t"  + "Customer's Name: ");
        customerName = in.nextLine();
        while(ch == 0) {

            while(true) {
                System.out.print("\t\t\t\t"  +  "Weapon ID: ");
                weaID = in.nextLine();
                weaObj = Weapon.search(weaID);

                if(weaObj != null) {
                    System.out.println("\n\t\t\t\t"  + "======== WEAPON DETAIL ======== ");
                    System.out.println("\n\t\t\t\t"  + weaObj.toString());
                    break;
                } else {
                    // System.out.println("Weapon ID is invalid or not exist!");
                    JOptionPane.showMessageDialog(null, "Weapon ID is invalid or not exist!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

            System.out.print("\n\t\t\t\t"  + "Quantity: ");
            quantity = Integer.parseInt(in.nextLine());

            catObj = Category.search(weaObj.getCID());
            Sale obj = new Sale(catObj,weaObj,customerIC,customerName,quantity);
            data.enqueue(obj);
            Object[] objs = {customerIC,customerName, weaObj.getWeaponName(), quantity, "RM"+ df.format(obj.totalPrice())};
            dtm.addRow(objs);

            total += obj.totalPrice();
            System.out.println("\n\t\t\t\t"  + "=========== Total Price: RM" + df.format(total) + " ===========");
            ch = JOptionPane.showConfirmDialog(null, "Shopping more? ", "Confirmation", JOptionPane.YES_NO_OPTION);
            // choice = in.nextLine().charAt(0);
            if(ch == 0) {
                System.out.print("\u000C");
                System.out.print("\033[H\033[2J");
                System.out.println("\t\t" + TEXT_YELLOW + "██████  ██    ██ ██████   ██████ ██   ██  █████  ███████ ███████");
                System.out.println("\t\t" + TEXT_YELLOW + "██   ██ ██    ██ ██   ██ ██      ██   ██ ██   ██ ██      ██     ");
                System.out.println("\t\t" + TEXT_YELLOW + "██████  ██    ██ ██████  ██      ███████ ███████ ███████ █████  ");
                System.out.println("\t\t" + TEXT_YELLOW + "██      ██    ██ ██   ██ ██      ██   ██ ██   ██      ██ ██     ");
                System.out.println("\t\t" + TEXT_YELLOW + "██       ██████  ██   ██  ██████ ██   ██ ██   ██ ███████ ███████" + TEXT_RESET);

                System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "=========== PURCHASE DETAIL ===========" + TEXT_RESET);
                System.out.println("\n\t\t\t\t"  + "Customer's IC: " + customerIC);
                System.out.println("\t\t\t\t"  + "Customer's Name: " + customerName + "\n");
            }
        }
        // ch = JOptionPane.showConfirmDialog(null, "Are you confirm? (⌐■_■)", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        // // choice = in.nextLine().charAt(0);
        ch = JOptionPane.showConfirmDialog(null, "Are you confirm? (⌐■_■)", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        if(ch == 0 )
        {
            Sale.add(data);
            // System.out.println("Purchase checkout successfully!");
            JOptionPane.showMessageDialog(null, "Purchase checkout successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
            f.setVisible(false);
            pressAnyKey();
            adminMenu();
        }
        else
            f.setVisible(false);
            adminMenu();
    }

    //search the the sales record
    static void searchSalesRecord() {
        System.out.print("\u000C");
        System.out.print("\033[H\033[2J");
        Scanner in = new Scanner(System.in);
        JFrame f = new JFrame();

        String header[] = new String[] {"IC NUMBER", "NAME", "WEAPON BOUGHT", "QUANTITY", "TOTAL PRICE (RM)"};
        DefaultTableModel dtm = new DefaultTableModel(header,0);
        JTable table = new JTable(dtm);
        dtm.setRowCount(0);

        System.out.println("\t\t" + TEXT_YELLOW + "███████ ███████  █████  ██████   ██████ ██   ██     ██████  ███████  ██████  ██████  ██████  ██████ ");
        System.out.println("\t\t" + TEXT_YELLOW + "██      ██      ██   ██ ██   ██ ██      ██   ██     ██   ██ ██      ██      ██    ██ ██   ██ ██   ██");
        System.out.println("\t\t" + TEXT_YELLOW + "███████ █████   ███████ ██████  ██      ███████     ██████  █████   ██      ██    ██ ██████  ██   ██");
        System.out.println("\t\t" + TEXT_YELLOW + "     ██ ██      ██   ██ ██   ██ ██      ██   ██     ██   ██ ██      ██      ██    ██ ██   ██ ██   ██");
        System.out.println("\t\t" + TEXT_YELLOW + "███████ ███████ ██   ██ ██   ██  ██████ ██   ██     ██   ██ ███████  ██████  ██████  ██   ██ ██████ " + TEXT_RESET);

        String ic;
        double total = 0.00;
        System.out.print("\n\t\t\t\t"  +  "Customer IC: ");
        ic = in.nextLine();

        LinkedList record = Sale.searchRecord(ic);
        Sale data = (Sale)record.getHead();
        System.out.println("\n\t\t\t\t\t============= RESULT =============\n");

        System.out.format("\t+-----------------+----------------------+----------------------+-----------------+---------------------+%n");
        System.out.format("\t| IC NUMBER       | NAME                 | WEAPON  BOUGHT       | QUANTITY        | TOTAL PRICE (RM)    |%n");
        System.out.format("\t+-----------------+----------------------+----------------------+-----------------+---------------------+%n");
        if(data == null) {
            JOptionPane.showMessageDialog(null, "Record does not exist!", "ERROR", JOptionPane.ERROR_MESSAGE);
            pressAnyKey();
            adminMenu();
        } else {

            while(data != null) {
                System.out.format("\t| %-15s | %-20s | %-20s | %-15s | %-20s|\n", data.getCustomerIC(), data.getCustomerName(), data.getWeaponName(), data.getQuantity(), "RM"+ df.format(data.totalPrice()));
                Object[] objs = {data.getCustomerIC(), data.getCustomerName(), data.getWeaponName(), data.getQuantity(), "RM"+ df.format(data.totalPrice())};
                dtm.addRow(objs);
                total += data.totalPrice();
                data = (Sale)record.getNext();
            }
            System.out.format("\t+-----------------+----------------------+----------------------+-----------------+---------------------+%n");
            System.out.println("\n\t\t\t\t=========== Total Purchase: RM" + df.format(total) + " ===========");
            f.setSize(550, 350);
            f.add(new JScrollPane(table));
            f.setVisible(true);
            pressAnyKey();
            f.setVisible(false);
            adminMenu();
        }
    }

    //delete sales record
    static void deletePurchaseRecord() {
        
        System.out.print("\u000C");
        System.out.print("\033[H\033[2J");
        Scanner in = new Scanner(System.in);
        LinkedList saleList = Sale.getAllSales();
        Queue saleQueue = new Queue();
        int ch = 0;

        System.out.println("\t\t" + TEXT_YELLOW + "██████  ███████ ██      ███████ ████████ ███████     ██████  ███████  ██████  ██████  ██████  ██████ ");
        System.out.println("\t\t" + TEXT_YELLOW + "██   ██ ██      ██      ██         ██    ██          ██   ██ ██      ██      ██    ██ ██   ██ ██   ██");
        System.out.println("\t\t" + TEXT_YELLOW + "██   ██ █████   ██      █████      ██    █████       ██████  █████   ██      ██    ██ ██████  ██   ██");
        System.out.println("\t\t" + TEXT_YELLOW + "██   ██ ██      ██      ██         ██    ██          ██   ██ ██      ██      ██    ██ ██   ██ ██   ██");
        System.out.println("\t\t" + TEXT_YELLOW + "██████  ███████ ███████ ███████    ██    ███████     ██   ██ ███████  ██████  ██████  ██   ██ ██████ " + TEXT_RESET);

        while(ch == 0) {
            System.out.print("\u000C");
            saleList.displayList(saleList.head);
            System.out.print("\n\t\t\t\t"  +  "Insert Customer IC: ");
            String ic = in.nextLine();
            
            saleList.deleteCust(ic);

            ch = JOptionPane.showConfirmDialog(null, "Delete more record? ", "Confirmation", JOptionPane.YES_NO_OPTION);
            // choice = in.nextLine().charAt(0);
        }

        //insert the linkedlist into queue
        Sale data = (Sale)saleList.getHead();
        while(data != null) {
            saleQueue.enqueue(data);
            data = (Sale)saleList.getNext();
        }

        ch = JOptionPane.showConfirmDialog(null, "Are you confirm? (⌐■_■)", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        // choice = in.nextLine().charAt(0);
        if(ch == 0 )
        {
            Sale.delete(saleQueue);
            // System.out.println("Record has been updated successfully!");
            JOptionPane.showMessageDialog(null, "Success!", "Info", JOptionPane.INFORMATION_MESSAGE);
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
        
        System.out.println("\t\t" + TEXT_GREEN + "██████╗ ███████╗██████╗  ██████╗ ██████╗ ████████╗██╗███╗   ██╗ ██████╗ ");
        System.out.println("\t\t" + TEXT_GREEN + "██╔══██╗██╔════╝██╔══██╗██╔═══██╗██╔══██╗╚══██╔══╝██║████╗  ██║██╔════╝ ");
        System.out.println("\t\t" + TEXT_GREEN + "██████╔╝█████╗  ██████╔╝██║   ██║██████╔╝   ██║   ██║██╔██╗ ██║██║  ███╗");
        System.out.println("\t\t" + TEXT_GREEN + "██╔══██╗██╔══╝  ██╔═══╝ ██║   ██║██╔══██╗   ██║   ██║██║╚██╗██║██║   ██║");
        System.out.println("\t\t" + TEXT_GREEN + "██║  ██║███████╗██║     ╚██████╔╝██║  ██║   ██║   ██║██║ ╚████║╚██████╔" + TEXT_RESET);

        Scanner in = new Scanner(System.in);
        System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "[1] Total sale of each category");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[2] Find highest weapon's category sold");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[3] Count total weapon sold by each category");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[4] Calculate the average sales by each category");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[5] Check all the report");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[99] Back to Admin Menu");

        boolean notValid = true;
        int choice = -1;
        System.out.println();
        while(notValid) {

            System.out.print("\n\t\t\t"  + TEXT_YELLOW + "Enter choice: " + TEXT_RESET);
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
                    JOptionPane.showMessageDialog(null, "Invalid input.. Ehem! (⌐■_■)", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //display record summary
    static void displayAllReport() {
        System.out.print("\u000C");
        System.out.print("\033[H\033[2J");
        
        System.out.println("\t\t" + TEXT_GREEN + "██████╗ ███████╗██████╗  ██████╗ ██████╗ ████████╗██╗███╗   ██╗ ██████╗ ");
        System.out.println("\t\t" + TEXT_GREEN + "██╔══██╗██╔════╝██╔══██╗██╔═══██╗██╔══██╗╚══██╔══╝██║████╗  ██║██╔════╝ ");
        System.out.println("\t\t" + TEXT_GREEN + "██████╔╝█████╗  ██████╔╝██║   ██║██████╔╝   ██║   ██║██╔██╗ ██║██║  ███╗");
        System.out.println("\t\t" + TEXT_GREEN + "██╔══██╗██╔══╝  ██╔═══╝ ██║   ██║██╔══██╗   ██║   ██║██║╚██╗██║██║   ██║");
        System.out.println("\t\t" + TEXT_GREEN + "██║  ██║███████╗██║     ╚██████╔╝██║  ██║   ██║   ██║██║ ╚████║╚██████╔" + TEXT_RESET);

        System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "============== ALL WEAPON RECORD ==============" + TEXT_RESET);
        displayAllWeapon();
        calcSaleByCategory();
        CountWeaponSoldByCat();
        FindHighestByCat();
        CalcAverageSalesByCat();
    }

    //calculate sales by category
    static void calcSaleByCategory() {
        
        System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "============== SALE BY CATEGORY ==============" + TEXT_RESET);
        LinkedList saleList = Sale.getAllSales();
        LinkedList catList = Category.getAllCategory();
        catList.head = catList.mergeSortByName(catList.head);
        Node catHead = catList.head;
        Node temp = catList.head;

        catHead = temp;
        System.out.format("\t\t\t+------------+----------------------+-----------------+%n");
        System.out.format("\t\t\t| ID         | CATEGORY             | PRICE (RM)      |%n");
        System.out.format("\t\t\t+------------+----------------------+-----------------+%n");

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
            System.out.format("\t\t\t| %-10s | %-20s | %-15s |\n", catObj.getCID(), catObj.getCName(), "RM" + df.format(total));
            catHead = catHead.next;
        }
        System.out.format("\t\t\t+------------+----------------------+-----------------+%n");
    }


    //display the main menu
    static void mainMenu() {
        System.out.print("\u000C");
        System.out.print("\033[H\033[2J");
        displayTitle();

        Scanner in = new Scanner(System.in);
        System.out.println("\n\t\t\t"  + TEXT_YELLOW + "============== MAIN SECTION ==============");
        System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "[1] Customer Section");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[2] Staff Section");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[99] Hasta la vista");

        boolean notValid = true;
        int choice;
        System.out.println();
        while(notValid) {

            System.out.print("\n\t\t\t"  + TEXT_YELLOW + "Enter choice: " + TEXT_RESET);
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

    //display customer's menu
    static void customerMenu() {
        System.out.print("\u000C");
        System.out.print("\033[H\033[2J");
        displayTitle();

        Scanner in = new Scanner(System.in);
        System.out.println("\n\t\t\t"  + TEXT_YELLOW + "============== CUSTOMER SECTION ==============");
        System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "[1] Purchase Weapon");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[2] Product List");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[99] Back to main menu");

        boolean notValid = true;
        int choice;
        System.out.println();
        while(notValid) {

            System.out.print("\n\t\t\t"  + TEXT_YELLOW + "Enter choice: " + TEXT_RESET);
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
                    displayWeapon();
                    customerMenu();
                    break;
                case 99:
                    notValid = false;
                    mainMenu();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid input.. Ehem! (⌐■_■)", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //display admin's menu
    static void adminMenu() {
        System.out.print("\u000C");
        System.out.print("\033[H\033[2J");
        displayTitle();

        Scanner in = new Scanner(System.in);
        System.out.println("\n\t\t\t"  + TEXT_YELLOW + "============== STAFF SECTION ==============");
        System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "[1] Register new Category");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[2] Register new Weapon");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[3] List of all Weapon");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[4] All Customer's record");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[5] Search customer record");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[6] Delete customer record");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[7] Reporting");
        System.out.println("\t\t\t\t"  + TEXT_YELLOW + "[99] Back to main menu");

        boolean notValid = true;
        int choice;
        System.out.println();
        while(notValid) {

            System.out.print("\t\t\t"  + TEXT_YELLOW + "Enter choice: " + TEXT_RESET);
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
                    displayWeapon();
                    adminMenu();
                    break;
                case 4:
                    notValid = false;
                    //search function go here
                    System.out.print("\u000C");
                    System.out.print("\033[H\033[2J");
                    System.out.println("\t\t" + TEXT_YELLOW + " ██████ ██    ██ ███████ ████████  ██████  ███    ███ ███████ ██████      ██████  ███████  ██████  ██████  ██████  ██████");
                    System.out.println("\t\t" + TEXT_YELLOW + "██      ██    ██ ██         ██    ██    ██ ████  ████ ██      ██   ██     ██   ██ ██      ██      ██    ██ ██   ██ ██   ██");
                    System.out.println("\t\t" + TEXT_YELLOW + "██      ██    ██ ███████    ██    ██    ██ ██ ████ ██ █████   ██████      ██████  █████   ██      ██    ██ ██████  ██   ██");
                    System.out.println("\t\t" + TEXT_YELLOW + "██      ██    ██      ██    ██    ██    ██ ██  ██  ██ ██      ██   ██     ██   ██ ██      ██      ██    ██ ██   ██ ██   ██");
                    System.out.println("\t\t" + TEXT_YELLOW + " ██████  ██████  ███████    ██     ██████  ██      ██ ███████ ██   ██     ██   ██ ███████  ██████  ██████  ██   ██ ██████ " + TEXT_RESET);

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
                    JOptionPane.showMessageDialog(null, "Invalid input.. Ehem! (⌐■_■)", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //display main title
    static void displayTitle() {
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
    }

    //find highest category sold
    static void FindHighestByCat()
    {
        LinkedList saleList = Sale.getAllSales();
        LinkedList catList = Category.getAllCategory();
        
        Category catData = (Category)catList.getHead();
        int highest = -1;
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
                    count += saleData.getQuantity();
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
        System.out.println("\t\tThe highest weapon's category sold is " + TEXT_GREEN + catHighest.getCName() + TEXT_RESET);
    }

    //count total weapon sold by each category
    static void CountWeaponSoldByCat()
    {
        System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "============== TOTAL WEAPON SOLD BY CATEGORY==============" + TEXT_RESET);
        LinkedList saleList = Sale.getAllSales();
        LinkedList catList = Category.getAllCategory();
        catList.head = catList.mergeSortByName(catList.head);
        Node catHead = catList.head;
        Node temp = catList.head;
        
        catHead = temp;
        int count = 0;
        System.out.format("\t\t\t+------------+----------------------+-----------------+%n");
        System.out.format("\t\t\t| ID         | CATEGORY             | QUANTITY        |%n");
        System.out.format("\t\t\t+------------+----------------------+-----------------+%n");
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
            System.out.format("\t\t\t| %-10s | %-20s | %-15s |\n", catObj.getCID(), catObj.getCName(), count);
            catHead = catHead.next;
        }
        System.out.format("\t\t\t+------------+----------------------+-----------------+%n");
    }

    //calculate average sales by each category
    static void CalcAverageSalesByCat()
    {
        System.out.println("\n\t\t\t\t"  + TEXT_YELLOW + "============== WEAPON SALE AVERAGE BY CATEGORY=============="+ TEXT_RESET);
        LinkedList saleList = Sale.getAllSales();
        LinkedList catList = Category.getAllCategory();
        catList.head = catList.mergeSortByName(catList.head);
        Node catHead = catList.head;
        Node temp = catList.head;

        double average = 0.00;
        int count = 0;
        catHead = temp;
        System.out.format("\t\t\t+------------+----------------------+-----------------+%n");
        System.out.format("\t\t\t| ID         | CATEGORY             | AVERAGE (RM)    |%n");
        System.out.format("\t\t\t+------------+----------------------+-----------------+%n");
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
            System.out.format("\t\t\t| %-10s | %-20s | %-15s |\n", catObj.getCID(), catObj.getCName(), "RM" + df.format(average));
            catHead = catHead.next;
        }
        System.out.format("\t\t\t+------------+----------------------+-----------------+%n");
    }

    //main function
    public static void main(String []  args) {
        //simple :3
        mainMenu();
    }
                      
}