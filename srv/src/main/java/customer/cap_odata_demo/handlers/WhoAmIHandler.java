package customer.cap_odata_demo.handlers;

import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.request.UserInfo;
import com.sap.cds.services.cds.CdsReadEventContext;
import org.springframework.stereotype.Component;
import org.springframework.security.access.annotation.Secured;

import java.util.Map;

@Component
@ServiceName("CatalogService")
public class WhoAmIHandler implements EventHandler {

    private final UserInfo user;

    public WhoAmIHandler(UserInfo user) {
        this.user = user;
    }

    @Secured({ "cap-odata-demo.User", "cap-odata-demo.batch.read" })
    @On(event = "READ", entity = "CatalogService.Products")
    public void onReadProducts(CdsReadEventContext context) {

        System.out.println(">>> READ Products triggered");

        boolean isUser = user.isAuthenticated() && user.getName() != null;

        System.out.println("User Info: " + user);
        System.out.println("Is Real User: " + isUser);
        System.out.println("User Name: " + user.getName());
        System.out.println("Client ID: " + user.getAdditionalAttribute("client_id"));
        
        if (isUser) {
            System.out.println(">>> Real user accessing: " + user.getName());
        } else {
            System.out.println(">>> Service account accessing: " + user.getAdditionalAttribute("client_id"));
        }
    }

}
