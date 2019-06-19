package cool.is.chu.stephen.codefellowship;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.io.File;
import java.nio.file.Files;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CodefellowshipApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// Test front page without being logged in
	@Test
	public void testCodefellowship_nologin() throws Exception {
		File login = new ClassPathResource("unittest/codefellowshipnologin.html").getFile();
		String html = new String(Files.readAllBytes(login.toPath()));

		this.mockMvc
				.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("codefellowship"))
				.andExpect(content().string(html));
				//.andDo(print());
	}
}