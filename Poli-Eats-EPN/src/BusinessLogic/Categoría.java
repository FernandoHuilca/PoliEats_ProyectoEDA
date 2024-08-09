package BusinessLogic;

public enum Categoría {
    BEBIDA("Bebida"),
    POSTRE("Postre"),
    SNACK("Snack"),
    COMIDA_RÁPIDA("Comida rápida");
    private String categoría;

    Categoría(String categoría) {
    this.categoría = categoría;
    }

    public String getCategoríaString(){
        return categoría;
    }

}
