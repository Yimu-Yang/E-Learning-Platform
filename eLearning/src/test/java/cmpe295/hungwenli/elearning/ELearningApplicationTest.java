package cmpe295.hungwenli.elearning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ELearningApplicationTest {

	@Test
	public void testPrint() {
		String output = "test: mvn test";
		assertEquals("test: mvn test", output, "Wrong Result");
	}

}
