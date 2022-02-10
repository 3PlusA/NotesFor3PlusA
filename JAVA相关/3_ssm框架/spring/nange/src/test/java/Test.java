import com.amateur.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        //传统方法
        Student student = new Student();
        System.out.println(student);

        //IOC容器自动创建对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        applicationContext.getId();
    }
}
