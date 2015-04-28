package spikeTest;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import spike.Ejemplo;


public class EjemploTest {
    
    private EjemploTestData data;
    
    @BeforeClass
    public void init() {
        data = new EjemploTestData();
    }
    
    @After
    public void reset() {
        data.reset();
    }

    @Test
    public void testEjemplo() {
        while(data.hasNext()){
            fail("Not yet implemented");
            data.next();
        }
    }

    @Test
    public void testM1() {
        while(data.hasNext()){
            Ejemplo ejemplo = new Ejemplo();
            assertEquals(data.getM1Result(), ejemplo.m1());
            data.next();
        }
    }

    @Test
    public void testM1Int() {
        fail("Not yet implemented");
    }

    @Test
    public void testM1Float() {
        fail("Not yet implemented");
    }

    @Test
    public void testM1Double() {
        fail("Not yet implemented");
    }

    @Test
    public void testM1Boolean() {
        fail("Not yet implemented");
    }

    @Test
    public void testM1String() {
        fail("Not yet implemented");
    }

    @Test
    public void testM1IntInt() {
        fail("Not yet implemented");
    }

}
