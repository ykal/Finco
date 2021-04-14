package framework.models;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class Data extends DefaultTableModel {
    /**
     * Populate model column names
     * @param columnNames
     */
    void setColumn(Vector<?> columnNames) {
        columnNames.forEach(columnName -> this.addColumn(columnName));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
