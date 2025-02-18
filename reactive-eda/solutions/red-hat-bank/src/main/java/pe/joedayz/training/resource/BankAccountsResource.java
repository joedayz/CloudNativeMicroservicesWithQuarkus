package pe.joedayz.training.resource;

import pe.joedayz.training.event.BankAccountWasCreated;
import pe.joedayz.training.model.BankAccount;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BankAccountsResource {

    @Channel("new-bank-accounts-out")
    Emitter<BankAccountWasCreated> emitter;

    @GET
    public Uni<List<BankAccount>> get() {
        return BankAccount.listAll(Sort.by("id"));
    }

    @POST
    public Uni<Response> create(BankAccount bankAccount) {
        return Panache
                .<BankAccount>withTransaction(bankAccount::persist)
                .onItem()
                .transform(
                    inserted -> {
                        sendBankAccountEvent(inserted.id, inserted.balance);

                        return Response.created(
                                URI.create("/accounts/" + inserted.id)
                        ).build();
                    }
                );
    }

    public void sendBankAccountEvent(Long id, Long balance) {
        emitter.send(new BankAccountWasCreated(id, balance));
    }
}
