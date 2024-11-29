package sg.ed.nus.iss.ssf_15w.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import sg.ed.nus.iss.ssf_15w.model.Contact;

@Repository
public class ContactRepository {
    private static final Logger logger = Logger.getLogger(ContactRepository.class.getName());
    private final Path path;

    public ContactRepository(@Value("${dataDir}") String dataDir) {
        Path p = Paths.get(dataDir);
        if (!Files.exists(p)) {
            try {
                Files.createDirectories(p);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error creating file. ");
            }
        }
        this.path = p;
    }

    public List<Contact> getAll() {
        List<Contact> allContacts = new ArrayList<>();
        try {
            List<File> files = Files.walk(path)
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .toList();
            for (File f : files) {
                BufferedReader in = new BufferedReader(new FileReader(f));
                String data = in.readLine();
                allContacts.add(Contact.of(data));
                in.close();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading data directory");
        }
        return allContacts;
    }

    public void add(Contact c) {
        Path newContactPath = path.resolve(c.getId() + ".txt");
        File newContact = newContactPath.toFile();
        try {
            newContact.createNewFile();
            BufferedWriter in = new BufferedWriter(new FileWriter(newContact));
            in.write(c.toString());
            in.flush();
            in.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating new contact file.");
        }
    }

    public Optional<Contact> findContactById(String id) {
        Path contactPath = path.resolve(id + ".txt");
        File contactFile = contactPath.toFile();

        try {
            BufferedReader in = new BufferedReader(new FileReader(contactFile));
            Contact contact = Contact.of(in.readLine());
            in.close();
            return Optional.of(contact);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error reading file for id " + id);
            return Optional.empty();
        }
    }
}
