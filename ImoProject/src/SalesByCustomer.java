//Name: SalesByCustomer
//Function: CLASS to store SalesByCustomer Information from Query
public class SalesByCustomer {

    private String soldDate;
    private String firstName;
    private String lastName;
    private String brand;
    private String model;
    private int ModelYear;
    private int soldPrice;

    public SalesByCustomer(String soldDate, String firstName, String lastName,String brand, String model, int ModelYear, int soldPrice) {
        super();
        this.soldDate = soldDate;
        this.lastName = lastName;
        this.firstName = firstName;
        this.brand = brand;
        this.model = model;
        this.ModelYear = ModelYear;
        this.soldPrice = soldPrice;
    }

    public String getSoldDate() {
        return soldDate;
    }


    public String getLastName() {
        return lastName;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getBrand(){
        return brand;
    }

    public String getModel(){
        return model;
    }
    public int getModelYear(){
        return ModelYear;
    }
    public int getSoldPrice(){
        return soldPrice;
    }
    @Override
    public String toString() {
        return String
                .format("SalesByCustomer [SoldDate=%s,FirstName=%s ,LastName=%s, Brand=%s, Model=%s, ModelYear=%s, SoldPrice=%s]",
                        soldDate,firstName,lastName,brand,model,ModelYear,soldPrice);
    }

}
