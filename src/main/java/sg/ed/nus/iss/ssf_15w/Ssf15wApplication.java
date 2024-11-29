package sg.ed.nus.iss.ssf_15w;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class Ssf15wApplication {
	private static final Logger logger = Logger.getLogger(Ssf15wApplication.class.getName());

	@Bean
	public CommonsRequestLoggingFilter log() {
		CommonsRequestLoggingFilter logger = new CommonsRequestLoggingFilter();
		logger.setIncludeClientInfo(true);
		logger.setIncludeQueryString(true);
		return logger;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Ssf15wApplication.class);

		// ApplicationArguments cliOpts = new DefaultApplicationArguments(args);
		// if (!cliOpts.containsOption("dataDir")) {
		// 	logger.severe("Provide --dataDir flag to specify directory to use.");
		// 	System.exit(-1);
		// }

		// String dataDir = cliOpts.getOptionValues("dataDir").getFirst();
		// Properties properties = new Properties();
		// properties.put("dataDir", dataDir);
		// app.setDefaultProperties(properties);
		app.run(args);

	}

}
