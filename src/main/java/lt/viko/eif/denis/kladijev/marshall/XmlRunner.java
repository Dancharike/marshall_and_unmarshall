/*
package lt.viko.eif.denis.kladijev.marshall;

import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.service.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lt.viko.eif.denis.kladijev.marshall.service.XmlService;

/**
 * This is testing class for XML
 * It is not included in application working
 * It just created for finding out how does system work at a specific time
 */
/*
@Component
public class XmlRunner implements CommandLineRunner
{
    private final XmlService xmlService;
    private final PlayerService playerService;

    public XmlRunner(XmlService xmlService, PlayerService playerService)
    {
        this.xmlService = xmlService;
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        Player player = playerService.GetAllPlayers().stream().findFirst().orElseThrow(() -> new RuntimeException("No player found in Database"));
        String filePath = "src/main/lt/viko/eif/denis/kladijev/marshall/exports/players.xml";

        xmlService.SavePlayerToXml(player, filePath);
        System.out.println("XML file successfully created: " + filePath);
    }
}
*/