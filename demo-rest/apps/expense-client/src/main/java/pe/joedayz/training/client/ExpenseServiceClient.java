package pe.joedayz.training.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import pe.joedayz.training.model.Expense;

import java.util.Set;


public interface ExpenseServiceClient {

    @GET
    Set<Expense> getAll();

    @POST
    Expense create(Expense expense);
}
