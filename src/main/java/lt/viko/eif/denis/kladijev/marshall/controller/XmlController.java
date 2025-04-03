package lt.viko.eif.denis.kladijev.marshall.controller;

import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lt.viko.eif.denis.kladijev.marshall.service.XmlService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/xml")
public class XmlController
{
    private final XmlService xmlService;
    private final PlayerService playerService;

    @Autowired
    public XmlController(XmlService xmlService, PlayerService playerService)
    {
        this.xmlService = xmlService;
        this.playerService = playerService;
    }

    /**
     * Exports a Player by ID to XML file and returns it as attachment.
     */
    @GetMapping("/export/player/{id}")
    public ResponseEntity<byte[]> exportPlayer(@PathVariable Long id)
    {
        var playerOptional = playerService.getById(id);

        if (playerOptional.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        try
        {
            File tempFile = File.createTempFile("player-" + id, ".xml");
            xmlService.savePlayerToXml(playerOptional.get(), tempFile.getAbsolutePath());

            byte[] fileContent = Files.readAllBytes(tempFile.toPath());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=player-" + id + ".xml");

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        }
        catch (IOException e)
        {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Imports a Player from an uploaded XML file.
     */
    @PostMapping("/import/player")
    public ResponseEntity<Player> importPlayer(@RequestParam("file") MultipartFile file)
    {
        try
        {
            File tempFile = File.createTempFile("import-player-", ".xml");
            file.transferTo(tempFile);

            Player player = xmlService.loadPlayerFromXml(tempFile.getAbsolutePath());
            Player savedPlayer = playerService.save(player);

            return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
        }
        catch (IOException e)
        {
            return ResponseEntity.internalServerError().build();
        }
    }
}
