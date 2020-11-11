import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DockerConnectMySQL {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://Full202086369:3306/MMiszczak";

   static final String USER = "user";
   static final String PASS = "pass";
   static Scanner in = new Scanner( System.in);
   
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   
   try{
      Class.forName("com.mysql.jdbc.Driver");
      TimeUnit.SECONDS.sleep(10);
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      stmt = conn.createStatement();
      String sql;
      sql = "CREATE TABLE IF NOT EXISTS Student (IDStudent int, FirstName varchar(255), LastName varchar(255), Phone varchar(255));";
      int uP = stmt.executeUpdate(sql);
      String optNr, idr, firstName, lastName, phone;
      int id; 
      
      do
      {
        System.out.println("1. Select *\n2. Insert\n3. Edit\n4. Delete\nS. Press E to Exit");
        optNr = in.nextLine();
        
        switch( optNr )
        {
            case "1" : sql = "SELECT IDStudent, FirstName, LastName, Phone FROM Student";
                       ResultSet rs = stmt.executeQuery(sql);
                                           System.out.println("ID    FirstName    LastName    Phone   ");
 
                       while(rs.next()){
                          id  = rs.getInt("IDStudent");
                          firstName = rs.getString("FirstName");
                          lastName = rs.getString("LastName");
                          phone = rs.getString("Phone");
 
                          System.out.println(id+"    "+firstName+"    "+lastName+"    "+phone);
 
                      
                       } rs.close();
                       break;
              case "2" :  
                        System.out.println("IDStudent");
                          idr = in.nextLine();
 
                        System.out.println("FirstName:");
                          firstName = in.nextLine();
 
                        System.out.println("LastName");
                          lastName = in.nextLine();
 
                        System.out.println("Phone:");
                          phone = in.nextLine();
                          
                        sql = " INSERT INTO Student (IDStudent, FirstName, LastName, Phone) VALUES ('"+idr+"', '"+firstName+"', '"+lastName+"', '"+phone+"')";
                        System.out.println("sql:"+sql);
                        int ri = stmt.executeUpdate(sql);
                     break;
                
                case "3" : 
                       sql = "SELECT IDStudent, FirstName, LastName, Phone FROM Student";
                       ResultSet rss = stmt.executeQuery(sql);
                                           System.out.println("ID    FirstName    LastName    Phone   ");
 
                       while(rss.next()){
                          id  = rss.getInt("IDStudent");
                          firstName = rss.getString("FirstName");
                          lastName = rss.getString("LastName");
                          phone = rss.getString("Phone");
 
                          System.out.println(id+"    "+firstName+"    "+lastName+"    "+phone);
 
                       
                       }
                        rss.close();
                        System.out.println("Enter ID to edit");
                          idr = in.nextLine();
 
                        System.out.println("FirstName");
                          firstName = in.nextLine();
 
                        System.out.println("LastName");
                          lastName = in.nextLine();
 
                        System.out.println("Phone:");
                          phone = in.nextLine();
 
                        sql = " UPDATE Student SET FirstName = '"+firstName+"' , LastName = '"+lastName+"', Phone = '"+phone+"' WHERE IDStudent= '"+idr+"';";
                        System.out.println("sql:"+sql);
                        int rii = stmt.executeUpdate(sql);
                     break;
                     
                case "4" : 
                       sql = "SELECT IDStudent, FirstName, LastName, Phone FROM Student";
                       ResultSet rsss = stmt.executeQuery(sql);
                                           System.out.println("ID    FirstName    LastName    Phone");
 
                       while(rsss.next()){
                          id  = rsss.getInt("IDStudent");
                          firstName = rsss.getString("FirstName");
                          lastName = rsss.getString("LastName");
                          phone = rsss.getString("Phone");
                          
                          System.out.println(id+"    "+firstName+"    "+lastName+"    "+phone);
 
                       }
                       rsss.close();
                        System.out.println("Enter ID to delete");
                          idr = in.nextLine();
                        sql = " DELETE FROM Student WHERE IDStudent= '"+idr+"';";
                        System.out.println("sql:"+sql);
                        int riii = stmt.executeUpdate(sql);
                     break;
        }
      }while (!optNr.equals("E"));   
      
      stmt.close();
      conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
 }
}
