package s12e02_hospital;

import java.util.Map;

class Paciente {

    String id;
    Map<String, Medico> medicos;

    void addMedico(Medico medico) {
        Medico pmedico = medicos.get(medico.id);
        if (pmedico != null) {
            return;
        }
        medicos.put(medico.id, medico);
        medico.addPaciente(this);
    }

    void delMedico() {

    }

}

class Medico {

    String id;
    Map<String, Paciente> pacientes;

    void addPaciente(Paciente paciente) {
        Paciente mpaciente = pacientes.get(paciente.id);
        if (mpaciente != null) {
            return;
        }
        pacientes.put(paciente.id, paciente);
        paciente.addMedico(this);
    }

    void delPaciente(String idPaciente) {
        Paciente mpaciente = pacientes.get(idPaciente);
        if (mpaciente == null) 
            return;
        

    }
}

class Hospital{


}

class Messenger{
    
    
    
}

public class Controller {

}
