package com.algebra.drools.web;

import com.algebra.drools.model.fact.RuleExecutionObject;
import com.algebra.drools.model.fact.RuleExecutionResult;
import com.algebra.drools.model.fact.TestRule;
import com.algebra.drools.service.DroolsRuleEngineService;
import com.algebra.drools.util.DroolsUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author al
 * @date 2019/12/31 11:11
 * @description
 */
@RestController
@RequestMapping("/test")
@Api("drools 测试类")
public class TestController {

    @Resource
    private DroolsRuleEngineService droolsRuleEngineService;

    @PostMapping("/testAction1")
    @ApiOperation("testAction1")
    public String testAction1(@RequestBody TestRule testRule, @RequestParam("scene") String scene){
        String result = "";
        try {
            System.out.println(JSONObject.toJSON(testRule));
            RuleExecutionObject object = new RuleExecutionObject();
            object.addFactObject(testRule);
            RuleExecutionResult ruleResult = new RuleExecutionResult();
            object.setGlobal("_result",ruleResult);
            RuleExecutionObject ruleExecutionObject = droolsRuleEngineService.excute(object, scene);
            result = JSONObject.toJSONString(ruleExecutionObject.getFactObjectList().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/clearRuleMap")
    @ApiOperation("clearRuleMap")
    public void clearRuleMap(){
        DroolsUtil.clearRuleMap();
    }

}
