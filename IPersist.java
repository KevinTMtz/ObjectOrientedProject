public interface IPersist {
    abstract void persist(Recurso[] resource);
    abstract Recurso retrieve();
}