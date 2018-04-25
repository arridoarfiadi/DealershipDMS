//Name: Sales
//Function: CLASS to store Sales Information from Query
public class Sales {
    private int id;
    private String soldDate;
    private String VIN;
    private String brand;
    private String model;
    private int ModelYear;
    private int soldPrice;

    private int ProfitorLoss;




    public Sales(int id, String soldDate, String VIN, String brand, String model, int ModelYear, int soldPrice,int ProfitorLoss) {
        super();
        this.soldDate = soldDate;
        this.brand = brand;
        this.model = model;
        this.ModelYear = ModelYear;
        this.soldPrice = soldPrice;
        this.ProfitorLoss = ProfitorLoss;
        this.VIN = VIN;
        this.id = id;
    }

    public int getId(){
        return id;
    }
    public String getSoldDate() {
        return soldDate;
    }


    public String getVIN() {
        return VIN;
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
                .format("Sales [SalesID =%s, SoldDate=%s,VIN=%s, Brand=%s, Model=%s, ModelYear=%s, SoldPrice=%s, ProfitorLoss =%s]",
                        id,soldDate,VIN,brand,model,ModelYear,soldPrice,ProfitorLoss);
    }
}
