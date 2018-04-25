import javax.swing.table.AbstractTableModel;
import java.util.List;


//Name: salesByCustomerTableModel
//Function: CLASS to define the Table Model to be shown on the GUI
public class salesByCustomerTableModel extends AbstractTableModel{

    private static final int SOLD_DATE_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int LAST_NAME_COL = 2;
    private static final int BRAND_COL = 3;
    private static final int MODEL_COL = 4;
    private static final int MODEL_YEAR_COL = 5;
    private static final int SOLD_PRICE_COL = 6;

    private String[] columnNames = { "Date Sold", "First Name", "Last Name","Brand",
            "Model","Model Year", "Price Sold"};
    private List<SalesByCustomer> salesByCustomers;

    public salesByCustomerTableModel(List<SalesByCustomer> theSalesC) {
        salesByCustomers = theSalesC;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return salesByCustomers.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public Object getValueAt(int row, int col) {

        SalesByCustomer tempSalesC = salesByCustomers.get(row);

        switch (col) {

            case SOLD_DATE_COL:
                return tempSalesC.getSoldDate();
            case FIRST_NAME_COL:
                return tempSalesC.getFirstName();
            case LAST_NAME_COL:
                return tempSalesC.getLastName();
            case BRAND_COL:
                return tempSalesC.getBrand();
            case MODEL_COL:
                return tempSalesC.getModel();
            case MODEL_YEAR_COL:
                return tempSalesC.getModelYear();
            case SOLD_PRICE_COL:
                return tempSalesC.getSoldPrice();
            default:
                return tempSalesC.getLastName();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
