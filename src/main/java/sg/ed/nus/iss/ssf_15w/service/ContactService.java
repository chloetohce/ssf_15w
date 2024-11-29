package sg.ed.nus.iss.ssf_15w.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Service;

import sg.ed.nus.iss.ssf_15w.model.Contact;
import sg.ed.nus.iss.ssf_15w.repository.ContactRedisRepository;
import sg.ed.nus.iss.ssf_15w.repository.ContactRepository;
import sg.ed.nus.iss.ssf_15w.utilities.Redis;

@Service
public class ContactService {
    private final ContactRedisRepository contactRepository;

    public ContactService(ContactRedisRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAll() {
        Map<String, String> data = contactRepository.getAll(Redis.KEY_CONTACTS);
        List<Contact> contacts = new ArrayList<>();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String values = entry.getValue();
            String[] valueArr = values.split(",");

            String[] dobArr = valueArr[4].split("-");
            LocalDate dob = LocalDate.of(Integer.parseInt(dobArr[0]), Integer.parseInt(dobArr[1]), Integer.parseInt(dobArr[2]));

            Contact c = new Contact(
                valueArr[1],
                valueArr[2],
                valueArr[3],
                dob,
                valueArr[0]
            );
            contacts.add(c);
        }
        return contacts;
    }

    public void add(Contact c) {
        contactRepository.add(Redis.KEY_CONTACTS, c.toString());
    }

    public Contact findContactById(String id) {
        String rawData = contactRepository.findById(Redis.KEY_CONTACTS, id);
        String[] valueArr = rawData.split(",");

        String[] dobArr = valueArr[4].split("-");
        LocalDate dob = LocalDate.of(Integer.parseInt(dobArr[0]), Integer.parseInt(dobArr[1]),
                Integer.parseInt(dobArr[2]));

        Contact c = new Contact(
                valueArr[1],
                valueArr[2],
                valueArr[3],
                dob,
                valueArr[0]);
        return c;
    }

}
