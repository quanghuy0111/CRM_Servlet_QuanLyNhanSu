package cybersoft.java09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cybersoft.java09.db.JDBCConnection;
import cybersoft.java09.entity.Role;

public class RoleRepository {

	/*
	 * Hàm lấy tất cả role trong database roles
	 * return: List role đã được lấy trong database
	 * Author: 
	 */
	public List<Role> getAllRole(){ // Truy vấn các cột trong bảng roles và add vào một ArrayList
		List<Role> roles = new ArrayList<Role>();
		try {
			//Câu truy vấn chọn tất cả thuộc tính của roles
			String query = "SELECT * FROM roles";
			
			//Tạo connection
			Connection connection = JDBCConnection.getConnection();
			
			//Truyền câu truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);
			
			//Thực thi
			ResultSet result = statement.executeQuery();
			
			//Dùng hàm while để lọc tất cả các trường hợp
			while(result.next()) {
				Role role = new Role();
				role.setId(result.getInt("id"));
				role.setName(result.getString("name"));
				role.setDescription(result.getString("description"));
				
				roles.add(role);
			}
			
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return roles;
	}
	
	/*
	 * Hàm thêm một role mới vào database của roles
	 * param role: chứa các thuộc tính của role mới được truyền vào
	 * return: table roles mới có chứa role vừa được thêm
	 * Author: 
	 */
	public void addNewRole(Role role) {
		try {
			//Tạo câu lệnh truy vấn
			String query = "INSERT INTO roles (name,description) VALUE (?,?)";
			
			//Tạo connection
			Connection connection = JDBCConnection.getConnection();
			
			//Truyền câu truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			
			//Thực thi câu truy vấn
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
	 * Hàm tìm kiếm thông tin của role trong database
	 * param id: id của role cần tìm kiếm
	 * return: trả về một role có id cần tìm
	 * Author: 
	 */
	public Role findRoleById(int id) {
		Role role = new Role();
		try {
			//tạo câu lệnh truy vấn
			String query = "SELECT * FROM roles WHERE id=?";
			
			//Kết nối database
			Connection connection = JDBCConnection.getConnection();
			
			//truyền câu lệnh truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			//Thực thi
			ResultSet result = statement.executeQuery();
			
			//Chuyển dữ liệu qua entity
			while(result.next()) {
				role.setId(Integer.parseInt(result.getString("id")));
				role.setName(result.getString("name"));
				role.setDescription(result.getString("description"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return role;
	}

	/*
	 * Hàm update role trong database
	 * param role_edit: chứa các thuộc tính của role cần sửa
	 * param id : Id của role dùng để tìm kiếm role theo id
	 * return: table roles đã được update theo id 
	 * Author: 
	 */
	public void editRole(Role role_edit, int id) {
		try {
			//Tạo câu lệnh truy vấn
			String query = "UPDATE roles SET name = ? , description = ? WHERE id=?";
			
			//Tạo connection
			Connection connection = JDBCConnection.getConnection();
			
			//Truyền câu truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, role_edit.getName());
			statement.setString(2, role_edit.getDescription());
			statement.setInt(3, id);
			
			//Thực thi câu lệnh
			int result = statement.executeUpdate();

			//Kiểm tra kết quả
			if(result < 1) {
				System.out.println("Edit Thất bại");
			}
			else {
				System.out.println("Edit Thành Công");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+"Hello");
		}
		
	}
	
	/*
	 * Hàm xóa 1 role trong database
	 * param id: id của role cần xóa
	 * return: table roles sau khi xóa 1 role theo id 
	 * Author: 
	 */
	public void deleteRole(int id) {
		try {
			//Tạo câu lệnh truy vấn
			String query = "DELETE FROM roles where id = ?";
			
			//Tạo connection
			Connection conn = JDBCConnection.getConnection();
			
			//Truyền câu truy vấn vào connection
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			
			//Thực thi 
			int result = statement.executeUpdate();
			
			//kiểm tra kết qủa
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
	
	
}
