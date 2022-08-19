import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class SearchObjectFactory
{
    public SearchObject getSearchObject()
    {
        String resourceName = "searchQuery.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            assert resourceStream != null;
            props.load(resourceStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        SearchObject searchObject = new SearchObject();
        searchObject.setHCPREPB(Arrays.asList(props.get("query.hcprepb").toString().split(",")));
        searchObject.setAccounts(Arrays.asList(props.get("query.accounts").toString().split(",")));
        searchObject.setCountry(props.get("query.country").toString());
        searchObject.setOrderGroups(Arrays.asList(props.get("query.ordergroups").toString().split(",")));
        searchObject.setCoorderGroups(Arrays.asList(props.get("query.coordergroups").toString().split(",")));
        searchObject.setScenario(props.get("query.scenario").toString());
        searchObject.setYear(props.get("query.year").toString());
        return searchObject;
    }
}
