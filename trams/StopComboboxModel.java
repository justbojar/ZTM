package trams;

import javax.swing.*;
import java.util.List;

/**
 * Created by Paweł Kulig on 21.01.2019.
 */
public class StopComboboxModel extends DefaultComboBoxModel<Stop> {

    public StopComboboxModel(Stop[] items) {
        super(items);
    }

    @Override
    public Stop getSelectedItem() {
        Stop selectedStop = (Stop) super.getSelectedItem();

        return selectedStop;
    }
}
