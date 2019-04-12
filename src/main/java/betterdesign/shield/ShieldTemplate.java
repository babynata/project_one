package betterdesign.shield;

import java.util.ArrayList;
import java.util.List;

public abstract class ShieldTemplate {

    String request;

    RuleTypeStrategy[] ruleTypeStrategies;

    ResultStrategy resultStrategy;

    abstract boolean hook();

    String getUrl() {
        if("id".equals(request))
        return "id";
        else return "name";
    }

    RuleTypeStrategy[] getExp(String url) {
        if("id".equals(url))
            return new RuleTypeStrategy[]{RuleTypeStrategy.SHILED_ID};
        else
            return new RuleTypeStrategy[]{RuleTypeStrategy.SHILED_ID,RuleTypeStrategy.SHILED_NAME};
    }

    List<ShieldRulesPO> getShieldRules(RuleTypeStrategy ... ruleTypeStrategy) {
        List<ShieldRulesPO> list = new ArrayList<>();
        for(RuleTypeStrategy ruleTypeStrategy1:ruleTypeStrategy){
            list.add(ruleTypeStrategy1.getShieldRules().getShieldRulesPo());
        }
        return list;
    }

    ResultPO getResultPO(ResultStrategy resultStrategy) {
        return resultStrategy.getResult().getResultPO();
    }

    void handleShieldInfo() {
        System.out.println("start handle shield info...");
    }

    abstract void handle();


}
