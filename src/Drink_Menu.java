import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Drink_Menu extends JFrame {
    private JLabel label;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private ButtonGroup G1;
    private JRadioButton jRadioButton3;
    private JRadioButton jRadioButton4;
    private ButtonGroup G2;
    private String whichDrink;
    public static double drinkPrice ;
    public static double totalDrinkPrice;
    private String dSize;
    private int dQty;
    private Drink drink;
    private OrderID orderid;
    private int oID;
    private String DrinkMenuLine;

    public Drink_Menu() {
        this.whichDrink = whichDrink;
        this.dSize = dSize;

        setTitle("Drink Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(20,1));

        // Create label
        label = new JLabel("Menu");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        try {
            File myObj = new File("./DrinkMenu.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                DrinkMenuLine= myReader.nextLine();
                label = new JLabel(DrinkMenuLine);
                label.setFont(new Font("Arial", Font.BOLD,15));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                add(label);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        label = new JLabel("Choose your Drink");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label);

        String[] choices = {"Coke","Limca","Fanta","Cold Coffee"};
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setVisible(true);
        add(cb);

        label = new JLabel("Please select an option regular/large");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label);

        G2 = new ButtonGroup();
        // Initialization of object of "JRadioButton" class.
        jRadioButton3 = new JRadioButton();
        // Initialization of object of "JRadioButton" class.
        jRadioButton4 = new JRadioButton();

        jRadioButton3.setText("Regular");
        jRadioButton4.setText("Large");

        add(jRadioButton3);
        add(jRadioButton4);

        G2.add(jRadioButton3);
        G2.add(jRadioButton4);

        jRadioButton3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e3) {
                dSize=jRadioButton3.getText();
            }
        });

        jRadioButton4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e4) {
                dSize=jRadioButton4.getText();
            }
        });

        label = new JLabel("Choose Quantity");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label);

        Integer[] qtyChoices = {1,2,3,4,5};
        final JComboBox<Integer> qtycb = new JComboBox<Integer>(qtyChoices);
        qtycb.setVisible(true);
        add(qtycb);

        // Add components to the frame

        JButton addCartButton = new JButton("Add to Cart");
        addCartButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                whichDrink= cb.getSelectedItem().toString();
                dQty=(int)qtycb.getSelectedItem();
                Drink D1 = new Drink(whichDrink, dSize,dQty);
                drinkPrice= D1.calcDrinkPrice(whichDrink, dSize,dQty);
                OrderID orderId = new OrderID();
                oID = orderId.getOrderID();
                D1.createDrinkOrder(oID,whichDrink, dSize,dQty,drinkPrice);
                totalDrinkPrice+=drinkPrice;
            }
        });
        add(addCartButton);

        JButton buyOtherButton = new JButton("Buy Other Items");
        buyOtherButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
        add(buyOtherButton);

        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Checkout checkout = new Checkout();
                checkout.setVisible(true);
            }
        });
        add(placeOrderButton);
    }
}