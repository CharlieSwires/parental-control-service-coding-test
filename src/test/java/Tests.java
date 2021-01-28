import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.bskyb.internettv.parental_control_service.ParentalControlServiceImpl;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;



public class Tests {
    private ParentalControlServiceImpl pcsi;
    @Before
    public void setUp() throws Exception {
        pcsi = new ParentalControlServiceImpl();
    }

    @Test
    public void test1() throws Exception {
        assertTrue("18Alien", pcsi.canWatchMovie("18", "Alien"));
        assertTrue("15Alien",!pcsi.canWatchMovie("15", "Alien"));

    }
    @Test
    public void test2() throws Exception {
        assertTrue("18Star Wars",pcsi.canWatchMovie("18", "Star Wars"));
        assertTrue("12Star Wars",pcsi.canWatchMovie("12", "Star Wars"));
        assertTrue("PGStar Wars",!pcsi.canWatchMovie("PG", "Star Wars"));
    }
    @Test
    public void test3() throws Exception {
        assertTrue("18Star Wars",pcsi.canWatchMovie("18", "Star Wars"));
        assertTrue("12Star Wars",pcsi.canWatchMovie("12", "Star Wars"));
        assertTrue("UStar Wars",!pcsi.canWatchMovie("U", "Star Wars"));
    }
    @Test
    public void test4() {
        try {
            assertTrue("UGodzilla",pcsi.canWatchMovie("U", "Godzilla"));
        } catch (Exception e) {

            assertTrue("TitleNotFoundException",e.getClass().equals(TitleNotFoundException.class));
        }
    }
    @Test
    public void test5() {
        try {
            assertTrue("null"
                    + "Godzilla",pcsi.canWatchMovie(null, "Godzilla"));
        } catch (Exception e) {

            assertTrue("TechnicalFailureException",e.getClass().equals(TechnicalFailureException.class));
        }
    }
    @Test
    public void test6() {
        try {
            assertTrue("Unull",pcsi.canWatchMovie("U", null));
        } catch (Exception e) {

            assertTrue("TechnicalFailureException",e.getClass().equals(TechnicalFailureException.class));
        }
    }
    @Test
    public void test7() {
        try {
            assertTrue("16Star Wars",pcsi.canWatchMovie("16", "Star Wars"));
        } catch (Exception e) {
            assertTrue("TechnicalFailureException",e.getClass().equals(TechnicalFailureException.class));
        }
    }

}
