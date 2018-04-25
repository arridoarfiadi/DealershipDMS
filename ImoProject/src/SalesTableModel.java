import javax.swing.table.AbstractTableModel;
import java.util.List;


//Name: SalesTableModel
//Function: CLASS to define the Table Model to be shown on the GUI
public class SalesTableModel extends AbstractTableModel {

    private static final int ID_COL = 0;
    private static final int SOLD_DATE_COL = 1;
    private static final int VIN_COL = 2;
    private static final int BRAND_COL = 3;
    private static final int MODEL_COL = 4;
    private static final int MODEL_YEAR_COL = 5;
    private static final int SOLD_PRICE_COL = 6;
    private static final int PROFIT_COL = 7;

    private String[] columnNames = { "SalesID","Date Sold", "VIN","Brand",
            "Model","Model Year", "Price Sold", "Profit or Loss"};
    private List<Sales> sales;

    public SalesTableModel(List<Sales> theSales) {
        sales = theSales;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return sales.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public Object getValueAt(int row, int col) {

        Sales tempSales = sales.get(row);

        switch (col) {

            case ID_COL:
                return tempSales.getId();
            case SOLD_DATE_COL:
                return tempSales.getSoldDate();
            case VIN_COL:
                return tempSales.getVIN();

            case BRAND_COL:
                return tempSales.getBrand();
            case MODEL_COL:
                return tempSales.getModel();
            case MODEL_YEAR_COL:
                return tempSales.getModelYear();
            case SOLD_PRICE_COL:
                return tempSales.getSoldPrice();
            case PROFIT_COL:
                return tempSales.getProfitorLoss();
            default:
                return tempSales.getId();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
