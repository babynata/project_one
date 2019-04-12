package betterdesign.shield;

public class MyShield extends ShieldTemplate {

    private MyShield(Builder builder) {
        this.request = builder.request;
        this.ruleTypeStrategies = builder.ruleTypeStrategies;
        this.resultStrategy = builder.resultStrategy;
    }

    @Override
    boolean hook() {
        return true;
    }

    @Override
    void handle() {
        if (this.hook()) {
            this.getShieldRules(this.getExp(this.getUrl()));
            this.getResultPO(this.resultStrategy);
            this.handleShieldInfo();
        }
    }

    public static final class Builder {

        private String request;

        private RuleTypeStrategy[] ruleTypeStrategies;

        private ResultStrategy resultStrategy;

        public Builder setRequest(String request) {
            this.request = request;
            return this;
        }

        public Builder setRuleTypeStrategies(RuleTypeStrategy[] ruleTypeStrategies) {
            this.ruleTypeStrategies = ruleTypeStrategies;
            return this;
        }

        public Builder setResultStrategy(ResultStrategy resultStrategy){
            this.resultStrategy = resultStrategy;
            return this;
        }

        public MyShield build() {
            return new MyShield(this);
        }
    }

    public static void main(String[] args){
        MyShield myShield=new MyShield.Builder().setRequest("id&name").setResultStrategy(ResultStrategy.RESULT_MODEL).build();
        myShield.handle();
    }

}
