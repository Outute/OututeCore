package com.edu.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.edu.dao.TutorialDao;
import com.edu.model.Tutorial;
import com.edu.service.TutorialNotFoundException;

import java.util.List;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 *
 * @author Reid
*/
@Repository("tutorialDao")
public class TutorialDaoHibernate extends GenericDaoHibernate<Tutorial, Long> implements TutorialDao {

    /**
     * Constructor that sets the entity to Tutorial.class.
     */
    public TutorialDaoHibernate() {
        super(Tutorial.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Tutorial> getTutorials() {
        return getHibernateTemplate().find("from Tutorial t order by upper(t.name)");
    }

    /**
     * {@inheritDoc}
     */
    public Tutorial saveTutorial(Tutorial tutorial) {
        if (log.isDebugEnabled()) {
            log.debug("tutorial id: " + tutorial.getId());
        }
        getHibernateTemplate().saveOrUpdate(tutorial);
        // necessary to throw a DataIntegrityViolation and catch it in TutorialManager
        getHibernateTemplate().flush();
		return tutorial;
    }

	public List<Tutorial> loadTutorialsByName(String name) throws TutorialNotFoundException {
        List tutorials = getHibernateTemplate().find("from Tutorial where name like ? ", "%" + name + "%");
        if (tutorials == null || tutorials.isEmpty()) {
            throw new TutorialNotFoundException("tutorials '" + name + "' not found...");
        } else {
            return tutorials;
        }
    }
}
