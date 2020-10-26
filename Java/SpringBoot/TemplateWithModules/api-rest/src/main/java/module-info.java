module com.gabeharms.projectName.api.rest {
    requires com.fasterxml.jackson.databind;
    requires com.gabeharms.projectName.service.dummy;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;

    uses com.gabeharms.projectName.service.dummy.DummyService;
    uses com.gabeharms.projectName.service.dummy.Dummy;
}
