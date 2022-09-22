// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.eventgrid.models;

import com.azure.core.annotation.Fluent;
import com.azure.resourcemanager.eventgrid.fluent.models.EventHubEventSubscriptionDestinationProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;

/** Information about the event hub destination for an event subscription. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "endpointType")
@JsonTypeName("EventHub")
@Fluent
public final class EventHubEventSubscriptionDestination extends EventSubscriptionDestination {
    /*
     * Event Hub Properties of the event subscription destination.
     */
    @JsonProperty(value = "properties")
    private EventHubEventSubscriptionDestinationProperties innerProperties;

    /**
     * Get the innerProperties property: Event Hub Properties of the event subscription destination.
     *
     * @return the innerProperties value.
     */
    private EventHubEventSubscriptionDestinationProperties innerProperties() {
        return this.innerProperties;
    }

    /**
     * Get the resourceId property: The Azure Resource Id that represents the endpoint of an Event Hub destination of an
     * event subscription.
     *
     * @return the resourceId value.
     */
    public String resourceId() {
        return this.innerProperties() == null ? null : this.innerProperties().resourceId();
    }

    /**
     * Set the resourceId property: The Azure Resource Id that represents the endpoint of an Event Hub destination of an
     * event subscription.
     *
     * @param resourceId the resourceId value to set.
     * @return the EventHubEventSubscriptionDestination object itself.
     */
    public EventHubEventSubscriptionDestination withResourceId(String resourceId) {
        if (this.innerProperties() == null) {
            this.innerProperties = new EventHubEventSubscriptionDestinationProperties();
        }
        this.innerProperties().withResourceId(resourceId);
        return this;
    }

    /**
     * Get the deliveryAttributeMappings property: Delivery attribute details.
     *
     * @return the deliveryAttributeMappings value.
     */
    public List<DeliveryAttributeMapping> deliveryAttributeMappings() {
        return this.innerProperties() == null ? null : this.innerProperties().deliveryAttributeMappings();
    }

    /**
     * Set the deliveryAttributeMappings property: Delivery attribute details.
     *
     * @param deliveryAttributeMappings the deliveryAttributeMappings value to set.
     * @return the EventHubEventSubscriptionDestination object itself.
     */
    public EventHubEventSubscriptionDestination withDeliveryAttributeMappings(
        List<DeliveryAttributeMapping> deliveryAttributeMappings) {
        if (this.innerProperties() == null) {
            this.innerProperties = new EventHubEventSubscriptionDestinationProperties();
        }
        this.innerProperties().withDeliveryAttributeMappings(deliveryAttributeMappings);
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
        if (innerProperties() != null) {
            innerProperties().validate();
        }
    }
}
