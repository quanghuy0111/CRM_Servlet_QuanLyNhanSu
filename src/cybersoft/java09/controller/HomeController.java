package cybersoft.java09.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java09.constants.UrlConstants;
import cybersoft.java09.dto.UserDto;
import cybersoft.java09.entity.User;
import cybersoft.java09.repository.TaskRepository;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(description = "Initalizes the table",urlPatterns = {UrlConstants.URL_HOME})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TaskRepository taskRepository;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		int totalTask = taskRepository.countTaskOfUser(user.getId());
		
		int notDoneTask = (int) taskRepository.countTaskNotDoneOfUser(user.getId());
		
		int pendingTask = (int) taskRepository.countTaskPendingOfUser(user.getId());
		
		int finishTask = totalTask - (notDoneTask+pendingTask);
		
		
		UserDto userDto = new UserDto();
		userDto.setNotDoneWorkPercent(notDoneTask);
		userDto.setPendingWorkPercent(pendingTask);
		userDto.setFinishWorkPercent(finishTask);
		
		request.setAttribute("userDto", userDto);
		request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_HOME + UrlConstants.URL_INDEX + ".jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		taskRepository = new TaskRepository();
	}
	
}
