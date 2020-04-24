import static org.junit.Assert.*;

import org.junit.Test;

import UserLogin.UserLoginDBMethods;
import UserLogin.UserLoginDBObj;

public class UserLoginTest {

	@Test
	public void testGetRecordByPrimaryKey() {
		//System.out.println("Success");
		UserLoginDBMethods obj = new UserLoginDBMethods("root", "malik369", "jdbc:mysql://localhost:3306/yearbook");
		String userId = "001";
		String userName = "chirag";
		String pass = "chirag1";
		UserLoginDBObj obj2 = (UserLoginDBObj)obj.getRecordByPrimaryKey(userId, userName, pass);
		assertEquals(userName, obj2.user_name);
	}

}
