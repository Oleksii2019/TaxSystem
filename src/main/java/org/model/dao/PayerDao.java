package org.model.dao;

import org.model.entity.Payer;

public interface PayerDao extends GenericDao<Payer> {
    boolean matchForLoginAndPassword(String login, String Password);
    boolean notMatchForLogin(String login);
    void addNewPayer(Payer payer);
    Long getPayerIdByLogin(String payerLogin);
    Long getOfficerIdByPayerID(String payerLogin);
    boolean isNotComplaintExist(Long payerID, Long officerID);
    void createComplaint(Long payerID, Long officerID);
}
