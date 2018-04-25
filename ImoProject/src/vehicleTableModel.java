import javax.swing.table.AbstractTableModel;
import java.util.List;


//Name: vehicleTableModel
//Function: CLASS to define the Table Model to be shown on the GUI
public class vehicleTableModel extends AbstractTableModel {

    private static final int VIN_COL = 0;
    private static final int BRAND_COL = 1;
    private static final int MODEL_COL = 2;
    private static final int MODEL_YEAR_COL = 3;
    private static final int MSRP_COL = 4;
    private static final int MILEAGE_COL = 5;
    private static final int CONDITION_COL = 6;
    private static final int COLOR_COL = 7;
    private static final int BRANCH_NAME = 8;

    private String[] columnNames = { "VIN","Brand", "Model","Model Year", "Price", "Mileage", "Condition", "Color", "Branch"};
    private List<Vehicle> vehicles;
    public vehicleTableModel(List<Vehicle> theVehicle) {
        vehicles = theVehicle;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return vehicles.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public Object getValueAt(int row, int col) {

        Vehicle tempVehicle = vehicles.get(row);

        switch (col) {

            case VIN_COL:
                return tempVehicle.getVIN();
            case BRAND_COL:
                return tempVehicle.getBrand();
            case MODEL_COL:
                return tempVehicle.getModel();
            case MODEL_YEAR_COL:
                return tempVehicle.getModelYear();
            case MSRP_COL:
                return tempVehicle.getMSRP();
            case MILEAGE_COL:
                return tempVehicle.getMileage();
            case CONDITION_COL:
                return tempVehicle.getConditions();
            case COLOR_COL:
                return tempVehicle.getColor();
            case BRANCH_NAME:
                return tempVehicle.getBranchName();
            default:
                return tempVehicle.getVIN();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}


