package lt.viko.eif.denis.kladijev.marshall.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link LongToStringAdapter}.
 */
public class LongToStringAdapterTest
{
    private final LongToStringAdapter adapter = new LongToStringAdapter();

    /**
     * Tests that marshalling a valid Long value returns its correct string representation.
     * @throws Exception if marshalling fails.
     */
    @Test
    public void testMarshal_ValidLong() throws Exception
    {
        Long value = 12345L;
        String expected = "12345";
        String actual = adapter.marshal(value);
        assertEquals(expected, actual, "Marshalling should convert Long to its string representation");
    }

    /**
     * Tests that unmarshalling a valid string returns the corresponding Long value.
     * @throws Exception if unmarshalling fails.
     */
    @Test
    public void testUnmarshal_ValidString() throws Exception
    {
        String valueStr = "12345";
        Long expected = 12345L;
        Long actual = adapter.unmarshal(valueStr);
        assertEquals(expected, actual, "Unmarshalling should convert string to Long");
    }

    /**
     * Tests that marshalling a null value returns null.
     * @throws Exception if marshalling fails.
     */
    @Test
    public void testMarshal_Null() throws Exception
    {
        assertNull(adapter.marshal(null), "Marshalling null should return null");
    }

    /**
     * Tests that unmarshalling a null value returns null.
     * @throws Exception if unmarshalling fails.
     */
    @Test
    public void testUnmarshal_Null() throws Exception
    {
        assertNull(adapter.unmarshal(null), "Unmarshalling null should return null");
    }

    /**
     * Tests that unmarshalling an empty string returns null.
     * @throws Exception if unmarshalling fails.
     */
    @Test
    public void testUnmarshal_EmptyString() throws Exception
    {
        assertNull(adapter.unmarshal(""), "Unmarshalling empty string should return null");
    }
}
