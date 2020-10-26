package com.gabeharms.projectName.service.dummy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DummyService { 

   Optional<Dummy> getDummy(Long id);
   List<Dummy> getDummys(Collection<Long> ids);
   Dummy createDummy(Dummy dummy);
   Dummy updateDummy(Dummy dummy);
   void deleteDummy(Long id);
}
