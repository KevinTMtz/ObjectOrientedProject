public interface IPersist {
    /*
    We used used arraylist instead of an array and we made them static,
    so it was not necessary to send them as parameters in the persist() method
    */

    abstract void persist();
    abstract void retrieve();
}