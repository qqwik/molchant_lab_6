
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Companies {
    private List companyList;
    private List fieldList;

    public Companies() {
        companyList = new ArrayList<Company>();
        fieldList = new ArrayList<String>();
    }

    public void menu() throws IOException {
        System.out.println("What would you like to do?");
        System.out.println("1.Find company by short title.");
        System.out.println("2.Search branch.");
        System.out.println("3.Search activity.");
        System.out.println("4.Search date foundation");
        System.out.println("5.Search number of employees.");
        System.out.println("6.Exit.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch (i) {
            case 1:
                findShortTitle();
                break;
            case 2:
                findBranch();
                break;
            case 3:
                findActivity();
                break;
            case 4:
                findDateFoundation();
                break;
            case 5:
                findEmployees();
                break;
            case 6:
                System.exit(0);
                break;

            default:

                throw new IllegalArgumentException("Unacceptable option.");

        }
        menu();
    }

    public void readInput(File fin) throws FileNotFoundException {
        addFields(fin);
        addCompanies(fin);
    }

    public void addFields(File fin) throws FileNotFoundException {
        Scanner sc = new Scanner(fin);
        String s;
        s = sc.nextLine();
        Scanner sc2 = new Scanner(s);
        sc2.useDelimiter(";");
        while (sc2.hasNext()) {
            fieldList.add(sc2.next());
        }
    }

    public void addCompanies(File fin) throws FileNotFoundException {
        Scanner sc = new Scanner(fin);
        String s;
        s = sc.nextLine();
        while (sc.hasNextLine()) {
            s = sc.nextLine();
            Scanner sc2 = new Scanner(s);
            sc2.useDelimiter(";");
            String name = sc2.next();
            String shortTitle = sc2.next();
            String dateUpdate = sc2.next();
            String address = sc2.next();
            String dateFoundation = sc2.next();
            int countEmployees = Integer.parseInt(sc2.next());
            String auditor = sc2.next();
            String phone = sc2.next();
            String email = sc2.next();
            String branch = sc2.next();
            String activity = sc2.next();
            String internetAddress = sc2.next();

            Company c = new Company(name, shortTitle, dateUpdate, address, dateFoundation, countEmployees, auditor, phone, email,
                    branch, activity, internetAddress);
            companyList.add(c);
            sc2.close();
        }
        sc.close();
    }

    public void findShortTitle() throws IOException {
        List shortTitle = new ArrayList<Company>();
        System.out.println("Enter short title of the company");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int count = 0;
        for (int i = 0; i < companyList.size(); i++) {
            Company c = (Company) companyList.get(i);
            if (s.equalsIgnoreCase(c.getShortTitle())) {
                shortTitle.add(c);
                count++;
            }
        }
        if (count != 0) {
            writeXML("shortTitle", shortTitle);
            writeJSON("shortTitle", shortTitle);
            writeLog(s, count);
            System.out.print(count + " results found.");
        }else{System.out.print("No results found.");
            writeLog(s, count);}
    }

    public void findBranch() throws IOException {
        List branch = new ArrayList<Company>();
        System.out.println("Enter branch ");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int count = 0;
        for (int i = 0; i < companyList.size(); i++) {
            Company c = (Company) companyList.get(i);
            if (s.equalsIgnoreCase(c.getBranch())) {
                branch.add(c);
                count++;
            }
        }
        if (count != 0) {
            writeXML("branch", branch);
            writeJSON("branch", branch);
            writeLog(s, count);
            System.out.print(count + " results found.");
        } else {
            System.out.print("No results found.");
            writeLog(s, count);
        }
    }

    public void findActivity() throws IOException {
        List activity = new ArrayList<Company>();
        System.out.println("Enter activity ");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int count = 0;
        for (int i = 0; i < companyList.size(); i++) {
            Company c = (Company) companyList.get(i);
            if (s.equalsIgnoreCase(c.getActivity())) {
                activity.add(c);
                count++;
            }
        }
        if (count != 0) {
            writeXML("activity", activity);
            writeJSON("activity", activity);
            writeLog(s, count);
            System.out.print(count + " results found.");
        } else {
            System.out.print("No results found.");
            writeLog(s, count);
        }
    }

    public void findDateFoundation() throws IOException, IllegalArgumentException {
        List date = new ArrayList<Company>();
        System.out.println("Enter from DD/MM/YYYY");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println("Enter till DD/MM/YYYY");
        String t = sc.nextLine();
        int count = 0;
        for (int i = 0; i < companyList.size(); i++) {
            Company c = (Company) companyList.get(i);
            if (checkDate(s, t, c.getDateFoundation())) {
                date.add(c);
                count++;
            }

        }
        if (count != 0) {
            writeXML("dateFoundation", date);
            writeJSON("dateFoundation", date);
            writeLog(s + " " + t, count);
            System.out.print(count + " results found.");
        } else {
            System.out.print("No results found.");
            writeLog(s, count);
        }
    }

    public void findEmployees() throws IOException, IllegalArgumentException {
        List employees = new ArrayList<Company>();
        System.out.println("Enter number from ");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println("Enter number till");
        String t = sc.nextLine();
        int count = 0;
        for (int i = 0; i < companyList.size(); i++) {
            Company c = (Company) companyList.get(i);
            if (checkEmployees(s, t, c.getCountEmployees())) {
                employees.add(c);
                count++;
            }
        }
        if (count != 0) {
            writeXML("employees", employees);
            writeJSON("employees", employees);
            writeLog(s + " " + t, count);
            System.out.print(count + " results found.");
        } else {
            System.out.print("No results found.");
            writeLog(s, count);
        }
    }

    private boolean checkDate(String from, String till, String s) throws IllegalArgumentException {
        int dayFrom = Integer.parseInt(from.substring(0, 2));
        int monthFrom = Integer.parseInt(from.substring(3, 5));
        int yearFrom = Integer.parseInt(from.substring(6, 10));
        int dayTill = Integer.parseInt(till.substring(0, 2));
        int monthTill = Integer.parseInt(till.substring(3, 5));
        int yearTill = Integer.parseInt(till.substring(6, 10));
        int day = Integer.parseInt(s.substring(0, 2));
        int month = Integer.parseInt(s.substring(3, 5));
        int year = Integer.parseInt(s.substring(6, 10));
        if (year > yearFrom) {
            if (year < yearTill) {
                return true;
            }
            if (year == yearTill) {
                if (month < monthTill) {
                    return true;
                }
                if (month == monthTill) {
                    if (day <= dayTill) {
                        return true;

                    }
                }
            }
        }
        if (year == yearFrom) {
            if (month > monthFrom) {
                return true;
            }
            if (month == monthFrom) {
                if (day >= dayFrom) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkEmployees(String from, String till, int c) {
        int f = Integer.parseInt(from);
        int t = Integer.parseInt(till);
        if (c >= f && c <= t) {
            return true;
        }
        return false;
    }

    private void writeLog(String s, int count) throws IOException {
        FileWriter fw = new FileWriter("log.txt", true);
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss ");
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
        Date date = new Date();
        fw.write(formatter.format(date));
        fw.write(" Searched for \"");
        fw.write(s);
        fw.write("\"  ");
        if (count == 0) {
            fw.write("No results found.\n");
        } else fw.write(count + " results found.\n");
        fw.close();
    }

    private void writeXML(String s, List<Company> c) throws FileNotFoundException {
        File file = new File(s + ".xml");
        PrintStream ps = new PrintStream(file);
        ps.println("<?xml version=\"1.0\"?>");
        ps.println("<CompanyList>");
        for (int i = 0; i < c.size(); i++) {
            ps.println("\t<Data>");
            writeXMLCompany(ps, c.get(i));
            ps.println("\t</Data>");
        }
        ps.println("</CompanyList>");

    }

    private void writeXMLCompany(PrintStream ps, Company c) {
        ps.println(XMLTag(c.getName(), 0));
        ps.println(XMLTag(c.getShortTitle(), 1));
        ps.println(XMLTag(c.getDateUpdate(), 2));
        ps.println(XMLTag(c.getAddress(), 3));
        ps.println(XMLTag(c.getDateFoundation(), 4));
        ps.println(XMLTag(c.getCountEmployees(), 5));
        ps.println(XMLTag(c.getAuditor(), 6));
        ps.println(XMLTag(c.getPhone(), 7));
        ps.println(XMLTag(c.getEmail(), 8));
        ps.println(XMLTag(c.getBranch(), 9));
        ps.println(XMLTag(c.getActivity(), 10));
        ps.println(XMLTag(c.getInternetAddress(), 11));
    }

    private String XMLTag(String c, int i) {
        StringBuilder s = new StringBuilder("\t\t<");
        s.append(fieldList.get(i));
        s.append(">");
        s.append(c);
        s.append("</");
        s.append(fieldList.get(i));
        s.append(">");
        return s.toString();
    }

    private String XMLTag(int c, int i) {
        StringBuilder s = new StringBuilder("\t\t<");
        s.append(fieldList.get(i));
        s.append(">");
        s.append(c);
        s.append("</");
        s.append(fieldList.get(i));
        s.append(">");
        return s.toString();
    }

    private void writeJSON(String s, List<Company> c) throws IOException {
        File file = new File(s + ".json");
        PrintStream ps = new PrintStream(file);
        ps.println("{");
        ps.println("\t\"CompanyList\": {");
        ps.println("\t\t\"Data\": [");
        for (int i = 0; i < c.size(); i++) {
            writeJSONCompany(ps, c.get(i));
            if (i != c.size() - 1) {
                ps.println(",");
            } else ps.print("\n");
        }
        ps.println("\t\t]");
        ps.println("\t}");
        ps.println("}");

    }

    private void writeJSONCompany(PrintStream ps, Company c) {
        ps.println("\t\t\t{");
        ps.println(JSONTag(c.getName(), 0));
        ps.println(JSONTag(c.getShortTitle(), 1));
        ps.println(JSONTag(c.getDateUpdate(), 2));
        ps.println(JSONTag(c.getAddress(), 3));
        ps.println(JSONTag(c.getDateFoundation(), 4));
        ps.println(JSONTag(c.getCountEmployees(), 5));
        ps.println(JSONTag(c.getAuditor(), 6));
        ps.println(JSONTag(c.getPhone(), 7));
        ps.println(JSONTag(c.getEmail(), 8));
        ps.println(JSONTag(c.getBranch(), 9));
        ps.println(JSONTag(c.getActivity(), 10));
        ps.println(JSONTag(c.getInternetAddress(), 11));
        ps.print("\t\t\t}");
    }

    private String JSONTag(String c, int i) {
        StringBuilder s = new StringBuilder("\t\t\t\t\"");
        s.append(fieldList.get(i));
        s.append("\": \"");
        s.append(c);
        if (i != 11) {
            s.append("\",");
        } else s.append("\"");
        return s.toString();
    }

    private String JSONTag(int c, int i) {
        StringBuilder s = new StringBuilder("\t\t\t\t\"");
        s.append(fieldList.get(i));
        s.append("\": \"");
        s.append(c);
        if (i != 11) {
            s.append("\",");
        } else s.append("\"");
        return s.toString();
    }
}
