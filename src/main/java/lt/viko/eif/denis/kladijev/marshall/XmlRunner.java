package lt.viko.eif.denis.kladijev.marshall;

import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lt.viko.eif.denis.kladijev.marshall.service.XmlService;

/**
 * This is testing class for XML
 * It is not included in application working
 * It just created for finding out how does system work at a specific time
 */

@Component
public class XmlRunner implements CommandLineRunner
{
    private final XmlService xmlService;

    public XmlRunner(XmlService xmlService)
    {
        this.xmlService = xmlService;
    }

    @Override
    public void run(String... args)
    {
        Player player = new Player();
        player.setNickName("Denis");

        String filePath = "player.xml";

        xmlService.SavePlayerToXml(player, filePath);
        Player loadedPlayer = xmlService.LoadPlayerFromXml(filePath);
        System.out.println("Loaded player: " + loadedPlayer);
    }
}
