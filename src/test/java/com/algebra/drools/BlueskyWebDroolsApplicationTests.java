package com.algebra.drools;

import com.algebra.drools.model.fact.RuleExecutionObject;
import com.algebra.drools.model.fact.RuleExecutionResult;
import com.algebra.drools.model.fact.TestRule;
import com.algebra.drools.service.DroolsRuleEngineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DroolsApplication.class)
public class BlueskyWebDroolsApplicationTests {

    @Autowired
    DroolsRuleEngineService droolsRuleEngineService;

    /**
     * Date 2017/7/27
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明:  测试规则
     */
    @Test
    public void executeDroolsTest() throws Exception {
        try {
            RuleExecutionObject object = new RuleExecutionObject();
            TestRule test = new TestRule();
            test.setAmount(100);
            test.setScore(10);
            test.setMessage("lihao");
            object.addFactObject(test);
            RuleExecutionResult result = new RuleExecutionResult();
            object.setGlobal("_result",result);

            this.droolsRuleEngineService.excute(object,"test");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
