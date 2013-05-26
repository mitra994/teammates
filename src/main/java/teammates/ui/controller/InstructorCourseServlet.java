package teammates.ui.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import teammates.common.Common;
import teammates.common.exception.EntityDoesNotExistException;

@SuppressWarnings("serial")
/**
 * Servlet to display the course list page for instructors
 */
public class InstructorCourseServlet extends ActionServlet<InstructorCourseHelper> {

	@Override
	protected InstructorCourseHelper instantiateHelper() {
		return new InstructorCourseHelper();
	}


	@Override
	protected void doAction(HttpServletRequest req,
			InstructorCourseHelper helper)
			throws EntityDoesNotExistException {
		
		helper.loadCourseList();
		helper.setStatus();
		generateLogEntry(req, helper);
		
	}

	//TODO: implement a smoother mechanism for generating the log message
	private void generateLogEntry(HttpServletRequest req,
			InstructorCourseHelper helper) {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(helper.courses.size());
		activityLogEntry = instantiateActivityLogEntry(
				Common.INSTRUCTOR_COURSE_SERVLET,
				Common.INSTRUCTOR_COURSE_SERVLET_PAGE_LOAD,
				true, helper, getRequestedURL(req), data);
	}


	@Override
	protected String getDefaultForwardUrl() {
		return Common.JSP_INSTRUCTOR_COURSE;
	}


	@Override
	protected String generateActivityLogEntryMessage(String servletName, String action, ArrayList<Object> data) {
		return InstructorCourseHelper
				.generateActivityLogEntryMessageForCourseList(servletName, action, data);
	}
	
	
}
