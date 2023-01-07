package com.shakirov.spting_pp.listener;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

@Repository
public class EntityListener {

    @EventListener (condition = "#root.args[0].accessType.name() == 'READ'")  // root.args[0]  можно писать p0, тут мы обращаемся к аргументам переданным в метод, на 0 месте у нас EntityEvent entityEvent
    @Order(10)  // Порядок вызова. Сортированный. Все слушатели имеющие число меньше, вызываются раньше, все кто больше вызываются позже
    public void acceptEntityRead(EntityEvent entityEvent) {
        System.out.println("***********______________Entity: " + entityEvent);
    }
}