package sg.ed.nus.iss.ssf_13w.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import sg.ed.nus.iss.ssf_13w.model.Contact;
import sg.ed.nus.iss.ssf_13w.repository.ContactRepository;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAll() {
        return contactRepository.getAll();
    }

    public void add(Contact c) {
        contactRepository.add(c);
    }

    public Optional<Contact> findContactById(String id) {
        return contactRepository.findContactById(id);
    }

}
