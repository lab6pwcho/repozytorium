import java.sql.*;

public class plik {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://db:10.0.10.3:3306/zad1";

   static final String USER = "pkalasa";
   static final String PASS = "password";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      Class.forName("com.mysql.jdbc.Driver");

      System.out.println("Czekaj na połączenie...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      stmt = conn.createStatement();
      
	stmt.executeUpdate("CREATE TABLE Tabela(id int, kolumna1 varchar(255), kolumna2 varchar(255), kolumna3 varchar(255));");
	stmt.executeUpdate("INSERT INTO Tabela (id, kolumna1, kolumna2, kolumna3) VALUES (1, 'tekst1', 'tekst2', 'tekst3');");
	stmt.executeUpdate("INSERT INTO Tabela (id, kolumna1, kolumna2, kolumna3) VALUES (2, 'tresc1', 'tresc2', 'tresc3');");
	stmt.executeUpdate("INSERT INTO Tabela (id, kolumna1, kolumna2, kolumna3) VALUES (3, 'tresc1', 'tresc2', 'tresc3');");

      ResultSet result = stmt.executeQuery("SELECT * FROM Tabela");

      while(result.next())
	{
         int id  = result.getInt("id");
         String kol1 = result.getString("kolumna1");
         String kol2 = result.getString("kolumna2");
	 String kol3 = result.getString("kolumna3");
	
	 System.out.print("ID: " +id);
	 System.out.print(",kolumna1:" + kol1);
	 System.out.print(",kolumna2:" + kol2);
	 System.out.print(",kolumna3:" + kol3);

	}
      result.close();
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
