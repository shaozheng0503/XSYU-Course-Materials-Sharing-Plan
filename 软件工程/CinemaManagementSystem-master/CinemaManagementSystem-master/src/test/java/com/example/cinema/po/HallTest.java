package com.example.cinema.po;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Hall Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>五月 21, 2019</pre>
 */
public class HallTest {
    private Hall hall;

    @Before
    public void before() throws Exception {
        hall = new Hall();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getParsedSeats()
     */
    @Test
    public void testGetParsedSeats() throws Exception {
        String parsedSeats = "[[0,0,0],[1,0,1]]";
        int[][] seats = new int[2][];
        seats[0] = new int[]{0, 0, 0};
        seats[1] = new int[]{1, 0, 1};
        this.hall.setSeats(parsedSeats);
        Assert.assertEquals(seats, this.hall.getParsedSeats());
    }

    /**
     * Method: setGeneratedSeats(int[][] seats)
     */
    @Test
    public void testSetGeneratedSeats() throws Exception {
        int[][] seats = new int[2][];
        seats[0] = new int[]{0, 0, 0};
        seats[1] = new int[]{1, 0, 1};
        this.hall.setGeneratedSeats(seats);
        Assert.assertEquals("[[0,0,0],[1,0,1]]", this.hall.getSeats());
    }


} 
