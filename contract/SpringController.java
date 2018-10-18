package contract;

import contract.facade.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringController {
    @Autowired
    private Facade facade;
}
