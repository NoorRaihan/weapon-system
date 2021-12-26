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

    private String catID;
    private String catName;

    //constructor
    public Category() { this(null,null); }

    public Category(String catID, String catName) {
        this.catID = catID;
        this.catName = catName;
    }

    //accessor
    public String getCatID() {return catID;}
    public String getCatName() {return catName;}

    //mutator
    public void setAll(String catID, String catName) {
        this.catID = catID;
        this.catName = catName;
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

    //toString
    public String toString() {
        return "\nCategory ID: " + catID + "\nCategory Name: " + catName;
    }


}