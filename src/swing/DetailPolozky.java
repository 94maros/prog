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

            JPanel priezvisko = new JPanel();
                priezvisko.setLayout(new FlowLayout(FlowLayout.RIGHT));
                stlpec1.add(priezvisko);

            JPanel adresa = new JPanel();
                adresa.setLayout(new FlowLayout(FlowLayout.RIGHT));
                stlpec1.add(adresa);

            JPanel mesto = new JPanel();
                mesto.setLayout(new FlowLayout(FlowLayout.RIGHT));
                stlpec2.add(mesto);

            JPanel stat = new JPanel();
                stat.setLayout(new FlowLayout(FlowLayout.RIGHT));
                stlpec2.add(stat);

            JPanel telC = new JPanel();
                telC.setLayout(new FlowLayout(FlowLayout.RIGHT));
                stlpec2.add(telC);


    }

}
