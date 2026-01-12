package customer.cap_odata_demo.handlers;

import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.catalogservice.Products_;

import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;

import org.springframework.stereotype.Component;

// @Component
// @ServiceName("CatalogService")
public class WHandler implements EventHandler {

    // public WHandler() {
    //     System.out.println(">>> WHandler LOADED");
    // }

    // @Before(event = CqnService.EVENT_READ, entity = Products_.CDS_NAME)
    // public void beforeRead(CdsReadEventContext context) {
    //     System.out.println(">>> BEFORE READ Products");
    // }

    // // @On(event = CqnService.EVENT_READ, entity = Products_.CDS_NAME)
    // // public void onReadProducts(CdsReadEventContext context) {
    // //     System.out.println(">>> READ Products triggered");
    // // }
}
