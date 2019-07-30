package org;

public enum TableOfURI {
    ROOI("/", "index.jsp", true, false, false),
    HOME( "/tax_system/home", "/WEB-INF/jsp/home.jsp", true, false, false),
    NPERSON_LOGIN("/tax_system/person-login","/WEB-INF/jsp/payer/nperson_login_form.jsp", true, false, false),
    NPERSON_LOGIN_COMMAND("/tax_system/person-login/login","/WEB-INF/jsp/payer/nperson_login_form.jsp", true, false, false),
    JPERSON_LOGIN("/tax_system/juridical-person-login","/WEB-INF/jsp/officer/officer_login_form.jsp", true, false, false),
    JPERSON_LOGIN_COMMAND("/tax_system/juridical-person-login/login","/WEB-INF/jsp/officer/officer_login_form.jsp", true, false, false),
    OFFICER_LOGIN("/tax_system/officer-login","/WEB-INF/jsp/officer/officer_login_form.jsp", true, false, false),
    OFFICER_LOGIN_COMMAND("/tax_system/officer-login/login","/WEB-INF/jsp/officer/officer_login_form.jsp", true, false, false),
    REGISTRATION("/tax_system/payer-registration","/WEB-INF/jsp/payer/reg_form.jsp", true, false, false),
    PAYER_REPORTS("/tax_system/payer-reports","/WEB-INF/jsp/payer/payer_report_list_form.jsp", false, true, false),
    OFFICER_REPORTS("/tax_system/officer-reports","/WEB-INF/jsp/officer/officer_report_list_form.jsp", false, false, true);

    private TableOfURI(String pagePath, String filePath,
                       Boolean accessForGuest, Boolean accessForPayer,
                       Boolean accessForOfficer) {
        this.pagePath = pagePath;
        this.filePath = filePath;
        this.accessForGuest = accessForGuest;
        this.accessForPayer = accessForPayer;
        this.accessForOfficer = accessForOfficer;
    }

    private String pagePath;
    private String filePath;
    private Boolean accessForGuest;
    private Boolean accessForPayer;
    private Boolean accessForOfficer;

    public String getPagePath() {
        return pagePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public Boolean getAccessForGuest() {
        return accessForGuest;
    }

    public Boolean getAccessForPayer() {
        return accessForPayer;
    }

    public Boolean getAccessForOfficer() {
        return accessForOfficer;
    }

}
