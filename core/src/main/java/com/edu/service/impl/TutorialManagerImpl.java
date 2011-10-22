package com.edu.service.impl;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import com.edu.dao.TimeScheduleDao;
import com.edu.dao.TutorialDao;
import com.edu.model.TimeSchedule;
import com.edu.model.Tutorial;
import com.edu.model.User;
import com.edu.service.TutorialExistsException;
import com.edu.service.TutorialManager;
import com.edu.service.TutorialNotFoundException;
import com.edu.service.TutorialService;
import com.edu.service.UserManager;

import javax.jws.WebService;
import java.util.List;

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
	private TimeScheduleDao timeScheduleDao;

	//private TutorialTutorMappingDao tutorialTutorMappingDao;

	public void setTutorialDao(TutorialDao tutorialDao) {
		this.dao = tutorialDao;
		this.tutorialDao = tutorialDao;
	}

	public void setTimeScheduleDao(TimeScheduleDao timeScheduleDao) {
		this.timeScheduleDao = timeScheduleDao;
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
			tutorialDao.saveTutorial(tutorial);
		} catch (DataIntegrityViolationException e) {
			//e.printStackTrace();
			log.warn(e.getMessage());
			throw new TutorialExistsException("tutorial '" + tutorial.getName()
					+ "' already exists!");
		} catch (JpaSystemException e) { // needed for JPA
			//e.printStackTrace();
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
	public List getTutorials(Tutorial tutorial) {
		return tutorialDao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TimeSchedule> getAllTimeScheduleByTutorialId(Long tutorialId) {
		return timeScheduleDao.getAllTimeScheduleByTutorialId(tutorialId);
	}

	/**
	 * {@inheritDoc}
	 */
	public TimeSchedule getTimeSchedule(Long timeScheduleId) {
		return timeScheduleDao.get(timeScheduleId);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeTimeSchedule(Long timeScheduleId) {
		timeScheduleDao.remove(timeScheduleId);
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveTimeSchedule(TimeSchedule timeSchedule) {
		timeScheduleDao.save(timeSchedule);
	}
}
