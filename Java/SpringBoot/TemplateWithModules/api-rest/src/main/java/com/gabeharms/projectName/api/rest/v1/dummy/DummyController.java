package com.gabeharms.projectName.api.rest.v1.dummy;

import com.gabeharms.projectName.service.dummy.DummyService;
import com.gabeharms.projectName.service.dummy.Dummy;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/v1/dummy")
public class DummyController {

    private DummyService dummyService;

    public DummyController(DummyService dummyService) {
        this.dummyService = dummyService;
    }

    @GetMapping("/{id}")
    public Dummy get(@PathVariable Long id) {
        return dummyService.getDummy(id)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Dummy> get(@RequestParam List<Long> ids) {
        return dummyService.getDummys(ids);
    }

    @PostMapping
    public Dummy create(@RequestBody DummyCreateRequest request) {
        return dummyService.createDummy(new Dummy(request.name));
    }

    @PatchMapping("/{id}")
    public Dummy update(@PathVariable Long id, @RequestBody DummyUpdateRequest request) {
        return dummyService.updateDummy(new Dummy(request.name));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dummyService.deleteDummy(id);
    }

}
