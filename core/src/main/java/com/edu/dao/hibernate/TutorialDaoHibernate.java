package com.edu.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.edu.dao.TutorialDao;
import com.edu.model.Tutorial;
import com.edu.service.TutorialNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve User objects.
 *
 * @author Reid
*/
@Repository("tutorialDao")
public class TutorialDaoHibernate extends GenericDaoHibernate<Tutorial, Long>
		implements TutorialDao {

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
		return getHibernateTemplate().find(
				"from Tutorial t order by upper(t.name)");
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

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> loadTutorialsByName(String name)
			throws TutorialNotFoundException {
		List tutorials = getHibernateTemplate().find(
				"from Tutorial where name like ? ", "%" + name + "%");
		return tutorials;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findTutorials(String name, Date start, Date end,
			String tutorName, String sortBy) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(128);
		{
			hql.append("from Tutorial t ");
		}
		if (start != null) {
			hql.append(" join t.tutorialSchedules ts ");
		}
		if (tutorName != null) {
			hql.append(" join t.tutors tr ");
		}
		{
			hql
					.append(" where t.enabled=? and (1=1");
			params.add(true);
		}
		if (name != null) {
			hql.append(" and t.name like ? ");
			params.add("%" + name + "%");
		}
		if (start != null) {
			hql.append(" and ts.endDate>=? ");
			params.add(start);
		}
		if (end != null) {
			hql.append(" and ts.startDate<=? ");
			params.add(end);
		}
		if (tutorName != null) {
			hql.append(" and tr.firstName=? ");
			params.add(tutorName);
		}
		{
			hql.append(") order by t.name");
		}
		String query = hql.toString().replace("1=1 and", "").replace("(1=1)",
				"");
		return getHibernateTemplate().find(query, params.toArray());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findTutorialsByUserId(Long userId) {
		String hql = "select distinct t from Tutorial t join t.tutorialSchedules ts join ts.students s where s.id=? and t.enabled=? order by t.name";
		return getHibernateTemplate().find(hql, userId, true);
	}
}
