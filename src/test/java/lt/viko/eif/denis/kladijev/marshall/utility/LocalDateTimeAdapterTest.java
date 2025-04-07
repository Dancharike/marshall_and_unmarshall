package lt.viko.eif.denis.kladijev.marshall.utility;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LocalDateTimeAdapter class.
 */
public class LocalDateTimeAdapterTest
{
    private final LocalDateTimeAdapter adapter = new LocalDateTimeAdapter();

    /**
     * Test that a valid ISO_LOCAL_DATE_TIME the string is correctly converted to LocalDateTime.
     */
    @Test
    public void testUnmarshal_ValidDateString() throws Exception
    {
        String dateStr = "2025-04-07T12:34:56";
        LocalDateTime expected = LocalDateTime.of(2025, 4, 7, 12, 34, 56);
        LocalDateTime actual = adapter.unmarshal(dateStr);
        assertEquals(expected, actual, "Unmarshalling should return the expected LocalDateTime");
    }

    /**
     * Test that корректное LocalDateTime converted to format string ISO_LOCAL_DATE_TIME.
     */
    @Test
    public void testMarshal_ValidLocalDateTime() throws Exception
    {
        LocalDateTime dateTime = LocalDateTime.of(2025, 4, 7, 12, 34, 56);
        String expected = "2025-04-07T12:34:56";
        String actual = adapter.marshal(dateTime);
        assertEquals(expected, actual, "Marshalling should return the expected string");
    }

    /**
     * Test that passing an invalid string to unmarshal throws an exception.
     */
    @Test
    public void testUnmarshal_InvalidDateString()
    {
        String invalidDate = "invalid-date";
        assertThrows(Exception.class, () -> adapter.unmarshal(invalidDate), "Unmarshalling an invalid date string should throw an exception");
    }

    /**
     * Test that calling marshal(null) results in a NullPointerException being thrown.
     */
    @Test
    public void testMarshal_Null()
    {
        assertThrows(NullPointerException.class, () -> adapter.marshal(null), "Marshalling null should throw NullPointerException");
    }
}
