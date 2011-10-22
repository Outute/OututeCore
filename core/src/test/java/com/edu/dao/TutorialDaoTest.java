package com.edu.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.ExpectedException;

import com.edu.dao.BaseDaoTestCase;
import com.edu.dao.TutorialDao;
import com.edu.model.Tutorial;
import com.edu.model.User;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class TutorialDaoTest extends BaseDaoTestCase {
    @Autowired
    private TutorialDao tutorialDao;

    @Test
    public void testFindTutorialByName() throws Exception {
        List<Tutorial> tutorial = tutorialDao.loadTutorialsByName("tu");
        assertTrue(tutorial.size() > 0);
    }

    @Test
    public void testGetTutorials() throws Exception {
        List<Tutorial> tutorial = tutorialDao.getAll();
        assertTrue(tutorial.size() > 0);
    }

    @Test
    @ExpectedException(DataAccessException.class)
    public void testsaveTutorial() throws Exception {
      Tutorial tutorial = new Tutorial();
      tutorial.setId(new Long(0));
      tutorial.setDescription("Tutorial test 2");
      tutorial.setType(1);
      tutorial.setCategory(1);
      tutorial.setCost(30);
      tutorial.setMethod(2);
      tutorial.setEnabled(true);
      tutorial.setTutorialLocked(false);
      tutorial.setTutorialExpired(false);
      tutorial.setSchedule(new Date());
      tutorial.setCreateTime(new Date());
      tutorial.setModifyTime(new Date());
      tutorial.setLengthInMins(45);
      tutorial.setOpenDays(5);
      tutorial.setVersion(0);
      Set<User> tutors = new HashSet();
      User user = new User();
      tutors.add(user);
      //tutorial.setTutors(tutors);
      //tutorial.setAdmins(tutors);

      tutorial = tutorialDao.saveTutorial(tutorial);
      flush();

      tutorial = tutorialDao.get(tutorial.getId());

      assertEquals("test tutorial 2", tutorial.getName());

      log.debug("removing tutorial...");

      tutorialDao.remove(tutorial.getId());
      flush();
    }
}
