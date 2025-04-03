package lt.viko.eif.denis.kladijev.marshall.config;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.eif.denis.kladijev.marshall.model.Achievement;
import lt.viko.eif.denis.kladijev.marshall.model.Game;
import lt.viko.eif.denis.kladijev.marshall.model.InventoryItem;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlConfig
{
    @Bean
    public JAXBContext jaxbContext() throws JAXBException
    {
        return JAXBContext.newInstance(Player.class, Game.class, Achievement.class, InventoryItem.class);
    }

    @Bean
    public Marshaller marshaller(JAXBContext context) throws JAXBException
    {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    @Bean
    public Unmarshaller unmarshaller(JAXBContext context) throws JAXBException
    {
        return context.createUnmarshaller();
    }
}
