package lt.viko.eif.denis.kladijev.marshall.utility;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LongToStringAdapter extends XmlAdapter<String, Long>
{
    @Override
    public Long unmarshal(String v) throws Exception
    {
        return (v == null || v.isEmpty()) ? null : Long.valueOf(v);
    }

    @Override
    public String marshal(Long v) throws Exception
    {
        return v == null ? null : v.toString();
    }
}
