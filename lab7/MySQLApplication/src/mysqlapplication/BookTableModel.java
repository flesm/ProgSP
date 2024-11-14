package mysqlapplication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class BookTableModel extends AbstractTableModel{
    
    private int colnum=6;
    private int rownum;

    private String[] colNames = {"ID", "Author","Name","Year", "Cost", "IsNew"};

    private  ArrayList<String[]> ResultSets;

    public BookTableModel(ResultSet rs){
        ResultSets=new ArrayList<>();  
        try{
            while(rs.next()){
                String[] row = {
                 rs.getString("ID"), rs.getString("Author"),
                 rs.getString("Name"), rs.getString("Year"),
                 rs.getString("Cost"), rs.getString("IsNew"),
                };
                ResultSets.add(row);
            }   
        }
        catch(SQLException e){
            System.out.println("Exception in BookTableModel");
        }
    }



    @Override
    public String getColumnName(int param) {
        return colNames[param];
    }

    @Override
    public int getRowCount() {
        return ResultSets.size();
    }

    @Override
    public Object getValueAt(int rowindex, int columnindex) {
        String[] row = ResultSets.get(rowindex);
        return row[columnindex];
    }
    
    @Override
    public int getColumnCount() {
        return colnum;
    }  
}
