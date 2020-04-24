package Employee;
import java.sql.*;
import javax.sql.*;
public class GenerateId
{
	public int generateEmployeeId(){
	int emp_id=0;
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/hr_management","root","root@123");
		Statement stmt = conn.createStatement();
		String query="select max(emp_id) as emp_id from PEOPLE_EMPLOYEE ";
		ResultSet rs = null;
		rs = stmt.executeQuery(query);
		if(rs.next()){
			String id = rs.getString("emp_id");
			emp_id=Integer.parseInt(id);
		}
		emp_id = emp_id + 1;		
	    }
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	return emp_id;	
	}
}