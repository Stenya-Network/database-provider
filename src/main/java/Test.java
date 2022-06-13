import net.stenya.nicky.databaseprovider.DatabaseProvider;
import org.bson.Document;

public class Test {

    public static void main(String[] args) {
        DatabaseProvider databaseProvider = new DatabaseProvider();
        databaseProvider.connect();
    }

}
