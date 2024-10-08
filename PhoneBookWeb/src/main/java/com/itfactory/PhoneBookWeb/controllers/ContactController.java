package com.itfactory.PhoneBookWeb.controllers;

import com.itfactory.PhoneBookWeb.model.Contact;
import com.itfactory.PhoneBookWeb.repos.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ContactController {


//accesam repository ul

    private ContactRepository contactRepo;

    //creem setter cu @Autowired sa injectam obiectul creeat automat prin @Repository

    @Autowired
    public void setContactRepo(ContactRepository contactRepo) {
        this.contactRepo = contactRepo;
    }


    //punem metoda care asculta dupa apeluri GET si returneaza lista de contacte

    @GetMapping("/contacts") //mapare la URL "/contacts
    public String getAllContacts(Model modelulMeu){

        List<Contact>listaContacte = contactRepo.getAllContacts(); //apelam metoda prin obiectul creat de spring


        modelulMeu.addAttribute("contacte", listaContacte);

        return "contacts";

    }

    @PostMapping("/contacts")
    //nu mai returnam o pagina html noua, vrem sa ne apeleze acelasi URL ca la metoda GET, sa faca un GET pt lista de contacte - folosim RedirectView
    public RedirectView addContact(Contact newContact){//toate elementele pe care le va lua din formularul HTML vor fi puse automat intr-un obiect de tip contact
        contactRepo.addContact(newContact);

        return new RedirectView("http://localhost/contacts");

    }

    @GetMapping("/deleteContact/{id}")//punem parametru id
    public RedirectView deleteContact(@PathVariable(name="id") int id) {//prin @PathVariable stie sa le diferentieze
        contactRepo.deleteContact(id);

        return new RedirectView("http://localhost/contacts");

    }


}

