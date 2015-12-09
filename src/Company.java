public class Company {
    private String name;
    private String shortTitle;
    private String dateUpdate;
    private String address;
    private String dateFoundation;
    private int countEmployees;
    private String auditor;
    private String phone;
    private String email;
    private String branch;
    private String activity;
    private String internetAddress;

    public Company(String name, String shortTitle, String dateUpdate, String address,
                   String dateFoundation, int countEmployees, String auditor, String phone, String email,
                   String branch, String activity, String internetAddress) {
        this.name = new String(name);
        this.shortTitle = new String(shortTitle);
        this.dateUpdate = dateUpdate;
        this.address = new String(address);
        this.dateFoundation = dateFoundation;
        this.countEmployees = countEmployees;
        this.auditor = new String(auditor);
        this.phone = new String(phone);
        this.email = new String(email);
        this.branch = new String(branch);
        this.activity = new String(activity);
        this.internetAddress = new String(internetAddress);
    }

    public String getName() {
        return name;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public String getAddress() {
        return address;
    }

    public String getDateFoundation() {
        return dateFoundation;
    }

    public int getCountEmployees() {
        return countEmployees;
    }

    public String getAuditor() {
        return auditor;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getBranch() {
        return branch;
    }

    public String getActivity() {
        return activity;
    }

    public String getInternetAddress() {
        return internetAddress;
    }

}



