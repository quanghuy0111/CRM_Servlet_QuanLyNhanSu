package cybersoft.java09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cybersoft.java09.db.JDBCConnection;
import cybersoft.java09.dto.JobDto;
import cybersoft.java09.entity.Job;
import cybersoft.java09.entity.Role;
import cybersoft.java09.entity.User;

public class JobRepository {
	/*
	 * Hàm lấy tất cả job trong database jobs
	 * return: List job đã được lấy trong database
	 * Author: 
	 */
	public List<Job> getAllJob(){ 
		List<Job> jobs = new ArrayList<Job>();
		try {
			//Câu truy vấn
			String query = "SELECT * FROM jobs";
			
			//Tạo connection
			Connection connection = JDBCConnection.getConnection();
			
			//Truyền câu truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);

			//Thực hiện lấy database
			ResultSet result = statement.executeQuery();
			
			//Lọc tất cả trường hợp
			while(result.next()) {
				Job job = new Job();
				
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");
				
				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());
				
				
				job.setStartDate(dateStart);
				job.setEndDate(endDate);
				
				
				jobs.add(job);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return jobs;
	}

	/*
	 * Hàm thêm một job mới vào database của jobs
	 * param job: chứa các thuộc tính của job mới được truyền vào
	 * return: table jobs mới có chứa job vừa được thêm
	 * Author: 
	 */
	public void addNewJob(Job job) {
		try {
			//Tạo câu lệnh truy vấn
			String query = "INSERT INTO jobs (name,start_date,end_date) VALUES(?,?,?)";
			
			//Kết nối với db
			Connection connection = JDBCConnection.getConnection();
			
			//Truyền câu lệnh truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, job.getName());
			statement.setTimestamp(2, new Timestamp(job.getStartDate().getTime()));
			statement.setTimestamp(3, new Timestamp(job.getEndDate().getTime()));
			
			//Thực thi câu lệnh truy vấn
			int result = statement.executeUpdate();

			//Kiểm tra kết quả
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
	 * Hàm tìm kiếm thông tin của job trong database
	 * param id: id của job cần tìm kiếm
	 * return: trả về một job có id cần tìm
	 * Author: 
	 */
	public Job findJobById(int id) {
		Job job = new Job();
		try {
			//Tạo câu lệnh truy vấn 
			String query = "SELECT * FROM jobs WHERE id=?";
			
			//kết nối với db
			Connection connection = JDBCConnection.getConnection();
			
			//truyền câu truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			//Thực thi câu lệnh truy vấn
			ResultSet result = statement.executeQuery();
			
			//chuyển dữ liệu qua entity
			while(result.next()) {

				
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");
				
				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());
				
				job.setStartDate(dateStart);
				job.setEndDate(endDate);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return job;
	}
	
	/*
	 * Hàm update job trong database
	 * param job: chứa các thuộc tính của job cần sửa
	 * param id : Id của job dùng để tìm kiếm job theo id
	 * return: table jobs đã được update theo id 
	 * Author: 
	 */
	public void editJob(Job job,int id) {
		try {
			//tạo câu lệnh truy vấn
			String query = "UPDATE jobs SET name=?, start_date=?, end_date=? where id = ?";
			
			//kết nối db
			Connection connection = JDBCConnection.getConnection();
			
			//truyền câu lệnh truy vấn
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, job.getName());
			statement.setTimestamp(2, new Timestamp(job.getStartDate().getTime()));
			statement.setTimestamp(3, new Timestamp(job.getEndDate().getTime()));
			statement.setInt(4, id);

			//thực thi câu lệnh truy vấn
			int result = statement.executeUpdate();

			//kiểm tra có thành công hay không
			if(result < 1) {
				System.out.println("Edit Thất bại");
			}
			else {
				System.out.println("Edit Thành Công");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/*
	 * Hàm xóa 1 job trong database
	 * param id: id của job cần xóa
	 * return: table jobs sau khi xóa 1 job theo id 
	 * Author: 
	 */
	public void deleteJob(int id) {
		try {
			//Tạo câu lệnh truy vấn
			String query = "DELETE FROM jobs where id = ?";
			
			//tạo connection
			Connection conn = JDBCConnection.getConnection();
			
			//truyền câu truy vấn vào connection
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			
			//Thực thi câu lệnh
			int result = statement.executeUpdate();
			

			//kiểm tra kết quả
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
	 * Hàm tìm kiếm thông tin của user trong database dựa vào id của job, status
	 * param id: id của job cần tìm kiếm
	 * return: trả về một user có id job cần tìm
	 * Author: 
	 */
	public List<User> findUsersByJobID(int id) { // Lấy list user thuộc dự án
		List<User> users = new ArrayList<User>();
		try {
			//Câu truy vấn lấy ra id và fullname của user từ job và status của task
			String query = "select u.id,u.fullname from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id join jobs j on t.job_id=j.id where j.id = ?"  ; 
					
			//tạo connection
			Connection connection = JDBCConnection.getConnection();
			
			//Truyền câu truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			//thực thi câu lệnh truy vấn
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				User user = new User();
				
				user.setId(result.getInt("id"));
				user.setFullName(result.getString("fullname"));

				users.add(user);

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return users;
	}
	
	/*
	 * Hàm lấy hiển thị thông tin job
	 * param id: id của job cần tìm kiếm
	 * return: trả về list job có id job cần tìm
	 * Author: 
	 */
	public List<JobDto> getUserOfListDto(int id){ //Lấy thuộc tính User trong JobDto
		List<JobDto> listJobDto = new ArrayList<JobDto>();
		try {
			
			String query = "select u.id,u.fullname from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id join jobs j on t.job_id=j.id where j.id = ?"  ; 
					
			Connection connection = JDBCConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				User user = new User();
				
				user.setId(result.getInt("id"));
				user.setFullName(result.getString("fullname"));
				
				JobDto jobDto=new JobDto();
				jobDto.setUser(user);;

				listJobDto.add(jobDto);

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listJobDto;
	}
	
	/*
	 * Hàm tìm job thuộc user
	 * param id: id của user cần tìm kiếm
	 * return: trả về list job có id user cần tìm
	 * Author: 
	 */
	public Job findJobByUserID(int id) { //Tìm dự án thuộc user
		Job job = new Job();
		try {
			
			String query = "select j.id,j.name from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id join jobs j on t.job_id=j.id where u.id = ?"  ; 
					
			Connection connection = JDBCConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return job;
	}
	
}
