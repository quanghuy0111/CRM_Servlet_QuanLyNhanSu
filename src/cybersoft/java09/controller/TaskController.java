package cybersoft.java09.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java09.constants.UrlConstants;
import cybersoft.java09.dto.TaskDto;
import cybersoft.java09.entity.Job;
import cybersoft.java09.entity.Task;
import cybersoft.java09.entity.User;
import cybersoft.java09.repository.JobRepository;
import cybersoft.java09.repository.TaskRepository;
import cybersoft.java09.repository.UserRepository;

/**
 * Servlet implementation class TaskController
 */
@WebServlet(urlPatterns = {UrlConstants.URL_TASK,
		   UrlConstants.URL_TASK_EDIT,
		   UrlConstants.URL_TASK_DELETE,
		   UrlConstants.URL_TASK_ADD})
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskRepository taskRepository;
	private JobRepository jobRepository;
	private UserRepository userRepository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		int id;
		//Lấy thông tin user đang đăng nhập
		HttpSession session = request.getSession();
		User user_session = (User)session.getAttribute("user");
		
		switch (path) {
		case UrlConstants.URL_TASK:

			List<TaskDto> taskDtos = taskRepository.getAllTask();

			request.setAttribute("taskDtos", taskDtos);

			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_TASK + UrlConstants.URL_TASK + ".jsp").forward(request, response);
			break;
		case UrlConstants.URL_TASK_ADD:
			List<User> users = userRepository.getAlluser();
			List<Job> jobs = jobRepository.getAllJob();

			request.setAttribute("users", users);
			request.setAttribute("jobs", jobs);

			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_TASK + UrlConstants.URL_TASK_ADD + ".jsp").forward(request, response);
			break;

		case UrlConstants.URL_TASK_EDIT:

			id = Integer.parseInt(request.getParameter("id"));
			List<User> users_edit = userRepository.getAlluser();
			List<Job> jobs_edit = jobRepository.getAllJob();
			Job job = jobRepository.findJobByUserID(user_session.getId());
			
			if(user_session.getRole_Id() == 2) {    //Nếu User hiện tại có role là LEADER , users_edit sẽ remove các 
													//user là Admin trong list users_edit. Đồng thời remove các dự án không
													// không thuộc User leader này
				// leader không thể sửa thông tin của admin nên bỏ đi lựa chọn sửa role thành admin							
				Iterator<User> itr = users_edit.iterator();
				while (itr.hasNext()) {
					User user = itr.next();
					if(user.getRole_Id()==1) {
						itr.remove();
					}

				//User leader chỉ có thể sửa những dự án của mình làm
					Iterator<Job> itr_job = jobs_edit.iterator();
					while (itr_job.hasNext()) {
						Job j = itr_job.next();
						if(job.getId()!=j.getId()) {
							itr_job.remove();
						}
					}
				}
			}



			Task task = taskRepository.findTaskById(id);
			System.out.println("ID là "+id);

			request.setAttribute("task", task);
			request.setAttribute("users", users_edit);
			request.setAttribute("jobs", jobs_edit);
			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_TASK + UrlConstants.URL_TASK_EDIT + ".jsp").forward(request, response);
			break;
		case UrlConstants.URL_TASK_DELETE:
			id = Integer.parseInt(request.getParameter("id"));
			taskRepository.deleteTask(id);

			response.sendRedirect(getServletContext().getContextPath()+ UrlConstants.URL_TASK);
			
			break;
		default:
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		int job = Integer.parseInt(request.getParameter("job"));
		String taskName = request.getParameter("taskName");
		int user = Integer.parseInt(request.getParameter("user"));

		String start_date = request.getParameter("startDate");
		String end_date = request.getParameter("endDate");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		switch (path) {
		case UrlConstants.URL_TASK:

			break;
		case UrlConstants.URL_TASK_ADD:
			try {
				Date startDate = sdf.parse(start_date);
				Date endDate = sdf.parse(end_date);
				Task task = new Task(taskName, startDate, endDate, user, job, 1);

				taskRepository.addNewTask(task);
				response.sendRedirect(getServletContext().getContextPath()+ UrlConstants.URL_TASK);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			break;

		case UrlConstants.URL_TASK_EDIT   :
			int id =Integer.parseInt(request.getParameter("id")) ;


			try {
				Date startDate = sdf.parse(start_date);
				Date endDate = sdf.parse(end_date);
				Task task = new Task(taskName, startDate, endDate, user, job, 1);
				System.out.println(task);
				System.out.println(id);
				
				taskRepository.editTask(task, id);
				
				response.sendRedirect(getServletContext().getContextPath()+ UrlConstants.URL_TASK);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			break;


		default:
			break;
		}
	}

	@Override
	public void init() throws ServletException {
		taskRepository = new TaskRepository();
		jobRepository = new JobRepository();
		userRepository = new UserRepository();
	}


}
