package trams;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * Created by Paweł Kulig on 21.01.2019.
 */
public class TimetableFrame extends JFrame{

    List<TimetableRow> rows;
    TimetableModel model;

    public TimetableFrame(Stop stop) {
        setTitle("Rozklad jazdy");
        setBounds(100, 100, 905, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout());

        rows = TimetableRow.getTimetableRows(stop.getID());

        JLabel label = new JLabel("Rozklad dla przystanku: " + stop.getDescription());
        contentPane.add(label);

        model = new TimetableModel(rows);
        JTable table = new JTable(model);
        table.setRowMargin(2);
        table.setRowHeight(18);
        table.setBorder(null);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setBounds(10, 139, 859, 120);
        contentPane.add(scrollPane);
        scrollPane.setViewportView(table);

        JButton quitButton = new JButton("Powrot");
        this.add(quitButton);
        quitButton.addActionListener(e -> {
            ChooseStopFrame frame = new ChooseStopFrame();
            frame.setVisible(true);
            this.setVisible(false);
            dispose();
        });
    }
}
