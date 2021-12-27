//IO stuffs
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class Category {

    private String cID;
    private String cName;

    //constructor
    public Category() { this(null,null); }

    public Category(String cID, String cName) {
        this.cID = cID;
        this.cName = cName;
    }

    //accessor
    public String getCID() {return cID;}
    public String getCName() {return cName;}

    //mutator
    public void setAll(String cID, String cName) {
        this.cID = cID;
        this.cName = cName;
    }
    
    //checking if the ID already existed or not
    static boolean checkExist(String ID) {
        boolean flag = false;

        File chkfile = new File("category.txt");
        boolean exists = chkfile.exists();

        if(exists) {

            LinkedList catList = getAllCategory();
            Category data = (Category)catList.getHead();

            while(data != null) {
                if(data.getCID().equalsIgnoreCase(ID)) {
                    return true;
                }
                data = (Category)catList.getNext();
            }
        } else {
            flag = false;
        }
        return flag;
    }

    //add category to text file
    static void add(Queue data){

        try{
            PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter("category.txt", true)));
            
            while(!data.isEmpty()) {
                Category obj = (Category)data.dequeue();
                add.println(obj.getCID() + ";" + obj.getCName());
            }
            add.close();
            add.flush();
        }
        catch(IOException ioe){
            System.err.println(ioe);
        }
    }

    //extract all data from textfile
    static LinkedList getAllCategory() {
        LinkedList catList = new LinkedList();

        try {
            BufferedReader in = new BufferedReader(new FileReader("category.txt"));
            String data = in.readLine();

            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data, ";");

                String catID =  inputs.nextToken();
                String catName = inputs.nextToken();

                Category obj = new Category(catID,catName);
                catList.insertAtBack(obj);
                data = in.readLine();
            }
            in.close();
        }catch (IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe);
        }

        return catList;
    }

    //search the category info by ID
    //to search and return category obj
    static Category search(String id) {
        
        Category obj = null;
        LinkedList list = getAllCategory();
        Category data = (Category)list.getHead();

        while(data != null) {
            
            if(data.getCID().equalsIgnoreCase(id)) {
                return data;
            }
            data = (Category)list.getNext();
        }
        return obj;
    }


    //toString
    public String toString() {
        return "\nCategory ID: " + cID + "\nCategory Name: " + cName;
    }


}