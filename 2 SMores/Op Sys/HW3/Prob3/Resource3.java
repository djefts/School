// A shared resource. Its value attribute should only
// be adjusted by +1 or -1 via setValue().
// Used to illustrate race hazards.
class Resource3 {
    private volatile int value;

    public synchronized int getValue() {
        return value;
    }

    // Value should only be changed by 1
    public synchronized void setValue(int v) {
        if(Math.abs(value - v) != 1) {
            System.out.println("Mismatch: " + value + " " + v);
        }
        value = v;
    }
}