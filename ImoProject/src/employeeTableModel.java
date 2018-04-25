import javax.swing.table.AbstractTableModel;
import java.util.List;

//Name: employeeTableModel
//Function: CLASS to define the Table Model to be shown on the GUI

public class employeeTableModel extends AbstractTableModel{
    private static final int ID_COL = 0;
    private static final int FIRST_NAME_COL = 1;
    private static final int LAST_NAME_COL = 2;
    private static final int PHONE_NUM_COL = 3;
    private static final int SALARY_COL = 4;
    private static final int SALES_QUOTA_COL = 5;

    private String[] columnNames = { "EmployeeID", "First Name", "Last Name","Phone Number",
            "Salary","Sales Quota"};
    private List<Employee> employees;

    public employeeTableModel(List<Employee> theEmployees) {
        employees = theEmployees;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public Object getValueAt(int row, int col) {

        Employee tempEmployee = employees.get(row);

        switch (col) {

            case ID_COL:
                return tempEmployee.getId();
            case FIRST_NAME_COL:
                return tempEmployee.getFirstName();
            case LAST_NAME_COL:
                return tempEmployee.getLastName();
            case PHONE_NUM_COL:
                return tempEmployee.getPhoneNum();
            case SALARY_COL:
                return tempEmployee.getSalary();
            case SALES_QUOTA_COL:
                return tempEmployee.getSalesQuota();
            default:
                return tempEmployee.getLastName();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
