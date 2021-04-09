package cybersoft.java09.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cybersoft.java09.dto.*;


import cybersoft.java09.db.JDBCConnection;
import cybersoft.java09.entity.Task;
import cybersoft.java09.entity.User;

public class UserRepository {
	/*
	 * Hàm lấy tất cả user trong database users
	 * return: List user đã được lấy trong database
	 * Author: 
	 */
	public List<User> getAlluser(){ // Truy vấn các cột trong bảng roles và add vào một ArrayList
		List<User> users = new ArrayList<User>();
		try {
			//Câu truy vấn chọn tất cả thuộc tính từ users
			String query = "SELECT * FROM users";
			
			//Tạo connection
			Connection connection = JDBCConnection.getConnection();
			
			//Truyền câu truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);

			//Thực hiện lấy database trong users
			ResultSet result = statement.executeQuery();
			
			//Dùng hàm while để lọc tất cả các trường hợp
			while(result.next()) {
				User user = new User(result.getInt("id"),result.getString("email"),
						result.getString("password"),
						result.getString("fullname"),
						result.getString("avatar"),
						result.getInt("role_id"));
				//Sau khi lấy được các thuộc tính trong database ra thì thêm vào list
				users.add(user);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//Trả về list user
		return users;
	}

	/*
	 * Hàm Truy vấn userDTO để hiển thị thông tin trong đó có role name ứng với roleID
	 * return: List usersDto có chứa thông tin role dựa theo id_role
	 * Author: 
	 */
	public List<UserDto> findAllUserRole() { //Truy vấn userDTO để hiển thị thông tin trong đó có role name ứng với roleID
		List<UserDto> users = new ArrayList<UserDto>();
		try {
			
			// B1: Kết nối db
			Connection conn = JDBCConnection.getConnection();
			
			
			// B2: Tạo câu lệnh truy vấn
			String query = "SELECT u.id, u.email, u.password, u.fullname, u.avatar, r.name FROM users u JOIN roles r ON u.role_id = r.id";
			PreparedStatement statement = conn.prepareStatement(query);
			
			// B3: Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			
			// B4: Chuyển dữ liệu qua entity (java class)
			while (resultSet.next()) {
				// Dùng hàm khởi tạo có tham số
				UserDto userDto = new UserDto(resultSet.getInt("id"), resultSet.getString("email"),
						resultSet.getString("password"), resultSet.getString("fullname"), resultSet.getString("avatar"),
						resultSet.getString("name"));
				users.add(userDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	/*
	 * Hàm update user trong database
	 * param user: chứa các thuộc tính của user cần sửa
	 * param id : Id của user dùng để tìm kiếm user theo id
	 * return: table users đã được update theo id 
	 * Author: 
	 */
	public void editUser(User user,int id) {
		try {
			//Tạo câu lệnh truy vấn
			String query = "UPDATE users SET email = ? , password = ? , fullname = ? , avatar = ? , role_id = ? WHERE id=?";
			
			//Tạo connection
			Connection connection = JDBCConnection.getConnection();
			
			//Truyền câu truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassWord());
			statement.setString(3, user.getFullName());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRole_Id());
			statement.setInt(6, id);
			
			//Thực thi câu truy vấn
			int result = statement.executeUpdate();

			//Kiểm tra có thành công hay không
			if(result < 1) {
				System.out.println("Thêm Thất bại");
			}
			else {
				System.out.println("Thêm Thành Công");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * Hàm thêm một user mới vào database của users
	 * param user: chứa các thuộc tính của user mới được truyền vào
	 * return: table users mới có chứa user vừa được thêm
	 * Author: 
	 */
	public void addUser(User user) { //Thêm mới một user vào database
		try {
			//Tạo câu lệnh truy vấn
			String query = "INSERT INTO users (email,password,fullname,avatar,role_id) VALUES(?,?,?,?,?)";
			
			//Tạo connection
			Connection connection = JDBCConnection.getConnection();
			
			//Truyền câu truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassWord());
			statement.setString(3, user.getFullName());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRole_Id());

			//Thực thi
			int result = statement.executeUpdate();

			//Trả về kết quả có thành công hay không
			if(result < 1) {
				System.out.println("Thêm Thất bại");
			}
			else {
				System.out.println("Thêm Thành Công");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Hàm xóa 1 user trong database
	 * param id: id của user cần xóa
	 * return: table users sau khi xóa 1 user theo id 
	 * Author: 
	 */
	public void deleteUser(int id) {
		try {
			
			//Tạo câu lệnh truy vấn
			String query = "DELETE FROM users where id = ?";
			
			//Tạo connection
			Connection conn = JDBCConnection.getConnection();
			
			//Truyền câu truy vấn vào connection
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setInt(1, id);
			
			//thực thi
			int result = statement.executeUpdate();
			
			//in ra kết quả
			if(result < 1) {
				System.out.println("Xóa Thất bại");
			}
			else {
				System.out.println("Xóa Thành Công");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Hàm tìm kiếm thông tin của user trong database
	 * param id: id của user cần tìm kiếm
	 * return: trả về một user có id cần tìm
	 * Author: 
	 */
	public User findById(int id) {
		// B1: Kết nối db
		User user = new User();
		try {
			Connection conn = JDBCConnection.getConnection();

			// B2: Tạo câu lệnh truy vấn
			PreparedStatement statement = 
					conn.prepareStatement("SELECT * FROM users WHERE id = ?");
			statement.setInt(1, id);
			// B3: Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			// B4: Chuyển dữ liệu qua entity (java class)

			while (resultSet.next()) {
				// Dùng hàm khởi tạo có tham số
				user = new User(
						resultSet.getInt("id"), 
						resultSet.getString("email"), 
						resultSet.getString("password"),
						resultSet.getString("fullname"), 
						resultSet.getString("avatar"), 
						resultSet.getInt("role_id"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/*
	 * Hàm tìm kiếm thông tin task của user
	 * param userID: id của user cần tìm kiếm
	 * param statusID: trạng thái của task cần tìm kiếm
	 * return: trả về danh sách task của id user và trạng thái cần tìm
	 * Author: 
	 */
	public List<Task> findTaskOfUser(int userID, int statusID){
		List<Task> listTaskUser = new ArrayList<Task>();
		try {
			//Tạo câu truy vấn chọn tên task, ngày bắt đầu và ngày kết thúc của task 
			String query = "select t.name,t.start_date,t.end_date from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id where u.id = ? AND s.id= ?";
			
			//Kết nối db
			Connection connection = JDBCConnection.getConnection();
			
			//truyền câu truy vấn
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1,userID);
			
			if(statusID == 1)
			{
				statement.setInt(2, 1);
			}
			else if (statusID == 2)
			{
				statement.setInt(2, 2);
			}
			else {
				statement.setInt(2, 3);
			}
			
			//thực thi truy vấn
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Task task = new Task();
				task.setName(result.getString("name"));
				
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");
				
				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());
				
				
				task.setStartDate(dateStart);
				task.setEndDate(endDate);
				

				listTaskUser.add(task);

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return listTaskUser;
	}
	
	/*
	 * Hàm kiểm tra đăng nhập của người dùng
	 * param email: email của user cần 
	 * param password: mật khẩu của user 
	 * return: trả về user có email và pass cần tìm, nếu không thì null
	 * Author: 
	 */
	public User checkLogin(String email, String password) {
		// B1: Kết nối db
		User user = new User();
		try {
			Connection conn = JDBCConnection.getConnection();

			// B2: Tạo câu lệnh truy vấn
			PreparedStatement statement = 
					conn.prepareStatement("SELECT * FROM users WHERE email = ? and password = ?");
			statement.setString(1, email);
			statement.setString(2, password);
			// B3: Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			// B4: Chuyển dữ liệu qua entity (java class)

			while (resultSet.next()) {
				// Dùng hàm khởi tạo có tham số
				user = new User(
						resultSet.getInt("id"), 
						resultSet.getString("email"), 
						resultSet.getString("password"),
						resultSet.getString("fullname"), 
						resultSet.getString("avatar"), 
						resultSet.getInt("role_id"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/*
	 * Hàm tìm kiếm thông tin task của user
	 * param userID: id của user cần tìm kiếm
	 * return: trả về danh sách task của user cần tìm
	 * Author: 
	 */
	public List<TaskDto> findTaskByUserID(int userID){
		List<TaskDto> listTaskDto = new ArrayList<TaskDto>();
		try {
			
			String query = "select t.id,t.name,t.start_date,t.end_date,s.name as status_name from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id where u.id = ?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1,userID);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				TaskDto taskDto = new TaskDto();
				taskDto.setId(result.getInt("id"));
				taskDto.setName(result.getString("name"));
				
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");
				
				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());
				
				
				taskDto.setStartDate(dateStart);
				taskDto.setEndDate(endDate);
				taskDto.setStatus(result.getString("status_name"));
				
				listTaskDto.add(taskDto);

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return listTaskDto;
	}
	
	
	
	
}
