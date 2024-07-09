import java.io.IOException;
import java.io.*;

public class Drink{
    public static double totalDrinkPrice;
    private String whichDrink;
    public static double drinkPrice;
    private double firstDrinkPrice;
    public static double finalDrinkPrice;
    private String dSize;
    private int dQty;

    public Drink(String which_drink,String dSize,int dQty) {
        this.whichDrink = whichDrink;
        this.dSize = dSize;
        this.dQty=dQty;
    }

    public double calcDrinkPrice(String which_drink,String dSize,int dQty) {
        drinkPrice=50;
        if (dSize== "Large") {
            drinkPrice += 20;

        }else{}
        
        drinkPrice=drinkPrice*dQty;
        return drinkPrice;
    }

    public void createDrinkOrder(int oID, String whichDrink,String dSize,int dQty,double finalDrinkPrice){
        String s=oID+"  "+whichDrink+"  "+dSize+"  "+dQty+"  "+drinkPrice;
        try {
            OutputStream myWriter = new FileOutputStream("./McdOrder.txt",true);
            myWriter.write(s.getBytes());
            myWriter.write("\r\n".getBytes());

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}