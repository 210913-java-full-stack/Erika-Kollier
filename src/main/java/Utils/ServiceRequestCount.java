package Utils;

public abstract class ServiceRequestCount {
    // The request count for this session
    private static int requestCount = 0;

    public static void increment(){
        requestCount++;
    }

    public static int getRequestCount(){
        return requestCount;
    }
}
