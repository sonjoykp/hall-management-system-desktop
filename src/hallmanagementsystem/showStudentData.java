/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hallmanagementsystem;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javafx.scene.control.Tab;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author sonjoy
 */
public class showStudentData {
    /*private String action;
    private String std_id;
    private String name;
    private String res_status;
    private String dept;
    private String blood_group;
    private String address;
    private String room_no;
    private String mess_card_no;
    private String term;
    private String level;
    private String library_id;
    private String phone_no;*/
    
    private Connection connection;
    
    private ResultSet rs;
    
    private String query;
    
    private Vector<String> allcolumnNames = new Vector<String>();
    private Vector<String> rescolumnNames = new Vector<String>();
    private Vector<String> attatchcolumnNames = new Vector<String>();
    public  showStudentData(Connection con)
    {
        connection= con;
        
        allcolumnNames.add("Student ID");
        allcolumnNames.add("Name");
        allcolumnNames.add("Resident Status");
        allcolumnNames.add("Room No");
        allcolumnNames.add("Mess Card");
        allcolumnNames.add("Level");
        allcolumnNames.add("Term");
        allcolumnNames.add("Phone");
        allcolumnNames.add("Dept");
        allcolumnNames.add("Hall Library Card");
        allcolumnNames.add("Blood Group");
        allcolumnNames.add("Permanent Address");
        
        attatchcolumnNames.add("Student ID");
        attatchcolumnNames.add("Name");
        //columnNames.add("Resident Status");
        //columnNames.add("Room No");
        //columnNames.add("Mess Card");
        attatchcolumnNames.add("Level");
        attatchcolumnNames.add("Term");
        attatchcolumnNames.add("Phone");
        attatchcolumnNames.add("Dept");
        //columnNames.add("Hall Library Card");
        attatchcolumnNames.add("Blood Group");
        attatchcolumnNames.add("Permanent Address");
        
        rescolumnNames.add("Student ID");
        rescolumnNames.add("Name");
        //rescolumnNames.add("Resident Status");
        rescolumnNames.add("Room No");
        rescolumnNames.add("Mess Card");
        rescolumnNames.add("Level");
        rescolumnNames.add("Term");
        rescolumnNames.add("Phone");
        rescolumnNames.add("Dept");
        rescolumnNames.add("Hall Library Card");
        rescolumnNames.add("Blood Group");
        rescolumnNames.add("Permanent Address");
    }
    public DefaultTableModel buildTableModel(ResultSet rs,int tableType)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

// names of columns
       // Vector<String> columnNames = new Vector<String>();
        //new Vector<String>()
        int columnCount = metaData.getColumnCount();
        /*for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }*/
        

// data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        DefaultTableModel tabModel= new DefaultTableModel(); 
        if(tableType==1)//all or specific
                tabModel = new DefaultTableModel(data, allcolumnNames);
        else if(tableType==2)//resident
                tabModel = new DefaultTableModel(data, rescolumnNames);
        else if(tableType==3)//attatched
                tabModel = new DefaultTableModel(data, attatchcolumnNames);
        return tabModel;

    }
    public TableModel showAllStudents() throws SQLException
    {
        query = "select * from STUDENTS";
        
        Statement statement = connection.createStatement();
                rs = statement.executeQuery(query);
        return buildTableModel(rs, 1);
                
    }
     public TableModel specificStudent(String stdId) throws SQLException
    {
        query = "select * from STUDENTS where student_id = "+stdId;
        
        Statement statement = connection.createStatement();
                rs = statement.executeQuery(query);
        return buildTableModel(rs, 1);
    }
    public TableModel showResidentStudents() throws SQLException
    {
        query = "select STUDENT_ID,NAME,ROOM_NO,MESS_CARD_NO,L,T,PHONE_NO,DEPT,LIBRARY_ID,BLOOD_GROUP,ADDRESS from STUDENTS where RES_status= 'Resident'";
        
        Statement statement = connection.createStatement();
                rs = statement.executeQuery(query);
        return buildTableModel(rs, 2);
    }
     public TableModel showAttachedStudents() throws SQLException
    {
        query = "select STUDENT_ID,NAME,L,T,PHONE_NO,DEPT,BLOOD_GROUP,ADDRESS from STUDENTS where res_status= 'Attatched'";
        
        Statement statement = connection.createStatement();
                rs = statement.executeQuery(query);
        return buildTableModel(rs, 3);
    }
    
}
