package lt.viko.eif.denis.kladijev.marshall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.service.PlayerService;
import lt.viko.eif.denis.kladijev.marshall.service.XmlService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {@link XmlController}.
 */
@WebMvcTest(XmlController.class)
public class XmlControllerTest
{
    @Autowired private MockMvc mockMvc;
    @MockBean private XmlService xmlService;
    @MockBean private PlayerService playerService;
    @Autowired private ObjectMapper objectMapper;

    /**
     * Tests the export of a Player as XML.
     * <p>
     * This test simulates the behavior of {@link XmlService#savePlayerToXml(Player, String)}
     * by writing a sample XML content to the file specified by the passed file path.
     * Then it verifies that the response contains the expected XML content.
     * </p>
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testExportPlayer() throws Exception
    {
        // create a sample Player object
        Player player = new Player("Denis", "denis@example.com", 5, 1000);
        player.setId(1L);
        Mockito.when(playerService.getById(1L)).thenReturn(Optional.of(player));

        // define sample XML content
        String sampleXml = "<?xml version=\"1.0\"?><Player>Sample</Player>";

        // intercept the savePlayerToXml call and simulate writing sample XML to the given file path
        Mockito.doAnswer(invocation -> {
            String filePath = invocation.getArgument(1);
            Files.write(new File(filePath).toPath(), sampleXml.getBytes(StandardCharsets.UTF_8));
            return null;
        }).when(xmlService).savePlayerToXml(Mockito.eq(player), Mockito.anyString());

        mockMvc.perform(get("/api/xml/export/player/1")).andExpect(status().isOk()).andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, containsString("filename=player-1.xml"))).andExpect(content().string(containsString("Sample")));
    }

    /**
     * Tests the import of a Player from an XML file.
     * <p>
     * This test uses a {@link org.springframework.mock.web.MockMultipartFile} to simulate an uploaded XML file.
     * The XML content is parsed by the {@link XmlService#loadPlayerFromXml(String)} method and then saved
     * via the {@link PlayerService#save(Player)} method. The test verifies that the JSON response contains
     * the expected player's nickname.
     * </p>
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testImportPlayer() throws Exception
    {
        String xmlContent = "<?xml version=\"1.0\"?><Player id=\"1\"><nickName>Denis</nickName><email>denis@example.com</email><level>5</level><experience>1000</experience></Player>";
        // create a mock multipart file.
        MockMultipartFile file = new MockMultipartFile("file", "player.xml", "text/xml", xmlContent.getBytes(StandardCharsets.UTF_8));

        Player player = new Player("Denis", "denis@example.com", 5, 1000);
        player.setId(1L);
        Mockito.when(xmlService.loadPlayerFromXml(anyString())).thenReturn(player);
        Mockito.when(playerService.save(Mockito.any(Player.class))).thenReturn(player);

        mockMvc.perform(multipart("/api/xml/import/player").file(file)).andExpect(status().isCreated()).andExpect(jsonPath("$.nickName", containsString("Denis")));
    }
}
