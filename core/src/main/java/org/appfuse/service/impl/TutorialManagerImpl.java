package org.appfuse.service.impl;

import org.appfuse.dao.TutorialDao;
import org.appfuse.model.Tutorial;
import org.appfuse.model.User;
import org.appfuse.service.TutorialExistsException;
import org.appfuse.service.TutorialManager;
import org.appfuse.service.TutorialNotFoundException;
import org.appfuse.service.TutorialService;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;


/**
 * Implementation of TutorialManager interface.
 *
 * @author Reid
 */
@Service("tutorialManager")
@WebService(serviceName = "TutorialService", endpointInterface = "org.appfuse.service.TutorialService")
public class TutorialManagerImpl extends GenericManagerImpl<Tutorial, Long> implements TutorialManager, TutorialService {
    private TutorialDao tutorialDao;
    //private TutorialTutorMappingDao tutorialTutorMappingDao;

    @Autowired
    public void setTutorialDao(TutorialDao tutorialDao) {
        this.dao = tutorialDao;
        this.tutorialDao = tutorialDao;
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

        if (tutorial.getVersion() == null) {
            // if new tutorial, lowercase tutorialId
            tutorial.setId(new Long(0));
        }

        try {
            tutorialDao.saveTutorial(tutorial);
        } catch (DataIntegrityViolationException e) {
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new TutorialExistsException("tutorial '" + tutorial.getName() + "' already exists!");
        } catch (JpaSystemException e) { // needed for JPA
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new TutorialExistsException("tutorial '" + tutorial.getName() + "' already exists!");
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
    public User getTutorialByTutorName(String tutorName) throws TutorialNotFoundException {
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
}
