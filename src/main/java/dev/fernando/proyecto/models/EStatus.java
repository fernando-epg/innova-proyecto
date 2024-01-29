package dev.fernando.proyecto.models;

public enum EStatus {
    RESERVED("Reserved"),
    AVAILABLE("Available"),
    CANCELLED("Cancelled");
    
    public final String label;
    
    private EStatus(String label) {
        this.label = label;
    }
    
    public static EStatus valueOfLabel(String label) {
        for(EStatus es : values()) {
            if(es.label.equals(label)) {
                return es;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.label;
    }
}
