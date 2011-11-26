package com.edu.webapp.action;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.MailException;

import com.edu.Constants;
import com.edu.model.Tutorial;
import com.edu.model.TutorialSchedule;
import com.edu.model.TutorialScheduleStudent;
import com.edu.model.TutorialScheduleStudentKey;
import com.edu.model.User;
import com.edu.util.DateUtil;
import com.edu.webapp.util.RequestUtil;
import com.opensymphony.xwork2.Preparable;

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
		Date startDate = tutorialSchedule.getStartDate();
		{
			Calendar c = Calendar.getInstance();
			c.setTime(startDate);
			c.set(Calendar.HOUR_OF_DAY, fromto[0] / 60);
			c.set(Calendar.MINUTE, fromto[0] % 60);
			tutorialSchedule.setFromTime(c.getTime());
		}
		tutorialSchedule.setModifyTime(new Date());
		{
			Calendar c = Calendar.getInstance();
			c.setTime(startDate);
			c.set(Calendar.HOUR_OF_DAY, fromto[1] / 60);
			c.set(Calendar.MINUTE, fromto[1] % 60);
			tutorialSchedule.setToTime(c.getTime());
		}
		if (tutorialSchedule.getId() == null) {
			tutorialSchedule.setTutorial(tutorial);
		}
		{
			User user = getUser();
			List<TutorialSchedule> had = tutorialManager
					.findTutorTutorialSchedule(user.getId(), startDate,
							startDate);
			long newFrom = tutorialSchedule.getFromTime().getTime();
			long newTo = tutorialSchedule.getToTime().getTime();
			boolean hasError = false;
			for (TutorialSchedule ts : had) {
				long tsFrom = DateUtil
						.changeToDate(ts.getFromTime(), startDate).getTime();
				long tsTo = DateUtil.changeToDate(ts.getToTime(), startDate)
						.getTime();
				if (newFrom >= tsFrom && newFrom <= tsTo || newTo >= tsFrom
						&& newTo <= tsTo) {
					super.addActionError("tutorial: name="
							+ ts.getTutorial().getName() + ", id="
							+ ts.getTutorial().getId()
							+ ", tutorial schedule time conflict.");
					hasError = true;
				}
			}
			if (hasError) {
				return SUCCESS;
			}
		}
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
		int totalCost = 0;
		if (user != null) {
			List<TutorialScheduleStudent> find = tutorialManager
					.findTutorialSchedulesByUserId(tutorial.getId(), user
							.getId());
			tutorialSchedules = new ArrayList<TutorialSchedule>();
			for (TutorialScheduleStudent tss : find) {
				TutorialSchedule clone = tss.getTutorialSchedule().clone();
				clone.setFromTime(DateUtil.changeToDate(clone.getFromTime(),
						tss.getId().getLectureDate()));
				clone.setToTime(DateUtil.changeToDate(clone.getToTime(), tss
						.getId().getLectureDate()));
				tutorialSchedules.add(clone);
				if (clone.getTutorial().getType() == Tutorial.TYPE_WORKSHOP) {
					totalCost += clone.getTutorial().getCost();
				} else {
					totalCost += clone.getCost();
				}
			}
		}
		{
			getRequest().setAttribute("totalCost", totalCost);
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
		Integer category = null;
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
		{
			String categoryStr = getRequest().getParameter("search.category");
			try {
				category = Integer.valueOf(categoryStr.trim());
			} catch (Exception e) {
			}
		}
		tutorials = tutorialManager.findTutorials(id, name, start, end,
				tutorName, category, sortBy);
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
		for (TutorialSchedule ts : tutorial.getTutorialSchedules()) {
			if (DateUtil.isInDate(ts.getStartDate(), start, ts
					.getDurationType(), ts.getEndsOccurrence())) {
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
		String dates = getRequest().getParameter("book.dates");
		if (ids == null || dates == null) {
			return SUCCESS;
		}
		String[] idArr = ids.split(",");
		String[] dateArr = dates.split(",");
		if (idArr.length < 1 || idArr.length != dateArr.length) {
			return SUCCESS;
		}
		int totalCost = 0;
		if (tutorial.getType() == Tutorial.TYPE_WORKSHOP) {
			Map<String, Integer> idMap = new HashMap<String, Integer>();
			{
				for (int i = 0; i < idArr.length; i++) {
					idMap.put(idArr[i], i);
				}
			}
			for (TutorialSchedule ts : tutorial.getTutorialSchedules()) {
				if (idMap.containsKey(ts.getId().toString())) {
					try {
						TutorialSchedule clone = ts.clone();
						Date date = DateUtil.convertStringToDate(dateArr[idMap
								.get(clone.getId().toString())]);
						clone.setFromTime(DateUtil.changeToDate(clone
								.getFromTime(), date));
						clone.setToTime(DateUtil.changeToDate(
								clone.getToTime(), date));
						tutorialSchedules.add(clone);
						totalCost += ts.getTutorial().getCost();
					} catch (Exception e) {
					}
				}
			}
		} else if (tutorial.getType() == Tutorial.TYPE_CLASS) {
			Map<Long, TutorialSchedule> tsMap = new HashMap<Long, TutorialSchedule>();
			{
				for (TutorialSchedule ts : tutorial.getTutorialSchedules()) {
					tsMap.put(ts.getId(), ts);
				}
			}
			for (int i = 0; i < idArr.length; i++) {
				try {
					Long tsId = Long.valueOf(idArr[i].trim());
					TutorialSchedule ts = tsMap.get(tsId);
					if (ts == null) {
						continue;
					}
					TutorialSchedule clone = ts.clone();
					Date date = DateUtil.convertStringToDate(dateArr[i]);
					clone.setFromTime(DateUtil.changeToDate(
							clone.getFromTime(), date));
					clone.setToTime(DateUtil.changeToDate(clone.getToTime(),
							date));
					tutorialSchedules.add(clone);
					totalCost += clone.getCost();
				} catch (Exception e) {
				}
			}
		}
		{
			getRequest().setAttribute("totalCost", totalCost);
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
		String dates = getRequest().getParameter("register.dates");
		User user = getUser();
		List<TutorialScheduleStudentKey> list = new ArrayList<TutorialScheduleStudentKey>();
		if (user != null && ids != null && dates != null) {
			String[] idArr = ids.split(",");
			String[] dateArr = dates.split(",");
			for (int i = 0; i < idArr.length; i++) {
				try {
					TutorialScheduleStudentKey key = new TutorialScheduleStudentKey(
							Long.valueOf(idArr[i].trim()), user.getId(),
							DateUtil.convertStringToDate(dateArr[i]));
					list.add(key);
				} catch (Exception e) {
				}
			}
			tutorialManager.registerTutorial(tutorial.getId(), list
					.toArray(new TutorialScheduleStudentKey[list.size()]));
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
		Date date = null;
		try {
			date = DateUtil.convertStringToDate(getRequest().getParameter(
					"scheduleDate"));
		} catch (Exception e) {
		}
		if (user != null && tutorialSchedule != null && date != null) {
			tutorialManager.cancelTutorialSchedule(tutorialSchedule.getId(),
					user.getId(), date);
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
			tutorialManager.cancelTutorial(tutorial.getId(), user.getId());
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
					return SUCCESS;
				}
			}
		}
		tutorialSchedules = tutorialManager.findTutorTutorialSchedule(null,
				start, end);
		getHighLightDate(start);
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
		User user = getUser();
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
		tutorialSchedules = tutorialManager.findStudentTutorialSchedule(
				user == null ? null : user.getId(), start, end);
		getHighLightDate(start);
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
		User user = getUser();
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
		tutorialSchedules = tutorialManager.findStudentTutorialSchedule(
				user == null ? null : user.getId(), start, end);
		getHighLightDate(start);
		return SUCCESS;
	}

	/**
	 * get the highLight dates and set to request
	 * @param start
	 * @author <a href="mailto:iffiff1@hotmail.com">Tyler Chen</a> 
	 * @since 2011-11-27
	 */
	private void getHighLightDate(Date start) {
		User user = getUser();
		Date monthFirst = DateUtil.getMonthFirstDay(start);
		Date monthLast = DateUtil.getMonthLastDay(start);
		List<TutorialScheduleStudent> list = tutorialManager
				.findTutorialSchedulesByStudentIdAndDate(user.getId(),
						monthFirst, monthLast);
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		for (TutorialScheduleStudent tss : list) {
			Date lectureDate = tss.getId().getLectureDate();
			sb.append(sdf.format(lectureDate)).append(",");
		}
		getRequest().setAttribute("highLight", sb.toString());
	}

	public String findCurrentTutorials() {
		getRequest().setAttribute("isCurrent", true);
		String name = null;
		{
			name = getRequest().getParameter("search.name");
			name = name == null ? null : name.trim();
		}
		tutorials = tutorialManager.findCurrentTutorials(25, 0, name);
		return SUCCESS;
	}

	public String findHistoryTutorials() {
		getRequest().setAttribute("isHistory", true);
		String name = null;
		{
			name = getRequest().getParameter("search.name");
			name = name == null ? null : name.trim();
		}
		tutorials = tutorialManager.findHistoryTutorials(25, 0, name);
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
			m.put("tutorial", t);
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
