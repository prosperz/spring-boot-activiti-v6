package com.example.springbootactivitiv6.deployment;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * step 01 流程定义的部署
 * 受影响的表
 * act_re_deployment、
 * act_re_procdef、
 * act_ge_bytearray
 */
@SpringBootTest
public class DeploymentTest01 {
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private HistoryService historyService;

    /**
     * 流程定义的部署
     */
    @Test
    public void  addDeployment(){
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource("processes/tran.bpmn20.xml");
        deploymentBuilder.name("tran");
        Deployment deployment = deploymentBuilder.deploy();
        System.out.println(deployment.getName());
        // 初始化流程参数
        Map<String, Object> vars = new HashMap<>();
        vars.put("days", 4);
        // 启动流程
        runtimeService.startProcessInstanceByKey("tran");
        runtimeService.startProcessInstanceByKey("tran", vars);
        runtimeService.startProcessInstanceByKey("tran", "testBusinessKey1");
        runtimeService.startProcessInstanceByKey("tran", "testBusinessKey2", vars);



    }

    @Test
    public void processInstanceQuery(){
        List<HistoricProcessInstance> datas = historyService.createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey("testBusinessKey1")
                .list();
        System.out.println(datas.size());
    }
}
