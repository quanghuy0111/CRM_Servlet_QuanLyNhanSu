package cybersoft.java09.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java09.constants.UrlConstants;
import cybersoft.java09.dto.UserDto;
import cybersoft.java09.entity.User;
import cybersoft.java09.repository.UserRepository;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {
	private UserRepository userRepository;
	/**
	 * Default constructor. 
	 */
	public AuthFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//Kiểm tra các link không dùng filter
		String action = req.getServletPath();
		if(action.equals(UrlConstants.URL_LOGIN)) {
			//Chuyển hướng về login
			chain.doFilter(request, response);
			return;
		}

		//==================ĐĂNG NHẬP====================
		//Code xử lí request
		//Kiểm tra email, password của user
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		//TH1 nếu user chưa thực hiện đăng nhập
		if(user==null) {
			//Chuyển hướng về trang login
			res.sendRedirect(req.getContextPath() + UrlConstants.URL_LOGIN);
			return;
		}
		
		else {
			int roleID = user.getRole_Id();

			//startwith Chọn những link bắt đầu bằng "/role"
			//Link bắt đầu bằng role và thêm mới user mà không phải admin thì không được truy cập
			if(roleID != 1 && (action.equals(UrlConstants.URL_USER_ADD) || action.startsWith(UrlConstants.URL_ROLE))) {
				res.sendRedirect(req.getContextPath() + UrlConstants.URL_403_ERROR); return;
			}

			//nếu đăng nhập là user thì không được vô nhưng trang thêm sửa xóa và trang groupwork
			 if(roleID == 3 && ((action.endsWith("edit"))  || action.endsWith("delete") ||
					action.startsWith(UrlConstants.URL_JOB) || action.endsWith("add"))){
				 
				 
				 res.sendRedirect(req.getContextPath() + UrlConstants.URL_403_ERROR);

				 return;
				 
			 
			 }
			



			chain.doFilter(request, response);

		}






	}









	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		userRepository = new UserRepository();
	}

}
