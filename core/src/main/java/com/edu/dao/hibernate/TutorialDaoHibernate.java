package com.edu.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.edu.dao.TutorialDao;
import com.edu.model.Tutorial;
import com.edu.service.TutorialNotFoundException;
import com.edu.util.DateUtil;

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
			String tutorName, Integer category, String sortBy,
			boolean existsSchedules) {
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
			hql.append(" where t.enabled=? ");
			params.add(true);
		}
		if (existsSchedules) {
			hql.append(" and exists elements(t.tutorialSchedules) ");
		}
		{
			hql.append(" and (1=1");
		}
		if (category != null) {
			hql.append(" and t.category=? ");
			params.add(category);
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
		String query = hql.toString().replace("1=1 and", "").replace(
				"and (1=1)", "");
		return getHibernateTemplate().find(query, params.toArray());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findTutorialsByUserId(Long userId) {
		String hql = "select distinct t from TutorialScheduleStudent tss join tss.tutorialSchedule.tutorial t where tss.student.id=? and t.enabled=? order by t.name";
		return getHibernateTemplate().find(hql, userId, true);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findTutorialsByTutorId(Long tutorId) {
		String hql = "select t from Tutorial t join t.tutors tr where tr.id=? and t.enabled=? order by t.name";
		return getHibernateTemplate().find(hql, tutorId, true);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isOwnedTutorial(Long tutorialId, Long userId) {
		StringBuffer hql = new StringBuffer(128);
		{
			hql.append("select count(t.id) from Tutorial t ");
			hql.append(" join t.tutors tt ");
			hql.append(" where t.id=? and tt.id=?");
		}
		String query = hql.toString();
		List<?> list = getHibernateTemplate().find(query, tutorialId, userId);
		return list != null && !list.isEmpty() && list.get(0) != null
				&& ((Number) list.get(0)).longValue() > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isHasTakenTutorial(Long tutorialId, Long userId) {
		StringBuffer hql = new StringBuffer(128);
		{
			hql.append("select count(t.id) from Tutorial t ");
			hql.append(" join t.tutorialSchedules ts ");
			hql.append(" join ts.tutorialScheduleStudents tss ");
			hql.append(" where t.id=? and tss.id.studentId=?");
		}
		String query = hql.toString();
		List<?> list = getHibernateTemplate().find(query, tutorialId, userId);
		return list != null && !list.isEmpty() && list.get(0) != null
				&& ((Number) list.get(0)).longValue() > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isOwnedOrTakenTutorial(Long tutorialId, Long userId) {
		StringBuffer hql = new StringBuffer(128);
		{
			hql.append("select t.id from Tutorial t ");
			hql.append(" join t.tutors tt ");
			hql.append(" left join t.tutorialSchedules ts ");
			hql.append(" left join ts.tutorialScheduleStudents tss ");
			hql.append(" where t.id=? and (tt.id=? or tss.id.studentId=?)");
		}
		String query = hql.toString();
		List<?> list = getHibernateTemplate().find(query, tutorialId, userId);
		return list != null && !list.isEmpty() && list.get(0) != null
				&& ((Number) list.get(0)).longValue() > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findCurrentTutorials(int pageSize, int currentPage,
			String name, Long userId) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(128);
		{
			hql.append("select distinct t from Tutorial t ");
			hql.append(" join t.tutors tt ");
			hql.append(" left join t.tutorialSchedules ts ");
			hql.append(" left join ts.tutorialScheduleStudents tss ");
			hql.append(" where t.enabled=? and ts.endDate>=? ");
			params.add(true);
			params.add(DateUtil.clearTimes(new Date()).getTime());
		}
		if (userId != null) {
			hql.append(" and (tt.id=? or tss.id.studentId=?) ");
			params.add(userId);
			params.add(userId);
		}
		if (name != null) {
			hql.append(" and t.name like ? ");
			params.add("%" + name + "%");
		}
		{
			hql.append(" order by t.name");
		}
		String query = hql.toString();
		return getHibernateTemplate().find(query, params.toArray());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Tutorial> findHistoryTutorials(int pageSize, int currentPage,
			String name, Long userId) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(128);
		{
			hql.append("select distinct t from Tutorial t ");
			hql.append(" join t.tutors tt ");
			hql.append(" left join t.tutorialSchedules ts ");
			hql.append(" left join ts.tutorialScheduleStudents tss where 1=1 ");
		}
		if (userId != null) {
			hql.append(" and (tt.id=? or tss.id.studentId=?) ");
			params.add(userId);
			params.add(userId);
		}
		if (name != null) {
			hql.append(" and t.name like ? ");
			params.add("%" + name + "%");
		}
		{
			hql.append(" and t.id not in (select tt.id from Tutorial tt ");
			hql.append("  join tt.tutorialSchedules ts ");
			hql.append("  where t.enabled=? and ts.endDate>=?");
			hql.append(")");
			hql.append(" order by t.name");
			params.add(true);
			params.add(DateUtil.clearTimes(new Date()).getTime());
		}
		String query = hql.toString().replace("where and", "where");
		return getHibernateTemplate().find(query, params.toArray());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Long> ownedOrTakenTutorialIds(List<Long> tutorialIds,
			Long userId) {
		if (tutorialIds == null || tutorialIds.isEmpty()) {
			return new ArrayList<Long>();
		}
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(128);
		{
			hql.append("select t.id from Tutorial t ");
			hql.append(" join t.tutors tt ");
			hql.append(" left join t.tutorialSchedules ts ");
			hql.append(" left join ts.tutorialScheduleStudents tss ");
			hql.append(" where t.id in (");
			for (int i = tutorialIds.size() - 1; i > -1; i--) {
				hql.append(tutorialIds.get(i)).append(",");
				if (i > 0 && i % 10 == 0) {
					hql.append("\r\n");
				}
			}
			hql.setLength(hql.length() - 1);
			hql.append(")");
		}
		if (userId != null) {
			hql.append(" and (tt.id=? or tss.id.studentId=?) ");
			params.add(userId);
		}
		String query = hql.toString();
		return getHibernateTemplate().find(query, params.toArray());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Long> ownedTutorialIds(List<Long> tutorialIds, Long userId) {
		if (tutorialIds == null || tutorialIds.isEmpty()) {
			return new ArrayList<Long>();
		}
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(128);
		{
			hql.append("select t.id from Tutorial t ");
			hql.append(" join t.tutors tt ");
			hql.append(" where t.id in (");
			for (int i = tutorialIds.size() - 1; i > -1; i--) {
				hql.append(tutorialIds.get(i)).append(",");
				if (i > 0 && i % 10 == 0) {
					hql.append("\r\n");
				}
			}
			hql.setLength(hql.length() - 1);
			hql.append(")");
		}
		if (userId != null) {
			hql.append(" and tt.id=? ");
			params.add(userId);
		}
		String query = hql.toString();
		return getHibernateTemplate().find(query, params.toArray());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Long> hasTakenTutorialIds(List<Long> tutorialIds, Long userId) {
		if (tutorialIds == null || tutorialIds.isEmpty()) {
			return new ArrayList<Long>();
		}
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(128);
		{
			hql.append("select t.id from Tutorial t ");
			hql.append(" join t.tutorialSchedules ts ");
			hql.append(" join ts.tutorialScheduleStudents tss ");
			hql.append(" where t.id in (");
			for (int i = tutorialIds.size() - 1; i > -1; i--) {
				hql.append(tutorialIds.get(i)).append(",");
				if (i > 0 && i % 10 == 0) {
					hql.append("\r\n");
				}
			}
			hql.setLength(hql.length() - 1);
			hql.append(")");
		}
		if (userId != null) {
			hql.append(" and tss.id.studentId=? ");
			params.add(userId);
		}
		String query = hql.toString();
		return getHibernateTemplate().find(query, params.toArray());
	}
}
