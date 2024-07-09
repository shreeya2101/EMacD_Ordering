import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Side_Menu extends JFrame {
    private JLabel label;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private ButtonGroup G1;
    private JRadioButton jRadioButton3;
    private JRadioButton jRadioButton4;
    private ButtonGroup G2;
    private JRadioButton jRadioButton5;
    private JRadioButton jRadioButton6;
    private ButtonGroup G3;
    private String whichSide;
    private String sideVegNonveg;
    private String dipReqd;
    public static double sidePrice;
    public static double totalSidePrice;
    private int sQty;
    private Side side;
    private String sSize;
    private OrderID orderid;
    private int oID;
    private String sideMenuLine;

    public Side_Menu() {
        this.whichSide = whichSide;
        this.sideVegNonveg=sideVegNonveg;
        this.dipReqd=dipReqd;
        this.sSize=sSize;

        setTitle("Side Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(25,1));

        // Create label
        label = new JLabel("Menu");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        try {
            File myObj = new File("./SideMenu.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                sideMenuLine= myReader.nextLine();
                label = new JLabel(sideMenuLine);

                label.setFont(new Font("Arial", Font.BOLD,15));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                add(label);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        label = new JLabel("Choose Sides Type");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        //label.setHorizontalAlignment(SwingConstants.CENTER);
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

        label = new JLabel("Choose your Side");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label);

        String[] choices = {"Veggie Wrap","Veg Puff","Cheesy Fries"};
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setVisible(true);
        add(cb);
        jRadioButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e1) {
                sideVegNonveg= jRadioButton1.getText();
                cb.removeAllItems();
                cb.addItem("Veggie Wrap");
                cb.addItem("Veg Puff");
                cb.addItem("Cheesy Fries");
            }
        });

        jRadioButton2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e2) {
                sideVegNonveg= jRadioButton2.getText();
                cb.removeAllItems();
                cb.addItem("Chicken Wrap");
                cb.addItem("Fish Fingers");
                cb.addItem("Chicken Kebab");
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

        jRadioButton3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e3) {
                sSize=jRadioButton3.getText();
            }
        });

        jRadioButton4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e4) {
                sSize=jRadioButton4.getText();
            }
        });

        label = new JLabel("Please select an option for dip");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label);

        G3 = new ButtonGroup();
        // Initialization of object of "JRadioButton" class.
        jRadioButton5 = new JRadioButton();
        // Initialization of object of "JRadioButton" class.
        jRadioButton6 = new JRadioButton();

        jRadioButton5.setText("Yes Dip");
        jRadioButton6.setText("No Dip");

        add(jRadioButton5);
        add(jRadioButton6);

        G3.add(jRadioButton5);
        G3.add(jRadioButton6);

        jRadioButton5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e5) {
                dipReqd=jRadioButton5.getText();
            }
        });

        jRadioButton6.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e6) {
                dipReqd=jRadioButton6.getText();
            }
        });
        // Add components to the frame
        label = new JLabel("Choose Quantity");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        //label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        Integer[] qtyChoices = {1,2,3,4,5};
        final JComboBox<Integer> qtycb = new JComboBox<Integer>(qtyChoices);
        qtycb.setVisible(true);
        add(qtycb);

        JButton addCartButton = new JButton("Add to Cart");
        addCartButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                whichSide= cb.getSelectedItem().toString();
                sQty=(int)qtycb.getSelectedItem();
                Side S1 = new Side(sideVegNonveg,whichSide,dipReqd,sSize,sQty);
                sidePrice = S1.calcSidePrice(sideVegNonveg,whichSide,dipReqd,sSize,sQty);
                OrderID orderId = new OrderID();
                oID = orderId.getOrderID();
                S1.createSideOrder(oID,sideVegNonveg,whichSide,dipReqd,sSize,sQty,sidePrice);
                totalSidePrice+=sidePrice;
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