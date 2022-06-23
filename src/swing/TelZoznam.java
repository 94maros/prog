package swing;

import model.Polozka;
import swing.Podrobnosti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class TelZoznam extends JFrame {

    private List<Polozka> polozky = new LinkedList<Polozka>();
    private DefaultListModel zoznamModel;
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
        zoznamModel = new DefaultListModel();
        JList zoznam = new JList(zoznamModel);
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
                if (!det.Zrusit()){
                    polozky.add(polozka);
                    zoznamModel.addElement(polozka.getMeno()+" "+polozka.getPriezvisko());
                }
            }
        });
        tlacidla.add(pridat);


        JButton upravit = new JButton("Zmen!");
        upravit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int pol = zoznam.getSelectedIndex();
                Polozka polozka = polozky.get(pol);
                DetailPolozky det = new DetailPolozky(TelZoznam.this, Moznosti.UPRAVIT, polozka);
                det.setVisible(true);

                if(det.Zrusit()==false){
                    zoznamModel.setElementAt(polozka.getMeno()+" "+polozka.getPriezvisko(), pol);
                }
            }
        });
        tlacidla.add(upravit);


        JButton odstranit = new JButton("Vymaz!");
        int pol = zoznam.getSelectedIndex();
        //neviditelne pred oznacenim prvku
        /*if(!zoznam.isSelectedIndex(pol)){
            odstranit.setEnabled(false);
            pack();
        }*/
        odstranit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pol = zoznam.getSelectedIndex();
                //viditelne po oznaceni prvku
                /*if(zoznam.isSelectedIndex(pol)){
                    odstranit.setEnabled(true);
                    pack();
                }*/

                Polozka polozka = polozky.get(pol);
                DetailPolozky det = new DetailPolozky(TelZoznam.this, Moznosti.ODSTRANIT, polozka);
                //det.setVisible(true);

                if(det.Zrusit()==false){
                    zoznamModel.removeElementAt(pol);
                    pack();
                }
            }
        });

        tlacidla.add(odstranit);


        JButton detail = new JButton("Podrobnosti");
        detail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pol = zoznam.getSelectedIndex();
                Polozka polozka = polozky.get(pol);
                Podrobnosti det = new Podrobnosti(TelZoznam.this, Moznosti.DETAIL, polozka);
                det.setVisible(true);
                if (!det.Zrusit()){
                    //zoznamModel.addElement(polozka.getMeno()+" "+polozka.getPriezvisko());
                }
            }
        });
        tlacidla.add(detail);

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
