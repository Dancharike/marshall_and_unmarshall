package lt.viko.eif.denis.kladijev.marshall.service;

import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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

    public void SavePlayerToXml(Player player, String filePath)
    {
        try(FileOutputStream fos = new FileOutputStream(filePath))
        {
            marshaller.marshal(player, new StreamResult(fos));
            System.out.println("Saved player to XML: " + filePath);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error saving player to XML", e);
        }
    }

    public Player LoadPlayerFromXml(String filePath)
    {
        try(FileInputStream fis = new FileInputStream(filePath))
        {
            return (Player) unmarshaller.unmarshal(new StreamSource(fis));
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error loading player from XML", e);
        }
    }
}
