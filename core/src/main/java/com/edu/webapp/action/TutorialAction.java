package com.edu.webapp.action;

import com.opensymphony.xwork2.Preparable;

import com.edu.Constants;
import com.edu.model.TutorialSchedule;
import com.edu.model.Tutorial;
import com.edu.model.User;
import com.edu.util.DateUtil;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Action for facilitating User Management feature.
 */
public class TutorialAction extends BaseAction implements Preparable {
	private static final long serialVersionUID = 6776558938712115192L;
	private List<Tutorial> tutorials;
	private Tutorial tutorial;
	private Long id;
	private String query;
	//
	private List<TutorialSchedule> tutorialSchedules;
	private TutorialSchedule tutorialSchedule;

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
			String tutorialScheduleId = getRequest().getParameter(
					"tutorialSchedule.id");
			if (!(tutorialScheduleId == null || "".equals(tutorialScheduleId))) {
				tutorialSchedule = tutorialManager
						.getTutorialSchedule(new Long(tutorialScheduleId));
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

	public Long getId() {
		return id;
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

	public List<TutorialSchedule> getTutorialSchedules() {
		return tutorialSchedules;
	}

	public TutorialSchedule getTutorialSchedule() {
		return tutorialSchedule;
	}

	public void setTutorialSchedule(TutorialSchedule tutorialSchedule) {
		this.tutorialSchedule = tutorialSchedule;
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
				User user = getUser();
				if (user != null) {
					tutors.add(user);
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
	 * list all TutorialSchedules of the tutorial
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	public String listAllTutorialSchedule() {
		id = id == null ? tutorial.getId() : id;
		if (id == null) {
			tutorialSchedules = new ArrayList<TutorialSchedule>();
		} else {
			tutorialSchedules = tutorialManager
					.getAllTutorialScheduleByTutorialId(id);
		}
		return SUCCESS;
	}

	/**
	 * save a TutorialSchedule to the tutorial 
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	public String addTutorialSchedule() {
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
		tutorialSchedule.setCreateTime(new Date());
		{
			Calendar c = Calendar.getInstance();
			c.setTime(tutorialSchedule.getStartDate());
			c.set(Calendar.HOUR_OF_DAY, fromto[0] / 60);
			c.set(Calendar.MINUTE, fromto[0] % 60);
			tutorialSchedule.setFromTime(c.getTime());
		}
		tutorialSchedule.setModifyTime(new Date());
		{
			Calendar c = Calendar.getInstance();
			c.setTime(tutorialSchedule.getStartDate());
			c.set(Calendar.HOUR_OF_DAY, fromto[1] / 60);
			c.set(Calendar.MINUTE, fromto[1] % 60);
			tutorialSchedule.setToTime(c.getTime());
		}
		if (tutorialSchedule.getId() == null) {
			tutorialSchedule.setTutorial(tutorial);
		}
		System.out.println(tutorialSchedule.getStartDate());
		try {
			tutorialManager.saveTutorialSchedule(tutorialSchedule);
		} catch (Exception ade) {
			ade.printStackTrace();
			// thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
			log.warn(ade.getMessage());
			return SUCCESS;
		}
		//tutorialSchedule = tutorialManager.getTutorialSchedule(tutorialSchedule.getId());
		//System.out.println(tutorialSchedule.getStartDate());
		return SUCCESS;
	}

	/**
	 * remove a TutorialSchedule from the tutorial
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-18
	 */
	public String removeTutorialSchedule() {
		if (tutorialSchedule.getId() != null) {
			tutorialManager.removeTutorialSchedule(tutorialSchedule.getId());
			List<Object> args = new ArrayList<Object>();
			args.add(tutorialSchedule.toString());
			saveMessage(getText("TutorialSchedule.deleted", args));
		}
		return SUCCESS;
	}

	/**
	 * find someone's registered tutorial
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	public String findRegisteredTutorial() {
		User user = getUser();
		if (user != null) {
			tutorials = tutorialManager.findTutorialsByUserId(user.getId());
		}
		return SUCCESS;
	}

	/**
	 * find someone's registered tutorial schedule
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	public String findRegisteredTutorialSchedule() {
		User user = getUser();
		if (user != null) {
			tutorialSchedules = tutorialManager.findTutorialSchedulesByUserId(
					tutorial.getId(), user.getId());
		}
		return SUCCESS;
	}

	/**
	 * search tutorials
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-22
	 */
	public String findTutorials() {
		String name = null, tutorName = null, sortBy = null;
		Date start = null, end = null;
		{
			name = getRequest().getParameter("search.name");
			name = name == null ? null : name.trim();
		}
		{
			String startStr = getRequest().getParameter("search.start");
			if (startStr != null && (startStr.trim()).length() > 0) {
				try {
					start = DateUtil.convertStringToDate(startStr);
				} catch (Exception e) {
					addFieldError("start date", "is invalid value(MM/dd/yyyy).");
				}
			}
		}
		{
			String endStr = getRequest().getParameter("search.end");
			if (endStr != null && (endStr.trim()).length() > 0) {
				try {
					end = DateUtil.convertStringToDate(endStr);
				} catch (Exception e) {
					addFieldError("end date", "is invalid value(MM/dd/yyyy).");
				}
			}
		}
		{
			tutorName = getRequest().getParameter("search.tutorName");
			tutorName = tutorName == null ? null : tutorName.trim();
		}
		{
			sortBy = getRequest().getParameter("search.sortBy");
			sortBy = sortBy == null ? null : sortBy.trim();
		}
		tutorials = tutorialManager.findTutorials(id, name, start, end,
				tutorName, sortBy);
		return SUCCESS;
	}

	/**
	 * view a tutorial for booking
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-22
	 */
	public String viewTutorial() {
		tutorialSchedules = new ArrayList<TutorialSchedule>();
		if (tutorial == null) {
			return SUCCESS;
		}
		Date start = null, end = null;
		{
			String startStr = getRequest().getParameter("search.start");
			if (startStr != null && (startStr.trim()).length() > 0) {
				try {
					start = DateUtil.convertStringToDate(startStr);
				} catch (Exception e) {
					addFieldError("start date", "is invalid value(MM/dd/yyyy).");
				}
			}
		}
		{
			String endStr = getRequest().getParameter("search.end");
			if (endStr != null && (endStr.trim()).length() > 0) {
				try {
					end = DateUtil.convertStringToDate(endStr);
				} catch (Exception e) {
					addFieldError("end date", "is invalid value(MM/dd/yyyy).");
				}
			}
		}
		start = start == null ? end == null ? new Date() : end : start;
		{
			getRequest().setAttribute("startDate", start);
		}
		boolean isRight = false;
		for (TutorialSchedule ts : tutorial.getTutorialSchedules()) {
			if (ts.getDurationType() == TutorialSchedule.DURATION_NO_REPEAT) {// no repeat
				isRight = DateUtil.isSameDate(ts.getStartDate(), start);
			} else if (ts.getDurationType() == TutorialSchedule.DURATION_DAYLY) {// daily
				isRight = DateUtil.isDaily(ts.getStartDate(), start, ts
						.getEndsOccurrence());
			} else if (ts.getDurationType() == TutorialSchedule.DURATION_WEEKLY) {// weekly
				isRight = DateUtil.isBiWeekly(ts.getStartDate(), start, ts
						.getEndsOccurrence());
			} else if (ts.getDurationType() == TutorialSchedule.DURATION_BI_WEEKLY) {// bi-weekly
				isRight = DateUtil.isMonthly(ts.getStartDate(), start, ts
						.getEndsOccurrence());
			} else if (ts.getDurationType() == TutorialSchedule.DURATION_MONTHLY) {// monthly
				isRight = DateUtil.isMonthly(ts.getStartDate(), start, ts
						.getEndsOccurrence());
			}
			if (isRight) {
				tutorialSchedules.add(ts);
			}
		}
		return SUCCESS;
	}

	/**
	 * booking class for register
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	public String bookTutorial() {
		tutorialSchedules = new ArrayList<TutorialSchedule>();
		String ids = getRequest().getParameter("book.ids");
		ids = "," + ids + ",";
		for (TutorialSchedule ts : tutorial.getTutorialSchedules()) {
			if (ids.indexOf("," + ts.getId() + ",") > -1) {
				tutorialSchedules.add(ts);
			}
		}
		return SUCCESS;
	}

	/**
	 * register all of selected tutorials
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-22
	 */
	public String registerTutorial() {
		String ids = getRequest().getParameter("register.ids");
		List<Long> idList = new ArrayList<Long>();
		if (ids != null) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				try {
					idList.add(Long.valueOf(id.trim()));
				} catch (Exception e) {
				}
			}
		}
		Long[] array = idList.toArray(new Long[idList.size()]);
		User user = getUser();
		if (user != null) {
			tutorialManager.registerTutorial(tutorial.getId(), array, user
					.getId());
		}
		return SUCCESS;
	}

	/**
	 * cancel specific tutorial schedule
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	public String cancelTutorialSchedule() {
		User user = getUser();
		if (user != null && tutorialSchedule != null) {
			tutorialManager.cancelTutorialSchedule(tutorialSchedule.getId(),
					user.getId());
		}
		return SUCCESS;
	}

	/**
	 * cancel specific tutorial
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-25
	 */
	public String cancelTutorial() {
		User user = getUser();
		if (user != null && tutorial != null) {
			tutorialManager.cancelTutorialSchedule(tutorial.getId(), user
					.getId());
		}
		return SUCCESS;
	}

	/**
	 * find specific date tutorial schedule
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-31
	 */
	public String findDayTutorialSchedule() {
		Date start = null, end = null;
		{
			String startStr = getRequest().getParameter("search.start");
			if (startStr != null && (startStr.trim()).length() > 0) {
				try {
					start = DateUtil.convertStringToDate(startStr);
					end = DateUtil.getMaxDay(start).getTime();
				} catch (Exception e) {
					addFieldError("start date", "is invalid value(MM/dd/yyyy).");
				}
			}
		}
		tutorialSchedules = tutorialManager.findTutorialSchedule(start, end);
		return SUCCESS;
	}

	/**
	 * find specific week tutorial
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-31
	 */
	public String findWeekTutorial() {
		Date start = null, end = null;
		{
			String startStr = getRequest().getParameter("search.start");
			if (startStr != null && (startStr.trim()).length() > 0) {
				try {
					start = DateUtil.convertStringToDate(startStr);
					start = DateUtil.getSundayDay(start);
					end = DateUtil.getSaturdayDay(start);
				} catch (Exception e) {
					addFieldError("start date", "is invalid value(MM/dd/yyyy).");
				}
			}
		}
		tutorialSchedules = tutorialManager.findTutorialSchedule(start, end);
		return SUCCESS;
	}

	/**
	 * find specific month tutorial
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-31
	 */
	public String findMonthTutorial() {
		Date start = null, end = null;
		{
			String startStr = getRequest().getParameter("search.start");
			if (startStr != null && (startStr.trim()).length() > 0) {
				try {
					start = DateUtil.convertStringToDate(startStr);
					start = DateUtil.getMonthFirstDay(start);
					end = DateUtil.getMonthLastDay(start);
				} catch (Exception e) {
					addFieldError("start date", "is invalid value(MM/dd/yyyy).");
				}
			}
		}
		tutorialSchedules = tutorialManager.findTutorialSchedule(start, end);
		return SUCCESS;
	}

	/**
	 * separated tutorial schedules into {hour:scheduleInfo} 
	 * @param tutorialSchedules
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-31
	 */
	public static Map<Integer, List<Map<String, Object>>> processDaySchedule(
			List<TutorialSchedule> tutorialSchedules) {
		Map<Integer, List<Map<String, Object>>> map = new HashMap<Integer, List<Map<String, Object>>>();
		if (tutorialSchedules == null || tutorialSchedules.isEmpty()) {
			return map;
		}
		for (int i = 0; i < 12; i++) {
			map.put(i * 2, new ArrayList<Map<String, Object>>());
		}
		for (TutorialSchedule ts : tutorialSchedules) {
			int fromMinute = DateUtil.getMinute(ts.getFromTime());
			List<Map<String, Object>> list = map.get((fromMinute / 120) * 2);
			if (list != null) {
				list.add(tutorialScheduleToMap(ts));
			}
		}
		return map;
	}

	/**
	 * separated tutorial schedules into {weekDate:scheduleInfo}
	 * @param tutorialSchedules
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-31
	 */
	public static Map<String, List<Map<String, Object>>> processWeekSchedule(
			List<TutorialSchedule> tutorialSchedules) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
		if (tutorialSchedules == null || tutorialSchedules.isEmpty()) {
			return map;
		}
		Date start = DateUtil.getSundayDay(tutorialSchedules.get(0)
				.getStartDate());
		Date next = start;
		for (int i = 0; i < 7; i++) {
			next = i == 0 ? start : DateUtil.nextDate(next);
			map.put(sdf.format(next), new ArrayList<Map<String, Object>>());
		}
		for (TutorialSchedule ts : tutorialSchedules) {
			List<Map<String, Object>> list = map.get(sdf.format(ts
					.getStartDate()));
			if (list != null) {
				list.add(tutorialScheduleToMap(ts));
			}
		}
		return map;
	}

	/**
	 * separated tutorial schedules into {dayOfMonth:scheduleInfo}
	 * @param tutorialSchedules
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-31
	 */
	public static Map<String, List<Map<String, Object>>> processMonthSchedule(
			List<TutorialSchedule> tutorialSchedules) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
		if (tutorialSchedules == null || tutorialSchedules.isEmpty()) {
			return map;
		}
		Date start = DateUtil.getMonthFirstDay(tutorialSchedules.get(0)
				.getStartDate());
		String dateStr = sdf.format(start);
		String month = sdf.format(start).substring(0, 6);
		Date next = start;
		while (dateStr.indexOf(month) == 0) {
			map.put(dateStr, new ArrayList<Map<String, Object>>());
			next = DateUtil.nextDate(next);
			dateStr = sdf.format(next);
		}
		for (TutorialSchedule ts : tutorialSchedules) {
			List<Map<String, Object>> list = map.get(sdf.format(ts
					.getStartDate()));
			if (list != null) {
				list.add(tutorialScheduleToMap(ts));
			}
		}
		return map;
	}

	/**
	 * separated tutorial schedules into morning, afternoon, evening
	 * @param tutorialSchedules
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-31
	 */
	public static Map<String, List<Map<String, Object>>> processTimeAreaTutorialSchedule(
			List<TutorialSchedule> tutorialSchedules) {
		Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
		{
			map.put("morning", new ArrayList<Map<String, Object>>());
			map.put("afternoon", new ArrayList<Map<String, Object>>());
			map.put("evening", new ArrayList<Map<String, Object>>());
		}
		for (TutorialSchedule ts : tutorialSchedules) {
			int fromMinute = DateUtil.getMinute(ts.getFromTime());
			if (fromMinute / 60 < 12) {
				map.get("morning").add(tutorialScheduleToMap(ts));
			} else if (fromMinute / 60 < 18) {
				map.get("afternoon").add(tutorialScheduleToMap(ts));
			} else {
				map.get("evening").add(tutorialScheduleToMap(ts));
			}
		}
		int maxCount = 0;
		for (Entry<String, List<Map<String, Object>>> entry : map.entrySet()) {
			maxCount = Math.max(maxCount, entry.getValue().size());
		}
		for (Entry<String, List<Map<String, Object>>> entry : map.entrySet()) {
			maxCount = Math.max(maxCount, entry.getValue().size());
			while (entry.getValue().size() < maxCount) {
				entry.getValue().add(null);
			}
		}
		return map;
	}

	/**
	 * tutorial schedule information to map
	 * @param tutorialSchedule
	 * @return
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-10-31
	 */
	private static Map<String, Object> tutorialScheduleToMap(
			TutorialSchedule tutorialSchedule) {
		Map<String, Object> m = new HashMap<String, Object>();
		{
			Tutorial t = tutorialSchedule.getTutorial();
			int fromMinute = DateUtil.getMinute(tutorialSchedule.getFromTime());
			int toMinute = DateUtil.getMinute(tutorialSchedule.getToTime());
			m.put("id", t.getId());
			m.put("name", t.getName());
			m.put("scheduleId", tutorialSchedule.getId());
			m.put("fromMinute", fromMinute);
			m.put("toMinute", toMinute);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mmaaa",
					Locale.ENGLISH);
			m.put("fromTime", sdf.format(tutorialSchedule.getFromTime()));
			m.put("toTime", sdf.format(tutorialSchedule.getToTime()));
			if (t.getType() == Tutorial.TYPE_WORKSHOP) {
				m.put("cost", t.getCost());
				m.put("maxParticipate", t.getMaxParticipate());
			} else {
				m.put("cost", tutorialSchedule.getCost());
				m.put("maxParticipate", tutorialSchedule.getMaxParticipate());
			}
		}
		return m;
	}
}
