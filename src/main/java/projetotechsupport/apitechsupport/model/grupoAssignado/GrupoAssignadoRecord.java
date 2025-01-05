package projetotechsupport.apitechsupport.model.grupoAssignado;

public record GrupoAssignadoRecord(Long id, String nome) {

    public GrupoAssignadoRecord(GrupoAssignado grupoAssignado) {this(grupoAssignado.getId(), grupoAssignado.getNome());}
}
