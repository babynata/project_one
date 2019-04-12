package betterdesign.shield;

public enum RuleTypeStrategy {

    SHILED_ID(new IDRule()),
    SHILED_NAME(new NameRule());

    private ShieldRules shieldRules;

    RuleTypeStrategy(ShieldRules shieldRules) {
        this.shieldRules = shieldRules;
    }

    public ShieldRules getShieldRules() {
        return shieldRules;
    }
}
