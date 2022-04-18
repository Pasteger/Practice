package sample.database;

public class Request {
    private String number;
    private String dateCreate;
    private String abonentNumber;
    private String personalAccount;
    private String service;
    private String serviceStatus;
    private String serviceGenus;
    private String serviceType;
    private String equipmentsType;
    private String problemType;
    private String problemDescription;
    private String dateClose;

    public Request(
            String number, String dateCreate, String abonentNumber, String personalAccount, String service,
            String serviceStatus, String serviceGenus, String serviceType, String equipmentsType,
            String problemType, String problemDescription, String dateClose) {

        this.number = number;
        this.dateCreate = dateCreate;
        this.abonentNumber = abonentNumber;
        this.personalAccount = personalAccount;
        this.service = service;
        this.serviceStatus = serviceStatus;
        this.serviceGenus = serviceGenus;
        this.serviceType = serviceType;
        this.equipmentsType = equipmentsType;
        this.problemType = problemType;
        this.problemDescription = problemDescription;
        this.dateClose = dateClose;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getAbonentNumber() {
        return abonentNumber;
    }

    public void setAbonentNumber(String abonentNumber) {
        this.abonentNumber = abonentNumber;
    }

    public String getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(String personalAccount) {
        this.personalAccount = personalAccount;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getServiceGenus() {
        return serviceGenus;
    }

    public void setServiceGenus(String serviceGenus) {
        this.serviceGenus = serviceGenus;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getEquipmentsType() {
        return equipmentsType;
    }

    public void setEquipmentsType(String equipmentsType) {
        this.equipmentsType = equipmentsType;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getDateClose() {
        return dateClose;
    }

    public void setDateClose(String dateClose) {
        this.dateClose = dateClose;
    }
}
