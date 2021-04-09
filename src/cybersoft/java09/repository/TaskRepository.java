package cybersoft.java09.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cybersoft.java09.db.JDBCConnection;
import cybersoft.java09.dto.TaskDto;
import cybersoft.java09.entity.Job;
import cybersoft.java09.entity.Task;

public class TaskRepository {
	private UserRepository userRepository = new UserRepository();
	private JobRepository jobRepository = new JobRepository();
	private StatusRepository statusRepository = new StatusRepository();

	/*
	 * Hàm lấy tất cả task trong database tasks
	 * return: List task đã được lấy trong database
	 * Author: 
	 */
	public List<TaskDto> getAllTask(){ 
		List<TaskDto>  taskDtos = new ArrayList<TaskDto>();
		try {
			//Câu truy vấn chọn tất cả thuộc tính của task
			String query = "SELECT * FROM tasks";

			//kết nối db
			Connection connection = JDBCConnection.getConnection();

			//Truyền câu truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);

			//Thực thi
			ResultSet result = statement.executeQuery();


			while(result.next()) {
				TaskDto taskDto = new TaskDto();

				taskDto.setId(result.getInt("id"));
				taskDto.setName(result.getString("name"));

				//để lấy được ngày tháng đầu tiên phải lấy timestamp
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");

				//Sau đó chuyển đổi timestamp thành date
				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());


				taskDto.setStartDate(dateStart);
				taskDto.setEndDate(endDate);

				//tên job
				taskDto.setJob(jobRepository.findJobById(result.getInt("job_id")).getName());
				//tên người làm task
				taskDto.setUser(userRepository.findById(result.getInt("user_id")).getFullName());
				//Trạng thái
				taskDto.setStatus(statusRepository.findStatusById(result.getInt("status_id")).getName());

				taskDtos.add(taskDto);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return taskDtos;
	}

	/*
	 * Hàm tìm kiếm thông tin của task trong database
	 * param id: id của task cần tìm kiếm
	 * return: trả về một task có id cần tìm
	 * Author: 
	 */
	public Task findTaskById(int id) {
		Task task = new Task();
		try {

			//Câu truy vấn
			String query = "SELECT * FROM tasks WHERE id=?";

			//Kết nối db
			Connection connection = JDBCConnection.getConnection();

			//Truyền câu truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, id);

			//Thực thi
			ResultSet result = statement.executeQuery();


			while(result.next()) {


				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");

				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());


				task.setStartDate(dateStart);
				task.setEndDate(endDate);

				task.setUserID(result.getInt("user_id"));
				task.setJobID(result.getInt("job_id"));
				task.setStatusID(result.getInt("status_id"));

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return task;
	}

	/*
	 * Hàm thêm một task mới vào database của tasks
	 * param task: chứa các thuộc tính của task mới được truyền vào
	 * return: table tasks mới có chứa task vừa được thêm
	 * Author: 
	 */
	public void addNewTask(Task task) {
		try {

			//tạo câu lệnh truy vấn
			String query = "INSERT INTO tasks (name,start_date,end_date,user_id,job_id,status_id) VALUES(?,?,?,?,?,?)";

			//kết nối db
			Connection connection = JDBCConnection.getConnection();

			//truyền câu lệnh truy vấn vào connection
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, task.getName());
			statement.setTimestamp(2, new Timestamp(task.getStartDate().getTime()));
			statement.setTimestamp(3, new Timestamp(task.getEndDate().getTime()));
			statement.setInt(4, task.getUserID());
			statement.setInt(5, task.getJobID());
			statement.setInt(6, task.getStatusID());

			//Thực thi câu lệnh
			int result = statement.executeUpdate();

			//kiểm tra kết quả
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
	 * Hàm update task trong database
	 * param task: chứa các thuộc tính của task cần sửa
	 * param id : Id của user dùng để tìm kiếm task theo id
	 * return: table tasks đã được update theo id 
	 * Author: 
	 */
	public void editTask(Task task,int id) {
		try {
			//Tạo câu lệnh truy vấn
			String query = "UPDATE tasks SET name=?, start_date= ?, end_date= ? ,user_id = ?, job_id = ? , status_id = ? where id = ?";

			//kết nối db
			Connection connection = JDBCConnection.getConnection();

			//Truyền vào connection
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, task.getName());
			statement.setTimestamp(2, new Timestamp(task.getStartDate().getTime()));
			statement.setTimestamp(3, new Timestamp(task.getEndDate().getTime()));
			statement.setInt(4, task.getUserID());
			statement.setInt(5, task.getJobID());
			statement.setInt(6, task.getStatusID());
			statement.setInt(7, id);

			//Thực thi
			int result = statement.executeUpdate();

			//Kiểm tra kết quả
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
	 * Hàm xóa 1 task trong database
	 * param id: id của task cần xóa
	 * return: table tasks sau khi xóa 1 task theo id 
	 * Author: 
	 */
	public void deleteTask(int id) {
		try {
			//Tạo câu truy vấn
			String query = "DELETE FROM tasks where id = ?";

			//Kết nối db
			Connection conn = JDBCConnection.getConnection();

			//Truyền câu truy vấn 
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);

			//thực thi
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
	 * Hàm lấy ra list tasks dựa vào id của user
	 * param id: id của user cần lấy
	 * return: list tasks của user 
	 * Author: 
	 */
	public List<TaskDto> getTaskByUserID(int user_id){ 
		List<TaskDto>  taskDtos = new ArrayList<TaskDto>();
		try {
			//Tạo câu truy vấn
			String query = "SELECT * FROM tasks where user_id = ?";

			//kết nối db
			Connection connection = JDBCConnection.getConnection();

			//Truyền câu truy vấn
			PreparedStatement statement = connection.prepareStatement(query);


			statement.setInt(1, user_id);

			//Thực thi câu truy vấn
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

				taskDto.setJob(jobRepository.findJobById(result.getInt("job_id")).getName());
				taskDto.setUser(userRepository.findById(result.getInt("user_id")).getFullName());
				taskDto.setStatus(statusRepository.findStatusById(result.getInt("status_id")).getName());

				taskDtos.add(taskDto);

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return taskDtos;
	}

	/*
	 * Hàm thay đổi trạng thái tasks 
	 * param status_id: trạng thái của task
	 * param id: id của task
	 * param user_id: id của user
	 * return: trả về tast đã sửa đổi trạng thái 
	 * Author: 
	 */
	public void editTaskStatus(int status_id,int id,int user_id) {
		try {

			String query = "UPDATE tasks SET status_id = ? where id = ? and user_id = ?";


			Connection connection = JDBCConnection.getConnection();


			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, status_id);
			statement.setInt(2, id);
			statement.setInt(3, user_id);


			int result = statement.executeUpdate();


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
	 * Hàm lấy ra task dựa vào id của user và id của task
	 * param user_id: id của user cần lấy
	 * param task_id: id của task cần lấy
	 * return: tasks có id user và task user cần lấy 
	 * Author: 
	 */
	public TaskDto getTaskByUserIDAndTaskID(int user_id,int task_id){ 
		TaskDto taskDto = new TaskDto();
		try {

			String query = "SELECT * FROM tasks where user_id = ? and id = ?";


			Connection connection = JDBCConnection.getConnection();


			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, user_id);
			statement.setInt(2, task_id);


			ResultSet result = statement.executeQuery();


			while(result.next()) {


				taskDto.setId(result.getInt("id"));
				taskDto.setName(result.getString("name"));
				Timestamp startDate = result.getTimestamp("start_date");
				Timestamp endDate = result.getTimestamp("end_date");

				Date dateStart= new Date(startDate.getTime());
				Date dateEnd= new Date(endDate.getTime());


				taskDto.setStartDate(dateStart);
				taskDto.setEndDate(endDate);

				taskDto.setJob(jobRepository.findJobById(result.getInt("job_id")).getName());
				taskDto.setUser(userRepository.findById(result.getInt("user_id")).getFullName());
				taskDto.setStatus(statusRepository.findStatusById(result.getInt("status_id")).getName());


			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return taskDto;
	}

	/*
	 * Hàm đếm user này làm bao nhiêu task 
	 * param id: id của user cần lấy
	 * return: số lượng task của user
	 * Author: 
	 */
	public int countTaskOfUser(int id) {
		int tong = 0;
		try {
			String query = "SELECT COUNT(t.id) as numberTask from tasks t JOIN users u ON t.user_id = u.id  where u.id = ?";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				tong += result.getInt("numberTask");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());


		}

		return tong;
	}
	
	/*
	 * Hàm đếm user này còn bao nhiêu task chưa làm xong 
	 * param id: id của user cần lấy
	 * return: số lượng task user chưa làm xong làm
	 * Author: 
	 */
	public float countTaskNotDoneOfUser(int id) {
		float tong = 0;
		try {
			String query = "select count(t.id) as numberTaskNotDone from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id join jobs j on t.job_id=j.id where u.id = ? AND s.id = 1";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				tong += result.getInt("numberTaskNotDone");
			}


		} catch (Exception e) {
			System.out.println(e.getMessage());


		}

		return tong;
	}
	
	/*
	 * Hàm đếm user này có bao nhiêu task đang chờ xử lý 
	 * param id: id của user cần lấy
	 * return: số lượng task user đang chờ để làm
	 * Author: 
	 */
	public float countTaskPendingOfUser(int id) {
		float tong = 0;
		try {
			String query = "select count(t.id) as numberTaskPending from tasks t join users u on t.user_id = u.id join status s on t.status_id = s.id join jobs j on t.job_id=j.id where u.id = ? AND s.id = 2";
			Connection connection = JDBCConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, id);
		
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				tong += result.getInt("numberTaskPending");
			}


		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return tong;
	}
	
	
}
