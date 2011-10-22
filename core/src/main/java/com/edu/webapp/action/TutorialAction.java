package com.edu.webapp.action;

import com.opensymphony.xwork2.Preparable;

import com.edu.Constants;
import com.edu.model.TimeSchedule;
import com.edu.model.Tutorial;
import com.edu.model.User;
import com.edu.webapp.util.RequestUtil;
import org.springframework.mail.MailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContext;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
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
	private boolean ajax;
	//
	private List<TimeSchedule> timeSchedules;
	private TimeSchedule timeSchedule;

	/**
	 * Grab the entity from the database before populating with request parameters
	 */
	public void prepare() {
		// prevent failures on new
		if (getRequest().getMethod().equalsIgnoreCase("post")) {
			String tutorialId = getRequest().getParameter("tutorial.id");
			if (!(tutorialId == null || "".equals(tutorialId))) {
				tutorial = tutorialManager.getTutorial(new Long(tutorialId));
			}
			String timeScheduleId = getRequest()
					.getParameter("timeSchedule.id");
			if (!(timeScheduleId == null || "".equals(timeScheduleId))) {
				timeSchedule = tutorialManager.getTimeSchedule(new Long(
						timeScheduleId));
			}
		}
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

	public List<TimeSchedule> getTimeSchedules() {
		return timeSchedules;
	}

	public void setAjax(boolean ajax) {
		this.ajax = ajax;
	}

	public TimeSchedule getTimeSchedule() {
		return timeSchedule;
	}

	public void setTimeSchedule(TimeSchedule timeSchedule) {
		this.timeSchedule = timeSchedule;
	}

	/**
	 * Delete the tutorial passed in.
	 *
	 * @return success
	 */
	public String delete() {
		if (tutorial.getId() != null) {
			tutorialManager.removeTutorial(tutorial.getId());
			List<Object> args = new ArrayList<Object>();
			args.add(tutorial.getName());
			saveMessage(getText("Tutorial.deleted", args));
		}
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
		boolean isNew = tutorial != null && tutorial.getId() == null;
		// only attempt to change tutorial if user is tutor or admin
		// for other users, prepare() method will handle populating
		if (isRole(Constants.ADMIN_ROLE) || isRole(Constants.TUTOR_ROLE)) {
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
				tutorialManager.saveTutorial(tutorial);
			} catch (Exception ade) {
				ade.printStackTrace();
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
		if (!tutorials.isEmpty()) {
			tutorial = tutorials.get(0);
		}
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

	/**
	 * list all TimeSchedules of the tutorial
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	public String listAllTimeSchedule() {
		id = id == null ? tutorial.getId() : id;
		if (id == null) {
			timeSchedules = new ArrayList<TimeSchedule>();
		} else {
			timeSchedules = tutorialManager.getAllTimeScheduleByTutorialId(id);
		}
		return SUCCESS;
	}

	/**
	 * save a TimeSchedule to the tutorial 
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	public String addTimeSchedule() {
		String fromTime = getRequest().getParameter("fromTime");
		String toTime = getRequest().getParameter("toTime");
		String[] strArr = new String[] { fromTime, toTime };
		String[] ampm = new String[] { "am", "pm" };
		int[] fromto = new int[] { 0, 0 };
		for (int i = 0; i < strArr.length; i++) {
			String tmp = strArr[i] == null ? "" : strArr[i].toLowerCase();
			for (int j = 0; j < ampm.length; j++) {
				boolean contains = tmp.contains(ampm[j])
						&& (tmp = tmp.replace(ampm[j], "")) != null;
				if (contains || j + 1 == ampm.length) {
					String[] split = tmp.split(":");
					fromto[i] = Integer.valueOf(split.length > 0 ? split[0]
							.trim() : "0")
							* 60
							+ (contains ? j * 12 * 60 : 0)
							+ Integer.valueOf(split.length > 1 ? split[1]
									.trim() : "0");
					continue;
				}
			}
		}
		if (fromto[0] >= fromto[1] || fromto[0] < 0 || fromto[1] >= 24 * 60) {
			addFieldError("From", "is invalid value.");
			return SUCCESS;
		}
		timeSchedule.setCreateTime(new Date());
		{
			Calendar c = Calendar.getInstance();
			c.setTime(timeSchedule.getStartDate());
			c.set(Calendar.HOUR_OF_DAY, fromto[0] / 60);
			c.set(Calendar.MINUTE, fromto[0] % 60);
			timeSchedule.setFromTime(c.getTime());
		}
		timeSchedule.setModifyTime(new Date());
		{
			Calendar c = Calendar.getInstance();
			c.setTime(timeSchedule.getStartDate());
			c.set(Calendar.HOUR_OF_DAY, fromto[1] / 60);
			c.set(Calendar.MINUTE, fromto[1] % 60);
			timeSchedule.setToTime(c.getTime());
		}
		if (timeSchedule.getId() == null) {
			timeSchedule.setTutorial(tutorial);
		}
		System.out.println(timeSchedule.getStartDate());
		try {
			tutorialManager.saveTimeSchedule(timeSchedule);
		} catch (Exception ade) {
			ade.printStackTrace();
			// thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
			log.warn(ade.getMessage());
			return SUCCESS;
		}
		//timeSchedule = tutorialManager.getTimeSchedule(timeSchedule.getId());
		//System.out.println(timeSchedule.getStartDate());
		return SUCCESS;
	}

	/**
	 * remove a TimeSchedule from the tutorial
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	public String removeTimeSchedule() {
		if (timeSchedule.getId() != null) {
			tutorialManager.removeTimeSchedule(timeSchedule.getId());
			List<Object> args = new ArrayList<Object>();
			args.add(timeSchedule.toString());
			saveMessage(getText("TimeSchedule.deleted", args));
		}
		return SUCCESS;
	}
	
	/**
	 * search tutorials
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-22
	 */
	public String findTutorials(){
		return SUCCESS;
	}
	
	/**
	 * view a tutorial for booking
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-22
	 */
	public String viewTutorial(){
		return SUCCESS;
	}
	
	/**
	 * register all of selected tutorials
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-22
	 */
	public String registerTutorial(){
		return SUCCESS;
	}
}
