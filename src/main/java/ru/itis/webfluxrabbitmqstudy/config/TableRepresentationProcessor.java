package ru.itis.webfluxrabbitmqstudy.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.webfluxrabbitmqstudy.controller.TableController;
import ru.itis.webfluxrabbitmqstudy.entity.Table;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TableRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Table>> {
    @Override
    public EntityModel<Table> process(EntityModel<Table> model) {
        Table table = model.getContent();
        if (table != null && !table.isBooked()) {
            model.add(linkTo(methodOn(TableController.class).book(table.getId())).withRel("book"));
        }
        return model;
    }
}
