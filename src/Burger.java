import java.io.IOException;
import java.io.*;

public class Burger {
    private String whichBurger;
    public static double burgerPrice;
    public static double totalBurgerPrice;
    private String bSize;
    private String addOn1;
    private String addOn2;
    private String addOn3;
    private String burgerVegNonveg;
    private int bQty;

    public Burger(String burgerVegNonveg,String whichBurger,String bSize,String addOn1,String addOn2,String addOn3,int bQty) {
        this.burgerVegNonveg=burgerVegNonveg;
        this.whichBurger = whichBurger;
        this.bSize = bSize;
        this.addOn1 = addOn1;
        this.addOn2 = addOn2;
        this.addOn3 = addOn3;
        this.bQty=bQty;
    }
    public double calcBurgerPrice(String burgerVegNonveg,String whichBurger,String bSize,String addOn1,String addOn2,String addOn3,int bQty) {
        burgerPrice=80;
        if (burgerVegNonveg == "Non Veg") {
            burgerPrice += 20;
            if (bSize == "Large") {
                burgerPrice += 20;
                if (addOn1 == "Olives") {
                    burgerPrice += 30;
                }
                if (addOn2 == "Corn") {
                    burgerPrice += 40;
                }
                if (addOn3 == "Extra Cheese") {
                    burgerPrice += 50;
                }
            } else {
                if (addOn1 == "Olives") {
                    burgerPrice += 30;
                }
                if (addOn2 == "Corn") {
                    burgerPrice += 40;
                }
                if (addOn3 == "Extra Cheese") {
                    burgerPrice += 50;
                }
            }
        } else {
            if (bSize == "Large") {
                burgerPrice += 20;
                if (addOn1 == "Olives") {
                    burgerPrice += 30;
                }
                if (addOn2 == "Corn") {
                    burgerPrice += 40;
                }
                if (addOn3 == "Extra Cheese") {
                    burgerPrice += 50;
                }
            } else {
                if (addOn1 == "Olives") {
                    burgerPrice += 30;
                }
                if (addOn2 == "Corn") {
                    burgerPrice += 40;
                }
                if (addOn3 == "Extra Cheese") {
                    burgerPrice += 50;
                }
            }
        }
        burgerPrice=burgerPrice*bQty;
        return burgerPrice;
    }

        public void createBurgerOrder( int oID, String burgerVegNonveg, String whichBurger, String bSize, String addOn1, String
        addOn2, String addOn3,int bQty,double burgerPrice){
            String s = oID + "  " + burgerVegNonveg+"  "+ whichBurger + "  " + bSize + "  " + addOn1 + "  " + addOn2 + "  " + addOn3 + "  " +bQty+"  "+ burgerPrice;
            try {
                OutputStream myWriter = new FileOutputStream("./McdOrder.txt", true);
                myWriter.write(s.getBytes());
                myWriter.write("\r\n".getBytes());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }