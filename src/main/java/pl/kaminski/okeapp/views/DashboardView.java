package pl.kaminski.okeapp.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.kaminski.okeapp.service.CrmService;

@Route(value ="dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | Vaadin CRM")
public class DashboardView extends VerticalLayout {

    private CrmService service;

    public DashboardView (CrmService service) {
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
