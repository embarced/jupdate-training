package jupdate.modules;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class HelloJaxb {
    public static void main(String[] args) throws JAXBException, IOException {
        JAXBContext.newInstance(Test.class).createMarshaller()
                .marshal(new Test("Demo"), System.out);
        System.out.println(new String(HelloJaxb.class.getClassLoader().getResourceAsStream("file.txt").readAllBytes(), StandardCharsets.UTF_8));
    }
    @XmlRootElement
    public static class Test {
        @XmlElement
        private String value;
        public Test(String value) {
            this.value = value;
        }
        public Test() {}
    }
}