package cybersoft.java09.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java09.constants.UrlConstants;
import cybersoft.java09.dto.JobDto;
import cybersoft.java09.entity.Job;
import cybersoft.java09.entity.User;
import cybersoft.java09.repository.JobRepository;
import cybersoft.java09.repository.UserRepository;


/**
 * Servlet implementation class GroupWorkController
 */
@WebServlet(urlPatterns = {UrlConstants.URL_JOB,
		   UrlConstants.URL_JOB_ADD,
		   UrlConstants.URL_JOB_DELETE,
		   UrlConstants.URL_JOB_DETAILS,
		   UrlConstants.URL_JOB_EDIT})
public class GroupWorkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private JobRepository jobRepository;   
    private UserRepository userRepository;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupWorkController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	public void init() throws ServletException {
		jobRepository = new JobRepository();
		userRepository = new UserRepository();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		HttpSession session = request.getSession();
		
		//lấy ra user hiện có của session
		User user = (User) session.getAttribute("user");
		switch (path) {
		case UrlConstants.URL_JOB:
			
			List<Job> jobs = jobRepository.getAllJob();
			request.setAttribute("jobs", jobs);

			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_JOB + UrlConstants.URL_JOB + ".jsp").forward(request, response);
			break;
			
		case UrlConstants.URL_JOB_DETAILS:
			int id_detail = Integer.parseInt(request.getParameter("id"));
			
			List<JobDto> jobDtos = jobRepository.getUserOfListDto(id_detail);
			
			for(JobDto jobDto : jobDtos) {
				
				jobDto.setTaskNotDone(userRepository.findTaskOfUser(jobDto.getUser().getId(), 1));
				jobDto.setTaskPending(userRepository.findTaskOfUser(jobDto.getUser().getId(), 2));
				jobDto.setTaskDone(userRepository.findTaskOfUser(jobDto.getUser().getId(), 3));
			}
			System.out.println(jobDtos.get(0).getTaskNotDone().get(0));
			
			request.setAttribute("jobDtos", jobDtos);
			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_JOB + UrlConstants.URL_JOB_DETAILS + ".jsp").forward(request, response);
			break;
			
		case UrlConstants.URL_JOB_ADD:
			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_JOB + UrlConstants.URL_JOB_ADD + ".jsp").forward(request, response);
			break;
			
		case UrlConstants.URL_JOB_EDIT:
			int id = Integer.parseInt(request.getParameter("id"));
			//tìm kiếm job theo id
			Job job = jobRepository.findJobById(id);
			
			//tìm kiếm user đang làm job có id = ?
			List<User> users = jobRepository.findUsersByJobID(id);
			
			//vì nhân viên không thể vô được bất kì trang edit nào nên chỉ xét trường hợp là manager.
			if(user.getRole_Id()==2) {
				
				//Duyệt list user đang làm job có id = ?
				for(User u : users) {
					
					// chỉ những manager nào đang quản lý job nào mới được truy cập vào sửa job đó.
					// so sánh user của session đang đăng nhập với danh sách user đang làm job
					if(u.getId()!= user.getId()) {
						
						//Nếu user đang đăng nhập không làm job thì báo lỗi
						response.sendRedirect(getServletContext().getContextPath()+UrlConstants.URL_403_ERROR);
						return;
					}
				}
			}
			//trường hợp còn lại chắc chắn là admin
			request.setAttribute("job", job);
			
			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_JOB + UrlConstants.URL_JOB_EDIT + ".jsp").forward(request, response);
			break;
			
		case UrlConstants.URL_JOB_DELETE:
			int id_del = Integer.parseInt(request.getParameter("id"));
			jobRepository.deleteJob(id_del);
			
			response.sendRedirect(getServletContext().getContextPath()+ UrlConstants.URL_JOB);
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
		
		switch (path) {
		case UrlConstants.URL_JOB:
		
			break;
		case UrlConstants.URL_JOB_DETAILS:
			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_JOB + UrlConstants.URL_JOB_DETAILS + ".jsp").forward(request, response);
			break;
			
		case UrlConstants.URL_JOB_ADD:
			String name = request.getParameter("name");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				//ép kiểu date
				Date startDate = sdf.parse(start_date);
				Date endDate = sdf.parse(end_date);
				Job job = new Job(name, startDate, endDate);
				
				jobRepository.addNewJob(job);
				response.sendRedirect(getServletContext().getContextPath()+ UrlConstants.URL_JOB);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			break;
			
		case UrlConstants.URL_JOB_EDIT:
			int id = Integer.parseInt(request.getParameter("id"));
			String name_edit = request.getParameter("name");
			String start_date_edit = request.getParameter("start_date");
			String end_date_edit = request.getParameter("end_date");
			
			SimpleDateFormat sdf_edit = new SimpleDateFormat("dd/MM/yyyy");
			try {
				//ép kiểu date
				Date startDate = sdf_edit.parse(start_date_edit);
				Date endDate = sdf_edit.parse(end_date_edit);
				Job job = new Job(name_edit, startDate, endDate);
				
				jobRepository.editJob(job, id);
				response.sendRedirect(getServletContext().getContextPath()+ UrlConstants.URL_JOB);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			
			break;
		default:
			break;
		}
	}

	
	
	

}
