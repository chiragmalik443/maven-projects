package Student;
import java.sql.*;
import javax.sql.*;
public class GenerateId
{
	public int generateStudentId(){
	int stu_id=0;
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/yearbook","root","malik369");
		Statement stmt = conn.createStatement();
		String query="select max(stu_id) as stu_id from PEOPLE_STUDENT ";
		ResultSet rs = null;
		rs = stmt.executeQuery(query);
		if(rs.next()){
			String id = rs.getString("stu_id");
			stu_id=Integer.parseInt(id);
		}
		stu_id =stu_id + 1;		
	    }
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	return stu_id;	
	}
}