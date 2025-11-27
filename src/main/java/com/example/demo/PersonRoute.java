
package com.example.demo.routes;

import com.example.demo.model.Person;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class PersonRoute extends RouteBuilder {

    @Override
    public void configure() {

        restConfiguration()
                .component("undertow")
                .host("0.0.0.0")
                .port(8080)
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");


        rest("/api/persons")
            .get().outType(Person[].class).to("direct:listPersons")
            .post().type(Person.class).outType(Person.class).to("direct:createPerson")
            .get("/{id}").outType(Person.class).to("direct:getPerson")
            .put("/{id}").type(Person.class).outType(Person.class).to("direct:updatePerson")
            .delete("/{id}").outType(String.class).to("direct:deletePerson");

        from("direct:listPersons")
                .bean("personController", "list");

        from("direct:createPerson")
                .bean("personController", "create");

        from("direct:getPerson")
                .bean("personController", "get");

        from("direct:updatePerson")
                .bean("personController", "update");

        from("direct:deletePerson")
                .bean("personController", "delete");
    }
}
