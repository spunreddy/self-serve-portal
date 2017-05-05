package com.snaplogic.portal.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.snaplogic.portal.model.User;
import com.snaplogic.portal.service.UserService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=cap1 Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class NewUserPortlet extends MVCPortlet {
	public static final Logger logger = LoggerFactory.getLogger(NewUserPortlet.class);
	
	
	//Action method
	public void addUser(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {

		logger.info("In Portlet........");
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
	        User.class.getName(), request);

	    String requestorFirstName = ParamUtil.getString(request, "first_name");
	    String requestorLastName = ParamUtil.getString(request, "last_name");
	    String requestorEmail = ParamUtil.getString(request, "email");
	    String org = ParamUtil.getString(request, "org");
	    boolean isAdmin = Boolean.valueOf(ParamUtil.getString(request, "is_admin"));
	    boolean allowPasswordLogin = Boolean.valueOf(ParamUtil.getString(request, "allow_password_login"));
	    boolean createHomeDirectory = Boolean.valueOf(ParamUtil.getString(request, "create_home_dir"));
	    
	    logger.info("First Name: " + requestorFirstName);
	    logger.info("Last Name: " + requestorLastName);
	    logger.info("Email: " + requestorEmail);
	    logger.info("Organization: " + org);
	    logger.info("Administrator: " + isAdmin);
	    logger.info("Allow Password Login: " + allowPasswordLogin);
	    logger.info("Create Home Directory: " + createHomeDirectory);

	    
	    User newUser = new User(requestorFirstName, requestorLastName, requestorEmail, org, isAdmin, allowPasswordLogin, createHomeDirectory);
	    
	    logger.info("Successfully built the User Object: " + newUser.toString());
	    

	    try {
	        UserService _userService = new UserService();
			_userService.addUser(serviceContext.getUserId(),
	                newUser, serviceContext);
			
			logger.info("Returned from the Service Method Successfully");
			

	        SessionMessages.add(request, "userAdded");

	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	    	e.printStackTrace();
	        SessionErrors.add(request, e.getClass().getName());

	        response.setRenderParameter("mvcPath",
	            "/view.jsp");
	    }

	}
}