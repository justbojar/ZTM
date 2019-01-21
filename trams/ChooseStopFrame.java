package trams;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Paweł Kulig on 21.01.2019.
 */
public class ChooseStopFrame extends JFrame {

    List<Stop> stops;

    public ChooseStopFrame() {
        stops = Stop.getAllStops();

        setTitle("Wybierz przystanek");
        setBounds(100, 100, 250, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        StopComboboxModel model = new StopComboboxModel(stops.toArray(new Stop[stops.size()]));
        JComboBox<Stop> comboBox = new JComboBox<Stop>(model);
        this.add(comboBox);

        JButton button = new JButton("Pokaz rozklad");
        button.addActionListener(e -> {
            Stop selectedStop = (Stop) comboBox.getSelectedItem();

            TimetableFrame timetableFrame = new TimetableFrame(selectedStop);
            timetableFrame.setVisible(true);
            this.dispose();
        });

        this.add(button);
    }
}
