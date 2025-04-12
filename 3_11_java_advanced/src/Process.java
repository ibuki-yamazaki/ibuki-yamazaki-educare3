public class Process {
    private Status status;

    public Process() {
        this.status = Status.START;
    }

    public void progress() {
        if (this.status == Status.START) {
            this.status = Status.DONE;
        } else if (this.status == Status.DONE) {
            this.status = Status.STOP;
        }
    }

    public String getStatus() {
        return this.status.getStatusDescription();
    }
}
