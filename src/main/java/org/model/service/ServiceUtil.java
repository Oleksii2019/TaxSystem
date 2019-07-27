package org.model.service;

import org.model.UserRole;
import org.model.entity.Payer;

import static org.Constants.*;

public class ServiceUtil {

    public static Payer createPayerWithForm(String login, String name,
                                            String password) {
        return Payer.builder().setLogin(login)
                    .setName(name)
                    .setPassword(password)
                    .setRole(UserRole.PAYER.ordinal())
                    .setOfficerID(DEFAULT_OFFICER_FOR_PAYER)
                    .build();
    }
}
