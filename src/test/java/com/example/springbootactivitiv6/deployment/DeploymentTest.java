package com.example.springbootactivitiv6.deployment;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * step 01 流程定义的部署
 * 受影响的表
 * act_re_deployment、
 * act_re_procdef、
 * act_ge_bytearray
 */
@SpringBootTest
public class DeploymentTest {
    @Resource
    private RepositoryService repositoryService;

    /**
     * 流程定义的部署
     */
    @Test
    public void  addDeployment(){
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource("processes/holiday.bpmn20.xml");
        deploymentBuilder.name("holiday");
        Deployment deployment = deploymentBuilder.deploy();
        System.out.println(deployment.getName());
    }

    @Test
    public void queryProcessDefintion(){
        // 查询流程定义实体
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId("1")
                .singleResult();
        // 输出结果为
        System.out.println(processDefinition.getDiagramResourceName());
    }
}
