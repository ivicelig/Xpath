import java.util.List;

public class SearchObject
{
    private String country;
    private String scenario;
    private String year;
    private List<String> HCPREPB;
    private List<String> orderGroups;
    private List<String> coorderGroups;
    private List<String> accounts;

    public SearchObject()
    {
    }

    public List<String> getCoorderGroups()
    {
        return coorderGroups;
    }

    public void setCoorderGroups(List<String> coorderGroups)
    {
        this.coorderGroups = coorderGroups;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getScenario()
    {
        return scenario;
    }

    public void setScenario(String scenario)
    {
        this.scenario = scenario;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public List<String> getHCPREPB()
    {
        return HCPREPB;
    }

    public void setHCPREPB(List<String> HCPREPB)
    {
        this.HCPREPB = HCPREPB;
    }

    public List<String> getOrderGroups()
    {
        return orderGroups;
    }

    public void setOrderGroups(List<String> orderGroups)
    {
        this.orderGroups = orderGroups;
    }

    public List<String> getAccounts()
    {
        return accounts;
    }

    public void setAccounts(List<String> accounts)
    {
        this.accounts = accounts;
    }
}