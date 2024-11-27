package sg.ed.nus.iss.ssf_13w.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import sg.ed.nus.iss.ssf_13w.model.Contact;

@Repository
public class ContactRepository {
    private static final Logger logger = Logger.getLogger(ContactRepository.class.getName());
    private final List<Contact> contacts;
    private final File data;

    public ContactRepository(@Value("${dataDir}") String dataDir) {
        Path p = Paths.get(dataDir);
        if (!Files.exists(p)) {
            try {
                Files.createDirectories(p);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error creating file. ");
            }
        }
        
        p = p.resolve("contacts.csv");
        File f = p.toFile();
        this.data = f;
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error creating csv file.");
            }
        }
        this.contacts = getData(f);
    }

    public List<Contact> getAll() {
        return contacts;
    }

    public void add(Contact c) {
        contacts.add(c);
        saveData();
    }

    private List<Contact> getData(File db) {
        List<Contact> tempContacts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(db))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] dataArr = line.split(",");
                String[] dobArr = dataArr[4].split("-");
                LocalDate dob = LocalDate.of(Integer.parseInt(dobArr[0]), Integer.parseInt(dobArr[1]), Integer.parseInt(dobArr[2]));
                tempContacts.add(
                    new Contact(dataArr[1], dataArr[2], dataArr[3], dob, dataArr[0])
                );
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error reading data.");
        }
        return tempContacts;
    }

    private void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(data, false))) {
            for (Contact c : contacts) {
                bw.write(c.toString());
                bw.newLine();
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error saving data.");
        }
    }
}
