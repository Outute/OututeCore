package com.edu.service.impl;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import com.edu.dao.TutorialScheduleDao;
import com.edu.dao.TutorialDao;
import com.edu.dao.UserDao;
import com.edu.model.TutorialSchedule;
import com.edu.model.Tutorial;
import com.edu.model.User;
import com.edu.service.TutorialExistsException;
import com.edu.service.TutorialManager;
import com.edu.service.TutorialNotFoundException;
import com.edu.service.TutorialService;
import com.edu.service.UserManager;
import com.edu.util.DateUtil;

import javax.jws.WebService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private UserDao userDao;

	//private TutorialTutorMappingDao tutorialTutorMappingDao;

	public void setTutorialDao(TutorialDao tutorialDao) {
		this.dao = tutorialDao;
		this.tutorialDao = tutorialDao;
	}

	public void setTutorialScheduleDao(TutorialScheduleDao tutorialScheduleDao) {
		this.tutorialScheduleDao = tutorialScheduleDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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
	public List<TutorialSchedule> findTutorialSchedulesByUserId(
			Long tutorialId, Long userId) {
		return tutorialScheduleDao.findTutorialSchedulesByUserId(tutorialId,
				userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findTutorials(Long id, String name, Date start,
			Date end, String tutorName, Integer category, String sortBy) {
		if (id != null) {
			Tutorial t = tutorialDao.get(id);
			List<Tutorial> list = new ArrayList<Tutorial>();
			if (t != null) {
				list.add(t);
			}
			return list;
		}
		return tutorialDao.findTutorials(name, start, end, tutorName, category,
				sortBy);
	}

	/**
	 * {@inheritDoc}
	 */
	public void cancelTutorialSchedule(Long tutorialScheduleId, Long userId) {
		TutorialSchedule tutorialSchedule = tutorialScheduleDao
				.get(tutorialScheduleId);
		if (tutorialSchedule != null) {
			User user = userDao.get(userId);
			if (tutorialSchedule.getStudents().contains(user)) {
				tutorialSchedule.getStudents().remove(user);
				tutorialScheduleDao.save(tutorialSchedule);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void cancelTutorial(Long tutorialId, Long userId) {
		List<TutorialSchedule> list = tutorialScheduleDao
				.getAllTutorialScheduleByTutorialId(tutorialId);
		User user = userDao.get(userId);
		for (TutorialSchedule tutorialSchedule : list) {
			if (tutorialSchedule.getStudents().contains(user)) {
				tutorialSchedule.getStudents().remove(user);
				tutorialScheduleDao.save(tutorialSchedule);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerTutorial(Long tutorialId, Long[] tutorialScheduleIds,
			Long userId) {
		List<TutorialSchedule> list = tutorialScheduleDao
				.getAllTutorialScheduleByTutorialId(tutorialId);
		Set<Long> set = new HashSet<Long>(Arrays.asList(tutorialScheduleIds));
		User user = userDao.get(userId);
		for (TutorialSchedule tutorialSchedule : list) {
			boolean isContains = set.contains(tutorialSchedule.getId());
			if (tutorialSchedule.getStudents().contains(user)) {
				if (!isContains) {
					tutorialSchedule.getStudents().remove(user);
					tutorialScheduleDao.save(tutorialSchedule);
				}
			} else if (isContains) {
				tutorialSchedule.getStudents().add(user);
				tutorialScheduleDao.save(tutorialSchedule);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TutorialSchedule> findTutorialSchedule(Date start, Date end) {
		return tutorialScheduleDao.findTutorialSchedule(start, end);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findCurrentTutorials(int pageSize, int currentPage,
			String name) {
		return tutorialDao.findCurrentTutorials(pageSize, currentPage, name);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findHistoryTutorials(int pageSize, int currentPage,
			String name) {
		return tutorialDao.findHistoryTutorials(pageSize, currentPage, name);
	}
}
