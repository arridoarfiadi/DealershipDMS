import javax.swing.table.AbstractTableModel;
import java.util.List;

//Name: customerTableModel
//Function: CLASS to define the Table Model to be shown on the GUI

public class customerTableModel extends AbstractTableModel {
    private static final int ID_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int LAST_NAME_COL = 2;
    private static final int PHONE_NUM_COL = 3;

    private String[] columnNames = { "CustomerID", "First Name", "Last Name","Phone Number"};
    private List<Customer> customers;

    public customerTableModel(List<Customer> theCustomer) {
        customers = theCustomer;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public Object getValueAt(int row, int col) {

        Customer tempCustomer = customers.get(row);

        switch (col) {

            case ID_COL:
                return tempCustomer.getId();
            case FIRST_NAME_COL:
                return tempCustomer.getFirstName();
            case LAST_NAME_COL:
                return tempCustomer.getLastName();
            case PHONE_NUM_COL:
                return tempCustomer.getPhoneNum();

            default:
                return tempCustomer.getLastName();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
