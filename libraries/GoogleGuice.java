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
import com.google.inject.name.Names;

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
    @Inject                              // field injection (incorrect usage: offensive to object)
    @Named("first")                      // a built-in binding annotation used to distinguish same type injection
    private String firstName;

    @Inject                              // field injection (incorrect usage: offensive to object)
    @Named("last")                       // a built-in binding annotation used to distinguish same type injection
    private String lastName;

    private Message message;

    @Inject                              // constructor injection (correct usage but create extra complexity only)
    public Application(Message msg) {    // a Message instance will be injected at run-time
        this.message = msg;
    }

    @Inject                              // setter injection (incorrect usage: offensive to object)
    public void setMessage(Message msg){ // a Message instance will be injected at run-time 
        this.message = msg;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public boolean send(String msg, String rec){ // write business logic here
        return this.message.send(msg, rec);
    }
}

class BindingModule extends AbstractModule {
    @Override
    public void configure() {
        this.bind(Message.class).toInstance(new Facebook());
        this.bind(String.class).annotatedWith(Names.named("first")).toInstance("John");
        this.bind(String.class).annotatedWith(Names.named("last")).toInstance("Wall");
    }
}

public class GoogleGuice {

    public static void main(String[] args) {
        // Guice.createInjector(AbstractModule): create injector & module to bind Message at run-time
        Injector injector = Guice.createInjector(new BindingModule());
        // getInstance([classname]): create instance via the injector at run-time
        Application app = injector.getInstance(Application.class);
        app.send("Hello", "World");

        System.out.println(app.getName());
    }
}
