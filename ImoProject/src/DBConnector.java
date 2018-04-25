//Libraries
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Name: DBConnector
//Function: Serves as the API for the program that provides the Statements, connect to the Database and perform queries/insertion
public class DBConnector {

    //Connecting to the DB
	Connection conn;
	//Takes in the hostAddress, username and password to connect to DB
	public DBConnector(String host, String username, String password) {
		try {
			this.conn = (Connection) DriverManager.getConnection(host, username, password);
			System.out.println("DB connected");
		}
		catch (Exception e){
			System.out.println("DB not connected");
			
		}
	}


    //Name: convertRowToEmployee
    //Function: convert the result set into employee class
    //Returns: an Employee class object
	private Employee convertRowToEmployee(ResultSet myRs) throws SQLException {

	    //Gets value from result set
		int id = myRs.getInt("EmployeeID");
		String firstName= myRs.getString("FirstName");
		String middleName= myRs.getString("MiddleName");
		String lastName = myRs.getString("LastName");
		String phoneNum= myRs.getString("PhoneNumber");
		int Salary =  myRs.getInt("Salary");
		int Zipcode=myRs.getInt("Zipcode");
		int SalesQuota= myRs.getInt("SalesQuota");
		int BranchID=myRs.getInt("BranchID");
		Employee tempEmployee = new Employee(id,firstName,middleName,lastName,phoneNum,Salary,Zipcode,SalesQuota,BranchID);

		return tempEmployee;
	}

	//Name: getEmployeeByLN
    //Function: get employee information by their last name
    //Returns: list of Employee class object
	public List<Employee> getEmployeeByLN(String lastName, String branch){
		PreparedStatement myStatement = null;
		ResultSet myResult =null;
		List<Employee> list = new ArrayList<>();

		try {
		    //for all branch
			if (branch == "All Branch") {
				myStatement = conn.prepareStatement("Select * from Employee where LastName like ?");
				myStatement.setString(1, lastName);
				myResult = myStatement.executeQuery();
			}
			//for specific branches
			else{
				myStatement = conn.prepareStatement("Select e.* from Employee e, Branch b where LastName like ? and e.BranchID = b.BranchID and b.BranchName like ?");
				myStatement.setString(1, lastName);
				myStatement.setString(2, branch);
				myResult = myStatement.executeQuery();
			}
			//gets the data
			while (myResult.next()){
				Employee tempEmp = convertRowToEmployee(myResult);
				list.add(tempEmp);
			}

		}


		catch (Exception e){
			System.out.println("Insert query error");
		}
		return list;

	}

    //Name: getEmployeeByFNLN
    //Function: get employee information by their first name and last name
    //Returns: list of Employee class object
	public List<Employee> getEmployeeByFNLN(String firstName, String lastName, String branch){
		PreparedStatement myStatement = null;
		ResultSet myResult =null;
		List<Employee> list = new ArrayList<>();

		try {
		    //for all branches
			if (branch == "All Branch") {
				myStatement = conn.prepareStatement("Select * from Employee where FirstName like ? and LastName like ?");
				myStatement.setString(1, firstName);
				myStatement.setString(2, lastName);
				myResult = myStatement.executeQuery();
			}
			//for specific branches
			else{
				myStatement = conn.prepareStatement("Select e.* from Employee e, Branch b where FirstName like ? and LastName like ? and e.BranchID = b.BranchID and b.BranchName like ?");
				myStatement.setString(1, firstName);
				myStatement.setString(2, lastName);
				myStatement.setString(3, branch);
				myResult = myStatement.executeQuery();
			}
            //gets the data
			while (myResult.next()){
				Employee tempEmp = convertRowToEmployee(myResult);
				list.add(tempEmp);
			}
		}

		catch (Exception e){
			System.out.println("Insert query error");
		}
		return list;
	}

    //Name: getAllEmployee
    //Function: get employee information
    //Returns: list of Employee class object
	public List<Employee> getAllEmployee(String branch){
		List<Employee> list = new ArrayList<>();
		PreparedStatement myStatement = null;
		ResultSet myResult =null;
		try {
		    //for all branch
			if (branch == "All Branch"){
				myStatement = conn.prepareStatement("Select * from Employee");
				myResult = myStatement.executeQuery();
			}
			//for specific branch
			else{
				myStatement = conn.prepareStatement("Select e.* from Employee e, Branch b where e.BranchID = b.BranchID and b.BranchName like ?");
				myStatement.setString(1,branch);
				myResult = myStatement.executeQuery();
			}

			//gets the data
			while (myResult.next()){
				Employee tempEmp = convertRowToEmployee(myResult);
				list.add(tempEmp);
			}
		}

		catch (Exception e){
			System.out.println("Insert query error");
		}
		return list;
	}



    //Name: convertRowToCustomer
    //Function: convert the result set into Customer class
    //Returns: an Customer class object
    private Customer convertRowToCustomer(ResultSet myRs) throws SQLException{
	    //converts the data
        int id = myRs.getInt("CustomerID");
        String firstName= myRs.getString("FirstName");
        String middleName= myRs.getString("MiddleName");
        String lastName = myRs.getString("LastName");
        String phoneNum= myRs.getString("PhoneNumber");
        String SSN = myRs.getString("SSN");
        Customer tempCustomer = new Customer(id, firstName,middleName,lastName,phoneNum,SSN);
        return tempCustomer;

    }

    //Name: getCustomerByLN
    //Function: get customer information by their last name
    //Returns: list of Customer class object
	public List<Customer> getCustomerByLN(String lastName, String branch) {
        PreparedStatement myStatement = null;
        ResultSet myResult =null;
        List<Customer> list = new ArrayList<>();
		try {
		    //for all branch
            if (branch == "All Branch") {
                myStatement = conn.prepareStatement("Select * from Customer where LastName like ?");
                myStatement.setString(1, lastName);
                myResult = myStatement.executeQuery();
            }
            //for specific branch
            else{
                myStatement = conn.prepareStatement("Select c.* from Customer c, Branch b, CustomerList cl where c.LastName like ? and b.BranchName like ? and cl.BranchID = b.BranchID and c.CustomerID = cl.CustomerID");
                myStatement.setString(1, lastName);
                myStatement.setString(2, branch);
                myResult = myStatement.executeQuery();
            }
            //gets the data
            while (myResult.next()){
                Customer tempCus = convertRowToCustomer(myResult);
                list.add(tempCus);
            }


		}
		catch (Exception e) {
			System.out.println("Insert query error");
		}
		return list;
	}

    //Name: getCustomerByFNLN
    //Function: get employee information by their first name and last name
    //Returns: list of Customer class object
	public List<Customer> getCustomerByFNLN(String firstName, String lastName, String branch) {
        PreparedStatement myStatement = null;
        ResultSet myResult =null;
        List<Customer> list = new ArrayList<>();
        try {
            //for all branch
            if (branch == "All Branch") {
                myStatement = conn.prepareStatement("Select * from Customer where LastName like ? and FirstName like ?");
                myStatement.setString(1, lastName);
                myStatement.setString(2, firstName);
                myResult = myStatement.executeQuery();
            }
            //for specific branch
            else{
                myStatement = conn.prepareStatement("Select c.* from Customer c, Branch b, CustomerList cl where c.LastName like ? and c.FirstName like ? and b.BranchName like ? and cl.BranchID = b.BranchID and c.CustomerID = cl.CustomerID");
                myStatement.setString(1, lastName);
                myStatement.setString(2, firstName);
                myStatement.setString(3, branch);
                myResult = myStatement.executeQuery();
            }
            //gets the data
            while (myResult.next()){
                Customer tempCus = convertRowToCustomer(myResult);
                list.add(tempCus);
            }


        }
        catch (Exception e) {
            System.out.println("Insert query error");
        }
        return list;
	}

    //Name: getCustomer
    //Function: get customer information
    //Returns: list of Customer class object
	public List<Customer> getCustomer(String branch){
        PreparedStatement myStatement = null;
        ResultSet myResult =null;
        List<Customer> list = new ArrayList<>();
        try {
            //for all branch
            if (branch == "All Branch") {
                myStatement = conn.prepareStatement("Select * from Customer");
                myResult = myStatement.executeQuery();
            }
            //for specific branch
            else{
                myStatement = conn.prepareStatement("Select c.* from Customer c, Branch b, CustomerList cl where b.BranchName like ? and cl.BranchID = b.BranchID and c.CustomerID = cl.CustomerID");
                myStatement.setString(1, branch);
                myResult = myStatement.executeQuery();
            }
            //display(columns,myResult);
            while (myResult.next()){
                Customer tempCus = convertRowToCustomer(myResult);
                list.add(tempCus);
            }


        }
        catch (Exception e) {
            System.out.println("Insert query error");
        }
        return list;
    }


    //Name: convertRowToSalesC
    //Function: convert the result set into SalesByCustomer class
    //Returns: an SalesByCustomer class object
    private SalesByCustomer convertRowToSalesC(ResultSet myRs) throws SQLException{
	    //converts the data
        String soldDate = myRs.getString("SoldDate");
        String firstName= myRs.getString("FirstName");
        String lastName = myRs.getString("LastName");
        String Brand= myRs.getString("Brand");
        String Model = myRs.getString("Model");
        int ModelYear = myRs.getInt("ModelYear");
        int SoldPrice = myRs.getInt("SoldPrice");
        SalesByCustomer tempSalesC = new SalesByCustomer(soldDate,firstName,lastName,Brand,Model, ModelYear,SoldPrice);
        return tempSalesC;
    }

    //Name: getSalesByCustomer
    //Function: get Sales information
    //Returns: list of SalesByCustomer class object
    public List<SalesByCustomer> getSalesByCustomer(String branch){
        PreparedStatement myStatement = null;
        ResultSet myResult =null;
        List<SalesByCustomer> list = new ArrayList<>();
        try {
            if (branch == "All Branch") {
                myStatement = conn.prepareStatement("Select s.SoldDate, c.FirstName, c.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice from Sales s, Vehicle v, Customer c where s.CustomerID = c.CustomerID and s.VIN = v.VIN");
                myResult = myStatement.executeQuery();
            }
            else{
                myStatement = conn.prepareStatement("Select s.SoldDate, c.FirstName, c.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice from Sales s, Vehicle v, Customer c, Branch b where s.CustomerID = c.CustomerID and s.VIN = v.VIN and b.BranchID= v.BranchID and b.BranchName like ?");
                myStatement.setString(1, branch);
                myResult = myStatement.executeQuery();
            }
            //display(columns,myResult);
            while (myResult.next()){
                SalesByCustomer tempSalesC = convertRowToSalesC(myResult);
                list.add(tempSalesC);
            }


        }
        catch (Exception e) {
            System.out.println("Insert query error");
        }
	    return list;
    }

    //Name: getSalesByCustomerByLN
    //Function: get Sales information by Customer Last Name
    //Returns: list of SalesByCustomer class object
	public List<SalesByCustomer> getSalesByCustomerByLN(String lastName,String branch){
		PreparedStatement myStatement = null;
		ResultSet myResult =null;
		List<SalesByCustomer> list = new ArrayList<>();
		try {
			if (branch == "All Branch") {
				myStatement = conn.prepareStatement("Select s.SoldDate, c.FirstName, c.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice from Sales s, Vehicle v, Customer c  where c.LastName like ? and s.CustomerID = c.CustomerID and s.VIN = v.VIN");
				myStatement.setString(1, lastName);
				myResult = myStatement.executeQuery();
			}
			else{
				myStatement = conn.prepareStatement("Select s.SoldDate, c.FirstName, c.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice from Sales s, Vehicle v, Customer c, Branch b where c.LastName like ? and s.CustomerID = c.CustomerID and s.VIN = v.VIN and b.BranchID= v.BranchID and b.BranchName like ?");
				myStatement.setString(1, lastName);
				myStatement.setString(2, branch);
				myResult = myStatement.executeQuery();
			}
			//display(columns,myResult);
			while (myResult.next()){
				SalesByCustomer tempSalesC = convertRowToSalesC(myResult);
				list.add(tempSalesC);
			}


		}
		catch (Exception e) {
			System.out.println("Insert query error");
		}
		return list;
	}

    //Name: getSalesByCustomerByFNLN
    //Function: get Sales information by Customer First Name Last Name
    //Returns: list of SalesByCustomer class object
	public List<SalesByCustomer> getSalesByCustomerByFNLN(String firstName, String lastName,String branch){
		PreparedStatement myStatement = null;
		ResultSet myResult =null;
		List<SalesByCustomer> list = new ArrayList<>();
		try {
			if (branch == "All Branch") {
				myStatement = conn.prepareStatement("Select s.SoldDate, c.FirstName, c.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice from Sales s, Vehicle v, Customer c where c.LastName like ? and c.FirstName like ? and s.CustomerID = c.CustomerID and s.VIN = v.VIN");
				myStatement.setString(1, lastName);
				myStatement.setString(2, firstName);
				myResult = myStatement.executeQuery();

			}
			else{
				myStatement = conn.prepareStatement("Select s.SoldDate, c.FirstName, c.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice from Sales s, Vehicle v, Customer c, Branch b where c.LastName like ? and c.FirstName like ? and s.CustomerID = c.CustomerID and s.VIN = v.VIN and b.BranchID= v.BranchID and b.BranchName like ?");
				myStatement.setString(1, lastName);
				myStatement.setString(2, firstName);
				myStatement.setString(3, branch);
				myResult = myStatement.executeQuery();
			}
			//display(columns,myResult);
			while (myResult.next()){
				SalesByCustomer tempSalesC = convertRowToSalesC(myResult);
				list.add(tempSalesC);
			}


		}
		catch (Exception e) {
			System.out.println("Insert query error");
		}
		return list;
	}


    //Name: convertRowToSalesE
    //Function: convert the result set into SalesByEmployee class
    //Returns: an SalesByEmployee class object
    private SalesByEmployee convertRowToSalesE(ResultSet myRs) throws SQLException{
	    //reads the result
        String soldDate = myRs.getString("SoldDate");
        String firstName= myRs.getString("FirstName");
        String lastName = myRs.getString("LastName");
        String Brand= myRs.getString("Brand");
        String Model = myRs.getString("Model");
        int ModelYear = myRs.getInt("ModelYear");
        int SoldPrice = myRs.getInt("SoldPrice");
        int ProfitorLoss = myRs.getInt("ProfitorLoss");
        SalesByEmployee tempSalesE = new SalesByEmployee(soldDate,firstName,lastName,Brand,Model, ModelYear,SoldPrice,ProfitorLoss);
        return tempSalesE;
    }

    //Name: getSalesByEmployee
    //Function: get Sales information
    //Returns: list of SalesByEmployee class object
    public List<SalesByEmployee> getSalesByEmployee(String branch){
        PreparedStatement myStatement = null;
        ResultSet myResult =null;
        List<SalesByEmployee> list = new ArrayList<>();
        try {
            if (branch == "All Branch") {
                myStatement = conn.prepareStatement("Select s.SoldDate, e.FirstName, e.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice,  s.ProfitorLoss from Sales s, Vehicle v, Employee e where e.EmployeeID = s.EmployeeID and s.VIN = v.VIN");
                myResult = myStatement.executeQuery();
            }
            else{
                myStatement = conn.prepareStatement("Select s.SoldDate, e.FirstName, e.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice,  s.ProfitorLoss from Sales s, Vehicle v, Employee e, Branch b where s.EmployeeID = e.EmployeeID and s.VIN = v.VIN and b.BranchID= v.BranchID and b.BranchName like ?");
                myStatement.setString(1, branch);
                myResult = myStatement.executeQuery();
            }
            //display(columns,myResult);
            while (myResult.next()){
                SalesByEmployee tempSalesE = convertRowToSalesE(myResult);
                list.add(tempSalesE);
            }


        }
        catch (Exception e) {
            System.out.println("Insert query error");
        }
        return list;
    }

    //Name: getSalesByEmployeeByLN
    //Function: get Sales information by Employee Last Name
    //Returns: list of SalesByEmployee class object
    public List<SalesByEmployee> getSalesByEmployeeByLN(String lastName,String branch){
        PreparedStatement myStatement = null;
        ResultSet myResult =null;
        List<SalesByEmployee> list = new ArrayList<>();
        try {
            if (branch == "All Branch") {
                myStatement = conn.prepareStatement("Select s.SoldDate, e.FirstName, e.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice,  s.ProfitorLoss from Sales s, Vehicle v, Employee e where e.LastName like ? and e.EmployeeID = s.EmployeeID and s.VIN = v.VIN");
                myStatement.setString(1, lastName);
                myResult = myStatement.executeQuery();
            }
            else{
                myStatement = conn.prepareStatement("Select s.SoldDate, e.FirstName, e.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice,  s.ProfitorLoss from Sales s, Vehicle v, Employee e, Branch b where e.LastName like ? and s.EmployeeID = e.EmployeeID and s.VIN = v.VIN and b.BranchID= v.BranchID and b.BranchName like ?");
                myStatement.setString(1, lastName);
                myStatement.setString(2, branch);

                myResult = myStatement.executeQuery();
            }
            //display(columns,myResult);
            while (myResult.next()){
                SalesByEmployee tempSalesE = convertRowToSalesE(myResult);
                list.add(tempSalesE);
            }


        }
        catch (Exception e) {
            System.out.println("Insert query error");
        }
        return list;
    }

    //Name: getSalesByEmployeeByFNLN
    //Function: get Sales information by Employee First Name Last Name
    //Returns: list of SalesByEmployee class object
    public List<SalesByEmployee> getSalesByEmployeeByFNLN(String firstName, String lastName,String branch){
        PreparedStatement myStatement = null;
        ResultSet myResult =null;
        List<SalesByEmployee> list = new ArrayList<>();
        try {
            if (branch == "All Branch") {
                myStatement = conn.prepareStatement("Select s.SoldDate, e.FirstName, e.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice,  s.ProfitorLoss from Sales s, Vehicle v, Employee e where e.LastName like ? and e.FirstName like ? and e.EmployeeID = s.EmployeeID and s.VIN = v.VIN");
                myStatement.setString(1, lastName);
                myStatement.setString(2, firstName);
                myResult = myStatement.executeQuery();
            }
            else{
                myStatement = conn.prepareStatement("Select s.SoldDate, e.FirstName, e.LastName, v.Brand, v.Model, v.ModelYear, s.SoldPrice,  s.ProfitorLoss from Sales s, Vehicle v, Employee e, Branch b where e.LastName like ? and e.FirstName like ? and s.EmployeeID = e.EmployeeID and s.VIN = v.VIN and b.BranchID= v.BranchID and b.BranchName like ?");
                myStatement.setString(1, lastName);
                myStatement.setString(2, firstName);
                myStatement.setString(3, branch);
                myResult = myStatement.executeQuery();
            }
            //display(columns,myResult);
            while (myResult.next()){
                SalesByEmployee tempSalesE = convertRowToSalesE(myResult);
                list.add(tempSalesE);
            }


        }
        catch (Exception e) {
            System.out.println("Insert query error");
        }
        return list;
    }



    //Name: convertRowToSales
    //Function: convert the result set into Sales class
    //Returns: an Sales class object
    private Sales convertRowToSales(ResultSet myRs) throws SQLException{
        int id = myRs.getInt("SalesID");
	    String soldDate = myRs.getString("SoldDate");
        String VIN= myRs.getString("VIN");
        String Brand= myRs.getString("Brand");
        String Model = myRs.getString("Model");
        int ModelYear = myRs.getInt("ModelYear");
        int SoldPrice = myRs.getInt("SoldPrice");
        int ProfitorLoss = myRs.getInt("ProfitorLoss");
        Sales tempSales = new Sales(id,soldDate,VIN,Brand,Model, ModelYear,SoldPrice,ProfitorLoss);
        return tempSales;
    }

    //Name: getSalesByID
    //Function: get Sales information by SalesID
    //Returns: list of Sales class object
	public List<Sales> getSalesByID(int id, String branch) {
        PreparedStatement myStatement = null;
        ResultSet myResult =null;
        List<Sales> list = new ArrayList<>();
		try {


            if (branch == "All Branch") {
                myStatement = conn.prepareStatement("SELECT s.SalesID,s.SoldDate,s.VIN,v.Brand, v.Model, v.ModelYear, s.SoldPrice, s.ProfitorLoss FROM Sales s, Vehicle v WHERE SalesID = ? and s.VIN = v.VIN");
                myStatement.setInt(1,id);
                myResult = myStatement.executeQuery();
            }
            else{
                myStatement = conn.prepareStatement("SELECT s.SalesID,s.SoldDate,s.VIN,v.Brand, v.Model, v.ModelYear, s.SoldPrice, s.ProfitorLoss FROM Branch b, Sales s, Vehicle v WHERE SalesID = ? and s.VIN = v.VIN and v.BranchID = b.BranchID and b.BranchName like ?");
                myStatement.setInt(1,id);
                myStatement.setString(2,branch);
                myResult = myStatement.executeQuery();
            }
            while (myResult.next()){
                Sales tempSales = convertRowToSales(myResult);
                list.add(tempSales);
            }
		}

		catch (Exception e) {
			System.out.println("Insert query error");
		}
		return list;
	}

    //Name: getSalesByVIN
    //Function: get Sales information by VIN
    //Returns: list of Sales class object
	public List<Sales> getSalesByVIN(String carVIN, String branch) {
        PreparedStatement myStatement = null;
        ResultSet myResult =null;
        List<Sales> list = new ArrayList<>();
        try {


            if (branch == "All Branch") {
                myStatement = conn.prepareStatement("SELECT s.SalesID,s.SoldDate,s.VIN,v.Brand, v.Model, v.ModelYear, s.SoldPrice, s.ProfitorLoss FROM Sales s, Vehicle v WHERE s.VIN = ? and s.VIN = v.VIN");
                myStatement.setString(1,carVIN);
                myResult = myStatement.executeQuery();
            }
            else{
                myStatement = conn.prepareStatement("SELECT s.SalesID,s.SoldDate,s.VIN,v.Brand, v.Model, v.ModelYear, s.SoldPrice, s.ProfitorLoss FROM Branch b, Sales s, Vehicle v WHERE s.VIN = ? and s.VIN = v.VIN and v.BranchID = b.BranchID and b.BranchName like ?");
                myStatement.setString(1,carVIN);
                myStatement.setString(2,branch);
                myResult = myStatement.executeQuery();
            }
            while (myResult.next()){
                Sales tempSales = convertRowToSales(myResult);
                list.add(tempSales);
            }
        }

        catch (Exception e) {
            System.out.println("Insert query error");
        }
        return list;
	}
    //Name: getSales
    //Function: get Sales information
    //Returns: list of Sales class object
    public List<Sales> getSales(String branch) {
        PreparedStatement myStatement = null;
        ResultSet myResult =null;
        List<Sales> list = new ArrayList<>();
        try {


            if (branch == "All Branch") {
                myStatement = conn.prepareStatement("SELECT s.SalesID,s.SoldDate,s.VIN,v.Brand, v.Model, v.ModelYear, s.SoldPrice, s.ProfitorLoss FROM Sales s, Vehicle v WHERE s.VIN = v.VIN");
                myResult = myStatement.executeQuery();
            }
            else{
                myStatement = conn.prepareStatement("SELECT s.SalesID,s.SoldDate,s.VIN,v.Brand, v.Model, v.ModelYear, s.SoldPrice, s.ProfitorLoss FROM Branch b, Sales s, Vehicle v WHERE s.VIN = v.VIN and v.BranchID = b.BranchID and b.BranchName like ?");

                myStatement.setString(1,branch);
                myResult = myStatement.executeQuery();
            }
            while (myResult.next()){
                Sales tempSales = convertRowToSales(myResult);
                list.add(tempSales);
            }
        }

        catch (Exception e) {
            System.out.println("Insert query error");
        }

        return list;
    }

    //Name: convertRowToVehicle
    //Function: convert the result set into Vehicle class
    //Returns: an Vehicle class object
    public Vehicle convertRowToVehicle(ResultSet myRs) throws SQLException{
        String VIN= myRs.getString("VIN");
        String Brand= myRs.getString("Brand");
        String Model = myRs.getString("Model");
        int ModelYear = myRs.getInt("ModelYear");
        int MSRP = myRs.getInt("MSRP");
        int Mileage = myRs.getInt("Mileage");
        String Conditions= myRs.getString("Conditions");
        String Color= myRs.getString("Color");
        String BranchName = myRs.getString("BranchName");
        Vehicle tempVeh = new Vehicle(VIN,Brand,Model, ModelYear,MSRP,Mileage,Conditions,Color,BranchName);
        return tempVeh;
    }

    //Name: getVehicle
    //Function: get Vehicle information by Model, MaxPrice, Mileage, Codition
    //Returns: list of Vehicle class object
    public List<Vehicle> getVehicle(String Model, int maxPrice, int mileage, String Conditions, String branch){
        PreparedStatement myStatement = null;
        ResultSet myResult =null;
        List<Vehicle> list = new ArrayList<>();
        try {


            if (branch == "All Branch") {
                    myStatement = conn.prepareStatement("SELECT v.VIN,v.Brand, v.Model, v.ModelYear, v.MSRP, v.Mileage, v.Conditions, v.Color, b.BranchName FROM Branch b, Vehicle v WHERE b.BranchID = v.BranchID and v.Model like ? and v.MSRP <= ? and v.Mileage <= ? and v.Conditions like ?");


                    myStatement.setString(1, Model);
                    myStatement.setInt(2, maxPrice);
                    myStatement.setInt(3, mileage);
                    myStatement.setString(4, Conditions);
                    myResult = myStatement.executeQuery();
            }
            else{
                    myStatement = conn.prepareStatement("SELECT v.VIN,v.Brand, v.Model, v.ModelYear, v.MSRP, v.Mileage, v.Conditions, v.Color, b.BranchName FROM Branch b, Vehicle v WHERE b.BranchID = v.BranchID and v.Model like ? and v.MSRP <= ? and v.Mileage <= ? and v.Conditions like ? and b.BranchName like ?");
                    myStatement.setString(1, Model);
                    myStatement.setInt(2, maxPrice);
                    myStatement.setInt(3, mileage);
                    myStatement.setString(4, Conditions);
                    myStatement.setString(5, branch);
                    myResult = myStatement.executeQuery();
            }

            while (myResult.next()){
                Vehicle tempVeh = convertRowToVehicle(myResult);
                list.add(tempVeh);
            }
        }

        catch (Exception e) {
            System.out.println("Insert query error");
        }

        return list;
    }

    //Name: getAllVehicle
    //Function: get Vehicle information
    //Returns: list of Vehicle class object
    public List<Vehicle> getAllVehicle(String branch) {
        PreparedStatement myStatement = null;
        ResultSet myResult = null;
        List<Vehicle> list = new ArrayList<>();
        try {


            if (branch == "All Branch") {
                myStatement = conn.prepareStatement("SELECT v.VIN,v.Brand, v.Model, v.ModelYear, v.MSRP, v.Mileage, v.Conditions, v.Color, b.BranchName FROM Branch b, Vehicle v WHERE b.BranchID = v.BranchID");
                myResult = myStatement.executeQuery();
            }
            else {
                myStatement = conn.prepareStatement("SELECT v.VIN,v.Brand, v.Model, v.ModelYear, v.MSRP, v.Mileage, v.Conditions, v.Color, b.BranchName FROM Branch b, Vehicle v WHERE b.BranchID = v.BranchID and b.BranchName like ?");
                myStatement.setString(1, branch);
                myResult = myStatement.executeQuery();
            }
            while (myResult.next()){
                Vehicle tempVeh = convertRowToVehicle(myResult);
                list.add(tempVeh);
            }

        }
        catch (Exception e) {
            System.out.println("Insert query error");
        }

        return list;
    }

    //Name: insertCustomer
    //Function: Insert New Customer
    //Returns: result for status update
    public String insertCustomer(int id, String first, String middle, String last, String phone, String ssn) {
        String result = "Failed";
	    try {
            PreparedStatement myStatement = conn.prepareStatement("INSERT INTO Customer (CustomerID, FirstName, MiddleName, LastName, PhoneNumber, SSN) VALUES (?,?,?,?,?,?)");
            myStatement.setInt(1,id);
            myStatement.setString(2, first);
            myStatement.setString(3, middle);
            myStatement.setString(4, last);
            myStatement.setString(5, phone);
            myStatement.setString(6, ssn);
            myStatement.execute();
            return "Success";
        }
        catch (Exception e) {
            System.out.println("Insert query error");
        }
        return result;
    }

    //Name: insertEmployee
    //Function: Insert New Employee
    //Returns: result for status update
    public String insertEmployee(int id, String first, String middle, String last, String phone, int salary, int zip, Integer salesQuota, int branch) {
        String result = "Failed";
	    try {
            PreparedStatement myStatement = conn.prepareStatement("INSERT INTO Employee (EmployeeID, FirstName, MiddleName, LastName, PhoneNumber, Salary, Zipcode, SalesQuota, Branch) VALUES (?,?,?,?,?,?,?,?,?)");
            myStatement.setInt(1,id);
            myStatement.setString(2, first);
            myStatement.setString(3, middle);
            myStatement.setString(4, last);
            myStatement.setString(5, phone);
            myStatement.setInt(6, salary);
            myStatement.setInt(7,zip);
            myStatement.setInt(8, salesQuota);
            myStatement.setInt(9, branch);

            myStatement.execute();
            return "Success";
        }
        catch (Exception e) {
            System.out.println("Insert query error");
        }
        return result;
    }

    //Name: insertSales
    //Function: Insert New Sales
    //Returns: result for status update
    public String insertSales(int id, int soldPrice, String soldDate, String vin, int eID, int cID) {
        String result = "Failed";
        try {
            // getting the msrp of the car
            PreparedStatement myStatement = conn.prepareStatement("SELECT MSRP FROM Vehicle WHERE VIN LIKE ?");
            myStatement.setString(1,vin);
            ResultSet myResult = myStatement.executeQuery();
            int msrp = myResult.getInt(1);
            int profitLoss = msrp - soldPrice;

            // inserting a sale to the Sales table
            myStatement = conn.prepareStatement("INSERT INTO Sales (SalesID, SoldPrice, SoldDate, ProfitOrLoss, VIN, EmployeeID, CustomerID) VALUES (?,?,?,?,?,?,?)");
            myStatement.setInt(1,id);
            myStatement.setInt(2, soldPrice);
            myStatement.setString(3, soldDate);
            myStatement.setInt(4, profitLoss);
            myStatement.setString(5, vin);
            myStatement.setInt(6, eID);
            myStatement.setInt(7, cID);
            myStatement.execute();
            return "Success";

        }
        catch (Exception e) {
            System.out.println("Insert query error");
        }
        return result;
    }

    //Name: insertVehicle
    //Function: Insert New Vehicle
    //Returns: result for status update
    public String insertVehicle(String vin, int milage, String condition, String brand, String model, int modelYear, int msrp, int stockNum, String color, int branchID, String titleStatus, String titleDateIssued) {
        String result = "Failed";
	    try {
            // insert to vehicle
            PreparedStatement myStatement = conn.prepareStatement("INSERT INTO Vehicle (VIN, Mileage, Conditions, Brand, Model, ModelYear, MSRP, StockNum, Color, BranchID) VALUES (?,?,?,?,?,?,?,?,?,?)");
            myStatement.setString(1,vin);
            myStatement.setInt(2, milage);
            myStatement.setString(3, condition);
            myStatement.setString(4, brand);
            myStatement.setString(5, model);
            myStatement.setInt(6, modelYear);
            myStatement.setInt(7, msrp);
            myStatement.setInt(8, stockNum);
            myStatement.setString(9, color);
            myStatement.setInt(10, branchID);
            myStatement.execute();

            // insert to vehicle title
            myStatement = conn.prepareStatement("INSERT INTO VehicleTitle (Status, issueDate, VIN) VALUES (?,?,?)");
            myStatement.setString(1, titleStatus);
            myStatement.setString(2, titleDateIssued);
            myStatement.setString(3, vin);

            myStatement.execute();
            return "Success";

        }
        catch (Exception e) {
            System.out.println("Insert query error");
        }
        return result;
    }



    //Name: close
    //Function: closes connection
	public void close(){
		try{
			conn.close();
		}
		catch (Exception e){
			System.out.println("Create View error");
		}
	}


}
