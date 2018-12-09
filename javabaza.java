import java.sql.*;

public class DockerConnectMySQL {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://10.0.10.3:3306/baza";

   static final String USER = "PLmysql";
   static final String PASS = "123qwe";
   
   public static void main(String[] args) throws InterruptedException{
   Connection conn = null;
   Statement stmt = null;
   Boolean baseExist = false;
   Boolean login = true;
   String sql;
   
   //Class.forName("com.mysql.cj.jdbc.Driver");
   
    while(login) {
        try{
          	System.out.println("Connecting to database...");
          	Class.forName("com.mysql.cj.jdbc.Driver");            
      	    conn = DriverManager.getConnection(DB_URL,USER,PASS);
            login = false; // Break out of loop because we got a connection - no exception was thrown
        }catch(SQLException se){
		Thread.sleep(10000);
            //se.printStackTrace();
        }catch(Exception e){
		Thread.sleep(10000);
            //e.printStackTrace();
        } finally {
        	//System.out.println("Connecting to database...");
        }
    }
    
    try{        
    	System.out.println("Check if table in base exist");
      	DatabaseMetaData md = conn.getMetaData();
      	ResultSet rs = md.getTables(null, null, "Persons", null);
      	while (rs.next()) {
            System.out.println("Table Exist");
	    	baseExist = true;
      	}
      	rs = null;  
      	if(!baseExist){
	      	System.out.println("Creating Table");
	      	stmt = conn.createStatement();
	      	sql = "CREATE TABLE Persons (PersonID int, LastName varchar(255), FirstName varchar(255), Address varchar(255), City varchar(255) )";
	      	stmt.executeUpdate(sql);
	      	stmt = null;
	    }
	   
      	stmt = conn.createStatement();
      	System.out.println("Inserting Data to Table");
      	sql = "INSERT INTO Persons (PersonID, LastName, FirstName, Address, City) VALUES (1, 'Nazwisko1', 'Imie1', 'Ulica1','Lublin'), (2, 'Nazwisko2', 'Imie2', 'Ulica2','Lublin'), (3, 'Nazwisko3', 'Imie3', 'Ulica3','Lublin')";
      	stmt.executeUpdate(sql);	 
      	stmt = null;
	   
      	stmt = conn.createStatement();
      	sql = "SELECT PersonID, FirstName, LastName, Address, City FROM Persons";
      	rs = stmt.executeQuery(sql);

	    while(rs.next()){
		    int id  = rs.getInt("PersonID");
        	String first = rs.getString("FirstName");
         	String last = rs.getString("LastName");
	 		String address = rs.getString("Address");
	 		String city = rs.getString("City");
		
         	System.out.println("ID: " + id);
         	System.out.println(", First: " + first);
         	System.out.println(", Last: " + last);
	 		System.out.println(", Address: " + address);
	 		System.out.println(", City: " + city);
      	}
      	
      	rs.close();
      	stmt.close();
      	conn.close();
   		
    }catch(SQLException se){
      	se.printStackTrace();
   	}catch(Exception e){
	    e.printStackTrace();
   	}finally{
		try{
         	if(stmt!=null) stmt.close();
      	}catch(SQLException se2){
      	
        }
      	try{
		    if(conn!=null) conn.close();
      	}catch(SQLException se){
		    se.printStackTrace();
      	}
   	}
 	}
}