package swing;

import model.Polozka;
import swing.Podrobnosti;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class TelZoznam extends JFrame {
    private JTextField hladane;
    private List<Polozka> polozky = new LinkedList<Polozka>();
    private DefaultListModel zoznamModel;
    public TelZoznam(){
        //nazov okna aplikacie
        setTitle("Telefónny zoznam");
        //novy kontajner
        Container okno = getContentPane();
        okno.setLayout(new BorderLayout());

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - okno.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - okno.getHeight()) / 2);
        okno.setLocation(x, y);


        //hladanie
        //zoznam - vytvorenie panela v okne
        JPanel list = new JPanel();
        list.setLayout(new FlowLayout());
        okno.add(list, BorderLayout.PAGE_START);
        //oznacenie
        JLabel hladaj = new JLabel("Zadaj hľadané meno:");
        list.add(hladaj);
        //zadat meno
        hladane = new JTextField(20);
        list.add(hladane);
        zoznamModel = new DefaultListModel();
        JList zoznam = new JList(zoznamModel);
        JScrollPane posuvnik = new JScrollPane(zoznam);
        okno.add(posuvnik, BorderLayout.CENTER);

        //
        hladane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                zoznam.clearSelection();
                String slovo = hladane.getText().trim();
                if (slovo.length()!=0){
                    for(int i=0;i<polozky.size();i++) {
                        Polozka pol = polozky.get(i);
                        if(pol.getMeno().indexOf(slovo)!=-1||pol.getPriezvisko().indexOf(slovo)!=1){
                            zoznam.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });




        //
        //zoznam


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
        odstranit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pol = zoznam.getSelectedIndex();
                if (pol!=1) {
                    Polozka polozka = polozky.get(pol);
                    DetailPolozky det = new DetailPolozky(TelZoznam.this, Moznosti.ODSTRANIT, polozka);

                    if (det.Zrusit() == false) {
                        zoznamModel.removeElementAt(pol);
                        pack();
                    }
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
