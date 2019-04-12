package betterdesign.shield;

public enum ResultStrategy {

    RESULT_MODEL(new ShieldModel()),
    RESULT_RESPONSE(new ShieldResonse());

    private Result result;

    ResultStrategy(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
