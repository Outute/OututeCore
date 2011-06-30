package org.appfuse.service;

import org.appfuse.model.Tutorial;
import org.appfuse.model.User;

import java.util.List;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author Reid
 */
public interface TutorialManager extends GenericManager<Tutorial, Long> {
    /**
     * {@inheritDoc}
     */
    List<Tutorial> getTutorials();

    /**
     * {@inheritDoc}
     */
    Tutorial getTutorial(Long tutorialId);

    /**
     * {@inheritDoc}
     */
    void saveTutorial(Tutorial tutorial) throws TutorialExistsException;

    /**
     * {@inheritDoc}
     */
    void removeTutorial(Long tutorialId);

    /**
     * Search a user for search terms.
     * @param searchTerm the search terms.
     * @return a list of matches, or all if no searchTerm.
     */
    List<Tutorial> search(String searchTerm);

    /**
     * {@inheritDoc}
     */
    public User getTutorialByTutorName(String tutorName) throws TutorialNotFoundException;
}
