package BusinessLogic;


// Aplicamos el concepto de Genérico
public class NodoListaSimple<TIPO> {

    private TIPO tipo;
    private NodoListaSimple<TIPO> liga;


    // Aplicamos Polimorfismo en los constructores
    public NodoListaSimple(TIPO tipo, NodoListaSimple<TIPO> liga){
        this.tipo = tipo;
        this.liga = liga;
    }
    public NodoListaSimple(TIPO tipo){
        this.tipo = tipo;
        this.liga = null;
    }
    public NodoListaSimple(){
        super();
    }

    public TIPO getDato(){
        return this.tipo;
    }

    public void setDato(TIPO tipo) {
        this.tipo = tipo;
    }

    public NodoListaSimple<TIPO> getLiga(){
        return this.liga;
    }

    public void setLiga(NodoListaSimple<TIPO> liga){
        this.liga = liga;
    }
}