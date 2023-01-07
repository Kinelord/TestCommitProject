package com.shakirov.http.controller.async;

import com.shakirov.model.websocket.Employee;
import com.shakirov.service.websocket.RabbitMQSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


//@RestController
@RequestMapping(value = "/api/rabbitmq/")
@RequiredArgsConstructor
public class RabbitMQWebController {


    private final AmqpTemplate amqpTemplate;
    private final RabbitMQSenderService rabbitMQSenderService;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("empName") String empName,
                           @RequestParam("empId") String empId) {

        Employee emp=new Employee();
        emp.setEmpId(empId);
        emp.setEmpName(empName);
        rabbitMQSenderService.send(emp);

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

    @GetMapping(value = "/producerForDLQ")
    public String producer(@RequestParam("empName") String empName,
                           @RequestParam("empId") String empId,
                           @RequestParam("salary") int salary) {
        Employee emp=new Employee();
        emp.setEmpId(empId);
        emp.setEmpName(empName);
        emp.setSalary(salary);

        rabbitMQSenderService.sendForDLQ(emp);
        return "Message sent to the RabbitMQ Successfully";
    }

}