import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame
{
    private JLabel label;
    public Menu()
    {
        setTitle("McDonald's Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Create label
        label = new JLabel("Welcome to McDonald's!");
        label.setFont(new Font("Arial", Font.BOLD,30));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Create buttons
        JButton burgerButton = new JButton("Burgers");
        JButton drinksButton = new JButton("Drinks");
        JButton sidesButton = new JButton("Sides");

        // Set button actions
        burgerButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                Burger_Menu burgermenu = new Burger_Menu();
                burgermenu.setVisible(true);
            }
        });

        drinksButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                Drink_Menu drinkmenu = new Drink_Menu();
                drinkmenu.setVisible(true);
            }
        });

        sidesButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                Side_Menu sidemenu = new Side_Menu();
                sidemenu.setVisible(true);
            }
        });

        // Set layout
        setLayout(new GridLayout(5, 1));

        // Add components to the frame
        add(label);
        add(burgerButton);
        add(drinksButton);
        add(sidesButton);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Menu().setVisible(true);
            }
        });
    }
}