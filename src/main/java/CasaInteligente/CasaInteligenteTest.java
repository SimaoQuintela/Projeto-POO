package CasaInteligente;

import static org.junit.jupiter.api.Assertions.*;

import CasaInteligente.SmartDevices.SmartBulb;
import CasaInteligente.SmartDevices.SmartSpeaker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CasaInteligenteTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CasaInteligenteTest {
    /**
     * Default constructor for test class CasaInteligenteTest
     */
    public CasaInteligenteTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testConstructor() {
        CasaInteligente casaInte1 = new CasaInteligente();
        assertTrue(casaInte1!=null);
        casaInte1 = new CasaInteligente("Campus de Gualtar");
        assertTrue(casaInte1!=null);
    }
    
    @Test
    public void testAddFindDevice() {
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar");
        SmartBulb smartBul1 = new SmartBulb("b1");
        SmartSpeaker smartSpe1 = new SmartSpeaker("s1", 0);
        assertFalse(casaInte1.existsDevice("b1"));
        assertFalse(casaInte1.existsDevice("s1"));
        casaInte1.addDevice(smartBul1, "sala");
        assertTrue(casaInte1.existsDevice("b1"));
        casaInte1.addDevice(smartSpe1, "cozinha");
        assertTrue(casaInte1.existsDevice("s1"));
        assertTrue(casaInte1.existsDevice("b1"));
    }

    @Test
    public void testGetDevice() {
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar");
        SmartBulb smartBul1 = new SmartBulb("b1");
        casaInte1.addDevice(smartBul1, "sala");
        assertTrue(casaInte1.getDevice("b1").equals(smartBul1));
    }

    @Test
    public void testSetOn() {
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar");
        SmartBulb smartBul1 = new SmartBulb("b1");
        assertFalse(smartBul1.getOn());
        casaInte1.addDevice(smartBul1, "cozinha");
        smartBul1.setOn(true);
        assertTrue(smartBul1.getOn());
        assertFalse(casaInte1.getDevice("b1").getOn());
    }

    @Test
    public void testSetAllOn() {
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar");
        SmartBulb smartBul1 = new SmartBulb("b1");
        SmartSpeaker smartSpe1 = new SmartSpeaker("s1", 0);
        casaInte1.addDevice(smartBul1, "sala");
        casaInte1.addDevice(smartSpe1, "quarto");
        assertFalse(casaInte1.getDevice("b1").getOn());
        assertFalse(casaInte1.getDevice("s1").getOn());
        casaInte1.setAllOn(true);
        assertTrue(casaInte1.getDevice("b1").getOn());
        assertTrue(casaInte1.getDevice("s1").getOn());
        casaInte1.setAllOn(false);
        assertFalse(casaInte1.getDevice("b1").getOn());
        assertFalse(casaInte1.getDevice("s1").getOn());
    }

    @Test
    public void testAddRoom() {
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar");
        casaInte1.addRoom("sala");
        assertTrue(casaInte1.hasRoom("sala"));
        assertFalse(casaInte1.hasRoom("quarto"));
    }

    @Test
    public void testAddToRoom() {
        CasaInteligente casaInte1 = new CasaInteligente("Gualtar");
        SmartBulb smartBul1 = new SmartBulb("b1");
        SmartSpeaker smartSpe1 = new SmartSpeaker("s1", 0);
        SmartSpeaker smartSpe2 = new SmartSpeaker("s2", 0);
        casaInte1.addDevice(smartBul1, "sotão");
        casaInte1.addDevice(smartSpe1, "sala");
        casaInte1.addDevice(smartSpe2, "cozinha");
        casaInte1.addRoom("sala");
        casaInte1.addRoom("quarto");
        casaInte1.addToRoom("sala", "b1");
        casaInte1.addToRoom("sala", "s1");
        casaInte1.addToRoom("quarto", "s2");
        assertTrue(casaInte1.roomHasDevice("sala", "b1"));
        assertTrue(casaInte1.roomHasDevice("sala", "s1"));
        assertFalse(casaInte1.roomHasDevice("sala", "s2"));
        assertTrue(casaInte1.roomHasDevice("quarto", "s2"));
    }


}

