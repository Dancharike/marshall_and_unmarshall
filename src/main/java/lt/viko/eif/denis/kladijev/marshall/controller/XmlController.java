package lt.viko.eif.denis.kladijev.marshall.controller;

import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lt.viko.eif.denis.kladijev.marshall.service.XmlService;

@RestController
@RequestMapping("/api/xml")
public class XmlController
{
    private final XmlService xmlService;

    @Autowired
    public XmlController(XmlService xmlService)
    {
        this.xmlService = xmlService;
    }

    @PostMapping("/save")
    public String SavePlayer(@RequestBody Player player)
    {
        xmlService.SavePlayerToXml(player, "player.xml");
        return "Player saved to XML successfully!";
    }

    @GetMapping("/load")
    public Player LoadPlayer()
    {
        return xmlService.LoadPlayerFromXml("player.xml");
    }
}
