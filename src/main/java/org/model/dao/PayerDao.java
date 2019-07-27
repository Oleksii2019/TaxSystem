package org.model.dao;

import org.model.entity.Payer;

public interface PayerDao extends GenericDao<Payer> {
    boolean matchForLoginAndPassword(String login, String Password);
    boolean notMatchForLogin(String login);
    void addNewPayer(Payer payer);
    Long getPayerIdByLogin(String payerLogin);
}
