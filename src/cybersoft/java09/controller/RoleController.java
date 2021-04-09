package cybersoft.java09.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java09.constants.UrlConstants;
import cybersoft.java09.entity.Role;
import cybersoft.java09.repository.RoleRepository;

/**
 * Servlet implementation class RoleController
 */
@WebServlet(urlPatterns = {UrlConstants.URL_ROLE_ADD,
		   UrlConstants.URL_ROLE_TABLE,
		   UrlConstants.URL_ROLE_EDIT,
		   UrlConstants.URL_ROLE_DELETE})
public class RoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoleRepository roleRepository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoleController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		
		switch (path) {
		case UrlConstants.URL_ROLE_ADD:

			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_ROLE + UrlConstants.URL_ROLE_ADD + ".jsp").forward(request, response);
			break;
		case UrlConstants.URL_ROLE_TABLE:
			List<Role> roles = roleRepository.getAllRole();
			request.setAttribute("roles", roles);

			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_ROLE + UrlConstants.URL_ROLE_TABLE + ".jsp").forward(request, response);
			break;

		case UrlConstants.URL_ROLE_EDIT:
			int id = Integer.valueOf(request.getParameter("id"));
			Role role = roleRepository.findRoleById(id);


			request.setAttribute("role", role);

			request.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_ROLE + UrlConstants.URL_ROLE_EDIT + ".jsp").forward(request, response);
			break;	
		case UrlConstants.URL_ROLE_DELETE:
			int id_edit = Integer.valueOf(request.getParameter("id"));
			
			roleRepository.deleteRole(id_edit);
			response.sendRedirect(request.getContextPath()+ UrlConstants.URL_ROLE_TABLE);
			
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

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		switch (path) {
		case UrlConstants.URL_ROLE_ADD:
			Role role = new Role(name,description);
			roleRepository.addNewRole(role);
			response.sendRedirect(request.getContextPath()+ UrlConstants.URL_ROLE_TABLE);
			break;
		case UrlConstants.URL_ROLE_EDIT:
			int id = Integer.valueOf(request.getParameter("id"));
			Role role_edit = new Role(name,description);
			roleRepository.editRole(role_edit,id);
			
			response.sendRedirect(request.getContextPath()+ UrlConstants.URL_ROLE_TABLE);
			break;
		case UrlConstants.URL_ROLE_TABLE:

			break;
		default:
			break;
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		roleRepository = new RoleRepository();
	}



}
