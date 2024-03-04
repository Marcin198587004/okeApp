package pl.kaminski.okeapp.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import pl.kaminski.okeapp.list.ListView;

import static com.vaadin.flow.router.HighlightCondition.*;

public class MainLayout extends AppLayout {

    public MainLayout(){
createHeader();
createDrawer();
    }

    private void createHeader(){
        H1 logo=new H1("Vaadin CRM");
logo.addClassNames("text-l","m-m" );

    HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
    header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
    header.expand(logo);
    header.setWidthFull();
    header.addClassNames("py-0","px-m");
    addToNavbar(header);


        }
    private void createDrawer() {
        RouterLink listView = new RouterLink("List", ListView.class);
        listView.setHighlightCondition(HighlightConditions.sameLocation());
addToDrawer(new VerticalLayout(
        listView
));

    }
    }