package sg.ed.nus.iss.ssf_13w.repository;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
    @Value("${dataDir}")
    private static String dataDir;

    private static final Logger logger = Logger.getLogger(ContactRepository.class.getName());

    @Autowired
    public ContactRepository(@Value("${dataDir}") String dataDir) {
        File db = new File(dataDir);
        if (!db.exists()) {
            try {
                db.createNewFile();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error reading file.");
            }
        }
    }
}
