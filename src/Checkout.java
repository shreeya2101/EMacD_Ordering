import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
public class Checkout extends JFrame {
    private String orderLine;
    private JLabel label;
    private Burger_Menu burger;
    private Drink_Menu drink;
    private Side_Menu side;
    private OrderID orderid;
    private int newOID;
    private double totalPrice;

    public Checkout(){

        setTitle("Checkout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(15,1));

        // Create label
        label = new JLabel("Your Order Details");
        label.setFont(new Font("Arial", Font.BOLD,30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        getOrderLine();

        totalPrice=burger.totalBurgerPrice+drink.totalDrinkPrice+side.totalSidePrice;
        System.out.println(totalPrice);
        label = new JLabel("Total Amount to be Paid"+": Rs. "+totalPrice);
        label.setFont(new Font("Arial", Font.BOLD,20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        JButton payButton = new JButton("Pay and Exit");
        add(payButton);
        payButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                burger.totalBurgerPrice=0;
                drink.totalDrinkPrice=0;
                side.totalSidePrice=0;
                totalPrice=0;
                dispose();
                try {
                    //Clear out the Order File to store Orders for next time
                    OutputStream myWriter = new FileOutputStream("./McdOrder.txt", false);
                    PrintWriter pw = new PrintWriter(myWriter, false);
                    pw.flush();
                    pw.close();
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");

                    //Increment Order Number for next Order
                    OutputStream myWriter1 = new FileOutputStream("./OrderID.txt", false);
                    newOID=orderid.oID+1;
                    myWriter1.write(String.valueOf(newOID).getBytes());
                    myWriter1.close();
                    System.out.println("Successfully wrote to the file.");
                }
                catch (IOException ex) {
                    System.out.println("An error occurred.");
                    ex.printStackTrace();
                }
            }
        });
    }
    public void getOrderLine(){
        try {
            File myObj = new File("./McdOrder.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                orderLine= myReader.nextLine();
                label = new JLabel(orderLine);
                label.setFont(new Font("Arial", Font.BOLD,15));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                add(label);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}