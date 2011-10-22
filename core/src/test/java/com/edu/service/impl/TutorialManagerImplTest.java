package com.edu.service.impl;

import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.edu.dao.TutorialDao;
import com.edu.model.Tutorial;
import com.edu.service.impl.BaseManagerMockTestCase;
import com.edu.service.impl.TutorialManagerImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TutorialManagerImplTest extends BaseManagerMockTestCase {
    private TutorialManagerImpl manager = null;
    private TutorialDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(TutorialDao.class);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetTutorial() {
        log.debug("testing get...");

        final Long id = 7L;
        final Tutorial tutorial = new Tutorial();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(tutorial));
        }});

        Tutorial result = manager.get(id);
        assertSame(tutorial, result);
    }

    @Test
    public void testGetTutorials() {
        log.debug("testing getAll...");

        final List tutorials = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(tutorials));
        }});

        List result = manager.getAll();

        assertSame(tutorials, result);
    }

    @Test
    public void testSaveTutorial() {
        log.debug("testing save...");

        final Tutorial tutorial = new Tutorial();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(tutorial)));
        }});

        manager.save(tutorial);
    }

    @Test
    public void testRemoveTutorial() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}