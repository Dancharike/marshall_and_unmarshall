package lt.viko.eif.denis.kladijev.marshall.service;

import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link XmlService} class.
 */
@SpringBootTest(classes = {XmlService.class, lt.viko.eif.denis.kladijev.marshall.config.TestXmlConfig.class})
public class XmlServiceTest
{
    @Autowired private XmlService xmlService;

    /**
     * Tests that {@link XmlService#savePlayerToXml(Player, String)} correctly marshals a Player object
     * to an XML file. The test writes the XML output to a temporary file and then reads the file back
     * to verify its content.
     * @throws IOException if an I/O error occurs while reading or writing the temporary file.
     */
    @Test
    public void testSavePlayerToXml() throws IOException
    {
        // create a sample Player object
        // create a temporary file to hold the XML output
        Player player = new Player("TestPlayer", "test@example.com", 1, 100);
        File tempFile = File.createTempFile("testPlayer", ".xml");
        try
        {
            // Save the player object to XML
            // Read the file content as a String
            // Assert that the XML content contains the expected Player element
            xmlService.savePlayerToXml(player, tempFile.getAbsolutePath());
            String xmlContent = Files.readString(tempFile.toPath(), StandardCharsets.UTF_8);
            assertThat(xmlContent).contains("<Player");
        }
        finally
        {
            tempFile.delete();
        }
    }

    /**
     * Tests that {@link XmlService#loadPlayerFromXml(String)} correctly unmarshals a Player object
     * from an XML file. The test writes a sample XML string to a temporary file, loads the Player from it,
     * and then verifies the fields.
     * @throws IOException if an I/O error occurs while reading or writing the temporary file.
     */
    @Test
    public void testLoadPlayerFromXml() throws IOException
    {
        // create a sample XML string representing a Player
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<Player id=\"1\">\n" +
                "    <nickName>TestPlayer</nickName>\n" +
                "    <email>test@example.com</email>\n" +
                "    <level>1</level>\n" +
                "    <experience>100</experience>\n" +
                "    <Games/>\n" +
                "    <Achievements/>\n" +
                "    <Inventory/>\n" +
                "    <totalAchievements>0</totalAchievements>\n" +
                "    <totalInventoryItems>0</totalInventoryItems>\n" +
                "</Player>";
        // create a temporary file and write the XML content to it
        File tempFile = File.createTempFile("testPlayer", ".xml");
        Files.writeString(tempFile.toPath(), xml, StandardCharsets.UTF_8);
        try
        {
            // load the player object from the temporary XML file
            // verify that the loaded player is not null and contains the expected data
            Player loadedPlayer = xmlService.loadPlayerFromXml(tempFile.getAbsolutePath());
            assertThat(loadedPlayer).isNotNull();
            assertThat(loadedPlayer.getNickName()).isEqualTo("TestPlayer");
            assertThat(loadedPlayer.getEmail()).isEqualTo("test@example.com");
        }
        finally
        {
            tempFile.delete();
        }
    }
}
