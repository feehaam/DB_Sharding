package playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import playground.entity.Entity1;

@Service
public class ClientMasterService {

    @Autowired
    private ClientMasterRepository clientMasterRepository;

    public String getClientNames(String client) {
        switch (client) {
            case "db1":
                DBContextHolder.setCurrentDb(ClientNames.DB1);
                break;
            case "db2":
                DBContextHolder.setCurrentDb(ClientNames.DB2);
                break;
            case "db3":
                DBContextHolder.setCurrentDb(ClientNames.DB3);
                break;
        }
        Entity1 e1 = clientMasterRepository.findByEntity1Name("John Doe");
        if(e1 != null) {
            return "found in database: " + client + " with id " + e1.getId();
        }
        return "found in " + client + " nada!";
    }
}