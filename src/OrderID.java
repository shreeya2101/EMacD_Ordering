import java.io.*;
import java.util.*; // Import the Scanner class to read text files

public class OrderID {
    public static int oID;

    public int getOrderID(){
        try {
            File myObj = new File("./OrderID.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                oID = myReader.nextInt();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(oID);
        return oID;
    }
}