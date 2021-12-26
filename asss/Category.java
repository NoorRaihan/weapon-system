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

            try {
                BufferedReader in = new BufferedReader(new FileReader("category.txt"));
                String data = in.readLine();
    
                while(data != null) {
                    StringTokenizer inputs = new StringTokenizer(data, ";");
                    String  tempID = inputs.nextToken();
    
                    if(tempID.equalsIgnoreCase(ID)) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                    data = in.readLine();
                }
                in.close();
            } catch (IOException ioe) {
                System.err.println("Something went wrong!" +ioe);
            }
        } else {
            flag = false;
        }
        return flag;
    }

    //add category to text file
    static void add(Queue data){

        while(!data.isEmpty())
        {
            Category obj = (Category)data.dequeue();
            try{
                PrintWriter add = new PrintWriter(new BufferedWriter(new FileWriter("category.txt", true)));
                add.println(obj.getCID() + ";" + obj.getCName());

                add.close();
                add.flush();
            }
            catch(IOException ioe){
                System.err.println(ioe);
            }
        }
    }

      //search the category info by ID
    static Category search(String id, String name) { //to search and return category name
        
        Category dat = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader("category.txt"));
            String data = in.readLine();

            while(data != null) {
                StringTokenizer inputs = new StringTokenizer(data, ";");

                String catID =  inputs.nextToken();
                String catName = inputs.nextToken();

                if(catID.equalsIgnoreCase(id) || catName.equalsIgnoreCase(name)) {
                    dat = new Category(catID,catName);
                    break;
                }
                data = in.readLine();
            }
            in.close();
        }catch (IOException ioe) {
            System.err.println("Something went wrong!\n" + ioe);
        }
        return dat;
    }


    //toString
    public String toString() {
        return "\nCategory ID: " + cID + "\nCategory Name: " + cName;
    }


}