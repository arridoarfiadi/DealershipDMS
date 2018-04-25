//Name: Vehicle
//Function: CLASS to store Vehicle Information from Query
public class Vehicle {
    private String VIN;
    private String Brand;
    private String Model;
    private int ModelYear;
    private int MSRP;
    private int Mileage;
    private String Conditions;
    private String Color;
    private String BranchName;

    public Vehicle(String VIN, String Brand, String Model, int ModelYear, int MSRP, int Mileage, String Conditions, String Color, String BranchName){
        super();
        this.VIN = VIN;
        this.Brand = Brand;
        this.Model = Model;
        this.ModelYear = ModelYear;
        this.MSRP = MSRP;
        this.Mileage= Mileage;
        this.Conditions = Conditions;
        this.Color = Color;
        this.BranchName = BranchName;
    }

    public String getVIN(){
        return VIN;

    }
    public String getBrand(){
        return Brand;
    }

    public String getModel(){
        return Model;
    }
    public int getModelYear(){
        return ModelYear;
    }
    public int getMSRP(){
        return MSRP;
    }
    public int getMileage(){
        return Mileage;
    }

    public String getConditions(){
        return Conditions;
    }
    public String getColor(){
        return Color;
    }
    public String getBranchName(){
        return BranchName;
    }



    @Override
    public String toString() {
        return String
                .format("Vehicle [VIN =%s, Brand=%s, Model=%s, ModelYear=%s, MSRP=%s, Mileage =%s, Conditions=%s, Color=%s, BranchName=%s]",
                        VIN,Brand,Model,ModelYear,MSRP,Mileage,Conditions,Color,BranchName);
    }


}
