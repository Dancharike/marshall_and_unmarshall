package lt.viko.eif.denis.kladijev.marshall.service;

import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Service
public class XmlService
{
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    @Autowired
    public XmlService(Marshaller marshaller, Unmarshaller unmarshaller)
    {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    public void savePlayerToXml(Player player, String filePath)
    {
        try (FileOutputStream fos = new FileOutputStream(filePath))
        {
            marshaller.marshal(player, new StreamResult(fos));
            System.out.println("Player saved to XML: " + filePath);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error saving player to XML", e);
        }
    }

    public Player loadPlayerFromXml(String filePath)
    {
        try (FileInputStream fis = new FileInputStream(filePath))
        {
            Player player = (Player) unmarshaller.unmarshal(new StreamSource(fis));
            System.out.println("Player loaded from XML: " + player);
            return player;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error loading player from XML", e);
        }
    }
}
