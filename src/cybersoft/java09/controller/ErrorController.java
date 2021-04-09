package cybersoft.java09.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java09.constants.UrlConstants;

@WebServlet(urlPatterns = {UrlConstants.URL_403_ERROR})
public class ErrorController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String action = req.getServletPath();
		
		switch (action) {
		case UrlConstants.URL_403_ERROR:
			req.getRequestDispatcher(UrlConstants.CONTEXT_PATH + UrlConstants.URL_404_ERROR + ".jsp")
			.forward(req, resp);
			break;

		default:
			break;
		}
	}
}
