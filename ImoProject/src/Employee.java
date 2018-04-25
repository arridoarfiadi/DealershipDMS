//Name: Employee
//Function: CLASS to store Employee Information from Query
public class Employee {

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNum;
    private int Salary;
    private int Zipcode;
    private int SalesQuota;
    private int BranchID;

    public Employee(int id, String firstName,String middleName,String lastName,String phoneNum, int Salary, int Zipcode, int SalesQuota, int BranchID) {
        super();
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phoneNum = phoneNum;
        this.Salary =Salary;
        this.Zipcode = Zipcode;
        this.SalesQuota = SalesQuota;
        this.BranchID = BranchID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName(){
        return middleName;
    }

    public String getPhoneNum(){
        return phoneNum;
    }

    public int getSalary(){
        return Salary;
    }
    public int getZipcode(){
        return Zipcode;
    }
    public int getSalesQuota(){
        return SalesQuota;
    }
    public int getBranchID(){
        return BranchID;
    }
    @Override
    public String toString() {
        return String
                .format("Employee [EmployeeID=%s,FirstName=%s, MiddleName,LastName=%s, PhoneNumber=%s, Salary =%s, Zipcode =%s,SalesQuota = %s, BranchID=%s]",
                        id,firstName,middleName,lastName,phoneNum,Salary,Zipcode,BranchID);
    }



}
