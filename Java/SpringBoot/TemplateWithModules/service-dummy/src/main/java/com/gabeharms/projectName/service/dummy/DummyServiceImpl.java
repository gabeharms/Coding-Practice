package com.gabeharms.projectName.service.dummy;

import java.util.Collection;
import java.util.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyServiceImpl implements DummyService {

    public DummyServiceImpl() {
    }

    //@Autowired
    //public DummyServiceImpl(DummyRepository dummyRepository) {
    //public DummyServiceImpl(DummyRepository dummyRepository) {
        //this.dummyRepository = dummyRepository;
    //}

    @Override
    public Optional<Dummy> getDummy(Long id) {
        //return dummyRepository.fetchDummy(id);
        return Optional.ofNullable(new Dummy("test"));
    }

    @Override
    public List<Dummy> getDummys(Collection<Long> ids) {
        //return dummyRepository.fetchDummys(ids);
        return new ArrayList<Dummy>(Arrays.asList(new Dummy("test")));
    }

    @Override
    public Dummy createDummy(Dummy dummy) {
        //return dummyRepository.createDummy(dummy);
        return new Dummy("test");
    }

    @Override
    public Dummy updateDummy(Dummy dummy) {
        //return dummyRepository.updateDummy(dummy);
        return new Dummy("test");
    }

    @Override
    public void deleteDummy(Long id) {
        //dummyRepository.deleteDummy(id);
    }
}
