package lt.viko.eif.denis.kladijev.marshall.utility;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.service.PlayerService;

public class PlayerAdapter extends XmlAdapter<String, Player>
{
    @Override
    public Player unmarshal(String idStr) throws Exception
    {
        if(idStr == null || idStr.isEmpty())
        {
            return null;
        }
        Long id = Long.valueOf(idStr);
        PlayerService playerService = ApplicationContextProvider.getBean(PlayerService.class);
        return playerService.getById(id).orElse(null);
    }

    @Override
    public String marshal(Player player) throws Exception
    {
        return (player != null && player.getId() != null) ? player.getId().toString() : null;
    }
}
