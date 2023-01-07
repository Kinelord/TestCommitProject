package com.shakirov.database.repository;

import com.shakirov.dto.PersonalInfoImpl;
import com.shakirov.dto.UserFilter;
import com.shakirov.model.Role;
import com.shakirov.model.User;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter (UserFilter filter);

    List<User> findAllByFilterForQueryDSL(UserFilter filter);

    List<PersonalInfoImpl> findAllByCompanyAndRole (Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);
}
