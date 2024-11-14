package mysqlapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDBConnection {
    private Connection myConnection;

    public MyDBConnection(){}

    public void init(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConnection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
   
    public Connection getMyConnection(){
        return myConnection;
    }

    public void close(ResultSet rs){
        if(rs !=null){
            try{
                rs.close();
            }
            catch(SQLException e){}
        }
    }

    public void close(java.sql.Statement stmt){
        if(stmt !=null){
            try{
                stmt.close();
            }
            catch(SQLException e){}
        }
    }

    public void destroy(){
        if(myConnection !=null){
            try{
                myConnection.close();
            }
            catch(SQLException e){}
        }
    }   
}

