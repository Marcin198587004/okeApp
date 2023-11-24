package pl.kaminski.okeapp;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.bind.annotation.GetMapping;

//@Route
//public class FirstPageFormGui extends VerticalLayout {
//
//public FirstPageFormGui(){
//    TextField placeHolderField = new TextField();
//    placeHolderField.setPlaceholder("Placholder");
//add(placeHolderField);
//}

@Route("potwierdzenie")
public class FirstPageFormGui extends Div {

    private Span status;

    public FirstPageFormGui() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        status = new Span();
        status.setVisible(false);

        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader("Rozpoczynasz wniosek o wgląd egzaminu zawodowego");
        dialog.setText(
                "Czy zdawałeś egzamin w województwie mazowieckim?");

        dialog.setCancelable(true);
        dialog.setCancelText("NIE");
        dialog.addCancelListener(event -> setText("spadaj do innego OKE"));

//        dialog.setRejectable(true);
//        dialog.setRejectText("NIE");
//        dialog.addRejectListener(event -> setText("spadaj do innego OKE"));

        dialog.setConfirmText("TAK");
        dialog.addConfirmListener(event -> setStatus("potwierdzone"));

       dialog.setConfirmButton("TAK, przejdź do formularza", confirmEvent -> {
           ;UI.getCurrent().navigate("formgui");

       Button button = new Button();
//        button.addClickListener(event -> {
//            UI.getCurrent().navigate("formgui");
            //dialog.open();
            status.setVisible(false);
        });

        dialog.open();

        layout.add( status);
        add(layout);

        // Center the button within the example
        getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                .set("bottom", "0").set("left", "0").set("display", "flex")
                .set("align-items", "center").set("justify-content", "center");
    }

    private void setStatus(String value) {
        status.setText("Status: " + value);
        status.setVisible(true);
    }

}




