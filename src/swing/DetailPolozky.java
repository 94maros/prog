package swing;

import model.Polozka;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class DetailPolozky extends JDialog {
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
            udaje.setLayout(new BoxLayout(udaje, BoxLayout.PAGE_AXIS));
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

            JPanel telC = new JPanel();
                telC.setLayout(new FlowLayout(FlowLayout.RIGHT));
                stlpec2.add(telC);
                JLabel popisCislo = new JLabel("Telefónne č.:");
                telC.add(popisCislo);
                JTextField zadajCislo = new JTextField(10);
                telC.add(zadajCislo);



    }

}
