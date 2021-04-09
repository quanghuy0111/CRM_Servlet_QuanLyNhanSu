package cybersoft.java09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cybersoft.java09.db.JDBCConnection;
import cybersoft.java09.entity.*;

public class StatusRepository {

	/*
	 * Hàm tìm kiếm thông tin của status trong database
	 * param id: id của status cần tìm kiếm
	 * return: trả về một status có id cần tìm
	 * Author: 
	 */
	public Status findStatusById(int id) {
		Status status = new Status();
		try {
			// tạo câu lệnh truy vấn
			String query = "SELECT * FROM status WHERE id=?";
			
			//Kết nôi với db
			Connection connection = JDBCConnection.getConnection();
			
			//Truyền câu lệnh truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			//Thực thi câu lệnh
			ResultSet result = statement.executeQuery();
			
			//Chuyển dữ liệu qua entity
			while(result.next()) {
				
				status.setName(result.getString("name"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return status;
	}
	
	/*
	 * Hàm lấy tất cả trạng thái
	 * return: trả về một list status
	 * Author: 
	 */
	public List<Status> getAllStatus() {
		List<Status> listStatus = new ArrayList<Status>();
		try {
			String query = "SELECT * FROM status";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Status status = new Status(result.getInt("id"), result.getString("name"));
				
				listStatus.add(status);
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listStatus;
	}
}
