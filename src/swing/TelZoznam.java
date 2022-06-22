package swing;

import model.Polozka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelZoznam extends JFrame {
    public TelZoznam(){
        //nazov okna aplikacie
        setTitle("Telefónny zoznam");
        //novy kontajner
        Container okno = getContentPane();
        okno.setLayout(new BorderLayout());
        //hladanie
        {//zoznam - vytvorenie panela v okne
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

        JPanel tlacidla = new JPanel();
        tlacidla.setLayout(new FlowLayout());
        okno.add(tlacidla, BorderLayout.PAGE_END);

        JButton pridat = new JButton("Pridaj!");
        pridat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polozka polozka = new Polozka();
                DetailPolozky det = new DetailPolozky(TelZoznam.this, Moznosti.PRIDAT, polozka);
                det.setVisible(true);
            }
        });
        tlacidla.add(pridat);
        JButton upravit = new JButton("Zmen!");
        upravit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polozka polozka = new Polozka();
                DetailPolozky det = new DetailPolozky(TelZoznam.this, Moznosti.UPRAVIT, polozka);
                det.setVisible(true);
            }
        });
        tlacidla.add(upravit);
        JButton odstranit = new JButton("Vymaz!");
        odstranit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polozka polozka = new Polozka();
                DetailPolozky det = new DetailPolozky(TelZoznam.this, Moznosti.ODSTRANIT, polozka);
                det.setVisible(true);
            }
        });


        tlacidla.add(odstranit);

        JButton ukoncit = new JButton("Koniec!");
        ukoncit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        tlacidla.add(ukoncit);


                pack();
    }
}
