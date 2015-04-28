package spikeTest;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import spike.Example;


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
            data.getEjemplo();
            fail("Not yet implemented");
            data.next();
        }
    }

    @Test
    public void testM1() {
        while(data.hasNext()){
            assertEquals(data.getM1Result(), data.getEjemplo().m1());
            data.next();
        }
    }

    @Test
    public void testM1Int() {
        while(data.hasNext()){
            assertEquals(data.getM1IntResult(), data.getEjemplo().m1(data.getM1IntX()));
            data.next();
        }
    }

    @Test
    public void testM1Float() {
        while(data.hasNext()){
            assertEquals(data.getM1FloatResult(), data.getEjemplo().m1(data.getM1FloatX()));
            data.next();
        }
    }

    @Test
    public void testM1Double() {
        while(data.hasNext()){
            assertEquals(data.getM1DoubleResult(), data.getEjemplo().m1(data.getM1DoubleX()));
            data.next();
        }
    }

    @Test
    public void testM1Boolean() {
        while(data.hasNext()){
            assertEquals(data.getM1BooleanResult(), data.getEjemplo().m1(data.getM1BooleanX()));
            data.next();
        }
    }

    @Test
    public void testM1String() {
        while(data.hasNext()){
            assertEquals(data.getM1StringResult(), data.getEjemplo().m1(data.getM1StringX()));
            data.next();
        }
    }

    @Test
    public void testM1IntInt() {
        while(data.hasNext()){
            assertEquals(data.getM1IntIntResult(), data.getEjemplo().m1(data.getM1IntIntX(), data.getM1IntIntY()));
            data.next();
        }
    }
    
    
}
