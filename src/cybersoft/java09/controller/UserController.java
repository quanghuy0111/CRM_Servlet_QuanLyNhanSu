package cybersoft.java09.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java09.constants.UrlConstants;
import cybersoft.java09.dto.UserDto;
import cybersoft.java09.entity.Role;
import cybersoft.java09.entity.Task;
import cybersoft.java09.entity.User;
import cybersoft.java09.repository.RoleRepository;
import cybersoft.java09.repository.TaskRepository;
import cybersoft.java09.repository.UserRepository;
import cybersoft.java09.service.UserService;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = {UrlConstants.URL_USER_DETAILS,
		UrlConstants.URL_USER_ADD,
		UrlConstants.URL_USER_EDIT,
		UrlConstants.URL_USER_DELETE,
		UrlConstants.URL_USER_TABLE})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private TaskRepository taskRepository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		HttpSession session = request.getSession();
		User user_session = (User)session.getAttribute("user");
		switch (path) {
		case UrlConstants.URL_USER_TABLE:
			List<UserDto> usersDto = userService.getAllUserRole(); 
			request.setAttribute("users", usersDto);

			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_USER + UrlConstants.URL_USER_TABLE + ".jsp").forward(request, response);
			break;
		case UrlConstants.URL_USER_ADD:
			List<Role> roles = roleRepository.getAllRole(); //Lấy toàn bộ Role

			request.setAttribute("roles", roles);
			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_USER + UrlConstants.URL_USER_ADD + ".jsp").forward(request, response);
			break;
		case UrlConstants.URL_USER_EDIT:

			int id = Integer.valueOf(request.getParameter("id"));

			User user = userRepository.findById(id);
			
			//Leader không thể sửa thông tin của admin
			//Chỉ xét leader vì nhân viên không thể vào trang edit
			if(user_session.getRole_Id() == 2) {
				//trong trường hợp không phải là nhân viên thì báo lỗi
				if(user.getRole_Id()!=3) {
					response.sendRedirect(request.getContextPath()+ UrlConstants.URL_403_ERROR);
					return;
				}
			}

			request.setAttribute("user", user);

			List<Role> roles_edit = roleRepository.getAllRole();

			if(user_session.getRole_Id()==2) {
				//Bỏ lựa chọn sửa quyền thành admin
				Iterator<Role> itr = roles_edit.iterator();
				while (itr.hasNext()) {
					Role role = itr.next();
					if(role.getId()==1) {
						itr.remove();
					}
				}
			}

			request.setAttribute("roles", roles_edit);

			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_USER + UrlConstants.URL_USER_EDIT + ".jsp").forward(request, response);

			// TRUY VẤN DB LẤY THÔNG TIN USER CẦN SỬA

			break;

		case UrlConstants.URL_USER_DELETE:

			int id_del = Integer.valueOf(request.getParameter("id"));
			User user_delete = userRepository.findById(id_del);
			
			//chỉ xét trường hợp là leader và người cần xóa chỉ được là nhân viên
			if(user_session.getRole_Id() == 2) {
				if(user_delete.getRole_Id()!=3) {
					response.sendRedirect(request.getContextPath()+UrlConstants.URL_403_ERROR);
					return;
				}
			}

			userRepository.deleteUser(id_del);
			response.sendRedirect(request.getContextPath()+ UrlConstants.URL_USER_TABLE);




			break;


		case UrlConstants.URL_USER_DETAILS:
			int id_detail = Integer.valueOf(request.getParameter("id"));
			List<Task> listTaskNotDone = userRepository.findTaskOfUser(id_detail, 1);
			List<Task> listTaskPending = userRepository.findTaskOfUser(id_detail, 2);
			List<Task> listTaskDone = userRepository.findTaskOfUser(id_detail, 3);

			//Lấy phần trăm công việc
			int totalTask = taskRepository.countTaskOfUser(id_detail);
			int notDoneTask = (int) ((taskRepository.countTaskNotDoneOfUser(id_detail)*100)/totalTask);
			int pendingTask = (int) ((taskRepository.countTaskPendingOfUser(id_detail)*100)/totalTask);
			int finishTask = 100 - (notDoneTask+pendingTask);

			//Gán phần trăm để tạo biểu đồ
			UserDto userDto = new UserDto();
			userDto.setNotDoneWorkPercent(notDoneTask);
			userDto.setPendingWorkPercent(pendingTask);
			userDto.setFinishWorkPercent(finishTask);



			User userDetail = userRepository.findById(id_detail);

			request.setAttribute("listTaskNotDone", listTaskNotDone);
			request.setAttribute("listTaskPending", listTaskPending);
			request.setAttribute("listTaskDone", listTaskDone);
			request.setAttribute("userDetail", userDetail);
			request.setAttribute("userDto", userDto);



			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_USER + UrlConstants.URL_USER_DETAILS + ".jsp").forward(request, response);
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


		String email = request.getParameter("email");
		String password = request.getParameter("passwd");
		String fullname = request.getParameter("fullname");
		String avatar = request.getParameter("avatar");
		int roleId = Integer.parseInt(request.getParameter("roleId"));

		switch (path) {

		case UrlConstants.URL_USER_ADD:

			/* String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12)); */
			User user = new User(email, password, fullname, avatar, roleId);

			userRepository.addUser(user);

			response.sendRedirect(request.getContextPath()+ UrlConstants.URL_USER_TABLE);
			break;

		case UrlConstants.URL_USER_EDIT:

			int id = Integer.parseInt(request.getParameter("id"));
			User userEdit = userRepository.findById(id);
			userEdit.setEmail(email);
			userEdit.setFullName(fullname);
			userEdit.setAvatar(avatar);
			userEdit.setRole_Id(roleId);

			// KIỂM TRA XEM NGƯỜI DÙNG NHẬP MẬT KHẨU MỚI KHÔNG
			/*
			 * if (password != null && !password.isEmpty()) { // MÃ HÓA MẬT KHẨU String
			 * hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12)); // CẬP NHẬT THÔNG TIN
			 * USER (BAO GỒM CẢ KHẨU) userEdit.setPassWord(hashed2); }
			 */

			userRepository.editUser(userEdit, id);

			response.sendRedirect(request.getContextPath()+ UrlConstants.URL_USER_TABLE);


			break;
		case UrlConstants.URL_USER_DETAILS:
			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_USER + UrlConstants.URL_USER_DETAILS + ".jsp").forward(request, response);
			break;
		default:
			break;
		}
	}

	@Override
	public void init() throws ServletException {
		userService = new UserService();
		roleRepository = new RoleRepository();
		userRepository = new UserRepository();
		taskRepository = new TaskRepository();
	}



}
