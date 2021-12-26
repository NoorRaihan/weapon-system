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

    //insert category
    static void addCategory(){
        System.out.print("\u000C");
        Scanner in = new Scanner(System.in);

        Queue data = new Queue();
        char choice = 'y';
        while(choice == 'y' || choice == 'Y')
        {
            boolean check =  true;
            String cID = null;
            String cName = null;
            while(check){
                System.out.print("\nCategory ID : ");
                cID = in.nextLine();
                boolean exist = Category.checkExist(cID);
                if(exist)
                {
                    System.out.println("Already exist !");
                }
                else
                {
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
        //add function go here lmbfao
        System.out.println("Are you confirm ?");
        choice = in.nextLine().charAt(0);
        if(choice == 'y' || choice == 'Y' )
        {
            Category.add(data);
            System.out.println("Succcessfully saved !");
        }
        else
            addCategory();
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
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e){
            System.err.println("went wrong!");
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
        System.out.println("\n[1] Buy dat thinggg");
        System.out.println("[2] Let's check what we have here");
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
                    break;
                case 2:
                    notValid = false;
                    //list product menu go here
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
                    break;
                case 3:
                    notValid = false;
                    //list function go here
                    break;
                case 4:
                    notValid = false;
                    //search function go here
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