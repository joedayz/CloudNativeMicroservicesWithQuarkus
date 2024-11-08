package pe.joedayz.training.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import pe.joedayz.training.model.Expense;

import java.util.Set;

@Path("/expenses")
@RegisterRestClient(configKey = "expense-service")
public interface ExpenseServiceClient {

    @GET
    Set<Expense> getAll();

    @POST
    Expense create(Expense expense);
}
