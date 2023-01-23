package jupdate.modules;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class HelloJaxb {
    public static void main(String[] args) throws JAXBException {
        JAXBContext.newInstance(Test.class).createMarshaller()
                .marshal(new Test("Demo"), System.out);
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