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

    //toString
    public String toString() {
        return "\nCategory ID: " + catID + "\nCategory Name: " + catName;
    }


}