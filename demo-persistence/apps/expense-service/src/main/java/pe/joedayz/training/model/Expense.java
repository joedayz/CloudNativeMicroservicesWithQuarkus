package pe.joedayz.training.model;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

// TODO: Add @Entity annotation and extend PanacheEntity
public class Expense {

    public enum PaymentMethod {
        CASH, CREDIT_CARD, DEBIT_CARD,
    }

    @NotNull
    public UUID uuid;
    public String name;

    @JsonbDateFormat(value = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime creationDate;
    public PaymentMethod paymentMethod;
    public BigDecimal amount;

    // TODO: Add many-to-one relationship between expense and associate
    public Associate associate;

    // TODO: Annotate the associateId with @Column
    public Long associateId;

    // TODO: Add a no-argument constructor

    public Expense(UUID uuid, String name, LocalDateTime creationDate,
                   PaymentMethod paymentMethod, String amount, Associate associate) {
        this.uuid = uuid;
        this.name = name;
        this.creationDate = creationDate;
        this.paymentMethod = paymentMethod;
        this.amount = new BigDecimal(amount);
        this.associate = associate;
        // TODO: Add associateId association
    }

    public Expense(String name, PaymentMethod paymentMethod, String amount, Associate associate) {
        this(UUID.randomUUID(), name, LocalDateTime.now(), paymentMethod, amount, associate);
    }

    @JsonbCreator
    public static Expense of(String name, PaymentMethod paymentMethod, String amount, Long associateId) {

        // TODO: Update regarding the new relationship
        return new Expense(name, paymentMethod, amount, null);
    }

    // TODO: Add update() method

}
