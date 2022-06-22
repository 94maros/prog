package swing;

import model.Polozka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.concurrent.Flow;

public class DetailPolozky extends JDialog {
    String predv;
    public DetailPolozky(TelZoznam zoznam, Moznosti moznosti, Polozka polozka){
        super(zoznam, true);
        switch(moznosti) {
            case PRIDAT:
                setTitle("Pridať položku");
                break;
            case UPRAVIT:
                setTitle("Upraviť položku");
                break;
            case ODSTRANIT:
                setTitle("Odstrániť položku");
                break;
        }
        Container okno = getContentPane();
        okno.setLayout(new BoxLayout(okno, BoxLayout.PAGE_AXIS));
        JPanel udaje = new JPanel();
        udaje.setLayout(new BoxLayout(udaje, BoxLayout.LINE_AXIS));
        okno.add(udaje);

        JPanel stlpec1 = new JPanel();
        stlpec1.setLayout(new BoxLayout(stlpec1, BoxLayout.PAGE_AXIS));
        udaje.add(stlpec1);

        JPanel stlpec2 = new JPanel();
        stlpec2.setLayout(new BoxLayout(stlpec2, BoxLayout.PAGE_AXIS));
        udaje.add(stlpec2);

        JPanel meno = new JPanel();
        meno.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec1.add(meno);
        JLabel popisMeno = new JLabel("Meno:");
        meno.add(popisMeno);
        JTextField zadajMeno = new JTextField(20);
        meno.add(zadajMeno);

        JPanel priezvisko = new JPanel();
        priezvisko.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec1.add(priezvisko);
        JLabel popisPriezvisko = new JLabel("Priezvisko:");
        priezvisko.add(popisPriezvisko);
        JTextField zadajPriezvisko = new JTextField(20);
        priezvisko.add(zadajPriezvisko);

        JPanel adresa = new JPanel();
        adresa.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec1.add(adresa);
        JLabel popisUlica = new JLabel("Ulica:");
        adresa.add(popisUlica);
        JTextField zadajUlicu = new JTextField(20);
        adresa.add(zadajUlicu);

        JPanel mesto = new JPanel();
        mesto.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec2.add(mesto);
        JLabel popisMesto = new JLabel("Mesto:");
        mesto.add(popisMesto);
        JTextField zadajMesto = new JTextField(10);
        mesto.add(zadajMesto);

        JPanel stat = new JPanel();
        stat.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec2.add(stat);
        JLabel popisStat = new JLabel("Stat:");
        stat.add(popisStat);
        JComboBox zadajStat = new JComboBox(new String[] {"Slovensko", "Česko", "Maďarsko", "Poľsko", "Rakúsko"});
        stat.add(zadajStat);


        //zamer - vybrat stat a zmeni sa predvolba automaticky
        zadajStat.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //if(zadajStat.contentsChanged()) zmena();
            }
        });


        JPanel telC = new JPanel();
        telC.setLayout(new FlowLayout(FlowLayout.RIGHT));
        stlpec2.add(telC);
        JLabel popisCislo = new JLabel("Telefónne č.:");
            switch (zadajStat.getSelectedItem().toString()) {
                //zadajStat.getSelectedItem()
                case "Slovensko":
                    predv = "+421";
                    break;
                case "Cesko":
                    predv = "+420";
                    break;
                case "Madarsko":
                    predv = "+36";
                    break;
                case "Rakusko":
                    predv = "+43";
                    break;
                case "Polsko":
                    predv = "+48";
                    break;

        }
        JLabel predvolba  =new JLabel(String.valueOf(predv));

        telC.add(popisCislo);telC.add(predvolba);
        JTextField zadajCislo = new JTextField(10);
        telC.add(zadajCislo);


        JPanel tlacidla = new JPanel();
        tlacidla.setLayout(new FlowLayout());
        okno.add(tlacidla);
        if(moznosti==Moznosti.PRIDAT) {
            JButton vytvor = new JButton("Vytvor!");
            tlacidla.add(vytvor);
        }
        if(moznosti==Moznosti.UPRAVIT) {
            JButton uprav = new JButton("Zmen!");
            tlacidla.add(uprav);
        }
        if(moznosti==Moznosti.ODSTRANIT) {
            JButton zmaz = new JButton("Vymaz");
            tlacidla.add(zmaz);
        }

        JButton zrus = new JButton("Zrušiť!");
        zrus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    DetailPolozky.this.setVisible(false);
                }
        });
        tlacidla.add(zrus);

        pack();


    }

}
