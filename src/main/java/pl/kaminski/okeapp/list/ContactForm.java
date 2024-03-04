package pl.kaminski.okeapp.list;




//import com.example.application.data.entity.Company;
//import com.example.application.data.entity.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import jakarta.validation.ValidationException;
import pl.kaminski.okeapp.Contact;

import java.util.List;


public class ContactForm extends FormLayout {
    Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);
    Contact contact = new Contact();
        TextField firstName = new TextField("firstName");
        TextField lastName = new TextField("lastName");
        TextField pesel = new TextField("pesel");
        EmailField emailAdress = new EmailField("emailAdress");
        EmailField qualification = new EmailField("qualification");
        EmailField partOfQualification = new EmailField("PartOfQualification");
        EmailField randomNumber = new EmailField("randomNumber");


//        ComboBox<Status> status = new ComboBox<>("Status");
//        ComboBox<Company> company = new ComboBox<>("Company");

        Button save = new Button("Save");
        Button delete = new Button("Delete");
        Button cancel = new Button("Cancel");

        public ContactForm() {
            addClassName("contact-form");
            binder.bindInstanceFields(this);


            add(
                    firstName,
                    lastName,
                    pesel,
                    emailAdress,
                    qualification,
                    partOfQualification,


                    createButtonsLayout()
            );
        }
public void setContact(Contact contact){
            this.contact = contact;
            binder.readBean(contact);
}
        private Component createButtonsLayout() {
            save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
            cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
save.addClickListener(clickEvent->validateAndSave());
            //save.addClickListener(event-> fireEvent(new SaveEvent(this,contact)));
            delete.addClickListener(event-> fireEvent(new DeleteEvent(this,contact)));
cancel.addClickListener(event-> fireEvent(new CloseEvent(this)));
            save.addClickShortcut(Key.ENTER);
            cancel.addClickShortcut(Key.ESCAPE);

            return new HorizontalLayout(save, delete, cancel);
        }

    private void validateAndSave() {
            try {
                binder.writeBean(contact);
                fireEvent(new SaveEvent(this,contact));
            }catch(ValidationException | com.vaadin.flow.data.binder.ValidationException e ){
                e.printStackTrace();
            }
    }

    // Events
    public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
        private Contact contact;

        protected ContactFormEvent(ContactForm source, Contact contact) {
            super(source, false);
            this.contact = contact;
        }

        public Contact getContact() {
            return contact;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(ContactForm source, Contact contact) {
            super(source, contact);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(ContactForm source, Contact contact) {
            super(source, contact);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(ContactForm source) {
            super(source, null);
        }
    }
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }}

    /*public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }*/


