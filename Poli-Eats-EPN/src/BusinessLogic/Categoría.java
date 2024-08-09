package BusinessLogic;

public enum Categoría {
    BEBIDA("Bebida"),
    POSTRE("Postre"),
    SNACK("Snack"),
    COMIDA_RÁPIDA("Comida rápida"),
    FRUTA("Fruta"),
    OTRO("Otro");
    private String categoría;

    Categoría(String categoría) {
    this.categoría = categoría;
    }

    public String getCategoríaString(){
        return categoría;
    }

}
