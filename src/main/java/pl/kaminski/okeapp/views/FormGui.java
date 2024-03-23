package pl.kaminski.okeapp.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.select.Select;
import pl.kaminski.okeapp.ConfirmPage;
import pl.kaminski.okeapp.models.Contact;
import pl.kaminski.okeapp.repository.ContactRepository;
import pl.kaminski.okeapp.utils.QualificationUtil;

import java.awt.*;
import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Route(value = "/formgui")
@AnonymousAllowed
public class FormGui extends VerticalLayout {
    private static final Random randomNumber = new Random();
//    Image img = new Image("http://i.imgur.com/GPpnszs.png", "Vaadin Logo");
//        img.setHeight("44px");
//   // addToNavbar(new DrawerToggle(), img);
    //BeanValidationBinder<Form> binder = new BeanValidationBinder<>(Form.class);

    private ContactRepository formRepo;
    private TextField textFieldFirstName;
    private TextField textFieldLastName;
    private TextField textFieldPesel;
    private TextField textFieldPhoneNumber;


    private Button button;
    private LocalTime localTime;
    private LocalDate localDate;
    private UUID uuid;
    Select<String> select = new Select<>();
    Select<String> select2 = new Select<>();

    EmailField field = new EmailField();

//Random randomNumber = new Random();


    @Autowired
    public FormGui(ContactRepository formRepo) throws IOException {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        this.formRepo = formRepo;
        textFieldFirstName = new TextField("imię");
        textFieldFirstName.setMinLength(2);
        textFieldFirstName.setMaxLength(20);
        textFieldFirstName.setPattern("[A-ZŻŹĆĄŚĘŁÓŃ][a-za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*");
        //textFieldName.setPattern("^[A-Z][a-z]+");
        //textFieldName.setClearButtonVisible(true);
        textFieldFirstName.setErrorMessage("popraw imię.Piersza litera imienia powinna byc duża ");


        textFieldLastName = new TextField("nazwisko");
        textFieldLastName.setMinLength(2);
        textFieldLastName.setMaxLength(20);
        textFieldLastName.setPattern("[A-Z][a-za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*");
        //textFieldSurName.setClearButtonVisible(true);
        textFieldLastName.setErrorMessage("popraw nazwisko. Pierwsza litera nazwiska powinna być duża");


        textFieldPesel = new TextField("pesel");
        textFieldPesel.setMinLength(10);
        textFieldPesel.setMaxLength(11);
        textFieldPesel.setPattern("^[0-9]+");
        // textFieldPesel.setClearButtonVisible(true);
        textFieldPesel.setErrorMessage("popraw pesel");


        textFieldPhoneNumber = new TextField("Numer telefonu");
        textFieldPhoneNumber.setPattern("^[0-9]+");
        textFieldPhoneNumber.setMinLength(9);
        textFieldPhoneNumber.setMaxLength(9);
        textFieldPhoneNumber.setErrorMessage("popraw nr telefonu. Numer powinien zaiwerać 9 cyfr. W przypadku numeru stacjonarnego nr kierunkowy bez 0 ");


        select.setLabel("Wybierz kwalifikacje");
        select.setItems(QualificationUtil.readFromFile());


        select2.setLabel("Wybierz część egzaminu");
        select2.setItems("część pisemna", "część praktyczna");


        field.setLabel("adres e-mail");
        field.setHelperText(" xxxxx@nazwa domeny");
        field.setClearButtonVisible(true);
        field.setPrefixComponent(VaadinIcon.ENVELOPE.create());

        field.addValueChangeListener(event -> {
        });


        button = new Button("dodaj");
        button.addClickListener(event -> {
            confirmForm();
        });

        add(textFieldFirstName);
        add(textFieldLastName);
        add(textFieldPesel);
        add(textFieldPhoneNumber);
        add(field);
        add(select);
        add(select2);
        add(button);
    }

    public void addForm() {
        Contact form = new Contact();
        form.setFirstName(textFieldFirstName.getValue());
        form.setLastName(textFieldLastName.getValue());
        form.setPesel(textFieldPesel.getValue());
        form.setPhoneNumber(textFieldPhoneNumber.getValue());
        form.setEmailAdress(field.getValue());
        form.setQualification(select.getValue());
        form.setPartOfQualification(select2.getValue());
        form.setLocalTime(LocalTime.now());
        form.setLocalDate(LocalDate.now());
        form.setUuid(UUID.randomUUID());

        int n = randomNumber.nextInt(1000, 9999);
        form.setRandomNumber(n);

        formRepo.save(form);

        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("Formularz wysłany. Nr zgłoszenia : " + n);

        dialog.setConfirmText("Wróc do strony głównej");
        dialog.addConfirmListener(event -> {
            UI.getCurrent().navigate("");
        });
        UI.getCurrent().addBeforeLeaveListener(event -> {
            dialog.close();
        });

        dialog.open();
    }

    public void confirmForm() {
        Span status;

        status = new Span();
        status.setVisible(false);


        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("Potwierdzenie");
        dialog.setText(
                "Czy wprowadzone dane są poprawne ? ");
        dialog.setCancelText("Wróć do edycji formularza");
        dialog.setCancelable(true);

        dialog.setConfirmText("TAK, Wyślij formularz");
        dialog.addConfirmListener(event -> {
            addForm();
        });

        UI.getCurrent().addBeforeLeaveListener(event -> {
            dialog.close();
        });

        dialog.open();
    }
}


