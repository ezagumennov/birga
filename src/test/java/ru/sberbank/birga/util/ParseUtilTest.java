package ru.sberbank.birga.util;

import org.junit.Test;
import ru.sberbank.birga.entities.Client;
import ru.sberbank.birga.entities.Operation;
import ru.sberbank.birga.entities.Order;
import ru.sberbank.birga.entities.Paper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParseUtilTest {
    private Client client = new Client("C1", 100, 1, 2, 3, 4);

    @Test
    public void parseClientTest() {
        assertEquals(client, ParseUtil.parseClient("C1\t100\t1\t2\t3\t4"));
    }

    @Test
    public void clientToStringTest() {
        assertEquals("C1\t100\t1\t2\t3\t4", ParseUtil.clientToString(client));
    }

    @Test
    public void parseOperationTest() {
        assertEquals(Operation.B, ParseUtil.parseOperation("b"));
        assertEquals(Operation.S, ParseUtil.parseOperation("s"));
        assertNull(ParseUtil.parseOperation("x"));
    }


    @Test
    public void parseOrderTest() {
        Order expectedOrder1 = new Order("C1", Operation.B, Paper.A, 1, 2);
        Order expectedOrder2 = new Order("C2", Operation.S, Paper.B, -1, 20);
        assertEquals(expectedOrder1, ParseUtil.parseOrder("C1\tb\tA\t1\t2"));
        assertEquals(expectedOrder2, ParseUtil.parseOrder("C2\ts\tB\t-1\t20"));
    }

    @Test
    public void parsePaperTest() {
        assertEquals(Paper.A, ParseUtil.parsePaper("A"));
        assertEquals(Paper.B, ParseUtil.parsePaper("B"));
        assertEquals(Paper.C, ParseUtil.parsePaper("C"));
        assertEquals(Paper.D, ParseUtil.parsePaper("D"));
        assertNull(ParseUtil.parsePaper("X"));
    }
}
