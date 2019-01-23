// A shared resource. Its value attribute should only
// be adjusted by +1 or -1 via setValue().
// Used to illustrate race hazards.
class ResourceSync {
    private volatile int value;

    public synchronized int getValue() {
        return value;
    }

    // Value should only be changed by 1
    public synchronized void setValue(boolean bool) {
        if(bool) {
            value++;
        } else {
            value--;
        }
    }
}