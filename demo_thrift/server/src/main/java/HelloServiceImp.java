import com.thrift.HelloService;

public class HelloServiceImp implements HelloService.Iface {

    public String sayHello(String name){

        return "hello client111111111";
    }

}
