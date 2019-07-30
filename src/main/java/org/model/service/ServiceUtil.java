package org.model.service;

import org.model.UserRole;
import org.model.entity.Payer;
import org.model.entity.Report;

import java.util.List;

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

    public static Long getSelectedReportId(List<Report> report,
                                           String selectedReport) {
        String[] args = selectedReport.split(DATA_TIME_SEPARATOR);
        return report.stream()
                .filter(e->e.getPayerID().equals(Long.valueOf(args[0]))
                        && e.getCreationTime().equals(args[1]))
                .findFirst().get().getId();
    }

}
