package trams;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Paweł Kulig on 21.01.2019.
 */
public class TimetableModel extends AbstractTableModel {

    List<TimetableRow> rows;
    String[] columnName = {"Godzina", "Linia"};

    public TimetableModel(List<TimetableRow> rows) {
        this.rows = rows;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TimetableRow row = rows.get(rowIndex);

        switch (columnIndex){
            case 0:
                return row.getTime();
            case 1:
                return row.getLine();
            default:
                return 0;
        }

    }

    @Override
    public String getColumnName(int col) {
        return columnName[col];
    }
}
