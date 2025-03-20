/*
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
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class XmlConfig
{
    @Bean
    public JAXBContext JaxbContext() throws JAXBException
    {
        return JAXBContext.newInstance(Player.class);
    }

    @Bean
    public Marshaller Marshaller(JAXBContext context) throws JAXBException
    {
        return context.createMarshaller();
    }

    @Bean
    public Unmarshaller Unmarshaller(JAXBContext context) throws JAXBException
    {
        return context.createUnmarshaller();
    }
}
*/