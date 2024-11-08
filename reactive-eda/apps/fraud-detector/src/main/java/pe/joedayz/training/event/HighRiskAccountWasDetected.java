package pe.joedayz.training.event;

public class HighRiskAccountWasDetected {
    public Long bankAccountId;

    public HighRiskAccountWasDetected() {}

    public HighRiskAccountWasDetected(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
}
