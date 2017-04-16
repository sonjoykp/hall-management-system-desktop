package hallmanagementsystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Insertion {

  
    private Connection connection;

  


    public Insertion(Connection con) {
        connection = con;
    }

    public void insertStudentRecord(String std_id, String name, String res_status, String room_no, String mess_card_no, String term, String level, String phone_no, String dept, String library_id, String blood_group, String address) {
       

        if (room_no.equals("")) {
            room_no = "null";

        }

        if (mess_card_no.equals("")) {
            mess_card_no = "null";

        }

        if (library_id.equals("")) {
            library_id = "null";

        }

        if (address.equals("")) {
            address = "null";

        }

        if (std_id.equals("") || name.equals("") || phone_no.equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter at least * marked Fields.");

        } else {
            try {
                //Statement statement = connection.createStatement();
                Statement statement = connection.createStatement();

                String quaryforinsert = "INSERT INTO STUDENTS VALUES (" + std_id + "," + "'" + name + "'" + "," + "'" + res_status + "'" + "," + room_no + "," + mess_card_no + "," + level + "," + term + "," + "'" + phone_no + "'" + "," + "'" + dept + "'" + "," + library_id + "," + "'" + blood_group + "'" + "," + "'" + address + "'" + ")";

                statement.executeQuery(quaryforinsert);
                statement.executeQuery("COMMIT");
                JOptionPane.showMessageDialog(null, "your information has been inserted!!!");

            } catch (SQLException ex) {
                //Logger.getLogger(SupervisorFrame.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Data is already inserted");
            }

        }
    }
    public int monthCount(String mon)
    {
        int m;
        switch(mon.charAt(0))
        {
            case 'J':
                if(mon.equals("JANUARY"))
                   m=1;
                else if(mon.equals("JUNE"))
                   m=6;
                else
                   m=7;
                break;
            case 'F':
                m=2;
                break;
            case 'M':
                if(mon.equals("MARCH"))
                    m=3;
                else
                    m=5;
                break;
                    
            case 'A':
                if(mon.equals("APRIL"))
                    m=4;
                else
                   m=8;
                break;
                
            case 'S':
                m=9;
                break;
            case 'O':
                m=10;
                break;
            case 'N':
                m=11;
                break;
            default:
                m=12;
                break;
        }
        return m;
    }
    
    public void insertPayment(String std_id,String feeType,String ammount,String day, String month,String year,String scroll)
    {
        String date = day+"-"+month.substring(0, 3)+"-"+year;
        String query;
        if(ammount.equals(""))
              ammount = "null";
        if(feeType.equals("Select Fee Type"))
              feeType ="null";
        Integer mo = new Integer(monthCount(month));
        
        query="INSERT INTO PAYMENTS VALUES("+ day+mo.toString()+year+scroll +", "+ std_id +", '"+ feeType+ "', "+ammount+", '"+date+"' )";
        System.out.println(query);
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
                statement.executeQuery("COMMIT");
                JOptionPane.showMessageDialog(null, "Payment has been inserted!!!");
        } catch (SQLException ex) {
           // Logger.getLogger(Insertion.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, "Update Payment Query Exception: \n Std_id may not be in students Table or duplicalte primary  key");
        }
    }

}
