import javax.swing.table.AbstractTableModel;
import java.util.List;

//Name: salesByEmployeeTableModel
//Function: CLASS to define the Table Model to be shown on the GUI
public class salesByEmployeeTableModel extends AbstractTableModel{

    private static final int SOLD_DATE_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int LAST_NAME_COL = 2;
    private static final int BRAND_COL = 3;
    private static final int MODEL_COL = 4;
    private static final int MODEL_YEAR_COL = 5;
    private static final int SOLD_PRICE_COL = 6;
    private static final int PROFIT_COL = 7;

    private String[] columnNames = { "Date Sold", "First Name", "Last Name","Brand",
            "Model","Model Year", "Price Sold", "Profit or Loss"};
    private List<SalesByEmployee> salesByEmployees;

    public salesByEmployeeTableModel(List<SalesByEmployee> theSalesE) {
        salesByEmployees = theSalesE;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return salesByEmployees.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public Object getValueAt(int row, int col) {

        SalesByEmployee tempSalesE = salesByEmployees.get(row);

        switch (col) {

            case SOLD_DATE_COL:
                return tempSalesE.getSoldDate();
            case FIRST_NAME_COL:
                return tempSalesE.getFirstName();
            case LAST_NAME_COL:
                return tempSalesE.getLastName();
            case BRAND_COL:
                return tempSalesE.getBrand();
            case MODEL_COL:
                return tempSalesE.getModel();
            case MODEL_YEAR_COL:
                return tempSalesE.getModelYear();
            case SOLD_PRICE_COL:
                return tempSalesE.getSoldPrice();
            case PROFIT_COL:
                return tempSalesE.getProfitorLoss();
            default:
                return tempSalesE.getLastName();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
