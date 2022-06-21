package swing;

import model.Polozka;

import javax.swing.*;
import java.awt.*;

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
    }
}
