package BusinessLayer.Units;

public class Health {

    private Integer pool;
    private Integer amount;

    public Health(Integer pool, Integer amount){
        this.pool=pool;
        this.amount=amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getPool() {
        return pool;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setPool(Integer pool) {
        this.pool = pool;
    }
}
