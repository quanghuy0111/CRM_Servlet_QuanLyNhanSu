package cybersoft.java09.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java09.constants.UrlConstants;
import cybersoft.java09.entity.User;
import cybersoft.java09.repository.UserRepository;

/**
 * Servlet implementation class AuthController
 */
@WebServlet(urlPatterns = {UrlConstants.URL_LOGIN,UrlConstants.URL_LOGOUT})
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserRepository userRepository;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		//Kiểm tra đã đăng nhập hay chưa
		if(action !=null && !action.isEmpty()) {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			
		}
		
		request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_LOGIN + UrlConstants.URL_INDEX + ".jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//Lấy ra user có email và password đã nhập
		User user = userRepository.checkLogin(email, password);
		System.out.println(user.getEmail());
		
		//Kiểm tra nếu có tồn tại user và email khác null thì chuyển đến trang home
		if(user!=null && user.getEmail()!=null) {
			//Gán user vào session
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			response.sendRedirect(request.getContextPath()+UrlConstants.URL_HOME);
		
		//Ngược lại là không đăng nhập thành công thì quay lại trang đăng nhập và đăng nhập lại
		}else {
			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_LOGIN + UrlConstants.URL_INDEX + ".jsp").forward(request, response);
			
		}


		
	}

	@Override
	public void init() throws ServletException {
		userRepository = new UserRepository();
	}
	
}
