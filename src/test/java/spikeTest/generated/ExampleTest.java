package spikeTest.generated;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import spike.Example;
import spike.generated.ExampleTestDataRetriever;

public class ExampleTest {

    private ExampleTestDataRetriever data;

    @BeforeClass
    public void init() {
        data = new ExampleTestDataRetriever();
    }

    @After
    public void reset() {
        data.reset();
    }

    @Test
    public void testExample() {
        while (data.hasNext(0)) {
            Example example = data.getExample();
            fail("Not yet implemented");
            data.next();
        }
    }

    @Test
    public void testExampleInt() {
        while (data.hasNext(1)) {
            Example example = data.getExample();
            fail("Not yet implemented");
            data.next();
        }
    }

    @Test
    public void testExampleIntInt() {
        while (data.hasNext(2)) {
            data.getExample();
            fail("Not yet implemented");
            data.next();
        }
    }

    @Test
    public void testM1() {
        while (data.hasNext()) {
            assertEquals(data.getM1Result(), data.getExample().m1());
            data.next();
        }
    }

    @Test
    public void testM1Int() {
        while (data.hasNext()) {
            assertEquals(data.getM1IntResult(), data.getExample().m1(data.getM1IntX()));
            data.next();
        }
    }

    @Test
    public void testM1Float() {
        while (data.hasNext()) {
            assertEquals(data.getM1FloatResult(), data.getExample().m1(data.getM1FloatX()));
            data.next();
        }
    }

    @Test
    public void testM1Double() {
        while (data.hasNext()) {
            assertEquals(data.getM1DoubleResult(), data.getExample().m1(data.getM1DoubleX()));
            data.next();
        }
    }

    @Test
    public void testM1Boolean() {
        while (data.hasNext()) {
            assertEquals(data.getM1BooleanResult(), data.getExample().m1(data.getM1BooleanX()));
            data.next();
        }
    }

    @Test
    public void testM1String() {
        while (data.hasNext()) {
            assertEquals(data.getM1StringResult(), data.getExample().m1(data.getM1StringX()));
            data.next();
        }
    }

    @Test
    public void testM1IntInt() {
        while (data.hasNext()) {
            assertEquals(data.getM1IntIntResult(), data.getExample().m1(data.getM1IntIntX(), data.getM1IntIntY()));
            data.next();
        }
    }

}
