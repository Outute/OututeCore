package com.edu.service.impl;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.RoleDao;
import com.edu.model.Role;
import com.edu.service.RoleManager;

import java.util.List;

/**
 * Implementation of RoleManager interface.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Service("roleManager")
public class RoleManagerImpl extends GenericManagerImpl<Role, Long> implements RoleManager {
    RoleDao roleDao;

    @Autowired
    public RoleManagerImpl(RoleDao roleDao) {
        super(roleDao);
        this.roleDao = roleDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Role> getRoles(Role role) {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public Role getRole(String rolename) {
        return roleDao.getRoleByName(rolename);
    }

    /**
     * {@inheritDoc}
     */
    public Role saveRole(Role role) {
        return dao.save(role);
    }

    /**
     * {@inheritDoc}
     */
    public void removeRole(String rolename) {
        roleDao.removeRole(rolename);
    }
}