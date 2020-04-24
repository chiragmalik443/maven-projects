package Faculty;
import java.sql.*;
import javax.sql.*;
public class GenerateId
{
	public int generateFacultyId(){
	int fac_id=0;
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/yearbook","root","malik369");
		Statement stmt = conn.createStatement();
		String query="select max(fac_id) as fac_id from PEOPLE_FACULTY ";
		ResultSet rs = null;
		rs = stmt.executeQuery(query);
		if(rs.next()){
			String id = rs.getString("fac_id");
			fac_id=Integer.parseInt(id);
		}
		fac_id = fac_id + 1;		
	    }
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	return fac_id;	
	}
}