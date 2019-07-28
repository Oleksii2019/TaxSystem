package org.model.dao;

import org.model.entity.Officer;

public interface OfficerDao  extends GenericDao<Officer> {
    boolean matchForLoginAndPassword(String login, String Password);
    Long getOfficerIdByLogin(String officerLogin);
}
