package co.usa.reto3.reto3.service;
import co.usa.reto3.reto3.model.Score;
import co.usa.reto3.reto3.repository.AdminRepository;
import co.usa.reto3.reto3.model.Admin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.getAll();

    }
    public Optional <Admin> getAdmin(int id){
        return adminRepository.getAdmin(id);
    }
    public Admin save (Admin c) {
        if(c.getID()==null) {
            return adminRepository.save(c);
        }
        else {
            Optional<Admin> aux=adminRepository.getAdmin(c.getID());
            if(aux.isEmpty()) {
                return adminRepository.save(c);
            }else {
                return c;
            }
        }

    }
    public Admin update(Admin a){
        if(a.getID()!=null){
            Optional<Admin>d=adminRepository.getAdmin(a.getID());
            if(!d.isEmpty()){
                if (a.getNAME()!=null){
                    d.get().setNAME(a.getNAME());
                }
                if (a.getEMAIL()!=null){
                    d.get().setEMAIL(a.getEMAIL());
                }
                if (a.getPASSWORD()!=null){
                    d.get().setPASSWORD(a.getPASSWORD());
                }
                return adminRepository.save((d.get()));
            }
        }
        return a;
    }
    public boolean deleteAdmin(int id){
        Optional<Admin> c=getAdmin(id);
        if(!c.isEmpty()){
            adminRepository.delete(c.get());
            return true;
        }
        return false;
    }
}
