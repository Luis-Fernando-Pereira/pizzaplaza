package br.com.pizzaplaza.orderservice.services;

import br.com.pizzaplaza.orderservice.dtos.OrderStatusEventDto;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderStatusNotifier {

    private final BroadcastProcessor<OrderStatusEventDto> processor = BroadcastProcessor.create();

    public Multi<OrderStatusEventDto> stream() {
        return processor;
    }

    public void emit(OrderStatusEventDto event) {
        processor.onNext(event);
    }
}
