
public enum Status {
    START, DONE, STOP;

    public String getStatusDescription() {
        switch (this) {
            case START:
                return "プロセスが開始されました";
            case DONE:
                return "プロセスが進行中です";
            case STOP:
                return "プロセスが終了しました";
            default:
                return "プロセスに異常が発生しました";
        }
    }
}
