package com.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import com.edu.dao.SystemConfigureDao;
import com.edu.dao.TutorialDao;
import com.edu.dao.TutorialScheduleDao;
import com.edu.dao.TutorialScheduleStudentDao;
import com.edu.dao.UserDao;
import com.edu.model.SystemConfigure;
import com.edu.model.Tutorial;
import com.edu.model.TutorialSchedule;
import com.edu.model.TutorialScheduleStudent;
import com.edu.model.TutorialScheduleStudentKey;
import com.edu.model.User;
import com.edu.service.TutorNotFoundException;
import com.edu.service.TutorialExistsException;
import com.edu.service.TutorialManager;
import com.edu.service.TutorialNotFoundException;
import com.edu.service.TutorialService;
import com.edu.service.UserManager;
import com.edu.util.DateUtil;

/**
 * Implementation of TutorialManager interface.
 *
 * @author Reid
 */
@Service("tutorialManager")
@WebService(serviceName = "TutorialService", endpointInterface = "com.edu.service.TutorialService")
public class TutorialManagerImpl extends GenericManagerImpl<Tutorial, Long>
		implements TutorialManager, TutorialService {
	@Autowired
	private TutorialDao tutorialDao;
	@Autowired
	private TutorialScheduleDao tutorialScheduleDao;
	@Autowired
	private TutorialScheduleStudentDao tutorialScheduleStudentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SystemConfigureDao systemConfigureDao;

	//private TutorialTutorMappingDao tutorialTutorMappingDao;

	public void setTutorialDao(TutorialDao tutorialDao) {
		this.dao = tutorialDao;
		this.tutorialDao = tutorialDao;
	}

	public void setTutorialScheduleDao(TutorialScheduleDao tutorialScheduleDao) {
		this.tutorialScheduleDao = tutorialScheduleDao;
	}

	public void setTutorialScheduleStudentDao(
			TutorialScheduleStudentDao tutorialScheduleStudentDao) {
		this.tutorialScheduleStudentDao = tutorialScheduleStudentDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setSystemConfigureDao(SystemConfigureDao systemConfigureDao) {
		this.systemConfigureDao = systemConfigureDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public Tutorial getTutorial(Long tutorialId) {
		return tutorialDao.get(tutorialId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> getTutorials() {
		return tutorialDao.getAllDistinct();
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveTutorial(Tutorial tutorial) throws TutorialExistsException {
		try {
			if (tutorial.getType() == Tutorial.TYPE_CLASS) {
				tutorial.setCost(0);
				tutorial.setMaxParticipate(0);
			}
			tutorialDao.saveTutorial(tutorial);
		} catch (DataIntegrityViolationException e) {
			log.warn(e.getMessage());
			throw new TutorialExistsException("tutorial '" + tutorial.getName()
					+ "' already exists!");
		} catch (JpaSystemException e) { // needed for JPA
			log.warn(e.getMessage());
			throw new TutorialExistsException("tutorial '" + tutorial.getName()
					+ "' already exists!");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeTutorial(Long tutorialId) {
		log.debug("removing tutorial: " + tutorialId);
		tutorialDao.remove(tutorialId);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param tutorName the login name of the tutor
	 * @return User the populated user object
	 * @throws TutorNotFoundException thrown when tutor name not found
	 */
	public User getTutorialByTutorName(String tutorName)
			throws TutorialNotFoundException {
		UserManager userManager;
		Long userId;
		//userId = userManager.getUserByUsername(tutorName);
		//return tutorialTutorMappingDao.get(userId);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> search(String searchTerm) {
		return super.search(searchTerm, Tutorial.class);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> getTutorials(Tutorial tutorial) {
		return tutorialDao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialSchedule> getAllTutorialScheduleByTutorialId(
			Long tutorialId) {
		return tutorialScheduleDao
				.getAllTutorialScheduleByTutorialId(tutorialId);
	}

	/**
	 * {@inheritDoc}
	 */
	public TutorialSchedule getTutorialSchedule(Long tutorialScheduleId) {
		return tutorialScheduleDao.get(tutorialScheduleId);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeTutorialSchedule(Long tutorialScheduleId) {
		tutorialScheduleDao.remove(tutorialScheduleId);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveTutorialSchedule(TutorialSchedule tutorialSchedule) {
		if (tutorialSchedule.getId() != null) {
			TutorialSchedule ts = tutorialScheduleDao.get(tutorialSchedule
					.getId());
			tutorialSchedule.setFromTime(ts.getFromTime());
			tutorialSchedule.setToTime(ts.getToTime());
		}
		tutorialSchedule.setStartDate(DateUtil.clearTimes(
				tutorialSchedule.getStartDate()).getTime());
		tutorialSchedule.setEndDate(DateUtil.getEndDate(tutorialSchedule
				.getStartDate(), tutorialSchedule.getDurationType(),
				tutorialSchedule.getEndsOccurrence()));
		tutorialScheduleDao.save(tutorialSchedule);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findTutorialsByUserId(Long userId) {
		return tutorialDao.findTutorialsByUserId(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findAvailableTutorialsByTutorId(Long tutorId) {
		return tutorialDao.findAvailableTutorialsByTutorId(tutorId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialScheduleStudent> findTutorialSchedulesByUserId(
			Long tutorialId, Long userId) {
		return tutorialScheduleStudentDao.findTutorialSchedulesByUserId(
				tutorialId, userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialScheduleStudent> findTutorialSchedulesByStudentIdAndDate(
			Long studentId, Date start, Date end) {
		return tutorialScheduleStudentDao
				.findTutorialSchedulesByStudentIdAndDate(studentId, start, end);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findTutorials(Long id, String name, Date start,
			Date end, String tutorName, Integer category, String sortBy,
			boolean existsSchedules) {
		if (id != null) {
			Tutorial t = tutorialDao.get(id);
			List<Tutorial> list = new ArrayList<Tutorial>();
			if (t != null) {
				list.add(t);
			}
			return list;
		}
		return tutorialDao.findTutorials(name, start, end, tutorName, category,
				sortBy, existsSchedules);
	}

	/**
	 * {@inheritDoc}
	 */
	public void cancelTutorialSchedule(Long tutorialScheduleId, Long userId,
			Date date) {
		TutorialSchedule tutorialSchedule = tutorialScheduleDao
				.get(tutorialScheduleId);
		if (tutorialSchedule.getTutorial().getType() == Tutorial.TYPE_WORKSHOP) {
			tutorialScheduleStudentDao.remove(tutorialScheduleId, userId, date);
		} else if (tutorialSchedule.getTutorial().getType() == Tutorial.TYPE_CLASS
				&& date != null) {
			tutorialScheduleStudentDao
					.remove(tutorialScheduleId, userId, DateUtil.changeToDate(
							tutorialSchedule.getFromTime(), date));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void cancelTutorial(Long tutorialId, Long userId) {
		tutorialScheduleStudentDao.remove(tutorialId, userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerTutorial(Long tutorialId,
			TutorialScheduleStudentKey[] ids) {
		if (ids == null || ids.length == 0 || tutorialId == null) {
			return;
		}
		for (TutorialScheduleStudentKey id : ids) {
			if (tutorialScheduleStudentDao.exists(id)) {
				continue;
			}
			TutorialSchedule ts = tutorialScheduleDao.get(id
					.getTutorialScheduleId());
			Tutorial tutorial = ts.getTutorial();
			if (!tutorial.getId().equals(tutorialId)) {
				continue;
			}
			{
				int maxParticipate = 0;
				if (tutorial.getType() == Tutorial.TYPE_WORKSHOP) {
					maxParticipate = tutorial.getMaxParticipate();
				} else {
					maxParticipate = ts.getMaxParticipate();
				}
				int countParticipate = tutorialScheduleStudentDao
						.countParticipate(ts.getId(),id.getLectureDate());
				if (countParticipate >= maxParticipate) {
					throw new RuntimeException("Tutorial.maxParticipate.error");
				}
			}
			if (tutorial.getType() == Tutorial.TYPE_WORKSHOP) {
				tutorialScheduleStudentDao.save(new TutorialScheduleStudent(id,
						ts.getFromTime()));
			} else if (tutorial.getType() == Tutorial.TYPE_CLASS) {
				Date changeToDate = DateUtil.changeToDate(ts.getFromTime(), id
						.getLectureDate());
				tutorialScheduleStudentDao.save(new TutorialScheduleStudent(id,
						changeToDate));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialSchedule> findStudentTutorialSchedule(Long userId,
			Date start, Date end) {
		if (userId != null) {
			List<TutorialSchedule> list = new ArrayList<TutorialSchedule>();
			List<TutorialScheduleStudent> find = tutorialScheduleStudentDao
					.findTutorialSchedules(userId, start, end);
			for (TutorialScheduleStudent tss : find) {
				TutorialSchedule clone = tss.getTutorialSchedule().clone();
				clone.setFromTime(tss.getLectureTime());
				clone.setToTime(DateUtil.changeToDate(clone.getToTime(), tss
						.getLectureTime()));
				list.add(clone);
			}
			return list;
		}
		return tutorialScheduleDao.findStudentTutorialSchedule(start, end,
				userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialSchedule> findTutorTutorialSchedule(Long tutorId,
			Date start, Date end) {
		return tutorialScheduleDao.findTutorTutorialSchedule(start, end,
				tutorId);
	}
	/**
	 * {@inheritDoc}
	 */
	public List<TutorialSchedule> findTutorAndStudentTutorialSchedule(Long tutorId,
			Date start, Date end) {
		return tutorialScheduleDao.findTutorAndStudentTutorialSchedule(start, end,
				tutorId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findCurrentTutorials(int pageSize, int currentPage,
			String name, Long userId) {
		return tutorialDao.findCurrentTutorials(pageSize, currentPage, name,
				userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findHistoryTutorials(int pageSize, int currentPage,
			String name, Long userId) {
		return tutorialDao.findHistoryTutorials(pageSize, currentPage, name,
				userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean canHasMoreParticipate(Long tutorialScheduleId) {
		return tutorialScheduleStudentDao
				.canHasMoreParticipate(tutorialScheduleId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialScheduleStudent> findNeedToNotificationByUserId(
			Long userId) {
		return tutorialScheduleStudentDao
				.findNeedToNotificationByUserId(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public int getNotificationMinute() {
		SystemConfigure cfg = systemConfigureDao
				.get(SystemConfigure.NOTIFICATION_DUETIME);
		Integer minute = cfg == null ? SystemConfigure.NOTIFICATION_MINUTE_DEFAULT
				: cfg.getIntegerValue();
		minute = minute == null ? SystemConfigure.NOTIFICATION_MINUTE_DEFAULT
				: minute;
		return minute;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isHasTakenTutorial(Long tutorialId, Long userId) {
		return tutorialDao.isHasTakenTutorial(tutorialId, userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isOwnedOrTakenTutorial(Long tutorialId, Long userId) {
		return tutorialDao.isOwnedOrTakenTutorial(tutorialId, userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isOwnedTutorial(Long tutorialId, Long userId) {
		return tutorialDao.isOwnedTutorial(tutorialId, userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Long> hasTakenTutorialIds(List<Long> tutorialIds, Long userId) {
		return tutorialDao.hasTakenTutorialIds(tutorialIds, userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Long> ownedOrTakenTutorialIds(List<Long> tutorialIds,
			Long userId) {
		return tutorialDao.ownedOrTakenTutorialIds(tutorialIds, userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Long> ownedTutorialIds(List<Long> tutorialIds, Long userId) {
		return tutorialDao.ownedTutorialIds(tutorialIds, userId);
	}
}
