//Name: Customer
//Function: CLASS to store Customer Information from Query
public class Customer {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNum;
    private String SSN;

    public Customer(int id, String firstName,String middleName,String lastName,String phoneNum, String SSN) {
        super();
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phoneNum = phoneNum;
        this.SSN = SSN;
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

    public String getSSN(){
        return SSN;
    }
    @Override
    public String toString() {
        return String
                .format("Customer [CustomerID=%s,FirstName=%s, MiddleName,LastName=%s, PhoneNumber=%s, SSN=%s]",
                        id,firstName,middleName,lastName,phoneNum,SSN);
    }
}
