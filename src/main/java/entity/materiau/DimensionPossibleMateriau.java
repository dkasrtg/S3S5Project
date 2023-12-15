package entity.materiau;

public class DimensionPossibleMateriau {
    private int id;
    private int idMateriau;
    private int idDimensionMateriau;

    public DimensionPossibleMateriau() {
    }

    public DimensionPossibleMateriau(int id, int idMateriau, int idDimensionMateriau) {
        setId(id);
        setIdMateriau(idMateriau);
        setIdDimensionMateriau(idDimensionMateriau);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(int idMateriau) {
        this.idMateriau = idMateriau;
    }

    public int getIdDimensionMateriau() {
        return idDimensionMateriau;
    }

    public void setIdDimensionMateriau(int idDimensionMateriau) {
        this.idDimensionMateriau = idDimensionMateriau;
    }
}
