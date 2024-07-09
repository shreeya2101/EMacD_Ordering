import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Burger_Menu extends JFrame {

    private JLabel label;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private ButtonGroup G1;
    private JRadioButton jRadioButton3;
    private JRadioButton jRadioButton4;
    private ButtonGroup G2;
    private String burgerVegNonveg;
    private String whichBurger;
    public static double burgerPrice;
    public static double totalBurgerPrice;
    private String bSize;
    private String addOn1;
    private String addOn2;
    private String addOn3;
    private int bQty;
    private Burger burger;
    private OrderID orderid;
    private int oID;
    private String BurgerMenuLine;

    public Burger_Menu() {
            this.burgerVegNonveg = burgerVegNonveg;
            this.whichBurger = whichBurger;
            this.bSize = bSize;
            this.addOn1 = addOn1;
            this.addOn2 = addOn2;
            this.addOn3 = addOn3;

            setTitle("Burger Menu");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(25, 1));

            // Create label
            label = new JLabel("Menu");
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label);

            try {
                File myObj = new File("./BurgerMenu.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    BurgerMenuLine= myReader.nextLine();
                    label = new JLabel(BurgerMenuLine);

                    label.setFont(new Font("Arial", Font.BOLD,15));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    add(label);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            label = new JLabel("Choose Burger Type");
            label.setFont(new Font("Arial", Font.BOLD, 20));
            add(label);

            // Initialization of object of "ButtonGroup" class.
            G1 = new ButtonGroup();
            // Initialization of object of "JRadioButton" class.
            jRadioButton1 = new JRadioButton();
            // Initialization of object of "JRadioButton" class.
            jRadioButton2 = new JRadioButton();

            jRadioButton1.setText("Veg");
            jRadioButton2.setText("Non Veg");

            add(jRadioButton1);
            add(jRadioButton2);

            G1.add(jRadioButton1);
            G1.add(jRadioButton2);

            label = new JLabel("Choose your Burger");
            label.setFont(new Font("Arial", Font.BOLD, 20));
            add(label);

            String[] choices = {"Aaloo Tikki Burger","Double Cheeseburger","Veggie Delight Burger"};
            final JComboBox<String> cb = new JComboBox<String>(choices);
            cb.setVisible(true);
            add(cb);

            jRadioButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) {
                    burgerVegNonveg = jRadioButton1.getText();
                    cb.removeAllItems();
                    cb.addItem("Aaloo Tikki Burger");
                    cb.addItem("Double Cheeseburger");
                    cb.addItem("Veggie Delight Burger");
                }
            });

            jRadioButton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e2) {
                    burgerVegNonveg = jRadioButton2.getText();
                    cb.removeAllItems();
                    cb.addItem("Chicken Burger");
                    cb.addItem("Double Egg Burger");
                    cb.addItem("Fish Fry Burger");
                }
            });

            label = new JLabel("Please select an option regular/large");
            label.setFont(new Font("Arial", Font.BOLD, 20));
            add(label);

            // Initialization of object of "ButtonGroup" class.
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

            jRadioButton3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e3) {
                    bSize = jRadioButton3.getText();
                }
            });

            jRadioButton4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e4) {
                    bSize = jRadioButton4.getText();
                }
            });

            // Add components to the frame
            label = new JLabel("Choose your Add Ons");
            label.setFont(new Font("Arial", Font.BOLD, 20));
            add(label);

            JCheckBox c1 = new JCheckBox("Olives");
            JCheckBox c2 = new JCheckBox("Corn");
            JCheckBox c3 = new JCheckBox("Extra Cheese");
            add(c1);
            add(c2);
            add(c3);

            c1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e5) {
                    addOn1 = c1.getText();
                }
            });

            c2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e6) {
                    addOn2 = c2.getText();
                }
            });

            c3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e7) {
                    addOn3 = c3.getText();
                }
            });

            label = new JLabel("Choose Quantity");
            label.setFont(new Font("Arial", Font.BOLD, 20));
            add(label);

            Integer[] qtyChoices = {1, 2, 3, 4, 5};
            final JComboBox<Integer> qtycb = new JComboBox<Integer>(qtyChoices);
            qtycb.setVisible(true);
            add(qtycb);

            JButton addCartButton = new JButton("Add to Cart");
            addCartButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    whichBurger = cb.getSelectedItem().toString();
                    bQty = (int) qtycb.getSelectedItem();
                    Burger B1 = new Burger(burgerVegNonveg, whichBurger, bSize, addOn1, addOn2, addOn3, bQty);
                    burgerPrice = B1.calcBurgerPrice(burgerVegNonveg, whichBurger, bSize, addOn1, addOn2, addOn3, bQty);
                    OrderID orderId = new OrderID();
                    oID = orderId.getOrderID();
                    B1.createBurgerOrder(oID, burgerVegNonveg, whichBurger, bSize, addOn1, addOn2, addOn3, bQty, burgerPrice);
                    totalBurgerPrice+=burgerPrice;
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