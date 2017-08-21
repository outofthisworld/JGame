package game;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by SeventhSense on 8/15/2017.
 */
public class Client {

    public static final Logger log = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) throws URISyntaxException {
        try {
            System.getProperties().load(new FileInputStream(Client.class.getResource("../res/gameconfig.properties").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(System.getProperty("debug"));
        if (System.getenv("debug") == null && System.getProperty("debug") == null)
            LogManager.getLogManager().reset();

        log.log(Level.INFO, "Logging on : {0}", true);

        Game.get();
    }
}
