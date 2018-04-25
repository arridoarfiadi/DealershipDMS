//Name: SalesByEmployee
//Function: CLASS to store SalesByEmployee Information from Query
public class SalesByEmployee {
    private String soldDate;
    private String firstName;
    private String lastName;
    private String brand;
    private String model;
    private int ModelYear;
    private int soldPrice;
    private int ProfitorLoss;

    public SalesByEmployee(String soldDate, String firstName, String lastName,String brand, String model, int ModelYear, int soldPrice,int ProfitorLoss) {
        super();
        this.soldDate = soldDate;
        this.lastName = lastName;
        this.firstName = firstName;
        this.brand = brand;
        this.model = model;
        this.ModelYear = ModelYear;
        this.soldPrice = soldPrice;
        this.ProfitorLoss = ProfitorLoss;
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
    public int getProfitorLoss(){
        return ProfitorLoss;
    }
    @Override
    public String toString() {
        return String
                .format("SalesByEmployee [SoldDate=%s,FirstName=%s ,LastName=%s, Brand=%s, Model=%s, ModelYear=%s, SoldPrice=%s, ProfitorLoss =%s]",
                        soldDate,firstName,lastName,brand,model,ModelYear,soldPrice,ProfitorLoss);
    }
}
