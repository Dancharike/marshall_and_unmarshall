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

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

@Configuration
public class XmlConfig
{
    @Bean
    public JAXBContext jaxbContext() throws JAXBException
    {
        return JAXBContext.newInstance(Player.class, Game.class, Achievement.class, InventoryItem.class);
    }

    @Bean
    public Schema xmlSchema() throws Exception
    {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return factory.newSchema(new File("src/main/resources/schema.xsd"));
    }

    @Bean
    public Marshaller marshaller(JAXBContext context, Schema xmlSchema) throws JAXBException
    {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setSchema(xmlSchema);
        return marshaller;
    }

    @Bean
    public Unmarshaller unmarshaller(JAXBContext context, Schema xmlSchema) throws JAXBException
    {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(xmlSchema);
        return unmarshaller;
    }
}
