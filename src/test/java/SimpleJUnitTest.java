import org.junit.jupiter.api.*;

public class SimpleJUnitTest {
    int result;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("### BeforeAllTests()\n");
    }

    @BeforeEach
    void beforeEachTest() {
        System.out.println("###   BeforeEachTest()");
        result = getResult();
    }

    @AfterEach
    void afterEachTest () {
        System.out.println("###   AfterEachTest()\n");
        result = 0;
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("### AfterAllTests()\n");
    }

    @Test
    void firstTest() {
        System.out.println("###     FirstTest()");
        Assertions.assertTrue(result > 2);
    }

    @Test
    void secondTest() {
        System.out.println("###     SecondTest()");
        Assertions.assertTrue(result > 2);
    }

    @Test
    void thirdTest() {
        System.out.println("###     ThirdTest()");
        Assertions.assertTrue(result > 2);
    }

    private int getResult() {
        return 3;
    }
}
