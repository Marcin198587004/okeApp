package pl.kaminski.okeapp;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.select.Select;
import pl.kaminski.okeapp.repository.ContactRepository;

import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;


@Route
public class   FormGui extends VerticalLayout {


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

        this.formRepo = formRepo;
        {
            textFieldFirstName = new TextField("imię");
            textFieldFirstName.setMinLength(2);
            textFieldFirstName.setMaxLength(20);
            textFieldFirstName.setPattern("[A-ZŻŹĆĄŚĘŁÓŃ][a-za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*");
            // textFieldName.setPattern("^[A-Z][a-z]+");
            //textFieldName.setClearButtonVisible(true);
            textFieldFirstName.setErrorMessage("popraw imię.Piersza litera imienia powinna byc duża ");

        }
        {
            textFieldLastName = new TextField("nazwisko");
            textFieldLastName.setMinLength(2);
            textFieldLastName.setMaxLength(20);
            textFieldLastName.setPattern("[A-Z][a-za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*");
            //textFieldSurName.setClearButtonVisible(true);
            textFieldLastName.setErrorMessage("popraw nazwisko. Pierwsza litera nazwiska powinna być duża");
        }
        {
            textFieldPesel = new TextField("pesel");
            textFieldPesel.setMinLength(10);
            textFieldPesel.setMaxLength(11);
            textFieldPesel.setPattern("^[0-9]+");
            // textFieldPesel.setClearButtonVisible(true);
            textFieldPesel.setErrorMessage("popraw pesel");
        }
        {
            textFieldPhoneNumber = new TextField("Numer telefonu");
            textFieldPhoneNumber.setPattern("^[0-9]+");
            textFieldPhoneNumber.setMinLength(9);
            textFieldPhoneNumber.setMaxLength(9);
            textFieldPhoneNumber.setErrorMessage("popraw nr telefonu. Numer powinien zaiwerać 9 cyfr. W przypadku numeru stacjonarnego nr kierunkowy bez 0 ");

        }
        {
            select.setLabel("Wybierz kwalifikacje");
            File file = new File("C:\\Users\\marcin.k\\IdeaProjects\\okeApp\\src\\main\\resources\\static\\qualification.txt");
            List<String> items = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    items.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            select.setItems(items);
        }

        {
            select2.setLabel("Wybierz część egzaminu");
            select2.setItems("część pisemna", "część praktyczna");
        }
        {
            field.setLabel("adres e-mail");
            field.setHelperText(" xxxxx@nazwa domeny");
            field.setClearButtonVisible(true);
            field.setPrefixComponent(VaadinIcon.ENVELOPE.create());

            field.addValueChangeListener(event -> {
            });

        }

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
        form.setLastname(textFieldLastName.getValue());
        form.setPesel(textFieldPesel.getValue());
        form.setPhoneNumber(textFieldPhoneNumber.getValue());
        form.setEmailAdress(field.getValue());
        form.setQualification(select.getValue());
        form.setPartOfQualification(select2.getValue());
        form.setLocalTime(Time.valueOf(LocalTime.now()));
        form.setLocaldate(Date.valueOf(LocalDate.now()));

        form.setUuid(UUID.randomUUID());
        Random randomNumber = new Random();
        int n = randomNumber.nextInt(1000,9999);
        form.setRandomNumber(n);

        formRepo.save(form);

        Notification notification = Notification
                .show("Formularz wysłany. Nr zgłoszenia : " + n);

        notification.setPosition(Notification.Position.MIDDLE);
        notification.setDuration(0);
    }

    public void confirmForm() {
        Span status;

        HorizontalLayout layout = new HorizontalLayout();
        status = new Span();
        status.setVisible(false);

        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("potwierdzenie");
        dialog.setText(
                "Czy wprowadzone dane są poprawne ? ");
        dialog.setCancelText("Wróć do edycji formularza");
        dialog.setCancelable(true);
//        dialog.addCancelListener(event -> {
//
//
//            setStatus("Canceled");
   // })
    ;

        dialog.setConfirmButton("TAK, Wyślij formularz", confirmEvent -> {
            addForm();
            UI.getCurrent().navigate("confirmpage");

        });
//        dialog.addConfirmListener(event -> {
//                    setStatus("Saved");
//
//
//                }
//
//        )
        ;

        Button button = new Button("");
        button.addClickListener(event -> {
            dialog.open();

        });

        dialog.open();
    }
//    public void setStatus(String value) {
//        Label status = null;
//        status.setText("Status: " + value);
//        status.setVisible(true);
//
//    }
}


