package com.shakirov.spting_pp.listener;

import lombok.Getter;

import java.util.EventObject;



public class EntityEvent extends EventObject {

    @Getter
    private final AccessType accessType;

    /**
     * Constructs a prototypical Event.
     *
     * @param source     the object on which the Event initially occurred
     * @param accessType
     * @throws IllegalArgumentException if source is null
     */
    public EntityEvent(Object source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
    }

}
