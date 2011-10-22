package com.edu.dao;

import org.appfuse.dao.GenericDao;
import org.springframework.transaction.annotation.Transactional;

import com.edu.model.Tutorial;
import com.edu.service.TutorialNotFoundException;

import java.util.List;

/**
 * User Data Access Object (GenericDao) interface.
 *
 * @author Reid
 */
public interface TutorialDao extends GenericDao<Tutorial, Long> {

    /**
     * Gets tutorials based on name.
     * @param tutorname the tutor's name
     * @return List list of tutorials
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when user not
     * found in database
     */
    @Transactional
    List<Tutorial> loadTutorialsByName(String name) throws TutorialNotFoundException;

    /**
     * Gets a list of tutorials ordered by the uppercase version of their name.
     *
     * @return List list of tutorials
     */
    List<Tutorial> getTutorials();

    /**
     * Saves a tutorial.
     * @param tutorial the object to be saved
     * @return the persisted Tutorial object
     */
    Tutorial saveTutorial(Tutorial tutorial);
}
