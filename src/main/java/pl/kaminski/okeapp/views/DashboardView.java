package pl.kaminski.okeapp.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import pl.kaminski.okeapp.service.ContactService;

@Route(value ="dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Vaadin CRM")
public class DashboardView extends VerticalLayout {

    private ContactService service;

    public DashboardView (ContactService service) {
        this.service = service;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getContactStats(), getCompaniesChart());
    }

    private Component getCompaniesChart() {
        Span stats = new Span(service.countContact() + "contact");
        stats.addClassNames("text-xl","mt-m");

        return stats;
    }

    private Component getContactStats(){
        return null;
    }


}
