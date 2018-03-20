import com.thrift.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class Main{
    public static void main(String[] args) {

        try {
            TTransport transport;
            transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            HelloService.Client client = new HelloService.Client(protocol);

            System.out.println(client.sayHello("client"));

            int max = 100000;

            Long start = System.currentTimeMillis();

            for (int i = 0; i < max; i++) {
                call(client);
            }

            Long end = System.currentTimeMillis();

            Long elapse = end - start;

            int perform = Double.valueOf(max / (elapse / 1000d)).intValue();

            System.out.print("thrift " + max + " 次RPC调用，耗时：" + elapse + "毫秒，平均" + perform + "次/秒");

            transport.close();

        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void call(HelloService.Client client) throws TException {

        //client.ping();
        //System.out.println("ping()=>" + client.ping());


       client.sayHello("client");
        //System.out.println(client.getPersonList(parameter));
    }
}
