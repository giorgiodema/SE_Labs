# Server
- Add these dependencies and plugin to pom file
```xml
    <dependencies>
        <dependency>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-rt-frontend-jaxws</artifactId>
            <version>3.1.6</version>
        </dependency>

        <dependency>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-rt-transports-http-jetty</artifactId>
            <version>3.1.6</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <configuration>
                    <mainClass>com.mycompany.soapserver.Server</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
- Create the Interface that will be exposed by the Web Server, and annotate the interface with `@WebService`
- Create a class that implements the interface and annotate that with `@WebService(endpointInterface="com.mycompany.Interface")` specifying the path of the interface

The enviroment will generate automatically the interface in WSDL, note that all return types of the methods in the web server interface must be simple types or lists of symple types in order to be automatically converted into xml by JAXB. To return complex type we must annotate the class to tell JAXB how to convert the class into xml.
```Java
@XmlType(name="Client")
public class Client{
    
    @XmlElement(name="name")
    final private String name;
    @XmlElement(name="surname")
    final private String surname;
    @XmlElement(name="id")
    final private String id;
```
Note that all these fields are simple types. To use complex types we should implement an adapter, there's an example in the rest section.

# CLient
- Add the following dependencies:
```xml
<dependencies>
    <dependency>
        <groupId>javax.xml</groupId>
        <artifactId>webservices-api</artifactId>
        <version>2.0</version>
    </dependency>
</dependencies>
```
- Then locate the wsdl interface of the webserver, rightclick on the project and go to  New -> Web Service Client. Then insert the address of the wsdl: endpoint/?wsdl. The stub will automatically generate the methods, that can be called simply dragging them in the main procedure from the folder Web Service -> References -> .wsld -> ImplService

