package pl.kaminski.okeapp.list;




//import com.example.application.data.entity.Company;
//import com.example.application.data.entity.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;


public class ContactForm extends FormLayout {
        TextField firstName = new TextField("firstName");
        TextField lastName = new TextField("lastName");
        TextField pesel = new TextField("pesel");
        EmailField emailAdress = new EmailField("emailAdress");
        EmailField qualification = new EmailField("qualification");
        EmailField partOfQualification = new EmailField("partOfQualification");
        EmailField randomNumber = new EmailField("randomNumber");


//        ComboBox<Status> status = new ComboBox<>("Status");
//        ComboBox<Company> company = new ComboBox<>("Company");

        Button save = new Button("Save");
        Button delete = new Button("Delete");
        Button close = new Button("Cancel");

        public ContactForm() {
            addClassName("contact-form");

            add(firstName,
                    lastName,
                    pesel,
                    emailAdress,
                    qualification,
                    partOfQualification,


                    createButtonsLayout());
        }

        private Component createButtonsLayout() {
            save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
            close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

            save.addClickShortcut(Key.ENTER);
            close.addClickShortcut(Key.ESCAPE);

            return new HorizontalLayout(save, delete, close);
        }
    }

