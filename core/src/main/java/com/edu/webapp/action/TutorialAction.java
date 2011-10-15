package com.edu.webapp.action;

import com.opensymphony.xwork2.Preparable;
import org.appfuse.Constants;
import org.appfuse.model.Tutorial;
import org.appfuse.model.User;

import com.edu.webapp.util.RequestUtil;
import org.springframework.mail.MailException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Action for facilitating User Management feature.
 */
public class TutorialAction extends BaseAction implements Preparable {
	private static final long serialVersionUID = 6776558938712115192L;
	private List<Tutorial> tutorials;
	private Tutorial tutorial;
	private Long id;
	private String query;

	/**
	 * Grab the entity from the database before populating with request parameters
	 */
	public void prepare() {
		// prevent failures on new
//		if (getRequest().getMethod().equalsIgnoreCase("post")
//				&& (!"".equals(getRequest().getParameter("tutorial.id")))) {
//			tutorial = tutorialManager.getTutorial(new Long(getRequest()
//					.getParameter("tutorial.id")));
//		}
	}

	/**
	 * Holder for tutorials to display on list screen
	 *
	 * @return list of tutorial
	 */
	public List<Tutorial> getTutorials() {
		return tutorials;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tutorial getTutorial() {
		return tutorial;
	}

	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
	}

	public void setQ(String q) {
		this.query = q;
	}

	/**
	 * Delete the tutorial passed in.
	 *
	 * @return success
	 */
	public String delete() {
		tutorialManager.removeTutorial(tutorial.getId());
		List<Object> args = new ArrayList<Object>();
		args.add(tutorial.getName());
		saveMessage(getText("Tutorial.deleted", args));

		return SUCCESS;
	}

	/**
	 * Grab the tutorial from the database based on the "id" passed in.
	 *
	 * @return success if tutorial found
	 * @throws IOException can happen when sending a "forbidden" from response.sendError()
	 */
	public String edit() throws IOException {
		// if a user's id is passed in
		if (id != null) {
			// lookup the tutorial using tutorial id
			tutorial = tutorialManager.getTutorial(id);
		} else {
			return null;
		}

		return SUCCESS;
	}

	/**
	 * Default: just returns "success"
	 *
	 * @return "success"
	 */
	public String execute() {
		return SUCCESS;
	}

	/**
	 * Sends users to "mainMenu" when !from.equals("list"). Sends everyone else to "cancel"
	 *
	 * @return "mainMenu" or "cancel"
	 */
	public String cancel() {
		if (!"list".equals(from)) {
			return "mainMenu";
		}
		return "cancel";
	}

	/**
	 * Save tutorial
	 *
	 * @return success if everything worked, otherwise input
	 * @throws Exception when setting "access denied" fails on response
	 */
	public String save() throws Exception {

		//Integer originalVersion = tutorial.getVersion();
		HttpServletRequest request = getRequest();
		boolean isNew = ("".equals(getRequest()
				.getParameter("tutorial.version")));
		// only attempt to change tutorial if user is tutor or admin
		// for other users, prepare() method will handle populating
		if (getRequest().isUserInRole(Constants.ADMIN_ROLE)
				|| getRequest().isUserInRole(Constants.TUTOR_ROLE)) {

			if (isNew) {
				User loginUser;
				Set<User> tutors;

				tutorial.setEnabled(true);
				tutorial.setTutorialLocked(false);
				tutorial.setTutorialExpired(false);
				// TODO to be implemented
				tutorial.setSchedule(new Date());
				tutorial.setCreateTime(new Date());
				tutorial.setModifyTime(new Date());
				tutorial.setOpenDays(5);
				tutorial.setVersion(0);
				// Set the login user as the tutor
				tutors = new HashSet<User>();
				SecurityContext ctx = SecurityContextHolder.getContext();
				if (ctx != null) {
					Authentication auth = ctx.getAuthentication();
					loginUser = (User) auth.getPrincipal();
					tutors.add(loginUser);
				}
				tutorial.setTutors(tutors);
			}
			try {
				tutorialManager.save(tutorial);
			} catch (AccessDeniedException ade) {
				// thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
				log.warn(ade.getMessage());
				getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}

			if (!"list".equals(from)) {
				// add success messages
				saveMessage(getText("tutorial.saved"));
				return SUCCESS;
			} else {
				// add success messages
				List<Object> args = new ArrayList<Object>();
				args.add(tutorial.getName());
				if (isNew) {
					saveMessage(getText("tutorial.added", args));
					// Send an account information e-mail
					mailMessage.setSubject(getText("signup.email.subject"));
					try {
						sendUserMessage(userManager.getUserByUsername(request
								.getRemoteUser()), getText(
								"newtutorial.email.message", args), RequestUtil
								.getAppURL(getRequest()));
					} catch (MailException me) {
						addActionError(me.getCause().getLocalizedMessage());
					}
					return SUCCESS;
				} else {
					saveMessage(getText("tutorial.updated", args));
					tutorial = tutorialManager.getTutorial(tutorial.getId());
					return INPUT;
				}
			}
		}
		log.warn("Failed to save tutorial");
		getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
		return null;
	}

	/**
	 * Fetch all tutorials from database and put into local "tutorials" variable for retrieval in the UI.
	 *
	 * @return "success" if no exceptions thrown
	 */
	public String list() {
		tutorials = tutorialManager.search(query);
		return SUCCESS;
	}

	/**
	 * Fetch all tutorials from database and put into local "tutorials" variable for retrieval in the UI.
	 *
	 * @return "success" if no exceptions thrown
	 */
	public String listAll() {
		tutorials = tutorialManager.getTutorials();
		return SUCCESS;
	}

	/**
	 * Fetch all users from database and put into local "tutorials" variable for retrieval in the UI.
	 *
	 * @return "success" if no exceptions thrown
	 */
	public String test() {
		tutorials = tutorialManager.search(query);
		return SUCCESS;
	}

}
