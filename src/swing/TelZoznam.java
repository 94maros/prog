package swing;

import javax.swing.*;
import java.awt.*;

public class TelZoznam extends JFrame {
    public TelZoznam(){
        //nazov okna aplikacie
        setTitle("Telefónny zoznam");
        //novy kontajner
        Container okno = getContentPane();
        okno.setLayout(new BorderLayout());
        //hladanie
        {//zoznam - vyhladavanie
        JPanel list = new JPanel();
        list.setLayout(new FlowLayout());
        okno.add(list, BorderLayout.PAGE_START);
        //oznacenie
        JLabel hladaj = new JLabel("Zadaj hľadané meno:");
        list.add(hladaj);
        //zadat meno
        JTextField hladane = new JTextField(20);
        list.add(hladane);}

        //zoznam
        JList zoznam = new JList();
        JScrollPane posuvnik = new JScrollPane(zoznam);
        okno.add(posuvnik, BorderLayout.CENTER);

        pack();
    }
}
