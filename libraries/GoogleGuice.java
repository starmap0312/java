// Dependency injection container
// 1) a design pattern allowing the removal of hard-coded dependencies
// 2) used to inject dependencie at run-time or compile-time 
// use case
// 1) if a constructor has 12 parameters

import javax.inject.Inject;
import javax.inject.Named;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.AbstractModule;

interface Message {
    boolean send(String msg, String receipient);
}

class Email implements Message {
    public boolean send(String msg, String receipient) {
        System.out.println("Send Email Message: " + msg + " to: " + receipient);
        return true;
    }
}

class Facebook implements Message {
    public boolean send(String msg, String receipient) {
        System.out.println("Send Facebook Message: " + msg + " to: " + receipient);
        return true;
    }
}

class Application {
    private Message message;

    // constructor injection 
    @Inject
    public Application(Message msg) {
        this.message = msg;
    }

    //setter injection
    @Inject
    public void setMessage(Message msg){
        this.message = msg;
    }

    public boolean send(String msg, String rec){ // business logic here
        return this.message.send(msg, rec);
    }
}

class BindingModule extends AbstractModule {
    @Override
    public void configure() {
        this.bind(Message.class).toInstance(new Facebook());
    }
}

public class GoogleGuice {

    public static void main(String[] args) {
        // Guice.createInjector(AbstractModule): create injector & module to bind Message at run-time
        Injector injector = Guice.createInjector(new BindingModule());
        // getInstance([classname]): create instance via the injector at run-time
        Application app = injector.getInstance(Application.class);
        app.send("Hello", "World");
    }
}
